@Login
Feature: Login
  As user, I want to be able to login with username and password
#  Features File in Gherkin Language(Simple language)
#  User story
# : (two dot after keywords)

#  Test Method = Test Case = Scenario

   Background: open login page
     Given user is on the login page

  @sales_manager
  Scenario: Login as sales manager and verify that title is Dashboard
    # Bdd scenario all the same above we can't run different scenarios as a together in tags = "@sales_manager or @driver" like that
   # Given user is on the login page
    When user logs in as a sales manager
    Then user should verify that title is a Dashboard

    @store_manager
  Scenario: Login as store manager and verify that title is Dashboard
  #  Given user is on the login page
    When user logs in as a store manager
    Then user should verify that title is a Dashboard

  @driver
  Scenario: Login as driver and verify that title is a Dashboard
   # Given user is on the login page
    When user logs in as a driver
    Then user should verify that title is a Dashboard

    @Login_With_Params
    Scenario: Login with parameter
     When user enters "storemanager85" as username and "UserUser123" as password
      Then user should verify that title is a Dashboard

  @Scenario_Outline
  Scenario Outline: User names tests for user <name>
    When user enters "<username>" as username and "<password>" as password
    Then user name should be "<name>"
    Examples:
      | username        | password    | name             |
      | user187         | UserUser123 | Jerel Vandervort |
      | user200         | UserUser123 | Lonzo Leuschke   |
      | storemanager52  | UserUser123 | Roma Medhurst    |
      | storemanager66  | UserUser123 | Carlos Ernser    |
      | salesmanager125 | UserUser123 | Cleveland Heller |
      | salesmanager140 | UserUser123 | Jameson Paucek   |

    # we have 6 test cases here all steps are identical only different is credentials and name
