package com.cydeo.tests.alerts_iframes_windows;

import com.cydeo.utilities.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class T1_Alert_Practices {

    WebDriver driver;
    @BeforeMethod
    public void setUpMethod(){
        //1. Open browser
        WebDriverManager.chromedriver().setup();
        driver= WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //2. Go to website: http://practice.cydeo.com/javascript_alerts
        driver.get("http://practice.cydeo.com/javascript_alerts" );
        }
        @Test
    public void AlertPractice_test1(){
        //3. Click to “Click for JS Alert” button
        WebElement infoAlertButton= driver.findElement(By.xpath("//button[@onclick='jsAlert()']"));
        infoAlertButton.click();

        //4.This step allow us to switch drivers attention from html page to the alert
        Alert alert=driver.switchTo().alert();

        //4. Click to OK button from the alert
        alert.accept();
        //5. Verify “You successfully clicked an alert” text is displayed.
            WebElement alertText = driver.findElement(By.xpath("//p[@id='result']"));
// System.out.println(" \'You successfully clicked an alert\' text is displayed = " + alertText.isDisplayed());
            //failure message only be displayed if assertion fails
            Assert.assertTrue(alertText.isDisplayed(),"Result text is not displayed");

            //another way to verify alert text as expexted as it should be
            String expectedAlertText="You successfully clicked an alert";
            String actualALertText= alertText.getText();
            Assert.assertEquals(expectedAlertText,actualALertText,"expected and actual result does not match");
        }

}
/*
TC #1: Information alert practice
1. Open browser
2. Go to website: http://practice.cydeo.com/javascript_alerts
3. Click to “Click for JS Alert” button
4. Click to OK button from the alert
5. Verify “You successfully clicked an alert” text is displayed.
 */