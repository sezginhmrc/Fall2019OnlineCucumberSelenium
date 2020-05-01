@Smoke_Test
Feature: Login tests with Scenario Outline



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

