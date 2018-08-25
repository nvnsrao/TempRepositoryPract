package utilities;




import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class ReadFromWebandWritetoExcel {
	
	@Test
	public void readWebWriteToExcel() {
		File f = new File("WebData.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet st = wb.createSheet();
		System.setProperty("webdriver.chrome.driver", "WebDriver/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://demo.guru99.com/test/table.html");
		
		WebElement table = driver.findElement(By.xpath("//table/tbody"));
		
	List<WebElement> trtags = table.findElements(By.tagName("tr"));
	int row=trtags.size();
	for (int i=0;i<row;i++) {
		List<WebElement> tdtags=trtags.get(i).findElements(By.tagName("td"));
		Row r = st.createRow(i);
		int col=tdtags.size();
		for (int j=0;j<col;j++) {
			Cell c = r.createCell(j);
			String text=tdtags.get(j).getText();
			c.setCellValue(text);
			
		}
	}
	FileOutputStream fo=null;
	try {
		fo=new FileOutputStream(f);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		wb.write(fo);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		try {
			fo.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
