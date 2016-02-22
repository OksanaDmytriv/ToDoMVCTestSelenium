package todomvctest.core.pages;

import org.openqa.selenium.WebElement;
import static com.codeborne.selenide.Selenide.actions;

public class Helpers {

    public static void hover(WebElement element) {
         actions().moveToElement(element).perform();
    }

    public static void doubleClick(WebElement element) {
        actions().doubleClick(element).perform();
    }
}
