package com.example.test.service;


import com.example.test.model.Article;
import com.example.test.model.Client;
import com.example.test.model.Famille;
import com.example.test.repository.clientRepo;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import static org.apache.poi.ss.util.CellUtil.createCell;

@Service
public class clientSer {
    @Autowired
    private clientRepo rep;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Client> list1;


    public List<Client> getClients(){
        return rep.findAll();
    }



   public void saveClient(Client c){
        rep.save(c);
   }




    public Client getClientById(Long id){
        Optional<Client> opt= rep.findById(id);
       Client c= null;
        if(opt.isPresent()){
            c=opt.get();

        }else{
            throw new RuntimeException("Employee doesn't exist for id: "+ id);
        }
        return c;
    }

    public void deleteClientById(long id){
        rep.deleteById(id);
    }
    public void exportExcel(List<Client> list){
        this.list1= list;
        workbook= new XSSFWorkbook();
    }

    private void writeHeaderLine(){
        sheet=workbook.createSheet("Clients");
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
        for(Client a: list1){
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
