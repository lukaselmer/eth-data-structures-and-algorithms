import java.io.File;


public class ETHTestCase {

	private File out;
	private File in;

	public ETHTestCase(File in) {
		this.in = in;
		out = new File(in.getAbsolutePath().replaceFirst(".in$", ".out"));
		if (!out.exists())
			throw new RuntimeException(
					"For every .in file, a .out file has to exist. Problem: "
							+ out + " does not exist.");
	}

	public static boolean isInFile(File in) {
		return in.getName().endsWith(".in");
	}

}
