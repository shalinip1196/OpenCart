package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

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
import org.apache.logging.log4j.LogManager;  //log4j
import org.apache.logging.log4j.Logger; 

/*
 * To achieving the Reusability 
 * Suppose TC1 setup() and tearDown() 
 * TC2 needs setup() and tearDown()
 * TC3 needs setup() and tearDown()
 * 
 * Here every class needs setup() and teardown() instead if we created seperate baseclass for this setup() and teradown()
 * We reuse it whenever it's required
 
 */
public class BaseClass {
	
  public static WebDriver driver;
  
  public Logger logger; //log4j
  public Properties p;
	
  @BeforeClass(groups= {"Sanity", "Regression", "Master"})
  @Parameters({"os","browser","url"})
  public void setup(String os, String br, String url) throws IOException {

      FileReader file = new FileReader("D:\\SeleniumWorkspace\\OpenCart\\src\\test\\resources\\config.properties");
      p = new Properties();
      p.load(file);

      logger = LogManager.getLogger(this.getClass());

      DesiredCapabilities capabilities = new DesiredCapabilities();

      if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {
          if (os.equalsIgnoreCase("windows")) {
              capabilities.setPlatform(Platform.WIN11);
          } else if (os.equalsIgnoreCase("mac")) {
              capabilities.setPlatform(Platform.MAC);
          }else if(os.equalsIgnoreCase("linux")) {
        	  capabilities.setPlatform(Platform.LINUX);
          }

          switch (br.toLowerCase()) {
              case "chrome": capabilities.setBrowserName("chrome"); break;
              case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
              case "firefox": capabilities.setBrowserName("firefox"); break;
          }

          driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);

      } else if (p.getProperty("execution_env").equalsIgnoreCase("local")) {
          switch (br.toLowerCase()) {
              case "chrome": driver = new ChromeDriver(); break;
              case "edge": driver = new EdgeDriver(); break;
              case "firefox": driver = new FirefoxDriver(); break;
              default: System.out.println("Invalid Browser"); return;
          }
      }

      driver.manage().deleteAllCookies();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
      driver.get(url);
      driver.manage().window().maximize();
  }
  @AfterClass(groups= {"Sanity", "Regression", "Master"})
	public void tearDown()
	{
		driver.quit();
	}
	
	public String randomString()
	{
		String generatedstring= RandomStringUtils.randomAlphabetic(5);
		return generatedstring;
	}
	
	public String randomNumber()
	{
		String generatednumber = RandomStringUtils.randomNumeric(10);
		return generatednumber;
	}
	
	public String randomAlphaNumeric()
	{
		String generatedstring = RandomStringUtils.randomAlphabetic(3);
		String generatednumber = RandomStringUtils.randomNumeric(3);
		return (generatedstring+"@"+generatednumber);
	}
	
	public String captureScreen(String tname) throws IOException
	{
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname +"_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		return targetFilePath;
		
	}
  
  
  
  
  
  
  
  
  
  
  
  
  
	/*@BeforeClass(groups= {"Sanity", "Regression", "Master"})
	@Parameters({"os","browser","url"})
	public void setup(String os, String br, String url, DesiredCapabilities capabilities) throws IOException
	{
		
		//Loading config.properties file
		FileReader file = new FileReader("D:\\SeleniumWorkspace\\OpenCart\\src\\test\\resources\\config.properties");
		p=new Properties();
		p.load(file);
		
		
		logger=LogManager.getLogger(this.getClass()); //logger variables to generate the logs file
		
		//remote execution
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities1 = new DesiredCapabilities();
			//os
			
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities1.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				capabilities1.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("No matching OS");
				return;
			}
		}
		
		//browser
		switch(br.toLowerCase())
		{
		case "chrome": capabilities.setBrowserName("chrome"); break;
		case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
		default: System.out.println("No matching browser");
		}
		
		driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
		
		
		
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
		//for parallel execution locally
			switch(br.toLowerCase())
			{
			case "chrome": driver=new ChromeDriver(); break;
			case "edge": driver=new EdgeDriver(); break;
			case "firefox": driver=new FirefoxDriver(); break;
			default : System.out.println("Invalid Browser"); return;
			}
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("addURL")); //reading url from the properties file
		driver.manage().window().maximize();
		
	}
	
	@AfterClass(groups= {"Sanity", "Regression", "Master"})
	public void tearDown()
	{
		driver.quit();
	}
	
	public String randomString()
	{
		String generatedstring= RandomStringUtils.randomAlphabetic(5);
		return generatedstring;
	}
	
	public String randomNumber()
	{
		String generatednumber = RandomStringUtils.randomNumeric(10);
		return generatednumber;
	}
	
	public String randomAlphaNumeric()
	{
		String generatedstring = RandomStringUtils.randomAlphabetic(3);
		String generatednumber = RandomStringUtils.randomNumeric(3);
		return (generatedstring+"@"+generatednumber);
	}
	
	public String captureScreen(String tname) throws IOException
	{
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname +"_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		return targetFilePath;
		
	}
*/
}
