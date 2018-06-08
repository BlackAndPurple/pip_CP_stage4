package models;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "PEOPLE", schema = "s225128")
public class People implements Serializable{

//    @Id
//    @Column(name = "person_id")
//    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "IdSeq")
    @SequenceGenerator(name="IdSeq",sequenceName="people_ids", allocationSize=1)
    private long person_id;

    @Column(columnDefinition = "VARCHAR(30)")
    private String name;

    @Column(name="MIDDLE_NAME", columnDefinition = "VARCHAR(30)")
    private String middleName;

    @Column(columnDefinition = "VARCHAR(30)")
    private String surname;
    private boolean sex;

    @Column(name="date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @OneToMany(targetEntity = Staff.class, mappedBy = "person")
    private Collection<Staff> staff;

    @Override
    public String toString() {
        String date = null;
        try{
            date = new SimpleDateFormat("dd-MM-yyyy").format(dateOfBirth);
        }catch(NullPointerException e){
            e.printStackTrace();
        }
        return person_id+" | "+name+" | "+middleName+" | "+surname+" | "+sex+" | "+ date;
    }

    public long getPerson_id() {
        return person_id;
    }

    public void setPerson_id(long person_id) {
        this.person_id = person_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public Date getDate_of_birth() {
        return dateOfBirth;
    }

    public void setDate_of_birth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Collection<Staff> getStaff() {
        return staff;
    }

    public void setStaff(Collection<Staff> staff) {
        this.staff = staff;
    }
}
