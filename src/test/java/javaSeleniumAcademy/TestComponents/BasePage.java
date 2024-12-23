package javaSeleniumAcademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import javaSeleniumAcademy.pageObjects.LandingPage;

public class BasePage {

	public WebDriver driver;
	public LandingPage landingPage=null;
	

	public WebDriver initializeDriver() throws IOException {
		
		//properties class
		Properties prop = new Properties();
		FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\javaSeleniumAcademy\\Resources\\GlobalData.properties");
		prop.load(fis);
		
		String browserName= System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");

		
		if(browserName.contains("chrome")){
			ChromeOptions options= new ChromeOptions();
			if(browserName.contains("headless")){
				
				options.addArguments("headless");
			}
		    driver = new ChromeDriver(options);
	        driver.manage().window().setSize(new Dimension(1440, 900)); //full screen
		
		
		}else if(browserName.equalsIgnoreCase("fireFox")) {
			System.getProperty("webdriver.gecko.driver", "C:\\Users\\HP\\Downloads\\geckodriver-v0.35.0-win-aarch64\\geckodriver.exe");
			driver = new FirefoxDriver();
		}else if(browserName.equalsIgnoreCase("edge")) {
		    driver = new EdgeDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	
	@BeforeMethod(alwaysRun = true)
	 public LandingPage launchApp() throws IOException {
		
	   driver= initializeDriver();
	   landingPage=new LandingPage(driver);
	   landingPage.navigateToURl();
	   return landingPage;
	   
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}
	
	//REad Data- convert json to string to object
	public List<HashMap<String, String>> getJsonDataToMap(String filePath, String encodingType) throws IOException {
		
        //read json to string		
	     String jsonContent = FileUtils.readFileToString( new File(filePath), encodingType);

	     //String to HashMap using Jackson Databind 
		  ObjectMapper mapper = new ObjectMapper();
		 // HashMap<String, Object> data = mapper.readValue(jsonContent, HashMap.class); 
		  List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});
		  
		
		return data;
   }
	
	
	//screenshot
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts= (TakesScreenshot) driver;
		File source= ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+ "\\reports\\" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		
		return System.getProperty("user.dir")+ "\\reports\\" + testCaseName + ".png";
	}

}
