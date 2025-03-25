package infnet.selenium.prestashop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testLoginWithValidCredentials() {
        driver.get("https://practicetestautomation.com/practice-test-login/");

        driver.findElement(By.id("username")).sendKeys("student");
        driver.findElement(By.id("password")).sendKeys("Password123");
        driver.findElement(By.id("submit")).click();

        //O Selenium pode verificar a URL atual para garantir que a autenticação foi bem-sucedida:
        //driver.getCurrentUrl() traz a url atual, e o assertEquals garante a comparação entre esta e a url esperada (expectedUrl).
        String expectedUrl = "https://practicetestautomation.com/logged-in-successfully/";
        assertEquals(expectedUrl, driver.getCurrentUrl(), "A URL da página de sucesso não corresponde!");

        WebElement successMessage = driver.findElement(By.tagName("h1"));
        assertTrue(successMessage.getText().contains("Logged In Successfully"), "A mensagem de login bem-sucedido não apareceu!");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
