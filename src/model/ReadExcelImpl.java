package model;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
/*
*
* 这个class实现ReadExcelInterface中的getData_2DArray方法
*
* double[][] getData_2DArray(String)：
*       这个方法会返回一个二维数组
*
* */

public class ReadExcelImpl implements ReadExcelService{

   /*      传入xls文件路径：  xlsAddress（String）；
                    如   "C:/Users/99455/Desktop/testExcel/lz01_MOD.xls"
            返回xls文件二维数组：  finalArray（double[][]）;
                    columns = 20，rows = 35
                    20列代表20组灵芝三萜，1列中有35行指标                      */
   @Override
    public double[][] getData_2DArray(String xlsAddress) {
        File xlsData = new File(xlsAddress);
       try {
           Workbook book = Workbook.getWorkbook(xlsData);
           Sheet sheet = book.getSheet(0);
           //  最后需要的二维数组
           double [][] finalArray = new double[sheet.getRows() - 2][sheet.getColumns() - 2];
           for (int columns = 0 ; columns < 20 ; columns++) {
               //  用ArrayList暂存该列
               ArrayList<String> numberG_ArrayList = new ArrayList<String>();
               for (int rows = 1; rows < sheet.getRows() - 1; rows++) {
                   Cell cell = sheet.getCell(columns + 1, rows);
                   numberG_ArrayList.add(cell.getContents());
               }
               //  用String[]转存
               String[] numberG_String = (String[]) numberG_ArrayList.toArray(new String[numberG_ArrayList.size()]);
               //  用double[]转存
               double[] numberG_Double = new double[numberG_ArrayList.size()];
               for (int i = 0; i < numberG_String.length; i++) {
                   numberG_Double[i] = Double.parseDouble("".equals(numberG_String[i].toString()) ? "0.00" : numberG_String[i].toString());
               }
               //  刚读取double[]，转存为double[][]中1列
               for (int rows = 0 ; rows < numberG_String.length ; rows++) {
                   finalArray[rows][columns] = numberG_Double[rows];
               }
           }
           return finalArray;
       } catch (BiffException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }
       return null;
   }

}
