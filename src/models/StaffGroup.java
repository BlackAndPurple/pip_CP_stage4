package models;


import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "STAFF_GROUP", schema = "s225128")
public class StaffGroup {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY/*, generator = "IdSeq6"*/)
    //@SequenceGenerator(name="IdSeq6",sequenceName="staff_group_ids", allocationSize=1)
    private long id;

    @Temporal(TemporalType.DATE)
    private Date date_of_beginning;

    @Temporal(TemporalType.DATE)
    private Date date_of_end;

    @ManyToOne
    @JoinColumn(name="STAFF_ID", referencedColumnName = "STAFF_id", insertable = false, updatable = false)
    private Staff staff;

    @ManyToOne
    @JoinColumn(name="GROUP_ID", referencedColumnName = "GROUP_ID", insertable = false, updatable = false)
    private Group group;

    @Column(name = "staff_id")
    private Long staff_id;

    @Column(name = "group_id")
    private Long group_id;

    @Override
    public String toString() {
        String since = null, until = null;
        try{
            since = new SimpleDateFormat("dd-MM-yyyy").format(date_of_beginning);
            until = new SimpleDateFormat("dd-MM-yyyy").format(date_of_end);
        }catch(NullPointerException e){
            e.printStackTrace();
        }
        return "ID: " + id + " | staff ID: " + staff_id + " | group ID: " + group_id + " | " +
                since + " - " + until;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate_of_beginning() {
        return date_of_beginning;
    }

    public void setDate_of_beginning(Date date_of_beginning) {
        this.date_of_beginning = date_of_beginning;
    }

    public Date getDate_of_end() {
        return date_of_end;
    }

    public void setDate_of_end(Date date_of_end) {
        this.date_of_end = date_of_end;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
        staff_id = staff.getStaff_id();
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
        group_id = group.getGroup_id();
    }
}
