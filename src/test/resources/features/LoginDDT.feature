@Scenario_Outline_2
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
      | driver        | Fleet      | Vehicles        | Car - Entities - System - Car - Entities - System                  |
      | driver        | Fleet      | Vehicles Model  | Vehicles Model - Entities - System - Car - Entities - System       |
      | driver        | Customers  | Accounts        | Accounts - Customers                                               |
      | driver        | Customers  | Contacts        | Contacts - Customers                                               |
      | driver        | Activities | Calendar Events | Calendar Events - Activities                                       |
      | driver        | System     | Jobs            | Jobs - System                                                      |
      | sales manager | Fleet      | Vehicles        | All - Car - Entities - System - Car - Entities - System            |
      | sales manager | Fleet      | Vehicles Model  | All - Vehicles Model - Entities - System - Car - Entities - System |
      | sales manager | Customers  | Accounts        | All - Accounts - Customers                                         |
      | sales manager | Customers  | Contacts        | All - Contacts - Customers                                         |
      | sales manager | Activities | Calendar Events | All - Calendar Events - Activities                                 |
      | sales manager | System     | Jobs            | All - Jobs - System                                                |
      | store manager | Fleet      | Vehicles        | All - Car - Entities - System - Car - Entities - System            |
      | store manager | Fleet      | Vehicles Model  | All - Vehicles Model - Entities - System - Car - Entities - System |
      | store manager | Customers  | Accounts        | All - Accounts - Customers                                         |
      | store manager | Customers  | Contacts        | All - Contacts - Customers                                         |
      | store manager | Activities | Calendar Events | All - Calendar Events - Activities                                 |
      | store manager | System     | Jobs            | All - Jobs - System                                                |


    #-        first column is key second is value -> Map
    #	    - 1 column is -> List<String>
    #	    - 2 column is -> Map<String,String>
    #	    - 2+ column is -> List<Map<String,String>> -> where keys will be always on the first row.
    #	    - if you specified data types a a Map, user get("keyname") to read values