package com.vytrack.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "com/vytrack/step_definitions",
        features = "@target/rerun.txt",
        // we dont need tags here it will be run according feature path
        // which is failed test cases path
        plugin = {
                "html:target/failed-default-report",
                "json:target/failed_report.json" +
                        "" }

)
public class FailedRunner {

}
