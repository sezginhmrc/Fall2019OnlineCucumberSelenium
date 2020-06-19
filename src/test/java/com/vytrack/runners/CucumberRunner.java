package com.vytrack.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.DataProvider;


// runner class is
// any kind of runner will be stored here
// not page or test package
// it will bre pretty much what features you want to test
// it is like xml runner but way simple than xml


@RunWith(Cucumber.class)
@CucumberOptions( // this options stands for specifying cucumber test parameters
        glue = "com/vytrack/step_definitions",
        // provide path for step definitions
        features =  "src/test/resources/features/",
        // provide path for features directory
        dryRun = false,
        // true to make sure if every test steps has code implmentation
        // Login.feature without step definitnons doest make sense.
        // every scenarios steps must have implmenetation
        // it basically check if each steps has code implemenatation
        strict = false,
         tags = "@Login",
        // here we specify the scenario that we want to run
        // or -> execute both scenarios
        plugin = {
                // this is the default report
                "html:target/default-report",
                // this line will generate jason report
                 "json:target/cucumber1.json",
                "rerun:target/rerun.txt"
        }


        // plugin for report

        //JUnit will invoke the class it references to run the
        // tests in that class instead of the runner built into JUnit
)
public class CucumberRunner extends AbstractTestNGCucumberTests {


        @Override
        @DataProvider(parallel = true)
        public Object[][] scenarios() {
                return super.scenarios();
        }


}

