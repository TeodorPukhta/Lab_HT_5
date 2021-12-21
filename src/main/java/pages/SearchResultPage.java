package pages;

import decorator.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.WebDriverSingleton;
import java.util.List;

public class SearchResultPage extends BasePage{
    @FindBy(xpath = "//div[@data-filter-name='producer']//a[@class='checkbox-filter__link']")
    private List<CheckBox> brandCheckBoxes;

    @FindBy(xpath = "//div[@data-filter-name='producer']//input[@name]")
    private InputText searchBrandField;

    @FindBy(xpath = "//button[contains(@class,'buy-button')]")
    private List<Button> addProductToBasketButton;

    @FindBy(xpath = "//li[contains(@class,'item--cart')]")
    private Button basketButton;

    @FindBy(xpath = "//div[@class='cart-receipt__sum-price']//span[not(@*)]")
    private Label sumPriceLabel;

    @FindBy(xpath = "//ul[@class='cart-list ng-star-inserted']//li[@class='cart-list__item ng-star-inserted']")
    private List<Button> productsInBasketLabel;


    public SearchResultPage() {
        super();
    }

    public void searchProductByBrandFilter(final String brand){
        searchBrandField.clearInputField();
        searchBrandField.inputTextIntoInputField(brand);
        brandCheckBoxes.get(1).waitForOneElementInList();
        brandCheckBoxes.get(0).setCheckBox(true);
    }
    public void clickOnAddProductToBasketButton(){
       try {
           addProductToBasketButton.get(0).safeClick();
       } catch (Exception e){
           addProductToBasketButton.get(0).safeClick();
       }
    }
    public void clickOnBasketIcon(){
        basketButton.safeClick();
    }

    public int getSumPriceValue(){
            new WebDriverWait(WebDriverSingleton.getInstance(),15).until(ExpectedConditions.elementToBeClickable(sumPriceLabel));
            return Integer.parseInt(sumPriceLabel.getLabelText());
    }
    public int countProductsInBasket(){
        return productsInBasketLabel.size();
    }

    public InputText getSearchBrandField() {
        return searchBrandField;
    }

    public List<CheckBox> getBrandCheckBoxes() {
        return brandCheckBoxes;
    }
}
