package com.reservationappbus.util;


import com.reservationappbus.entity.Passenger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;


@Service
public class ExcelGeneratorService {

    public ByteArrayInputStream generateExcelReport(List<Passenger> passengers) throws IOException {
        String[] columns = {"Passenger ID", "First Name", "Last Name", "Email", "Mobile", "Bus ID", "Route ID"};

        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Passengers Details");

            // Create header row
            Row headerRow = sheet.createRow(0);
            for (int col = 0; col < columns.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(columns[col]);
            }

            // Create data rows
            int rowIdx = 1;
            for (Passenger passenger : passengers) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(passenger.getPassengerId());
                row.createCell(1).setCellValue(passenger.getFirstName());
                row.createCell(2).setCellValue(passenger.getLastName());
                row.createCell(3).setCellValue(passenger.getEmail());
                row.createCell(4).setCellValue(passenger.getMobile());
                row.createCell(5).setCellValue(passenger.getBusId());
                row.createCell(6).setCellValue(passenger.getRouteId());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}
