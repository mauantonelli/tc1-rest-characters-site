package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import pages.HomePage;
import pages.ListPage;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteTest {
    private WebDriver driver;
    private HomePage homePage;
    private ListPage listPage;

    @BeforeEach
    public void setUp() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
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
    public void testDeleteCharacter() {
        listPage.deleteCharacter(0);

        assertThat(driver.switchTo().alert().getText()).contains("Personagem excluído com sucesso!");
        driver.switchTo().alert().accept();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
