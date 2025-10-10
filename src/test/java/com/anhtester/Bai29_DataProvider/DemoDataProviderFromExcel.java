package com.anhtester.Bai29_DataProvider;

import com.anhtester.Bai26_ParallelExecution_POM.pages.CustomersPage;
import com.anhtester.Bai26_ParallelExecution_POM.pages.DashboardPage;
import com.anhtester.Bai26_ParallelExecution_POM.pages.LoginPage;
import com.anhtester.common.BaseTest;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class DemoDataProviderFromExcel extends BaseTest {

   @Test(dataProvider = "data_provider_login_excel", dataProviderClass = DataProviderFactory.class)
   public void testLoginCRM_01(String email, String password) {
      System.out.println("Email: " + email + " | Password: " + password);
      LoginPage loginPage = new LoginPage();
      loginPage.loginCRM(email, password);
      loginPage.verifyLoginSuccess();
   }

   @Test(dataProvider = "data_provider_login_excel_hashtable", dataProviderClass = DataProviderFactory.class)
   public void testLoginCRM_02(Hashtable<String, String> data) {
      System.out.println("Email: " + data.get("EMAIL") + " | Password: " + data.get("PASSWORD"));
      LoginPage loginPage = new LoginPage();
      loginPage.loginCRM(data.get("EMAIL"), data.get("PASSWORD"));
      loginPage.verifyLoginSuccess();
   }

   // Sử dụng DataProvider với các dòng cụ thể (1, 3, 4)
   @Test(dataProvider = "data_provider_login_excel_specific_rows", dataProviderClass = DataProviderFactory.class)
   public void testLoginWithSpecificRows(String email, String password) {
      System.out.println("Email: " + email);
      System.out.println("Password: " + password);
   }

   // Sử dụng DataProvider với các dòng cụ thể dạng Hashtable
   @Test(dataProvider = "data_provider_login_excel_specific_rows_hashtable", dataProviderClass = DataProviderFactory.class)
   public void testLoginWithSpecificRowsHashtable(Hashtable<String, String> data) {
      String username = data.get("USERNAME");
      String password = data.get("PASSWORD");

      System.out.println("Username: " + username);
      System.out.println("Password: " + password);
   }

}
