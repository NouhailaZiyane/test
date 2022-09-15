package com.example.test.service;


import com.example.test.model.Client;
import com.example.test.model.Fournisseur;
import com.example.test.repository.clientRepo;
import com.example.test.repository.fourniRepo;
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
import java.util.Optional;

import static org.apache.poi.ss.util.CellUtil.createCell;

@Service
public class fourniSer {
    @Autowired
    private fourniRepo rep;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Fournisseur> list1;
    public List<Fournisseur> getFourni(){
        return rep.findAll();
    }



    public void saveFourni(Fournisseur c){
        rep.save(c);
    }




    public Fournisseur getFourniById(Long id){
        Optional<Fournisseur> opt= rep.findById(id);
        Fournisseur c= null;
        if(opt.isPresent()){
            c=opt.get();

        }else{
            throw new RuntimeException("Fournisseur doesn't exist for id: "+ id);
        }
        return c;
    }

    public void deleteFourniById(long id){
        rep.deleteById(id);
    }
    public void exportExcel(List<Fournisseur> list){
        this.list1= list;
        workbook= new XSSFWorkbook();
    }

    private void writeHeaderLine(){
        sheet=workbook.createSheet("Fournisseurs");
        Row row=sheet.createRow(0);
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        CellStyle style= workbook.createCellStyle();
        style.setFont(font);
        createCell(row, 0, "Nom", style);
        createCell(row, 1, "Prénom", style);
        createCell(row, 2, "Tél", style);
        createCell(row, 3, "Email", style);
        createCell(row, 4, "Ville", style);
        createCell(row, 5, "Adresse", style);
        createCell(row, 6, "Code postal", style);
    }
    private void writeDataLine(){
        int rowCount=1;
        CellStyle style= workbook.createCellStyle();
        XSSFFont font= workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        for(Fournisseur a: list1){
            Row row= sheet.createRow(rowCount++);
            int columnCount=0;
            createCell(row, columnCount++, a.getNom(), style);
            createCell(row, columnCount++, a.getPrenom(), style);
            createCell(row, columnCount++, a.getTel(), style);
            createCell(row, columnCount++, a.getMail(), style);
            createCell(row, columnCount++, a.getVille(), style);
            createCell(row, columnCount++, a.getAdresse(), style);
            createCell(row, columnCount++, a.getCPostal(), style);
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
