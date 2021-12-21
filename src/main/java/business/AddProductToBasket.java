package business;


import pages.SearchResultPage;

public class AddProductToBasket {
    private SearchResultPage searchResultPage;

    public void addFirstProductInListToBasket(SearchResultPage searchResultPage){
        this.searchResultPage = searchResultPage;
        searchResultPage.clickOnAddProductToBasketButton();
        searchResultPage.clickOnBasketIcon();
    }
    public int getBasketSumValue(){
        return searchResultPage.getSumPriceValue();
    }
    public int getNumberOfProductsInBasket(){
        return searchResultPage.countProductsInBasket();
    }
}
