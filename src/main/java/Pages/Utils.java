package Pages;

import org.openqa.selenium.WebDriver;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Utils {

    WebDriver driver;

    public Utils(WebDriver drv) {
        this.driver=drv;
    }

    public void refresh(){
        driver.navigate().refresh();
    }

    public String fileName =null;

    public void write(String text) {
        Path path = Paths.get(fileName);
        String message = text + "\n";

        try {

            Files.write(path, message.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);

        } catch (Exception e) {
            System.out.println("An error occurred.");
        }
    }

    public void makeEmpty(String fileName) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(fileName);
        writer.print("");
        writer.close();
    }

}
