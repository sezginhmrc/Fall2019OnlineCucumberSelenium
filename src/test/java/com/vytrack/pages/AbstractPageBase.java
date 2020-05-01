package com.vytrack.pages;


import com.vytrack.utilities.BrowserUtilities;
import com.vytrack.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPageBase {

    // this class will be extended by page classes
    // any common web element / locators can be stored here
    // any web element that will appears as is in anywhere
    // since navigation menu does not belong to particular page
    // we cannot really create a dedicated page class to store
    // elements from that menu

    protected WebDriver driver = Driver.getDriver();
    // more reusable code
    // every page will get driver like below
    protected WebDriverWait wait = new WebDriverWait(driver,15);
    // every page class implement this wait

    @FindBy(css = "#user-menu > a")
    protected WebElement currentuser;
    // this current owner element belongs to top menu and comman for all test cases..
    // it belongs basePage
    // We can store this top menu eleme
    // nts here BasePAge since they are shared for pages in application




    @FindBy(css = "[class='btn-group pull-right'] > button")
    private WebElement saveAndClose;
    // we moved this element here since it is common for pages


    public AbstractPageBase(){
        PageFactory.initElements(driver,this);
        // initialzes pagefactory to use @Findby in all subclasses
    }


    // we moved this method here beacuse it common for all pages .
    public  void clickOnSaveAndClose() {
        BrowserUtilities.wait(5);
        // wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='btn-group pull-right'] > button")));
        wait.until(ExpectedConditions.elementToBeClickable(saveAndClose)).click();
        BrowserUtilities.wait(3);
        waitForLoaderMask();
    }



    public String getCurrentUserName(){
        BrowserUtilities.waitForPageToLoad(10);
        wait.until(ExpectedConditions.visibilityOf(currentuser));
        return  currentuser.getText().trim();
    }
    /*
    *
    * @param tabName, like Dashboards, Fleet or Costumers
    * @parm moduleName, like Vehicles, Vehicles Odometer and Vehicles Costs
    * */
    public void navigateTo(String tabName, String moduleName) {
        String tabNameXpath = "//span[@class='title title-level-1' and contains(text(),'" + tabName + "')]";
        String moduleXpath = "//span[@class='title title-level-2' and text()='" + moduleName + "']";

        WebElement tabElement = driver.findElement(By.xpath(tabNameXpath));
        WebElement moduleElement = driver.findElement(By.xpath(moduleXpath));

        Actions actions = new Actions(driver);

        BrowserUtilities.wait(4);

        actions.moveToElement(tabElement).
                pause(2000).
                click(moduleElement).
                build().perform();


        // increase this wait tim here if still failing
        BrowserUtilities.wait(3);
        BrowserUtilities.waitForPageToLoad(15);
       // wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("[class*='loader-mask']")));
    }

    // this method can be used to wait that terrible loader mask(spinning wheel) will be gone
    // if loader mask is present, website is loading same data you cannot perform any operation
    public void waitForLoaderMask(){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("[class*='loader-mask']")));

    }
}
//mvn dependency:tree -> to upload all jars
//		mvn dependency:resolve-plugins -> resolving plugin issues
