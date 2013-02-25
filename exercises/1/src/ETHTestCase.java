import java.io.File;

public class ETHTestCase {

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
			throw new RuntimeException("For every .in file, a .out file has to exist. Problem: " + out + " does not exist.");
	}

	public static boolean isInFile(File in) {
		return in.getName().endsWith(".in");
	}

	@Override
	public String toString() {
		return getDir().getName() + " " + in.getName();
	}

	public File getDir() {
		return in.getParentFile();
	}

}
