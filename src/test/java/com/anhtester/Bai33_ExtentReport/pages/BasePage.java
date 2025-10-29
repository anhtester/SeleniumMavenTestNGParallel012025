package com.anhtester.Bai33_ExtentReport.pages;

import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;

public class BasePage {

   //Element chung cho tất cả các trang
   public By menuDashboard = By.xpath("//span[normalize-space()='Dashboard' and @class='menu-text']");
   public By menuCustomers = By.xpath("//span[normalize-space()='Customers' and @class='menu-text']");
   public By menuProjects = By.xpath("//span[normalize-space()='Projects' and @class='menu-text']");
   public By menuTasks = By.xpath("//span[normalize-space()='Tasks' and @class='menu-text']");
   public By iconProfile = By.xpath("//li[@class='icon header-user-profile']");
   public By optionLogout = By.xpath("//a[text()='Logout']");

   //Các hàm xử lý chung cho tất cả các trang
   public void logoutSystem() {
      WebUI.clickElement(iconProfile);
      WebUI.clickElement(optionLogout);
   }

   public void clickMenuDashboard() {
      WebUI.clickElement(menuDashboard);
   }

   public CustomersPage clickMenuCustomers() {
      WebUI.clickElement(menuCustomers);
      return new CustomersPage();
   }

   public ProjectsPage clickMenuProjects() {
      WebUI.clickElement(menuProjects);
      return new ProjectsPage();
   }

   public void clickMenuTasks() {
      WebUI.clickElement(menuTasks);
   }

}
