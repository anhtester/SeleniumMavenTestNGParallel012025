package com.anhtester.common;

import com.anhtester.drivers.DriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

public class BaseTest {
   public SoftAssert softAssert;

   @BeforeMethod
   @Parameters({"browser"})
   public void createDriver(@Optional("chrome") String browserName) {
      WebDriver driver;
      switch (browserName.trim().toLowerCase()) {
         case "chrome":
            System.out.println("Launching Chrome browser...");
            driver = new ChromeDriver();
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

      DriverManager.getDriver().manage().window().maximize();
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
