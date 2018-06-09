package beans;

import models.Parent;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

@Stateless(name = "SessionParentEJB")
public class SessionParentBean implements IParentBean{
    public SessionParentBean() {
    }

    @PersistenceUnit(unitName = "PU")
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");

    /**
     * Allows to get parent by person id
     * @param id    person id
     * @return      parent found
     */
    public Parent get(long id){
        EntityManager em = emf.createEntityManager();
        try {
            return (Parent) em.createQuery("select parent from Parent parent where parent.person_id="+id).getSingleResult();
        }catch (Exception e){
            em.close();
            return null;
        }
    }
}
