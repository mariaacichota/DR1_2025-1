package infnet.selenium.prestashop;

import org.openqa.selenium.WebDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

public class CartTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testAddProductToCartAndVerifyQuantity() {
        driver.get("https://demo.prestashop.com/#/en/front");
        driver.switchTo().frame(driver.findElement(By.id("framelive")));

        WebElement product = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".thumbnail-container")));
        scrollToElement(product);
        product.click();

        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".add-to-cart button")));
        addToCartButton.click();

        //O pop-up de confirmação garante que o produto foi adicionado, usamos ExpectedConditions.visibilityOfElementLocated() para pegar seu valor.
        WebElement confirmationPopup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart-content")));
        assertTrue(confirmationPopup.getText().contains("Product successfully added"), "O produto não foi adicionado!");

        WebElement proceedToCheckoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".cart-content-btn .btn-primary")));
        proceedToCheckoutButton.click();

        WebElement quantityElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart-items .product-quantity")));
        assertTrue(quantityElement.getText().contains("1"), "A quantidade no carrinho não está correta!");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}