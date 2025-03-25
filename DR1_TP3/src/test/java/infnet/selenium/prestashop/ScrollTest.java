package infnet.selenium.prestashop;

import org.openqa.selenium.WebDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertTrue;

// page_url = https://demo.prestashop.com/#/en/front
public class ScrollTest {
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
    public void testScrollDownAndUp() {
        driver.get("https://demo.prestashop.com/#/en/front");
        driver.switchTo().frame(driver.findElement(By.id("framelive")));

        WebElement footer = driver.findElement(By.cssSelector("footer"));
        //Chama o método para fazer o scroll até o elemento.
        scrollToElement(footer);

        //Verifica se o rodapé é visível com isDisplayed()
        assertTrue(footer.isDisplayed(), "O rodapé não está visível após a rolagem para baixo!");

        //Chama o método para fazer o scroll para o topo.
        scrollToTop();

        WebElement logo = driver.findElement(By.cssSelector("#_desktop_logo"));
        assertTrue(logo.isDisplayed(), "O topo da página não ficou visível após rolar para cima!");
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

    private void scrollToTop() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
    }
}