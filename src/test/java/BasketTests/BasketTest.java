package BasketTests;

import manager.PageFactoryManager;
import model.Product;
import model.Products;
import org.testng.annotations.*;
import pages.HomePage;
import pages.SearchResultPage;
import util.PropertiesReader;
import util.WebDriverSingleton;
import util.XMLReader;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class BasketTest {
    PageFactoryManager pageFactoryManager;
    HomePage homePage;
    SearchResultPage searchResultPage;

    @DataProvider(name = "data", parallel = true)
    public static Object[][] getData() {
        XMLReader xmlReader = new XMLReader();
        Products products = new Products();
        products.setProducts(xmlReader.getXmlData());
        Object[][] productData = new Object[products.getProducts().size()][2];
        for (int i = 0; i < products.getProducts().size(); i++) {
            Object[] column = productData[i];
            column[0] = products.getProducts().get(i).getId();
            column[1] = products.getProducts().get(i);
        }
        return productData;
    }

    @BeforeMethod
    public void testSetUp() {
        PropertiesReader propertiesReader = new PropertiesReader();
        WebDriverSingleton.getInstance().manage().window().maximize();
        WebDriverSingleton.getInstance().get(propertiesReader.getURL());
    }

    @BeforeTest
    public void profileSetUp() {
        chromedriver().setup();
    }

    @Test(dataProvider = "data")
    public void checkThatProductIsInBucketAndBlowPrice(String dataId, Product product) {
        homePage = new HomePage();
        homePage.searchByKeyWord(product.getName());
        searchResultPage = new SearchResultPage();
        searchResultPage.waitForPageToLoad();
        searchResultPage.scrollToElement(searchResultPage.getSearchBrandField());
        searchResultPage.searchProductByBrandFilter(product.getBrand());
        searchResultPage.clickOnAddProductToBasketButton();
        searchResultPage.clickOnBasketIcon();
        assertTrue(Integer.parseInt(product.getPrice()) < searchResultPage.getSumPriceValue());
        assertEquals(searchResultPage.getNumberOfProductsInBasket(), 1);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
     //   WebDriverSingleton.close();
    }
}
