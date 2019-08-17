
package src.languageAuto.CustomerCenter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import lib.CommonLib.CommonLibraryFunctions;

import lib.Utilities.Read_XLS;
import lib.Utilities.SuiteUtility;


//SuiteOneCaseOne Class Inherits From LACustomerCenterSuiteBase Class.
//So, SuiteOneCaseOne Class Is Child Class Of LACustomerCenterSuiteBase Class And LASuiteBase Class.
public class FUSEPseudoChangeSessionlanguageAccounts extends LACustomerCenterSuiteBase{
	Read_XLS FilePath = null;
	String SheetName = null;
	String DataSheetName = null;
	String TestCaseName = null;	
	String ToRunColumnNameTestCase = null;
	String ToRunColumnNameTestData = null;
	String TestDataToRun[]=null;
	String Random = "";
	String AccountName;
	int Counter=0;
	static boolean TestCasePass=true;
	static int DataSet=-1;	
	static boolean Testskip=false;
	static boolean Testfail=false;
	SoftAssert s_assert =null;	
	
	@BeforeTest
	public void checkCaseToRun() throws IOException{
		//Called init() function from SuiteBase class to Initialize .xls Files
		init();			
		//To set CustomerCenterSuite.xls file's path In FilePath Variable.
		FilePath = CustomerCenterSuite;		
		TestCaseName = this.getClass().getSimpleName();	
		//SheetName to check CaseToRun flag against test case.
		SheetName = "TestCasesList";
		//Name of DataSheetList In Excel sheet.
		DataSheetName= "CustomerCenterCaseOne";
		//Name of column In TestCasesList Excel sheet.
		ToRunColumnNameTestCase = "CaseToRun";
		//Name of column In Test Case Data sheets.
		ToRunColumnNameTestData = "DataToRun";
		//Bellow given syntax will Insert log In applog.log file.
		Add_Log.info(TestCaseName+" : Execution started.");
		
		//To check test case's CaseToRun = Y or N In related excel sheet.
		//If CaseToRun = N or blank, Test case will skip execution. Else It will be executed.
		if(!SuiteUtility.checkToRunUtility(FilePath, SheetName,ToRunColumnNameTestCase,TestCaseName)){
			Add_Log.info(TestCaseName+" : CaseToRun = N for So Skipping Execution.");
			//To report result as skip for test cases In TestCasesList sheet.
			SuiteUtility.WriteResultUtility(FilePath, SheetName, "Pass/Fail/Skip", TestCaseName, "SKIP");
			//To throw skip exception for this test case.
			throw new SkipException(TestCaseName+"'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of "+TestCaseName);
		}	
		//To retrieve DataToRun flags of all data set lines from related test data sheet.
		TestDataToRun = SuiteUtility.checkToRunUtilityOfData(FilePath, DataSheetName, ToRunColumnNameTestData);
	}
	
	//Accepts 4 column's String data In every Iteration.
	@Test(dataProvider="SuiteOneCaseOneData")
	public void SuiteOneCaseOneTest(String DataCol1,String DataCol2,String DataCol3,String DataCol4) throws Throwable{
		
		DataSet++;
		
		//Created object of testng SoftAssert class.
		s_assert = new SoftAssert();
		
		//If found DataToRun = "N" for data set then execution will be skipped for that data set.
		if(!TestDataToRun[DataSet].equalsIgnoreCase("Y")){	
			Add_Log.info(TestCaseName+" : DataToRun = N for data set line "+(DataSet+1)+" So skipping Its execution.");
			//If DataToRun = "N", Set Testskip=true.
			Testskip=true;
			throw new SkipException("DataToRun for row number "+DataSet+" Is No Or Blank. So Skipping Its Execution.");
		}
		
		//If found DataToRun = "Y" for data set then bellow given lines will be executed.
		//To Convert data from String to Integer
		String ValueOne = DataCol1;
		String ValueTwo = DataCol2;
		String ValueThree = DataCol3;
		String ValueFour= DataCol4;
		
		System.out.println("249_FUSE_Pseudo_Change Session language - Accounts");
		Random = String.valueOf((int) ((Math.random() *  100000))); 
		System.out.println(Random);
		AccountName = "AutoAccount" +  Random;
			
		//To Initialize browser.
		loadWebBrowser();		
		
		//To navigate to URL. It will read site URL from Param.properties file
		driver.get(Param.getProperty("siteURL"));		
				
		Add_Log.info("Step1: Login to the environment");
		
		Select List = new Select(getElementByXPath("language"));
		List.selectByValue("zh-cn");
		implicitWait(15);
		
		getElementByXPath("userid").clear();
		getElementByXPath("userid").sendKeys("sales_admin");
		getElementByXPath("password").clear();
		getElementByXPath("password").sendKeys("Welcome1");
		
		Thread.sleep(5000);
		
		getElementByXPath("signinbutton").click();
		implicitWait(60);
		
		Add_Log.info("Step2: Navigate to the account tab");
		explicitWaitForVisibilityOfElement("navigator");
		getElementByXPath("navigator").click();
		explicitWaitForVisibilityOfElement("accounttab");
		getElementByXPath("accounttab").click();
						  
		Add_Log.info("Step3: Create Account");
		
		explicitWaitForVisibilityOfElement("createaccountbutton");
		getElementByXPath("createaccountbutton").click();
		explicitWaitForVisibilityOfElement("accountnamelabel");
		explicitWaitForVisibilityOfElement("accountnametextfield");
		getElementByXPath("accountnametextfield").clear();
		getElementByXPath("accountnametextfield").sendKeys(AccountName);
		
		explicitWaitForVisibilityOfElement("accountaddresstextfield");
		getElementByXPath("accountaddresstextfield").clear();
		getElementByXPath("accountaddresstextfield").sendKeys("300 Oracle Parkway");		 
	
		
		explicitWaitForVisibilityOfElement("accountpostalcodetextfield");
		getElementByXPath("accountpostalcodetextfield").clear();
		explicitWaitForVisibilityOfElement("accountpostalcodedropdown");
		getElementByXPath("accountpostalcodedropdown").click();
				  
		explicitWaitForVisibilityOfElement("accountpostalcodedropdownsearchlabel");
		getElementByXPath("accountpostalcodedropdownsearchlabel").click();	   
		
		explicitWaitForVisibilityOfElement("accountpostalcodetextfield");
		getElementByXPath("accountpostalcodetextfield").click();	   
		getElementByXPath("accountpostalcodetextfield").sendKeys("94065");
	 
		explicitWaitForVisibilityOfElement("accountpostalcodesearchbutton");
		getElementByXPath("accountpostalcodesearchbutton").click();
		
		explicitWaitForVisibilityOfElement("accountpostalcoderadiobutton");
		getElementByXPath("accountpostalcoderadiobutton").click();
				  
		explicitWaitForVisibilityOfElement("accountpostalcodeokbutton");
		getElementByXPath("accountpostalcodeokbutton").click();
				  
		
		
				  
		try {
			explicitWaitForVisibilityOfElement("accountsaveandclosebutton");
			getElementByXPath("accountsaveandclosebutton").click(); 
		}
		catch(Exception e) {
			System.out.println("inside catch block");
		    Thread.sleep(5000);		
			JavascriptExecutor jse2 = (JavascriptExecutor)driver;
			jse2.executeScript("document.getElementById('_FOpt1:_FOr1:0:_FOSrZCM_CUSTOMERCTRINFRA360_CUSTOMERS_CRM_CARD:0:_FOTsr1:0:pt1:r22:0:pt1:AP1:cb3').click();");
		}
		
		Thread.sleep(10000);
		if(getElementByXPath("accountcontinuetocreatebutton").isDisplayed()) {
			getElementByXPath("accountcontinuetocreatebutton").click();
			System.out.println("Account created is"+ AccountName+"created");
			Thread.sleep(10000);
			
		}
		else
		{
			System.out.println("This is the first time of the account creation");
			System.out.println("Account created is"+ AccountName+"created");
			Thread.sleep(10000);
		}
		
		
		Add_Log.info("Step4: Search the created Account");
		
		
		explicitWaitForVisibilityOfElement("accountadvancesearchbutton");
		getElementByXPath("accountadvancesearchbutton").click();
		
		explicitWaitForVisibilityOfElement("accountoperatordropdown");
		getElementByXPath("accountoperatordropdown").click();
		
		explicitWaitForVisibilityOfElement("accountnamestartswithoption");
		getElementByXPath("accountnamestartswithoption").click();
		
		explicitWaitForVisibilityOfElement("accountnamesearchtextfield");
		getElementByXPath("accountnamesearchtextfield").clear();
		getElementByXPath("accountnamesearchtextfield").sendKeys(AccountName);
		
		explicitWaitForVisibilityOfElement("accountnamesearchbutton");
		getElementByXPath("accountnamesearchbutton").click();
		
				 
			
		explicitWaitForVisibilityOfElement("preaccountnamedisplayaftersearch",AccountName,"postaccountnamedisplayaftersearch");
		getElementByXPath("preaccountnamedisplayaftersearch",AccountName,"postaccountnamedisplayaftersearch").click();
		  
		Thread.sleep(7000);
		  
		Add_Log.info("Step5: Go to Edit Account and search match all the tabs");
		 
		explicitWaitForVisibilityOfElementByClass("editaccountpageleftsidetabs");
		List<WebElement> ele =  getElementsByClass("editaccountpageleftsidetabs");
		System.out.println(ele.size());
		  
		  
 
		String[] ActualValue = new String[20];
		for (int i = 0; i < ele.size(); ++i) {
		
			ActualValue[i]=ele.get(i).getText();
			System.out.println("The element"+i+" is: "+ActualValue[i]);
			
		//	System.out.println(ActualValue[i]);
		
			if((ActualValue[i]).isEmpty()){
				Mouseaction.clickAndHold(getElementByXPath("editaccountpageleftsidetabsscroll")).perform();
		  
				Thread.sleep(10000); 
				Mouseaction.release(getElementByXPath("editaccountpageleftsidetabsscroll")).perform();
				i=i-1;
				}
				  
		  }
				  
		  String ExpectedValue[] = { "概览", "概要信息", "团队", "联系人", "资产", "业务机会", "报价和订单", "销售线索", "关系", "服务请求", "附注", "评估", "活动", "交互历史记录" };
		  
		  for (int i = 0; i < ele.size(); ++i) {
			
			  
			  if(ActualValue[i].contentEquals(ExpectedValue[i])) {
				  System.out.println(ExpectedValue[i]);
		  		  System.out.println(ActualValue[i]);
		   		  System.out.println("Success");
		  		  
			  } else {
				  s_assert.assertEquals(ActualValue[i], ExpectedValue[i], "ActualResult Value "+ActualValue[i]+" And ExpectedResult Value "+ExpectedValue[i]+" Not Match");
				  Counter++;
				  Testfail=true;
			  }
		  }
		explicitWaitForVisibilityOfElement("editaccountpagecancelbutton");
		getElementByXPath("editaccountpagecancelbutton").click();
		Thread.sleep(20000);
			
		  
		  
		Add_Log.info("Step6: Sign out from the application");
		  		  
		System.out.println("Sign out");
		  
		
		explicitWaitForVisibilityOfElement("homepagenavigatetosignoutbutton");
		getElementByXPath("homepagenavigatetosignoutbutton").click();
		explicitWaitForVisibilityOfElement("homepagesignoutbutton");
		getElementByXPath("homepagesignoutbutton").click();
		
		
		explicitWaitForVisibilityOfElement("signoutconfirmbutton");
		getElementByXPath("signoutconfirmbutton").click();
		
		if(Testfail){
			//At last, test data assertion failure will be reported In testNG reports and It will mark your test data, test case and test suite as fail.
			s_assert.assertAll();
			}
		}
	
	
	
	//@AfterMethod method will be executed after execution of @Test method every time.
	@AfterMethod
	public void reporterDataResults(){		
		if(Testskip){
			Add_Log.info(TestCaseName+" : Reporting test data set line "+(DataSet+1)+" as SKIP In excel.");
			//If found Testskip = true, Result will be reported as SKIP against data set line In excel sheet.
			SuiteUtility.WriteResultUtility(FilePath, DataSheetName, "Pass/Fail/Skip", DataSet+1, "SKIP");
		}
		else if(Testfail){
			Add_Log.info(TestCaseName+" : Reporting test data set line "+(DataSet+1)+" as FAIL In excel.");
			//To make object reference null after reporting In report.
			s_assert = null;
			//Set TestCasePass = false to report test case as fail In excel sheet.
			TestCasePass=false;	
			//If found Testfail = true, Result will be reported as FAIL against data set line In excel sheet.
			SuiteUtility.WriteResultUtility(FilePath, DataSheetName, "Pass/Fail/Skip", DataSet+1, "FAIL");			
		}else{
			Add_Log.info(TestCaseName+" : Reporting test data set line "+(DataSet+1)+" as PASS In excel.");
			//If found Testskip = false and Testfail = false, Result will be reported as PASS against data set line In excel sheet.
			SuiteUtility.WriteResultUtility(FilePath, DataSheetName, "Pass/Fail/Skip", DataSet+1, "PASS");
		}
		//At last make both flags as false for next data set.
		Testskip=false;
		Testfail=false;
	}
	
	//This data provider method will return 4 column's data one by one In every Iteration.
	@DataProvider
	public Object[][] SuiteOneCaseOneData(){
		//To retrieve data from Data 1 Column,Data 2 Column,Data 3 Column and Expected Result column of SuiteOneCaseOne data Sheet.
		//Last two columns (DataToRun and Pass/Fail/Skip) are Ignored programatically when reading test data.
		return SuiteUtility.GetTestDataUtility(FilePath, DataSheetName);
	}	
	
	//To report result as pass or fail for test cases In TestCasesList sheet.
	@AfterTest
	public void closeBrowser(){
		//To Close the web browser at the end of test.
		closeWebBrowser();
		if(TestCasePass){
			Add_Log.info(TestCaseName+" : Reporting test case as PASS In excel.");
			SuiteUtility.WriteResultUtility(FilePath, SheetName, "Pass/Fail/Skip", TestCaseName, "PASS");
		}
		else{
			Add_Log.info(TestCaseName+" : Reporting test case as FAIL In excel.");
			SuiteUtility.WriteResultUtility(FilePath, SheetName, "Pass/Fail/Skip", TestCaseName, "FAIL");			
		}
	}
}