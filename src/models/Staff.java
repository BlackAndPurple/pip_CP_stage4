package models;


import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "STAFF", schema = "s225128")
public class Staff {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY/*, generator = "IdSeq4"*/)
    /*@SequenceGenerator(name="IdSeq4",sequenceName="staff_ids", allocationSize=1)*/
    private long staff_id;

    @ManyToOne
    @JoinColumn(name="PERSON_ID", referencedColumnName = "PERSON_id", insertable = false, updatable = false)
    private People person;

    @Column(name = "person_id")
    private Long person_id;

    @Column(name="FUNCTION", columnDefinition = "VARCHAR(30)")
    private String function;

    private String experience;

    @OneToMany(targetEntity = StaffGroup.class, mappedBy = "staff")
    private Collection<StaffGroup> staffGroup;

    @Override
    public String toString() {
        return staff_id +  " | " + person_id + " | " + function + " | " + experience;
    }

    public long getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(long staff_id) {
        this.staff_id = staff_id;
    }

    public People getPerson() {
        return person;
    }

    public void setPerson(People person) {
        this.person = person;
        person_id = person.getPerson_id();
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }


    public Collection<StaffGroup> getStaffGroup() {
        return staffGroup;
    }

    public void setStaffGroup(Collection<StaffGroup> staffGroup) {
        this.staffGroup = staffGroup;
    }
}
