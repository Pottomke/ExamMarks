package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class BlogPage {
    WebDriver driver;

    private final String url = "https://lennertamas.github.io/roxo/blog/";
    //TERMS & CONDITIONS
    private final By acceptB = By.xpath("//*[@id=\"terms-and-conditions-button\"]");
    private final By xB = By.xpath("//*[@onclick=\"closeModal()\"]");

    private final By blogPosts = By.xpath("//*[@class=\"col-lg-6\"]");
    private final By headline = By.xpath(".//h3//a");

    private final By readMore = By.xpath("//*[@class=\"read-more\"]");
    private final By heading = By.xpath("//h2");

    public final By nextLink = By.xpath("//*[@aria-label = \"Next\"]");


    public BlogPage(WebDriver drv) {
        this.driver = drv;
    }

    public void navigate(){
        driver.get(url);
    }

    public String getFirstBlogPostHeading(){
        driver.findElement(readMore).click();
        return driver.findElement(heading).getText();
    }

    public boolean isButtonThere(By buttonPath) {
        String atr = driver.findElement(buttonPath).getAttribute("class");
        return atr.equals("page-item disabled");
    }

    public void clickNextLink() {
        if (!isButtonThere(nextLink)) {
            driver.findElement(nextLink).click();
        }
    }

    public void saveHeadings(String filename){
        Utils u =new Utils(driver);
        u.fileName = filename;
        List<WebElement> list = driver.findElements(blogPosts);
        for (int i = 0; i < list.size(); i++) {
            String text = list.get(i).findElement(headline).getText();
            u.write(text);
        }
    }

}
