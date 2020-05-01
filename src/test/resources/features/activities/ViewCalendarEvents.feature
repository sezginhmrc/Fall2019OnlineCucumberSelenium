@View_Calendar_Events
Feature: View all calendar events
  As user , i should be able to see all calendar events a a table

  Scenario: User permissions
    Given user is on the login page
    When user logs in as a sales manager
    And user navigates to "Activities" and "Calendar Events"
    Then View Per Page menu should have following options
      | 10  |
      | 25  |
      | 50  |
      | 100 |


    # this is data table it will pas a list of string into step definitions
    # List <String> as a parameter
