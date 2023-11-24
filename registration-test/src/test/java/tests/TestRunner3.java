package tests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"features/Registration.feature"}, glue = {"step.definition"}, tags = "@positive",
				plugin = {"html:target/cucumber-report-registration-feature.html",
						"json:target/cucumber-report-registration-feature.json","pretty"} )
public class TestRunner3 extends AbstractTestNGCucumberTests {

}
