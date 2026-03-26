package pageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasePageElements {

    public static final By FEEDBACK_BUTTON = By.xpath("//*[@data-aut=\"feedback\"]");

    @FindBy(xpath = "//*[@data-testid=\"region_selection.nala\"]//*[text()=\"English\"]")
    public WebElement acceptEnglishLanguage;

    @FindBy(xpath = "//*[@data-aut=\"feedback\"]")
    public WebElement feedbackButton;

    @FindBy(xpath = "//*[@id='truste-consent-button']")
    public WebElement trusteConsentButton;
}