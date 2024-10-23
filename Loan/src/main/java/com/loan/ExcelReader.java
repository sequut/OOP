package com.loan;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;

public class ExcelReader {
    public LoanParameters readLoanParameters(File file) throws IOException {
        FileInputStream fileStream = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(fileStream);
        Sheet sheet = workbook.getSheetAt(0);

        double loanAmount = sheet.getRow(1).getCell(0).getNumericCellValue();
        double interestRate = sheet.getRow(1).getCell(1).getNumericCellValue();
        int loanTerm = (int) sheet.getRow(1).getCell(2).getNumericCellValue();
        LocalDate startDate = sheet.getRow(1).getCell(3).getLocalDateTimeCellValue().toLocalDate();

        workbook.close();
        return new LoanParameters(loanAmount, interestRate, loanTerm, startDate);
    }
}
