package com.example.test.controller;

import com.example.test.model.CommandeC;
import com.example.test.model.CommandeF;
import com.example.test.repository.cmdeCRepo;
import com.example.test.repository.cmdeFRepo;
import com.example.test.repository.condRepo;
import com.example.test.service.articleService;
import com.example.test.service.cmdeFSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/commandesC")
public class cmdeCController {
    @Autowired
    private cmdeFSer ser;

    @Autowired
    private cmdeCRepo repo;
    @Autowired
    private articleService serv;
    @Autowired
    private condRepo rep;

    @GetMapping("/list")
    public ResponseEntity<List<CommandeC>> getArticles() {
        List<CommandeC> c= repo.findAll();
        return new ResponseEntity<>(c, HttpStatus.ACCEPTED);
    }
    @PostMapping("/saveCC")
    public ResponseEntity<CommandeC> saveU(@RequestBody CommandeC a){
        return new ResponseEntity<>(repo.save(a),HttpStatus.CREATED);
    }
    @GetMapping("/countC")
    public Long countClients(){
        return repo.countC();
    }
    @GetMapping("/{id}")
    public ResponseEntity<CommandeC> getArticle(@PathVariable(value = "id") long id){
        Optional<CommandeC> a= repo.findById(id);
        if(a.isPresent()){

            return new ResponseEntity<>(a.get(), HttpStatus.OK);

        }else{
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/saveCC/{id}")
    public ResponseEntity<CommandeC> updateF(@PathVariable("id") long id, @RequestBody
    CommandeC f){
        CommandeC c= repo.findById(id).get();
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

    @DeleteMapping("/deleteCC/{id}")
    public  ResponseEntity<?> deleteFamille(@PathVariable(value = "id") Long id){
        repo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
