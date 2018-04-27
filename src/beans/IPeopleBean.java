package beans;

import models.People;

import javax.ejb.Local;
import java.util.Date;
import java.util.List;

//@Local
@Local(SessionPeopleBean.class)
public interface IPeopleBean {

   // public People add(People person);

    People get(long id);

    boolean update(long id, String name, String middleName, String surname, boolean sex, Date dateOfBirth);

    boolean delete(long id);

    List<People> getAll();

    void add(String name, String middleName, String surname, boolean sex, Date dateOfBirth);

}
