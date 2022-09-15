package com.example.test.service;

import com.example.test.model.CommandeF;
import com.example.test.model.Fournisseur;
import com.example.test.repository.cmdeFRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class cmdeFSer {
    @Autowired
     private cmdeFRepo rep;
    public List<CommandeF> getFourni(){
        return rep.findAll();
    }



    public void saveFourni(CommandeF c){
        rep.save(c);
    }




    public CommandeF getFourniById(Long id){
        Optional<CommandeF> opt= rep.findById(id);
        CommandeF c= null;
        if(opt.isPresent()){
            c=opt.get();

        }else{
            throw new RuntimeException("Fournisseur doesn't exist for id: "+ id);
        }
        return c;
    }

    public void deleteFourniById(long id){
        rep.deleteById(id);
    }


}
