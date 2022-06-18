import Pages.PortfolioPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class PortfolioPageTest {

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
    @DisplayName("isNextClickable")
    public void nextTest(){
        PortfolioPage pp = new PortfolioPage(driver);
        pp.navigate();
        pp.clickNextLink();
        String exp = "https://lennertamas.github.io/roxo/portfolio/page/2/";
        String act = driver.getCurrentUrl();
        Assertions.assertEquals(exp,act);
    }

    @Test
    @DisplayName("isLastClickable")
    public void lastTest(){
        PortfolioPage pp = new PortfolioPage(driver);
        pp.navigate();
        pp.clickLastLink();
        String exp = "https://lennertamas.github.io/roxo/portfolio/page/2/";
        String act = driver.getCurrentUrl();
        Assertions.assertEquals(exp,act);
    }

    @Test
    @DisplayName("isBackClickable")
    public void backTest(){
        PortfolioPage pp = new PortfolioPage(driver);
        pp.navigate();
        pp.clickNextLink();
        pp.clickBackLink();
        String exp = "https://lennertamas.github.io/roxo/portfolio/";
        String act = driver.getCurrentUrl();
        Assertions.assertEquals(exp,act);
    }

    @Test
    @DisplayName("isFirstClickable")
    public void firstTest(){
        PortfolioPage pp = new PortfolioPage(driver);
        pp.navigate();
        pp.clickNextLink();
        pp.clickFirstLink();
        String exp = "https://lennertamas.github.io/roxo/portfolio/";
        String act = driver.getCurrentUrl();
        Assertions.assertEquals(exp,act);
    }

    @Test
    @DisplayName("Pagination")
    public void paginationAndCountTest() {
        PortfolioPage pp = new PortfolioPage(driver);
        pp.navigate();

        int actual = 0;

        while(true){
            if(!pp.isButtonThere(pp.nextLink)){
                actual += pp.numberOfDivs();
                pp.clickNextLink();
            }
            actual += pp.numberOfDivs();
            break;
        }

        Assertions.assertEquals(5, actual);

    }


    @AfterEach
    public void Dispose(){
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
