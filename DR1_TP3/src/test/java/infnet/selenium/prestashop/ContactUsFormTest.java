package infnet.selenium.prestashop;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import static org.junit.jupiter.api.Assertions.assertTrue;

// page_url = https://demo.prestashop.com/#/en/front
public class ContactUsFormTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void testSubmitContactUsForm() {
        driver.get("https://demo.prestashop.com/#/en/front");

        //Por utilizar iframe, foi necessário mudar o contexto com o switchTo().frame.
        driver.switchTo().frame(driver.findElement(By.id("framelive")));
        WebElement contactUsLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Contact us")));
        contactUsLink.click();

        //Por ser um dropdown, não suporta o sendKeys(), logo, foi utilizado o Select.
        WebElement subjectDropdown = driver.findElement(By.id("id_contact"));
        Select select = new Select(subjectDropdown);
        select.selectByVisibleText("Customer service");

        //Campos de entrada de textos foram manipulados com sendKeys() para inserir dados.
        driver.findElement(By.id("email")).sendKeys("testuser@example.com");
        driver.findElement(By.id("message")).sendKeys("Gostaria de saber mais sobre os produtos.");

        //Utilizado o click() para acionar o botão.
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();

        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert.alert-success")));
        assertTrue(successMessage.getText().contains("Your message has been successfully sent"), "Mensagem de sucesso não foi exibida!");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}