package beans;

import models.People;

import javax.ejb.Local;
import java.util.Date;
import java.util.List;

//@Local
@Local(SessionPeopleBean.class)
public interface IPeopleBean {

   // public People add(People person);

    public People get(long id);

    public boolean update(long id, String name, String middleName, String surname, boolean sex, Date dateOfBirth);

    public boolean delete(long id);

    public List<People> getAll();

    public void add(String name, String middleName, String surname, boolean sex, Date dateOfBirth);

}
