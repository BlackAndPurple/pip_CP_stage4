package beans;

import models.People;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 * Bean implementing CRUD api for {@link models.People model}.
 */
@Stateless(name = "SessionPeopleBeanEJB")
public class SessionPeopleBean implements IPeopleBean{
    public SessionPeopleBean() {
    }

    @PersistenceUnit(unitName = "PU")
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");

    /**
     * Allows to get person by id.
     * @param id  the identifier of person we are looking for.
     * @return    Person with given id if found.
     */
    public People get(long id){
        EntityManager em = emf.createEntityManager();
        try {
            return (People)em.createQuery("select people from People people where people.person_id="+id).getSingleResult();
        }catch (Exception e){
            em.close();
            return null;
        }
    }


    public People get(String name, String middleName, String surname, boolean sex, Date dateOfBirth){
        EntityManager em = emf.createEntityManager();
        People person = null;
        LocalDate date = dateOfBirth.toInstant().atZone( ZoneId.of( "Europe/Moscow" ) ).toLocalDate();
        try{
            person = (People)em.createQuery("select person from People person where person.name = \'" + name +
                    "\' and person.middleName = \'" + middleName + "\' and person.surname = \'" +surname +
                    "\' and person.sex = " + sex + " and person.dateOfBirth = \'" + date +"\'").getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            em.close();
        }
        return person;
    }

    public Long getId(People person){
        try {
            return person.getPerson_id();
        }catch (NullPointerException e){
            return null;
        }
    }

    /**
     * Allows to update data of person with given id.
     * @param id            Record's identifier.
     * @param name          New name.
     * @param middleName    New middle name.
     * @param surname       New surname.
     * @param sex           New sex.
     * @param dateOfBirth   New date of Birth.
     * @return              True if update was successful. Otherwise false.
     */
    public boolean update(long id, String name, String middleName, String surname, boolean sex, Date dateOfBirth){
        People person = get(id);
        if (person != null) {

            person.setName(name);
            person.setMiddleName(middleName);
            person.setSurname(surname);
            person.setSex(sex);
            person.setDate_of_birth(dateOfBirth);
            return true;
        }
        return false;

    }

    /**
     * Allows to delete person with given id.
     * @param id    person's identifier.
     * @return      True if delete operation was successful. Otherwise false.
     */
    public boolean delete(long id){

        People person = get(id);
        if (person != null){
            EntityManager em = emf.createEntityManager();
            Query query = em.createQuery("delete from People people where people.person_id="+id);
            query.executeUpdate();
            em.close();
            return true;
        }else return false;

    }

    /**
     * Allows to get list of all the records in database.
     * @return List of people.
     */
    public List<People> getAll(){
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("Select people from People people");
        List<People> people = query.getResultList();
        em.close();
        return people;
    }

    /**
     * Allows to add new person to database.
     * @param name          New person's name.
     * @param middleName    New person's middle name.
     * @param surname       New person's surname.
     * @param sex           New person's sex.
     * @param dateOfBirth   New person's date of Birth.
     */
    public void add(String name, String middleName, String surname, boolean sex, Date dateOfBirth) {
        EntityManager em = emf.createEntityManager();
        People person = new People();
        person.setPerson_id(0);
        person.setName(name);
        person.setMiddleName(middleName);
        person.setSurname(surname);
        person.setSex(sex);
        person.setDate_of_birth(dateOfBirth);
        em.persist(person);
        em.close();
    }
}
