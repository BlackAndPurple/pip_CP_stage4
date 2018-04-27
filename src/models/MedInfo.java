package models;

import com.sun.istack.internal.Nullable;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "MED_INFO", schema = "s225128")
public class MedInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long med_id;

    @ManyToOne
    @JoinColumn(name="KID_ID",/*, referencedColumnName = "KID_id", */insertable = false, updatable = false)
    private Kid kid;

    @Column(name = "kid_id")
    private Long kid_id;

    @Temporal(TemporalType.DATE)
    private Date date_of_creating;

    private Integer height;
    private Double weight;

    private String inoculations;

    @Column(name = "CURRENT_DISEASES")
    private String currentDiseases;

    @Override
    public String toString() {
        return "ID: " + med_id + " | kid id:" + kid_id +" | date of creating: " + new SimpleDateFormat("dd-MM-yyyy").format(date_of_creating)+
                " | height: " + height + " | weight: " + weight + " | inoculations: " + inoculations+ " | current diseases: " + currentDiseases;
    }

    public long getMed_id() {
        return med_id;
    }

    public void setMed_id(long med_id) {
        this.med_id = med_id;
    }

    public Kid getKid() {
        return kid;
    }

    public void setKid(Kid kid) {
        this.kid = kid;
        kid_id = kid.getKid_id();
    }

    public Date getDate_of_creating() {
        return date_of_creating;
    }

    public void setDate_of_creating(Date date_of_creating) {
        this.date_of_creating = date_of_creating;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getInoculations() {
        return inoculations;
    }

    public void setInoculations(String inoculations) {
        this.inoculations = inoculations;
    }

    public String getCurrentDeseases() {
        return currentDiseases;
    }

    public void setCurrentDeseases(String currentDeseases) {
        this.currentDiseases = currentDeseases;
    }
}
