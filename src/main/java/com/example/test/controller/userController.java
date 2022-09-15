package com.example.test.controller;


import com.example.test.model.Article;
import com.example.test.model.Fournisseur;
import com.example.test.model.User;
import com.example.test.repository.userRepo;
import com.example.test.service.userService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"*"})
@RequestMapping("/users")
@RestController
@Controller
public class userController {
    @Autowired
    private userService uservice;
    @Autowired
    private userRepo rep;

    @GetMapping("/list")
    public ResponseEntity<List<User>> getAllUsers(){

        // model.addAttribute("listClients",  ser.getFourni());

        return new ResponseEntity<>(rep.findAll(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable(value = "id") long id) {
        User f=uservice.getUserById(id);
        return new ResponseEntity<>(f, HttpStatus.OK);
    }





    @PostMapping("/saveUser")
    public  ResponseEntity<User> saveFamille(@RequestBody User c)
    {
        
        return new ResponseEntity<>(rep.save(c),HttpStatus.CREATED);
    }


    @GetMapping("/deleteUser/{id}")
    public  String deleteArticle(HttpSession session, @PathVariable(value = "id") Long id){
        uservice.deleteUser(id);
        return"redirect:/accueil";
    }
    //ResponseEntity<User> getUtilisateur(@RequestBody String login,
    @PostMapping ("/login")
    public ResponseEntity<User>  checkLogin(@RequestBody User u){
        Optional<User> opt = rep.findUser(u.getNomUtilisateur());

        if (opt.isPresent() && opt.get().getPassword().equals(u.getPassword())){
            return  ResponseEntity.ok().body(opt.get());
        }else{
            return new ResponseEntity<>(null,HttpStatus.ACCEPTED);

        }
    }
    @PutMapping("/saveUs/{id}")
    public ResponseEntity<User> saveU(@PathVariable("id") long id,@RequestBody User a){
        User u=uservice.getUserById(id);
        u.setPrenom(a.getPrenom());
        u.setNom(a.getNom());
        u.setEmail(a.getEmail());
        u.setNomUtilisateur(a.getNomUtilisateur());
        return new ResponseEntity<>(rep.save(u),HttpStatus.CREATED);
    }
    @PutMapping("/pass/{id}")
    public ResponseEntity<User> saveU(@PathVariable("id") long id,@RequestBody String pass){
        User u=uservice.getUserById(id);

        u.setPassword(pass);
        return new ResponseEntity<>(rep.save(u),HttpStatus.CREATED);
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "login";
    }

}
