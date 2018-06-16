package models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "User", schema = "s225128")
public class User {

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private long password;

    @Column(name = "email")
    private String email;

    @Column(name = "admin")
    private boolean admin;

    @JsonIgnore
    @OneToOne
    @PrimaryKeyJoinColumn(name = "person_id", referencedColumnName = "person_id")
    private People person;

    @Column(name="person_id")
    private long person_id;

    public void setPerson(People person) {
        this.person = person;
        person_id = person.getPerson_id();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getPassword() {
        return password;
    }

    public void setPassword(long password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public People getPerson() {
        return person;
    }

    public long getPerson_id() {
        return person_id;
    }

    public void setPerson_id(long person_id) {
        this.person_id = person_id;
    }

    public boolean IsAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
