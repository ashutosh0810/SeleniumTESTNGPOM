package pomPages;

import Utility.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class AddnewAddressPage {
    private static final Logger logger = LogManager.getLogger(AddnewAddressPage.class);
    private WebDriver driver;

    public AddnewAddressPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = "//span[@class='base']")
    private WebElement addNewAddressTitle;
    //Add New Address
    @FindBy(how = How.XPATH, using = "//input[@id='street_1']")
    private WebElement streetAdd1TxtBox;
    @FindBy(how = How.XPATH, using = "//input[@id='street_2']")
    private WebElement streetAdd2TxtBox;
    @FindBy(how = How.XPATH, using = "//input[@id='city']")
    private WebElement cityTxtBox;
    @FindBy(how = How.XPATH, using = "//input[@id='telephone']")
    private WebElement phnNumTxtBox;
    @FindBy(how = How.XPATH, using = "//select[@id='region_id']")
    private WebElement stateDrpDwn;
    @FindBy(how = How.XPATH, using = "//input[@id='zip']")
    private WebElement zipTxtBox;
    @FindBy(how = How.XPATH, using = "//span[normalize-space()='Save Address']")
    private WebElement saveAddrButton;
    @FindBy(how = How.XPATH, using = "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")
    private WebElement addrSaveSuccessTxt;
    //
    @FindBy(how = How.XPATH, using = "//span[normalize-space()='Add New Address']")
    private WebElement addNewAddrBtn;

    public String get_addAddressTitle() {
        Util.pageLoad_wait(driver);
        Util.waitForElementToBeVisible(driver, addNewAddressTitle, Util.duration());
        //Add New Address
        return addNewAddressTitle.getText();
    }

    public void enter_Address() {
        Util.pageLoad_wait(driver);
        Util.waitForElementToBeVisible(driver, zipTxtBox, Util.duration());
        streetAdd1TxtBox.clear();
        streetAdd1TxtBox.sendKeys(Util.generateRandomStringData(6));
        streetAdd2TxtBox.clear();
        streetAdd2TxtBox.sendKeys(Util.generateRandomStringData(4));
        cityTxtBox.clear();
        cityTxtBox.sendKeys(Util.generateRandomStringData(3));
        phnNumTxtBox.clear();
        phnNumTxtBox.sendKeys(Util.generateRandomNumber(10));
        zipTxtBox.clear();
        zipTxtBox.sendKeys(Util.generateRandomNumber(5));
    }

    public void select_StateDrpDown() {
        Util.waitForElementToBeVisible(driver, stateDrpDwn, Util.duration());
        Select dropdown = new Select(stateDrpDwn);
        dropdown.selectByIndex(3);
    }

    public void click_saveAddrBtn() {
        Util.waitForElementToBeVisible(driver, saveAddrButton, Util.duration());
        saveAddrButton.click();
        Util.pageLoad_wait(driver);
    }

    public String get_successAddresstext() {
        Util.pageLoad_wait(driver);
        try {
            Util.waitForElementToBeVisible(driver, addrSaveSuccessTxt, Util.duration());
        } catch (Exception e) {
            logger.info(e.getLocalizedMessage());
        }
        return addrSaveSuccessTxt.getText();
        // title Address Book
        //You saved the address.
    }

    public boolean check_addNewAddresBtn() {
        try {
            if (addNewAddrBtn.isDisplayed()) {
                return true;
            }
        } catch (Exception e) {
            logger.debug(e.getLocalizedMessage());
        }
        return false;
    }

    public void click_AddNewAddrBtn() {
        Util.waitForElementToBeClickable(driver, addNewAddrBtn, Util.duration());
        addNewAddrBtn.click();
    }
}
