package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage {
    WebDriver driver;


    private final By name = By.xpath("//*[@id=\"name\"]");
    private final By bio = By.xpath("//*[@id=\"bio\"]");
    private final By phone = By.xpath("//*[@id=\"phone-number\"]");
    private final By saveB = By.xpath("//*[@onclick=\"editUser()\"]");
    private final By deleteB = By.xpath("//*[@onclick=\"showRealDeleteAccBtn()\"]");
    private final By sureB = By.xpath("//*[@onclick=\"deleteAccount()\"]");

    private final By saveMessage = By.xpath("//*[@id=\"edit-alert\"]");


    public ProfilePage(WebDriver drv) {
        this.driver = drv;
    }


    public void fillData(String pName, String pBio, String pPhone){
        driver.findElement(name).sendKeys(pName);
        driver.findElement(bio).sendKeys(pBio);
        driver.findElement(phone).sendKeys(pPhone);
    }

    public void save(){
        driver.findElement(saveB).click();
    }

    public void delete(){
        driver.findElement(deleteB).click();
        driver.findElement(sureB).click();
    }

    public String getMessage(){
        return driver.findElement(saveMessage).getText();
    }
}
