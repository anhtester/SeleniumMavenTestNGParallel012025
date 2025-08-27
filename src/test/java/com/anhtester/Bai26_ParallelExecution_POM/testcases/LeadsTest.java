package com.anhtester.Bai26_ParallelExecution_POM.testcases;

import com.anhtester.Bai26_ParallelExecution_POM.pages.CustomersPage;
import com.anhtester.Bai26_ParallelExecution_POM.pages.DashboardPage;
import com.anhtester.Bai26_ParallelExecution_POM.pages.LoginPage;
import com.anhtester.common.BaseTest;
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
      Assert.assertEquals(labelCutsomerValue, "4", "Customer leads count does not match expected value.");


   }
}
