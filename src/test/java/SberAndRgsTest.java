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
//        работает и так и так
//        User user = new User("Жуков", "Иван",
//                "Сергеевич", "(914) 145-05-43", "qwertyqwerty");

        RgsMainPage rgsMainPage = new RgsMainPage();
        RgsDmsPage rgsDmsPage = rgsMainPage.openDmsPage();
        rgsDmsPage.checkTitle();

        rgsDmsPage.clickSendFormButton();
        rgsDmsPage.checkTextAvailability();
        rgsDmsPage.fillInTheForm(user);
        rgsDmsPage.sendForm();
        rgsDmsPage.checkEmailError();

        Init.tearDown();
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

    @Test
    public void userTest() {
        System.out.println(User.getBirthRandomDate());

    }

    @After
    public void close() {
        Init.tearDown();
    }
}
