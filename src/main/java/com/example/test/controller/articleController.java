package com.example.test.controller;

import com.example.test.model.Client;
import com.example.test.model.Image;
import com.example.test.model.Article;
import com.example.test.model.Famille;
import com.example.test.repository.articleRepo;
import com.example.test.repository.familleRepo;
import com.example.test.service.articleService;
import com.example.test.service.familleService;
import org.apache.catalina.connector.Response;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/articles")
public class articleController {

    @Autowired
    private articleService articleSer;
    @Autowired
    private articleRepo rep;

    @Autowired
    private familleService familleSer;
    @Autowired
    private familleRepo rep1;
    @Autowired
    ServletContext context;


    @GetMapping("/list")
    public ResponseEntity<List<Article>> getArticles(Model model) {
        List<Article> a = articleSer.getArticles();
        return new ResponseEntity<>(a, HttpStatus.ACCEPTED);
    }

    @PutMapping(value = {"/saveImArticle"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Article> saveImArticle(@RequestPart("article") Article a, @RequestPart("image") MultipartFile file) throws IOException {
        Article ar= articleSer.getArticleById(a.getId());
        if(!file.isEmpty()) {boolean isExist = new File(context.getRealPath("/Images/")).exists();
            if (!isExist) {
                new File(context.getRealPath("/images/")).mkdir();
            }
            String filename = file.getOriginalFilename();
            String newFileName = FilenameUtils.getBaseName(filename) + "." + FilenameUtils.getExtension(filename);
            File serverFile = new File("/images/" + File.separator + newFileName);
            try {
                FileUtils.writeByteArrayToFile(serverFile, file.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
            ar.setImagesAr(newFileName);




        // return articleSer.saveArticle(a);
    }        return new ResponseEntity<>(rep.save(ar),HttpStatus.CREATED);
    }


    @PostMapping(value = {"/newArticle"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Response> saveArticle(@RequestPart("article") Article a, @RequestPart("image") MultipartFile file) throws IOException {
       if(!file.isEmpty()) {boolean isExist = new File(context.getRealPath("/Images/")).exists();
        if (!isExist) {
            new File(context.getRealPath("/images/")).mkdir();
        }
        String filename = file.getOriginalFilename();
        String newFileName = FilenameUtils.getBaseName(filename) + "." + FilenameUtils.getExtension(filename);
        File serverFile = new File("/images/" + File.separator + newFileName);
        try {
            FileUtils.writeByteArrayToFile(serverFile, file.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        a.setImagesAr(newFileName);
        Article art = articleSer.saveArticle(a);
        if (art != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }}else{
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
        // return articleSer.saveArticle(a);
    }


    @GetMapping("/image/{id}")
    public byte[] getPhoto(@PathVariable("id") Long id) throws IOException {
        Article a = articleSer.getArticleById(id);
        return Files.readAllBytes(Path.of("/images/" + a.getImagesAr()));

    }

    @GetMapping("/getNA")
    public ResponseEntity<Long> getNA() {
        Long number = articleSer.selectCount();
        return new ResponseEntity<>(number, HttpStatus.OK);
    }

    @GetMapping("/getNS")
    public ResponseEntity<Long> getNS() {
        Long number = rep.countStock();
        return new ResponseEntity<>(number, HttpStatus.OK);
    }

    @GetMapping("/getNAF")
    public ResponseEntity<Map> getNAF() {
        Map<String, Long> name = new TreeMap<>();

        long i = 0;
        for (Article a :
                articleSer.getArticles()) {
            i = rep.countArticleById(a.getId());
            name.put(a.getDesignation(), i);
        }
        return new ResponseEntity<>(name, HttpStatus.OK);

    }

    @GetMapping("/getP")
    public ResponseEntity<List> getP() {
        List<String> keys = new ArrayList<>();
        for (Famille f :
                familleSer.getFamilles()) {
            keys.add(f.getIntitule());
            //val.add(rep.countFa(f.getId()));
        }
        return new ResponseEntity<>(keys, HttpStatus.OK);

    }

    @GetMapping("/getV")
    public ResponseEntity<List> getV() {
        List<Long> val = new ArrayList<>();
        for (Famille f :
                familleSer.getFamilles()) {
            val.add(rep.countFa(f.getId()));
            //val.add(rep.countFa(f.getId()));
        }
        return new ResponseEntity<>(val, HttpStatus.OK);

    }

    @GetMapping("/accueil")
    public void count(Model model) {
        //double count=0;
        List<String> key = new ArrayList<>();
        List<Long> val = new ArrayList<>();
        for (Famille f :
                familleSer.getFamilles()) {
            key.add(f.getIntitule());
            val.add(rep.countFa(f.getId()));
        }
        long c = articleSer.selectCount();
        model.addAttribute("keySet", key);
        model.addAttribute("values", val);
        model.addAttribute("count", c);
        if (c != 0) {
            model.addAttribute("countStock", rep.countStock());
        } else {
            int i = 0;
            model.addAttribute("countStock", i);
        }
        Map<String, Long> name = new TreeMap<>();

        long i = 0;
        for (Article a :
                articleSer.getArticles()) {
            i = rep.countArticleById(a.getId());
            name.put(a.getDesignation(), i);
        }


        model.addAttribute("names", name);

    }




    @GetMapping("/export")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter=  new SimpleDateFormat("yyyy-MM-dd_HH:MM:ss");
        String currentDateTime= dateFormatter.format(new Date());
        String headerKey="Content-Disposition";
        String headerValue="attachement; filename=categories_"+currentDateTime+".xlsx";
        response.setHeader(headerKey, headerValue);
        List<Article> listAr=articleSer.getArticles();
        articleSer.exportExcel(listAr);
       articleSer.exportExcel(response);

    }


    @PutMapping("/saveU/{id}")
    public ResponseEntity<Article> saveU(@PathVariable("id") long id,@RequestBody Article a){
        Article ar=articleSer.getArticleById(id);
        ar.setF(a.getF());
        ar.setTypeArticle(a.getTypeArticle());
        ar.setCodeBarre(a.getCodeBarre());
        ar.setUniteGestion(a.getUniteGestion());
        ar.setNomenclature(a.getNomenclature());
        ar.setDesignation(a.getDesignation());
        ar.setStock(a.getStock());
        ar.setReference(a.getReference());
        return new ResponseEntity<>(rep.save(ar),HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticle(@PathVariable(value = "id") long id){
        Article a= articleSer.getArticleById(id);
        return new ResponseEntity<>(a, HttpStatus.OK);
    }
    @GetMapping("/updateArticle/{id}")
    public String updateArticle(@PathVariable(value = "id") long id, Model model){
        Article a= articleSer.getArticleById(id);
        model.addAttribute("article", a);
        List<Famille> f=familleSer.getFamilles();
        model.addAttribute("listfamille", f);
        return "update_article";
    }




    @DeleteMapping("/deleteArticle/{id}")
    public  ResponseEntity<?> deleteArticle(@PathVariable Long id){
        articleSer.deleteArtcleById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/saveArticle1")
        public  ResponseEntity<Article> saveFamille(@RequestBody Article c)
    {
        //ser.saveClient(c);
        return new ResponseEntity<>(rep.save(c),HttpStatus.CREATED);
    }



}
