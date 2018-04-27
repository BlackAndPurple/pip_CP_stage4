package models;


import com.sun.istack.internal.Nullable;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "KID", schema = "s225128")
public class Kid {

    @Id
    @Column(name = "kid_id")
//    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "kid_generator")
//    @SequenceGenerator(name="kid_generator",sequenceName="s225128.kid_ids", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long kid_id;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "person_id", referencedColumnName = "person_id")
    private People person;

    @Column(name="person_id")
    private long person_id;

    @Column(name="parent1_id")
    private Long parent1_id;


    @Column(name="parent2_id")
    @Nullable
    private Long parent2_id;

    @ManyToOne
    @JoinColumn(name="PARENT1_ID",/*, referencedColumnName = "parent_id",*/ insertable = false, updatable = false)
    private Parent parent1;


    @ManyToOne
    @JoinColumn(name="PARENT2_ID", /*referencedColumnName = "parent_id", */insertable = false, updatable = false, nullable = true)
    @Nullable
    private Parent parent2 = null;


    @OneToMany(targetEntity = MedInfo.class, mappedBy = "kid")
    private Collection<MedInfo> medInfos;

    @OneToMany(targetEntity = KidAccount.class, mappedBy = "kid"/*, cascade = CascadeType.PERSIST*/)
    private Collection<KidAccount> kidAccounts;

    public Collection<MedInfo> getMedInfos() {
        return medInfos;
    }

//    public Collection<KidAccount> getKidAccounts() {
//        return kidAccounts;
//    }

//    public void setKidAccounts(Collection<KidAccount> kidAccounts) {
//        this.kidAccounts = kidAccounts;
//    }

    public void setMedInfos(Collection<MedInfo> medInfos) {

        this.medInfos = medInfos;
    }

    @Override
    public String toString() {
        return "kid ID : " + kid_id + " | person_id: " + person_id +  " | Parents' IDs: " + parent1_id+", "+ parent2_id;
    }

    public long getKid_id() {
        return kid_id;
    }

    public void setKid_id(int kid_id) {
        this.kid_id = kid_id;
    }

    public People getPerson() {
        return person;
    }

    public void setPerson(People person) {
        this.person = person;
        person_id = person.getPerson_id();
    }

    public Parent getParent1() {
        return parent1;
    }

    public void setParent1(Parent parent1) {
        this.parent1 = parent1;

        parent1_id = parent1.getParent_id();
    }

    public Parent getParent2() {
        return parent2;
    }

    public void setParent2(Parent parent2) {
        this.parent2 = parent2;
        if(parent2 != null)
        {
            //this.parent2 = parent2;
            parent2_id = parent2.getParent_id();
        }


    }
}
