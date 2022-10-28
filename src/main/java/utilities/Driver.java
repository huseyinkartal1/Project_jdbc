package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Driver {


     private static WebDriver driver;

     public static WebDriver getDriver(){

         if(driver==null){

            WebDriverManager.chromedriver().setup();
            driver=new ChromeDriver();

            //create edilmesine kadar bekler
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            //explicityWait visible/clickable olmasını bekler

         }

         return driver;
     }



}
