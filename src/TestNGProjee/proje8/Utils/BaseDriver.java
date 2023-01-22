package TestNGProjee.proje8.Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseDriver {


    public static WebDriver driver;
    public static WebDriverWait wait;
    @BeforeClass(groups = "SmokeTest")
   public void baslangicIslemleri(){

        Logger logger = Logger.getLogger("");// butun loglara ulastimm .
        logger.setLevel(Level.SEVERE);  //Sadece errorlari goster
        System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");//chromo loglarini rundan sildik


        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies(); // sitenin senin bilgisayirinda yaptigi ayarlar siliniyor .sayfa baslangic ayarlarina donuyor

        Duration dr = Duration.ofSeconds(30);//sadece tum sayfanihn kodlarinin bilgisayartiniza inmesi suresiyleilgili
        driver.manage().timeouts().pageLoadTimeout(dr); // bu eklenmezse seleniuym sayfa yuklenene kadar (sonsuza) bekler.


        driver.manage().timeouts().implicitlyWait(dr);// butun webelementlerin element bazinda elemente ozel islem yapilmadamn once

        wait=new WebDriverWait(driver, Duration.ofSeconds(10));

            // LoginTest();

    }

//    void LoginTest(){
//        driver.get("https://opencart.abstracta.us/index.php?route=account/login");
//
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='email']")));
//        WebElement email=driver.findElement(By.cssSelector("input[name='email']"));
//        email.sendKeys("yildizhilal641@gmail.com");
//
//        WebElement password=driver.findElement(By.cssSelector("input[name='password']"));
//        password.sendKeys("12345");
//
//        WebElement loginBtn=driver.findElement(By.cssSelector("input[type='submit']"));
//        wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
//        loginBtn.click();
//
//    }

    @AfterClass(groups = "SmokeTest")
  public  void bitisIslemleri(){
        Tools.Bekle(10);
        driver.quit();

    }
}
