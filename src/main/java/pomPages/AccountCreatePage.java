package pomPages;

import Utility.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class AccountCreatePage {
    WebDriver driver;
    private static final Logger logger = LogManager.getLogger(AccountCreatePage.class);

    public AccountCreatePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = "//div[@class='panel header']//a[normalize-space()='Create an Account']")
    private WebElement createAnAccount_link;
    @FindBy(how = How.XPATH, using = "//span[text()=\"Create New Customer Account\"]")
    private WebElement createAnAccount_text;
    @FindBy(how = How.ID, using = "firstname")
    private WebElement firstName_txtBox;
    @FindBy(how = How.ID, using = "lastname")
    private WebElement lastName_txtBox;
    @FindBy(how = How.ID, using = "email_address")
    private WebElement email_txtbox;
    @FindBy(how = How.ID, using = "password")
    private WebElement pwd_txtBox;
    @FindBy(how = How.ID, using = "password-confirmation")
    private WebElement cnfrmPwd_txtBox;
    @FindBy(how = How.XPATH, using = "//button[@title='Create an Account']//span[contains(text(),'Create an Account')]")
    private WebElement submit_btn;
    @FindBy(how = How.XPATH, using = "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")
    private WebElement success_txt;

    public void click_createActLink() {
        Util.pageLoad_wait(driver);
        Util.waitForElementToBeVisible(driver, createAnAccount_link, Util.duration());
        logger.info(" User click on create account link ");
        createAnAccount_link.click();
    }

    public void enter_FirstName() {
        Util.waitForElementToBeVisible(driver, lastName_txtBox, Util.duration());
        firstName_txtBox.clear();
        logger.info(" User enter the first name  ");
        firstName_txtBox.sendKeys(Util.generateRandomStringData(12));
    }

    public void enter_LastName() {
        logger.info(" User enter the last name  ");
        lastName_txtBox.clear();
        lastName_txtBox.sendKeys(Util.generateRandomStringData(8));
    }

    public void enter_email() {
        logger.info(" User enter the email for the registration  ");
        email_txtbox.clear();
        email_txtbox.sendKeys(Util.generateRandomStringData(9) + "@gmail.com");
    }

    public void enter_password() {
        logger.info(" User enter the password for the registration  ");
        pwd_txtBox.clear();
        String password = Util.generateRandomStringData(9);
        pwd_txtBox.sendKeys(password);
        logger.info(" User enter the confirm password  for the registration  ");
        cnfrmPwd_txtBox.clear();
        cnfrmPwd_txtBox.sendKeys(password);
    }

    public void click_createActBtn() {
        logger.info(" User click the create account button  ");
        Util.waitForElementToBeClickable(driver, submit_btn, Duration.ofSeconds(20));
        submit_btn.click();
        logger.info(" submit_btn click successfull  ");
    }

    public String get_createNewActTxt() {
        Util.pageLoad_wait(driver);
        return createAnAccount_text.getText();
    }

    public String get_actCreateSuccess() {
        Util.pageLoad_wait(driver);
        Util.waitForElementToBeVisible(driver, success_txt, Util.duration());
        return success_txt.getText();
    }
}
