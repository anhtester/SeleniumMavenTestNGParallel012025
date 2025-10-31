package com.anhtester.Bai34_AllureReport.testcases;

import com.anhtester.Bai34_AllureReport.pages.LoginPage;
import com.anhtester.common.BaseTest;
import io.qameta.allure.*;
import org.testng.SkipException;
import org.testng.annotations.Test;

@Epic("CRM version 2.0.0")
@Feature("Login user feature")
@Story("Login with valid and invalid credentials")
public class LoginTest extends BaseTest {

   private LoginPage loginPage;

   @Description("Verify user can login with valid credentials")
   @Link(name = "https://jira.anhtester.com/login/CRM-01", url = "https://jira.anhtester.com/login/CRM-01")
   @Test(priority = 1, testName = "Login Success", description = "TC_LOGIN_CRM_01")
   public void testLoginSuccess() {
      loginPage = new LoginPage();
      loginPage.loginCRM("admin@example.com", "123456");
      loginPage.verifyLoginSuccess();
   }

   @Description("Verify user cannot login with invalid email")
   @Link(name = "https://jira.anhtester.com/login/CRM-02", url = "https://jira.anhtester.com/login/CRM-02")
   @Severity(SeverityLevel.CRITICAL)
   @Test(priority = 2, description = "TC_LOGIN_CRM_02")
   public void testLoginFailureWithEmailInvalid() {
      loginPage = new LoginPage();
      loginPage.loginCRM("admin@example.com", "123456");

      loginPage.verifyLoginFailureWithEmailOrPasswordInvalid();
   }

   @Test(priority = 3)
   public void testLoginFailureWithPasswordInvalid() {
      loginPage = new LoginPage();
      loginPage.loginCRM("admin@example.com", "123");

      loginPage.verifyLoginFailureWithEmailOrPasswordInvalid();
   }

   @Test(priority = 4)
   public void testLoginFailureWithEmailNull() {
      loginPage = new LoginPage();
      loginPage.loginCRM("", "123456");

      loginPage.verifyLoginFailureWithEmailNull();
   }

   @Test(priority = 5)
   public void testLoginFailureWithPasswordNull() {
      throw new SkipException("Skipping The Test Method ");
//      loginPage = new LoginPage();
//      loginPage.loginCRM("admin@example.com", "");
//
//      loginPage.verifyLoginFailureWithPasswordNull();
   }

}
