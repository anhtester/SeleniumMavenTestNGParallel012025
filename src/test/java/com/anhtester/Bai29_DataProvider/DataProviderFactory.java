package com.anhtester.Bai29_DataProvider;

import com.anhtester.helpers.ExcelHelper;
import com.anhtester.helpers.SystemHelper;
import org.testng.annotations.DataProvider;

import java.util.Arrays;

public class DataProviderFactory {
   @DataProvider(name = "loginData1")
   public Object[][] getDataLogin1() {
      return new Object[][]{
              {"admin@example.com", 123456},
              {"user1@example.com", 1234}
      };
   }

   @DataProvider(name = "loginData2", parallel = false)
   public Object[][] getDataLogin2() {
      return new Object[][]{
              {"admin@example.com", 123456, "Admin"},
              {"user@example.com", 123, "User"},
              {"customer@example.com", 123, "Customer"}
      };
   }

   @DataProvider(name = "AddNewCustomerData", parallel = false)
   public Object[][] getAddNewCustomerData() {
      return new Object[][]{
              {"New Customer 1010A4", "VIP", "USD", "Vietnamese", "Vietnam"},
              {"New Customer 1010A5", "Gold", "EUR", "English", "Canada"},
              {"New Customer 1010A6", "Silver", "USD", "German", "Germany"},
              {"New Customer 1010A7", "Silver", "USD", "German", "Germany"}
      };
   }

   @DataProvider(name = "data_provider_login_excel", parallel = true)
   public Object[][] data_provider_login_excel() {
      ExcelHelper excelHelper = new ExcelHelper();
      Object[][] data = excelHelper.getExcelData(SystemHelper.getCurrentDir() + "src/test/resources/testdata/DataCRM.xlsx", "Login_DataProvider");
      System.out.println("Login Data from Excel: " + data);
      return data;
   }

   @DataProvider(name = "data_provider_login_excel_hashtable", parallel = true)
   public Object[][] data_provider_login_excel_hashtable() {
      ExcelHelper excelHelper = new ExcelHelper();
      Object[][] data = excelHelper.getDataHashTable(SystemHelper.getCurrentDir() + "src/test/resources/testdata/DataCRM.xlsx", "Login_DataProvider", 1, 3);
      System.out.println("Login Data from Excel (Hashtable): " + data);
      return data;
   }

   @DataProvider(name = "data_provider_login_excel_specific_rows", parallel = true)
   public Object[][] data_provider_login_excel_specific_rows() {
      ExcelHelper excelHelper = new ExcelHelper();
      int[] specificRows = new int[]{1, 3, 4}; //Dòng cụ thể cần lấy
      Object[][] data = excelHelper.getDataFromSpecificRows(SystemHelper.getCurrentDir() + "src/test/resources/testdata/DataCRM.xlsx", "Login_DataProvider", specificRows);
      System.out.println("getDataFromSpecificRows: " + data);
      return data;
   }

   @DataProvider(name = "data_provider_login_excel_specific_rows_hashtable", parallel = true)
   public Object[][] data_provider_login_excel_specific_rows_hashtable() {
      ExcelHelper excelHelper = new ExcelHelper();
      int[] specificRows = new int[]{1, 3, 4}; //Dòng cụ thể cần lấy
      Object[][] data = excelHelper.getDataHashTableFromSpecificRows(SystemHelper.getCurrentDir() + "src/test/resources/testdata/DataCRM.xlsx", "Login_DataProvider", specificRows);
      System.out.println("getDataHashTableFromSpecificRows: " + data);
      return data;
   }

}
