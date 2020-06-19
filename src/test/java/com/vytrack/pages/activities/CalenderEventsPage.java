package com.vytrack.pages.activities;


import com.vytrack.pages.AbstractPageBase;
import com.vytrack.utilities.BrowserUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;


// POM is desgn patter  where we create page classes for every page on appplicaiton thus we store webelements and locators in page classes
// basically ech web applicaiton page has a class in my framework.
// PageFactory is to find elements seperatly than test cases
//Class become smaller, easier to understand. well organized more maintain

public class CalenderEventsPage extends AbstractPageBase {

    // test will be easy by this Page class
    // This our logic here it is POM
    // our test codes will be cleaner and well organized and easy to understand
    // LoginPage object and CalenaderEventsPage object

    @FindBy(css = "[title='Create Calendar event']")
    private WebElement createCalendarEvent;

    @FindBy(className = "select2-chosen")
    private WebElement owner ;


    @FindBy(css="[id^='date_selector_oro_calendar_event_form_start']")
    private WebElement startDate ;

    @FindBy(css = "[id^='time_selector_oro_calendar_event_form_start']")
    private WebElement startTime;

    @FindBy(css = "[id^='time_selector_oro_calendar_event_form_end']")
    private WebElement endTime;

    @FindBy(className = "grid-header-cell__label")
    private List<WebElement> columnNames ;

    @FindBy(css = "[id^='oro_calendar_event_form_title-uid']")
    private WebElement title;

    @FindBy (css = "iframe[id^='oro_calendar_event_form_description-uid']")
    private WebElement descriptionFrame ;

    @FindBy(id="tinymce")
    private WebElement descriptionTextArea;


 //   @FindBy(css = "[class='control-label html-property'] > p")
  //  private WebElement description ;

    @FindBy(xpath = "(//div[@class='control-label'])[1]")
    private WebElement generalInfoTitle;

    @FindBy(css = "[class='control-label html-property'] > p")
    private WebElement generalInfoDescription;

    @FindBy(xpath = "//*[contains(text(),'View per page:')]/following-sibling::*//a")
    private  List<WebElement> viewPerPageElements;

    @FindBy(css = "button[class*='btn dropdown-toggle']")
    private WebElement viewPerPageCaret ;

    public List<String> getViewPerPageOptions(){
        BrowserUtilities.waitForPageToLoad(15);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[title='Create Calendar event']")));
        viewPerPageCaret.click();
        BrowserUtilities.wait(3);
        return BrowserUtilities.getTextFromWebElements(viewPerPageElements);
    }



    public void enterTitle(String titleValue){
        BrowserUtilities.waitForPageToLoad(20);
        wait.until(ExpectedConditions.visibilityOf(title)).sendKeys(titleValue);
        BrowserUtilities.wait(3);


    }
    public void enterADescription (String description){
        BrowserUtilities.waitForPageToLoad(20);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(descriptionFrame));
        // found element frame element and implement our element insidee expectednconditions to swith the frame
        // An expectation for checking whether the given frame is available to switch to.
        descriptionTextArea.sendKeys(description);
       // wait.until(ExpectedConditions.textToBePresentInElement(descriptionTextArea,description));
        driver.switchTo().defaultContent(); // to exit from frame
    }




    public String getGeneralInfoTitleText(){
        BrowserUtilities.waitForPageToLoad(20);
      return   generalInfoTitle.getText();
    }
    public String getGeneralInfoDescriptionText(){
        BrowserUtilities.waitForPageToLoad(20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='control-label html-property'] > p")));
        return generalInfoDescription.getText();
    }

    // ##################################

    // to click on CalendarEvent
    public void clickToCreateCalendarEvent() {
        BrowserUtilities.waitForPageToLoad(20);
     //   wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[title='Create Calendar event']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(createCalendarEvent)).click();
        BrowserUtilities.waitForPageToLoad(20);
        BrowserUtilities.wait(5);
        waitForLoaderMask();
    }

    public String getOwnerName(){
        BrowserUtilities.waitForPageToLoad(10);
        // we can also add one mor wait condition to avoid noSuchElement
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("select2-chosen")));
        wait.until(ExpectedConditions.visibilityOf(owner));
        return owner.getText();
    }


    public String getTitleContent(){
        BrowserUtilities.waitForPageToLoad(10);
        wait.until(ExpectedConditions.visibilityOf(title));
        System.out.println(title.getText().trim());
        return title.getText().trim();
    }

    public String getStartDate(){
        BrowserUtilities.waitForPageToLoad(10);
        wait.until(ExpectedConditions.visibilityOf(startDate));
        // in case of still fails
        // add this
       // wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[id^='time_selector_oro_calendar_event_form_start']")));
        // details might tell you the solution !!!
        return startDate.getAttribute("value");
    }

    public String getStartTime(){
        BrowserUtilities.waitForPageToLoad(15);
        wait.until(ExpectedConditions.visibilityOf(startTime));
        return startTime.getAttribute("value");
    }
    public String getEndTime(){
        BrowserUtilities.waitForPageToLoad(15);
       wait.until(ExpectedConditions.visibilityOf(endTime));
        return  endTime.getAttribute("value");
    }

    public List<String> getColumnNames(){
        BrowserUtilities.waitForPageToLoad(25);
        return BrowserUtilities.getTextFromWebElements(columnNames);
        // this methods returns string list of webelement
    }

    }
    // StringUtil class is very popular and for many different package we have same class name
    // be careful while declaration of StringUtil

    // will have test for check user name is owner name
    // check title is blank
    // check dates and times


