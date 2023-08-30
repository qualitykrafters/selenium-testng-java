package tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageobjects.LoginPage;
import resources.Base;

public class LoginTest extends Base{
	public WebDriver driver;
	Logger log ;
	LoginPage loginPage;

	@BeforeMethod
	public void openPortal() throws IOException {
		log = LogManager.getLogger(LoginTest.class.getName());
		
		driver = initializeBrowser();
		log.debug("Browser got launched");
		
		driver.get(prop.getProperty("url"));
		log.debug("Navigated to application URL");
	}
	
	@Test
	public void loginAndAddUser() throws IOException, InterruptedException {

		loginPage = new LoginPage(driver);

		String userId = prop.getProperty("loginID");
		loginPage.enterUserID().sendKeys(userId);

		String userPassword = prop.getProperty("loginPassword");
		loginPage.enterPassword().sendKeys(userPassword);

		loginPage.getClickLogin().click();
		Thread.sleep(2000);
		Assert.assertTrue(loginPage.afterLoginPageTittle().isDisplayed());
	
	}
	
	@AfterMethod
	public void closeBrowser() {
		driver.quit();
		log.debug("Browser got closed");
		
	}

}
