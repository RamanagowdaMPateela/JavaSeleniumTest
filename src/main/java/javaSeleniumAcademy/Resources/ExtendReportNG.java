package javaSeleniumAcademy.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;



public class ExtendReportNG {

	
	    public static ExtentReports extentReports;
	    public static ExtentSparkReporter htmlReporter;

	    public static ExtentReports getReportObject() {
	            
	    	    String path= System.getProperty("user.dir") + "\\reports\\extentReport.html";
	            htmlReporter = new ExtentSparkReporter(path);
	            htmlReporter.config().setDocumentTitle("Automation Report");
	            htmlReporter.config().setEncoding("utf-8");

	            extentReports = new ExtentReports();
	            extentReports.attachReporter(htmlReporter);

	            extentReports.setSystemInfo("OS", System.getProperty("os.name"));
	            extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
	            extentReports.setSystemInfo("Tester", "Ram");
	        
	        return extentReports;
	    }

}
