package WikipediaExcelDrivenTesting;

import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ExcelHandler extends WebHandler {

    private static Workbook wb;
    private static Sheet sheet;
    private static FileInputStream fis;
    private static FileOutputStream fos;
    private static Row row;
    private static Cell cell;

    public static void main(String[] args) throws Exception {

        // creating  object from excel
        fis = new FileInputStream("././testData.xlsx");
        wb = WorkbookFactory.create(fis);
        sheet = wb.getSheet("Sheet1");
        int noOfRows = sheet.getLastRowNum();
        System.out.println("Total Search Cases = " +noOfRows);


        // loop for every search data
        for (int i =1; i<=noOfRows; i ++){

            Row r = sheet.getRow(i);
          String searchText = String.valueOf(sheet.getRow(i).getCell(0));  //first search text

            //method calling for result from webHandler
          String result =  WebHandler.wikiTest(searchText);

          // writing the result in excel object
            row = sheet.createRow(i);
            cell = row.createCell(0);
            cell.setCellValue(searchText);
            cell = row.createCell(1);
            cell.setCellValue(result);

            // writing the results in excel file
            fos = new FileOutputStream("././testData.xlsx");
            wb.write(fos);
            fos.flush();
            fos.close();
            System.out.println("Search Output of Search Case "+ i +" Written in excel");

        }

    }
}
