import java.io.File;

import org.junit.runner.RunWith;
import org.junit.runners.AllTests;
import org.junit.runners.Parameterized;

import junit.framework.AssertionFailedError;
import junit.framework.Test;
import junit.framework.TestResult;

public class ETHTestCase implements Test {

	private File out;
	private File in;

	public File getOut() {
		return out;
	}

	public File getIn() {
		return in;
	}

	public ETHTestCase(File in) {
		this.in = new File(in.getPath());
		out = new File(in.getPath().replaceFirst(".in$", ".out"));
		if (!out.exists())
			throw new RuntimeException(
					"For every .in file, a .out file has to exist. Problem: "
							+ out + " does not exist.");
	}

	public static boolean isInFile(File in) {
		return in.getName().endsWith(".in");
	}

	@Override
	public int countTestCases() {
		return 1;
	}

	@Override
	public void run(TestResult res) {
		// TODO: implement this
		//res.addFailure(this, new AssertionFailedError("blub"));
		res.startTest(this);
		res.endTest(this);
	}

}
