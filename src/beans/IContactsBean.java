package beans;

import models.ParentContacts;

import javax.ejb.Local;

@Local(SessionContactsBean.class)
public interface IContactsBean {
    ParentContacts getLatest(Long parentId);
}
