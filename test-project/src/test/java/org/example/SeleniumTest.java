package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SeleniumTest {
    private WebDriver driver;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        // There is a bug in ChromeDriver after Chrome updated to version 111.
        // Add options to driver solve the problem, but it is a temporary workaround
        // For more info: https://groups.google.com/g/chromedriver-users/c/xL5-13_qGaA
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    @Test
    void openProject() throws InterruptedException {
        final var softly = new SoftAssertions();
        driver.get("https://tc1-rest-characters-site-m5ee.vercel.app/");
        Thread.sleep(1000);
        String pageTitle = driver.getTitle();
        System.out.println("Page Title: " + pageTitle);
        softly.assertThat(pageTitle).isEqualTo("Cadastro de Personagens");
        driver.quit();
        softly.assertAll();
    }
}
