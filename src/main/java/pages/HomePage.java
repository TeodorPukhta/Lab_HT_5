package pages;

import decorator.Button;
import decorator.InputText;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage{
    @FindBy(xpath = "//button[contains(@class,'search-form__submit')]")
    private Button searchButton;

    @FindBy(xpath = "//input[@name='search']")
    private InputText searchField;

    public HomePage() {
        super();
    }

    public void searchByKeyWord(final String keyword){
        new WebDriverWait(webDriver,10).until(ExpectedConditions.visibilityOf(searchField));
        searchField.clearInputField();
        searchField.inputTextIntoInputField(keyword);
        searchButton.safeClick();
    }
}
