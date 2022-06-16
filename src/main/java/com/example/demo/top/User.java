package com.example.demo.top;

import javax.persistence.*;
import java.util.HashMap;

@Entity
@Table(name = "appusers")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Integer uid;

    private String email;

    @Column(name="f_name")
    private String fname;

    @Column(name="l_name")
    private String lname;

    @Column(name="user_password")
    private String password;

    public User () {

    }

    public User(String email, String f_name, String l_name, String user_password) {
        this.email = email;
        this.fname = f_name;
        this.lname = l_name;
        this.password = user_password;
    }

    public boolean sameEmail(User otherUser) {
        return this.email == otherUser.getEmail();
    }

    public HashMap<String, String> getJSONRes() {
        HashMap<String, String> res = new HashMap<>();
        res.put("email", this.email);
        res.put("f_name", this.fname);
        res.put("l_name", this.lname);
//        res.put("password", this.user_password);
        res.put("id", this.uid.toString());
        return res;
    }

    public Integer getID() {
        return this.uid;
    }

    public String getEmail() {
        return this.email;
    }

    public String getF_name() {
        return this.fname;
    }

    public String getL_name() {
        return this.lname;
    }

    public String getPassword() {
        return this.password;
    }

    public void setId(Integer id) {
        this.uid = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFname(String f_name) {
        this.fname = f_name;
    }

    public void setLname(String l_name) {
        this.lname = l_name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void copyUser(User user) //throws NoSuchAlgorithmException
    {
        //MessageDigest md = MessageDigest.getInstance("MD5");
        this.email = user.getEmail();
        this.lname = user.getL_name();
        this.fname = user.getF_name();
        //this.user_password = md.digest(user.getPassword());
        System.out.println(user.getPassword());
        this.password = user.getPassword();
    }

    @Override
    public String toString() {
        return "User{" + "id=" + this.uid + ", email='" + this.email + '\'' + ", f_name='" + this.fname + '\'' +  ", l_name='" + this.lname + '\''+ ", password='" + this.password + '\''+ '}';
    }






}
