import Pages.BlogPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

public class BlogPageTest {
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
    @DisplayName("getHeading")
    public void headingTest(){
        BlogPage bp = new BlogPage(driver);
        bp.navigate();
        String heading = bp.getFirstBlogPostHeading();
        String exp = "Design Inspiration: The Best Projects From December";
        Assertions.assertEquals(exp,heading);
    }

    @Test
    @DisplayName("getHeadingsToFile")
    public void headingsToFileTest(){
        BlogPage bp = new BlogPage(driver);
        bp.navigate();
        bp.saveHeadings("HeadingsFromPage.txt");
        //String heading = bp.getFirstBlogPostHeading();
        //String exp = "Design Inspiration: The Best Projects From December";
        //Assertions.assertEquals(exp,heading);
    }

    @Test
    @DisplayName("getHeadingsToFileWithPagination")
    public void headingsToFileWithPaginationTest() throws IOException {
        BlogPage bp = new BlogPage(driver);
        bp.navigate();

        while(true){
            if(!bp.isButtonThere(bp.nextLink)){
                bp.saveHeadings("AllHeadingsFromPage.txt");
                bp.clickNextLink();
            }
            bp.saveHeadings("AllHeadingsFromPage.txt");
            break;
        }

        Path filePath = Path.of("AllHeadingsFromPage.txt");

        String content = Files.readString(filePath);

        String exp = """
                Design Inspiration: The Best Projects From December
                The 10 Biggest Rebrands and Logo Designs of 2019
                Design Inspiration: The Best Projects From November
                Pt Chooses Classic Blue for Its Colour of the Year 2020
                The 10 Biggest Product Stories of 2019
                """;


        Assertions.assertEquals(exp, content);

    }

    @AfterEach
    public void Dispose(){
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
