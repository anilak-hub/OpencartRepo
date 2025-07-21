package testCases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
//Log4j imports
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	public static WebDriver driver;
	public Logger logger; //Log4j
	public Properties p;

	@BeforeClass(groups = {"regression","Sanity", "Master", "DataDriven"})
	@Parameters({"os", "browser"})
	public void setup(String os, String br) throws IOException {
		
		logger=LogManager.getLogger(this.getClass());
		
		//Loading Config.properties file
		FileReader file = new FileReader("./src//test//resources//config.properties");
		
		p=new Properties();
		p.load(file);
		
		switch (br.toLowerCase()) {
		case "chrome":driver=new ChromeDriver(); break;
		case "edge" : driver = new EdgeDriver(); break;
		case "firefox" : driver = new FirefoxDriver(); break;
		default:System.out.println("Invalid browser name"); return;
		}
		
		//driver=new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://tutorialsninja.com/demo/");
		driver.get(p.getProperty("url"));
		driver.manage().window().maximize();
	}
	@AfterClass(groups = {"regression","Sanity", "Master","DataDriven"})
	public void tearDown() {
		driver.close();
	}
	
	
	//Code to generate random string
	public String randomString() {
		String generatedString = RandomStringUtils.randomAlphabetic(6);
		return generatedString;
	}
	
	public String randomNumber() {
		String generatedNumber = RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}
	 public String randomPswd() {
			String generatedString = RandomStringUtils.randomAlphabetic(6);
			String generatedNumber = RandomStringUtils.randomNumeric(10);
		 return (generatedString+"@"+generatedNumber);
	 }
	 
	 
	 public String captureScreen(String tname) throws IOException {
		 
		 //Time stamp for screenshot
		 String timeStamp = new SimpleDateFormat("yyyy.MM.dd.hh.ss").format(new Date());
			
		 //Takes screenshot
		 File SourceFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		 
		 //sets path of the File and gives new name to the file every time
		 String targetFilePath=System.getProperty("user.dir")+ "\\screenshots"+tname+"_"+timeStamp;
		 
		 //creates new file in the same folder every time
		 File targetFile= new File(targetFilePath);
		 
		 //Renaming the file and saving in the folder
		 SourceFile.renameTo(targetFile);
		 //returning the path of file
		 return targetFilePath;
	 }
	 
	 
}
