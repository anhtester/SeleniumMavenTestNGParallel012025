package com.anhtester.keywords;

import com.anhtester.drivers.DriverManager;
import com.anhtester.helpers.PropertiesHelper;
import com.anhtester.helpers.SystemHelper;
import com.anhtester.reports.AllureManager;
import com.anhtester.reports.ExtentTestManager;
import com.anhtester.utils.LogUtils;
import com.aventstack.extentreports.Status;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;

public class WebUI {

   private static int WAIT_TIMEOUT = Integer.parseInt(PropertiesHelper.getValue("WAIT_TIMEOUT"));
   private static double STEP_TIME = Double.parseDouble(PropertiesHelper.getValue("STEP_TIME"));
   private static int PAGE_LOAD_TIMEOUT = Integer.parseInt(PropertiesHelper.getValue("PAGE_LOAD_TIMEOUT"));

   public static void logConsole(Object message) {
      System.out.println(message);
   }

   public static void sleep(double seconds) {
      try {
         Thread.sleep((long) (seconds * 1000));
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
   }

   public static void highlightElement(By by) {
      // Highlight the element using JavaScript
      String script = "arguments[0].style.border='3px solid red';";

      JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
      js.executeScript(script, DriverManager.getDriver().findElement(by));
   }

   public static void highlightElement(By by, String color) {
      // Highlight the element using JavaScript
      String script = "arguments[0].style.border='3px solid " + color + "';";
      ((JavascriptExecutor) DriverManager.getDriver()).executeScript(script, DriverManager.getDriver().findElement(by));
   }

   //Wait for Element

   public static WebElement waitForElementVisible(By by) {
      WebElement element = null;
      try {
         WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(WAIT_TIMEOUT), Duration.ofMillis(500));
         element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
         highlightElement(by);
         return element;
      } catch (Throwable error) {
         LogUtils.info("Timeout waiting for the element Visible. " + by.toString());
         Assert.fail("Timeout waiting for the element Visible. " + by.toString());
      }
      return element;
   }

   public static WebElement waitForElementVisible(By by, int seconds) {
      WebElement element = null;
      try {
         WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(seconds), Duration.ofMillis(200));
         element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
         highlightElement(by);
         return element;
      } catch (Throwable error) {
         LogUtils.error("Timeout waiting for the element Visible. " + by.toString());
         Assert.fail("Timeout waiting for the element Visible. " + by.toString());
      }
      return element;
   }

   public static WebElement waitForElementToBeClickable(By by) {
      WebElement element = null;
      try {
         WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(WAIT_TIMEOUT), Duration.ofMillis(500));
         element = wait.until(ExpectedConditions.elementToBeClickable(by));
         highlightElement(by);
         return element;
      } catch (Throwable error) {
         LogUtils.error("Timeout waiting for the element to be clickable. " + by.toString());
         Assert.fail("Timeout waiting for the element to be clickable. " + by.toString());
      }
      return element;
   }

   public static WebElement waitForElementToBeClickable(By by, int seconds) {
      WebElement element = null;
      try {
         WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(seconds), Duration.ofMillis(200));
         element = wait.until(ExpectedConditions.elementToBeClickable(by));
         highlightElement(by);
         return element;
      } catch (Throwable error) {
         LogUtils.error("Timeout waiting for the element to be clickable with  " + seconds + "(s) : " + by);
         Assert.fail("Timeout waiting for the element to be clickable with " + seconds + "(s) : " + by);
      }
      return element;
   }

   public static WebElement waitForElementPresent(By by) {
      WebElement element = null;
      try {
         WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(WAIT_TIMEOUT), Duration.ofMillis(200));
         element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
         highlightElement(by);
         return element;
      } catch (Throwable error) {
         LogUtils.error("Element not exist." + by);
         Assert.fail("Element not exist. " + by);
      }
      return element;
   }

   public static WebElement waitForElementPresent(By by, int seconds) {
      WebElement element = null;
      try {
         WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(seconds), Duration.ofMillis(200));
         element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
         highlightElement(by);
         return element;
      } catch (Throwable error) {
         LogUtils.error("Element not exist." + by);
         Assert.fail("Element not exist. " + by);
      }
      return element;
   }

   //Chờ đợi trang load xong mới thao tác
   public static void waitForPageLoaded() {
      WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(PAGE_LOAD_TIMEOUT), Duration.ofMillis(500));
      JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

      //Wait for Javascript to load
//      ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
//         @Override
//         public Boolean apply(WebDriver driver) {
//            return js.executeScript("return document.readyState").toString().equals("complete");
//         }
//      };
      // wait for Javascript to loaded
      ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) DriverManager.getDriver()).executeScript("return document.readyState")
              .toString().equals("complete");

      //Check JS is Ready
      boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

      //Wait Javascript until it is Ready!
      if (!jsReady) {
         //LogUtils.info("Javascript is NOT Ready.");
         //Wait for Javascript to load
         try {
            wait.until(jsLoad);
         } catch (Throwable error) {
            LogUtils.error(error.getMessage());
            Assert.fail("FAILED. Timeout waiting for page load.");
         }
      }
   }

   public static WebElement getWebElement(By by) {
      return DriverManager.getDriver().findElement(by);
   }

   public static List<WebElement> getWebElements(By by) {
      return DriverManager.getDriver().findElements(by);
   }

   public static boolean checkElementExist(By by) {
      List<WebElement> listElement = getWebElements(by);

      if (listElement.size() > 0) {
         LogUtils.info("checkElementExist: " + true + " --- " + by);
         return true;
      } else {
         LogUtils.info("checkElementExist: " + false + " --- " + by);
         return false;
      }
   }

   // Hàm kiểm tra sự tồn tại của phần tử với lặp lại nhiều lần
   public static boolean checkElementExist(By by, int maxRetries, int waitTimeMillis) {
      int retryCount = 0;

      while (retryCount < maxRetries) {
         try {
            WebElement element = getWebElement(by);
            if (element != null) {
               LogUtils.info("Tìm thấy phần tử ở lần thử thứ " + (retryCount + 1));
               return true; // Phần tử được tìm thấy
            }
         } catch (NoSuchElementException e) {
            LogUtils.warn("Không tìm thấy phần tử. Thử lại lần " + (retryCount + 1));
            retryCount++;
            try {
               Thread.sleep(waitTimeMillis); // Chờ trước khi thử lại
            } catch (InterruptedException ie) {
               ie.printStackTrace();
            }
         }
      }

      // Trả về false nếu không tìm thấy phần tử sau maxRetries lần
      LogUtils.info("Không tìm thấy phần tử sau " + maxRetries + " lần thử.");
      return false;
   }

   @Step("Open URL {0}")
   public static void openURL(String url) {
      DriverManager.getDriver().get(url);
      sleep(STEP_TIME);
      LogUtils.info("Open URL:  " + url);
      ExtentTestManager.logMessage(Status.PASS, "Open URL: " + url);

      if (PropertiesHelper.getValue("SCREENSHOT_ALL_STEP").equals("true")) {
         ExtentTestManager.addScreenshot("openURL_" + SystemHelper.getDateTimeNowFormat());
         AllureManager.saveScreenshotPNG();
      }

   }

   @Step("Click on element {0}")
   public static void clickElement(By by) {
      sleep(STEP_TIME);
      waitForElementToBeClickable(by).click();
      LogUtils.info("Click on element " + by);
      ExtentTestManager.logMessage(Status.PASS, "Click on element " + by);
      if (PropertiesHelper.getValue("SCREENSHOT_ALL_STEP").equals("true")) {
         ExtentTestManager.addScreenshot("clickElement_" + SystemHelper.getDateTimeNowFormat());
         AllureManager.saveScreenshotPNG();
      }
   }

   @Step("Click on element {0} with timeout {1} seconds")
   public static void clickElement(By by, int seconds) {
      sleep(STEP_TIME);
      waitForElementToBeClickable(by, seconds).click();
      LogUtils.info("Click on element " + by);
      ExtentTestManager.logMessage(Status.PASS, "Click on element " + by);
      if (PropertiesHelper.getValue("SCREENSHOT_ALL_STEP").equals("true")) {
         ExtentTestManager.addScreenshot("clickElement_" + SystemHelper.getDateTimeNowFormat());
         AllureManager.saveScreenshotPNG();
      }
   }

   @Step("Set text {1} on element {0}")
   public static void setText(By by, String text) {
      sleep(STEP_TIME);
      waitForElementVisible(by).sendKeys(text);
      LogUtils.info("Set text " + text + " on element " + by);
      ExtentTestManager.logMessage(Status.PASS, "Set text " + text + " on element " + by);
      if (PropertiesHelper.getValue("SCREENSHOT_ALL_STEP").equals("true")) {
         ExtentTestManager.addScreenshot("setText_" + SystemHelper.getDateTimeNowFormat());
         AllureManager.saveScreenshotPNG();
      }
   }

   @Step("Set text {1} on element {0} with timeout {2} seconds")
   public static void setText(By by, String text, int seconds) {
      sleep(STEP_TIME);
      waitForElementVisible(by, seconds).sendKeys(text);
      LogUtils.info("Set text " + text + " on element " + by);
      ExtentTestManager.logMessage(Status.PASS, "Set text " + text + " on element " + by);
      if (PropertiesHelper.getValue("SCREENSHOT_ALL_STEP").equals("true")) {
         ExtentTestManager.addScreenshot("setText_" + SystemHelper.getDateTimeNowFormat());
         AllureManager.saveScreenshotPNG();
      }
   }

   @Step("Get text of element {0}")
   public static String getElementText(By by) {
      waitForElementVisible(by);
      sleep(STEP_TIME);
      LogUtils.info("Get text of element " + by);
      String text = getWebElement(by).getText();
      LogUtils.info("==> TEXT: " + text);
      ExtentTestManager.logMessage(Status.PASS, "Get text of element " + by);
      ExtentTestManager.logMessage(Status.INFO, "==> Text: " + text);
      AllureManager.saveTextLog("==> TEXT: " + text);
      if (PropertiesHelper.getValue("SCREENSHOT_ALL_STEP").equals("true")) {
         ExtentTestManager.addScreenshot("getElementText_" + SystemHelper.getDateTimeNowFormat());
         AllureManager.saveScreenshotPNG();
      }
      return text; //Trả về một giá trị kiểu String
   }

   @Step("Get attribute {1} of element {0}")
   public static String getElementAttribute(By by, String attributeName) {
      waitForElementVisible(by);
      LogUtils.info("Get attribute of element " + by);
      String value = getWebElement(by).getAttribute(attributeName);
      LogUtils.info("==> Attribute value: " + value);
      ExtentTestManager.logMessage(Status.PASS, "Get attribute of element " + by);
      ExtentTestManager.logMessage(Status.INFO, "==> Attribute value: " + value);
      AllureManager.saveTextLog("==> Attribute value: " + value);
      if (PropertiesHelper.getValue("SCREENSHOT_ALL_STEP").equals("true")) {
         ExtentTestManager.addScreenshot("getElementAttribute_" + SystemHelper.getDateTimeNowFormat());
         AllureManager.saveScreenshotPNG();
      }
      return value;
   }

   public static String getElementCssValue(By by, String cssPropertyName) {
      waitForElementVisible(by);
      LogUtils.info("Get CSS value " + cssPropertyName + " of element " + by);
      String value = getWebElement(by).getCssValue(cssPropertyName);
      LogUtils.info("==> CSS value: " + value);
      return value;
   }

   public static void setTextAndKey(By by, String value, Keys key) {
      waitForPageLoaded();
      getWebElement(by).sendKeys(value, key);
      LogUtils.info("Set text: " + value + " on element " + by);
   }

   public static void scrollToElement(By by) {
      JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
      js.executeScript("arguments[0].scrollIntoView(false);", getWebElement(by));
   }

   public static void scrollToElement(WebElement element) {
      JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
      js.executeScript("arguments[0].scrollIntoView(false);", element);
   }

   public static void scrollToElementAtTop(By by) {
      JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
      js.executeScript("arguments[0].scrollIntoView(true);", getWebElement(by));
   }

   public static void scrollToElementAtBottom(By by) {
      JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
      js.executeScript("arguments[0].scrollIntoView(false);", getWebElement(by));
   }

   public static void scrollToElementAtTop(WebElement element) {
      JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
      js.executeScript("arguments[0].scrollIntoView(true);", element);
   }

   public static void scrollToElementAtBottom(WebElement element) {
      JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
      js.executeScript("arguments[0].scrollIntoView(false);", element);
   }

   public static void scrollToPosition(int X, int Y) {
      JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
      js.executeScript("window.scrollTo(" + X + "," + Y + ");");
   }

   public static boolean moveToElement(By by) {
      try {
         Actions action = new Actions(DriverManager.getDriver());
         action.moveToElement(getWebElement(by)).release(getWebElement(by)).build().perform();
         return true;
      } catch (Exception e) {
         LogUtils.error(e.getMessage());
         return false;
      }
   }

   public static boolean moveToOffset(int X, int Y) {
      try {
         Actions action = new Actions(DriverManager.getDriver());
         action.moveByOffset(X, Y).build().perform();
         return true;
      } catch (Exception e) {
         LogUtils.error(e.getMessage());
         return false;
      }
   }

   public static boolean hoverElement(By by) {
      try {
         Actions action = new Actions(DriverManager.getDriver());
         action.moveToElement(getWebElement(by)).perform();
         return true;
      } catch (Exception e) {
         return false;
      }
   }

   public static boolean mouseHover(By by) {
      try {
         Actions action = new Actions(DriverManager.getDriver());
         action.moveToElement(getWebElement(by)).perform();
         return true;
      } catch (Exception e) {
         return false;
      }
   }

   public static boolean dragAndDrop(By fromElement, By toElement) {
      try {
         Actions action = new Actions(DriverManager.getDriver());
         action.dragAndDrop(getWebElement(fromElement), getWebElement(toElement)).perform();
         //action.clickAndHold(getWebElement(fromElement)).moveToElement(getWebElement(toElement)).release(getWebElement(toElement)).build().perform();
         return true;
      } catch (Exception e) {
         LogUtils.error(e.getMessage());
         return false;
      }
   }

   public static boolean dragAndDropElement(By fromElement, By toElement) {
      try {
         Actions action = new Actions(DriverManager.getDriver());
         action.clickAndHold(getWebElement(fromElement)).moveToElement(getWebElement(toElement)).release(getWebElement(toElement)).build().perform();
         return true;
      } catch (Exception e) {
         LogUtils.error(e.getMessage());
         return false;
      }
   }

   public static boolean dragAndDropOffset(By fromElement, int X, int Y) {
      try {
         Actions action = new Actions(DriverManager.getDriver());
         //Tính từ vị trí click chuột đầu tiên (clickAndHold)
         action.clickAndHold(getWebElement(fromElement)).pause(1).moveByOffset(X, Y).release().build().perform();
         return true;
      } catch (Exception e) {
         LogUtils.error(e.getMessage());
         return false;
      }
   }

   public static boolean pressENTER() {
      try {
         Robot robot = new Robot();
         robot.keyPress(KeyEvent.VK_ENTER);
         robot.keyRelease(KeyEvent.VK_ENTER);
         return true;
      } catch (Exception e) {
         return false;
      }
   }

   public static boolean pressESC() {
      try {
         Robot robot = new Robot();
         robot.keyPress(KeyEvent.VK_ESCAPE);
         robot.keyRelease(KeyEvent.VK_ESCAPE);
         return true;
      } catch (Exception e) {
         return false;
      }
   }

   public static boolean pressF11() {
      try {
         Robot robot = new Robot();
         robot.keyPress(KeyEvent.VK_F11);
         robot.keyRelease(KeyEvent.VK_F11);
         return true;
      } catch (Exception e) {
         return false;
      }
   }

   public static boolean verifyEquals(Object actual, Object expected) {
      waitForPageLoaded();
      LogUtils.info("Verify equals: " + actual + " and " + expected);
      boolean check = actual.equals(expected);
      return check;
   }

   public static void assertEquals(Object actual, Object expected, String message) {
      waitForPageLoaded();
      LogUtils.info("Assert equals: " + actual + " and " + expected);
      Assert.assertEquals(actual, expected, message);
   }

   public static boolean verifyContains(String actual, String expected) {
      waitForPageLoaded();
      LogUtils.info("Verify contains: " + actual + " and " + expected);
      boolean check = actual.contains(expected);
      return check;
   }

   public static void assertContains(String actual, String expected, String message) {
      waitForPageLoaded();
      LogUtils.info("Assert contains: " + actual + " and " + expected);
      boolean check = actual.contains(expected);
      Assert.assertTrue(check, message);
   }

}
