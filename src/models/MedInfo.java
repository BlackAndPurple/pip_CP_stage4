package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.internal.Nullable;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "MED_INFO", schema = "s225128")
public class MedInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long med_id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="KID_ID",/*, referencedColumnName = "KID_id", */insertable = false, updatable = false)
    private Kid kid;

    @Column(name = "kid_id")
    private Long kid_id;


    @Column(name="date_of_creating")
    @Temporal(TemporalType.DATE)
    private Date dateOfCreating;

    private Integer height;
    private Double weight;

    private String inoculations;

    @Column(name = "CURRENT_DISEASES")
    private String currentDiseases;



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

    public Date getDateOfCreating() {
        return dateOfCreating;
    }

    public void setDateOfCreating(Date date_of_creating) {
        this.dateOfCreating = date_of_creating;
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

    public String getCurrentDiseases() {
        return currentDiseases;
    }

    public void setCurrentDiseases(String currentDeseases) {
        this.currentDiseases = currentDeseases;
    }
}
