package com.anhtester.listeners;

import com.anhtester.helpers.CaptureHelper;
import com.anhtester.reports.AllureManager;
import com.anhtester.reports.ExtentReportManager;
import com.anhtester.reports.ExtentTestManager;
import com.anhtester.utils.LogUtils;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

   public String getTestName(ITestResult result) {
      return result.getTestName() != null ? result.getTestName() : result.getMethod().getConstructorOrMethod().getName();
   }

   public String getTestDescription(ITestResult result) {
      return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
   }

   @Override
   public void onStart(ITestContext result) {
      LogUtils.info("Setup môi trường onStart: " + result.getStartDate());
      //Initialize report
      //Connect to database
      //Call API get Token
   }

   @Override
   public void onFinish(ITestContext result) {
      LogUtils.info("Kết thúc bộ test: " + result.getEndDate());
      //Generate report
      //Kết thúc và thực thi Extents Report
      ExtentReportManager.getExtentReports().flush();

      //Send email
   }

   @Override
   public void onTestStart(ITestResult result) {
      LogUtils.info("Bắt đầu chạy test case: " + result.getName());
      //count_total++;
      //Write log to file
      CaptureHelper.startRecord(result.getName());

      //Bắt đầu ghi 1 TCs mới vào Extent Report
      ExtentTestManager.saveToReport(getTestName(result), getTestDescription(result));
   }

   @Override
   public void onTestSuccess(ITestResult result) {
      LogUtils.info("Test case " + result.getName() + " is passed.");
      LogUtils.info("==> Status: " + result.getStatus());
      //count_passed++;
      //Write log to file
      //Write status to report
      //Extent Report
      ExtentTestManager.logMessage(Status.PASS, result.getName() + " is passed.");

      CaptureHelper.stopRecord();
   }

   @Override
   public void onTestFailure(ITestResult result) {
      LogUtils.error("Test case " + result.getName() + " is failed.");
      //LogUtils.info("==> Status: " + result.getStatus());
      //count_failed++;
      LogUtils.error("==> Lý do: " + result.getThrowable());
      CaptureHelper.takeScreenshot(result.getName());
      //Create ticket on Jira
      //Write log to file
      //Write status to report
      //Extent Report
      ExtentTestManager.addScreenshot(result.getName());
      ExtentTestManager.logMessage(Status.FAIL, result.getThrowable().toString());
      ExtentTestManager.logMessage(Status.FAIL, result.getName() + " is failed.");

      //Allure Report
      AllureManager.saveTextLog(result.getName() + " is failed.");
      AllureManager.saveScreenshotPNG();

      CaptureHelper.stopRecord();
   }

   @Override
   public void onTestSkipped(ITestResult result) {
      LogUtils.warn("Test case " + result.getName() + " is skipped.");
      //LogUtils.info("==> Status: " + result.getStatus());
      //Write log to file
      //Write status to report
      //Extent Report
      ExtentTestManager.logMessage(Status.SKIP, result.getThrowable().toString());
      ExtentTestManager.logMessage(Status.SKIP, result.getName() + " is skipped.");

      CaptureHelper.stopRecord();
   }
}