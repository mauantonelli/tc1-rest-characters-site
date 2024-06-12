package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import pages.EditPage;
import pages.HomePage;
import pages.ListPage;

import static org.assertj.core.api.Assertions.assertThat;

public class EditTest {
    private WebDriver driver;
    private HomePage homePage;
    private ListPage listPage;
    private EditPage editPage;

    @BeforeEach
    public void setUp() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        homePage = new HomePage(driver);
        listPage = new ListPage(driver);
        editPage = new EditPage(driver);
        driver.get("https://tc1-rest-characters-site-m5ee.vercel.app");

        homePage.enterName("Jane Doe");
        homePage.enterClass("Mage");
        homePage.enterLevel("5");
        homePage.submitForm();
        driver.switchTo().alert().accept();
        homePage.viewCharacterList();
    }

    @Test
    @DisplayName("Edit Character with Empty Name")
    public void testEditCharacterWithEmptyName() {
        listPage.editCharacter(0);
        editPage.enterName("");
        editPage.enterClass("Sorcerer");
        editPage.enterLevel("6");
        editPage.submitForm();

        assertThat(driver.switchTo().alert().getText()).contains("Todos os campos são obrigatórios.");
        driver.switchTo().alert().accept();
    }

    @Test
    @DisplayName("Edit Character with Empty Class")
    public void testEditCharacterWithEmptyClass() {
        listPage.editCharacter(0);
        editPage.enterName("Jane Smith");
        editPage.enterClass("");
        editPage.enterLevel("6");
        editPage.submitForm();

        assertThat(driver.switchTo().alert().getText()).contains("Todos os campos são obrigatórios.");
        driver.switchTo().alert().accept();
    }

    @Test
    @DisplayName("Edit Character with Empty Level")
    public void testEditCharacterWithEmptyLevel() {
        listPage.editCharacter(0);
        editPage.enterName("Jane Smith");
        editPage.enterClass("Sorcerer");
        editPage.enterLevel("");
        editPage.submitForm();

        assertThat(driver.switchTo().alert().getText()).contains("Todos os campos são obrigatórios.");
        driver.switchTo().alert().accept();
    }

    @Test
    @DisplayName("Edit Character with Invalid Level")
    public void testEditCharacterWithInvalidLevel() {
        listPage.editCharacter(0);
        editPage.enterName("Jane Smith");
        editPage.enterClass("Sorcerer");
        editPage.enterLevel("-1");
        editPage.submitForm();

        assertThat(driver.switchTo().alert().getText()).contains("Todos os campos são obrigatórios.");
        driver.switchTo().alert().accept();
    }
    @Test
    @DisplayName("Edit Character with Same Name, Class, and Level")
    public void testEditCharacterWithSameNameClassLevel() {
        listPage.editCharacter(0);
        editPage.enterName("Jane Doe");
        editPage.enterClass("Mage");
        editPage.enterLevel("5");
        editPage.submitForm();

        assertThat(driver.switchTo().alert().getText()).contains("Personagem Jane Doe atualizado com sucesso!");
        driver.switchTo().alert().accept();
    }

    @Test
    @DisplayName("Edit the Same Character Multiple Times")
    public void testEditSameCharacterMultipleTimes() {
        listPage.editCharacter(0);
        editPage.enterName("First Edit");
        editPage.enterClass("Warrior");
        editPage.enterLevel("10");
        editPage.submitForm();
        driver.switchTo().alert().accept();

        listPage.editCharacter(0);
        editPage.enterName("Second Edit");
        editPage.enterClass("Rogue");
        editPage.enterLevel("8");
        editPage.submitForm();
        driver.switchTo().alert().accept();

        listPage.editCharacter(0);
        editPage.enterName("Third Edit");
        editPage.enterClass("Paladin");
        editPage.enterLevel("15");
        editPage.submitForm();

        assertThat(driver.switchTo().alert().getText()).contains("Personagem Third Edit atualizado com sucesso!");
        driver.switchTo().alert().accept();
    }
    @Test
    @DisplayName("Edit Character Successfully")
    public void testEditCharacter() {
        listPage.editCharacter(0);
        editPage.enterName("Jane Smith");
        editPage.enterClass("Sorcerer");
        editPage.enterLevel("6");
        editPage.submitForm();

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
