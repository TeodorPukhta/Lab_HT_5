package business;
import org.testng.asserts.SoftAssert;

public class TestVerification {
    public void checkBasketTest(String price, AddProductToBasket addProductToBasket){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(Integer.parseInt(price) < addProductToBasket.getBasketSumValue());
        softAssert.assertEquals(addProductToBasket.getNumberOfProductsInBasket(), 1);
        softAssert.assertAll();
    }
}
