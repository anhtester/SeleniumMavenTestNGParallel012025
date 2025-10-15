package com.anhtester.Bai30_Screenshot_RecordVideo.testcases;

import com.anhtester.Bai30_Screenshot_RecordVideo.pages.DashboardPage;
import com.anhtester.Bai30_Screenshot_RecordVideo.pages.LoginPage;
import com.anhtester.Bai30_Screenshot_RecordVideo.pages.ProjectsPage;
import com.anhtester.common.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DashboardTest extends BaseTest {

   private LoginPage loginPage;
   private DashboardPage dashboardPage;
   private ProjectsPage projectsPage;

   @Test
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
