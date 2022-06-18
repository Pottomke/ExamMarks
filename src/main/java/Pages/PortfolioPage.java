package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

public class PortfolioPage {
    WebDriver driver;

    private final String url = "https://lennertamas.github.io/roxo/portfolio/";
    //
    private final By pItem = By.xpath("//*[@class=\"site-project-item\"]");
    public final By nextLink = By.xpath("//*[@aria-label=\"Next\"]");
    private final By lastLink = By.xpath("//*[@aria-label=\"Last\"]");
    private final By backLink = By.xpath("//*[@aria-label=\"Previous\"]");
    private final By firstLink = By.xpath("//*[@aria-label=\"First\"]");

    public PortfolioPage(WebDriver drv) {
        this.driver = drv;
    }

    public void navigate() {
        driver.get(url);
    }

    public int numberOfDivs() {
        int result = 0;

        List<WebElement> div = driver.findElements(pItem);
        result = div.size();

        return result;
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

    public void clickLastLink() {
        if (!isButtonThere(lastLink)) {
            driver.findElement(lastLink).click();
        }
    }

    public void clickBackLink() {
        if (!isButtonThere(backLink)) {
            driver.findElement(backLink).click();
        }
    }

    public void clickFirstLink() {
        if (!isButtonThere(firstLink)) {
            driver.findElement(firstLink).click();
        }
    }

}
