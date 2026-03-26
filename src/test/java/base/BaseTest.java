package base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import pages.BasePage;
import utils.DriverFactory;

import java.io.File;
import java.io.IOException;

public class BaseTest {

    protected WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = DriverFactory.initDriver();
        driver.get("https://www.rbauction.com");
        String currentUserAgent = (String) ((JavascriptExecutor) driver).executeScript("return navigator.userAgent;");
        System.out.println("DEBUG: The browser is identifying as: " + currentUserAgent);
        BasePage basePage = new BasePage(driver);
        basePage.waitForSiteToBeReady();
        basePage.acceptCookies();
        basePage.acceptEnglishLanguage();
    }

    @AfterClass
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @AfterMethod
    public void captureFailure(ITestResult result) throws IOException {
        if (ITestResult.FAILURE == result.getStatus()) {
            TakesScreenshot ts = (TakesScreenshot) DriverFactory.getDriver();
            File src = ts.getScreenshotAs(OutputType.FILE);
            File dest = new File("screenshots/" + result.getName() + ".png");
            FileUtils.copyFile(src, dest);
        }
    }
}