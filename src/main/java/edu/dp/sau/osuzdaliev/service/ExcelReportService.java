package edu.dp.sau.osuzdaliev.service;
import edu.dp.sau.osuzdaliev.model.Blender;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
@Service
public class ExcelReportService {
    public byte[] generateBlenderReport(List<Blender> blenders) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Blenders");
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Name");
            headerRow.createCell(1).setCellValue("Price(Uah)");
            headerRow.createCell(2).setCellValue("Price(Usd)");
            headerRow.createCell(3).setCellValue("Price(Eur)");
            headerRow.createCell(4).setCellValue("Link");
            int rowNum = 1;
            for (Blender blender : blenders) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(blender.getName());
                row.createCell(1).setCellValue(blender.getPrice());
                row.createCell(2).setCellValue(blender.getPriceUsd());
                row.createCell(3).setCellValue(blender.getPriceEur());
                row.createCell(4).setCellValue(blender.getLink());
            }
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                workbook.write(outputStream);
                return outputStream.toByteArray();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }
}
