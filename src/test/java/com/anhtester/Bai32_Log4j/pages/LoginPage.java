package com.anhtester.Bai32_Log4j.pages;

import com.anhtester.helpers.PropertiesHelper;
import com.anhtester.keywords.WebUI;
import com.anhtester.utils.LogUtils;
import org.openqa.selenium.By;
import org.testng.Assert;

public class LoginPage extends BasePage {

   //Khai báo driver trong từng trang
   private String url_login_admin = "https://crm.anhtester.com/admin";

   //Khai báo đối tượng element thuộc về trang Login
   private By headerLoginPage = By.xpath("");
   private By checkboxRememberMe = By.xpath("");
   private By linkForgotPassword = By.xpath("//a[normalize-space()='Forgot Password?']");
   private By inputEmail = By.xpath("//input[@id='email']");
   private By inputPassword = By.xpath("//input[@id='password']");
   private By buttonLogin = By.xpath("//button[normalize-space()='Login']");
   private By errorMessageInvalid = By.xpath("//div[@id='alerts']//div[contains(text(),'Invalid email or password')]");
   private By errorMessageRequiredEmail = By.xpath("//div[contains(text(),'The Email Address field is required.')]");
   private By errorMessageRequiredPassword = By.xpath("//div[contains(text(),'The Password field is required.')]");

   //Khai báo các hàm xử lý trong nội bộ trang Login

   public void verifyLoginPageDisplayed() {
      boolean check = WebUI.checkElementExist(headerLoginPage);
      Assert.assertTrue(check, "Login page is not displayed.");
   }

   public void navigateToLoginAdminPage() {
      WebUI.openURL(PropertiesHelper.getValue("url"));
      //LogUtils.info("Navigated to URL: " + PropertiesHelper.getValue("url"));
      WebUI.waitForPageLoaded();
   }

   private void enterEmail(String email) {
      WebUI.setText(inputEmail, email);
      //LogUtils.info("Entered email: " + email);
   }

   private void enterPassword(String password) {
      WebUI.setText(inputPassword, password);
      //LogUtils.info("Entered password: " + password);
   }

   private void clickLoginButton() {
      WebUI.clickElement(buttonLogin);
      //LogUtils.info("Clicked on Login button.");
   }

   public void loginCRM(String email, String password) {
      navigateToLoginAdminPage();
      enterEmail(email);
      enterPassword(password);
      clickLoginButton();
      WebUI.waitForPageLoaded();
   }

   public DashboardPage loginCRM() {
      navigateToLoginAdminPage();
      enterEmail(PropertiesHelper.getValue("email"));
      enterPassword(PropertiesHelper.getValue("password"));
      clickLoginButton();
      WebUI.waitForPageLoaded();
      verifyLoginSuccess();

      return new DashboardPage();
   }

   public void verifyLoginSuccess() {
      boolean check = WebUI.checkElementExist(By.xpath("//span[normalize-space()='Dashboard']"), 5, 1000);
      Assert.assertTrue(check, "Login failed or Dashboard not displayed.");
   }

   public void verifyLoginFailureWithEmailOrPasswordInvalid() {
      WebUI.waitForPageLoaded();
      boolean check = WebUI.checkElementExist(errorMessageInvalid, 5, 1000);
      Assert.assertTrue(check, "Error message for invalid email not displayed.");
   }

   public void verifyLoginFailureWithEmailNull() {
      boolean check = WebUI.checkElementExist(errorMessageRequiredEmail);
      Assert.assertTrue(check, "Error message for required email not displayed.");
   }

   public void verifyLoginFailureWithPasswordNull() {
      boolean check = WebUI.checkElementExist(errorMessageRequiredPassword);
      Assert.assertTrue(check, "Error message for required password not displayed.");
   }

}
