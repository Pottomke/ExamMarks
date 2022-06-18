package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class HomePage {
    WebDriver driver;

    private final String url = "https://lennertamas.github.io/roxo/";
    //TERMS & CONDITIONS
    private final By acceptB = By.xpath("//*[@id=\"terms-and-conditions-button\"]");
    private final By xB = By.xpath("//*[@onclick=\"closeModal()\"]");
    //REGISTER
    private final By register = By.xpath("//*[@onclick=\"showRegister()\"]");
    private final By rusern = By.xpath("//*[@id=\"register-username\"]");
    private final By rpw = By.xpath("//*[@id=\"register-password\"]");
    private final By eMail = By.xpath("//*[@id=\"register-email\"]");
    private final By desc = By.xpath("//*[@id=\"register-description\"]");
    private final By registerB = By.xpath("//*[@onclick=\"registerUser()\"]");
    private final By message = By.xpath("//*[@id=\"register-alert\"]");
    //LOGIN
    private final By login = By.xpath("//*[@id=\"register\"]/button[2]");
    private final By lusern = By.xpath("//*[@id=\"email\"]");
    private final By lpw = By.xpath("//*[@id=\"password\"]");
    private final By loginB = By.xpath("//*[@onclick=\"myFunction()\"]");
    //LOGOUT
    private final By logout = By.xpath("//*[@id=\"logout-link\"]");


    public HomePage(WebDriver drv) {
        this.driver = drv;
    }

    public void navigate(){
        driver.get(url);
    }

    public boolean isTermsAndConditionsThere(){
        return driver.findElement(acceptB)!=null;
    }

    public void acceptCookies(){
        driver.findElement(acceptB).click();
    }

    public void xCookies(){
        driver.findElement(xB).click();
    }

    public boolean cookiesCheck(Set<Cookie> cookies){
        if(!cookies.isEmpty()){
            return true;
        }
        return false;
    }

    public String register(String userName, String pwd, String email, String description){
        driver.findElement(register).click();
        driver.findElement(rusern).sendKeys(userName);
        driver.findElement(rpw).sendKeys(pwd);
        driver.findElement(eMail).sendKeys(email);
        driver.findElement(desc).sendKeys(description);
        driver.findElement(registerB).click();
        String result = driver.findElement(message).getText();

        return result;
    }

    public void registerFromDataFile(String filename) {
        Utils u = new Utils(driver);
        BufferedReader reader;
        try {
            ArrayList<By> inputFieldList = new ArrayList<>();
            inputFieldList.add(rusern);
            inputFieldList.add(rpw);
            inputFieldList.add(eMail);
            inputFieldList.add(desc);

            reader = new BufferedReader(new FileReader(filename));
            String line = "";
            ArrayList<String> lineList = new ArrayList<>();
            while (line != null) {
                line = reader.readLine();
                lineList.add(line);
            }

            lineList.remove(lineList.size()-1);

            int listLength = lineList.size();
            int i = 0;
            while (i < listLength) {
                driver.findElement(register).click();
                for (int k = 0; k < 4; k++) {
                    driver.findElement(inputFieldList.get(k)).sendKeys(lineList.get(i));
                    i++;
                }
                driver.findElement(registerB).click();
                u.refresh();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        u.refresh();
    }

    public void login(){
        driver.findElement(login).click();
    }

    public void loginDataInput(String userName, String pwd){
        driver.findElement(lusern).sendKeys(userName);
        driver.findElement(lpw).sendKeys(pwd);
        driver.findElement(loginB).click();
    }

    public boolean isLogout(){
        return driver.findElement(logout)!=null;
    }

    public void logout(){
        driver.findElement(logout).click();
    }

}
