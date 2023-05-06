package lumaProject.Tests;

import Utility.ExcelUtils;
import Utility.Util;
import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pomPages.AccountCreatePage;
import pomPages.LogoutPage;

import java.io.IOException;

public class CreateAccountTest extends BaseTest {
    private AccountCreatePage accountCreatePage;
    private static final Logger logger = LogManager.getLogger(CreateAccountTest.class);
    private LogoutPage logoutPage;

    @BeforeMethod
    public void beforeMethod() {
        extentTest = extentReports.createTest(" Create account testing");
        accountCreatePage = new AccountCreatePage(driver);
        logoutPage= new LogoutPage(driver);
    }

     @Test(groups = {"Regression"})
    public void createAccount() {
        extentTest = extentReports.createTest(" Create account Testing");
        logger.info(" Starting create account happy path flow ");
        extentTest.info(" User click on create account link ");
        accountCreatePage.click_createActLink();
        extentTest.info(" User click on create account link ");
        Assert.assertEquals(accountCreatePage.get_createNewActTxt(), "Create New Customer Account");
        Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
        extentTest.pass(" Account create page displayed ");
        accountCreatePage.enter_FirstName();
         extentTest.info(" User enter first name ");
        accountCreatePage.enter_LastName();
        extentTest.info(" User entered last name ");
        accountCreatePage.enter_email();
         extentTest.info(" User entered  email  ");
        accountCreatePage.enter_password();
         extentTest.info(" User entered password");
        try {
            accountCreatePage.click_createActBtn();
            logger.info(" accountCreatePage.click_createActBtn() successfull ");
            extentTest.pass(" Creaete acccount button clicked successfully ");
        } catch (Exception e) {
            logger.info(e.getLocalizedMessage());
        }
        Assert.assertEquals(accountCreatePage.get_actCreateSuccess(), "Thank you for registering with Main Website Store.");
        extentTest.pass(" Account created successfully  ");
        logger.info("User click logout icon ");
        logoutPage.logout_btn();
        logoutPage.signout_link();
        extentTest.info(" createAccount method clicked on logout");
    }


}


