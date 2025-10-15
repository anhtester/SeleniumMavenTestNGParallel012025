package com.anhtester.helpers;

import com.anhtester.drivers.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import org.monte.media.Format;
import org.monte.media.Registry;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import static org.monte.media.FormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

public class CaptureHelper extends ScreenRecorder {

   // Record with Monte Media library
   public static ScreenRecorder screenRecorder;
   public String name;

   //Hàm xây dựng
   public CaptureHelper(GraphicsConfiguration cfg, Rectangle captureArea, Format fileFormat, Format screenFormat, Format mouseFormat, Format audioFormat, File movieFolder, String name) throws IOException, AWTException {
      super(cfg, captureArea, fileFormat, screenFormat, mouseFormat, audioFormat, movieFolder);
      this.name = name;
   }

   //Hàm này bắt buộc để ghi đè custom lại hàm trong thư viên viết sẵn
   @Override
   protected File createMovieFile(Format fileFormat) throws IOException {

      if (!movieFolder.exists()) {
         movieFolder.mkdirs();
      } else if (!movieFolder.isDirectory()) {
         throw new IOException("\"" + movieFolder + "\" is not a directory.");
      }

      return new File(movieFolder, name + "_" + SystemHelper.getDateTimeNowFormat() + "." + Registry.getInstance().getExtension(fileFormat));
   }

   // Start record video
   public static void startRecord(String recordName) {
      //Tạo thư mục để lưu file video vào
      File file = new File(SystemHelper.getCurrentDir() + PropertiesHelper.getValue("VIDEO_RECORD_PATH"));
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      int width = screenSize.width;
      int height = screenSize.height;

      Rectangle captureSize = new Rectangle(0, 0, width, height);

      GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
      try {
         screenRecorder = new CaptureHelper(gc, captureSize, new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI), new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FrameRateKey, Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60), new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)), null, file, recordName);
         screenRecorder.start();
      } catch (IOException e) {
         throw new RuntimeException(e);
      } catch (AWTException e) {
         throw new RuntimeException(e);
      }
   }

   // Stop record video
   public static void stopRecord() {
      try {
         screenRecorder.stop();
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   public static void takeScreenshot(String screenshotName) {
      TakesScreenshot ts = (TakesScreenshot) DriverManager.getDriver();
      File source = ts.getScreenshotAs(OutputType.FILE);

      File theDir = new File(PropertiesHelper.getValue("SCREENSHOT_PATH"));
      if (!theDir.exists()) {
         theDir.mkdirs();
      }

      try {
         FileHandler.copy(source, new File(PropertiesHelper.getValue("SCREENSHOT_PATH") + screenshotName + ".png"));
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   public static void captureScreenshot(String screenshotName) {
      TakesScreenshot ts = (TakesScreenshot) DriverManager.getDriver();
      File source = ts.getScreenshotAs(OutputType.FILE);

      File theDir = new File(PropertiesHelper.getValue("SCREENSHOT_PATH"));
      if (!theDir.exists()) {
         theDir.mkdirs();
      }

      try {
         FileHandler.copy(source, new File(PropertiesHelper.getValue("SCREENSHOT_PATH") + screenshotName + ".png"));
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }
}
