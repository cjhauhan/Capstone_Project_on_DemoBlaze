package com.demoblaze.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class ExcelUtil {
    private Path filePath;
    private String sheetName;

    public ExcelUtil(String filePath, String sheetName) {
        this.filePath = Paths.get(filePath);
        this.sheetName = sheetName;
    }

    /** Ensure workbook + sheet exist, create header if missing */
    public void ensureWorkbookWithHeader(List<String> header) throws IOException {
        Workbook wb;
        Sheet sheet;
        if (Files.exists(filePath)) {
            try (InputStream in = Files.newInputStream(filePath)) {
                wb = WorkbookFactory.create(in);
            }
            sheet = wb.getSheet(sheetName);
            if (sheet == null) sheet = wb.createSheet(sheetName);
        } else {
            wb = new XSSFWorkbook();
            sheet = wb.createSheet(sheetName);
        }

        if (sheet.getPhysicalNumberOfRows() == 0) {
            Row r = sheet.createRow(0);
            for (int i = 0; i < header.size(); i++) {
                Cell c = r.createCell(i);
                c.setCellValue(header.get(i));
            }
        }

        try (OutputStream out = Files.newOutputStream(filePath)) {
            wb.write(out);
        }
        wb.close();
    }

    /** Append a single row (String cells) */
    public void appendRow(List<String> values) throws IOException {
        Workbook wb;
        Sheet sheet;
        if (Files.exists(filePath)) {
            try (InputStream in = Files.newInputStream(filePath)) {
                wb = WorkbookFactory.create(in);
            }
            sheet = wb.getSheet(sheetName);
            if (sheet == null) sheet = wb.createSheet(sheetName);
        } else {
            wb = new XSSFWorkbook();
            sheet = wb.createSheet(sheetName);
        }

        int rownum = sheet.getLastRowNum() + (sheet.getPhysicalNumberOfRows() == 0 ? 0 : 1);
        // if sheet empty and lastRowNum == 0 but no rows, start at 0 vs 1
        if (sheet.getPhysicalNumberOfRows() == 0) rownum = 0;

        Row row = sheet.createRow(rownum);
        for (int i = 0; i < values.size(); i++) {
            Cell c = row.createCell(i);
            c.setCellValue(values.get(i));
        }

        try (OutputStream out = Files.newOutputStream(filePath)) {
            wb.write(out);
        }
        wb.close();
    }
}
