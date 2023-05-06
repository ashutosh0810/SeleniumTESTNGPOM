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

public class HomePage {
    private WebDriver driver;
    private static final Logger logger = LogManager.getLogger(HomePage.class);

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = "//a[@aria-label='store logo']//img")
    private WebElement homeLogoImg;
    /* @FindBy(how = How.XPATH, using = "//a[@id='ui-id-3']//span[1]")
     private WebElement whatNewLink;*/
    @FindBy(how = How.XPATH, using = "//a[normalize-space()='About us']")
    private WebElement aboutUsLink;
    @FindBy(how = How.XPATH, using = "//p[@class='cms-content-important']")
    private WebElement aboutUsMsgTxt;
    @FindBy(how = How.XPATH, using = "//a[normalize-space()='Search Terms']")
    private WebElement searchTermLnk;
    @FindBy(how = How.XPATH, using = "//ul[@class='search-terms']")
    private WebElement searchTermTxt;

    public void click_homelogoImg() {
        Util.pageLoad_wait(driver);
        Util.waitForElementToBeVisible(driver, homeLogoImg, Util.duration());
        logger.info(" User clicking on home logo ");
        homeLogoImg.click();
        Util.pageLoad_wait(driver);
    }

    public void click_aboutUsLink() {
        Util.pageLoad_wait(driver);
        Util.waitForElementToBeVisible(driver, aboutUsLink, Util.duration());
        logger.info(" User clicking on About us logo  ");
        aboutUsLink.click();
    }

    public String get_aboutUsMsg() {
        Util.pageLoad_wait(driver);
        // Util.waitForElementToBeVisible(driver, aboutUsMsgTxt, Duration.ofSeconds(30));
        logger.info(" User read the about us description ");
        try {
            String txt = aboutUsMsgTxt.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aboutUsMsgTxt.getText();
    }

    public void click_searchTerm() {
        Util.pageLoad_wait(driver);
        Util.waitForElementToBeVisible(driver, searchTermLnk, Util.duration());
        logger.info(" User click on the serachTermLink ");
        searchTermLnk.click();
    }

    public String get_searchtermTxt() {
        Util.pageLoad_wait(driver);
        Util.waitForElementToBeVisible(driver, searchTermTxt, Util.duration());
        logger.info(" User reads on the searchTerm description  " + searchTermTxt.getText());
        return searchTermTxt.getText();
    }
}



