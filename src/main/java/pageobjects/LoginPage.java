package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css = "a[title='My Account']")
	WebElement clickMyAccountForLoginOrSignUp;
	public WebElement getClickMyAccountForLoginOrSignUp(){
		return  clickMyAccountForLoginOrSignUp;
	}


	@FindBy(id = "user-name")
	WebElement enterUserIdElement;
	public WebElement enterUserID() {
		return enterUserIdElement;
	}
	
	@FindBy(css = "#password")
	WebElement enterPasswordElement;
	public WebElement enterPassword() {
		return enterPasswordElement;
	}

	@FindBy(id = "login-button")
	WebElement getClickLoginButtonElement;
	public WebElement getClickLogin(){
		return  getClickLoginButtonElement;
	}

	@FindBy(css = ".app_logo")
	WebElement afterLoginPageTittleElement;
	public WebElement afterLoginPageTittle(){
		return afterLoginPageTittleElement;
	}

}
