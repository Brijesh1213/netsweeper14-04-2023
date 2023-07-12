package utility;

import java.awt.Color; 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.LinkOption;
import java.util.Calendar;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;



public class ExcelMethods {

	private Workbook wb;
	private Sheet sh;
	private Row row;
	private Column column;
	private Cell cell;
	public FileInputStream fis;
	public FileOutputStream fos;
	public String path;
	
	
	public ExcelMethods(String sheetName, String path) throws Throwable {
		try {
		this.path=path;
		fis = new FileInputStream(path);
		wb = WorkbookFactory.create(fis);
		sh = wb.getSheet(sheetName);
		fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 // returns the row count in a sheet
	public int getRowCount(String sheetName){
			int index = wb.getSheetIndex(sheetName);
			if(index==-1)
				return 0;
			else{
			sh = wb.getSheetAt(index);
			int number=sh.getLastRowNum()+1;
			return number;
			}
			
		}
	// returns the data from a cell
		public String getCellData(String sheetName,String colName,int rowNum){
			try{
				if(rowNum <=0)
					return "";
			
			int index = wb.getSheetIndex(sheetName);
			int col_Num=-1;
			if(index==-1)
				return "";
			
			sh = wb.getSheetAt(index);
			row=sh.getRow(0);
			for(int i=0;i<row.getLastCellNum();i++){
				//System.out.println(row.getCell(i).getStringCellValue().trim());
				if(row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
					col_Num=i;
			}
			if(col_Num==-1)
				return "";
			
			sh = wb.getSheetAt(index);
			row = sh.getRow(rowNum-1);
			if(row==null)
				return "";
			cell = row.getCell(col_Num);
			
			if(cell==null)
				return "";
			//System.out.println(cell.getCellType());
			if(cell.getCellType()==CellType.STRING)
				  return cell.getStringCellValue();
			else if(cell.getCellType()==CellType.NUMERIC || cell.getCellType()==CellType.FORMULA ){
				  
				  String cellText  = String.valueOf(cell.getNumericCellValue());
				  if (DateUtil.isCellDateFormatted(cell)) {
			           // format in form of M/D/YY
					  double d = cell.getNumericCellValue();
	
					  Calendar cal =Calendar.getInstance();
					  cal.setTime(DateUtil.getJavaDate(d));
			            cellText =
			             (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
			           cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" +
			                      cal.get(Calendar.MONTH)+1 + "/" + 
			                      cellText;
			           
			           //System.out.println(cellText);
	
			         }
	
				  
				  
				  return cellText;
			  }else if(cell.getCellType()==CellType.BLANK)
			      return ""; 
			  else 
				  return String.valueOf(cell.getBooleanCellValue());
			
			}
			catch(Exception e){
				
				e.printStackTrace();
				return "row "+rowNum+" or column "+colName +" does not exist in xls";
			}
		}
		
		// returns the data from a cell
		public String getCellData(String sheetName,int colNum,int rowNum){
			try{
				if(rowNum <=0)
					return "";
			
			int index = wb.getSheetIndex(sheetName);
	
			if(index==-1)
				return "";
			
		
			sh = wb.getSheetAt(index);
			row = sh.getRow(rowNum-1);
			if(row==null)
				return "";
			cell = row.getCell(colNum);
			if(cell==null)
				return "";
			
		  if(cell.getCellType()==CellType.STRING)
			  return cell.getStringCellValue();
		  else if(cell.getCellType()==CellType.NUMERIC || cell.getCellType()==CellType.FORMULA ){
			  
			  String cellText  = String.valueOf(cell.getNumericCellValue());
			  
			  return cellText;
		  }else if(cell.getCellType()==CellType.BLANK)
		      return "";
		  else 
			  return String.valueOf(cell.getBooleanCellValue());
			}
			catch(Exception e){
				
				e.printStackTrace();
				return "row "+rowNum+" or column "+colNum +" does not exist  in xls";
			}
		}
		
		
		// returns true if data is set successfully else false
		public boolean setCellData(String sheetName,String colName,int rowNum, String data){
			try{
			
			if(rowNum<=0)
				return false;
			
			int index = wb.getSheetIndex(sheetName);
			int colNum=-1;
			if(index==-1)
				return false;
			
			
			sh = wb.getSheetAt(index);
			
	
			row=sh.getRow(0);
			for(int i=0;i<row.getLastCellNum();i++){
				//System.out.println(row.getCell(i).getStringCellValue().trim());
				if(row.getCell(i).getStringCellValue().trim().equals(colName))
					colNum=i;
			}
			if(colNum==-1)
				return false;
	
			sh.autoSizeColumn(colNum); 
			row = sh.getRow(rowNum-1);
			if (row == null)
				row = sh.createRow(rowNum-1);
			
			cell = row.getCell(colNum);	
			if (cell == null)
		        cell = row.createCell(colNum);
	
		    // cell style
		    CellStyle cs = wb.createCellStyle();
		    cs.setWrapText(true);
		    cell.setCellStyle(cs);
		    cell.setCellValue(data);
	
		    fos = new FileOutputStream(path);
	
			wb.write(fos);
	
		    fos.close();	
	
			}
			catch(Exception e){
				e.printStackTrace();
				return false;
			}
			return true;
		}
		
		// returns true if data is set successfully else false
		public boolean setCellData(String sheetName,String colName,int rowNum, String data,String url){
			//System.out.println("setCellData setCellData******************");
			try{
			
	
			if(rowNum<=0)
				return false;
			
			int index = wb.getSheetIndex(sheetName);
			int colNum=-1;
			if(index==-1)
				return false;
			
			
			sh = wb.getSheetAt(index);
			//System.out.println("A");
			row=sh.getRow(0);
			for(int i=0;i<row.getLastCellNum();i++){
				//System.out.println(row.getCell(i).getStringCellValue().trim());
				if(row.getCell(i).getStringCellValue().trim().equalsIgnoreCase(colName))
					colNum=i;
			}
			
			if(colNum==-1)
				return false;
			sh.autoSizeColumn(colNum); //ashish
			row = sh.getRow(rowNum-1);
			if (row == null)
				row = sh.createRow(rowNum-1);
			
			cell = row.getCell(colNum);	
			if (cell == null)
		        cell = row.createCell(colNum);
				
		    cell.setCellValue(data);
		    CreationHelper createHelper = wb.getCreationHelper();
	
		    //cell style for hyperlinks
		    //by default hypelrinks are blue and underlined
		    CellStyle hlink_style = wb.createCellStyle();
		   Font hlink_font = wb.createFont();
		    hlink_font.setUnderline(Font.U_SINGLE);
		    hlink_font.setColor(IndexedColors.BLUE.getIndex());
		    hlink_style.setFont(hlink_font);
		    //hlink_style.setWrapText(true);
	
		    Hyperlink link = createHelper.createHyperlink(HyperlinkType.FILE);
		  
		    link.setAddress(url);
		    cell.setHyperlink(link);
		    cell.setCellStyle(hlink_style);
		      
		    fos = new FileOutputStream(path);
			wb.write(fos);
	
		    fos.close();	
	
			}
			catch(Exception e){
				e.printStackTrace();
				return false;
			}
			return true;
		}
		
	//	
		
		
		
		// returns true if sheet is created successfully else false
		public boolean addSheet(String  sheetname){		
			
			FileOutputStream fileOut;
			try {
				 wb.createSheet(sheetname);	
				 fileOut = new FileOutputStream(path);
				 wb.write(fileOut);
			     fileOut.close();		    
			} catch (Exception e) {			
				e.printStackTrace();
				return false;
			}
			return true;
		}
		
		// returns true if sheet is removed successfully else false if sheet does not exist
		public boolean removeSheet(String sheetName){		
			int index = wb.getSheetIndex(sheetName);
			if(index==-1)
				return false;
			
			FileOutputStream fileOut;
			try {
				wb.removeSheetAt(index);
				fileOut = new FileOutputStream(path);
				wb.write(fileOut);
			    fileOut.close();		    
			} catch (Exception e) {			
				e.printStackTrace();
				return false;
			}
			return true;
		}
		// returns true if column is created successfully
		public boolean addColumn(String sheetName,String colName){
			//System.out.println("**************addColumn*********************");
			
			try{				
				
				int index = wb.getSheetIndex(sheetName);
				if(index==-1)
					return false;
				
			CellStyle style = wb.createCellStyle();
			style.setFillForegroundColor((org.apache.poi.ss.usermodel.Color) Color.gray);
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			
			sh=wb.getSheetAt(index);
			
			row = sh.getRow(0);
			if (row == null)
				row = sh.createRow(0);
			
			//cell = row.getCell();	
			//if (cell == null)
			//System.out.println(row.getLastCellNum());
			if(row.getLastCellNum() == -1)
				cell = row.createCell(0);
			else
				cell = row.createCell(row.getLastCellNum());
		        
		        cell.setCellValue(colName);
		        cell.setCellStyle(style);
		        
		        fos = new FileOutputStream(path);
				wb.write(fos);
			    fos.close();		    
	
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
			
			return true;
			
			
		}
		// removes a column and all the contents
		public boolean removeColumn(String sheetName, int colNum) {
			try{
			if(!isSheetExist(sheetName))
				return false;
			
			sh=wb.getSheet(sheetName);
			CellStyle style = wb.createCellStyle();
			style.setFillForegroundColor((org.apache.poi.ss.usermodel.Color) Color.GRAY);
			CreationHelper createHelper = wb.getCreationHelper();
			style.setFillPattern(FillPatternType.NO_FILL);
			
		    
		
			for(int i =0;i<getRowCount(sheetName);i++){
				row=sh.getRow(i);	
				if(row!=null){
					cell=row.getCell(colNum);
					if(cell!=null){
						cell.setCellStyle(style);
						row.removeCell(cell);
					}
				}
			}
			fos = new FileOutputStream(path);
			wb.write(fos);
		    fos.close();
			}
			catch(Exception e){
				e.printStackTrace();
				return false;
			}
			return true;
			
		}
	  // find whether sheets exists	
		public boolean isSheetExist(String sheetName){
			int index = wb.getSheetIndex(sheetName);
			if(index==-1){
				index=wb.getSheetIndex(sheetName.toUpperCase());
					if(index==-1)
						return false;
					else
						return true;
			}
			else
				return true;
		}
		
		// returns number of columns in a sheet	
		public int getColumnCount(String sheetName){
			// check if sheet exists
			if(!isSheetExist(sheetName))
			 return -1;
			
			sh = wb.getSheet(sheetName);
			row = sh.getRow(0);
			
			if(row==null)
				return -1;
			
			return row.getLastCellNum();
			
			
			
		}
		//String sheetName, String testCaseName,String keyword ,String URL,String message
		public boolean addHyperLink(String sheetName,String screenShotColName,String testCaseName,int index,String url,String message){
			//System.out.println("ADDING addHyperLink******************");
			
			url=url.replace('\\', '/');
			if(!isSheetExist(sheetName))
				 return false;
			
		    sh = wb.getSheet(sheetName);
		    
		    for(int i=2;i<=getRowCount(sheetName);i++){
		    	if(getCellData(sheetName, 0, i).equalsIgnoreCase(testCaseName)){
		    		//System.out.println("**caught "+(i+index));
		    		setCellData(sheetName, screenShotColName, i+index, message,url);
		    		break;
		    	}
		    }
	
	
			return true; 
		}
		public int getCellRowNum(String sheetName,String colName,String cellValue){
			
			for(int i=2;i<=getRowCount(sheetName);i++){
		    	if(getCellData(sheetName,colName , i).equalsIgnoreCase(cellValue)){
		    		return i;
		    	}
		    }
			return -1;
			
		}	

}
