package com.example.manager;

import com.example.metier.Question;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@ManagedBean(name="questionManager")
@SessionScoped
public class QuestionManager implements Serializable {
    private Question question;
    private List<Question> questions;
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Quiz");
    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

    //constructor
    public QuestionManager() {
        question = new Question();
        questions = listQuestions();
    }

    //Question getter and setter
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    //Questions getter and setter
    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    //---------------- ADD & LIST QUESTIONS(question manager)
    public String addQuestion() throws SQLException {
        question.setEnonce(this.question.getEnonce());
        question.setNote_question(this.question.getNote_question());
        question.setQuiz_id(this.question.getQuiz_id());
        addQuestionDAO(question);
        return "questions.xhtml?id="+ question.getId() +"&faces-redirect=true";
    }
    public List<Question> listQuestions() {
        if (questions == null) {
            questions = listQuestionsDAO();
        }
        return questions;
    }


    //---------------- ADD & LIST QUESTIONS(DAO)
    public static void addQuestionDAO(Question question) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = em.getTransaction();
            transaction.begin();
            em.persist(question);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
    public List<Question> listQuestionsDAO(){
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("select q from Question q");
        List<Question> lst = query.getResultList();
        em.close();
        return lst;
    }

    public Question fetchOneQuestion(){
        EntityManager em = emf.createEntityManager();
        String id = externalContext.getRequestParameterMap().get("id");
        if (id == null) {
            return null;
        }
        Question question = em.find(Question.class, Long.parseLong(id));
        em.close();
        return question;
    }
}