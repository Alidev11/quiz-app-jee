package com.example.metier;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="user")

public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "last_name")
    private String nom;
    @Column(name = "first_name")
    private String prenom;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String profile;

//    @OneToMany(mappedBy = "user")
//    private List<Quiz> quizzes;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private List<Quiz> quizzes;
//    @OneToMany(mappedBy = "id.userId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private List<UserQuizScore> UserScores = new ArrayList<UserQuizScore>();

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private List<Quiz> quizzes;
//    @OneToMany(mappedBy = "id.userId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private List<UserQuizResultat> UserScores = new ArrayList<UserQuizResultat>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User(){
        List<Quiz> quizzes = new ArrayList<Quiz>();
    }

    // constructors, getters, setters, and other methods omitted for brevity


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

//    public List<Quiz> getQuizzes() {
//        return quizzes;
//    }
//
//    public void setQuizzes(List<Quiz> quizzes) {
//        this.quizzes = quizzes;
//    }
}
