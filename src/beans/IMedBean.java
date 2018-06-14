package beans;

import models.MedInfo;

import javax.ejb.Local;

@Local(SessionMedBean.class)
public interface IMedBean {
    MedInfo getLatest(Long kidId);
}
