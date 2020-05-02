package com.vytrack.pages;


import com.vytrack.utilities.BrowserUtilities;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.Driver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPageBase {

    // this loginPage class is coreespoding of Login Page of Application
    // it provides web elements and methods for login page of application
    // @FindBy anonotions are used for findin elemenets without findElement
    // PageFactory is used for instaiance of object and find instance of class
    // it will return the these element and methods in test scripts

    /// these elements should be private
    // they are not supposed to be accesible in test cases
    // We already using them here in methods
    // they not will be accesible anywhere out of this class
    // benefit of encasuplation
    // it is about design and more organize code
    // all logic implement here in page class
    // oop in action........


    @FindBy(id = "prependedInput")
    private WebElement username;


    @FindBy(id = "prependedInput2")
    private WebElement password;

    @FindBy(id = "_submit")
    private WebElement login;

    @FindBy(linkText = "Forgot your password?")
    private WebElement forgotPassword;


    //warning mesasge
    @FindBy(css = "*[class='alert alert-error']")
    private WebElement warningMessage;

    // method that returns a text of waning message webelement
    // call it in test class
    public String getWarningMessageText() {
        return warningMessage.getText();
    }


    // this is method for login
    // it accepts two parameter (username and password)

    // login gor specific user(driver or store manager or sales manager)
    public void login(String usernameValue, String passwordValue) {
        BrowserUtilities.waitForPageToLoad(10);
        username.sendKeys(usernameValue);
        password.sendKeys(passwordValue, Keys.ENTER);
        BrowserUtilities.waitForPageToLoad(10);
        BrowserUtilities.wait(3);
    }


    // if there is no dependencies on the the rule use this method
    // login for default user
    // we get the data from properties file where we store project information
    public void login() {
        username.sendKeys(ConfigurationReader.getProperty("username"));
        password.sendKeys(ConfigurationReader.getProperty("password"), Keys.ENTER);
        BrowserUtilities.wait(3);
    }


    /**
     * this method stands for login based on user type
     * For example: if parameter is equals to driver, user will login as a driver
     * If role parameter is not correct, method will throw exception
     *
     * @param role - driver, sales manager or store manager
     */
    // it will login based on user_type ;)
    public void login(String role) {
        String userName = "";
        if (role.equalsIgnoreCase("driver")) {
            userName = "user15";
        } else if (role.equalsIgnoreCase("sales manager")) {
            userName = "salesmanager110";
        } else if (role.equalsIgnoreCase("store manager")) {
            userName = "storemanager85";
        } else {
            throw new RuntimeException("Invalid role!");
        }
        System.out.println("Login as " + role);
        login(userName, "UserUser123");
    }
}
// login functions ;
// first version is -> lets you to create from method
// second version is -> using credentials to create  from credential file
// METHOD OVERLOADING !  ! ! ! ! ! ! ! ! (different parameters)

// method signature is combination of method name and parameter list

