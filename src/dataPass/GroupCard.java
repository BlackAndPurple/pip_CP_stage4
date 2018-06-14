package dataPass;

import java.util.Date;

public class GroupCard {
    private String groupName;
    private String groupType;
    private Date since;
    private Date till;

    public GroupCard(String groupName, String groupType, Date since, Date till) {
        this.groupName = groupName;
        this.groupType = groupType;
        this.since = since;
        this.till = till;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public Date getSince() {
        return since;
    }

    public void setSince(Date since) {
        this.since = since;
    }

    public Date getTill() {
        return till;
    }

    public void setTill(Date till) {
        this.till = till;
    }
}
