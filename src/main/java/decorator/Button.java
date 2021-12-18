package decorator;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import util.WebDriverSingleton;
import java.time.Duration;

public class Button extends ElementDecorator{
    public Button(WebElement webElement) {
        super(webElement);
    }

    public void waitUntilClickable(){
        new FluentWait<>(WebDriverSingleton.getInstance())
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public void safeClick(){
        try {
                super.click();
        } catch (Exception e) {
            waitUntilClickable();
            super.click();
        }
    }
}
