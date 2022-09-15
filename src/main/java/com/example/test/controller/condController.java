package com.example.test.controller;


import com.example.test.model.Article;
import com.example.test.model.Conditionnement;
import com.example.test.model.Qualite;
import com.example.test.repository.articleRepo;
import com.example.test.repository.condRepo;
import com.example.test.service.articleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = {"*"})
@RequestMapping("/cond")
@RestController
public class condController {
    @Autowired
    private condRepo condRep;

    @Autowired
    private articleService ser;

    @GetMapping("/list/{id}")
    public ResponseEntity<List<Conditionnement>> getAllClients(@PathVariable Long id) {
        Article a = ser.getArticleById(id);
        List<Conditionnement> list = new ArrayList<>();
        for (Conditionnement c : condRep.findAll()
        ) {
            if (c.getA() == a) {
                list.add(c);

            }

        }
        return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
    }

    @PostMapping("/addC")
    public ResponseEntity<Conditionnement> saveC(@RequestBody Conditionnement c) {
        return new ResponseEntity<>(condRep.save(c), HttpStatus.CREATED);
    }

    @PutMapping("/updateC/{id}")
    public ResponseEntity<Conditionnement> updateF(@PathVariable("id") long id,@RequestBody Conditionnement f){
        Conditionnement q= condRep.findById(id).get();
       q.setPrixA(f.getPrixA());
       q.setPrixV(f.getPrixV());
       q.setQte(f.getQte());
       q.setUnite(f.getUnite());
        return new ResponseEntity<>(condRep.save(q),HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteC/{id}")
    public  ResponseEntity<?> deleteFamille(@PathVariable Long id){
        condRep.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
