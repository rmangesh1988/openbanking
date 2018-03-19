package eu.dnb.openbanking.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by rmang on 15-03-2018.
 */
@Entity
public class CustomerEngagement {

    public enum EngagementType {
        DEPOSIT, CREDITCARD, MORTGAGE
    }

    @Id
    @NotNull
    private String engagementId;

    private EngagementType engagementType;

    @Pattern(regexp = "^\\d{11}$", message = "{accountNumber.invalid}")
    private String accountNumber;

    private String friendlyName;

    private Boolean corporate;

    public CustomerEngagement() {
    }

    public String getEngagementId() {
        return engagementId;
    }

    public void setEngagementId(String engagementId) {
        this.engagementId = engagementId;
    }

    public EngagementType getEngagementType() {
        return engagementType;
    }

    public void setEngagementType(EngagementType engagementType) {
        this.engagementType = engagementType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

    public void setFriendlyName(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public Boolean getCorporate() {
        return corporate;
    }

    public void setCorporate(Boolean corporate) {
        this.corporate = corporate;
    }

    @Override
    public String toString() {
        return "CustomerEngagement{" +
                "engagementId='" + engagementId + '\'' +
                ", engagementType=" + engagementType +
                ", accountNumber='" + accountNumber + '\'' +
                ", friendlyName='" + friendlyName + '\'' +
                ", corporate=" + corporate +
                '}';
    }
}
