package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JsUtils {

    public void forceClearSearchBoxNew(WebDriver driver, WebElement input) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // 1. Bypass React's value tracking by calling the native setter
        // 2. Dispatch 'input' and 'change' events so React state updates
        String script = "var element = arguments[0]; " +
                "var nativeInputValueSetter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set; " +
                "nativeInputValueSetter.call(element, ''); " +
                "element.dispatchEvent(new Event('input', { bubbles: true })); " +
                "element.dispatchEvent(new Event('change', { bubbles: true }));";

        js.executeScript(script, input);
    }
}
