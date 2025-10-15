package com.anhtester.Bai30_Screenshot_RecordVideo.testcases;

import com.anhtester.Bai30_Screenshot_RecordVideo.pages.CustomersPage;
import com.anhtester.Bai30_Screenshot_RecordVideo.pages.DashboardPage;
import com.anhtester.Bai30_Screenshot_RecordVideo.pages.LoginPage;
import com.anhtester.common.BaseTest;
import com.anhtester.helpers.PropertiesHelper;
import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LeadsTest extends BaseTest {

   private LoginPage loginPage;
   private DashboardPage dashboardPage;
   private CustomersPage customersPage;

   @Test
   public void testLeadsOverview() {
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
