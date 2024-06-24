package commonFunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class CustomerPage {
WebDriver driver;
public CustomerPage(WebDriver driver)
{
	this.driver=driver;
}
//define Repository
@FindBy(xpath = "(//a[contains(text(),'Customers')])[2]")
WebElement ObjCustomerLink;
@FindBy(xpath = "(//span[@data-caption='Add'])[1]")
WebElement ObjClickAddicon;
@FindBy(name = "x_Customer_Number")
WebElement ObjCustomerNumber;
@FindBy(name = "x_Customer_Name")
WebElement ObjCustomerName;
@FindBy(name = "x_Address")
WebElement ObjAddress;
@FindBy(name = "x_City")
WebElement ObjCity;
@FindBy(name = "x_Country")
WebElement ObjCountry;
@FindBy(name = "x_Contact_Person")
WebElement ObjcontactPerson;
@FindBy(name = "x_Phone_Number")
WebElement Objphonenumber;
@FindBy(name = "x__Email")
WebElement ObjEmail;
@FindBy(name = "x_Mobile_Number")
WebElement ObjMobileNumber;
@FindBy(name = "x_Notes")
WebElement ObjNotes;
@FindBy(id="btnAction")
WebElement ObjClickAddBtn;
@FindBy(xpath = "//button[normalize-space()='OK!']")
WebElement ObjClickConfirmOk;
@FindBy(xpath = "(//button[starts-with(text(),'OK')])[6]")
WebElement ObjClickAlertmOk;
@FindBy(xpath = "//span[@data-caption='Search']")
WebElement ObjSearchPanel;
@FindBy(xpath = "//input[@id='psearch']")
WebElement ObjSearchTextbox;
@FindBy(xpath = "//button[@id='btnsubmit']")
WebElement ObjSearchBtn;
@FindBy(xpath = "//table[@class='table ewTable']/tbody/tr[1]/td[5]/div/span/span")
WebElement Webtable;
//method for add customer
public boolean addCustomer(String Cname,String Address,String City,String Country,String Cperson,
		String Pnumber,String Emial,String Mnumber,String notes)throws Throwable
{
	Actions ac = new Actions(driver);
	ac.moveToElement(this.ObjCustomerLink).click().perform();
	ac.pause(4000);
	ac.moveToElement(this.ObjClickAddicon).click().perform();
	String Exp_Data =this.ObjCustomerNumber.getAttribute("value");
	this.ObjCustomerName.sendKeys(Cname);
	this.ObjAddress.sendKeys(Address);
	this.ObjCity.sendKeys(City);
	this.ObjCountry.sendKeys(Country);
	this.ObjcontactPerson.sendKeys(Cperson);
	this.Objphonenumber.sendKeys(Pnumber);
	this.ObjEmail.sendKeys(Emial);
	this.ObjMobileNumber.sendKeys(Mnumber);
	this.ObjNotes.sendKeys(notes);
	ac.moveToElement(this.ObjClickAddBtn).click().perform();
	ac.pause(3000);
	ac.moveToElement(this.ObjClickConfirmOk).click().perform();
	Thread.sleep(2000);
	ac.moveToElement(this.ObjClickAlertmOk).click().perform();
	if(!ObjSearchTextbox.isDisplayed())
		ObjSearchPanel.click();
	ObjSearchTextbox.clear();
	ObjSearchTextbox.sendKeys(Exp_Data);
	ObjSearchBtn.click();
	String Act_data = Webtable.getText();
	if(Act_data.equals(Exp_Data))
	{
		Reporter.log(Exp_Data+"      "+Act_data,true);
		return true;
	
	}
	else
	{
		Reporter.log(Exp_Data+"      "+Act_data,true);
		return false;
	}
}




}
