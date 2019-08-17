package lib.CommonBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class SuiteBase {	
	public static Logger Add_Log = null;
	public boolean BrowseralreadyLoaded=false;
	public static Properties Param = null;
	public static Properties Object = null;
	public static WebDriver driver=null;
	public static WebDriver ExistingchromeBrowser;
	public static WebDriver ExistingmozillaBrowser;
	public static WebDriver ExistingIEBrowser;
	public static Actions Mouseaction = null;
	
	public void init() throws IOException{
		//To Initialize logger service.
		Add_Log = Logger.getLogger("rootLogger");		
				
		//Initialize Param.properties file.
		Param = new Properties();
		FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"//src//lib//CommonBase//Param.properties");
		Param.load(fip);
		Add_Log.info("Param.properties file loaded successfully.");		
	
		
	}
	
	public void loadWebBrowser(){
		//Check If any previous webdriver browser Instance Is exist then run new test In that existing webdriver browser Instance.
			if(Param.getProperty("testBrowser").equalsIgnoreCase("Mozilla") && ExistingmozillaBrowser!=null){
				driver = ExistingmozillaBrowser;
				return;
			}else if(Param.getProperty("testBrowser").equalsIgnoreCase("chrome") && ExistingchromeBrowser!=null){
				driver = ExistingchromeBrowser;
				return;
			}else if(Param.getProperty("testBrowser").equalsIgnoreCase("IE") && ExistingIEBrowser!=null){
				driver = ExistingIEBrowser;
				return;
			}		
		
		
			if(Param.getProperty("testBrowser").equalsIgnoreCase("Mozilla")){
				//To Load Firefox driver Instance. 
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"//BrowserDrivers//geckodriver.exe");
				driver = new FirefoxDriver();
				ExistingmozillaBrowser=driver;
				Add_Log.info("Firefox Driver Instance loaded successfully.");
				
			}else if(Param.getProperty("testBrowser").equalsIgnoreCase("Chrome")){
				//To Load Chrome driver Instance.
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//BrowserDrivers//chromedriver.exe");
				driver = new ChromeDriver();
				ExistingchromeBrowser=driver;
				Add_Log.info("Chrome Driver Instance loaded successfully.");
				
			}else if(Param.getProperty("testBrowser").equalsIgnoreCase("IE")){
				//To Load IE driver Instance.
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"//BrowserDrivers//IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				ExistingIEBrowser=driver;
				Add_Log.info("IE Driver Instance loaded successfully.");
				
			}			
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			driver.manage().window().maximize();			
	}
	
	public void closeWebBrowser(){
		driver.close();
		//null browser Instance when close.
		ExistingchromeBrowser=null;
		ExistingmozillaBrowser=null;
		ExistingIEBrowser=null;
	}
	
	//getElementByXPath function for static xpath
	public WebElement getElementByXPath(String Key){
		try{
			//This block will find element using Key value from web page and return It.
			return driver.findElement(By.xpath(Object.getProperty(Key)));
		}catch(Throwable t){
			//If element not found on page then It will return null.
			Add_Log.debug("Object not found for key --"+Key);
			return null;
		}
	}
	
	//getElementByXPath function for dynamic xpath
	public WebElement getElementByXPath(String Key1, int val, String key2){
		try{
			//This block will find element using values of Key1, val and key2 from web page and return It.
			return driver.findElement(By.xpath(Object.getProperty(Key1)+val+Object.getProperty(key2)));
		}catch(Throwable t){
			//If element not found on page then It will return null.
			Add_Log.debug("Object not found for custom xpath");
			return null;
		}
	}
	
	
	//getElementByXPath function for dynamic xpath
		public WebElement getElementByXPath(String Key1, String val, String key2){
			try{
				//This block will find element using values of Key1, val and key2 from web page and return It.
				return driver.findElement(By.xpath(Object.getProperty(Key1)+val+Object.getProperty(key2)));
			}catch(Throwable t){
				//If element not found on page then It will return null.
				Add_Log.debug("Object not found for custom xpath");
				return null;
			}
		}
	
	//Call this function to locate element by ID locator.
	public WebElement getElementByID(String Key){
		try{
			return driver.findElement(By.id(Object.getProperty(Key)));
		}catch(Throwable t){
			Add_Log.debug("Object not found for key --"+Key);
			return null;
		}
	}
	
	//Call this function to locate element by Name Locator.
	public WebElement getElementByName(String Key){
		try{
			return driver.findElement(By.name(Object.getProperty(Key)));
		}catch(Throwable t){
			Add_Log.debug("Object not found for key --"+Key);
			return null;
		}
	}
	
	//Call this function to locate element by cssSelector Locator.
	public WebElement getElementByCSS(String Key){
		try{
			return driver.findElement(By.cssSelector(Object.getProperty(Key)));
		}catch(Throwable t){
			Add_Log.debug("Object not found for key --"+Key);
			return null;
		}
	}
	
	//Call this function to locate element by ClassName Locator.
	public WebElement getElementByClass(String Key){
		try{
			return driver.findElement(By.className(Object.getProperty(Key)));
		}catch(Throwable t){
			Add_Log.debug("Object not found for key --"+Key);
			return null;
		}
	}
	
	//Call this function to locate elements by ClassName Locator.
		public List<WebElement> getElementsByClass(String Key){
			try{
				return driver.findElements(By.className(Object.getProperty(Key)));
			}catch(Throwable t){
				Add_Log.debug("Object not found for key --"+Key);
				return null;
			}
		}
	
	//Call this function to locate element by tagName Locator.
	public WebElement getElementByTagName(String Key){
		try{
			return driver.findElement(By.tagName(Object.getProperty(Key)));
		}catch(Throwable t){
			Add_Log.debug("Object not found for key --"+Key);
			return null;
		}
	}
	
	//Call this function to locate element by link text Locator.
	public WebElement getElementBylinkText(String Key){
		try{
			return driver.findElement(By.linkText(Object.getProperty(Key)));
		}catch(Throwable t){
			Add_Log.debug("Object not found for key --"+Key);
			return null;
		}
	}
	
	//Call this function to locate element by partial link text Locator.
	public WebElement getElementBypLinkText(String Key){
		try{
			return driver.findElement(By.partialLinkText(Object.getProperty(Key)));
		}catch(Throwable t){
			Add_Log.debug("Object not found for key --"+Key);
			return null;
		}
	}
	
	//Call this function to perform explicit wait by "visibility of element located by Xpath"
		public void explicitWaitForVisibilityOfElement(String Element) {
			System.out.print(Object.getProperty(Element));
			WebDriverWait wait = new WebDriverWait(driver, 20);
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Object.getProperty(Element))));
		}
		
		
		public void explicitWaitForVisibilityOfElement(String Element1, String Val, String Element2) {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Object.getProperty(Element1)+Val+Object.getProperty(Element2))));
		}
		
		//Call this function to perform explicit wait by "visibility of element located by Class"
				public void explicitWaitForVisibilityOfElementByClass(String Element) {
					WebDriverWait wait = new WebDriverWait(driver, 20);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(Object.getProperty(Element))));
				}
		
		public void implicitWait(int Time){
			
			driver.manage().timeouts().implicitlyWait(Time, TimeUnit.SECONDS);
			
		}
}
