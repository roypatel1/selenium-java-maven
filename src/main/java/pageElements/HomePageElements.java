package pageElements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePageElements {

    @FindBy(xpath = "//*[@data-testid='search input']")
    public WebElement searchBox;

    @FindBy(xpath = "//*[@data-testid=\"search button\"]")
    public WebElement searchButton;
}