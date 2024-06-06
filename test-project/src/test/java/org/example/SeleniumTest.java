package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class SeleniumTest {
    private WebDriver driver;
    private String BASE_URL = "https://tc1-rest-characters-site-m5ee.vercel.app/";

//    @BeforeEach
//    void setUp() {
//        WebDriverManager.chromedriver().setup();
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--remote-allow-origins=*");
//        driver = new ChromeDriver(options);
//    }

    @BeforeEach
    void setUpEdge() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new EdgeDriver(options);
    }

    @Test
    void openProject() throws InterruptedException {
        final var softly = new SoftAssertions();
        driver.get(BASE_URL);
        Thread.sleep(1000);
        String pageTitle = driver.getTitle();
        System.out.println("Page Title: " + pageTitle);
        softly.assertThat(pageTitle).isEqualTo("Cadastro de Personagens");
        driver.quit();
        softly.assertAll();
    }
}
