package dataPass;

public class KidCard {
    public KidCard(Long kidId, String name, String middleName, String surname, boolean sex) {
        this.kidId = kidId;
        this.name = name;
        this.middleName = middleName;
        this.surname = surname;
        this.sex = sex;
    }

    private Long kidId;
    private String name;
    private String middleName;
    private String surname;

    public KidCard() {
    }

    private boolean sex;

    public Long getKidId() {
        return kidId;
    }

    public void setKidId(Long kidId) {
        this.kidId = kidId;
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
}
