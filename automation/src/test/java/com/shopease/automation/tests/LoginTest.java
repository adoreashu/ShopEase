package com.shopease.automation.tests;

import com.shopease.automation.utils.ScreenshotListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.time.Duration;

@Listeners(ScreenshotListener.class)
public class LoginTest {
    WebDriver driver;
    
    @BeforeClass
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        ScreenshotListener.setDriver(driver);
    }
    
    @Test
    public void testLogin() {
        driver.get("http://localhost:3000/login.html");
        driver.findElement(By.id("email")).sendKeys("john.doe@example.com");
        driver.findElement(By.id("password")).sendKeys("Password@123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        
        try { Thread.sleep(2000); } catch (Exception e) {}
        Assert.assertTrue(driver.getCurrentUrl().contains("index.html"), "Should redirect to home page");
    }
    
    @AfterClass
    public void teardown() { if(driver != null) driver.quit(); }
}
