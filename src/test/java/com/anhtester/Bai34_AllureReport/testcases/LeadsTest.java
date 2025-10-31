package com.anhtester.Bai34_AllureReport.testcases;

import com.anhtester.Bai34_AllureReport.pages.CustomersPage;
import com.anhtester.Bai34_AllureReport.pages.DashboardPage;
import com.anhtester.Bai34_AllureReport.pages.LoginPage;
import com.anhtester.common.BaseTest;
import com.anhtester.helpers.PropertiesHelper;
import com.anhtester.keywords.WebUI;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("CRM version 2.0.0")
@Feature("Leads feature")
@Story("Verify leads overview")
public class LeadsTest extends BaseTest {

   private LoginPage loginPage;
   private DashboardPage dashboardPage;
   private CustomersPage customersPage;

   @Description("Verify leads overview page displays correct counts")
   @Link(name = "https://jira.anhtester.com/login/CRM-05", url = "https://jira.anhtester.com/login/CRM-05")
   @Test(priority = 1, description = "TC_LEADS_CRM_01")
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
