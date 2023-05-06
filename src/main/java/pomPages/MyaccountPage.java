package pomPages;

import Utility.Util;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class MyaccountPage {
    private WebDriver driver;

    public MyaccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = "//span[@class='base']")
    private WebElement myActTitleTxt;
    @FindBy(how = How.XPATH, using = "//span[normalize-space()='Manage Addresses']")
    private WebElement manageAddressLink;
    @FindBy(how = How.XPATH, using = "//div[@class='panel header']//button[@type='button']")
    private WebElement myActPanelBtn;
    @FindBy(how = How.XPATH, using = "//div[@aria-hidden='false']//a[normalize-space()='My Account']")
    private WebElement myActrightLink;

    public void click_myActpanelBtn() {
        Util.pageLoad_wait(driver);
        Util.waitForElementToBeVisible(driver,myActPanelBtn,Util.duration());
        myActPanelBtn.click();
    }

    public void click_myActrightLink() {
        Util.pageLoad_wait(driver);
        Util.waitForElementToBeVisible(driver,myActrightLink,Util.duration());
        myActrightLink.click();
    }

    public void click_manageAddressLink() {
        Util.pageLoad_wait(driver);
        Util.waitForElementToBeVisible(driver, manageAddressLink, Util.duration());
        manageAddressLink.click();
    }

    public String get_myaccountTxt() {
        return myActTitleTxt.getText();
    }
}


