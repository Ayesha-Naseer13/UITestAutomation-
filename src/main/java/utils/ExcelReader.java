package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {
    private String filePath;

    public ExcelReader(String filePath) {
        this.filePath = filePath;
    }

    public List<String> getSearchKeywords(String sheetName) throws IOException {
        List<String> keywords = new ArrayList<>();
        FileInputStream fis = new FileInputStream("src/test/resources/testdata/testData.xlsx");
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetName);

        for (Row row : sheet) {
            Cell cell = row.getCell(0);  // Assuming the search keywords are in the first column
            if (cell != null) {
                keywords.add(cell.getStringCellValue());
            }
        }
        workbook.close();
        fis.close();
        return keywords;
    }
}
