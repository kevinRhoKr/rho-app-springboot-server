package com.example.demo.top;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PreUpdate;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import java.util.*;

@RestController
@RequestMapping(path="")
public class PostController {

    private Map<String, String> getJSONRes(Post post, User user) {
        HashMap<String, String> res = new HashMap<>();
        res.put("post_id", post.get_id().toString());
        res.put("f_name", user.getF_name());
        res.put("l_name", user.getL_name());
        res.put("date", new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(post.get_date()));
        res.put("body", post.get_body());
        res.put("email", user.getEmail());
        return res;
    }

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private UserRepository userRepo;

    @CrossOrigin
    @GetMapping("/posts")
    public List<Map<String, String>> getAllPosts() {
        List<Map<String, String>> res = new ArrayList<>();

        List<Post> allPosts = postRepo.findAll();
        List<User> allUsers = userRepo.findAll();

        System.out.println(allPosts.size() + " " + allUsers.size());

        /*

            look for a JPA way to do this with two repositories.

        */

        for (int i = 0; i < allPosts.size(); ++i) {
            for (int j = 0; j < allUsers.size(); ++j) {
                if (Objects.equals(allPosts.get(i).get_user_id(), allUsers.get(j).getID())) {
                    res.add(getJSONRes(allPosts.get(i), allUsers.get(j)));
                    break;
                }
            }
        }

        return res;
    }

    @CrossOrigin
    @PostMapping("/posts/new")
    public Post newPost(@RequestBody Map<String, String>req) {

        List<User> allUsers = userRepo.findAll();

        Integer user_id = -1;
        for (int i = 0; i < allUsers.size(); ++i) {
            if (Objects.equals(allUsers.get(i).getEmail(), req.get("email"))) {
                user_id = allUsers.get(i).getID();
                break;
            }
        }

        Post newPost = new Post();
        newPost.set_body(req.get("text"));
        newPost.set_date(new Timestamp(System.currentTimeMillis()));
        newPost.set_user_id(user_id);

        return postRepo.save(newPost);

    }

    @CrossOrigin
    @DeleteMapping("/posts/delete")
    public HashMap<String, String> deletePost(@RequestBody Map<String, String> req) {

        System.out.println("deleting...");
        System.out.println(req);

        HashMap<String, String> res = new HashMap<>();

        try {
            postRepo.deleteById(Integer.parseInt(req.get("post_id")));
            res.put("status", "success");
        } catch (Exception e) {
            res.put("status", "failed");
        }
        return res;
    }

    @CrossOrigin
    @PutMapping("/posts/update")
    public HashMap<String, String> updatePost(@RequestBody Map<String, String> req) {

        System.out.println("updating..");

        HashMap<String, String> res = new HashMap<>();

        

        return res;


    }

}
