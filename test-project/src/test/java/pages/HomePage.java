package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private WebDriver driver;
    private By nameField = By.id("name");
    private By classField = By.id("class");
    private By levelField = By.id("level");
    private By submitButton = By.cssSelector("button[type='submit']");
    private By viewListButton = By.xpath("//button[contains(text(),'Ver Lista de Personagens')]");
    private By viewInsertButton = By.xpath("//button[contains(text(),'Voltar para Cadastro')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void enterClass(String className) {
        driver.findElement(classField).sendKeys(className);
    }

    public void enterLevel(String level) {
        driver.findElement(levelField).sendKeys(level);
    }

    public void submitForm() {
        driver.findElement(submitButton).click();
    }

    public void viewCharacterList() {
        driver.findElement(viewListButton).click();
    }

    public void viewInsertCharacter() {
        driver.findElement(viewInsertButton).click();
    }

    public WebElement getViewCharacterListButton() {
        return driver.findElement(viewListButton);
    }
}
