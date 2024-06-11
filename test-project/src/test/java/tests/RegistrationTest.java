package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import pages.HomePage;

import static org.assertj.core.api.Assertions.assertThat;

public class RegistrationTest {
    private WebDriver driver;
    private HomePage homePage;

    @BeforeEach
    public void setUp() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        homePage = new HomePage(driver);
        driver.get("https://tc1-rest-characters-site-m5ee.vercel.app");
    }

    @Test
    public void testSuccessfulRegistration(){
        homePage.enterName("John Doe");
        homePage.enterClass("Warrior");
        homePage.enterLevel("10");
        homePage.submitForm();

        assertThat(driver.switchTo().alert().getText()).contains("Personagem John Doe cadastrado com sucesso!");
        driver.switchTo().alert().accept();

    }

    @Test
    public void testRegistrationWithEmptyName() {
        homePage.enterName("");
        homePage.enterClass("Warrior");
        homePage.enterLevel("10");
        homePage.submitForm();

        assertThat(driver.switchTo().alert().getText()).contains("Todos os campos são obrigatórios.");
        driver.switchTo().alert().accept();
    }

    @Test
    public void testRegistrationWithEmptyClass() {
        homePage.enterName("Jane Doe");
        homePage.enterClass("");
        homePage.enterLevel("5");
        homePage.submitForm();

        assertThat(driver.switchTo().alert().getText()).contains("Todos os campos são obrigatórios.");
        driver.switchTo().alert().accept();
    }

    @Test
    public void testRegistrationWithEmptyLevel() {
        homePage.enterName("Jane Doe");
        homePage.enterClass("Mage");
        homePage.enterLevel("");
        homePage.submitForm();

        assertThat(driver.switchTo().alert().getText()).contains("Todos os campos são obrigatórios.");
        driver.switchTo().alert().accept();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
