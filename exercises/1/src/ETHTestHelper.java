import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class ETHTestHelper {

	public static Set<ETHTestCase> getETHTestCases(File directory) {
		Set<ETHTestCase> inFiles = new HashSet<ETHTestCase>();

		for (File assignment : directory.listFiles()) {
			if (!assignment.isDirectory())
				continue;
			for (File in : assignment.listFiles()) {
				if (ETHTestCase.isInFile(in))
					inFiles.add(new ETHTestCase(in));
			}
		}
		return inFiles;
	}

}
