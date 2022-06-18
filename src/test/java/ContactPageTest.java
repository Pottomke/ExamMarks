import Pages.ContactPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class ContactPageTest {
    WebDriver driver;

    @BeforeEach
    public void Setup(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-extensions");
        options.addArguments("--incognito");
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @Test
    @DisplayName("sendSuccess")
    public void sendContactTest(){
        ContactPage cp= new ContactPage(driver);
        cp.navigate();
        cp.fillContactPage("asd","asd","asd@asd.hu",1,"asd");
        cp.send();
        cp.okAlert();
        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Boolean act = cp.isButtonThere();
        Assertions.assertFalse(act);
    }

    @Test
    @DisplayName("sendOops")
    public void sendOopsContactTest(){
        ContactPage cp= new ContactPage(driver);
        cp.navigate();
        cp.send();
        cp.okAlert();
        // driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Boolean act = cp.isButtonThere();
        Assertions.assertTrue(act);
    }

    @AfterEach
    public void Dispose(){
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
