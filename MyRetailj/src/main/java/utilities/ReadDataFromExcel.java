package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ReadDataFromExcel {
	//static Object[][] rs;
	static int rowcount;
	static int colcount;
	@Test(priority=0)
	public void readData() {
		Object[][] rs;
		FileInputStream fis=null;
		XSSFSheet st=null;
		XSSFWorkbook wb;
		File f = new File("TestSuite1Data.xlsx");
		try {
			 fis= new FileInputStream(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			 wb = new XSSFWorkbook(fis);
			 st = wb.getSheetAt(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int rc = st.getLastRowNum()-st.getFirstRowNum();
		int cc=st.getRow(0).getLastCellNum();
		rs=new Object[rc+1][cc];
		rowcount=rc;
		colcount=cc;
		
		for (int i=0;i<rc+1;i++) {
			Row row = st.getRow(i);
			System.out.println("Row Number is:"+i);
			for (int j=0;j<cc;j++) {
				Cell cell = row.getCell(j,org.apache.poi.ss.usermodel.Row.CREATE_NULL_AS_BLANK);
				System.out.println("Column Number is: "+j);
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_BLANK:
					//String bval=cell.getStringCellValue();
					String bval="";
					System.out.println(bval);
					rs[i][j]=bval;
					break;
				case Cell.CELL_TYPE_STRING:
					String sval = cell.getStringCellValue();
					System.out.println(sval);
					rs[i][j]=sval;
					break; 
				case Cell.CELL_TYPE_NUMERIC:
					String nval = String.valueOf(cell.getNumericCellValue());
					rs[i][j]=nval;
					break;
					
				}
				
			}
		}
		writeData("write",rs,rowcount+1,colcount);
		
	}
	
	
	//@Test(priority=1)
	public void writeData(String mywrite,Object[][] rs,int rowcount,int colcount) {
		File f = new File("TestSuite1Data.xlsx");
		FileInputStream fis=null;
		XSSFWorkbook wb=null;
		try {
			fis = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			wb = new XSSFWorkbook(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XSSFSheet st = wb.createSheet(mywrite);
		
		for (int i=0;i<rowcount;i++) {
			Row row = st.createRow(i);
			for (int j=0;j<colcount;j++) {
				Cell cell = row.createCell(j);
				//String text = rs[i][j].toString();
				
				cell.setCellValue(rs[i][j].toString());
			}
		}
		FileOutputStream fo=null;
		try {
			 fo = new FileOutputStream(f);
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
		
	}

}
