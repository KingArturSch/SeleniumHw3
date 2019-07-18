import core.Init;
import org.junit.After;
import org.junit.Test;
import rgsPages.RgsDmsPage;
import rgsPages.RgsMainPage;

public class RgsTest {

    @Test
    public void rgsTest() {
        Init.setUp("https://rgs.ru");
        RgsMainPage rgsMainPage = new RgsMainPage();
        RgsDmsPage rgsDmsPage = rgsMainPage.openDmsPage();
        rgsDmsPage.checkTitle();
        rgsDmsPage.clickSendFormButton();
        rgsDmsPage.checkTextAvailability();
        rgsDmsPage.fillInTheForm();
        rgsDmsPage.sendForm();
        rgsDmsPage.checkEmailError();

    }

    @After
    public void close() {
        Init.tearDown();
    }
}
