package com.example.manager;

import com.example.metier.User;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@ManagedBean(name="userManager")
@SessionScoped
public class UserManager implements Serializable {

    User user;
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Quiz");

    //constructor
    public UserManager() {
        user = new User();
    }

    //User getter and setter
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public String loginCheck() {
        user.setLogin(this.user.getLogin());
        user.setPassword(this.user.getPassword());
        user.setProfile(this.user.getProfile());

        if(loginDAO(user)){
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user_id", user.getId());
            if(user.getProfile().equals("Admin"))
                return "admin.xhtml";
            else if(user.getProfile().equals("Etudiant"))
                return "etudiant.xhtml";
            else
                return "poseur.xhtml";
        }else{
            return "login.xhtml";
        }
    }

    public static Boolean loginDAO(User user) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = em.getTransaction();
            transaction.begin();
            Query query = em.createQuery("select u from User u where u.login = :username AND u.password = :password AND u.profile = :profile", User.class);
            query.setParameter("username", user.getLogin());
            query.setParameter("password", user.getPassword());
            query.setParameter("profile", user.getProfile());

            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            externalContext.getSessionMap().put("currentUser", user.getId());

            List<User> users = query.getResultList();
            transaction.commit();
            return !users.isEmpty();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
    public String logOut() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login.xhtml?faces-redirect=true";
    }
}
