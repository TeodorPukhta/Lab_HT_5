package manager;

import pages.HomePage;
import pages.SearchResultPage;

public class PageFactoryManager {

    public HomePage getHomePage() {
        return new HomePage();
    }

    public SearchResultPage getSearchResultPage() {
        return new SearchResultPage();
    }
}
