package com.shopease.automation.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotListener implements ITestListener {
    // A static thread-local variable to hold the driver instance for the current test
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void setDriver(WebDriver webDriver) {
        driver.set(webDriver);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver currentDriver = driver.get();
        if (currentDriver != null) {
            try {
                TakesScreenshot ts = (TakesScreenshot) currentDriver;
                File source = ts.getScreenshotAs(OutputType.FILE);
                String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String dest = "automation/screenshots/" + result.getName() + "_" + timestamp + ".png";
                Files.createDirectories(Paths.get("automation/screenshots"));
                Files.copy(source.toPath(), Paths.get(dest));
                System.out.println("Screenshot saved to: " + dest);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
