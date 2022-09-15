package com.example.test.service;


import com.example.test.model.User;
import com.example.test.repository.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class userService {
    @Autowired
    private userRepo userRe;
    public void saveUser(User user){
        userRe.save(user);
    }
    public User getUserById(long id){
        Optional<User> opt=userRe.findById(id);
        User u= null;
        if (opt.isPresent()) {
            u = opt.get();
        }else {
            throw new RuntimeException("User not found "+ id);
        }
        return u;
    }


    public boolean checkLogin(String username, String password, HttpSession session){
        Optional<User> opt =userRe.findUser(username);
        if (opt.isPresent() && opt.get().getPassword().equals(password)){
            session.setAttribute("username", username);
            session.setAttribute("id", opt.get().getId());
            return true;
        }else return false;
    }

    public void deleteUser(long id){
        userRe.deleteById(id);
    }
}
