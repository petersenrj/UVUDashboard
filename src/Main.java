import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		
		FileInputStream in = new FileInputStream(new File("test.xls"));
		
		HSSFWorkbook workbook = new HSSFWorkbook(in);
		
		HSSFSheet sheet = workbook.getSheet("test");
		
		int colNum = sheet.getRow(0).getLastCellNum();
		
		HSSFRow row = sheet.getRow(0);
		
		int cost =0, taxes=0, income=0;
		for(int i = 0; i < colNum; i++)
		{
			
			HSSFCell cell = row.getCell(i);
			String temp = cell.toString();
			if(temp.equals("Cost"))
			{
				cost = i;
			}
			else if(temp.equals("taxes"))
			{
				taxes = i;
			}
			else if(temp.equals("monthly rental income"))
			{
				income = i;
			}
			
		}
		
		int rowNum = sheet.getLastRowNum() + 1;
		
		calculateIndividualReturn calc = new calculateIndividualReturn();
		
		ArrayList<Double> returns = new ArrayList<Double>();
		
		
		int cost1 = 0, taxes1 = 0, income1 = 0;
		for(int j = 1; j < rowNum; j++)
		{
			row = sheet.getRow(j);
			HSSFCell cellCost = row.getCell(cost);
			HSSFCell cellTaxes = row.getCell(taxes);
			HSSFCell cellIncome = row.getCell(income);
			String costString = cellCost.toString();
			String costTaxes = cellTaxes.toString();
			String costIncome = cellIncome.toString();
			double tempCost = Double.parseDouble(costString);
			double tempTaxes = Double.parseDouble(costTaxes);
			double tempIncome = Double.parseDouble(costIncome);
			
			returns.add(calc.calculate(tempCost, tempTaxes, tempIncome));
		}
		
		System.out.println("Property 1 = " + returns.get(0));
		System.out.println("Property 2 = " + returns.get(1));
		System.out.println("Property 3 = " + returns.get(2));
		System.out.println("Property 4 = " + returns.get(3));
		
		
		
		
//		HSSFWorkbook workbook = new HSSFWorkbook();
//		HSSFSheet sheet = workbook.createSheet("FirstExcelSheet");
//		HSSFRow row = sheet.createRow(0);
//		HSSFCell cell = row.createCell(0);
//		cell.setCellValue("1. Cell");
//		
//		workbook.write(new FileOutputStream("excel.xls"));
//		workbook.close();
//		System.out.println("hello");
//		Connection conn = sqliteConnetion.dbConnector();
		

	}

}
