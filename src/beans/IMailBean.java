package beans;

import javax.ejb.Local;

@Local(SessionMailBean.class)
public interface IMailBean {
    void send(String password, String to);
}
