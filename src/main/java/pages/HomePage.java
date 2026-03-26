package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import pageElements.HomePageElements;
import utils.ElementLocatorHelper;

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
        forceClearSearchBox();
        homePageElements.searchBoxActive.sendKeys(string);
    }

    public void forceClearSearchBox() {

        WebElement input = homePageElements.searchBoxActive;

        // Clear using JS (most reliable)
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value='';", input);

        // Trigger React change event
        js.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", input);
        
        //homePageElements.searchBoxActive.sendKeys(Keys.chord(Keys.COMMAND, "a"));
        //homePageElements.searchBoxActive.sendKeys(Keys.DELETE);
    }

    public Boolean searchButtonIsDisplayed() {
        return homePageElements.searchButton.isDisplayed();
    }

    public void clickSearchEnter() {
        homePageElements.searchButton.click();
        elementLocatorHelper.waitUntilPageUrlContains(driver, "search?freeText");
    }

}