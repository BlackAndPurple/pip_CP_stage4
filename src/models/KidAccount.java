package models;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "KID_ACCOUNT", schema = "s225128")
public class KidAccount {

    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    //@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "kid_generator")
//    @SequenceGenerator(name="kid_generator",sequenceName="s225128.kid_ids", allocationSize=1)
    private long account_id;
//
    @Column(name = "kid_id")
    private long kid_id;

//    public long getKid_id() {
//        return kid_id;
//    }
//
//    public void setKid_id(long kid_id) {
//        this.kid_id = kid_id;
//    }
    //
    @Column(name = "group_id")
    private long group_id;

    @ManyToOne
    @JoinColumn(name="KID_ID",/* referencedColumnName = "KID_id", */insertable = false, updatable = false)
    private Kid kid;

    @ManyToOne
    @JoinColumn(name="GROUP_ID",/* referencedColumnName = "GROUP_id",*/ insertable = false, updatable = false)  //one to one would be better
    private Group group;

    @Temporal(TemporalType.DATE)
    private Date date_of_creating;

    @Temporal(TemporalType.DATE)
    private Date date_of_leaving;

    @Override
    public String toString() {
        String since = null, until = null;
        try{
            since = new SimpleDateFormat("dd-MM-yyyy").format(date_of_creating);
            until = new SimpleDateFormat("dd-MM-yyyy").format(date_of_leaving);
        }catch(NullPointerException e){
            e.printStackTrace();
        }
        return "account ID: " + account_id + " | kid ID: " + kid_id +" | group ID: " + group_id + " | " + since + " - " + until;
    }

    public long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(long account_id) {
        this.account_id = account_id;
    }

    public Kid getKid() {
        return kid;
    }

    public void setKid(Kid kid) {
        this.kid = kid;
        //kid.getKidAccounts().add(this);
        kid_id = kid.getKid_id();


    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
        group_id = group.getGroup_id();
        //groupId = group.getGroup_id();
    }

    public Date getDate_of_creating() {
        return date_of_creating;
    }

    public void setDate_of_creating(Date date_of_creating) {
        this.date_of_creating = date_of_creating;
    }

    public Date getDate_of_leaving() {
        return date_of_leaving;
    }

    public void setDate_of_leaving(Date date_of_leaving) {
        this.date_of_leaving = date_of_leaving;
    }
}
