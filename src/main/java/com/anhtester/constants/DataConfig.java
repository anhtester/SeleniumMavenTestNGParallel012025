package com.anhtester.constants;

import com.anhtester.helpers.PropertiesHelper;

public class DataConfig {
   //"final" nghĩa là HẰNG giá trị: không thể cập nhật lại
   public static final int WAIT_TIMEOUT = Integer.parseInt(PropertiesHelper.getValue("WAIT_TIMEOUT"));
   public static final double STEP_TIME = Double.parseDouble(PropertiesHelper.getValue("STEP_TIME"));
   public static final int PAGE_LOAD_TIMEOUT = Integer.parseInt(PropertiesHelper.getValue("PAGE_LOAD_TIMEOUT"));
   public static final String EXCEL_DATA_FILE_PATH = PropertiesHelper.getValue("EXCEL_DATA_FILE_PATH");
   public static final String SCREENSHOT_ALL_STEP = PropertiesHelper.getValue("SCREENSHOT_ALL_STEP");
   public static final String SCREENSHOT_FAILURE = PropertiesHelper.getValue("SCREENSHOT_FAILURE");
   public static final String SCREENSHOT_SUCCESS = PropertiesHelper.getValue("SCREENSHOT_SUCCESS");
}
