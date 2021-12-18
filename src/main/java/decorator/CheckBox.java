package decorator;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import util.WebDriverSingleton;

import java.time.Duration;
import java.util.NoSuchElementException;

public class CheckBox extends ElementDecorator{
    public CheckBox(WebElement webElement) {
        super(webElement);
    }

    public void waitForOneElementInList(){
        new FluentWait<>(WebDriverSingleton.getInstance())
                .withTimeout(Duration.ofSeconds(3))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.invisibilityOf(webElement));
    }
    public void setCheckBox(boolean checkBoxStatus){
        if((webElement.isSelected() && !checkBoxStatus) || (!webElement.isSelected()&& checkBoxStatus)) webElement.click();
        else System.out.println("Element is in needed state");
    }
}
