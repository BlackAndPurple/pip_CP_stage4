package beans;

import models.Kid;

import javax.ejb.Local;
import java.util.List;

@Local(SessionKidBean.class)
public interface IKidBean {
   List<Kid> getKidsByParentId(Long parentId);
}
