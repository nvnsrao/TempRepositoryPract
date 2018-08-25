package Retailj.MyRetailj;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	private WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		driver.switchTo().frame("mainFrame");
	}
	
	@FindBy(id="uid")
	WebElement userName;
	
	@FindBy(id="pwd")
	WebElement password;
	
	@FindBy(id="Login")
	WebElement loginBtn;
	
	public void setUsername(String uName) {
		userName.sendKeys(uName);
	}
	
	public void setPassword(String pwd) {
		password.sendKeys(pwd);
	}
	
	public void clickLogin() {
		loginBtn.click();
	}
	
	public void logIntoRetailJ(String user,String pass) {
		setUsername(user);
		setPassword(pass);
		clickLogin();
		
	}

}
