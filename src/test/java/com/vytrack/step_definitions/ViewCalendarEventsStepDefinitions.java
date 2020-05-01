package com.vytrack.step_definitions;

import com.vytrack.pages.activities.CalenderEventsPage;
import com.vytrack.utilities.BrowserUtilities;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class ViewCalendarEventsStepDefinitions {

    CalenderEventsPage calenderEventsPage = new CalenderEventsPage();

    @Then("View Per Page menu should have following options")
    public void view_Per_Page_menu_should_have_following_options( List<String> dataTable) {

        Assert.assertEquals(dataTable,calenderEventsPage.getViewPerPageOptions());
        //  expcected -> dataTable 10,25,50,100 (we specified them in feature
        // actual    -> we wrote the code and got the per page options from web page.
    }
}
