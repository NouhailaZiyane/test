package com.example.test.controller;

import com.example.test.model.*;
import com.example.test.repository.articleRepo;
import com.example.test.repository.cmdeFRepo;
import com.example.test.repository.condRepo;
import com.example.test.repository.fourniRepo;
import com.example.test.service.articleService;
import com.example.test.service.cmdeFSer;
import com.example.test.service.fourniSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/commandesF")
public class cmdeFController {
    @Autowired
    private cmdeFSer ser;

    @Autowired
    private cmdeFRepo repo;
    @Autowired
    private articleService serv;
    @Autowired
    private condRepo rep;

    @GetMapping("/list")
    public ResponseEntity<List<CommandeF>> getArticles() {
       List<CommandeF> c= repo.findAll();
        return new ResponseEntity<>(c, HttpStatus.ACCEPTED);
    }
    @PostMapping ("/saveCF")
    public ResponseEntity<CommandeF> saveU(@RequestBody CommandeF a){
        return new ResponseEntity<>(repo.save(a),HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CommandeF> getArticle(@PathVariable(value = "id") long id){
        Optional<CommandeF>a= repo.findById(id);
        if(a.isPresent()){

            return new ResponseEntity<>(a.get(), HttpStatus.OK);

        }else{
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/saveCF/{id}")
    public ResponseEntity<CommandeF> updateF(@PathVariable("id") long id, @RequestBody
    CommandeF f){
        CommandeF c= repo.findById(id).get();
        c.setA(f.getA());
        c.setDatecmde(f.getDatecmde());
        c.setC(f.getC());
        c.setF(f.getF());
        c.setDaterec(f.getDaterec());
        c.setCondPai(f.getCondPai());
        c.setModePai(f.getModePai());
        c.setQuantite(f.getQuantite());
        c.setMontant(f.getMontant());
        return new ResponseEntity<>(repo.save(c),HttpStatus.CREATED);
    }
    @GetMapping("/countC")
    public Long countClients(){
        return repo.countC();
    }

    @DeleteMapping("/deleteCF/{id}")
    public  ResponseEntity<?> deleteFamille(@PathVariable(value = "id") Long id){
        repo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
