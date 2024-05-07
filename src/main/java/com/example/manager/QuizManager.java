package com.example.manager;

import com.example.metier.Quiz;
import com.example.metier.User;
import jakarta.faces.bean.ManagedBean;
        import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@ManagedBean(name="quizManager")
@SessionScoped
public class QuizManager implements Serializable {
    private Quiz quiz;
    private List<Quiz> quizzes;
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Quiz");

    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    //constructor
    public QuizManager() {
        quiz = new Quiz();
        quizzes=listQuizzes();
    }

    //Quiz getter
    public Object getQuiz() {
        return quiz;
    }

    //Quiz setter
    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    //quizzes getter
    public List<Quiz> getQuizzes() {
        return quizzes;
    }

//------------------------------ ADD & LIST QUIZ (quiz manager)
    public List<Quiz> listQuizzes() {
        if (quizzes == null) {
            quizzes = listQuizDAO();
        }
        return quizzes;
    }

    public String addQuiz() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = null;
        quiz.setLibelle(this.quiz.getLibelle());
        quiz.setTheme(this.quiz.getTheme());
        quiz.setNote(this.quiz.getNote());
        //quiz.setUser_id((int) externalContext.getSessionMap().get("user_id"));
        quiz.setUser_id(1);
        try {
            transaction = em.getTransaction();
            transaction.begin();
            em.persist(quiz);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
        return "poseur.xhtml?faces-redirect=true";
    }
//--------------------------------- QUIZ DAO
    public static List<Quiz> listQuizDAO(){
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("select quiz from Quiz quiz");
        List<Quiz> lst = query.getResultList();
        em.close();
        return lst;
    }

}
