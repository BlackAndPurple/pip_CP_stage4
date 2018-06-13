package beans;

import models.Parent;
import models.ParentContacts;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Stateless(name = "SessionContactsEJB")
public class SessionContactsBean implements IContactsBean{
    public SessionContactsBean() {
    }

    @EJB
    IParentBean parentBean;

    @PersistenceUnit(unitName = "PU")
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");

    /**
     * Allows to get latest contacts record of the parent with given Id.
     * @param parentId
     * @return          Latest contacts record.
     */
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

    /**
     * Allows to add new contacts record with current date.
     * @param parent
     * @param homeAddress
     * @param job
     * @param jobPhoneNumber
     * @param cellphoneNumber
     * @return  true if adding was successful, otherwise false.
     */
    public boolean add(Parent parent, String homeAddress, String job, String jobPhoneNumber, String cellphoneNumber){
        EntityManager em = emf.createEntityManager();
        boolean returnValue = false;
        try{
            ParentContacts contacts = new ParentContacts();
            //contacts.
            contacts.setParent(parent);
            contacts.setHomeAddress(homeAddress);
            contacts.setJob(job);
            contacts.setJobPhoneNumber(jobPhoneNumber);
            contacts.setCellphoneNumber(cellphoneNumber);
            contacts.setDate_of_creating(new Date());
            em.persist(contacts);
            returnValue = true;
        }catch (Exception e){
            returnValue = false;
        }finally {
            em.close();
        }
        return returnValue;
    }
}
