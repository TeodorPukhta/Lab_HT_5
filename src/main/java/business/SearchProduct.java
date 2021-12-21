package business;

import pages.HomePage;
import pages.SearchResultPage;

public class SearchProduct {
    private SearchResultPage searchResultPage = new SearchResultPage();

    public void searchProduct(String product, String brand){
        new HomePage().searchByKeyWord(product);
        searchResultPage.waitForPageToLoad();
        searchResultPage.scrollToElement(searchResultPage.getSearchBrandField());
        searchResultPage.searchProductByBrandFilter(brand);
    }
    public SearchResultPage getSearchResultPage() {
        return searchResultPage;
    }
}
