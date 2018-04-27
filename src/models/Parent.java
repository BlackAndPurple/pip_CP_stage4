package models;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "PARENT", schema = "s225128")
public class Parent {

    @Id
    @Column(name = "PARENT_ID")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "IdSeq1")
    @SequenceGenerator(name="IdSeq1",sequenceName="parent_ids", allocationSize=1)
    private long parent_id;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "PERSON_ID", referencedColumnName = "PERSON_ID")
    private People person;

    @Column(name = "person_id")
    private long person_id = 1;

    public long getPerson_id() {
        person_id =  person.getPerson_id();
        return person_id;
    }


    /*sets person_id to be shown in table*/
    public void setPerson_id() {
        this.person_id = person.getPerson_id();

    }

    @OneToMany(targetEntity = ParentContacts.class, mappedBy = "parent")
    private Collection<ParentContacts> parentContacts;

    public Collection<ParentContacts> getParentContacts() {
        return parentContacts;
    }

    @Override
    public String toString() {
        return "parent_id: "+parent_id + " | person_id: " + person.getPerson_id();
//        return "parent_id: "+parent_id + " | person_id: " + person.getPerson_id() +
//                " | "+person.getName()+" | "+person.getMiddleName()+" | "+
//                person.getSurname()+" | "+person.isSex();
    }

    public long getParent_id() {
        return parent_id;
    }

    public People getPerson() {
        return person;
    }

    public void setPerson(People person) {
        this.person = person;
    }
}
