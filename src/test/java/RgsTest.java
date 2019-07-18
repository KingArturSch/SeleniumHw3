import core.Init;
import core.User;
import org.junit.After;
import org.junit.Test;
import rgsPages.RgsDmsPage;
import rgsPages.RgsMainPage;

public class RgsTest {

    @Test
    public void rgsTest() {
        Init.setUp("https://rgs.ru");
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

    }


    @Test
    public void userTest() {
        User user = User.getRandomUserForRgs();
        System.out.println(user.getTelephoneNumber());

    }

    @After
    public void close() {
        Init.tearDown();
    }
}
