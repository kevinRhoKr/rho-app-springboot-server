package com.example.demo.top;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path="")
public class RequestController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RequestRepository reqRepo;

    @CrossOrigin
    @PostMapping("/contact/new")
    public Requst newReq(@RequestBody Map<String, String> req) {

        List<User> allUsers = userRepo.findAll();

        Integer user_id = -1;
        for (int i = 0; i < allUsers.size(); ++i) {
            if (Objects.equals(allUsers.get(i).getEmail(), req.get("email"))) {
                user_id = allUsers.get(i).getID();
                break;
            }
        }

        Requst newReq = new Requst();
        newReq.setBody(req.get("text"));
        newReq.setUser_id(user_id);
        return reqRepo.save(newReq);

    }

    @CrossOrigin
    @GetMapping("/contact/requests")
    public List<HashMap<String, String>> getRequests() {
        List<Requst> allRequests = reqRepo.findAll();
        List<User> allUsers = userRepo.findAll();

        List<HashMap<String, String>> res = new ArrayList<>();

        for (int i = 0; i < allRequests.size(); ++i) {
            for (int j = 0; j < allUsers.size(); ++j) {
                if (Objects.equals(allRequests.get(i).getUser_id(), allUsers.get(j).getID())) {
                    User user= allUsers.get(j);
                    Requst request = allRequests.get(i);
                    HashMap<String, String> entry = new HashMap<>();
                    entry.put("f_name", user.getF_name());
                    entry.put("l_name", user.getL_name());
                    entry.put("email", user.getEmail());
                    entry.put("body", request.getBody());
                    res.add(entry);
                }
            }
        }

        return res;

    }

}
