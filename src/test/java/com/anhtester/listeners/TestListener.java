package com.anhtester.listeners;

import com.anhtester.helpers.CaptureHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

   @Override
   public void onStart(ITestContext result) {
      System.out.println("Setup môi trường onStart: " + result.getStartDate());
      //Initialize report
      //Connect to database
      //Call API get Token
   }

   @Override
   public void onFinish(ITestContext result) {
      System.out.println("Kết thúc bộ test: " + result.getEndDate());
      //Generate report
      //Send email
   }

   @Override
   public void onTestStart(ITestResult result) {
      System.out.println("Bắt đầu chạy test case: " + result.getName());
      //count_total++;
      //Write log to file
      CaptureHelper.startRecord(result.getName());
   }

   @Override
   public void onTestSuccess(ITestResult result) {
      System.out.println("Test case " + result.getName() + " is passed.");
      System.out.println("==> Status: " + result.getStatus());
      //count_passed++;
      //Write log to file
      //Write status to report
      CaptureHelper.stopRecord();
   }

   @Override
   public void onTestFailure(ITestResult result) {
      System.out.println("Test case " + result.getName() + " is failed.");
      System.out.println("==> Status: " + result.getStatus());
      //count_failed++;
      System.out.println("==> Lý do: " + result.getThrowable());
      CaptureHelper.takeScreenshot(result.getName());
      //Create ticket on Jira
      //Write log to file
      //Write status to report
      CaptureHelper.stopRecord();
   }

   @Override
   public void onTestSkipped(ITestResult result) {
      System.out.println("Test case " + result.getName() + " is skipped.");
      System.out.println("==> Status: " + result.getStatus());
      //Write log to file
      //Write status to report
      CaptureHelper.stopRecord();
   }
}