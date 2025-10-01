package com.anhtester.Bai29_DataProvider;

import com.anhtester.Bai26_ParallelExecution_POM.pages.LoginPage;
import com.anhtester.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DemoDataProvider extends BaseTest {

   @DataProvider(name = "loginData1")
   public Object[][] getDataLogin1() {
      return new Object[][]{
              {"admin@example.com", 123456},
              {"user1@example.com", 1234}
      };
   }

   @Test(dataProvider = "loginData1")
   public void testLogin1(String email, int password) {
      System.out.println("Email: " + email + " | Password: " + password);
   }

   @DataProvider(name = "loginData2")
   public Object[][] getDataLogin2() {
      return new Object[][]{
              {"admin@example.com", 123456, "Admin" },
              {"user@example.com", 123, "User" },
              {"customer@example.com", 123, "Customer" }
      };
   }

   @Test(dataProvider = "loginData2")
   public void testLogin2(String email, int password, String role) {
      System.out.println("Email: " + email + " | Password: " + password + " | Role: " + role);
   }

   @Test(dataProvider = "loginData2")
   public void testLoginCRM(String email, int password, String role) {
      System.out.println("Email: " + email + " | Password: " + password + " | Role: " + role);
      LoginPage loginPage = new LoginPage();
      loginPage.loginCRM(email, String.valueOf(password));
   }

}
