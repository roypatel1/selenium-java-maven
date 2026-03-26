package tests;


import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchResultsPage;
import utils.RetryAnalyzer;

public class TestFilterYear extends BaseTest {

    /**
     * Navigate to www.rbauction.com
     * Search for “F-150”
     * Captures the total number of results returned
     * Applies the “Year” filter to be from “2010” to current year
     * Verifies the number of results returned is different using a numerical comparison (i.e. greater than or less then)
     */
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testYearFilter() {
        HomePage home = new HomePage(driver);
        home.search("F-150");

        //verify search button is displayed before clicking
        Assert.assertTrue(home.searchButtonIsDisplayed());

        //click search button and verify url is changed
        home.clickSearchEnter();
        Assert.assertTrue(driver.getCurrentUrl().contains("search?freeText"));

        SearchResultsPage results = new SearchResultsPage(driver);

        //verify before count before filter
        int beforeCount = results.getResultsCount();
        Assert.assertTrue(beforeCount>=0, "Results count should be integer");

        //apply year filter
        results.applyYearFilter("F-150","2010");
        int afterCount = results.getResultsCount();

        //verify beforecount and aftercount after applying filter is different
        Assert.assertNotEquals(beforeCount, afterCount);
    }
}
