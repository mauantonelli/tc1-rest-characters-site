package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ListPage {
    private WebDriver driver;
    private By characterList = By.id("character-list");

    public ListPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getCharacterInfo(int index) {
        return driver.findElements(characterList).get(index).getText();
    }

    public void editCharacter(int index) {
        driver.findElements(By.cssSelector(".edit")).get(index).click();
    }

    public void deleteCharacter(int index) {
        driver.findElements(By.cssSelector(".delete")).get(index).click();
        driver.switchTo().alert().accept();
    }
}
