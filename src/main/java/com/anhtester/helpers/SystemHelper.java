package com.anhtester.helpers;

import com.anhtester.utils.LogUtils;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SystemHelper {
   public static String getCurrentDir() {
      String current = System.getProperty("user.dir") + File.separator;
      LogUtils.info("Current Directory: " + current);
      return current;
   }

   public static String getDateTimeNowFormat() {
      // Lấy thời gian hiện tại
      LocalDateTime now = LocalDateTime.now();

      // Định dạng ngày giờ theo pattern có chứa dấu : và khoảng trắng
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

      // Chuyển sang chuỗi và thay thế ký tự
      String formatted = now.format(formatter)
              .replace("-", "_")
              .replace(":", "_")
              .replace(" ", "_");

      return formatted;
   }
}
