package Retailj.MyRetailj;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WelcomePage {
	private WebDriver driver;
	
	public WelcomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("welcomeMain");
	}
	
	
	@FindBy(id="WelcomeMessage")
	WebElement welcome;
	
	public String getWelcomeText() {
		return welcome.getText();
	}

}
