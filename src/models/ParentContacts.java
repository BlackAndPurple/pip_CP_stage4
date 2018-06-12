package models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.naming.NameAlreadyBoundException;
import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "PARENT_CONTACTS", schema = "s225128")
public class ParentContacts implements Serializable {

    @JsonIgnore
    @Id
    @Column(name = "contacts_ID")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "IdSeq5")
    @SequenceGenerator(name="IdSeq5",sequenceName="s225128.contacts_ids", allocationSize=1)
    private Long contacts_id;

    @JsonIgnore
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_creating")
    private Date dateOfCreating;

    @JsonIgnore
    @Column(name = "parent_id")
    private long parent_id;


    @Column(name="HOME_ADDRESS", columnDefinition = "VARCHAR(200)")
    private String homeAddress;

    @Column(columnDefinition = "VARCHAR(200)")
    private String job;

    @Column(name = "JOB_PHONE_NUMBER", columnDefinition = "VARCHAR(30)")
    private String jobPhoneNumber;

    @Column(name="CELL_PHONE_NUMBER", columnDefinition = "VARCHAR(30)")
    private String cellphoneNumber;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "parent_id", /*, referencedColumnName = "parent_id", */insertable = false, updatable=false) //used to be name="PARENT_ID" and it didnt work
    //@Column(name = "parent_id", insertable=false, updatable=false)
    private Parent parent;

    public void setParent(Parent parent) {
        this.parent = parent;
        parent_id = parent.getParent_id();
    }

//    @Override
//    public String toString() {
//        return "ID: " + contacts_id + " | parent_id: " + parent_id + " | date_of_creating: " + new SimpleDateFormat("dd-MM-yyyy").format(dateOfCreating) + " | job: " +
//                job + " | home_address: " + homeAddress + " | job_phone_number: " + jobPhoneNumber + " | cell_phone_number: " + cellphoneNumber;
//    }

    public Long getContacts_id() {
        return contacts_id;
    }



    public Parent getParent() {
        return parent;
    }


    public Date getDate_of_creating() {
        return dateOfCreating;
    }

    public void setDate_of_creating(Date date_of_creating) {
        this.dateOfCreating = date_of_creating;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getJobPhoneNumber() {
        return jobPhoneNumber;
    }

    public void setJobPhoneNumber(String jobPhoneNumber) {
        this.jobPhoneNumber = jobPhoneNumber;
    }

    public String getCellphoneNumber() {
        return cellphoneNumber;
    }

    public void setCellphoneNumber(String cellphoneNumber) {
        this.cellphoneNumber = cellphoneNumber;
    }
}
