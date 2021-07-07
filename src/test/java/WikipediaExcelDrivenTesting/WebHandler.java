package WikipediaExcelDrivenTesting;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class WebHandler {




    public static String wikiTest(String searchText) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\selenium chrome webdriver//chromedriver.exe");// path to web driver
        WebDriver webDriver1 = new ChromeDriver(); // opens browser

        webDriver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        webDriver1.get("https://en.wikipedia.org/wiki/Main_Page");  // serches url

        By search = By.id("searchInput");
        webDriver1.findElement(search).sendKeys(searchText);
        webDriver1.findElement(search).sendKeys(Keys.ENTER);

        String result;

        //try and catch as the search results can be exact or the first result is to be selected
        try{
            result = webDriver1.findElement(By.xpath("//div[@class='mw-parser-output']//p[2]")).getText();

        }catch (NoSuchElementException e){
            webDriver1.findElement(By.xpath("//a[@title='Special:Search' or @id='mw-search-DYM-suggestion']")).click();
            webDriver1.findElement(By.xpath("//a[@data-serp-pos='0']")).click();
            result = webDriver1.findElement(By.xpath("//div[@class='mw-parser-output']//p[2]")).getText();
        }

        webDriver1.close();
        return result;



    }
}
