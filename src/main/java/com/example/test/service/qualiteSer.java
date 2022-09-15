package com.example.test.service;


import com.example.test.repository.qualiteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class qualiteSer {
    @Autowired
    private qualiteRepo rep;
    public void updateQual (long id, String note, String quali){
        rep.updateQualite(id, note, quali);
    }
}
