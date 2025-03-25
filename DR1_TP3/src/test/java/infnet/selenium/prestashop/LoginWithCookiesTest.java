package infnet.selenium.prestashop;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

// page_url = https://demo.prestashop.com/#/en/front
public class LoginWithCookiesTest {
    private WebDriver driver;
    private Set<Cookie> savedCookies;

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

        String expectedUrl = "https://practicetestautomation.com/logged-in-successfully/";
        assertEquals(expectedUrl, driver.getCurrentUrl(), "URL incorreta após login!");

        //Variável para salvar os cookies da sessão atual.
        savedCookies = driver.manage().getCookies();
    }

    @Test
    public void testReusingCookiesToStayLogged() {
        driver.get("https://practicetestautomation.com/practice-test-login/");

        //Adicionando cookies salvos para evitar login manual
        if (savedCookies != null) {
            for (Cookie cookie : savedCookies) {
                driver.manage().addCookie(cookie);
            }
            driver.navigate().refresh();
        }

        //Verificar se a URL indica que o usuário já está logado
        String expectedUrl = "https://practicetestautomation.com/logged-in-successfully/";
        assertEquals(expectedUrl, driver.getCurrentUrl(), "Usuário não manteve a sessão ativa!");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
