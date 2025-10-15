package com.anhtester.Bai30_Screenshot_RecordVideo;

import com.anhtester.Bai30_Screenshot_RecordVideo.pages.CustomersPage;
import com.anhtester.Bai30_Screenshot_RecordVideo.pages.DashboardPage;
import com.anhtester.Bai30_Screenshot_RecordVideo.pages.LoginPage;
import com.anhtester.common.BaseTest;
import com.anhtester.drivers.DriverManager;
import com.anhtester.helpers.CaptureHelper;
import com.anhtester.helpers.PropertiesHelper;
import com.anhtester.helpers.SystemHelper;
import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class DemoVideoRecord extends BaseTest {
//   @BeforeClass
//   public void recordVideo() {
//      CaptureHelper.startRecord("DemoVideoRecord");
//   }
//
//   @AfterClass
//   public void stopRecordVideo() {
//      CaptureHelper.stopRecord();
//   }

   @Test
   public void testVideoRecord(Method method) {
      CaptureHelper.startRecord(method.getName());

      LoginPage loginPage = new LoginPage();

      loginPage.loginCRM("admin@example.com", "123456");
      loginPage.verifyLoginSuccess();
   }

   @Test
   public void testLeadsOverview(Method method) {
      CaptureHelper.startRecord(method.getName());

      LoginPage loginPage;
      DashboardPage dashboardPage;
      CustomersPage customersPage;

      loginPage = new LoginPage();
      loginPage.loginCRM();
      WebUI.clickElement(By.xpath("//span[@class='menu-text'][normalize-space()='Leads']"));
      WebUI.waitForPageLoaded();
      WebUI.clickElement(By.xpath("//i[@class='fa fa-bar-chart']"));
      //WebUI.waitForPageLoaded();
      //WebUI.checkElementExist(By.xpath("//span[normalize-space()='Active']/preceding-sibling::span"), 5, 1000);
      String labelActiveValue = WebUI.getElementText(By.xpath("//span[normalize-space()='Active']/preceding-sibling::span"));
      Assert.assertEquals(labelActiveValue, "7", "Active leads count does not match expected value.");
      String labelCutsomerValue = WebUI.getElementText(By.xpath("//span[normalize-space()='Customer']/preceding-sibling::span"));
      Assert.assertEquals(labelCutsomerValue, "2", "Customer leads count does not match expected value.");

      //Set data to properties file
      PropertiesHelper.setValue("leads_active", labelActiveValue);
      PropertiesHelper.setValue("leads_customer", labelCutsomerValue);

   }

}
