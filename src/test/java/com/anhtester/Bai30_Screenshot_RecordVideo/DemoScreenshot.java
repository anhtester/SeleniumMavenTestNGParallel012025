package com.anhtester.Bai30_Screenshot_RecordVideo;

import com.anhtester.common.BaseTest;
import com.anhtester.drivers.DriverManager;
import com.anhtester.helpers.CaptureHelper;
import com.anhtester.helpers.SystemHelper;
import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

public class DemoScreenshot extends BaseTest {
   @Test
   public void testTakeScreenshot(Method method) {
      WebUI.openURL("https://anhtester.com");
      Assert.assertEquals(DriverManager.getDriver().getTitle(), "123Anh Tester Automation Testing");
      CaptureHelper.takeScreenshot("IMG_HomePage");
      WebUI.clickElement(By.xpath("//a[@id='btn-login']"));
      WebUI.waitForPageLoaded();
      CaptureHelper.takeScreenshot(method.getName() + "_" + SystemHelper.getDateTimeNowFormat());
   }

}
