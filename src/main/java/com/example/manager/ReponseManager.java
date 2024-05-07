package com.example.manager;

import com.example.metier.Reponse;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@ManagedBean(name="reponseManager")
@SessionScoped
public class ReponseManager implements Serializable {
    private Reponse reponse;
    private List<Reponse> reponses;
    private int question_id_url;
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Quiz");
    public ExternalContext getExternalContext() {
        return FacesContext.getCurrentInstance().getExternalContext();
    }

    public ReponseManager() {
        this.reponse = new Reponse();
        this.reponses = listReponses();
    }

    public Reponse getReponse() {
        return reponse;
    }

    public void setReponse(Reponse reponse) {
        this.reponse = reponse;
    }

    public List<Reponse> getReponses() {
        return reponses;
    }

    public void setReponses(List<Reponse> reponses) {
        this.reponses = reponses;
    }

    public int getQuestion_id_url() {
        return question_id_url;
    }

    public void setQuestion_id_url(int question_id_url) {
        this.question_id_url = question_id_url;
    }

    //---------------- ADD & LIST QUESTIONS(question manager)
    public String addReponse(){
        reponse.setEnonce(this.reponse.getEnonce());
        reponse.setEtat(this.reponse.getEtat());
        reponse.setQuestion_id(question_id_url);
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = em.getTransaction();
            transaction.begin();
            em.persist(reponse);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
        return "questions.xhtml?id="+ question_id_url +"&faces-redirect=true";
    }

    public List<Reponse> listReponses() {
        if (reponses == null) {
            reponses = listReponsesDAO();
        }
        return reponses;
    }

    public List<Reponse> listReponsesDAO(){
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("select r from Reponse r where r.question_id = :id");
        query.setParameter("id", question_id_url);
        List<Reponse> lst = query.getResultList();
        em.close();
        return lst;
    }
}
