package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.ListPage;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteTest {
    private WebDriver driver;
    private HomePage homePage;
    private ListPage listPage;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        homePage = new HomePage(driver);
        listPage = new ListPage(driver);
        driver.get("https://tc1-rest-characters-site-m5ee.vercel.app");

        homePage.enterName("Doe John");
        homePage.enterClass("Rogue");
        homePage.enterLevel("3");
        homePage.submitForm();
        driver.switchTo().alert().accept();
        homePage.viewCharacterList();
    }

    @Test
    @DisplayName("Delete Character and Verify Success Alert")
    public void testDeleteCharacter() {
        listPage.deleteCharacter(0);

        assertThat(driver.switchTo().alert().getText()).contains("Personagem excluído com sucesso!");
        driver.switchTo().alert().accept();
    }

    @Test
    @DisplayName("Delete Character and Verify List is Empty")
    public void testDeleteCharacterAndVerifyListIsEmpty() {
        listPage.deleteCharacter(0);

        assertThat(driver.switchTo().alert().getText()).contains("Personagem excluído com sucesso!");
        driver.switchTo().alert().accept();
        assertThat(listPage.isCharacterListEmpty()).isTrue();
    }

    @Test
    @DisplayName("Attempt to Delete Non-Existing Character")
    public void testDeleteNonExistingCharacter() {
        try {
            listPage.deleteCharacter(1);
        } catch (IndexOutOfBoundsException e) {
            assertThat(e).isInstanceOf(IndexOutOfBoundsException.class);
        }
    }

    @Test
    @DisplayName("Delete Multiple Characters and Verify List is Empty")
    public void testDeleteMultipleCharacters() {
        homePage.enterName("Jane Doe");
        homePage.enterClass("Mage");
        homePage.enterLevel("5");
        homePage.submitForm();
        driver.switchTo().alert().accept();
        homePage.viewCharacterList();

        listPage.deleteCharacter(0);
        assertThat(driver.switchTo().alert().getText()).contains("Personagem excluído com sucesso!");
        driver.switchTo().alert().accept();

        listPage.deleteCharacter(0);
        assertThat(driver.switchTo().alert().getText()).contains("Personagem excluído com sucesso!");
        driver.switchTo().alert().accept();

        assertThat(listPage.isCharacterListEmpty()).isTrue();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
