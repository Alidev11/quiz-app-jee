package com.example.metier;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Question")
public class Question implements Serializable{
    public Question() {
//        reponses = new ArrayList<Reponse>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "enonce")
    private String enonce;

    @Column(name = "note")
    private int note_question;

    @Column(name = "quiz_id")
    private int quiz_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnonce() {
        return enonce;
    }

    public void setEnonce(String enonce) {
        this.enonce = enonce;
    }

    public int getNote_question() {
        return note_question;
    }

    public void setNote_question(int note_question) {
        this.note_question = note_question;
    }

    public int getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(int quiz_id) {
        this.quiz_id = quiz_id;
    }
}
