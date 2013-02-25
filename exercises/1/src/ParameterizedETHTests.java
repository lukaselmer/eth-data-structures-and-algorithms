import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ParameterizedETHTests {
	@Parameters(name = "{0}")
	public static List<ETHTestCase[]> data() {
		Set<ETHTestCase> inFiles = ETHTestHelper.getETHTestCases(new File("./testfiles/"));

		ArrayList<ETHTestCase[]> a = new ArrayList<ETHTestCase[]>();
		for (ETHTestCase ethTestCase : inFiles) {
			a.add(new ETHTestCase[] { ethTestCase });
		}
		return a;
	}

	private final ETHTestCase ethTestCase;

	public ParameterizedETHTests(ETHTestCase ethTestCase) {
		this.ethTestCase = ethTestCase;
	}

	@Test
	public void test() throws Exception {
		new ETHTestRunner().run(ethTestCase);
	}
}
