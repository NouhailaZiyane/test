package com.example.test.service;


import com.example.test.model.Article;
import com.example.test.model.Famille;
import com.example.test.repository.articleRepo;
import com.example.test.repository.familleRepo;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.apache.poi.ss.util.CellUtil.createCell;

@Service
public class articleService {
    @Autowired
    private articleRepo articleRep;

    @Autowired
    familleRepo repF;

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Article> list1;


    public List<Article> getArticles(){
        return articleRep.findAll();
    }


    public Article saveArticle(Article a)
        {
            return articleRep.save(a);
          /*  a.setDesignation(designation);
            a.setTypeArticle(typeArticle);
            a.setReference(reference);
            a.setNomenclature(nomenclature);
            a.setUniteGestion(ug);
            a.setCodeBarre(codeBarre);
            a.setStock(stock);
            a.setF(f);
             articleRep.save(a);

            return true;**/
        }
        public void exportExcel(List<Article> list){
               this.list1= list;
               workbook= new XSSFWorkbook();
        }

        private void writeHeaderLine(){
        sheet=workbook.createSheet("Articles");
        Row row=sheet.createRow(0);
            XSSFFont font = workbook.createFont();
            font.setBold(true);
            font.setFontHeight(16);
            CellStyle style= workbook.createCellStyle();
            style.setFont(font);
            createCell(row, 0, "Désignation", style);
            createCell(row, 1, "Réference", style);
            createCell(row, 2, "Type d'article", style);
            createCell(row, 3, "Code barre", style);
            createCell(row, 4, "Nomenclature", style);
            createCell(row, 5, "Unité de gestion", style);
            createCell(row, 6, "Stock", style);
            createCell(row, 7, "Famille d'article", style);
        }
        private void writeDataLine(){
          int rowCount=1;
          CellStyle style= workbook.createCellStyle();
          XSSFFont font= workbook.createFont();
          font.setFontHeight(14);
          style.setFont(font);
          for(Article a: list1){
              Row row= sheet.createRow(rowCount++);
              int columnCount=0;
              createCell(row, columnCount++, a.getDesignation(), style);
              createCell(row, columnCount++, a.getReference(), style);
              createCell(row, columnCount++, a.getTypeArticle(), style);
              createCell(row, columnCount++, a.getCodeBarre(), style);
              createCell(row, columnCount++, a.getNomenclature(), style);
              createCell(row, columnCount++, a.getUniteGestion(), style);
              createCell(row, columnCount++, String.valueOf(a.getStock()), style);
              createCell(row, columnCount++, a.getF().getIntitule(), style);
          }
        }
        public void exportExcel(HttpServletResponse http) throws IOException {
            writeHeaderLine();
            writeDataLine();
            ServletOutputStream out= http.getOutputStream();
            workbook.write(out);
            workbook.close();
            out.close();
        }
        public long selectCount(){
        long count=articleRep.count();
        return count;
        }



    public Article getArticleById(Long id){
       Optional<Article> opt= articleRep.findById(id);
       Article a= null;
       if(opt.isPresent()){
           a=opt.get();

       }else{
           throw new RuntimeException("Employee doesn't exist for id: "+ id);
       }
       return a;
    }

    public void deleteArtcleById(long id){
        articleRep.deleteById(id);
    }
}
