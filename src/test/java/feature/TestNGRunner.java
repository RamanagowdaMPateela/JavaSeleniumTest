package feature;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/feature", glue = "stepDefs",
monochrome = true, tags = "@Regression", plugin = "html:target/cucumber.html")
public class TestNGRunner extends AbstractTestNGCucumberTests {

}
