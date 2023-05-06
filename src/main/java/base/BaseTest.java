package base;

import Utility.ExtentManager;
import Utility.Util;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTest {
    protected WebDriver driver;
    protected static ExtentReports extentReports;
    protected ExtentTest extentTest;
    private static final Logger logger = LogManager.getLogger(BaseTest.class);
    private static ThreadLocal <ExtentTest> extentTestThreadLocal = new ThreadLocal <>();

    @BeforeSuite
    public void beforeSuite() {
        extentReports = ExtentManager.createExtentReports();
    }

    @BeforeClass
    @Parameters("browser")
    public void setup(String browser) {
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--incognito");
                chromeOptions.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(chromeOptions);
                driver.manage().deleteAllCookies();
                driver.manage().window().maximize();
                String url = Util.getData("testUrl");
                driver.get(Util.getData("testUrl"));
                logger.info(" Launching Chrome browsers ");
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxProfile firefoxProfile = new FirefoxProfile();
                firefoxProfile.setPreference("browser.privatebrowsing.autostart", true);
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setProfile(firefoxProfile);
                driver = new FirefoxDriver();
                driver.manage().deleteAllCookies();
                driver.get(Util.getData("testUrl"));

                logger.info(" Launching FireFox browsers ");
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("-inprivate");
                edgeOptions.addArguments("--remote-allow-origins=*");
                driver = new EdgeDriver(edgeOptions);
                driver.manage().deleteAllCookies();
                driver.get(Util.getData("testUrl"));

                logger.info(" Launching Edge browsers ");
                break;
            default:
                throw new IllegalArgumentException("Invalid browser: " + browser);
        }
    }

    @BeforeMethod
    @Parameters({"browser"})
    public void beforeMethod(Method method, String browser) {
        String testName = method.getName();
        extentTest = extentReports.createTest(testName + " (" + browser.toUpperCase() + ")");
        extentTest.info(" Browser selected for the AUT is  " + browser.toUpperCase());
        extentTestThreadLocal.set(extentTest);
    }

    @AfterMethod
    public void afterMethod(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = takeScreenshot(result.getName());
            extentTest.log(Status.FAIL, "Test Case Failed: " + result.getName() + result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            extentTest.log(Status.PASS, "Test Passed" + result.getName() + "  ");
        } else {
            extentTest.log(Status.SKIP, "Test Skipped");
        }
    }

   @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

/*    @AfterTest
    public void teardown_testLevel() {
        if (driver != null) {
            driver.close();
        }
    }*/

    @AfterSuite
    public void afterSuite() {
        extentReports.flush();
    }

    private String takeScreenshot(String screenshotName) throws IOException {
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String screenshotPath = "test-output/Current test results/Screenshots/" + screenshotName + "_" + timestamp + ".png";
        Path screenshotDestination = Paths.get(screenshotPath);
        try {
            try {
                Files.createDirectories(screenshotDestination.getParent());
            } catch (IOException e) {
                System.out.println(" Folder created or not created ");
                // e.printStackTrace();
            }
            Files.copy(sourceFile.toPath(), screenshotDestination);
        } catch (IOException e) {
            System.out.println(" copying not happening");
            e.printStackTrace();
        }
        String relativeScreenshotPath = new File("test-output/Current test results").toURI().relativize(screenshotDestination.toUri()).getPath();
        return relativeScreenshotPath;
    }

    protected static ExtentTest getExtentTest() {
        return extentTestThreadLocal.get();
    }

    protected <TPage> TPage createPage(Class <TPage> pageClass) {
        return PageFactory.initElements(driver, pageClass);
    }
}
