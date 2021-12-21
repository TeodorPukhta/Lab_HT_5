package tests;

import business.AddProductToBasket;
import business.SearchProduct;
import business.TestVerification;
import model.Product;
import model.Products;
import org.testng.annotations.*;
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
        SearchProduct searchProduct = new SearchProduct();
        searchProduct.searchProduct(product.getName(), product.getBrand());

        AddProductToBasket addProductToBasket = new AddProductToBasket();
        addProductToBasket.addFirstProductInListToBasket(searchProduct.getSearchResultPage());

        new TestVerification().checkBasketTest(product.getPrice(), addProductToBasket);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        WebDriverSingleton.close();
    }
}
