package tests;

import model.Product;
import model.Products;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.SearchResultPage;
import util.PropertiesReader;
import util.WebDriverSingleton;
import util.XMLReader;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;

public class BasketTest {

    @DataProvider(parallel = true)
    public Object[][] provideData() {
        XMLReader xmlReader = new XMLReader();
        Products products = new Products();
        products.setProducts(xmlReader.getXmlData());
        return products.getProducts().stream()
                .map(product -> new Object[]{product.getId(),product})
                .toArray(Object[][]::new);
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

    @Test(dataProvider = "provideData")
    public void checkThatProductIsInBucketAndBlowPrice(String dataId, Product product) {
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage();
        homePage.searchByKeyWord(product.getName());
        SearchResultPage searchResultPage = new SearchResultPage();
        searchResultPage.waitForPageToLoad();
        searchResultPage.scrollToElement(searchResultPage.getSearchBrandField());
        searchResultPage.searchProductByBrandFilter(product.getBrand());
        searchResultPage.clickOnAddProductToBasketButton();
        searchResultPage.clickOnBasketIcon();
        softAssert.assertTrue(Integer.parseInt(product.getPrice()) < searchResultPage.getSumPriceValue());
        softAssert.assertEquals(searchResultPage.getNumberOfProductsInBasket(), 1);
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        WebDriverSingleton.close();
    }
}
