package com.anhtester.common;

import com.anhtester.drivers.DriverManager;
import com.anhtester.helpers.PropertiesHelper;
import com.anhtester.listeners.TestListener;
import com.anhtester.utils.LogUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

@Listeners(TestListener.class)
public class BaseTest {
   public SoftAssert softAssert;

   @BeforeSuite
   public void setupBeforeSuite() {
      PropertiesHelper.loadAllFiles();
   }

   @BeforeMethod
   @Parameters({"BROWSER"})
   public void createDriver(@Optional("chrome") String browserName) {
      WebDriver driver;

      if (PropertiesHelper.getValue("BROWSER").isEmpty() || PropertiesHelper.getValue("BROWSER") == null) {
         browserName = browserName;
      } else {
         browserName = PropertiesHelper.getValue("BROWSER");
      }

      switch (browserName.trim().toLowerCase()) {
         case "chrome":
            LogUtils.info("Launching Chrome browser...");

            ChromeOptions options = new ChromeOptions();

            if (PropertiesHelper.getValue("HEADLESS").equalsIgnoreCase("true")) {
               options.addArguments("--headless=new"); // chạy headless
               options.addArguments("--window-size=" + PropertiesHelper.getValue("WINDOW_SIZE")); // set kích thước
            }

            driver = new ChromeDriver(options);

            break;
         case "firefox":
            LogUtils.info("Launching Firefox browser...");
            driver = new FirefoxDriver();
            break;
         case "edge":
            LogUtils.info("Launching Edge browser...");
            driver = new EdgeDriver();
            break;
         default:
            LogUtils.info("Browser: " + browserName + " is invalid, Launching Chrome as browser of choice...");
            driver = new ChromeDriver();
      }

      DriverManager.setDriver(driver);

      if (PropertiesHelper.getValue("HEADLESS").equalsIgnoreCase("false")) {
         DriverManager.getDriver().manage().window().maximize();
      }

      softAssert = new SoftAssert();
   }

   @AfterMethod(alwaysRun = true)
   public void closeDriver(ITestResult result) {
      if (DriverManager.getDriver() != null) {
         DriverManager.quit();
         softAssert.assertAll();
      }
   }

}
