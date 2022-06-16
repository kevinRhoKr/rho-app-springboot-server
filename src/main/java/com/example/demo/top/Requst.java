package com.example.demo.top;

import javax.persistence.*;

@Entity
@Table(name = "requests")
public class Requst {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer request_id;
    private String body;
    private Integer user_id;

    Requst() {}

    // getters
    public Integer getRequest_id() { return this.request_id; }
    public String getBody() { return this.body; }
    public Integer getUser_id() { return this.user_id; }

    // setters
    public void setRequest_id(Integer id) { this.request_id = id; }
    public void setBody(String body) { this.body = body; }
    public void setUser_id(Integer id) { this.user_id = id; }

    //toString
    @Override
    public String toString() {
        return "Request{" + "request_id=" + this.request_id + ", user_id=" + this.user_id + '\'' + "body= " + this.body + '}';
    }

}
