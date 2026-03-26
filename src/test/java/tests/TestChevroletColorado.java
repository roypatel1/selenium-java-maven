package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchResultsPage;
import utils.RetryAnalyzer;

public class TestChevroletColorado extends BaseTest {

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

         SearchResultsPage results = new SearchResultsPage(driver);
         int count = results.getResultsCount();
         Assert.assertTrue(count>=0, "Results count should be integer");
         Assert.assertTrue(results.getFirstResultText().contains("Chevrolet"), "First result does not contain expected text");
         Assert.assertTrue(results.getFirstResultText().contains("Colorado"), "First result does not contain expected text");
     }
}