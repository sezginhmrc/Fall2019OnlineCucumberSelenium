package com.vytrack.step_definitions;

import com.vytrack.pages.activities.CalenderEventsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class CreateCalendarEventStepDefinitions {
    CalenderEventsPage calenderEventsPage = new CalenderEventsPage();

    @Then("user clicks on create calendar event button")
    public void user_clicks_on_create_calendar_event_button() {
        System.out.println("User clicks on Create Calendar Event button");
        calenderEventsPage.clickToCreateCalendarEvent();
    }

    @Then("user enters {string} as title")
    public void user_enters_as_title(String string) {
        System.out.println("User enters title : " + string);
        calenderEventsPage.enterTitle(string);
    }

    @Then("user enters {string} as description")
    public void user_enters_as_description(String string) {
        System.out.println("User enters description: " + string);
        calenderEventsPage.enterADescription(string);
    }

    @Then("user click on save and close button")
    public void user_click_on_save_and_close_button() {
        System.out.println("user clicks on save and close button");
        calenderEventsPage.clickOnSaveAndClose();
    }

    @Then("user verifies that description is {string}")
    public void user_verifies_that_description_is(String string) {
        //verifying entered description
        Assert.assertEquals(string, calenderEventsPage.getGeneralInfoDescriptionText());

    }

    @Then("user verifies that title is {string}")
    public void user_verifies_that_title_is(String string) {
        // verifying entered title
        Assert.assertEquals(string, calenderEventsPage.getGeneralInfoTitleText());
    }


    @When("user enters new calendar event information:")
    public void user_enters_new_calendar_event_information(Map<String, String> dataTable) {
        // how to get data from map ?
      calenderEventsPage.enterADescription( dataTable.get("description"));
    calenderEventsPage.enterTitle( dataTable.get("title"));
    }



    @Then("user verifis new calendar event was created successfully")
    public void user_verifis_new_calendar_event_was_created_successfully(Map<String, String>  dataTable) {
        Assert.assertEquals(dataTable.get("description"),calenderEventsPage.getGeneralInfoDescriptionText());
        Assert.assertEquals(dataTable.get("title"),calenderEventsPage.getGeneralInfoTitleText());
    }


}

//

//
//
//
//
//    And user click on process button
//    And user navigates to "View all orders" page
//    Then user verifies that order is displayed
//
//      | Name      | Product     | #  | Date      | Street     | City   | State   | Zip   | Card | Card Number | Exp   |
//      | Test user | Screensaver | 12 | 04/23/220 | CamMahalle | Espiye | Giresun | 28610 | Visa | 123456789   | 04/25 |
