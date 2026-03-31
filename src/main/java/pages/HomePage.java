package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pageElements.HomePageElements;
import utils.ElementLocatorHelper;
import utils.JsUtils;

public class HomePage {

    WebDriver driver;
    HomePageElements homePageElements;
    ElementLocatorHelper elementLocatorHelper = new ElementLocatorHelper();

    public HomePage(WebDriver driver) {
        this.driver = driver;
        homePageElements = new HomePageElements();
        PageFactory.initElements(driver, homePageElements);
    }

    public void search(java.lang.String string) {
        elementLocatorHelper.waitUntilPageLoaded(driver);
        elementLocatorHelper.scrollToTopOfPage(driver);
        elementLocatorHelper.waitForClickabilityOfAndClick(driver, homePageElements.searchBox, 30);
        new JsUtils().forceClearSearchBoxNew(driver,homePageElements.searchBox);
        homePageElements.searchBox.sendKeys(string);
    }

    public Boolean searchButtonIsDisplayed() {
        return homePageElements.searchButton.isDisplayed();
    }

    public void clickSearchEnter() {
        homePageElements.searchButton.click();
        elementLocatorHelper.waitUntilPageUrlContains(driver, "search?freeText");
        elementLocatorHelper.waitExplicitly(driver, 5000);
    }
}