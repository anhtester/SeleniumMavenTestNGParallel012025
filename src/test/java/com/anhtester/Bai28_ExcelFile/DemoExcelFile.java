package com.anhtester.Bai28_ExcelFile;

import com.anhtester.Bai26_ParallelExecution_POM.pages.LoginPage;
import com.anhtester.common.BaseTest;
import com.anhtester.helpers.ExcelHelper;
import org.testng.annotations.Test;

public class DemoExcelFile {
   @Test
   public void testReadExcelFile() {
      System.out.println("Demo Read Excel File");
      ExcelHelper excel = new ExcelHelper();
      excel.setExcelFile("src/test/resources/testdata/DataCRM.xlsx", "Login");

      System.out.println(excel.getCellData("EMAIL", 2));
      System.out.println(excel.getCellData("PASSWORD", 2));

//      LoginPage loginPage = new LoginPage();
//      loginPage.loginCRM(
//              excel.getCellData("EMAIL", 2),
//              excel.getCellData("PASSWORD", 2)
//      );
   }

   @Test
   public void testReadExcelFile_Customer() {
      ExcelHelper excel = new ExcelHelper();
      excel.setExcelFile("src/test/resources/testdata/DataCRM.xlsx", "Customer");

      System.out.println(excel.getCellData("CUSTOMER_NAME", 1));
      System.out.println(excel.getCellData("VAT", 1));

   }

   @Test
   public void testWriteExcelFile() {
      System.out.println("Demo Write Excel File");
      ExcelHelper excel = new ExcelHelper();
      excel.setExcelFile("src/test/resources/testdata/DataCRM.xlsx", "Login");

      excel.setCellData("PASS", "TEST_RESULT", 1);
      excel.setCellData("FAIL", "TEST_RESULT", 2);
   }
}
