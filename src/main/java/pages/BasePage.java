package pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pageElements.BasePageElements;
import utils.ElementLocatorHelper;

public class BasePage {

    WebDriver driver;
    BasePageElements basePageElements;
    ElementLocatorHelper elementLocatorHelper = new ElementLocatorHelper();

    public BasePage(WebDriver driver) {
        this.driver = driver;
        basePageElements = new BasePageElements();
        PageFactory.initElements(driver, basePageElements);
    }

    public void waitForSiteToBeReady() {
        try{
            elementLocatorHelper.waitUntilExpectedConditionVisabilityOf(driver, BasePageElements.FEEDBACK_BUTTON, 120);
        } catch (TimeoutException e){
            //derive if we should wait longer or exit
        }
    }

    public void acceptCookies() {
        if (elementLocatorHelper.isElementDisplayed(driver, basePageElements.trusteConsentButton, 5)) {
            basePageElements.trusteConsentButton.click();
        }
    }

    public void acceptEnglishLanguage() {
        if (elementLocatorHelper.isElementDisplayed(driver, basePageElements.acceptEnglishLanguage, 5)) {
            basePageElements.acceptEnglishLanguage.click();
        }
    }

    public void navigateToRBAuction() {
        driver.navigate().to("https://www.rbauction.com");
        elementLocatorHelper.waitUntilPageLoadedNew(driver);
    }
}