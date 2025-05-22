package testBase;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Properties;
import java.util.*;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.beust.jcommander.Parameter;

public class BaseClass
{
	public  WebDriver driver;
	public Properties prp;
	
	
	@BeforeClass(groups = {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	 public void setup(String os , String brsr) throws IOException
	{
		
		FileInputStream file = new FileInputStream("./src//test//resources//config.properties");
		prp = new Properties();
		prp.load(file);
		/*--------------------Decide Environment-----------------------------------------*/
		if(prp.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			//get hub url
			String hubrl_string = "http://192.168.141.225:4444";
			//convert into URL;
			URL huburl = new URL(hubrl_string);
			
			DesiredCapabilities cap = new DesiredCapabilities();
			
			//set os
			
				switch(os.toLowerCase())
				{
				case "windows":cap.setPlatform(Platform.WIN11);break;
				case "mac":cap.setPlatform(Platform.MAC);break;
				case "linux":cap.setPlatform(Platform.LINUX);break;
				default:System.out.println("No matching operating  ");return;
				
				}
			//set browser
				
				switch(brsr.toLowerCase())
				{
				case "chrome":cap.setBrowserName("Chrome");break;
				case "edge":cap.setBrowserName("MicrosoftEdge");break;
				case "firefox":cap.setBrowserName("Firefox");break;
				default:System.out.println("No matching browser ");	return;
				}
				
				driver= new RemoteWebDriver(new URL("http://192.168.141.225:4444/wd/hub"),cap);
			
		}
		
		if(prp.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch(brsr.toLowerCase())
			{
			case "chrome":driver =new ChromeDriver();break;
			case "edge":driver =new EdgeDriver();break;
			case "firefox":driver =new FirefoxDriver();break;
			default:return;
			}
		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		driver.get(prp.getProperty("appurl"));
	}
	
	@AfterClass(groups = {"Sanity","Regression","Master"})
	public void tearDown()
	{
		driver.quit();
	}
	
	
	public String randomStrings()
	{
	
		String randomString = RandomStringUtils.randomAlphabetic(5);
		return randomString;
	}
	
	public String  randomNumeric()
	{
		String randomNumber = RandomStringUtils.randomNumeric(10);
		return randomNumber;
	}
	
	public String randomAlphaNumeric()
	{
		String generate_alphabets = RandomStringUtils.randomAlphabetic(5);
		String generate_numbers = RandomStringUtils.randomNumeric(4);
		return 	(generate_alphabets+ "@" + generate_numbers );
	}
	
	public String  captureScreenShot(String ssname)	//will receive method name here. 
	{
		
		TakesScreenshot takess = (TakesScreenshot) driver;
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		
		//capture screenshot 
		File src_file =  takess.getScreenshotAs(OutputType.FILE);
		
		//create a target path 
		String target_path = System.getProperty("user.dir")+"\\screenshots\\"+ssname+"_"+timeStamp+ ".png";
		
		//copy that file in our targeted path
		File target_file = new File(target_path);
		
		//copy src_file to target_file
		src_file.renameTo(target_file);
		
		//return target file path to then only it will get attached in report.
		return target_path;
		
		//this method is called by ExtentReportManager class whenever test gets failed.
		
	}

}
