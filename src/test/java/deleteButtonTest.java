import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.List;

//        - Откроет эту страницу;
//        - Нажмет 2 раза на кнопку "Add Element";
//        - Проверит, что было добавлено ровно две кнопки "Delete";
//        - Нажмет на каждую из кнопок "Delete";
//        - Убедится, что все кнопки "Delete" были удалены.


public class deleteButtonTest {
    private WebDriver driver;



    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void test() {
        WebDriverManager.chromedriver().setup();

        String baseURL = "http://the-internet.herokuapp.com/add_remove_elements/";
        driver = new ChromeDriver();
        // открываем страницу
        driver.get(baseURL);

        // находим кнопку добавления
        WebElement addButton = driver.findElement(By.cssSelector("div.example > button"));
        //нажимаем кнопку дважды
        addButton.click();
        addButton.click();
        //проверяем наличие двух кнопок delete
        List<WebElement> deleteButtons = driver.findElements(By.cssSelector("div#elements > button"));
        Assert.assertEquals(deleteButtons.size(), 2);
        //нажимаем delete
        driver.findElement(By.cssSelector("div#elements > button")).click();
        driver.findElement(By.cssSelector("div#elements > button")).click();
        //проверяем что кнопок нет
        Assert.assertTrue(driver.findElements(By.cssSelector("div#elements > button")).size() == 0);
    }
}
