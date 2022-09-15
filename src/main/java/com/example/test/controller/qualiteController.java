package com.example.test.controller;

import com.example.test.model.*;
import com.example.test.repository.articleRepo;
import com.example.test.repository.qualiteRepo;
import com.example.test.service.articleService;
import com.example.test.service.qualiteSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = {"*"})
@RequestMapping("/qualite")
@RestController
public class qualiteController {
    @Autowired
    private qualiteRepo rep;

    @Autowired
    private qualiteSer serQ;


    @Autowired
    private articleService ser;

    @GetMapping("/list/{id}")
    public ResponseEntity<Qualite> getAllClients(@PathVariable Long id){

        Article a= ser.getArticleById(id);
        for (Qualite q: rep.findAll()
             ) {
            if(q.getA()==a){
                return new ResponseEntity<>(q, HttpStatus.ACCEPTED);

            }
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/saveQ")
    public ResponseEntity<Qualite> saveC(@RequestBody Qualite q){
        return new ResponseEntity<>(rep.save(q), HttpStatus.CREATED);
    }

    @GetMapping("/afficheQ")
    public ResponseEntity<Qualite> getAllClients(@RequestBody  Qualite f){
        return new ResponseEntity<>(rep.findById(f.getId()).get(), HttpStatus.ACCEPTED);
    }



    @PutMapping("/updateQ/{id}")
    public ResponseEntity<Qualite> updateF(@PathVariable("id") long id,@RequestBody Qualite f){
      Qualite q= rep.findById(id).get();
      q.setQualite(f.getQualite());
      q.setNote(f.getNote());
        return new ResponseEntity<>(rep.save(q),HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteQ/{id}")
    public  ResponseEntity<?> deleteFamille(@PathVariable Long id){
        rep.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
