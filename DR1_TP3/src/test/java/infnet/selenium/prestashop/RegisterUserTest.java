package infnet.selenium.prestashop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertTrue;

// page_url = https://demo.prestashop.com/#/en/front
public class RegisterUserTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testRegisterUser() {
        driver.get("https://demo.prestashop.com/#/en/front");
        driver.switchTo().frame(driver.findElement(By.id("framelive")));
        driver.findElement(By.cssSelector(".user-info a")).click();
        driver.findElement(By.cssSelector(".no-account a")).click();

        driver.findElement(By.name("id_gender")).click();
        driver.findElement(By.name("firstname")).sendKeys("John");
        driver.findElement(By.name("lastname")).sendKeys("Doe");

        Random random = new Random();
        String email = "testuser" + random.nextInt(1000) + "@test.com";
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys("SecurePassword123");

        driver.findElement(By.name("customer_privacy")).click();
        driver.findElement(By.name("psgdpr")).click();

        driver.findElement(By.cssSelector("button.btn.btn-primary.form-control-submit")).click();

        WebElement accountInfo = driver.findElement(By.cssSelector(".account"));
        assertTrue(accountInfo.isDisplayed(), "Usuário não foi registrado corretamente!");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}