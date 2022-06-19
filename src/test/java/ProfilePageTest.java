import Pages.HomePage;
import Pages.ProfilePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ProfilePageTest {
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
    @DisplayName("profileFill")
    public void profileFillTest(){
        ProfilePage prop = new ProfilePage(driver);
        HomePage hp = new HomePage(driver);

        hp.navigate();
        hp.acceptCookies();
        hp.register("Farkas","pwd","","");
        hp.login();
        hp.loginDataInput("Farkas","pwd");
        hp.goToProfile();
        prop.fillData("Zoli","cica","");
        prop.save();
        String message = prop.getMessage();
        String exp = "Profile Edited!";
        Assertions.assertEquals(exp,message);
    }

    @Test
    @DisplayName("profileChange")
    public void profileChangeTest(){
        ProfilePage prop = new ProfilePage(driver);
        HomePage hp = new HomePage(driver);

        hp.navigate();
        hp.acceptCookies();
        hp.register("Farkas","pwd","","");
        hp.login();
        hp.loginDataInput("Farkas","pwd");
        Set<Cookie> cookiesBefore = driver.manage().getCookies();
        hp.goToProfile();
        prop.fillData("Zoli","cica","");
        prop.save();
        Set<Cookie> cookiesAfter = driver.manage().getCookies();
        boolean isTheSame = cookiesBefore == cookiesAfter;
        Assertions.assertFalse(isTheSame);
    }

    @Test
    @DisplayName("profileDelete")
    public void profileDeleteTest(){
        ProfilePage prop = new ProfilePage(driver);
        HomePage hp = new HomePage(driver);

        hp.navigate();
        hp.acceptCookies();
        hp.register("Farkas","pwd","","");
        hp.login();
        hp.loginDataInput("Farkas","pwd");
        Set<Cookie> cookiesBefore = driver.manage().getCookies();

        hp.goToProfile();

        prop.delete();
        Set<Cookie> cookiesAfter = driver.manage().getCookies();

        boolean isTheSame = cookiesBefore == cookiesAfter;

        Assertions.assertFalse(isTheSame);

    }


    @AfterEach
    public void Dispose(){
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
