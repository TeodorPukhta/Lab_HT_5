package pages;

import decorator.CustomFieldDecorator;
import decorator.InputText;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.WebDriverSingleton;

public class BasePage {
    WebDriver webDriver;

    public BasePage() {
        this.webDriver = WebDriverSingleton.getInstance();
        PageFactory.initElements(new CustomFieldDecorator(webDriver),this);
    }

    public void waitForPageToLoad(){
        WebDriverWait wait = new WebDriverWait(WebDriverSingleton.getInstance(),10);
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }
    public void scrollToElement(InputText webElement){
        JavascriptExecutor js = (JavascriptExecutor) WebDriverSingleton.getInstance();
        js.executeScript("window.scrollBy(0,"+webElement.getLocation().x+1+")", "");
    }
}
