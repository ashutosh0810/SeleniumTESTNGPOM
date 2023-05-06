package lumaProject.Tests;

import Utility.ExcelUtils;
import Utility.Util;
import base.BaseTest;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pomPages.HomePage;
import pomPages.Loginpage;
import pomPages.LogoutPage;

import java.io.IOException;

public class LoginpageTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(LoginpageTest.class);
    private Loginpage loginpage;
    private LogoutPage logoutPage;
    private HomePage homePage;

    @BeforeMethod
    public void beforeMethod() {
        extentTest = extentReports.createTest(" Login Functionalities");
        loginpage = createPage(Loginpage.class);
        logoutPage = createPage(LogoutPage.class);
        homePage = createPage(HomePage.class);
    }

    @DataProvider(name = "userData")
    public Object[][] getUserData() throws IOException {
        String sheetName = Util.getData("sheetName");
        return ExcelUtils.readExcelData(sheetName);
    }

    @Test
    public void dummyTest() {
        System.out.println(" Testing  Dummy ");
        Assert.assertEquals("Testing", "Testing");
    }

    @Test(dataProvider = "userData" , groups = "Regression")
    public void test_ValidLogin(String userName, String pwd) {
        SoftAssert softAssert = new SoftAssert();
        extentTest = extentReports.createTest(" Login with multiple user testing ");
        extentTest.info(" Clicking on sign in link");
        //driver.get(Util.getData("testUrl"));

        loginpage.clicksignInLink();
        extentTest.log(Status.PASS, " sign in linked clicked successfully");
        Assert.assertTrue(loginpage.get_loginpageText().equalsIgnoreCase("Customer Login"), " Login page displayed");
        extentTest.pass(" login page displayed successfully");
        logger.info(" User Enter Username from excel ");
        loginpage.enterEmail(userName);
        logger.info(" User Enter password from excel ");
        loginpage.enterPwd(pwd);
        logger.info(" User click Sign in button  ");
        loginpage.click_SignInBtn();
        extentTest.log(Status.PASS, " user clicked sign in button");
        homePage.click_homelogoImg();
        logger.info(" User Validaing page title  ");
        softAssert.assertEquals(driver.getTitle(), "Home Page");
        //Assert.assertEquals(driver.getTitle(), "Home Page");
        extentTest.pass(" Home page title successfull");
        //You are signed out
        try {
            logoutPage.logout_btn();
            logger.info("User clicked right side logout icon ");
            logoutPage.signout_link();
            logger.info("User clicked right side logout link  ");
            extentTest.info(" User signout ");
            if (logoutPage.logout_text().equalsIgnoreCase("You are signed out")) {
                logger.info(" Logout successfull by clicking right link ");
                extentTest.pass(" Logout successfull ");
            }
        } catch (Exception e) {
            logger.info(" Logout Unsuccessfull by clicking right link  RETRY ");
            homePage.click_homelogoImg();
            logoutPage.logout_btn();
            logger.info("User clicked right side logout icon ");
            logoutPage.signout_link();
            logger.info("User clicked right side logout link  ");
            extentTest.info(" User signout ");
            e.printStackTrace();
        }
        softAssert.assertAll();
        //  logoutPage.waittoBeOnHomePage();
    }

    @Test (groups = {"Sanity"})
    public void validateWrongCredentials() {
        extentTest = extentReports.createTest(" Vaidate wrong credential testing");
        logger.info(" User click on Sign in link  ");
        loginpage.clicksignInLink();
        extentTest.log(Status.PASS, " sign in linked clicked successfully");
        logger.info(" User Enter wrong Credentials ");
        loginpage.enterEmail(Util.getData("masterUsername"));
        loginpage.enterPwd(Util.generateRandomStringData(3));
        logger.info(" User click on sign in Button ");
        loginpage.click_SignInBtn();
        extentTest.pass(" Sign in button clicked ");
        String exp = "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.";
        Assert.assertEquals(loginpage.get_wrongPwderrorTxt(), exp);
    }

    @Test
    public void forgetPassword() {
        extentTest = extentReports.createTest("forget password  Testing");
        logger.info(" User click on Sign in link  ");
        loginpage.clicksignInLink();
        extentTest.pass("  User click on Sign in link Success ");
        logger.info(" User click on forget password link  ");
        loginpage.click_Forgetpwd();
        extentTest.pass("User click on forget password link successfull");
        logger.info(" User enters password on the forget password page  ");
        loginpage.enter_EmailforFogetpwd();
        logger.info(" User click  reset password button ");
        loginpage.click_ResetPwdBtn();
        Assert.assertEquals(driver.getTitle(), "Forgot Your Password?");
        extentTest.pass(" Forget password page displayed");
    }
}

