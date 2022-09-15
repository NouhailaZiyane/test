package com.example.test.controller;


import com.example.test.model.Article;
import com.example.test.model.Client;
import com.example.test.model.Fournisseur;
import com.example.test.repository.clientRepo;
import com.example.test.repository.fourniRepo;
import com.example.test.service.clientSer;
import com.example.test.service.fourniSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@CrossOrigin(origins = {"*"})
@RequestMapping("/fournisseurs")
@RestController
public class fourniController {
    @Autowired
    private fourniSer ser;

    @Autowired
    private fourniRepo repo;

    @GetMapping("/list")
    public ResponseEntity<List<Fournisseur>> getAllClients(Model model){

       // model.addAttribute("listClients",  ser.getFourni());

        return new ResponseEntity<>(ser.getFourni(), HttpStatus.ACCEPTED);
    }
    @GetMapping("/countFour")
    public Long countFournis(){
        return repo.countF();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Fournisseur> getArticle(@PathVariable(value = "id") long id) {
        Fournisseur f=ser.getFourniById(id);
        return new ResponseEntity<>(f, HttpStatus.OK);
    }

        @PostMapping("/saveFourni")
    public  ResponseEntity<Fournisseur> saveFamille(@RequestBody Fournisseur c)
    {
        return new ResponseEntity<>(repo.save(c),HttpStatus.CREATED);
    }
    @PutMapping("/saveF/{id}")
    public ResponseEntity<Fournisseur> updateF(@PathVariable("id") long id, @RequestBody
                          Fournisseur f){
       // repo.updateFourni(id, nom, prenom, mail, tel, cPostal, fax, pays, ville, adresse);
        Fournisseur f1=ser.getFourniById(id);
        f1.setAdresse(f.getAdresse());
        f1.setCPostal(f.getCPostal());
        f1.setFax(f.getFax());
        f1.setMail(f.getMail());
        f1.setNom(f.getNom());
        f1.setPrenom(f.getPrenom());
        f1.setTel(f.getTel());
        f1.setPays(f.getPays());
        f1.setVille(f.getVille());
        return new ResponseEntity<>(repo.save(f1),HttpStatus.CREATED);
    }

    @GetMapping("/export")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter=  new SimpleDateFormat("yyyy-MM-dd_HH:MM:ss");
        String currentDateTime= dateFormatter.format(new Date());
        String headerKey="Content-Disposition";
        String headerValue="attachement; filename=categories_"+currentDateTime+".xlsx";
        response.setHeader(headerKey, headerValue);
        List<Fournisseur> listAr= repo.findAll();
        ser.exportExcel(listAr);
        ser.exportExcel(response);

    }
    @DeleteMapping("/deleteFourni/{id}")
    public  ResponseEntity<?> deleteFamille(@PathVariable Long id){
        ser.deleteFourniById(id);
        //return"redirect:/fournis";
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
