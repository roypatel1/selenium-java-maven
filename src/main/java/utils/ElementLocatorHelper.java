package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;

public class ElementLocatorHelper {

    /*
    To use containingClass or classInWidget or classInWidgetSelectIndex please write code like below, this helpers will be very helpful moving forward, specially around widgets with same name
        By quickActionsLocator = By.xpath(classInWidget("widget-c1-quick-actions", "quick-action-trigger" ));

        private By quickActionDropdown(int elementIndex) {
        return By.xpath(classInWidgetSelectIndex("uib-dropdown-menu", "quick-action-option", elementIndex));
        }
     */

    private static String containingClass(String className) {
        return "contains(concat(' ',normalize-space(@class),' '),' " + className + " ')";
    }

    public String qaIdInWidget(String widgetClassName, String qaId) {
        String widget = "[contains(concat(' ',normalize-space(@class),' '),' " + widgetClassName + " ')]";
        String elementMatcher = "[@data-qa=\"" + qaId + "\"]";
        return "//*" + widget + "//" + elementMatcher;
    }

    public String classInWidget(String widgetClassName, String elementClassName) {
        String widget = "[" + containingClass(widgetClassName) + "]";
        String elementMatcher = "[" + containingClass(elementClassName) + "]";
        return "//*" + widget + "//*" + elementMatcher;
    }

    public String classInWidgetSelectIndex(String widgetClassName, String elementClassName, int index) {
        String widget = "[" + widgetClassName + "]";
        String elementMatcher = "[" + elementClassName + "]";
        return "//*" + widget + "//*" + elementMatcher + "[" + index + "]";
    }

    public void waitForClickabilityOfAndClick(WebDriver driver, WebElement webElement, int waitTime) throws TimeoutException{
            WebDriverWait wait = new WebDriverWait(driver, Duration.of(waitTime, SECONDS));
            wait.until(ExpectedConditions.elementToBeClickable(webElement)).click();
    }

    public WebElement waitUntilExpectedConditionClickabilityOfElement(WebDriver driver, WebElement webElement, int waitTime) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.of(waitTime, SECONDS));
        return wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public WebElement waitUntilExpectedConditionVisabilityOf(WebDriver driver, By elementLocator, int waitTime) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.of(waitTime, SECONDS));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
    }

    public WebElement waitUntilExpectedConditionVisabilityOf_HandledException(WebDriver driver, By elementLocator, int waitTime) throws TimeoutException{
        WebDriverWait wait = new WebDriverWait(driver, Duration.of(waitTime, SECONDS));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
    }

    public WebElement waitUntilExpectedConditionVisabilityOf(WebDriver driver, WebElement elementLocator, int waitTime) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.of(waitTime, SECONDS));
        return wait.until(ExpectedConditions.visibilityOf(elementLocator));
    }

    public boolean checkMobileElementExistWithWait(WebDriver driver, WebElement element) {
        boolean objectExist = false;
        try {
            if (checkElementIsDisplayed(driver, element)) {
                objectExist = true;
            } else {
                objectExist = isElementDisplayed(driver, element, 2);
            }
            return objectExist;
        } catch (Exception e) {
            return false;
        }
    }

    public String getTextIfMobileElementExist(WebDriver driver, WebElement element) {
        boolean objectExist = false;
        try {
            if (checkElementIsDisplayed(driver, element)) {
                objectExist = true;
            }
            return element.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean checkMobileElementExistWithoutWait(WebDriver driver, WebElement element) {
        boolean objectExist = false;
        try {
            if (checkElementIsDisplayed(driver, element)) {
                objectExist = true;
            }
            return objectExist;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean checkElementIsDisplayed(WebDriver driver, WebElement element) {
        try {
            return retryingFind(driver,element);
        } catch (Exception e) {
            System.out.println("element does not exist  : " + element);
            return false;
        }
    }

    public boolean isElementDisplayed(WebDriver driver, WebElement ele, int waitTime) {
        boolean found = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.of(waitTime, SECONDS));
            WebElement element = wait.until(ExpectedConditions.visibilityOf(ele));
            if (element.isDisplayed()) {
                found = true;
            }
        } catch (Exception e) {
            System.out.println(" in the helper method exception caught element is not displayed" + e );
            found = false;
        }
        return found;
    }

    public boolean scrollDownIntoView(WebDriver driver, WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            return element.isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean scrollIntoView(WebDriver driver, WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: \"auto\", block: \"center\", inline: \"nearest\"});", element);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean scrollUpIntoView(WebDriver driver, WebElement element) {
        try {
            try{
                scrollToTopOfPage(driver);
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
            } catch (NoSuchElementException nse){
                driver.navigate().refresh();
                scrollToTopOfPage(driver);
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
            }
            return element.isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void scrollToTopOfPage(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
    }

    public void scrollToLocateElement(WebDriver driver, WebElement element){
        int tryScrolling = 0;
        boolean elementFound = checkMobileElementExistWithoutWait(driver, element);
        while (!elementFound && tryScrolling < 5) {
            elementFound = scrollUpIntoView(driver, element);
            if(elementFound){
                break;
            }
            tryScrolling++;
        }
        tryScrolling = 0;
        while (!elementFound && tryScrolling < 8) {
            elementFound = scrollDownIntoView(driver, element);
            if(elementFound){
                break;
            }
            tryScrolling++;
        }
    }

    public void clickElementWithExecuteScript(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {
            js.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            System.out.println("could not click element with java script " + e);
        }
    }

    public void clickElementWithExecuteScriptWithoutException(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    public void clickElementWithActionChain(WebDriver driver, WebElement element) {
        // Create an object of actions class and pass reference of WebDriver as a parameter to its constructor.
        Actions actions = new Actions(driver);

        // Call moveToElement() method of actions class to move mouse cursor to a WebElement A.
        actions.moveToElement(element);
        actions.click();
    }

    public boolean retryingFindClick(WebDriver driver, WebElement element) {
        boolean result = false;
        int attempts = 0;
        while(!result || attempts < 2 ) {
            try {
                element.click();
                result = true;
                break;
            } catch (ElementClickInterceptedException elementClickInterceptedException) {
                try {
                    clickElementWithExecuteScriptWithoutException(driver,element);
                    result = true;
                    break;
                } catch (Exception exception){
                    result = false;
                }
            } catch(Exception e) {
                result = false;
            }
            attempts++;
        }
        return result;
    }

    public boolean retryingFind(WebDriver driver, WebElement element) {
        boolean result = false;
        int attempts = 0;
        while(attempts < 2) {
            try {
                element.isDisplayed();
                result = true;
                break;
            } catch(StaleElementReferenceException e) {
                result = false;
            }
            attempts++;
        }
        return result;
    }

    public void waitUntilPageLoaded(WebDriver driver){
        int waitTime=5;
        new WebDriverWait(driver, Duration.of(waitTime, SECONDS)).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public void waitUntilPageLoadedNew(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState")
                        .equals("complete")
        );
    }

    public void waitUntilPageTitleChange(WebDriver driver, String expectedTitle){
        int waitTime=5;
        WebDriverWait wait = new WebDriverWait(driver,  Duration.of(waitTime, SECONDS));
        wait.until(ExpectedConditions.titleContains(expectedTitle));
    }

    public void waitUntilPageUrlChange(WebDriver driver, String expectedUrl){
        int waitTime=5;
        WebDriverWait wait = new WebDriverWait(driver,  Duration.of(waitTime, SECONDS));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
    }

    public void waitUntilPageUrlContains(WebDriver driver, String expectedUrl){
        int waitTime=5;
        WebDriverWait wait = new WebDriverWait(driver,  Duration.of(waitTime, SECONDS));
        wait.until(ExpectedConditions.urlContains(expectedUrl));
    }

    public void scrollDownOnPage(WebDriver driver)
        {
            //to perform Scroll on application using Selenium
            JavascriptExecutor js = (JavascriptExecutor) driver;
            //js.executeScript("window.scrollBy(0,350)", "");
            js.executeScript("window.scrollBy(0,150)", "");
        }

    public void scrollDownOnPage(WebDriver driver, int pixels)
    {
        //to perform Scroll on application using Selenium
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,"+pixels+")", "");
    }

    public void scrollDownOnPage750(WebDriver driver)
    {
        //to perform Scroll on application using Selenium
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,750)", "");
    }

    public void waitSleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
