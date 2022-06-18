package Pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class ContactPage {
    WebDriver driver;

    private final String url = "https://lennertamas.github.io/roxo/contact/";

    private final By fName = By.xpath("//*[@id=\"first-name\"]");
    private final By lName = By.xpath("//*[@id=\"last-name\"]");
    private final By eMail = By.xpath("//*[@id=\"email\"]");
    private final By pType = By.xpath("//*[@id=\"projectType\"]");
    private final By type1 = By.xpath("//*[@id=\"projectType\"]/option[2]");
    private final By type2 = By.xpath("//*[@id=\"projectType\"]/option[3]");
    private final By aboutP = By.xpath("//*[@id=\"aboutProject\"]");
    private final By sendB = By.xpath("//*[@id=\"contact-form-button\"]");


    public ContactPage(WebDriver drv) {
        this.driver = drv;
    }

    public void navigate(){
        driver.get(url);
    }

    public void fillContactPage(String fname,String lname, String email, int type, String about){
        driver.findElement(fName).sendKeys(fname);
        driver.findElement(lName).sendKeys(lname);
        driver.findElement(eMail).sendKeys(email);
        driver.findElement(pType).click();
        if(type == 1){
            driver.findElement(type1).click();
        }
        else{
            driver.findElement(type2).click();
        }
        driver.findElement(aboutP).sendKeys(about);
    }

    public void send(){
        driver.findElement(sendB).click();
    }
    public void okAlert(){
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public Boolean isButtonThere(){

        WebElement button = driver.findElement(sendB);
        Boolean result = true;

        try {
            String value = button.getAttribute("style");
            if (!value.equals(null)){
                result = false;
            }
        } catch (Exception e) {
            result= false;
        }

        return result;
    }

}
