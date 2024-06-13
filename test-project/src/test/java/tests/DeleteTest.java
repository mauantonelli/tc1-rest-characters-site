package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.ListPage;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteTest {
    private WebDriver driver;
    private HomePage homePage;
    private ListPage listPage;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        homePage = new HomePage(driver);
        listPage = new ListPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Increased timeout
        driver.get("https://tc1-rest-characters-site-m5ee.vercel.app");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        homePage.enterName("Doe John");
        homePage.enterClass("Rogue");
        homePage.enterLevel("3");
        homePage.submitForm();
        wait.until(ExpectedConditions.alertIsPresent()).accept();
        homePage.viewCharacterList();
    }

    @Test
    @DisplayName("Delete Character and Verify Success Alert")
    public void testDeleteCharacter() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".delete")));
        listPage.deleteCharacter(0);

        wait.until(ExpectedConditions.alertIsPresent());
        assertThat(driver.switchTo().alert().getText()).contains("Personagem excluído com sucesso!");
        driver.switchTo().alert().accept();
    }

    @Test
    @DisplayName("Delete Character and Verify List is Empty")
    public void testDeleteCharacterAndVerifyListIsEmpty() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".delete")));
        listPage.deleteCharacter(0);

        wait.until(ExpectedConditions.alertIsPresent());
        assertThat(driver.switchTo().alert().getText()).contains("Personagem excluído com sucesso!");
        driver.switchTo().alert().accept();

        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(".delete"), 0));
        assertThat(listPage.isCharacterListEmpty()).isTrue();
    }

    @Test
    @DisplayName("Attempt to Delete Non-Existing Character")
    public void testDeleteNonExistingCharacter() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".delete")));
        try {
            listPage.deleteCharacter(1);
        } catch (IndexOutOfBoundsException e) {
            assertThat(e).isInstanceOf(IndexOutOfBoundsException.class);
        }
    }

    @Test
    @DisplayName("Test delete multiple characters")
    public void testDeleteMultipleCharacters() {
        homePage.viewInsertCharacter();
        homePage.enterName("Doe John");
        homePage.enterClass("Rogue");
        homePage.enterLevel("3");
        homePage.submitForm();
        wait.until(ExpectedConditions.alertIsPresent()).accept();
        homePage.viewCharacterList();

        listPage.deleteCharacter(0);
        assertThat(driver.switchTo().alert().getText()).contains("Personagem excluído com sucesso!");
        wait.until(ExpectedConditions.alertIsPresent()).accept();

        listPage.deleteCharacter(0);
        assertThat(driver.switchTo().alert().getText()).contains("Personagem excluído com sucesso!");
        wait.until(ExpectedConditions.alertIsPresent()).accept();

        assertThat(listPage.isCharacterListEmpty()).isTrue();
    }


    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
