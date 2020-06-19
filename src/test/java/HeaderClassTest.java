import Pages.Header.Header;
import Pages.Home.Home;
import Utils.Browser;
import Utils.Global;
import Utils.Popup;
import io.qameta.allure.Attachment;
import io.qameta.allure.Owner;
import org.junit.*;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.*;

import java.util.concurrent.TimeUnit;

public class HeaderClassTest {
    public static WebDriver driver;
    public Header header;
    public Popup popup;
    public Home main;
    private Browser browserClose;
    public Global global;

    @BeforeClass
    public static void setup() {
        driver = new Browser(driver).Chrome();
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
    }

    @Before
    public void beforeClassMethod() {
        driver.get("https://dostavka.auchan.ru/");
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        header = new Header(driver);
        popup = new Popup(driver);
        main = new Home(driver);
        global = new Global(driver);
        browserClose = new Browser(driver);
        popup.clickClosePopup();
        popup.clickCloseIAgree();
    }

    //////////////////Скриншоты при ошибках//////////////////////
    @Rule
    public TestWatcher screenshotOnFailure = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            makeScreenshotOnFailure();
        }

        @Attachment("Screenshot on failure")
        public byte[] makeScreenshotOnFailure() {
            try {
                return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            } catch (WebDriverException e) {
                e.printStackTrace();
            }
            return new byte[0];
        }
    };

    //////////////////////////Хедер////////////////////////////////
    @Test
    @Owner(value = "Большакова Полина Денисовна")
    public void HeaderBasket() {
        header.Basket();
    }

    @Test
    @Owner(value = "Большакова Полина Денисовна")
    public void HeaderLK() {
        header.LK();
    }

    @Test
    @Owner(value = "Большакова Полина Денисовна")
    public void HeaderLogo() {
        header.Logo();
    }

    @Test
    @Owner(value = "Большакова Полина Денисовна")
    public void HeaderSearct() throws InterruptedException {
        header.Searct();
    }

    @Test
    @Owner(value = "Большакова Полина Денисовна")
    public void HeaderDeliveryAddress() {
        header.DeliveryAddress();
    }

    @Test
    @Owner(value = "Большакова Полина Денисовна")
    public void HeadermoveCatalog() throws InterruptedException {
        header.moveCatalog();
    }


    @AfterClass
    public static void tearDownClass() {
        driver.quit();
    }

}