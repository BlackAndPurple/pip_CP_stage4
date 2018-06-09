package beans;

import models.Parent;

import javax.ejb.Local;

@Local(SessionParentBean.class)
public interface IParentBean {

    Parent get(long id);
}
