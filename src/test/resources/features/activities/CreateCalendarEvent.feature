@CreateCalendarEvent
Feature: Crete calendar event
  As user , I want to be able to create calendar events.


  Scenario: Create calendar event as sales manager
    Given user is on the login page
    When user logs in as a sales manager
    And user navigates to "Activities" and "Calendar Events"
    # we pass the data here and in step definitions navigating the page
    Then user clicks on create calendar event button
    And user enters "Sprint Retrospective" as title
    And user enters "On this meeting we discuss what went well, what went wrong and what can be improved" as description
    Then user click on save and close button
    And user verifies that description is "On this meeting we discuss what went well, what went wrong and what can be improved"
    And user verifies that title is "Sprint Retrospective"


  Scenario: Create calendar event as sales manager with data table
    Given user is on the login page
    When user logs in as a sales manager
    And user navigates to "Activities" and "Calendar Events"
      Then user clicks on create calendar event button
      And user enters new calendar event information:
      # Key         value  (we pass the key in implementation to get value)
     | description | On this meeting we discuss what went well, what went wrong and what can be improved |
     | title       | Sprint Retro  |

    Then user click on save and close button

    And user verifis new calendar event was created successfully
      | description | On this meeting we discuss what went well, what went wrong and what can be improved |
      | title       | Sprint Retro  |

