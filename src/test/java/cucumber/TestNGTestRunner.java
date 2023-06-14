package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/cucumber", glue = "kartonline.stepDefinitions", monochrome = true, plugin = {
		"html:cucumber_report/cucumber.html" })
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
