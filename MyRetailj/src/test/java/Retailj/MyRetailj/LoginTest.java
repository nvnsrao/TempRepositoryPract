package Retailj.MyRetailj;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utilities.BasePage;

public class LoginTest extends BasePage {
	String browser;
	Properties p = new Properties();
	
	public LoginTest() throws Exception {
		File f = new File("data.properties");
		FileInputStream fis = new FileInputStream(f);
		
		p.load(fis);
		browser = p.getProperty("browser");
		
		Runtime.getRuntime().exec("cmd /c D:\\Retail_J\\RJ12.1_Till\\StartWebServer2.bat");
		
	}
	
	
	@BeforeClass
	public void launchBrowser() {
		loadDriver(browser);
	}
	
	@Test(priority=0)
	public void navigatetoPage() {
		driver.get(p.getProperty("url"));
	}
	
	@Test(priority=1)
	public void logIntoRetailJ() {
		LoginPage lp = new LoginPage(driver);
		lp.logIntoRetailJ(p.getProperty("username"), p.getProperty("password"));
	}
	
	@Test(priority=2)
	public void welComeframeTest() {
		WelcomePage wp = new WelcomePage(driver);
		String text = wp.getWelcomeText();
		System.out.println("Welcome textis:"+text);
		driver.switchTo().defaultContent();
	}

}
