package config;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.apache.xmlbeans.impl.repackage.Repackage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import commonFunctions.AdminLoginPage;
import commonFunctions.AdminLogoutPage;

public class AppUtil {
public static WebDriver driver;
public static Properties conpro;
@BeforeTest
public void setup() throws Throwable
{
	conpro= new Properties();
	conpro.load(new FileInputStream("./PropertyFiles/Environment.properties"));
	if(conpro.getProperty("Browser").equalsIgnoreCase("chrome")) 
	{
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(conpro.getProperty("Url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//access admin login page class elements
		AdminLoginPage login = PageFactory.initElements(driver, AdminLoginPage.class);
		//call login method from adminlogin page class
		login.adminLogin("admin", "master");
				
	}
	else if(conpro.getProperty("Browser").equalsIgnoreCase("firefox")) 
	{
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get(conpro.getProperty("Url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//access admin login page class elements
		AdminLoginPage login = PageFactory.initElements(driver, AdminLoginPage.class);
		//call login method from adminlogin page class
		login.adminLogin("admin", "master");
}
	else
	{
		Reporter.log("Browser is not matching",true);
	}
}

@AfterTest
public static void tearDown()
{
	AdminLogoutPage logout = PageFactory.initElements(driver, AdminLogoutPage.class);
	logout.adminLogout();
	driver.quit();
}
}
