package pages;

import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageElements.SearchResultsPageElements;
import utils.ElementLocatorHelper;
import utils.JsUtils;

import java.time.Duration;


public class SearchResultsPage {

    WebDriver driver;
    SearchResultsPageElements searchResultsPageElements;
    ElementLocatorHelper elementLocatorHelper = new ElementLocatorHelper();

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        searchResultsPageElements = new SearchResultsPageElements();
        PageFactory.initElements(driver, searchResultsPageElements);
    }

    public int getResultsCount() {
        elementLocatorHelper.waitUntilExpectedConditionVisabilityOf(driver, searchResultsPageElements.resultsCount, 15);
        String text = searchResultsPageElements.resultsCount.getText().replaceAll("^(\\d+).*", "$1");
        return Integer.parseInt(text);
    }

    public String getFirstResultText() {
        return searchResultsPageElements.firstResult.getText();
    }

    public void applyYearFilter(String fromYear, String toYear) {
        try {
            searchResultsPageElements.filterInputYearFrom.clear();
            searchResultsPageElements.filterInputYearFrom.sendKeys(fromYear);
        } catch (ElementNotInteractableException e){
            elementLocatorHelper.scrollIntoView(driver,searchResultsPageElements.filterYearExpand);
            searchResultsPageElements.filterYearExpand.click();
            elementLocatorHelper.scrollIntoView(driver,searchResultsPageElements.filterInputYearFrom);
            new JsUtils().forceClearSearchBoxNew(driver,searchResultsPageElements.filterInputYearFrom);
            searchResultsPageElements.filterInputYearFrom.sendKeys(fromYear);
        }
        new JsUtils().forceClearSearchBoxNew(driver,searchResultsPageElements.filterInputYearTo);
        searchResultsPageElements.filterInputYearTo.sendKeys(toYear);
        searchResultsPageElements.filterInputYearTo.sendKeys(Keys.ENTER);
        applyYearFilterUrlChange();
    }

    public void applyYearFilterUrlChange() {
        elementLocatorHelper.waitUntilPageUrlContains(driver, "manufactureYearRange");
        elementLocatorHelper.waitExplicitly(driver,2000);
    }

    public boolean waitForFirstResultToContainSearchTerm(String string) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean isTextPresent = wait.until(ExpectedConditions.textToBePresentInElement(searchResultsPageElements.firstResult, string
        ));
        return isTextPresent;
    }
}