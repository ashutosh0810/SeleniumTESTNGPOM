package lumaProject.Tests;

import Utility.Util;
import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pomPages.*;
// this will cover multiple login test , wrong password error test , Forgot password test , Create account testing, Login testing and adding address testing  5 -->

public class AddAddressTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(LoginpageTest.class);
    private Loginpage loginpage;
    private LogoutPage logoutPage;
    private AccountCreatePage accountCreatePage;
    private MyaccountPage myaccountPage;
    private AddnewAddressPage addnewAddressPage;

    @BeforeMethod
    public void beforeMethod() {
        extentTest = extentReports.createTest(" Testing the Add Address  functionalities ");
        loginpage = createPage(Loginpage.class);
        logoutPage = createPage(LogoutPage.class);
        accountCreatePage = createPage(AccountCreatePage.class);
        myaccountPage = createPage(MyaccountPage.class);
        addnewAddressPage = createPage(AddnewAddressPage.class);
    }

    @Test(groups = {"Regression"})
    public void dummyTest() {
        System.out.println(" Testing  Dummy ");
        Assert.assertEquals("Testing", "Testing");
    }

    @Test(groups = {"Regression"})
    public void addAddressTest() {
        // Sign in
        extentTest = extentReports.createTest("Add Address Testing");
        logger.info(" User click on Sign in link  ");
        loginpage.clicksignInLink();
        extentTest.info(" user clicked on sign in link ");
        logger.info(" User Enter  Credentials ");
        loginpage.enterEmail(Util.getData("masterUsername"));
        loginpage.enterPwd(Util.getData("masterPassword"));
        logger.info(" User click on sign in Button ");
        loginpage.click_SignInBtn();
        extentTest.info(" Sign in successfull");
        // Navaigate to my account
        myaccountPage.click_myActpanelBtn();
        logger.info(" User click on My account right side  link  ");
        myaccountPage.click_myActrightLink();
        Assert.assertEquals(myaccountPage.get_myaccountTxt(), "My Account");
        extentTest.pass(" My account page dispalyed ");
        myaccountPage.get_myaccountTxt();
        logger.info(" User click on manage address ");
        myaccountPage.click_manageAddressLink();
        extentTest.info(" User click on manage address link  succesfull");
        //Address page
        if (addnewAddressPage.check_addNewAddresBtn()) {
            logger.info(" Address already existing ");
            logger.info(" User click the new address button ");
            addnewAddressPage.click_AddNewAddrBtn();
            extentTest.info(" User click the new address button  success");
            Assert.assertEquals(addnewAddressPage.get_addAddressTitle(), "Add New Address");
            extentTest.pass(" Add New Address page displayed");
            addnewAddressPage.enter_Address();
            addnewAddressPage.select_StateDrpDown();
            extentTest.info("  User entered the address deatails  ");
            addnewAddressPage.click_saveAddrBtn();
            //You saved the address.
            Assert.assertEquals(driver.getTitle(), "Address Book");
            Assert.assertEquals(addnewAddressPage.get_successAddresstext(), "You saved the address.");
            extentTest.pass("  New address added  successfully  " + addnewAddressPage.get_successAddresstext());
            logger.info("User click logout icon ");
            logoutPage.logout_btn();
            logoutPage.signout_link();
        } else {
            addnewAddressPage.enter_Address();
            addnewAddressPage.select_StateDrpDown();
            addnewAddressPage.click_saveAddrBtn();
            //You saved the address.
            Assert.assertEquals(driver.getTitle(), "Address Book");
            Assert.assertEquals(addnewAddressPage.get_successAddresstext(), "You saved the address.");
            extentTest.pass("  New address added  successfully  ");
            logger.info("User click logout icon ");
            logoutPage.logout_btn();
            logoutPage.signout_link();
        }
    }














/*  //  @Test(dataProvider = "userData")
    public void test_ValidLogin(String userName, String pwd) {
        SoftAssert softAssert = new SoftAssert();
        extentTest = extentReports.createTest(" Login with multiple user testing ");
        extentTest.info(" Clicking on sign in link");
        loginpage.clicksignInLink();
        Assert.assertTrue(loginpage.get_loginpageText().equalsIgnoreCase("Customer Login"), " Login page displayed");
        logger.info(" User Enter Username from excel ");
        loginpage.enterEmail(userName);
        logger.info(" User Enter password from excel ");
        loginpage.enterPwd(pwd);
        logger.info(" User click Sign in button  ");
        loginpage.click_SignInBtn();
        logger.info(" User Validaing page title  ");
        softAssert.assertEquals(driver.getTitle(),"Home Page");
        //Assert.assertEquals(driver.getTitle(), "Home Page");
        softAssert.assertAll();
        logger.info("User click logout icon ");
        logoutPage.logout_btn();
        logoutPage.signout_link();
        //You are signed out
        if (logoutPage.logout_text().equalsIgnoreCase("You are signed out")) {
            logger.info(" Logout successfull");
            extentTest.pass(" Logout successfull ");
        }
        logoutPage.waittoBeOnHomePage();
    }*/

  /* // @Test
    public void forgetPassword() {
        extentTest = extentReports.createTest("forget password  Testing");
        logger.info(" User click on Sign in link  ");
        loginpage.clicksignInLink();
        logger.info(" User click on forget password link  ");
        loginpage.click_Forgetpwd();
        logger.info(" User enters password on the forget password page  ");
        loginpage.enter_EmailforFogetpwd();
        logger.info(" User click  reset password button ");
        loginpage.click_ResetPwdBtn();
        Assert.assertEquals(driver.getTitle(), "Forgot Your Password?");
        extentTest.pass(" Forget password page displayed");
    }
*/
/*    //@Test
    public void validateWrongCredentials() {
        extentTest = extentReports.createTest(" Vaidate wrong credential testing");
        logger.info(" User click on Sign in link  ");
        loginpage.clicksignInLink();
        logger.info(" User Enter wrong Credentials ");
        loginpage.enterEmail(Util.getData("masterUsername"));
        loginpage.enterPwd(Util.generateRandomStringData(3));
        logger.info(" User click on sign in Button ");
        loginpage.click_SignInBtn();
        String exp = "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.";
        Assert.assertEquals(loginpage.get_wrongPwderrorTxt(), exp);
    }*/

  /* // @Test
    public void createAccount() {
        extentTest = extentReports.createTest(" Create account Testing");
        logger.info(" Starting create account happy path flow ");
        accountCreatePage.click_createActLink();
        Assert.assertEquals(accountCreatePage.get_createNewActTxt(), "Create New Customer Account");
        Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
        extentTest.pass(" Account create page displayed ");
        accountCreatePage.enter_FirstName();
        accountCreatePage.enter_LastName();
        accountCreatePage.enter_email();
        accountCreatePage.enter_password();
        try {
            accountCreatePage.click_createActBtn();
            logger.info(" accountCreatePage.click_createActBtn() successfull ");
        } catch (Exception e) {
            logger.info(e.getLocalizedMessage());
        }
        Assert.assertEquals(accountCreatePage.get_actCreateSuccess(), "Thank you for registering with Main Website Store.");
        extentTest.pass(" Account created successfully  ");
        logger.info("User click logout icon ");
        logoutPage.logout_btn();
        logoutPage.signout_link();
    }*/
}
