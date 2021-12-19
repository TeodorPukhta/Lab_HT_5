package pages;

import decorator.CustomFieldDecorator;
import decorator.InputText;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.WebDriverSingleton;

public class BasePage {
    WebDriver webDriver;

    public BasePage() {
        this.webDriver = WebDriverSingleton.getInstance();
        PageFactory.initElements(new CustomFieldDecorator(WebDriverSingleton.getInstance()),this);
    }

    public void waitForPageToLoad(){
        WebDriverWait wait = new WebDriverWait(WebDriverSingleton.getInstance(),10);
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }
    public void scrollToElement(InputText webElement){
//        Actions actions = new Actions(webDriver);
//        actions.moveByOffset(webElement.getLocation().x,webElement.getLocation().y).build().perform();
        JavascriptExecutor js = (JavascriptExecutor) WebDriverSingleton.getInstance();
      //  js.executeScript("arguments[0].scrollIntoView();", webElement);
        js.executeScript("window.scrollBy(0,"+webElement.getLocation().x+")", "");
    }
}
