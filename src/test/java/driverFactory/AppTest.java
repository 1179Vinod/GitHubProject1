package driverFactory;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctions.CustomerPage;
import config.AppUtil;
import utilities.ExcleFileUtil;

public class AppTest extends AppUtil{
String inputpath = "./FileInput/CustomerData.xlsx";
String outputpath = "./FileOutput/DataDrivenResults.xlsx";
ExtentReports report;
ExtentTest logger;
String TestData = "customer";
@Test
public void StartTest() throws Throwable
{
	report = new ExtentReports("./target/ExtentReports/Customer.html");
	ExcleFileUtil xl = new ExcleFileUtil(inputpath);
	//count no of rows in customer sheet
	int rc = xl.rowCount(TestData);
	Reporter.log("No of rows are::"+rc,true);
	for(int i =1;i<=rc;i++)
	{
		logger = report.startTest("Customer Module");
		logger.assignAuthor("Ranga");
		//read all cells from customer sheet
		String cname =xl.getCellData(TestData,i,0);
		String Address = xl.getCellData(TestData, i, 1);
		String City = xl.getCellData(TestData, i, 2);
		String Country= xl.getCellData(TestData, i, 3);
		String Cperson = xl.getCellData(TestData, i, 4);
		String pnumber = xl.getCellData(TestData, i, 5);
		String email = xl.getCellData(TestData, i, 6);
		String mnumber = xl.getCellData(TestData, i, 7);
		String notes = xl.getCellData(TestData, i, 8);
		CustomerPage cus = PageFactory.initElements(driver, CustomerPage.class);
		boolean res =cus.addCustomer(cname, Address, City, Country, Cperson, pnumber, email, mnumber, notes);
		logger.log(LogStatus.INFO,cname+"    "+Address+"         "+City+"    "+Country+"    "+Cperson+"  "+pnumber+"  "+email+" "+mnumber+"  "+notes);
		if(res)
		{
			//if res is true write as pass into status cell
			xl.setCellData(TestData, i, 9, "pass", outputpath);
			logger.log(LogStatus.PASS, "Customer Number Found in table");
			
		}
		else
		{
			//if res is fail write as pass into status cell
			xl.setCellData(TestData, i, 9, "fail", outputpath);
			logger.log(LogStatus.FAIL, "Customer Number  Not Found in table");
		}
		report.endTest(logger);
		report.flush();
		
		
	}
}
}
