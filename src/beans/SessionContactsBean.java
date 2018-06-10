package beans;

import models.ParentContacts;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Stateless(name = "SessionContactsEJB")
public class SessionContactsBean implements IContactsBean{
    public SessionContactsBean() {
    }

    @PersistenceUnit(unitName = "PU")
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");

    public ParentContacts getLatest(Long parentId){
        EntityManager em = emf.createEntityManager();
        List<ParentContacts> allContacts;
        ParentContacts contacts = null;

        try{
            allContacts = em.createQuery("select contacts from ParentContacts contacts where contacts.parent_id = " + parentId+" order by contacts.dateOfCreating desc" ).getResultList();
            contacts = allContacts.get(0);
            //contacts = (ParentContacts)em.createQuery("select contacts from ParentContacts contacts where contacts.parent_id = " + parentId+" order by contacts.date_of_creating desc" ).getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            em.close();
        }
        return contacts;
    }
}
