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
import java.time.LocalTime;

public class LogoutPage {
    WebDriver driver;
    private static final Logger logger = LogManager.getLogger(LogoutPage.class);

    public LogoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = "//div[@class='panel header']//button[@type='button']")
    private WebElement logout_btn;
    @FindBy(how = How.XPATH, using = "//div[@aria-hidden='false']//a[normalize-space()='Sign Out']")
    private WebElement sigput_link;
    @FindBy(how = How.XPATH, using = "//span[@class='base']")
    private WebElement signout_text;

    public void logout_btn() {
        Util.waitForPageToLoad(driver, Duration.ofSeconds(30));
        Util.waitForElementToBeClickable(driver, logout_btn, Duration.ofSeconds(30));
        Util.waitForElementToBeVisible(driver, logout_btn, Duration.ofSeconds(30));
        logout_btn.click();
    }

    public void signout_link() {
        Util.waitForPageToLoad(driver, Duration.ofSeconds(30));
        Util.waitForElementToBeClickable(driver, logout_btn, Duration.ofSeconds(30));
        Util.waitForElementToBeVisible(driver, logout_btn, Duration.ofSeconds(30));
        sigput_link.click();
        logger.info(" User clicked on sign out button ");
    }

    public String logout_text() {
        return signout_text.getText();
    }

    public void waittoBeOnHomePage() {
        Util.waitForPageToLoad(driver, Duration.ofSeconds(30));
        logger.info(" Waiting the logout page to be on the landing page ");
        LocalTime time1 = LocalTime.now();
        System.out.println(" >>>>>>>>> waiting for page to be in login page again" + time1);
        try {
            Util.waitForElementToBeInVisible(driver, signout_text, Util.duration());
        } catch (Exception e) {
            e.printStackTrace();
        }
        LocalTime time2 = LocalTime.now();
        logger.info(" Waiting the logout page to be on the landing page  Success");
    }
}
