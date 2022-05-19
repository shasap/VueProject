package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class VueTestSuit {
    protected static WebDriver driver;
    // Open chrome browser
    public static void openBrowser(int time)
    {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
        driver.manage().window().maximize();
    }

    public static void driverWaitsUntilContainsUrl(int time, String url)
    {
        WebDriverWait wait1 = new WebDriverWait(driver,Duration.ofSeconds(time));
        wait1.until(ExpectedConditions.urlContains(url));
    }

    // Click on Join, Email etc
    public static void clickOnElement(By by)
    {
        driver.findElement(by).click();
    }

    // Enter text into text field
    public static void enterText(By by, String text)
    {
        driver.findElement(by).sendKeys(text);
    }

    public static void driverWaitForElementToBeClickable(By by,int time)
    {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    public static String randomDate()
        {
            //Instant instant = Instant.now();
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmmss");
            String format = formatter.format(date);
            return formatter.format(date);
        }



    public static void main(String args[])
    {
        System.setProperty("webdriver.chrome.driver","src\\test\\java\\drivers\\chromedriver.exe");

        // Open Browser
        openBrowser(10);

        // Type URL
        driver.get("https://www.myvue.com/cinema/harrow?sc_cmp=ppc_google__SS%20|%20Brand%20|%20Harrow%20|%20Exact_Harrow%20|%20Vue%20|%20Exact&gclid=Cj0KCQjwg_iTBhDrARIsAD3Ib5iiN9lxf62YY6RWU2fefP3vUNiUvmRXnJFGzyDakltbZV7cPZrS9ysaAsVjEALw_wcB%22");

        // Click on Accept/Reject cookies

         driverWaitForElementToBeClickable(By.xpath("//button[@id=\"onetrust-reject-all-handler\"]"),30);

        // Click on Join and then with Email
        clickOnElement(By.cssSelector("[class=\"js-show-register-overlay\"]"));
        clickOnElement(By.cssSelector("[id=\"register-step-2\"]"));

        //Enter first name
        enterText(By.name("fname"),"Harry");

        // Enter last name
        enterText(By.cssSelector("[type=\"sname\"]"),"Potter");

        // Select location of Vue
        WebElement localVue = driver.findElement(By.name("vue"));
        Select select = new Select(localVue);
        select.selectByVisibleText("Harrow");


        // Enter Email & confirm Email
        enterText(By.name("email"),"Potter"+randomDate()+"@gmail.com");
        enterText(By.cssSelector("[name=\"cemail\"]"),"Potter"+randomDate()+"@gmail.com");

        // Enter password & confirm password
        enterText(By.name("password"),"Test1234");

        // Enter DOB
        enterText(By.className("form__date__day"),"01");
        enterText(By.id("form__date__month"),"01");
        enterText(By.name("year"),"1999");

        clickOnElement(By.className("icon-tick-black-before"));

        // Click Join
       // clickOnElement(By.id("id=\"register-errors\""));

    }
}
