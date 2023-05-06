package Utility;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {

    private static final String EXCEL_FILE_PATH = "src/main/resources/testData.xlsx";

    public static Object[][] readExcelData(String sheetName) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(EXCEL_FILE_PATH));
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheet(sheetName);

        int numRows = sheet.getPhysicalNumberOfRows();
        int numCols = sheet.getRow(0).getPhysicalNumberOfCells();

        Object[][] data = new Object[numRows - 1][numCols];

        for (int i = 1; i < numRows; i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < numCols; j++) {
                Cell cell = row.getCell(j);
                data[i - 1][j] = getCellValue(cell);
            }
        }

        workbook.close();
        fileInputStream.close();

        return data;
    }

    private static Object getCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }

        CellType cellType = cell.getCellType();
        switch (cellType) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return cell.getNumericCellValue();
            case BOOLEAN:
                return cell.getBooleanCellValue();
            default:
                return null;
        }
    }
}
