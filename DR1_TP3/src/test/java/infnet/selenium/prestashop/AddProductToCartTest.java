package infnet.selenium.prestashop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;

// page_url = https://demo.prestashop.com/#/en/front
public class AddProductToCartTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testAddProductToCart() {
        driver.get("https://demo.prestashop.com/#/en/front");
        driver.switchTo().frame(driver.findElement(By.id("framelive")));

        WebElement firstProduct = driver.findElement(By.cssSelector(".products .product-title a"));
        String productName = firstProduct.getText();
        firstProduct.click();
        driver.findElement(By.cssSelector(".add-to-cart")).click();

        WebElement proceedButton = driver.findElement(By.cssSelector(".cart-content-btn .btn-primary"));
        assertTrue(proceedButton.isDisplayed(), "Produto não foi adicionado ao carrinho!");
        proceedButton.click();

        WebElement cartProduct = driver.findElement(By.cssSelector(".cart-item .product-line-info a"));
        assertTrue(cartProduct.getText().contains(productName), "O produto no carrinho não corresponde ao adicionado!");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}