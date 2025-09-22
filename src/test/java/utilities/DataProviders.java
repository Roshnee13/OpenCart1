package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException
	{
		String path = ".\\testData\\Opencart_LoginData.xlsx"; //taking xl file from testdata
		
		utilityExcel xlutil = new utilityExcel(path); //creating object for xlutility
		
		int totalrows=xlutil.getRowCount("Sheet1");	
		int totalcols=xlutil.getCellCount("Sheet1",1);
		
		String logindata[][]= new String[totalrows][totalcols]; //created 2D array for storing data
		
		for(int i=1;i<=totalrows;i++) //1 read data from xl 
		{
			for(int j=0;j<totalcols;j++) //0 i is row j is col
			{
				logindata[i-1][j] = xlutil.getCellData("Sheet1", i, j);
			}
		}	
		return logindata;		//retuening 2D array	
	}
}
