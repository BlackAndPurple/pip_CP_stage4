package beans;

import models.KidAccount;

import javax.ejb.Local;
import java.util.List;

@Local(SessionAccountBean.class)
public interface IAccountBean {
    List<KidAccount> get(Long kidId);
}
