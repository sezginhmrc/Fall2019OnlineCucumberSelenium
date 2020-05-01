package com.vytrack.pages.fleet;

import com.vytrack.pages.AbstractPageBase;
import com.vytrack.utilities.BrowserUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class VehiclesPage extends AbstractPageBase {

    // for every page we gonna create a corresponding page class
    // we can group page classes based on tab name.
    // VyTrack app pages -> Vehicles, Vehicles Models, Vehicle Models
    // Framework         -> VehiclesPage.java, VehiclesModelsPage.java

    @FindBy(partialLinkText = "Create Car")
    private WebElement createCar ;


    // Create car elements
    @FindBy(css = "input[name='custom_entity_type[LicensePlate]']")
    private WebElement licencePlateInput;
    @FindBy(css="input[name='custom_entity_type[Driver]']")
    private WebElement driverInput;
    @FindBy (css="input[name='custom_entity_type[Location]']")
    private WebElement locationInput;
    @FindBy (css="input[name='custom_entity_type[ModelYear]']")
    private WebElement modelYear;
    @FindBy (css="input[name='custom_entity_type[Color]']")
    private WebElement color;
    @FindBy (xpath = "(//button[@type='submit'])[1]")
    private WebElement submit;

    public void setLicencePlateInput(String licencePlate){
        BrowserUtilities.waitForPageToLoad(20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[name='custom_entity_type[LicensePlate]']")));
        wait.until(ExpectedConditions.visibilityOf(licencePlateInput));
        licencePlateInput.sendKeys(licencePlate);
    }
    public void setDriverInput(String driver){
        driverInput.sendKeys(driver);
    }
    public void setLocationInput(String location){
        locationInput.sendKeys(location);
    }
    public void setModelYear(String year){
        modelYear.sendKeys(year);
    }
    public void setColor(String color){
        this.color.sendKeys(color);
    }
    public void submit(){
        submit.click();
    }




    // for simple click for Vehicles Page and test should call this method and handle all other actions
    public void clickToCreateCar(){
       BrowserUtilities.waitForPageToLoad(10);
        wait.until(ExpectedConditions.elementToBeClickable(createCar)).click();
        waitForLoaderMask();
        // test should call click and handle
    }


    // will get any value of parameter with xpath
    public String getCarGeneralInfo(String parameter) {
        String xpath = "//label[text()='" + parameter + "']/following-sibling::div/div";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        return driver.findElement(By.xpath(xpath)).getText().trim();
    }


}
