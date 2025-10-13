package com.anhtester.helpers;

import com.anhtester.drivers.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;

public class CaptureHelper {
   public static void takeScreenshot(String screenshotName) {
      TakesScreenshot ts = (TakesScreenshot) DriverManager.getDriver();
      File source = ts.getScreenshotAs(OutputType.FILE);

      File theDir = new File(PropertiesHelper.getValue("SCREENSHOT_PATH"));
      if (!theDir.exists()) {
         theDir.mkdirs();
      }

      try {
         FileHandler.copy(source, new File(PropertiesHelper.getValue("SCREENSHOT_PATH") + screenshotName + ".png"));
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }
}
