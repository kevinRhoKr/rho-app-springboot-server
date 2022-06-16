package com.example.demo.top;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "apposts")
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer post_id;
    private Integer user_id;
    private Timestamp date;
    private String body;

    public Post() {}

    public Post (Post anotherPost) {
        this.user_id = anotherPost.user_id;
        this.date = anotherPost.date;
        this.body = anotherPost.body;
    }

    public Integer get_id() {
        return this.post_id;
    }

    public Integer get_user_id() { return this.user_id; }
    public Timestamp get_date() { return this.date; }
    public String get_body() { return this.body; }

    public void set_id(Integer id) { this.post_id = id; }
    public void set_user_id(Integer id) { this.user_id = id; }
    public void set_date(Timestamp date) { this.date = date; }
    public void set_body(String body) { this.body = body; }

    @Override
    public String toString() {
        return "Post{" + "post_id=" + this.post_id + ", user_id='" + this.user_id + '\'' + ", date='" + this.date + '\'' + ", body='" + this.body + '}';
    }


}


