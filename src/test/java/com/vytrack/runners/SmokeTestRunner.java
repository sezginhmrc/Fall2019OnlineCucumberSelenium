package com.vytrack.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "com/vytrack/step_definitions",
        features = "src/test/resources/features",
        dryRun = false,
        strict = false,
        tags = "@Smoke_Test",
        plugin = {
                "html:target/smoke_test_default-report",
                "json:target/cucumber10.json",
                        "rerun:target/rerun.txt" }
)
public class SmokeTestRunner {
}
