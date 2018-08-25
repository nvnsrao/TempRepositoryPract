package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ReadFromandTwoExcel {
	XSSFWorkbook inbook;
	XSSFWorkbook outbook=null;

	XSSFSheet inst;
	XSSFSheet outst;
	FileInputStream fis;
	FileOutputStream fos;
	File fin;
	File fout;
	@Test
	public void readwriteExcel() throws Exception {
		fin = new File("D:\\Selenium\\MavenProject\\MyRetailj\\TestData.xlsx");
		//fout = new File("D:\\Selenium\\MavenProject\\MyRetailj\\TestData.xlsx");

		fis = new FileInputStream(fin);
		
		inbook = new XSSFWorkbook(fis);
		//outbook = new XSSFWorkbook(fis);
		inst = inbook.getSheetAt(0);
		//outst = outbook.createSheet("outdata");
		outst = inbook.createSheet("outdata");

		int rowcount = inst.getLastRowNum()-inst.getFirstRowNum();
		int colcount = inst.getRow(0).getLastCellNum();
		
		for (int i=0;i<rowcount;i++) {
			Row row = inst.getRow(i);
			Row rout = outst.createRow(i);
			for (int j=0;j<colcount;j++) {
				Cell cell = row.getCell(j);
				Cell cout = rout.createCell(j);
				//String celltext= cell.get
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_NUMERIC:
					String nvalue = String.valueOf(cell.getNumericCellValue());
					cout.setCellValue(nvalue);
					break;
				case Cell.CELL_TYPE_STRING:
					String svalue = cell.getStringCellValue();
					cout.setCellValue(svalue);

					break;
				case Cell.CELL_TYPE_BLANK:
					String bvalue = "";
					cout.setCellValue(bvalue);

					break;
				}
			}
		}
		fout = new File("D:\\Selenium\\MavenProject\\MyRetailj\\TestData.xlsx");
		fos = new FileOutputStream(fout);
		inbook.write(fos);
		
	}

}
