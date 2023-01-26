package com.hdel.web.service.common;


import com.hdel.web.dto.excel.Excel;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelUtil {
    public String readExcel(@RequestParam("file") MultipartFile file, Model model) throws TikaException, IOException {
        List<Excel> dataList = new ArrayList<>();

        try (InputStream is = file.getInputStream();) {

            Tika tika = new Tika();
            String mimeType = tika.detect(is);
            if (isAllowedMIMEType(mimeType)) {
                Workbook workbook = new XSSFWorkbook(file.getInputStream());

                Sheet worksheet = workbook.getSheetAt(0);

                String atchFileId = null;

                for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) { // 1번째 행부터 끝까지
                    Row row = worksheet.getRow(i);

                    Excel data = new Excel();
                    data.setElevatorNo(row.getCell(0).getStringCellValue());
                    data.setExceptionCnt((int)row.getCell(1).getNumericCellValue());

                    dataList.add(data);
                }

                model.addAttribute("list", dataList);
            } else {
                throw new IOException();
            }
        } catch (Exception e) {
            throw new TikaException("ERROR");
        }

        return "list";
    }

    private boolean isAllowedMIMEType(String mimeType) {
        if (mimeType.equals("application/x-tika-ooxml"))
            return true;
        return false;
    }

}
