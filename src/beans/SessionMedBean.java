package beans;

import models.MedInfo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Stateless(name = "SessionMedEJB")
public class SessionMedBean implements IMedBean{
    public SessionMedBean() {
    }

    @PersistenceUnit(unitName = "PU")
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");

    /**
     * Allows to get latest contacts record of the parent with given Id.
     * @param kidId
     * @return          Latest contacts record.
     */
    public MedInfo getLatest(Long kidId){
        EntityManager em = emf.createEntityManager();
        List<MedInfo> allMeds;
        MedInfo med = null;

        try{
            allMeds = em.createQuery("select med from MedInfo med where med.kid_id = " + kidId+" order by med.dateOfCreating desc" ).getResultList();
            med = allMeds.get(0);
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            em.close();
        }
        return med;
    }

}
