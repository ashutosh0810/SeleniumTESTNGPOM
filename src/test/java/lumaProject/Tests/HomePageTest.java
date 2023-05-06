package lumaProject.Tests;

import base.BaseTest;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pomPages.HomePage;
import pomPages.Loginpage;
import pomPages.LogoutPage;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomePageTest extends BaseTest {
    // this is for the miscellaneous like what is new and privacya nd policies etc
    private static final Logger logger = LogManager.getLogger(HomePageTest.class);
    private Loginpage loginpage;
    private LogoutPage logoutPage;
    private HomePage homePage;

    @BeforeMethod
    public void beforeMethod() {
        extentTest = extentReports.createTest(" Testing the Intial functionalities ");
        loginpage = createPage(Loginpage.class);
        logoutPage = createPage(LogoutPage.class);
        homePage = createPage(HomePage.class);
    }

    //@Test
    public void dummy() {
        extentTest.info(" 1st sentence ");
        extentTest.pass(" dummy passed");
        extentTest.log(Status.PASS, " this is logging the pass functionalities");
    }

    @Test(groups = {"Sanity"})
    public void validate_HomeIcon() {
        extentTest = extentReports.createTest(" Validating the home logo and home page title ");
        extentTest.info(" Validting the the home page logo and page title");
        homePage.click_homelogoImg();
        extentTest.info(" User clicked on the home page ");
        logger.info(" Validating the home page ");
        Assert.assertEquals(driver.getTitle(), "Home Page");
        extentTest.pass(" Home page title successful ");
    }

    @Test(groups = {"Sanity"})
    public void validate_AboutUs() {
        extentTest = extentReports.createTest(" Validating the About us  ");
        SoftAssert softAssert = new SoftAssert();
        logger.info(" User click on the about us link  ");
        homePage.click_aboutUsLink();
        extentTest.info(" User clikc about Us link successfully ");
        logger.info(" User check the about us message ");
        softAssert.assertEquals(driver.getTitle(), "About us");
        extentTest.pass(" About us page title successfull");
        logger.info(" Validating the about us ");
        String abtUs = homePage.get_aboutUsMsg();
        extentTest.info(" User get the about us message  " + abtUs);
        Pattern pattern = Pattern.compile(
                "(\\d+)\\s+(\\w+)");
        Matcher matcher = pattern.matcher(abtUs);
        List <String> storeNumList = new ArrayList <>();
        String temp = null;
        while (matcher.find()) {
            int val1 = Integer.parseInt(matcher.group(1));
            String val2 = matcher.group(2);
            temp = val1 + val2;
            storeNumList.add(temp);
        }
        logger.info(" Number of States are " + storeNumList.get(0));
        logger.info(" Number of Stores  are " + storeNumList.get(1));
        try {
            Assert.assertEquals(storeNumList.get(0), "230stores");
            extentTest.log(Status.PASS, " total store number is " + storeNumList.get(0));
            Assert.assertEquals(storeNumList.get(1), "43states");
            extentTest.log(Status.PASS, " total state presence is : " + storeNumList.get(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
