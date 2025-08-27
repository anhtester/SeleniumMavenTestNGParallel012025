package com.anhtester.Bai26_ParallelExecution_POM.testcases;

import com.anhtester.Bai26_ParallelExecution_POM.pages.CustomersPage;
import com.anhtester.Bai26_ParallelExecution_POM.pages.DashboardPage;
import com.anhtester.Bai26_ParallelExecution_POM.pages.LoginPage;
import com.anhtester.common.BaseTest;
import org.testng.annotations.Test;

public class CustomerTest extends BaseTest {

   private LoginPage loginPage;
   private DashboardPage dashboardPage;
   private CustomersPage customersPage;

   @Test
   public void testAddNewCustomer() {
      loginPage = new LoginPage();
      dashboardPage = loginPage.loginCRM();
      customersPage = dashboardPage.clickMenuCustomers();
      customersPage.verifyCustomerPageDisplayed();
      customersPage.clickAddNewCustomerButton();
      customersPage.fillDataNewCustomer("Test Customer 1808A1", "VIP", "USD", "Vietnamese", "Vietnam");
      customersPage.clickSaveButton();
      customersPage.verifyAlertMessageSuccessDisplayed();
      customersPage.verifyCustomerDetail("Test Customer 1808A1", "VIP", "USD", "Vietnamese", "Vietnam");
   }
}
