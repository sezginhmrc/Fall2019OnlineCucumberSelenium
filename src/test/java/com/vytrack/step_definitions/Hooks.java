package com.vytrack.step_definitions;

import com.vytrack.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Hooks {

    protected WebDriverWait wait;

// cucumber html report will be added in a couple weeks
// cucumber also has many default reports.


// this Before hook(Cucumber) will be executed before every scenario
// in other hands @BeforeMethod(Testng) will be executed for every methods
    @Before(order = 2)
    public void setup() {
        System.out.println("Test setup");
        Driver.getDriver().manage().window().maximize();
        wait = new WebDriverWait(Driver.getDriver(), 20);

    }

    // These are only for specific scenarios(for driver)
    @Before(value = "@driver", order = 1)
    public void specialSetup() {
        System.out.println("Setup for driver");
    }

    @After("@driver")
    public void specialTearDown() {
        System.out.println("Tear down for driver only");
    }


    // this After hook(Cucumber) will be executed after every scenario
    // we gonna modify this after to take screenshot
    @After
    public void teardown(Scenario scenario) {
        // scenario is class can be used for hook
        // * Before or After Hooks that declare a parameter of this type will receive an instance of this class.
        // * It allows writing text and embedding media into reports, as well as inspecting results (in an After block).
        // same logic as previos framework to get screenshot
        // scenario is  cucumber version of ITestResult (testng)
        if (scenario.isFailed()) {
            TakesScreenshot takesScreenshot = (TakesScreenshot) Driver.getDriver();
            byte[] image = takesScreenshot.getScreenshotAs(OutputType.BYTES);
            // screenshot is array of byte
            scenario.embed(image, "image/png,", scenario.getName());
        }
        System.out.println("Test clean up");
        Driver.closeDriver();
    }

}
