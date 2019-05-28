package au.com.revit.utilities;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;

public class WaitUtils {

    public static WebElement fluentlyWaitUntilElementIsVisible(WebElement webelement, WebDriver driver, int timeOutInSeconds) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).ignoring(Exception.class).withTimeout(Duration.ofSeconds(timeOutInSeconds));
        return wait.until(ExpectedConditions.visibilityOf(webelement));
    }

    public static WebElement fluentlyWaitUntilElementIsClickable(WebElement webelement, WebDriver driver, int timeOutInSeconds) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).ignoring(Exception.class).withTimeout(Duration.ofSeconds(timeOutInSeconds));
        return wait.until(ExpectedConditions.elementToBeClickable(webelement));
    }


    public static boolean fluentlyWaitUntilElementDisaapers(WebElement webelement, WebDriver driver, int timeOutInSeconds) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).ignoring(Exception.class).withTimeout(Duration.ofSeconds(timeOutInSeconds));
        return wait.until(ExpectedConditions.invisibilityOf(webelement));
    }

    public static void selectValueFromList(WebDriver driver, String xpathExpression, String valuetobeselected) throws Exception {
        boolean flag = false;
        WebElement typeselection = driver.findElement(By.xpath(xpathExpression));
        hardWait(1);
        try {
            for (WebElement sortItem : typeselection.findElements(By.xpath(xpathExpression))) {
                if (sortItem.getText().trim().equalsIgnoreCase(valuetobeselected.trim())) {
                    flag = true;
                    sortItem.click();
                    hardWait(1);
                }
            }
            if (!flag) {
                Assert.fail("Value is not Present in the List " + valuetobeselected);

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public static void hardWait(int timeInSeconds) {
        try {
            Thread.sleep(timeInSeconds * 1000l);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void waitABit(int timeInMillies) {
        try {
            Thread.sleep(timeInMillies);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void browserRefresh(WebDriver driver) {
        driver.navigate().refresh();
    }

    public void selectCertificate() {
        Thread certSelectionThread = null;
        Runnable run = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000 * 8);
                    Robot robot = new Robot();
                    robot.keyPress(KeyEvent.VK_ENTER);
                } catch (AWTException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        certSelectionThread = new Thread(run);
        certSelectionThread.start();
//        this.open();
        if (certSelectionThread != null) {
            try {
                certSelectionThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
