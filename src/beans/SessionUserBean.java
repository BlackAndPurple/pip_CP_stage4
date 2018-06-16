package beans;

import models.People;
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

    /**
     * Allows to get single user by it's username and password.
     * @param username
     * @param password     Password hash
     * @return              Found user or null
     */
    public User get(String username, long password){
        EntityManager em = emf.createEntityManager();
        User user = null;
        try {
            user =  (User)em.createQuery("select user from User user where user.username=\'"+username +
                    "\' and user.password=" + password).getSingleResult();
        }catch (Exception e){ }
        finally {
            em.close();
        }
        return user;
    }

    /**
     * Allows to add new user to DB.
     * @param username
     * @param password      Password hash
     * @param email         User's email
     * @param person        Person associated with this user
     */
    public void add(String username, long password, String email, People person){
        EntityManager em = emf.createEntityManager();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setPerson(person);
        em.persist(user);
        em.close();

    }

    /**
     * Allows to update an existing user.
     * @param user
     * @return      True if update was successful
     */
    public boolean update(User user){
        boolean result = false;
        EntityManager em =  emf.createEntityManager();
        try {
            em.merge(user);
            result = true;

        }catch (Exception e){ }
        finally {
            em.close();
        }
        return result;
    }
}
