package test;

import utility.ExcelMethods;

public class xx {

	
	static ExcelMethods excel;
	public xx() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		excel = new ExcelMethods("Sheet1", ".\\src\\test\\resources\\testData.xlsx");
  System.out.println(excel.getCellData("Sheet1", "URL", 2));
	}

}
