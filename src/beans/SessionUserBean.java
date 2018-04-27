package beans;

import models.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

@Stateless(name = "SessionUserEJB")
public class SessionUserBean implements IUserBean {
    public SessionUserBean() {
    }

    @PersistenceUnit(unitName = "PU")
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");


    /**
     * Allows to get single user by its username.
     * @param username
     * @return          Found user or null.
     */
    public User get(String username){
        EntityManager em = emf.createEntityManager();
        User user = null;
        try {
            user =  (User)em.createQuery("select user from User user where user.username=\'"+username + "\'").getSingleResult();
        }catch (Exception e){ }
        finally {
            em.close();
        }
        return user;
    }
}
