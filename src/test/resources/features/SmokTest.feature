@Smoke_Test
Feature: Modules Test with Scenario Outline



  Scenario Outline: Go to <module> and verify title: <title>
    Given user is on the login page
    Given logs is as "<user_type>"
    And user navigates to "<tab>" and "<module>"
    Then user verifies that page title is "<title>"
    # "" means parameter to pass into step definitions
    # <> means parameters gets value from examples table
    Examples:
      | user_type     | tab        | module          | title                                                              |

      | sales manager | Fleet      | Vehicles        | All - Car - Entities - System - Car - Entities - System            |
      | sales manager | Fleet      | Vehicles Model  | All - Vehicles Model - Entities - System - Car - Entities - System |
      | sales manager | Customers  | Accounts        | All - Accounts - Customers                                         |
      | sales manager | Customers  | Contacts        | All - Contacts - Customers                                         |
      | sales manager | Activities | Calendar Events | All - Calendar Events - Activities                                 |
      | sales manager | System     | Jobs            | All - Jobs - System                                                |

  Scenario: Login as sales manager and verify that title is Dashboard
    # Bdd scenario all the same above we can't run different scenarios as a together in tags = "@sales_manager or @driver" like that
   # Given user is on the login page
    When user logs in as a sales manager
    Then user should verify that title is a Dashboard