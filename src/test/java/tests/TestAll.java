package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchResultsPage;
import utils.RetryAnalyzer;

public class TestAll extends BaseTest {

    /**
     * Test 1:
     *  Navigate to www.rbauction.com
     *  Searches for “Ford F-150”
     *  Captures the total number of results returned
     *  This should be represented as an integer.
     *  Verifies that the first result on the page has the word “Ford F-150” in it
     */
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testFordSearch() {
        HomePage home = new HomePage(driver);
        home.search("Ford F-150");

        //verify search button is displayed before clicking
        Assert.assertTrue(home.searchButtonIsDisplayed());

        //click search button and verify url is changed
        home.clickSearchEnter();
        Assert.assertTrue(driver.getCurrentUrl().contains("search?freeText"));
        Assert.assertTrue(driver.getCurrentUrl().contains("F-150"));

        SearchResultsPage results = new SearchResultsPage(driver);
        int count = results.getResultsCount();
        Assert.assertTrue(count >= 0, "Results count should be integer");
        Assert.assertTrue(results.waitForFirstResultToContainSearchTerm("Ford F-150"), "First result did not update to 'Ford F-150' within 10 seconds");
    }

    /**
     * Test 2:
     *  Navigate to www.rbauction.com
     *  Searches for “Chevrolet Colodrado”
     *  Captures the total number of results returned
     *  This should be represented as an integer.
     *  Verifies that the first result on the page has the word “Chevrolet Colodrado” in it
     */
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testChevroletColoradoSearch() {
        HomePage home = new HomePage(driver);
        home.search("Chevrolet colorado");

        //verify search button is displayed before clicking
        Assert.assertTrue(home.searchButtonIsDisplayed());

        //click search button and verify url is changed
        home.clickSearchEnter();
        Assert.assertTrue(driver.getCurrentUrl().contains("search?freeText"));
        Assert.assertTrue(driver.getCurrentUrl().contains("Chevrolet"));

        SearchResultsPage results = new SearchResultsPage(driver);
        int count = results.getResultsCount();
        Assert.assertTrue(count >= 0, "Results count should be integer");
        Assert.assertTrue(results.getFirstResultText().contains("Chevrolet"), "First result does not contain expected text");
        Assert.assertTrue(results.getFirstResultText().contains("Colorado"), "First result does not contain expected text");
    }

    /**
     * Test 3:
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

        SearchResultsPage results = new SearchResultsPage(driver);

        //verify before count before filter
        int beforeCount = results.getResultsCount();
        Assert.assertTrue(beforeCount >= 0, "Results count should be integer");

        //apply year filter
        results.applyYearFilter("2010", "2026");
        int afterCount = results.getResultsCount();

        //verify beforecount and aftercount after applying filter is different
        Assert.assertNotEquals(beforeCount, afterCount, "value for before count was" + beforeCount + " and value for after count was " + afterCount);
    }

    /**
     * Test 4:
     *  Navigate to www.rbauction.com
     *  Searches for “thiscardoesnotexist”
     *  Captures the total number of results returned
     *  This should be represented as an integer.
     *  Verifies that the first result on the page has the word “Chevrolet Colodrado” in it
     */
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testIncorrectSearch() {
        HomePage home = new HomePage(driver);
        home.search("thiscardoesnotexist");

        //verify search button is displayed before clicking
        Assert.assertTrue(home.searchButtonIsDisplayed());

        //click search button and verify url is changed
        home.clickSearchEnter();
        Assert.assertTrue(driver.getCurrentUrl().contains("thiscardoesnotexist"));

        SearchResultsPage results = new SearchResultsPage(driver);
        int count = results.getResultsCount();
        Assert.assertEquals(count, 0, "Results count should be integer");
    }
}
