package pomPages;

import Utility.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class Loginpage {
    WebDriver driver;

    public Loginpage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = ".//div[@class='panel header']//a[contains(text(),'Sign In')]")
    private WebElement signIn_link;
    @FindBy(how = How.XPATH, using = ".//*[@id='email']")
    private WebElement textBox_userName;
    @FindBy(how = How.XPATH, using = ".//*[@id='pass']")
    private WebElement textBox_password;
    @FindBy(how = How.XPATH, using = ".//*[@type=\"submit\" and @class=\"action login primary\"]")
    private WebElement signIn_button;
    @FindBy(how = How.XPATH, using = "//span[@class='base']")
    private WebElement customerLogin_txt;
    @FindBy(how = How.XPATH, using = "//a[@class='action remind']//span[contains(text(),'Forgot Your Password?')]")
    private WebElement forgetPwd_link;
    @FindBy(how = How.XPATH, using = "//span[@class='base']")
    private WebElement forgetPwd_txt;
    @FindBy(how = How.XPATH, using = "//input[@id='email_address']")
    private WebElement forgetPwdEmail_txtBox;
    @FindBy(how = How.XPATH, using = "//span[normalize-space()='Reload captcha']")
    private WebElement captcha_txt;
    // This is for the reset password
    @FindBy(how = How.XPATH, using = "//span[normalize-space()='Reset My Password']")
    private WebElement resetPwd_link;
    @FindBy(how = How.XPATH, using = "//div[@id='captcha_user_forgotpassword-error']")
    private WebElement forgetPwdError_txt;
    @FindBy(how = How.XPATH, using = "//div[@id='email-error']")
    private WebElement wrongEmail_txt;
    @FindBy(how = How.XPATH, using = "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")
    private WebElement wrongPwdError_txt;

    public void clicksignInLink() {
        Util.pageLoad_wait(driver);
        Util.waitForElementToBeVisible(driver, signIn_link, Util.duration());
        signIn_link.click();
    }

    public void enterPwd(String password) {
        Util.waitForElementToBeVisible(driver, textBox_password, Util.duration());
        textBox_password.clear();
        textBox_password.sendKeys(password);
    }

    public void enterEmail(String email) {
        Util.waitForElementToBeVisible(driver, textBox_userName, Util.duration());
        textBox_userName.clear();
        textBox_userName.sendKeys(email);
    }

    public void click_SignInBtn() {
        Util.waitForElementToBeVisible(driver, signIn_button, Util.duration());
        Util.pageLoad_wait(driver);
        signIn_button.click();
        driver.navigate().refresh();
        Util.pageLoad_wait(driver);
    }

    public String get_loginpageText() {
        return customerLogin_txt.getText();
    }

    public String get_wrongEmailTxt() {
        Util.pageLoad_wait(driver);
        Util.waitForElementToBeVisible(driver, wrongEmail_txt, Util.duration());
        return wrongEmail_txt.getText();
    }

    public void enter_EmailforFogetpwd() {
        Util.pageLoad_wait(driver);
        Util.waitForElementToBePresent(driver, By.xpath("//input[@id='email_address']"), Duration.ofSeconds(30));
        forgetPwdEmail_txtBox.sendKeys(Util.generateRandomStringData(4) + "@yeh.com");
    }

    public void click_Forgetpwd() {
        Util.pageLoad_wait(driver);
        forgetPwd_link.click();
    }

    public String get_forgetPwdTxt() {
        return forgetPwd_txt.getText();
    }

    public void click_ResetPwdBtn() {
        Util.pageLoad_wait(driver);
        Util.waitForElementToBeVisible(driver, resetPwd_link, Util.duration());
        resetPwd_link.click();
    }

    public void get_forgetPwdError() {
        Util.pageLoad_wait(driver);
        Util.waitForElementToBeVisible(driver, forgetPwdError_txt, Util.duration());
    }

    public String get_wrongPwderrorTxt() {
        return wrongPwdError_txt.getText();
    }
}
