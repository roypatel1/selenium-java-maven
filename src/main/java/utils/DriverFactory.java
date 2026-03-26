package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import java.time.Duration;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver initDriver() {

        switch (ConfigReader.getBrowser()) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                String env = ConfigReader.getEnv();
                if (env != null && env.equals("ci")) {
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-dev-shm-usage");
                } else if (env != null && env.equals("local")) {
                    //create directory in your local by executing this command mkdir -p /Users/rp/selenium-profile
                    //options.addArguments("user-data-dir=/Users/rp/selenium-profile");
                    options.setPageLoadStrategy(PageLoadStrategy.EAGER);
                }
                if(ConfigReader.getHeadless()){
                    options.addArguments("--headless=new");
                }
                options.addArguments("--disable-blink-features=AutomationControlled");
                options.addArguments("--start-maximized");
                options.addArguments("--disable-notifications");
                options.addArguments("--disable-geolocation");
                options.addArguments("--remote-allow-origins=*");
                options.addArguments("--lang=en");
                options.addArguments("--disable-infobars");

                WebDriverManager.chromedriver().setup();

                driver.set(new ChromeDriver(options));
                driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
                driver.get().manage().window().maximize();
                return driver.get();

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions ffOptions = new FirefoxOptions();

                ffOptions.addArguments("--ignore-ssl-errors=yes");
                ffOptions.addArguments("--ignore-certificate-errors");
                if(ConfigReader.getHeadless()){
                    ffOptions.addArguments("--no-firefox");
                }
                ffOptions.addArguments("--no-sandbox");
                ffOptions.addArguments("--disable-extensions");
                ffOptions.addArguments("--disable-dev-shm-usage");
                driver.set(new FirefoxDriver(ffOptions));
                driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
                driver.get().manage().window().maximize();
                return driver.get();
            case "safari":
                WebDriverManager.safaridriver().setup();
                driver.set(new SafariDriver());
                return driver.get();
            case "ie":
                WebDriverManager.iedriver().setup();
                driver.set(new InternetExplorerDriver());
                return driver.get();
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver.set(new EdgeDriver());
                return driver.get();
        }
        return driver.get();
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        driver.get().quit();
        driver.remove();
    }
}