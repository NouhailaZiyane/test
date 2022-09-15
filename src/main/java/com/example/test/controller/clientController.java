package com.example.test.controller;

import com.example.test.model.Article;
import com.example.test.model.Client;
import com.example.test.model.Famille;
import com.example.test.model.Fournisseur;
import com.example.test.repository.clientRepo;
import com.example.test.service.clientSer;
import com.example.test.service.familleService;
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
import java.util.Optional;

@CrossOrigin(origins = {"*"})
@RequestMapping("/clients")
@RestController
public class clientController {
    @Autowired
    private clientSer ser;

    @Autowired
    private clientRepo repo;

    @GetMapping("/list")
    public ResponseEntity<List< Client>> getAllClients(Model model){
        //model.addAttribute("listClients",  ser.getClients());
        return new ResponseEntity<>(repo.findAll(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/export")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter=  new SimpleDateFormat("yyyy-MM-dd_HH:MM:ss");
        String currentDateTime= dateFormatter.format(new Date());
        String headerKey="Content-Disposition";
        String headerValue="attachement; filename=categories_"+currentDateTime+".xlsx";
        response.setHeader(headerKey, headerValue);
        List<Client> listAr= repo.findAll();
        ser.exportExcel(listAr);
        ser.exportExcel(response);

    }
    @PostMapping("/saveClient")
    public  ResponseEntity<Client> saveFamille(@RequestBody Client c)
    {
        //ser.saveClient(c);
        return new ResponseEntity<>(repo.save(c),HttpStatus.CREATED);
    }
    @PutMapping("/saveCl/{id}")
    public ResponseEntity<Client> updateF(@PathVariable("id") long id, @RequestBody
   Client f){
        Client f1=ser.getClientById(id);
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
    @GetMapping("/countC")
    public Long countClients(){
        return repo.countC();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Client> getArticle(@PathVariable(value = "id") long id){
       Optional<Client> a= repo.findById(id);
       if(a.isPresent()){

           return new ResponseEntity<>(a.get(), HttpStatus.OK);

       }else{
           return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
       }
    }


    @DeleteMapping("/deleteClient/{id}")
    public  ResponseEntity<?> deleteFamille(@PathVariable(value = "id") Long id){
        ser.deleteClientById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
