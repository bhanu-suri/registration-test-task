package tests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"features/Registration.feature"}, glue = {"step.definition"}, tags = "@negative",
				plugin = {"html:target/cucumber-report-registration-feature.html",
						"json:target/cucumber-report-registration-feature.json","pretty"} )
public class TestRunner2 extends AbstractTestNGCucumberTests {

}
