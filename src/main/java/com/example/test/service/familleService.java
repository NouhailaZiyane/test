package com.example.test.service;


import com.example.test.model.Article;
import com.example.test.model.Famille;
import com.example.test.repository.familleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class familleService {
    @Autowired
    private familleRepo familleRep;

    public List<Famille> getFamilles(){
        return familleRep.findAll();
    }
    public void saveFamille(Famille f ){
        familleRep.save(f);
    }
    public void updateF(Long id, String intitule, String code){
        familleRep.updateFamille(id, intitule, code);
    }
    public Famille getFamilleById(Long id){
        Optional<Famille> opt= familleRep.findById(id);
        Famille a= null;
        if(opt.isPresent()){
            a=opt.get();

        }else{
            throw new RuntimeException("Employee doesn't exist for id: "+ id);
        }
        return a;
    }

    public void deleteFamilleById(long id){
        familleRep.deleteById(id);
    }
}

