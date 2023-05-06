package Utility;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

public class Util {
    private static Properties properties;
    private static Properties objProp;

    static {
        try (InputStream input = new FileInputStream("src/main/resources/config.properties")) {
            properties = new Properties();
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error loading configuration file");
        }
    }

    public static String getData(String key) {
        return properties.getProperty(key);
    }

    public static String getObjectlocator(String element) {
        try (InputStream input = new FileInputStream("src/main/resources/config.properties")) {
            objProp = new Properties();
            objProp.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error loading configuration file");
        }
        return objProp.getProperty(element);
    }

    public static String generateRandomStringData(int targetStringLength) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        //int targetStringLength = 10;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        System.out.println(generatedString);
        return generatedString;
    }

    public static String generateRandomNumber(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Length of the random number should be greater than 0");
        }
        Random random = new Random();
        StringBuilder randomNumber = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int digit = random.nextInt(10); // Generate a random digit between 0 and 9 (inclusive)
            randomNumber.append(digit);
        }
        return randomNumber.toString();
    }

    public static void waitForElementToBeVisible(WebDriver driver, WebElement element, Duration timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForElementToBeInVisible(WebDriver driver, WebElement element, Duration timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public static void waitForElementToBeClickable(WebDriver driver, WebElement element, Duration timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForElementToBePresent(WebDriver driver, By locator, Duration timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static void waitForUrlToContain(WebDriver driver, String urlFragment, Duration timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.urlContains(urlFragment));
    }

    public static void waitForPageToLoad(WebDriver driver, Duration timeoutInSeconds) {
        ExpectedCondition <Boolean> pageLoadCondition = new ExpectedCondition <Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(pageLoadCondition);
    }

    public static void pageLoad_wait(WebDriver driver) {
        Util.waitForPageToLoad(driver, Duration.ofSeconds(Long.parseLong(Util.getData("defaultTimeOut"))));
    }

    public static Duration duration() {
        return Duration.ofSeconds(Long.parseLong(getData("defaultTimeOut")));

    }


    public static void scrollToElement(WebDriver driver, WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void highlightElement(WebDriver driver, WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].style.border='3px solid red'", element);
    }

   /* public static void scrollToAndHighlightElement(WebDriver driver, WebElement element) {
        waitForElementToBeVisible(driver,element,Duration.ofSeconds(30));
        scrollToElement(driver, element);
        highlightElement(driver, element);
    }*/

}
