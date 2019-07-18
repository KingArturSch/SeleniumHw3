import core.Init;
import core.User;
import org.junit.After;
import org.junit.Test;
import rgsPages.RgsDmsPage;
import rgsPages.RgsMainPage;
import sberPages.SberMainPage;
import sberPages.SberTravelInsuracncePage;

public class SberAndRgsTest {

    @Test
    public void rgsTest() {
        Init.setUp(RgsMainPage.RGS_URL);
        User user = User.getRandomUserForRgs();
        RgsMainPage rgsMainPage = new RgsMainPage();
        RgsDmsPage rgsDmsPage = rgsMainPage.openDmsPage();
        rgsDmsPage.checkTitle();
        rgsDmsPage.clickSendFormButton();
        rgsDmsPage.checkTextAvailability();
        rgsDmsPage.fillInTheForm(user);
        rgsDmsPage.sendForm();
        rgsDmsPage.checkEmailError();
    }

    @Test
    public void sberTest() {
        Init.setUp(SberMainPage.SBER_URL);
        User insuredUser = User.getRandomInsuredUserForSber();
        User insurantUser = User.getRandomInsurantUserForSber();
        SberMainPage sberMainPage = new SberMainPage();
        SberTravelInsuracncePage sberTravelInsuracncePage = sberMainPage.openSberTravelAndShoppingPage().openFormInsurance();
        sberTravelInsuracncePage.setMinimalClickAndNext();
        sberTravelInsuracncePage.fillInTheFormInsuredUser(insuredUser);
        sberTravelInsuracncePage.fillInTheFormInsurantUser(insurantUser);
        sberTravelInsuracncePage.sendForm();
        sberTravelInsuracncePage.checkMessage();
    }


    @After
    public void close() {
        Init.tearDown();
    }
}
