package pageElements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchResultsPageElements {

    @FindBy(xpath = "//*[@data-testid=\"non-cat-header\"]")
    public WebElement resultsCount;

    @FindBy(xpath = "//*[@data-testid=\"item-card-shared-component\"]")
    public List<WebElement> searchResults;

    @FindBy(xpath = "(//*[@data-testid=\"item-card-shared-component\"])[1]")
    public WebElement firstResult;

    @FindBy(xpath = "//*[@data-testid=\"search filter button\"]")
    public WebElement filter;

    @FindBy(xpath = "//*[@data-testid=\"advanced-search-manufactureYearRange\"]")
    public WebElement filterInputYear;

    @FindBy(xpath = "//*[@data-testid=\"advanced-search-freeText\"]")
    public WebElement filterKeywordInput;

    @FindBy(xpath = "//*[@data-testid=\"advanced search button\"]")
    public WebElement filterSearchButton;
}