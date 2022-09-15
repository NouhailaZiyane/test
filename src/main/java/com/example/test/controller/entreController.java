package com.example.test.controller;


import com.example.test.model.Client;
import com.example.test.model.Entrepot;
import com.example.test.model.Famille;
import com.example.test.repository.entreRepo;
import com.example.test.service.entreSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = {"*"})
@RequestMapping("/entrepots")
@RestController
public class entreController {
    @Autowired
    private entreRepo e;
    @Autowired
    private entreSer ser;

    @GetMapping("/list")
    public ResponseEntity<List<Entrepot>> getAllFamilies(){

        // model.addAttribute("listFamille",  familleSer.getFamilles());
        return new ResponseEntity<>(e.findAll(), HttpStatus.OK);
    }

    @PostMapping("/saveE")
    public ResponseEntity<Entrepot> saveArticle(@RequestBody Entrepot a){
        return new ResponseEntity<>(e.save(a),HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entrepot> getArticle(@PathVariable(value = "id") long id){
        Entrepot f= e.findById(id).get();
        return new ResponseEntity<>(f, HttpStatus.OK);
    }
    @GetMapping("/export")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter=  new SimpleDateFormat("yyyy-MM-dd_HH:MM:ss");
        String currentDateTime= dateFormatter.format(new Date());
        String headerKey="Content-Disposition";
        String headerValue="attachement; filename=categories_"+currentDateTime+".xlsx";
        response.setHeader(headerKey, headerValue);
        List<Entrepot> listAr= e.findAll();
        ser.exportExcel(listAr);
        ser.exportExcel(response);

    }

    @PutMapping("/saveE/{id}")
    public ResponseEntity<Entrepot> updateArticle(@PathVariable("id") long id,@RequestBody Entrepot f ){
        Entrepot fa= e.findById(id).get();
       fa.setIntitule(f.getIntitule());
       fa.setAdresse(f.getAdresse());
       fa.setCpostal(f.getCpostal());
       fa.setTel(f.getTel());
       fa.setMail(f.getMail());
       fa.setVille(f.getVille());
        return new ResponseEntity<>(e.save(fa),HttpStatus.CREATED);
    }

    @DeleteMapping ("/deleteE/{id}")
    public  ResponseEntity<?> deleteFamille(@PathVariable(value = "id") Long id){
        e.delete(e.findById(id).get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
