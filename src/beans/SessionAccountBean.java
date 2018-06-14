package beans;

import models.Kid;
import models.KidAccount;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Stateless(name = "SessionAccountEJB")
public class SessionAccountBean implements IAccountBean{
    public SessionAccountBean() {
    }

    @PersistenceUnit(unitName = "PU")
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");

    /**
     * Allows to find all kid account records by kid id.
     * @param kidId     Kid id
     * @return          list of found kid account records.
     */
    public List<KidAccount> get(Long kidId){
        EntityManager em = emf.createEntityManager();
        try {
            return  em.createQuery("select account from KidAccount account where account.kid_id="+kidId).getResultList();
        }catch (Exception e){
            em.close();
            return null;
        }
    }

}
