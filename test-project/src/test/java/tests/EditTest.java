package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.EditPage;
import pages.HomePage;
import pages.ListPage;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class EditTest {
    private WebDriver driver;
    private HomePage homePage;
    private ListPage listPage;
    private EditPage editPage;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        homePage = new HomePage(driver);
        listPage = new ListPage(driver);
        editPage = new EditPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://tc1-rest-characters-site-m5ee.vercel.app");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        homePage.enterName("Jane Doe");
        homePage.enterClass("Mage");
        homePage.enterLevel("5");
        homePage.submitForm();
        wait.until(ExpectedConditions.alertIsPresent()).accept();
        homePage.viewCharacterList();
    }

    @Test
    @DisplayName("Edit Character with Empty Name")
    public void testEditCharacterWithEmptyName() {
        listPage.editCharacter(0);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        editPage.enterName("");
        editPage.enterClass("Sorcerer");
        editPage.enterLevel("6");
        editPage.submitForm();

        wait.until(ExpectedConditions.alertIsPresent());
        assertThat(driver.switchTo().alert().getText()).contains("Todos os campos são obrigatórios.");
        driver.switchTo().alert().accept();
    }

    @Test
    @DisplayName("Edit Character with Empty Class")
    public void testEditCharacterWithEmptyClass() {
        listPage.editCharacter(0);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        editPage.enterName("Jane Smith");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("class")));
        editPage.enterClass("");
        editPage.enterLevel("6");
        editPage.submitForm();

        wait.until(ExpectedConditions.alertIsPresent());
        assertThat(driver.switchTo().alert().getText()).contains("Todos os campos são obrigatórios.");
        driver.switchTo().alert().accept();
    }

    @Test
    @DisplayName("Edit Character with Empty Level")
    public void testEditCharacterWithEmptyLevel() {
        listPage.editCharacter(0);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        editPage.enterName("Jane Smith");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("class")));
        editPage.enterClass("Sorcerer");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("level")));
        editPage.enterLevel("");
        editPage.submitForm();

        wait.until(ExpectedConditions.alertIsPresent());
        assertThat(driver.switchTo().alert().getText()).contains("Todos os campos são obrigatórios.");
        driver.switchTo().alert().accept();
    }

    @Test
    @DisplayName("Edit Character with Invalid Level")
    public void testEditCharacterWithInvalidLevel() {
        listPage.editCharacter(0);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        editPage.enterName("Jane Smith");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("class")));
        editPage.enterClass("Sorcerer");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("level")));
        editPage.enterLevel("-1");
        editPage.submitForm();

        wait.until(ExpectedConditions.alertIsPresent());
        assertThat(driver.switchTo().alert().getText()).contains("Todos os campos são obrigatórios.");
        driver.switchTo().alert().accept();
    }

    @Test
    @DisplayName("Edit Character with Same Name, Class, and Level")
    public void testEditCharacterWithSameNameClassLevel() {
        listPage.editCharacter(0);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        editPage.enterName("Jane Doe");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("class")));
        editPage.enterClass("Mage");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("level")));
        editPage.enterLevel("5");
        editPage.submitForm();

        wait.until(ExpectedConditions.alertIsPresent());
        assertThat(driver.switchTo().alert().getText()).contains("Personagem Jane Doe atualizado com sucesso!");
        driver.switchTo().alert().accept();
    }

    @Test
    @DisplayName("Edit the Same Character Multiple Times")
    public void testEditSameCharacterMultipleTimes() {
        listPage.editCharacter(0);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        editPage.enterName("First Edit");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("class")));
        editPage.enterClass("Warrior");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("level")));
        editPage.enterLevel("10");
        editPage.submitForm();
        wait.until(ExpectedConditions.alertIsPresent()).accept();

        listPage.editCharacter(0);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        editPage.enterName("Second Edit");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("class")));
        editPage.enterClass("Rogue");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("level")));
        editPage.enterLevel("8");
        editPage.submitForm();
        wait.until(ExpectedConditions.alertIsPresent()).accept();

        listPage.editCharacter(0);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        editPage.enterName("Third Edit");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("class")));
        editPage.enterClass("Paladin");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("level")));
        editPage.enterLevel("15");
        editPage.submitForm();

        wait.until(ExpectedConditions.alertIsPresent());
        assertThat(driver.switchTo().alert().getText()).contains("Personagem Third Edit atualizado com sucesso!");
        driver.switchTo().alert().accept();
    }

    @Test
    @DisplayName("Edit Character Successfully")
    public void testEditCharacter() {
        listPage.editCharacter(0);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        editPage.enterName("Jane Smith");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("class")));
        editPage.enterClass("Sorcerer");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("level")));
        editPage.enterLevel("6");
        editPage.submitForm();

        wait.until(ExpectedConditions.alertIsPresent());
        assertThat(driver.switchTo().alert().getText()).contains("Personagem Jane Smith atualizado com sucesso!");
        driver.switchTo().alert().accept();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
