package lumaProject.Tests;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pomPages.WhatsNewPage;

public class WhatsNewTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(WhatsNewTest.class);
    private WhatsNewPage whatsNewPage;

    @BeforeMethod
    public void beforeMethod() {
        extentTest = extentReports.createTest(" Testing the Whatis New in the AUT ");
        whatsNewPage = createPage(WhatsNewPage.class);
    }

    @Test(groups = {"Sanity"})
    public void validateWhatsNew() {
        extentTest.info(" Validating the what is new in AUT ");
        logger.info(" User click on the whatNewLink");
        whatsNewPage.click_whatNewLink();
        whatsNewPage.click_womenHodieLink();
        logger.info(" Validating the number of new Hoddies in AUT ");
        Assert.assertEquals(whatsNewPage.get_noofNewhoodieTxt(), "15 Items");
    }
}
