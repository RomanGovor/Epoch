package com.project.demo.domain;

import javax.persistence.*;

@Entity
                              /////
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)       //Идентификатор для различания записей в одной табличке
    private Integer id;

    private String text;                                  // Текст сообщения
    private String tag;                                   // Теги

//    private User author;
//
//    public User getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(User author) {
//        this.author = author;
//    }



    public Message() {
    }

    public Message(String text, String tag) {
        this.text = text;
        this.tag = tag;
    }

    private void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}