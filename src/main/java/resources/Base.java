package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageobjects.LoginPage;

public class Base {
	WebDriver driver = null;
	public Properties prop;
	
	public WebDriver initializeBrowser() throws IOException {
		
//		System.out.println(System.getProperty("user.dir"));
//		src/main/java/resources/data.properties 
		prop = new Properties();
		String propertiesPath = System.getProperty("user.dir")+"//src//main//java//resources//data.properties";
		FileInputStream fis = new FileInputStream(propertiesPath);
		prop.load(fis);
		
		String browserName = prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome")) {
			
//			WebDriverManager.chromedriver().setup();

//			System.getProperty("user.dir")+"\\src\\main\\java\\resources\\chromedriver.exe";

			String chromeDriverPath = prop.getProperty("chromeDriverPath");
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			driver = new ChromeDriver();
			
		}else if(browserName.equalsIgnoreCase("firefox")) {
			
//			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			
		}else if(browserName.equalsIgnoreCase("ie")) {
			
//			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		return driver;
		
	}
	
	public void selectDropdownValueByVisibleText(WebDriver driver, WebElement element, String dropdownText) {
		Select select = new Select(element);
		select.selectByVisibleText(dropdownText);
	}
	
	public void selectDropdownValueByValue(WebDriver driver, WebElement element, String dropdownValue) {
		Select select = new Select(element);
		select.selectByVisibleText(dropdownValue);
	}
	
	public void selectDropdownValueByIndex(WebDriver driver, WebElement element, int dropdownIndex) {
		Select select = new Select(element);
		select.selectByIndex(dropdownIndex);
	}
	
	public void moveToElementAndClick(WebDriver driver, WebElement element1, WebElement element2) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element1);
		actions.moveToElement(element2);
		actions.click().build().perform();
		
	}
	
	public void waitForElementToBePresent(WebDriver driver, String byLocator) {
		WebDriverWait webDriverWait = new WebDriverWait(driver,Duration.ofSeconds(6));
		WebElement element = webDriverWait.until
				(ExpectedConditions.visibilityOfElementLocated(By.xpath(byLocator)));
		
	}
	
	public String takeScreenshot(String testName,WebDriver driver) throws IOException {
		
		File SourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destinationFilePath = System.getProperty("user.dir")+"//screenshots//"+testName+".png";
		FileUtils.copyFile(SourceFile,new File(destinationFilePath));
		
		return destinationFilePath;
	
	}
	
	

}
