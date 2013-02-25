import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

import com.sun.tools.javac.util.Pair;

public class A1Test {

	@Test
	public void testUebung1() {

		// get \r\n oder \r für mac oder so ähnlich
		String separator = System.getProperty("line.separator");

		/* ****************************
		 * /* Modify here
		 */
		/* *************************** */
		String input = "2" + separator + "20 0 1 1 1" + separator
				+ "22 5 10 1 -1";
		String expectedOutput = "6765" + separator + "-10" + separator;
		/* ************************* */

		// Input stuff
		byte[] inputData = input.getBytes();
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
				inputData);

		// Output stuff
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(byteArrayOutputStream);

		// Redirect streams
		PrintStream oldOut = System.out;
		System.setOut(out);
		System.setIn(byteArrayInputStream);

		/* ****************************
		 * /* Modify here
		 */
		/* *************************** */
		// Call stuff
		Main.main(new String[0]);
		/* ************************* */

		// validate stuff
		System.setOut(oldOut);

		System.out.println("Input was:\n" + input + "\n");

		String output = new String(byteArrayOutputStream.toByteArray());
		if (!output.equals(expectedOutput)) {
			System.out.println("Expected Output:\n" + expectedOutput
					+ "\n\nbut was:\n" + output);
		}
		Assert.assertEquals(expectedOutput, output);

	}
	
	@Test
	public void testGenericInputFiles() throws IOException {
		Set<ETHTestCase> tests = getETHTestCases();
		for (ETHTestCase test : tests) {
			// TODO evaluate tests, check results
		}
	}

	private Set<ETHTestCase> getETHTestCases() {
		Set<ETHTestCase> inFiles = new HashSet<ETHTestCase>();
		for (File in : new File("./testfiles/").listFiles()) {
			if (ETHTestCase.isInFile(in)) {
				inFiles.add(new ETHTestCase(in));
			}
		}
		return inFiles;
	}

}
