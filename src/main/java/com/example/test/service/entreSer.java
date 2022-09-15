package com.example.test.service;

import com.example.test.model.Entrepot;
import com.example.test.model.Fournisseur;
import com.example.test.repository.entreRepo;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.apache.poi.ss.util.CellUtil.createCell;

@Service
public class entreSer {
    @Autowired
    entreRepo rep;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Entrepot> list1;
    public void exportExcel(List<Entrepot> list){
        this.list1= list;
        workbook= new XSSFWorkbook();
    }

    private void writeHeaderLine(){
        sheet=workbook.createSheet("Entrepots");
        Row row=sheet.createRow(0);
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        CellStyle style= workbook.createCellStyle();
        style.setFont(font);
        createCell(row, 0, "Intitulé", style);
        createCell(row, 1, "Tél", style);
        createCell(row, 2, "Email", style);
        createCell(row, 3, "Ville", style);
        createCell(row, 4, "Adresse", style);
        createCell(row, 5, "Code postal", style);
    }
    private void writeDataLine(){
        int rowCount=1;
        CellStyle style= workbook.createCellStyle();
        XSSFFont font= workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        for(Entrepot a: list1){
            Row row= sheet.createRow(rowCount++);
            int columnCount=0;
            createCell(row, columnCount++, a.getIntitule(), style);
            createCell(row, columnCount++, a.getTel(), style);
            createCell(row, columnCount++, a.getMail(), style);
            createCell(row, columnCount++, a.getVille(), style);
            createCell(row, columnCount++, a.getAdresse(), style);
            createCell(row, columnCount++, a.getCpostal(), style);
        }
    }
    public  void exportExcel(HttpServletResponse http) throws IOException {
        writeHeaderLine();
        writeDataLine();
        ServletOutputStream out= http.getOutputStream();
        workbook.write(out);
        workbook.close();
        out.close();
    }
}
