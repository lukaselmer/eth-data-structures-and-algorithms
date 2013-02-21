import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import junit.framework.Assert;

import org.junit.Test;

public class A1Test {

	@Test
	public void testUebung1() {
		
		/* ****************************
		/* Modify here */
		/* ****************************/
		String input = "2\n20 0 1 1 1\n22 5 10 1 -1";
		String expectedOutput = "6765\n-10";
		/* **************************/
		
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
		/* Modify here */
		/* ****************************/
		// Call stuff
		A1.main(new String[0]);
		/* **************************/
		
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

}
