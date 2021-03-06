package beans;

import dataPass.KidCard;
import models.Kid;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Stateless(name = "SessionKidEJB")
public class SessionKidBean implements IKidBean{
    public SessionKidBean() {
    }

    @PersistenceUnit(unitName = "PU")
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");

    public List<Kid> getKidsByParentId(Long parentId){
        EntityManager em = emf.createEntityManager();
        List<Kid> kids = null;
        try{
            kids = em.createQuery("select kid from Kid kid where kid.parent1_id = " + parentId+" or kid.parent2_id = " + parentId ).getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            em.close();
        }
        return kids;
    }


    /**
     * Allows to get kid by id.
     * @param id  the identifier of kid we are looking for.
     * @return    Kid with given id if found.
     */
    public Kid get(long id){
        EntityManager em = emf.createEntityManager();
        try {
            return (Kid)em.createQuery("select kid from Kid kid where kid.kid_id="+id).getSingleResult();
        }catch (Exception e){
            em.close();
            return null;
        }
    }
}
