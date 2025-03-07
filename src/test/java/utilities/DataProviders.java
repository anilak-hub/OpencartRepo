package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	//DataProvider 1
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException {
		String path="C:\\Users\\anilk\\Desktop\\selenium practice\\Opencart121\\testData\\ddt.xlsx";//Taking excell file from testData
		
		ExcellUtility xlutil= new ExcellUtility(path);//Creating object of ExcellUtility class
		
		int tottalRows=xlutil.getRowCount("Sheet1");//getting row count in sheet1
		int totalCells=xlutil.getCellCount("Sheet1", 1);//getting cell count in sheet1
		
		String loginData[][]= new String[tottalRows][totalCells];//created two dimension array which can store login data
		
		for (int i = 1; i <=tottalRows; i++) 
		{
			for (int j = 0; j <totalCells; j++) 
			{
				loginData[i-1][j] = xlutil.getCelldata("Sheet1", i, j);
			}
		}
		return loginData;
	

	}
	
}
