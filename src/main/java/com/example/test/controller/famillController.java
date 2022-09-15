package com.example.test.controller;

import com.example.test.model.Article;
import com.example.test.model.Famille;
import com.example.test.repository.familleRepo;
import com.example.test.service.familleService;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/familles")
public class famillController {
    @Autowired
    private familleService familleSer;
    @Autowired
    private familleRepo rep;

    @GetMapping("/list")
    public ResponseEntity<List<Famille>> getAllFamilies(Model model){

       // model.addAttribute("listFamille",  familleSer.getFamilles());
        return new ResponseEntity<>(familleSer.getFamilles(), HttpStatus.OK);
    }

    @GetMapping("/newFamille")
    public String newFamille(Model model){
        Famille f = new Famille();
        model.addAttribute("famille", f);
        return "new_famille";
    }
    @PostMapping("/saveFamille")
    public ResponseEntity<Famille> saveArticle(@RequestBody Famille a){
        return new ResponseEntity<>(rep.save(a),HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Famille> getArticle(@PathVariable(value = "id") long id){
        Famille f= familleSer.getFamilleById(id);
        return new ResponseEntity<>(f, HttpStatus.OK);
}

    @PutMapping("/saveF/{id}")
    public ResponseEntity<Famille> updateArticle(@PathVariable("id") long id,@RequestBody Famille f ){
        Famille fa= familleSer.getFamilleById(id);
        fa.setCode(f.getCode());
        fa.setIntitule(f.getIntitule());
        return new ResponseEntity<>(rep.save(fa),HttpStatus.CREATED);
    }

    @DeleteMapping ("/deleteF/{id}")
    public  ResponseEntity<?> deleteFamille(@PathVariable(value = "id") Long id){
        familleSer.deleteFamilleById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
