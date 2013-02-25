import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ETHTestSuite {
	@Parameters
	public static List<File[]> data() {
		Set<ETHTestCase> inFiles = ETHTestHelper.getETHTestCases();
		
		ArrayList<File[]> a = new ArrayList<File[]>();
		for (ETHTestCase ethTestCase : inFiles) {
			a.add(new File[]{ethTestCase.getIn(), ethTestCase.getOut()});
		}
		return a;
	}

	private final File in;
	private final File out;

	public ETHTestSuite(File in, File out) {
		this.in = in;
		this.out = out;
	}
	
	@Test
	public void test() {
		// TODO: execute tests, use in and out
		//Assert.assertEquals(in, out);
		System.out.println(in);
		System.out.println(out);
	}
}
