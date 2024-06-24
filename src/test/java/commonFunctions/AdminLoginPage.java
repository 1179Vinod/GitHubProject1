package commonFunctions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminLoginPage {
	//define Repository
	@FindBy(id="btnreset")
	WebElement ObjResetBtn;
	@FindBy(xpath = "//*[@id=\"username\"]")
	WebElement ObjUser;
	@FindBy(id="password")
	WebElement Objpass;
	@FindBy(id="btnsubmit")
	WebElement ObjLoginBtn;
	//method for Login
	public void adminLogin(String user,String pass)
	{
		ObjResetBtn.click();
		ObjUser.sendKeys(user);
		Objpass.sendKeys(pass);
		ObjLoginBtn.click();
	}

}
