package com.example.metier;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="user_quiz_resultat")
public class UserQuizResultat implements Serializable{

    @Id
    @Column(name = "quiz_id")
    private int quizId;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "score_final")
    private int score_final;


    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getScore_final() {
        return score_final;
    }

    public void setScore_final(int score_final) {
        this.score_final = score_final;
    }
}
