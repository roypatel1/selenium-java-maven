package pageElements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchResultsPageElements {

    @FindBy(xpath = "//*[@data-testid=\"search-count-header\"]")
    public WebElement resultsCount;

    @FindBy(xpath = "//*[@data-testid=\"item-card-shared-component\"]")
    public List<WebElement> searchResults;

    @FindBy(xpath = "(//*[@data-testid=\"item-card-shared-component\"])[1]")
    public WebElement firstResult;

    @FindBy(xpath = "//*[@data-testid=\"search filter button\"]")
    public WebElement filter;

    @FindBy(xpath = "//*[@id=\"manufactureYearRange-header\"]//*[@data-testid=\"ExpandMoreIcon\"]")
    public WebElement filterYearExpand;

    @FindBy(xpath = "//*[@id=\"manufactureYearRange_min\"]")
    public WebElement filterInputYearFrom;

    @FindBy(xpath = "//*[@id=\"manufactureYearRange_max\"]")
    public WebElement filterInputYearTo;

    @FindBy(xpath = "//*[@data-testid=\"advanced-search-freeText\"]")
    public WebElement filterKeywordInput;

    @FindBy(xpath = "//*[@data-testid=\"advanced search button\"]")
    public WebElement filterSearchButton;
}