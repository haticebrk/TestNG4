package TestNGProjee.proje8;

import Utils.BaseDriver;
import Utils.BaseDriverParameter;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import proje8.BaseDriverParmterss;
import proje8._02_ElementLocators;

public class _01_proje8_POM extends BaseDriverParmterss {
    String email2;
    String email3;

    @Test(priority = 1)
    void LoginTest() {
        _02_ElementLocators el = new _02_ElementLocators(driver);

        driver.get("https://admin-demo.nopcommerce.com/login");
        el.email.clear();
        el.email.sendKeys("admin@yourstore.com");
        el.password.clear();
        el.password.sendKeys("admin");
        el.loginbutton.click();
        Assert.assertTrue(el.Validationlogin.getText().contains("Dashboard"));

    }

    @Test(priority = 2, dependsOnMethods = "LoginTest")
    void CheckLefNawMenu() {
        _02_ElementLocators el = new _02_ElementLocators(driver);
        for (int i = 0; i < el.leftNawMenu.size(); i++) {
            wait.until(ExpectedConditions.elementToBeClickable(el.leftNawMenu.get(i)));
            el.leftNawMenu.get(i).click();
            if(el.leftNawMenu.get(i).getAttribute("style").contains("display: block;"))
                System.out.println("menu acik");


        }
        for (int i = 0; i < el.leftNawValidation.size(); i++) {

            boolean degisken = false;
            if (el.leftNawValidation.size() > 0)
                degisken = true;
        }
        System.out.println(" tum elemeanlar gorunur durumda");


    }

    @Test(priority = 3, dependsOnMethods = "LoginTest")
    void createCostumer() {
        email2 = RandomStringUtils.randomAlphabetic(6) + "@gmail.com";
        _02_ElementLocators el = new _02_ElementLocators(driver);
        el.customersOne.click();
        el.customersTwo.click();
        el.addNew.click();
        el.emailCustomer.sendKeys(email2);
        el.passwordCustomer.sendKeys("1234TS");
        el.firstNameCustomer.sendKeys("Ali");
        el.lastNamecustomer.sendKeys("Yilmaz");
        el.male.click();
        el.dateOBirth.sendKeys("12/05/1990");
        el.save.click();

        el.messageValidation();


    }

    @Test(priority = 4)
    void editCutomer() {
        email3 = RandomStringUtils.randomAlphabetic(7) + "@gmail.com";
        _02_ElementLocators el = new _02_ElementLocators(driver);
        el.customersOne.click();
        el.customersTwo.click();
        el.emailSearch.sendKeys(email2);
        el.firstNameSearch.sendKeys("Ali");
        el.lastNameSearch.sendKeys("Yilmaz");
        el.searchButton.click();
        wait.until(ExpectedConditions.visibilityOf(el.validationSearch));
        Assert.assertTrue(el.validationSearch.getText().toLowerCase().contains("registered"));

        el.edit2.click();

        el.emailCustomer.clear();
        el.emailCustomer.sendKeys(email3);
        el.save.click();

        el.messageValidation();

    }

    @Test(priority = 5)
    void DeleteCustomer() {
        _02_ElementLocators el = new _02_ElementLocators(driver);
        el.customersOne.click();
        el.customersTwo.click();
        el.emailSearch.sendKeys(email3);
        el.firstNameSearch.sendKeys("Ali");
        el.lastNameSearch.sendKeys("Yilmaz");
        el.searchButton.click();
        el.edit2.click();

        el.delete.click();
        el.deletebutton.click();

        el.messageValidation();

    }

    @Test(priority = 6)
    void SearchTest() {
        _02_ElementLocators el = new _02_ElementLocators(driver);

        new Actions(driver).moveToElement(el.searchBox).click().sendKeys("Ship").
                sendKeys(Keys.DOWN).sendKeys(Keys.ENTER).build().perform();

        Assert.assertTrue(el.validationShipments.getText().contains("Shipments"));


    }




}

