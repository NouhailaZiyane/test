package com.example.test.controller;


import com.example.test.model.Article;
import com.example.test.model.Entrepot;
import com.example.test.model.Mouvet;
import com.example.test.repository.mouvetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/mouvets")
public class mouvetController {
    @Autowired
    private mouvetRepo e;
    @GetMapping("/list")
    public ResponseEntity<List<Mouvet>> getAllFamilies(){
        // model.addAttribute("listFamille",  familleSer.getFamilles());
        return new ResponseEntity<>(e.findAll(), HttpStatus.OK);
    }
    @PostMapping("/getMouvet")
    public ResponseEntity<Article> getMouvet(@RequestBody Mouvet a){
        //List<Mouvet> m1= null;
        Long i=e.select(a.getA().getId());

        if(i==null) i= Long.valueOf(0);
        if(a.getStock()>a.getA().getStock()){
            Article res= new Article();
            res.setStock(-1);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        else if(i==a.getA().getStock()){
            return new ResponseEntity<>(null, HttpStatus.OK);
        }else if(i+a.getStock()>a.getA().getStock()){
            Article res= new Article();
            res.setStock(-20);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }else{
            e.save(a);
            Article res= new Article();
            res.setStock(-100);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
    }
    @PostMapping("/updateMouvet")
    public ResponseEntity<Article> getMouvet1(@RequestBody Mouvet a){
        //List<Mouvet> m1= null;
        e.delete(a);
        Long i=e.select(a.getA().getId());

        if(i==null) i= Long.valueOf(0);
        if(a.getStock()>a.getA().getStock()){
            Article res= new Article();
            res.setStock(-1);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        else if(i==a.getA().getStock()){
            return new ResponseEntity<>(null, HttpStatus.OK);
        }else if(i+a.getStock()>a.getA().getStock()){
            Article res= new Article();
            res.setStock(-20);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }else{
            e.save(a);
            Article res= new Article();
            res.setStock(-100);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
    }

    @PostMapping("/saveM")
    public ResponseEntity<Mouvet> saveArticle(@RequestBody Mouvet a){
        return new ResponseEntity<>(e.save(a),HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mouvet> getArticle(@PathVariable(value = "id") long id){
        Mouvet f= e.findById(id).get();
        return new ResponseEntity<>(f, HttpStatus.OK);
    }

    @PutMapping("/saveM/{id}")
    public ResponseEntity<Mouvet> updateArticle(@PathVariable("id") long id,@RequestBody Mouvet f ){
        Mouvet fa= e.findById(id).get();
        fa.setA(f.getA());
        fa.setE(f.getE());
        fa.setStock(f.getStock());
        return new ResponseEntity<>(e.save(fa),HttpStatus.CREATED);
    }

    @DeleteMapping ("/deleteM/{id}")
    public  ResponseEntity<?> deleteFamille(@PathVariable(value = "id") Long id){
        e.delete(e.findById(id).get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
