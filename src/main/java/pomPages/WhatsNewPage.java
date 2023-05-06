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

public class WhatsNewPage {
    private WebDriver driver;
    private static final Logger logger = LogManager.getLogger(WhatsNewPage.class);

    public WhatsNewPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = ".//*[text()=\"What's New\"]")
    private WebElement whatnewLink;
    @FindBy(how = How.XPATH, using = "//main[@id='maincontent']//ul[1]//li[1]//a[1]")
    private WebElement womenshodiesSwtShirtLink;
    @FindBy(how = How.XPATH, using = "//div[@class='page-wrapper']//div[2]//p[1]")
    private WebElement numberItmTxt;



    public void click_whatNewLink()
    {
        Util.pageLoad_wait(driver);
        Util.waitForElementToBeVisible(driver,whatnewLink, Util.duration());
        whatnewLink.click();

    }

    public void click_womenHodieLink()
    {
        Util.pageLoad_wait(driver);
        Util.waitForElementToBeVisible(driver,womenshodiesSwtShirtLink,Util.duration());
        womenshodiesSwtShirtLink.click();
    }

    public String get_noofNewhoodieTxt()
    {
        Util.pageLoad_wait(driver);
        Util.waitForElementToBeVisible(driver,numberItmTxt,Util.duration());
        return numberItmTxt.getText();
    }
}
