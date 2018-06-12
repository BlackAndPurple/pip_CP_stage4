package beans;

import models.Parent;
import models.ParentContacts;

import javax.ejb.Local;

@Local(SessionContactsBean.class)
public interface IContactsBean {
    ParentContacts getLatest(Long parentId);
    boolean add(Parent parent, String homeAddress, String job, String jobPhoneNumber, String cellphoneNumber);
}
