package com.anhtester.Bai34_AllureReport.testcases;

import com.anhtester.Bai34_AllureReport.pages.DashboardPage;
import com.anhtester.Bai34_AllureReport.pages.LoginPage;
import com.anhtester.Bai34_AllureReport.pages.ProjectsPage;
import com.anhtester.common.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("CRM version 2.0.0")
@Feature("Dashboard feature")
@Story("Verify labels on Dashboard")
public class DashboardTest extends BaseTest {

   private LoginPage loginPage;
   private DashboardPage dashboardPage;
   private ProjectsPage projectsPage;

   @Link(name = "https://jira.anhtester.com/login/CRM-04", url = "https://jira.anhtester.com/login/CRM-04")
   @Test(priority = 1, description = "TC_DASHBOARD_CRM_01")
   public void testLabelProjectInProgress() {
      loginPage = new LoginPage();
//      loginPage.loginCRM("admin@example.com", "123456");
//      loginPage.verifyLoginSuccess();
//
//      dashboardPage = new DashboardPage(driver);

      dashboardPage = loginPage.loginCRM();

      dashboardPage.verifyDashboardPageDisplayed();

      String totalProjectsInProgressOnDashboard = dashboardPage.getTotalProjectsInProgress();

      projectsPage = dashboardPage.clickMenuProjects(); //Chuyển sang trang Projects

//      projectsPage = new ProjectsPage(driver);

      Assert.assertEquals(totalProjectsInProgressOnDashboard, projectsPage.getTotalProjectsInProgress() + " / " + projectsPage.getTotalProjects(),
              "Total Projects In Progress on Dashboard does not match total Projects In Progress on Projects Page.");

      //dashboardPage.verifyTotalProjectsInProgress();
   }
}
