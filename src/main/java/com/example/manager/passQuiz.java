package com.example.manager;

import com.example.metier.*;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.persistence.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Long.parseLong;

@ManagedBean(name = "passQuiz")
@SessionScoped
public class passQuiz implements Serializable {
    //    @ManagedProperty(value="#{param.quizId}")
    private int quizId;

    private Quiz quiz;
    private List<Question> questions;
    private List<Reponse> Reponses;

    private Map<Integer, Integer> selectedAnswers;
    private int totalScore = 0;
    private UserQuizResultat listOfNotes;

    public UserQuizResultat getListOfNotes() {
        return listOfNotes;
    }

    public void setListOfNotes(UserQuizResultat listOfNotes) {
        this.listOfNotes = listOfNotes;
    }

    public List<Reponse> getReponses() {
        return Reponses;
    }

    public void setReponses(List<Reponse> reponses) {
        Reponses = reponses;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Quiz");
    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

    //constructor
    public passQuiz() {
        quiz = new Quiz();
        questions = new ArrayList<>();
        Reponses = new ArrayList<>();
        selectedAnswers = new HashMap<Integer, Integer>();
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, String> requestParameterMap = externalContext.getRequestParameterMap();
        String quizIdParam = requestParameterMap.get("quizId");
        if (quizIdParam != null) {
            quizId = Integer.parseInt(quizIdParam);
            searchQuiz(quizId);
        }
        totalScore = 0;

        selectedAnswers = new HashMap<>();
        listOfNotes = new UserQuizResultat();
    }

    @PostConstruct
    public void init() {
        selectedAnswers = new HashMap<>();
    }

    public void initSelectedAnswers() {
        // Initialize the map of selected answers with -1 for each question ID
        selectedAnswers = new HashMap<>();
        for (Question question : questions) {
            selectedAnswers.put(question.getId(), -1);
        }
    }

    //
    int currentUserId = (int) externalContext.getSessionMap().get("currentUser");

    public void submit() {
        try {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = null;

        for (Map.Entry<Integer, Integer> entry : selectedAnswers.entrySet()) {
            int questionId = entry.getKey();
            String answerIdStr = externalContext.getRequestParameterMap().get("question" + questionId);
            int answerId = -1;

            if (answerIdStr != null && !answerIdStr.isEmpty()) {
                answerId = Integer.parseInt(answerIdStr);
            }

            // Retrieve the question from the database
            Question question = em.find(Question.class, questionId);

            // If the answer is not selected, continue to the next iteration
            if (answerId != -1) {
                // Retrieve the answer from the database
                Reponse answer = em.find(Reponse.class, answerId);

                // Check if the answer is correct
                if (answer.getEtat().equals("Correct")) {
                    // Add the score to the total score
                    totalScore += question.getNote_question();
                }
            }
        }

        UserQuizResultat tab = new UserQuizResultat();
        tab.setScore_final(totalScore);
        tab.setQuizId(quizId);
        tab.setUserId(currentUserId);

        transaction = em.getTransaction();
        transaction.begin();
        em.persist(tab);
        transaction.commit();
        em.close();
        } catch (Exception e) {
            // handle exceptions here
        }

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try {
            externalContext.redirect("lesNotes.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getMarksFromDb() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = null;
        transaction = em.getTransaction();
        transaction.begin();
        listOfNotes = em.find(UserQuizResultat.class, currentUserId);
        transaction.commit();
        em.close();
    }


    // Create a new UserQuizResultatAnswer entity and set its properties
//    for (Map.Entry<Long, Long> entry : selectedAnswers.entrySet()) {
//        long questionId = entry.getKey();
//        String answerIdStr = externalContext.getRequestParameterMap().get("question" + questionId);
//        long answerId = -1L;
//        if (answerIdStr != null && !answerIdStr.isEmpty()) {
//            answerId = Long.parseLong(answerIdStr);
//        }
//
//        if (answerId != -1L) {
//            UserQuizResultatAnswer answer = new UserQuizResultatAnswer();
//            answer.setQuizResultatId(tab.getId());
//            answer.setQuestionId(questionId);
//            answer.setAnswerId(answerId);
//            // Persist the UserQuizResultatAnswer entity
//            em.persist(answer);
//        }
//    }


    public void searchQuiz(int qzId) {
        // Create an EntityManagerFactory and retrieve an EntityManager
        EntityManager em = emf.createEntityManager();

        quiz = em.find(Quiz.class, qzId);

        if (quiz != null) {
            Query query = em.createQuery("SELECT q FROM Question q WHERE q.quiz_id = :qzId");
            query.setParameter("qzId", qzId);
//                questions = query.getResultList();
            setQuestions(query.getResultList());
        } else {
            System.out.println("Quiz not found for ID: " + qzId);
        }
    }

    public List<Reponse> searchAnswer(int QId) {
        // Create an EntityManagerFactory and retrieve an EntityManager
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT r FROM Reponse r WHERE r.question_id = :QId");
        query.setParameter("QId", QId);
        setReponses(query.getResultList());
        return Reponses;
    }

    public void getAnswersForQuestions() {
        Map<Integer, List<Reponse>> answersByQuestionId = new HashMap<>();
        for (Question question : questions) {
            List<Reponse> answers = searchAnswer(question.getId());
            answersByQuestionId.put(question.getId(), answers);
        }
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("answersByQuestionId", answersByQuestionId);

    }


}