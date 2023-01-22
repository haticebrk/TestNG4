package TestNGProjee.proje8.Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseDriverParameter {


    public  WebDriver driver;  // paralel calisirken iki browser ayni anda driver aldigi icin hangi browser icin olacagini bilemiyor
    //kafasi karisiyor o yuzden static bir tane dedigi icin static sildik
    public static WebDriverWait wait;

    @BeforeClass
    @Parameters("browser")
    public void baslangicIslemleri(String browser) {

        Logger logger = Logger.getLogger("");
        logger.setLevel(Level.SEVERE);

        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
            System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
            driver = new ChromeDriver();

        } else if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
            System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        Duration dr = Duration.ofSeconds(30);
        driver.manage().timeouts().pageLoadTimeout(dr);


        driver.manage().timeouts().implicitlyWait(dr);// butun webelementlerin element bazinda elemente ozel islem yapilmadamn once
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       // LoginTest();

    }


    void LoginTest() {

        driver.get("https://opencart.abstracta.us/index.php?route=account/login");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='email']")));
        WebElement email=driver.findElement(By.cssSelector("input[name='email']"));
        email.sendKeys("yildizhilal641@gmail.com");

        WebElement password=driver.findElement(By.cssSelector("input[name='password']"));
        password.sendKeys("12345");

        WebElement loginBtn=driver.findElement(By.cssSelector("input[type='submit']"));
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
        loginBtn.click();
    }

    @AfterClass
    public void bitisIslemleri() {
        Tools.Bekle(10);
        driver.quit();

    }
}
