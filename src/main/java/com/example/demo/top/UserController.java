package com.example.demo.top;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path="")
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/allUsers")
    public List<User> getAllUsers() {
        System.out.println(userRepo.findAll());
        return userRepo.findAll();
    }

    @CrossOrigin
    @PostMapping("/user/signup")
    public HashMap<String, String> signup(@RequestBody User user) // throws NoSuchAlgorithmException
    {
        System.out.println(user);
        User newUser = new User();
        HashMap<String, String> res = new HashMap<>();

        List<User> allUsers = userRepo.findAll();

        for (int i = 0; i < allUsers.size(); ++i) {
            if (Objects.equals(allUsers.get(i).getEmail(), user.getEmail())) {
                res.put("status", "fail");
                return res;
            }
            // System.out.println("here " + allUsers.get(i).getEmail());
        }

        newUser.copyUser(user);
        userRepo.save(newUser);
        return newUser.getJSONRes();
    }

    //@CrossOrigin(origins="http://localhost:3000")
    @CrossOrigin
    @PostMapping("/user/login")
    public HashMap<String, String> login(@RequestBody User user) // throws NoSuchAlgorithmException
    {
        System.out.println(user);

        HashMap<String, String> res = new HashMap<>();

        List<User> allUsers = userRepo.findAll();
        System.out.println("length: " + allUsers.size());

        for (int i = 0; i < allUsers.size(); ++i) {
            if (Objects.equals(allUsers.get(i).getEmail(), user.getEmail()) &&
                Objects.equals(allUsers.get(i).getPassword(), user.getPassword())) {

                res.put("status", "true");
                return res;
            }

        }
        res.put("status", "false");
        return res;
    }

    @CrossOrigin
    @PostMapping("/user")
    public HashMap<String, String> getUser(@RequestBody User user) // throws NoSuchAlgorithmException
    {
        HashMap<String, String> res = new HashMap<>();

        List<User> allUsers = userRepo.findAll();

        for (int i = 0; i < allUsers.size(); ++i) {
            if (Objects.equals(allUsers.get(i).getEmail(), user.getEmail())) {
                return allUsers.get(i).getJSONRes();
            }
        }
        res.put("status", "not found");
        return res;
    }




}


