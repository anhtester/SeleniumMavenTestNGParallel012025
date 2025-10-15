package com.anhtester.Bai30_Screenshot_RecordVideo.testcases;

import com.anhtester.Bai30_Screenshot_RecordVideo.pages.CustomersPage;
import com.anhtester.Bai30_Screenshot_RecordVideo.pages.DashboardPage;
import com.anhtester.Bai30_Screenshot_RecordVideo.pages.LoginPage;
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
