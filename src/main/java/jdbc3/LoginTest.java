package jdbc3;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.ConnectionManager;
import utilities.Driver;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Arrays;

public class LoginTest {
    WebDriver driver;
    {
        driver = Driver.getDriver();
    }

    @Test(priority = 1)
    public void gotoSite() {
        driver.get("https://www.saucedemo.com/");
    }

    @Test(dataProvider = "getData", priority = 2)
    public void loginTest(Object[] arr) throws InterruptedException {

        System.out.println(Arrays.toString(arr));

        WebElement username = driver.findElement(By.xpath("//*[@id=\"user-name\"]"));
        username.clear();
        username.sendKeys(arr[0].toString());

        WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        password.clear();
        password.sendKeys(arr[1].toString());

        int num= Integer.parseInt(arr[2].toString());
        if(num!=0){
            WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"login-button\"]"));
            loginButton.click();
            WebElement menu = driver.findElement(By.xpath("//*[@id=\"react-burger-menu-btn\"]"));
            menu.click();
            WebElement logout = driver.findElement(By.xpath("//*[@id=\"logout_sidebar_link\"]"));
            logout.click();

        }

    }

    @Test(priority = 3)
    public void closeDriver() {
        driver.close();
        driver.quit();
    }

    @DataProvider
    public Object[][] getData() {

        try {
            ConnectionManager getConnection = new ConnectionManager("jdbc:sqlite:src/data.sqlite");
            ResultSet row1 = getConnection.getResultSet("SELECT COUNT(*) FROM kartal");
            int row = row1.getInt(1);
            ResultSet rs = getConnection.getResultSet("SELECT adi, soyadi, yas FROM kartal");
            int col = rs.getMetaData().getColumnCount();

            Object[][] arr = new Object[row][col];

            int rowNum = 0;

            while (rs.next()) {
                Object[] rows = new Object[col];

                for (int i = 1; i <= col; i++) {
                    rows[i - 1] = rs.getString(i);

                }
                arr[rowNum++] = rows;

            }
            getConnection.close();
            return arr;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
