package pages;

import decorator.CustomFieldDecorator;
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
        webDriver = WebDriverSingleton.getInstance();
        PageFactory.initElements(new CustomFieldDecorator(webDriver),this);
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }
    public void waitForPageToLoad(){
        WebDriverWait wait = new WebDriverWait(webDriver,10);
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }
    public void scrollToElement(WebElement webElement){
        Actions actions = new Actions(webDriver);
        actions.moveByOffset(webElement.getLocation().x+1,webElement.getLocation().y).build().perform();
    }
}
