import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

import org.junit.Assert;

public class ETHTestRunner {

	private static final boolean USE_REFLECTION = true;
	private static final String TEST_SPECIFIC_CLASS = null;

	private PrintStream onErrorLog;
	private ByteArrayOutputStream onErrorLogOut;

	public boolean equals(String first, String second) {
		return first == second || first != null && first.equals(second);
	}

	public void run(ETHTestCase ethTestCase) throws FileNotFoundException, IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {

		// redirect io streams
		PrintStream oldOut = System.out;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			System.setOut(new PrintStream(baos));
			System.setIn(getInputStream(ethTestCase));

			// run your code
			if (USE_REFLECTION) {
				if (!callMainMethod(ethTestCase)) {
					// TODO: find a better solution?
					System.err.println("Ignoring test " + ethTestCase.getDir().getName());
					return;
				}

			} else {
				// If you don't trust reflection, do it manually here... ;-)
				// A1.main(new String[0]);
			}

		} finally {
			// revert io streams
			System.setOut(oldOut);
		}

		initErrorLog();

		// validate with test sets
		onErrorLog.println("############################################");
		onErrorLog.println("Input:");

		Scanner inputScanner = new Scanner(getInputStream(ethTestCase));
		Scanner expectedOutputScanner = new Scanner(new FileInputStream(ethTestCase.getOut()));
		Scanner ouputScanner = new Scanner(new ByteArrayInputStream(baos.toByteArray()));

		log(inputScanner.nextLine());
		while (inputScanner.hasNextLine()) {
			log(inputScanner.nextLine());

			String expected = expectedOutputScanner.hasNextLine() ? expectedOutputScanner.nextLine() : null;
			String actual = ouputScanner.hasNextLine() ? ouputScanner.nextLine() : null;
			if (!equals(expected, actual))
				printError(expected, actual);

			Assert.assertEquals(expected, actual);
		}
	}

	private FileInputStream getInputStream(ETHTestCase ethTestCase) throws FileNotFoundException {
		return new FileInputStream(ethTestCase.getIn());
	}

	private void printError(String expected, String actual) {
		onErrorLog.flush();
		System.out.print(onErrorLogOut);
		System.err.format("You failed: expected:\n<%s>, but was:\n<%s>%n%n", expected, actual);
	}

	private void log(String nextLine) {
		onErrorLog.println(nextLine);
	}

	private void initErrorLog() {
		onErrorLogOut = new ByteArrayOutputStream();
		onErrorLog = new PrintStream(onErrorLogOut);
	}

	private boolean callMainMethod(ETHTestCase ethTestCase) throws IllegalAccessException, InvocationTargetException {
		try {
			String name = ethTestCase.getDir().getName();
			if (TEST_SPECIFIC_CLASS != null && !TEST_SPECIFIC_CLASS.equals(name))
				return false;
			Class<?> cls = Class.forName(name);

			if (Modifier.isPublic(cls.getModifiers())) {
				fail("Your class " + name + " must not be public (evil ETH Judge)");
			}

			try {
				Method meth = cls.getMethod("main", String[].class);
				if (!Modifier.isPublic(meth.getModifiers())) {
					fail("Main Method on " + name + " must be public (evil ETH Judge)");
				}
				meth.invoke(null, new Object[] { new String[0] });
			} catch (NoSuchMethodException e) {
				fail("Method main on " + name + " not found (evil ETH Judge)");
				e.printStackTrace();
			}

		} catch (ClassNotFoundException e) {
			// e.printStackTrace();
			fail("For your tests, create a class which has the same name as your test folder.\n"
					+ "E.g.: Tests in: './testfiles/A1/*' => Implementation should be in: A1.main()");
		}
		ethTestCase.getDir().getName();
		return true;
	}

	private void fail(String msg) {
		System.err.println(msg);
		Assert.fail(msg);
	}

}
