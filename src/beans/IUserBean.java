package beans;

import models.People;
import models.User;

import javax.ejb.Local;

@Local(SessionUserBean.class)
public interface IUserBean {
    User get(String username);
    void add(String username, long password, String email, People person);
    User get(String username, long password);
    boolean update(User user);
}
