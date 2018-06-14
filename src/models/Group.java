package models;


import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "GROUP", schema = "s225128")
public class Group {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY/*, generator = "IdSeq3"*/)
    //@SequenceGenerator(name="IdSeq3",sequenceName="group_ids", allocationSize=1)
    private Long group_id;


    @Column(columnDefinition = "VARCHAR(20)")
    private String name;


    @Column(name="TYPE_OF_GROUP", columnDefinition = "VARCHAR(20)")
    private String typeOfGroup = null;

//    @OneToMany(targetEntity = KidAccount.class, mappedBy = "group")
//    private Collection<KidAccount> kidAccounts;

    @OneToMany(targetEntity = StaffGroup.class, mappedBy = "group")
    private Collection<StaffGroup> staffGroup;



    public Long getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Long group_id) {
        this.group_id = group_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeOfGroup() {
        return typeOfGroup;
    }

    public void setTypeOfGroup(String typeOfGroup) {
        this.typeOfGroup = typeOfGroup;
    }

//    public Collection<KidAccount> getKidAccounts() {
//        return kidAccounts;
//    }
//
//    public void setKidAccounts(Collection<KidAccount> kidAccounts) {
//        this.kidAccounts = kidAccounts;
//    }

    public Collection<StaffGroup> getStaffGroup() {
        return staffGroup;
    }

    public void setStaffGroup(Collection<StaffGroup> staffGroup) {
        this.staffGroup = staffGroup;
    }
}
