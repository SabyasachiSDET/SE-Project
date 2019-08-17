package src.languageAuto.LASuiteBase;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

import org.apache.log4j.Logger;

import lib.CommonBase.SuiteBase;
import lib.Utilities.Read_XLS;

public class LASuiteBase extends SuiteBase{

	
	public static Read_XLS LATestSuiteListExcel=null;
	public static Read_XLS CustomerCenterSuite=null;
	
	
	public void init() throws IOException{
		//To Initialize logger service.
		Add_Log = Logger.getLogger("rootLogger");				
				
		
		//Initializing Test Suite List(TestSuiteList.xls) File Path Using Constructor Of Read_XLS Utility Class.
		LATestSuiteListExcel = new Read_XLS(System.getProperty("user.dir")+"\\src\\src\\languageAuto\\ExcelFiles\\LATestSuiteList.xlsx");
		//Initializing Test Suite One(SuiteOne.xls) File Path Using Constructor Of Read_XLS Utility Class.
		CustomerCenterSuite = new Read_XLS(System.getProperty("user.dir")+"\\src\\src\\languageAuto\\ExcelFiles\\CustomerCenterSuite.xlsx");
		//Bellow given syntax will Insert log In applog.log file.
		Add_Log.info("All Excel Files Initialised successfully.");
		
		//Initialize Param.properties file.
		Param = new Properties();
		FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"//src//lib//CommonBase//Param.properties");
		Param.load(fip);
		Add_Log.info("Param.properties file loaded successfully.");		
	
		//Initialize Objects.properties file.
		Object = new Properties();
		//fip = new FileInputStream(System.getProperty("user.dir")+"//src//src//languageAuto//LASuiteBase//Objects.properties");
		//Object.load(fip);
		fip = new FileInputStream(new File(System.getProperty("user.dir")+"//src//src//languageAuto//LASuiteBase//Objects.properties"));
		Object.load(new InputStreamReader(fip, Charset.forName("UTF-8")));
		Add_Log.info("Objects.properties file loaded successfully.");
	}
}
