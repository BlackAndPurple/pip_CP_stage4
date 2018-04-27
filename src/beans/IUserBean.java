package beans;

import models.User;

import javax.ejb.Local;

@Local(SessionUserBean.class)
public interface IUserBean {
    User get(String username);
}
