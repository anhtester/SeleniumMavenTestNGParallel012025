package com.anhtester.common;

import com.anhtester.drivers.DriverManager;
import com.anhtester.helpers.PropertiesHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class BaseTest {
   public SoftAssert softAssert;

   @BeforeSuite
   public void setupBeforeSuite() {
      PropertiesHelper.loadAllFiles();
   }

   @BeforeMethod
   @Parameters({"browser"})
   public void createDriver(@Optional("chrome") String browserName) {
      WebDriver driver;

      if (PropertiesHelper.getValue("browser").isEmpty() || PropertiesHelper.getValue("browser") == null) {
         browserName = browserName;
      } else {
         browserName = PropertiesHelper.getValue("browser");
      }

      switch (browserName.trim().toLowerCase()) {
         case "chrome":
            System.out.println("Launching Chrome browser...");

            ChromeOptions options = new ChromeOptions();

            if (PropertiesHelper.getValue("headless").equalsIgnoreCase("true")) {
               options.addArguments("--headless=new"); // chạy headless
               options.addArguments("--window-size=" + PropertiesHelper.getValue("window_size")); // set kích thước
            }

            driver = new ChromeDriver(options);

            break;
         case "firefox":
            System.out.println("Launching Firefox browser...");
            driver = new FirefoxDriver();
            break;
         case "edge":
            System.out.println("Launching Edge browser...");
            WebDriverManager.edgedriver().setup(); //Tải msedgedriver.exe tương ứng version của trình duyệt đang dùng
            driver = new EdgeDriver();
            break;
         default:
            System.out.println("Browser: " + browserName + " is invalid, Launching Chrome as browser of choice...");
            driver = new ChromeDriver();
      }

      DriverManager.setDriver(driver);

      if (PropertiesHelper.getValue("headless").equalsIgnoreCase("false")) {
         DriverManager.getDriver().manage().window().maximize();
      }

      softAssert = new SoftAssert();
   }

   @AfterMethod(alwaysRun = true)
   public void closeDriver() {
      if (DriverManager.getDriver() != null) {
         DriverManager.quit();
         softAssert.assertAll();
      }
   }

}
