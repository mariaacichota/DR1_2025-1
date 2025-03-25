package infnet.selenium.prestashop;

import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

// page_url = https://practicetestautomation.com/practice-test-login/
public class ScreenshotTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    //Capturas de tela podem ser usadas para comprovar que um teste passou ou falhou, e em casos de falha, onde o erro ocorreu.
    @Test
    public void testCaptureScreenshot() {
        driver.get("https://practicetestautomation.com/practice-test-login/");
        takeScreenshot("full_page.png");

         WebElement loginForm = driver.findElement(By.id("login"));
        takeElementScreenshot(loginForm, "login_form.png");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void takeScreenshot(String fileName) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("screenshots/" + fileName));
            System.out.println("Screenshot salva: screenshots/" + fileName);
        } catch (IOException e) {
            System.err.println("Erro ao salvar screenshot: " + e.getMessage());
        }
    }

    private void takeElementScreenshot(WebElement element, String fileName) {
        File screenshot = element.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("screenshots/" + fileName));
            System.out.println("Screenshot do elemento salva: screenshots/" + fileName);
        } catch (IOException e) {
            System.err.println("Erro ao salvar screenshot: " + e.getMessage());
        }
    }
}