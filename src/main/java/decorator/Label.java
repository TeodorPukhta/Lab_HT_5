package decorator;

import org.openqa.selenium.WebElement;

public class Label extends ElementDecorator{
    public Label(WebElement webElement) {
        super(webElement);
    }
    public String getLabelText(){
        return webElement.getText();
    }
}
