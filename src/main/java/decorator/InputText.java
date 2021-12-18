package decorator;

import org.openqa.selenium.WebElement;

public class InputText extends ElementDecorator{
    public InputText(WebElement webElement) {
        super(webElement);
    }

    public void clearInputField(){
        if(!webElement.getText().equals("")) webElement.clear();
    }
    public void inputTextIntoInputField(CharSequence charSequence){
        webElement.sendKeys(charSequence);
    }
}
