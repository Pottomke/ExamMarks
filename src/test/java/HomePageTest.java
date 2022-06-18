import Pages.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class HomePageTest {

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
    @DisplayName("isNavigationSuccess")
    public void navigationTest(){
        HomePage hp = new HomePage(driver);
        hp.navigate();
        String act = driver.getCurrentUrl();
        String exp ="https://lennertamas.github.io/roxo/";
        Assertions.assertEquals(exp, act);
    }

    @Test
    @DisplayName("isTermsAndConditionsExists")
    public void isTermsAndConditionsTest(){
        HomePage hp = new HomePage(driver);
        hp.navigate();
        boolean isTermsAndConditionsThere = hp.isTermsAndConditionsThere();

        Assertions.assertTrue(isTermsAndConditionsThere);
    }

    @Test
    @DisplayName("acceptCookies")
    public void acceptTermAndConditionTest(){
        HomePage hp = new HomePage(driver);
        hp.navigate();
        hp.acceptCookies();
        Set<Cookie> cookies = driver.manage().getCookies();
        boolean isCookiesThere = hp.cookiesCheck(cookies);
        Assertions.assertTrue(isCookiesThere);
    }

    @Test
    @DisplayName("xCookies")
    public void xTermAndConditionTest(){
        HomePage hp = new HomePage(driver);
        hp.navigate();
        hp.xCookies();
        Set<Cookie> cookies = driver.manage().getCookies();
        boolean isCookiesThere = hp.cookiesCheck(cookies);
        Assertions.assertFalse(isCookiesThere);
    }

    @Test
    @DisplayName("isRegistrationSuccess")
    public void registrationTest(){
        HomePage hp = new HomePage(driver);
        hp.navigate();
        hp.xCookies();
        String message = hp.register("asd","asd","asd","asd");
        String exp ="User registered!";
        Assertions.assertEquals(exp,message);
    }

    @Test
    @DisplayName("isLoginFail")
    public void loginFTest(){
        HomePage hp = new HomePage(driver);
        hp.navigate();
        hp.xCookies();
        hp.loginDataInput("asd","asd");
        String act = driver.getCurrentUrl();
        String exp ="https://lennertamas.github.io/roxo/landing.html";

        Assertions.assertNotEquals(exp,act);
    }

    @Test
    @DisplayName("isLoginSuccess")
    public void loginSTest(){
        HomePage hp = new HomePage(driver);
        hp.navigate();
        hp.xCookies();
        hp.register("asd","asd","asd","asd");
        hp.login();
        hp.loginDataInput("asd","asd");
        String act = driver.getCurrentUrl();
        String exp ="https://lennertamas.github.io/roxo/landing.html";

        Assertions.assertEquals(exp,act);
    }

    @Test
    @DisplayName("isLogoutExists")
    public void isLogoutTest(){
        HomePage hp = new HomePage(driver);
        hp.navigate();
        hp.xCookies();
        hp.register("asd","asd","asd","asd");
        hp.login();
        hp.loginDataInput("asd","asd");
        boolean isLogoutThere = hp.isLogout();

        Assertions.assertTrue(isLogoutThere);
    }

    @Test
    @DisplayName("isLogoutSuccess")
    public void logoutTest(){
        HomePage hp = new HomePage(driver);
        hp.navigate();
        hp.xCookies();
        hp.register("asd","asd","asd","asd");
        hp.login();
        hp.loginDataInput("asd","asd");
        hp.logout();
        boolean isTermsAndConditionsAccaptable = hp.isTermsAndConditionsThere();

        Assertions.assertTrue(isTermsAndConditionsAccaptable);
    }

    @Test
    @DisplayName("repetedFromDataFile")
    public void registerFromDataFileTest(){
        HomePage hp = new HomePage(driver);
        hp.navigate();
        hp.acceptCookies();
        hp.registerFromDataFile("users.txt");
        Set<Cookie> cookies = driver.manage().getCookies();
        int acceptanceCookie = 1;
        Assertions.assertEquals(5,cookies.size()-acceptanceCookie);
    }

    @AfterEach
    public void Dispose(){
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
