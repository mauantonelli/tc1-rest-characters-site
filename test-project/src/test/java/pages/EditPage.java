package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditPage {
    private WebDriver driver;
    private By nameField = By.id("name");
    private By classField = By.id("class");
    private By levelField = By.id("level");
    private By submitButton = By.cssSelector("button[type='submit']");

    public EditPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterName(String name) {
        driver.findElement(nameField).clear();
        driver.findElement(nameField).sendKeys(name);
    }

    public void enterClass(String className) {
        driver.findElement(classField).clear();
        driver.findElement(classField).sendKeys(className);
    }

    public void enterLevel(String level) {
        driver.findElement(levelField).clear();
        driver.findElement(levelField).sendKeys(level);
    }

    public void submitForm() {
        driver.findElement(submitButton).click();
    }
}
