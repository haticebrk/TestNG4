package TestNGProjee.proje8;

import Utils.BaseDriver;
import Utils.BaseDriverParameter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class _02_ElementLocators {

    public _02_ElementLocators(WebDriver driver){

        PageFactory.initElements(driver,this);}

   @FindBy(css = "input[id='Email']")
   public   WebElement email;

    @FindBy(css = "input[id='Password']")
   public WebElement password;


    @FindBy(css = "button[type='submit']")
   public WebElement loginbutton;

  @FindBy(xpath = "//h1[normalize-space()='Dashboard']")
    public WebElement Validationlogin;


  @FindBy(css = "i[class^='nav-icon fas fa']")
    public List<WebElement> leftNawMenu;

    @FindBy(css = "ul[class='nav nav-treeview']")
    public List<WebElement> leftNawValidation;




    @FindBy(css = "i[class='nav-icon far fa-user']")
    public WebElement customersOne;

    @FindBy(xpath = "//*[text()=' Customers']")
    public WebElement customersTwo;

    @FindBy(css ="a[class='btn btn-primary']")
    public WebElement addNew;


    @FindBy(id="Email")
    public WebElement emailCustomer;
    @FindBy(id="Password")
    public WebElement passwordCustomer;

    @FindBy(id="FirstName")
    public WebElement firstNameCustomer;

    @FindBy(id="LastName")
    public WebElement lastNamecustomer;

    @FindBy(id="Gender_Male")
    public WebElement male;

    @FindBy(id="DateOfBirth")
    public WebElement dateOBirth;

    @FindBy(name="save")
    public WebElement save;
    @FindBy(css ="[class='alert alert-success alert-dismissable']")
    public WebElement validationCustomer;



    @FindBy(id="SearchFirstName")
    public WebElement firstNameSearch;

    @FindBy(id="SearchLastName")
    public WebElement lastNameSearch;

    @FindBy(id="SearchEmail")
    public WebElement emailSearch;

    @FindBy(id="search-customers")
    public WebElement searchButton;


    @FindBy(xpath= "//td[text()='Registered']")
   public WebElement validationSearch;

    @FindBy(id = "customer-delete")
    public WebElement delete;

    @FindBy(css ="button[class='btn btn-danger float-right']")
    public WebElement deletebutton;


    @FindBy(css =" input[placeholder='Search']")
    public WebElement searchBox;

    @FindBy(css ="h1[class='float-left']")
    public WebElement validationShipments;


    @FindBy(css ="a[class='btn btn-default']")
    public WebElement edit2;

    public void messageValidation() {
        WebDriverWait wait=new WebDriverWait(BaseDriver.driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(validationCustomer));
        Assert.assertTrue(validationCustomer.getText().contains("success"));

    }

}
