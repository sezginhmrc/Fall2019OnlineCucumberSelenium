package com.vytrack.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "com/vytrack/step_definitions",
        features = "src/test/resources/features/activities",
        dryRun = false,
        strict = false,
        plugin = {
                "html:target/default-report",
                "json:target/cucumber10.json" +
                        "" }

)
// we created this runner also to execute in parallel


public class ActivitiesRunner {
    // this class will be runners for all components under Activities
    // for every module we will have different runner in project
    // for maintenance.
}
