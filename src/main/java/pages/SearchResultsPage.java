package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pageElements.SearchResultsPageElements;
import utils.ElementLocatorHelper;

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
        String text = searchResultsPageElements.resultsCount.getText().replaceAll(".*of (\\d+) results.*", "$1");
        return Integer.parseInt(text);
    }

    public String getFirstResultText() {
        return searchResultsPageElements.firstResult.getText();
    }

    public void applyYearFilter(String keyword, String fromYear) {
        searchResultsPageElements.filter.click();
        searchResultsPageElements.filterKeywordInput.sendKeys(keyword);
        searchResultsPageElements.filterInputYear.sendKeys(fromYear);
        searchResultsPageElements.filterSearchButton.click();
        applyYearFilterUrlChange();
        elementLocatorHelper.waitSleep(10000);
    }

    public void applyYearFilterUrlChange() {
        elementLocatorHelper.waitUntilPageUrlContains(driver, "manufactureYearRange");
    }
}