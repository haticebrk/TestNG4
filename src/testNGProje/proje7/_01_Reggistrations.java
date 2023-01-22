package testNGProje.proje7;

import Utils.BaseDriver;
import Utils.Tools;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class _01_Reggistrations extends BaseDriver {
    String email2;
    String password2;

    @Test(priority = 1)
    void Reggistrations() {

        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");

        email2 = RandomStringUtils.randomAlphabetic(6) + "@gmail.com";
        password2 = RandomStringUtils.randomAlphabetic(6);
//     //import org.apache.commons.lang3.RandomStringUtils;


        WebElement register = driver.findElement(By.id("register-button"));
        register.click();

        WebElement gender = driver.findElement(By.id("gender-female"));
        gender.click();

        WebElement firstName = driver.findElement(By.id("FirstName"));
        firstName.sendKeys("Ayse");
        WebElement lastName = driver.findElement(By.id("LastName"));
        lastName.sendKeys("Duman");


        WebElement dateOfBirdDay = driver.findElement(By.cssSelector("[name='DateOfBirthDay']"));
        Select dateofBirtDay1 = new Select(dateOfBirdDay);
        dateofBirtDay1.selectByValue("5");
        WebElement dateOfBirdMonth = driver.findElement(By.cssSelector("[name='DateOfBirthMonth']"));
        Select dateOfMonth1 = new Select(dateOfBirdMonth);
        dateOfMonth1.selectByValue("9");
        WebElement dateOfBirdYear = driver.findElement(By.cssSelector("[name='DateOfBirthYear']"));
        Select dateOfYear1 = new Select(dateOfBirdYear);
        dateOfYear1.selectByValue("1987");

        WebElement email = driver.findElement(By.id("Email"));
        email.sendKeys(email2);


        WebElement password = driver.findElement(By.id("Password"));
        password.sendKeys(password2);


        WebElement confirmpassword = driver.findElement(By.id("ConfirmPassword"));
        confirmpassword.sendKeys(password2);

        WebElement register2 = driver.findElement(By.id("register-button"));
        register2.click();

        WebElement validation = driver.findElement(By.cssSelector("[class='result']"));

        Assert.assertTrue(validation.getText().contains("Your registration completed"));

        WebElement logout = driver.findElement(By.linkText("Log out"));
        logout.click();
    }


    @Test(priority = 2, dependsOnMethods = {"Reggistrations"})
    void LoginTest() {

        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");

        WebElement login = driver.findElement(By.linkText("Log in"));
        login.click();
        WebElement email = driver.findElement(By.id("Email"));
        // email.sendKeys("TecnuStudy345@gmail.com");
        //  Tools.Bekle(2);
        WebElement password = driver.findElement(By.id("Password"));
        // password.sendKeys("A2345w");
        Actions actions = new Actions(driver);
        actions.moveToElement(email).click().sendKeys(email2).build().perform();
        actions.moveToElement(password).click().sendKeys(password2).build().perform();
        WebElement loginBtn = driver.findElement(By.xpath("(//button[@type='submit'])[2]"));
        loginBtn.click();
        WebElement validationlogin = driver.findElement(By.cssSelector("[class='ico-account']"));
        Assert.assertTrue(validationlogin.getText().contains("My account"));
        WebElement logout = driver.findElement(By.linkText("Log out"));
        logout.click();


    }


    @Test(priority = 3, dataProvider = "getData")
    void DataProviderLogin(String email1, String password1) {

        WebElement login = driver.findElement(By.linkText("Log in"));
        login.click();
        WebElement email = driver.findElement(By.id("Email"));
        // email.sendKeys("TecnuStudy345@gmail.com");


        WebElement password = driver.findElement(By.id("Password"));
        // password.sendKeys("A2345w");

        Actions actions = new Actions(driver);
        actions.moveToElement(email).click().sendKeys(email1).build().perform();

        actions.moveToElement(password).click().sendKeys(password1).build().perform();


        WebElement loginBtn = driver.findElement(By.xpath("(//button[@type='submit'])[2]"));
        loginBtn.click();

        if (!email1.contains(email2) || !password1.contains(password2)) {
            WebElement errorMsg = driver.findElement(By.xpath("//div[@class='message-error validation-summary-errors']"));
            Assert.assertTrue(errorMsg.getText().contains("Login was unsuccessful. Please correct the errors and try again."));
        } else {
            WebElement logOut = driver.findElement(By.linkText("Log out"));
            logOut.click();
        }

    }

    @DataProvider
    public Object[][] getData() {
        Object[][] logins = {
                {email2, password2},
                {"TecnuStudy046@gmail.com", "ewtyy789"},

        };

        return logins;
    }

    @Test(priority = 4, dependsOnMethods = {"LoginTest"})
    void TabMenuTest() {

        List<WebElement> tabMenuList = driver.findElements(By.xpath("//ul[@class='top-menu notmobile']/li/a"));

        List<String> menuExpectedList = new ArrayList<>();

        menuExpectedList.add("Computers");
        menuExpectedList.add("Electronics");
        menuExpectedList.add("Apparel");
        menuExpectedList.add("Digital downloads");
        menuExpectedList.add("Books");
        menuExpectedList.add("Jewelry");
        menuExpectedList.add("Gift Cards");

        for (int i = 0; i < menuExpectedList.size(); i++) {

            Assert.assertEquals(tabMenuList.get(i).getText(), menuExpectedList.get(i), "Menu eslesmedi");
        }

    }

    @Test(priority = 5, dependsOnMethods = {"LoginTest"})
    void OrderGiftsTest() {

        WebElement giftCards = driver.findElement(By.cssSelector("ul[class='top-menu notmobile']>li:nth-child(7)"));
        giftCards.click();

        int random = (int) Math.random() * 3;

        switch (random) {
            case 0:
                WebElement virtual = driver.findElement(By.linkText("$25 Virtual Gift Card"));
                virtual.click();
                WebElement recipientsName0 = driver.findElement(By.id("giftcard_43_RecipientName"));
                recipientsName0.sendKeys("hatice");

                WebElement email = driver.findElement(By.id("giftcard_43_RecipientEmail"));
                email.sendKeys("htc6@gmail.com");

                WebElement yourName0 = driver.findElement(By.id("giftcard_43_SenderName"));
                yourName0.sendKeys("Ayse");
                WebElement yourEmail = driver.findElement(By.id("giftcard_43_SenderEmail"));
                yourEmail.sendKeys(email2);
                WebElement messsage0 = driver.findElement(By.cssSelector("textarea[id='giftcard_43_Message"));
                messsage0.sendKeys("message");

                WebElement addToCart0 = driver.findElement(By.cssSelector("button[id='add-to-cart-button-43']"));
                addToCart0.click();

                WebElement validation0 = driver.findElement(By.cssSelector("p[class='content']>a"));

                Assert.assertTrue(validation0.getText().contains("shopping cart"));
                break;


            case 1:

                WebElement physical = driver.findElement(By.linkText("$50 Physical Gift Card"));
                physical.click();

                WebElement recipientsName1 = driver.findElement(By.id("giftcard_44_RecipientName"));
                recipientsName1.sendKeys("hatice");
                WebElement yourName1 = driver.findElement(By.id("giftcard_44_SenderName"));
                yourName1.sendKeys("Ayse");
                WebElement messsage1 = driver.findElement(By.cssSelector("textarea[id='giftcard_44_Message"));
                messsage1.sendKeys("message");

                WebElement addToCart1 = driver.findElement(By.cssSelector("button[id='add-to-cart-button-44']"));
                addToCart1.click();

                WebElement validation1 = driver.findElement(By.cssSelector("p[class='content']>a"));

                Assert.assertTrue(validation1.getText().contains("shopping cart"));
                break;

            case 2:
                WebElement physical2 = driver.findElement(By.linkText("$100 Physical Gift Card"));
                physical2.click();
                WebElement recipientsName2 = driver.findElement(By.id("giftcard_45_RecipientName"));
                recipientsName2.sendKeys("hatice");
                WebElement yourName2 = driver.findElement(By.id("giftcard_45_SenderName"));
                yourName2.sendKeys("Ayse");
                WebElement messsage2 = driver.findElement(By.cssSelector("textarea[id='giftcard_45_Message']"));
                messsage2.sendKeys("message");

                WebElement addToCart2 = driver.findElement(By.cssSelector("button[id='add-to-cart-button-45']"));
                addToCart2.click();

                WebElement validation2 = driver.findElement(By.cssSelector("p[class='content']>a"));

                Assert.assertTrue(validation2.getText().contains("shopping cart"));

                break;
        }
    }

    @Test(priority = 6, dependsOnMethods = {"LoginTest"})
    void OrrderComputerTest() {

        WebElement computers = driver.findElement(By.cssSelector("ul[class='top-menu notmobile']>li:nth-child(1)"));
        new Actions(driver).moveToElement(computers).build().perform();

        WebElement desktops = driver.findElement(By.cssSelector("[href='/desktops']"));
        desktops.click();
        WebElement buildownComputer = driver.findElement(By.xpath("//button[@class='button-2 product-box-add-to-cart-button']"));
        buildownComputer.click();

        List<WebElement> list = driver.findElements(By.cssSelector("select[id='product_attribute_2']>option"));
    int random2=Tools.RandomNumberGenerator(list.size());
        list.get(random2).click();


        List<WebElement> list2 = driver.findElements(By.cssSelector("input[name='product_attribute_3']"));
    int random3=Tools.RandomNumberGenerator(list2.size());

        list2.get(random3).click();
        WebElement addToCart = driver.findElement(By.cssSelector("[id='add-to-cart-button-1']"));
        addToCart.click();
        if(list.size()==0){
            WebElement validation = driver.findElement(By.cssSelector("p[class='content']"));
            Assert.assertTrue(validation.getText().contains("RAM"));

        }else{
        WebElement validation2 = driver.findElement(By.cssSelector("p[class='content']>a"));
        Assert.assertTrue(validation2.getText().contains("shopping cart"));}

    }

    @Parameters("aranacakKelime")
    @Test(priority = 7, dependsOnMethods = {"LoginTest"})
    void PametersSearch(String text) {

        WebElement searcStore = driver.findElement(By.cssSelector("[id='small-searchterms']"));
        searcStore.sendKeys(text);
        WebElement search = driver.findElement(By.cssSelector("[class='button-1 search-box-button']"));
        search.sendKeys(text);

        WebElement validation = driver.findElement(By.cssSelector("[class='product-title']>[href='/adobe-photoshop-cs4']"));
        Assert.assertTrue(validation.getText().contains("Adobe Photoshop CS4"));


    }


}
