package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class RegistrationTest {
    private WebDriver driver;
    private HomePage homePage;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        homePage = new HomePage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://tc1-rest-characters-site-m5ee.vercel.app");
    }

    @Test
    @DisplayName("Successful Registration")
    public void testSuccessfulRegistration() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        homePage.enterName("John Doe");
        homePage.enterClass("Warrior");
        homePage.enterLevel("10");
        homePage.submitForm();

        wait.until(ExpectedConditions.alertIsPresent());
        assertThat(driver.switchTo().alert().getText()).contains("Personagem John Doe cadastrado com sucesso!");
        driver.switchTo().alert().accept();
    }

    @Test
    @DisplayName("Registration with Empty Name")
    public void testRegistrationWithEmptyName() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        homePage.enterName("");
        homePage.enterClass("Warrior");
        homePage.enterLevel("10");
        homePage.submitForm();

        wait.until(ExpectedConditions.alertIsPresent());
        assertThat(driver.switchTo().alert().getText()).contains("Todos os campos são obrigatórios.");
        driver.switchTo().alert().accept();
    }

    @Test
    @DisplayName("Registration with Empty Class")
    public void testRegistrationWithEmptyClass() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        homePage.enterName("Jane Doe");
        homePage.enterClass("");
        homePage.enterLevel("5");
        homePage.submitForm();

        wait.until(ExpectedConditions.alertIsPresent());
        assertThat(driver.switchTo().alert().getText()).contains("Todos os campos são obrigatórios.");
        driver.switchTo().alert().accept();
    }

    @Test
    @DisplayName("Registration with Empty Level")
    public void testRegistrationWithEmptyLevel() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        homePage.enterName("Jane Doe");
        homePage.enterClass("Mage");
        homePage.enterLevel("");
        homePage.submitForm();

        wait.until(ExpectedConditions.alertIsPresent());
        assertThat(driver.switchTo().alert().getText()).contains("Todos os campos são obrigatórios.");
        driver.switchTo().alert().accept();
    }

    @Test
    @DisplayName("Registration with Invalid Level")
    public void testRegistrationWithInvalidLevel() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        homePage.enterName("Invalid Level");
        homePage.enterClass("Mage");
        homePage.enterLevel("-1");
        homePage.submitForm();

        wait.until(ExpectedConditions.alertIsPresent());
        assertThat(driver.switchTo().alert().getText()).contains("Todos os campos são obrigatórios.");
        driver.switchTo().alert().accept();
    }

    @Test
    @DisplayName("Registration with Different Classes and Levels")
    public void testRegistrationWithDifferentClassesAndLevels() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        homePage.enterName("Character One");
        homePage.enterClass("Mage");
        homePage.enterLevel("5");
        homePage.submitForm();

        wait.until(ExpectedConditions.alertIsPresent());
        assertThat(driver.switchTo().alert().getText()).contains("Personagem Character One cadastrado com sucesso!");
        driver.switchTo().alert().accept();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        homePage.enterName("Character Two");
        homePage.enterClass("Rogue");
        homePage.enterLevel("8");
        homePage.submitForm();

        wait.until(ExpectedConditions.alertIsPresent());
        assertThat(driver.switchTo().alert().getText()).contains("Personagem Character Two cadastrado com sucesso!");
        driver.switchTo().alert().accept();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
