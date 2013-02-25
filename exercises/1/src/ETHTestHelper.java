import java.io.File;
import java.util.HashSet;
import java.util.Set;


public class ETHTestHelper {

	public static Set<ETHTestCase> getETHTestCases() {
		Set<ETHTestCase> inFiles = new HashSet<ETHTestCase>();
		for (File in : new File("./testfiles/").listFiles()) {
			if (ETHTestCase.isInFile(in)) {
				inFiles.add(new ETHTestCase(in));
			}
		}
		return inFiles;
	}

}
