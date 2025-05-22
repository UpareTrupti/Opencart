package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.activation.DataSource;

import org.apache.commons.mail.DataSourceResolver;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener
{
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extents;
	public ExtentTest test;
	
	String repName;
	
	public void onStart(ITestContext testcontext)
	{	
		/*
		SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt = new Date(0);
		String currentdatetimestamp=df.format(dt);
		*/
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date()); //fomarting current date in "yyyy.MM.dd.HH.mm.ss" format 
		
		repName = "Test-Report" + timeStamp +".html";		//Reportname with timestamp.html
		
		sparkReporter = new ExtentSparkReporter(".\\reports\\"+ repName);
		
		//Set UI using ExtentSparkReporter
		
		sparkReporter.config().setDocumentTitle("Opencart Automation Report");	//Title of report
		sparkReporter.config().setReportName("Opencart Functional Testing");	//Name of report
		sparkReporter.config().setTheme(Theme.DARK);
		
		//Set System info using ExtentReports
		
		extents =new ExtentReports();
		
		extents.attachReporter(sparkReporter);
		extents.setSystemInfo("Application", "Opencart");
		extents.setSystemInfo("Module", "Admin");
		extents.setSystemInfo("Submodule", "Customer");
		extents.setSystemInfo("User Name",System.getProperty("user.name"));
		extents.setSystemInfo("Environment", "QA");
		//Set operating system name dynamically will be populated from xml file we are running test case 
		String os =testcontext.getCurrentXmlTest().getParameter("os");
		extents.setSystemInfo("Operating System", os);
		//set browser name dynamically will be populated from xml file we are running test case
		String browser = testcontext.getCurrentXmlTest().getParameter("browser");
		extents.setSystemInfo("Browser", browser);
		//Set group name the test cases belong
		List<String> includedGroups =testcontext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty())	//if it is not empty then display in report
		{
			extents.setSystemInfo("Groups", includedGroups.toString());
		}
		
	}
	
	
	public void onTestSuccess(ITestResult result)
	{
		
		test = extents.createTest(result.getClass().getName());
		test.assignCategory(result.getMethod().getGroups());	//to display group in report
		test.log(Status.PASS,result.getName()+" got successfully executed..!");
		
	}
	
	public void onTestFailure(ITestResult result)
	{
		test = extents.createTest(result.getClass().getName());
		test.assignCategory(result.getMethod().getGroups());		//to display group in report
		test.log(Status.FAIL,result.getName()+"got failed..");
		test.log(Status.INFO, result.getThrowable().getMessage());	//print error message
		
		try
		{
			//Capture screenshot
			BaseClass bc = new BaseClass();
			
			String  imgPath= bc.captureScreenShot(result.getName());	//pass method name from result
			test.addScreenCaptureFromPath(imgPath);
		}
		catch (Exception e)
		{
			//if screenshot is not available or any issue (FileNotFoundException)
			e.printStackTrace();	//it will display warning message in console
			
		}
		
	}

	public void onTestSkipped(ITestResult result)
	{
		test =extents.createTest(result.getClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP,result.getName()+"got skipped");
		test.log(Status.INFO,result.getThrowable().getMessage());
	}
	
	public void onFinish(org.testng.ITestContext context)
	{
		extents.flush();     //collect all information from report
		
		/*------- After completing execution of test  open report through automation -------*/
		
		
		String ExtentReportPath = System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReport = new File(ExtentReportPath);
		try
		{
			Desktop.getDesktop().browse(extentReport.toURI());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		/*-----------------Send Report via email automatically----------------------------------- */
		
		
		
		/*
		try 
		{
			//convert report path into URL format 
			URL url = new URL(System.getProperty("user.dir")+"\\reports\\"+repName);
			
			ImageHtmlEmail email = new ImageHtmlEmail();
			
			email.setDataSourceResolver(new DataSourceUrlResolver(url));
			email.setHostName("smtp.googleeail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("sendername@gmail.com","sendersemailpassword"));//who is sending email.pass his email address and password
		    email.setSSLOnConnect(true);
		    email.setFrom("sendername@gmail.com"); //sender
		    email.setSubject("Test report result");
		    email.setMsg("Please find attached test report");
		    email.addTo("receivername@gmail.com");	//can add multiple receivers name.
		    email.attach(url,"Extent Report", "Please check report");
		    email.send();	//will send email.
		    
			
		} 
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		} catch (EmailException e) {
			
			e.printStackTrace();
		}
		*/
		
		
				
	}
}
