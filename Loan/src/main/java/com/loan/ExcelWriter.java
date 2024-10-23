package com.loan;

import javafx.scene.control.Alert;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelWriter {
    @SuppressWarnings("CallToPrintStackTrace")
    public void writeExcelTable(File file, List<PaymentSchedule> schedule) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("график платежей");

            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("номер платежа");
            header.createCell(1).setCellValue("дата платежа");
            header.createCell(2).setCellValue("общая сумма платежа");
            header.createCell(3).setCellValue("проценты");
            header.createCell(4).setCellValue("основной долг");
            header.createCell(5).setCellValue("остаток задолженности");

            int currentRow = 0;
            for (PaymentSchedule ps : schedule) {
                Row row = sheet.createRow(currentRow + 1);
                row.createCell(0).setCellValue(ps.paymentNumber());
                row.createCell(1).setCellValue(ps.paymentDate().toString());
                row.createCell(2).setCellValue(ps.totalPayment().toString());
                row.createCell(3).setCellValue(ps.interestPayment().toString());
                row.createCell(4).setCellValue(ps.principalPayment().toString());
                row.createCell(5).setCellValue(ps.remainingBalance().toString());

                currentRow += 1;
            }

            try (FileOutputStream fileStream = new FileOutputStream(file)) {
                workbook.write(fileStream);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("успех");
                alert.setHeaderText(null);
                alert.setContentText("график платежей успешно сохранён в excel файл.");
                alert.showAndWait();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ошибка");
            alert.setHeaderText(null);
            alert.setContentText("ошибка при сохранении файла: " + ex.getMessage());
            alert.showAndWait();
        }
    }
}
