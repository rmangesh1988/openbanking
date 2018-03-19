package eu.dnb.openbanking.domain;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

/**
 * Created by rmang on 15-03-2018.
 */
@Entity
public class Customer {

    public enum CustomerType{
        PERSON, COMPANY;
    }

    @Id
    @NotNull
    @Size(min = 9, max = 11)
    private String customerId;

    private String firstName;

    private String lastName;

    @NotNull
    private CustomerType customerType;

    private String companyName;

    @Valid
    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @Email
    private String email;

    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "{phone.invalid}")
    private String phone;

    @Size(min = 2, max = 2)
    private String countryOfBirth;

    @Size(min = 2, max = 2)
    private String countryCitizenship;

    @Size(min = 2, max = 2)
    private String countryTax;

    @Valid
    @OneToMany(cascade = CascadeType.ALL)
    private List<CustomerEngagement> customerEngagements;

    public Customer() {
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountryOfBirth() {
        return countryOfBirth;
    }

    public void setCountryOfBirth(String countryOfBirth) {
        this.countryOfBirth = countryOfBirth;
    }

    public String getCountryCitizenship() {
        return countryCitizenship;
    }

    public void setCountryCitizenship(String countryCitizenship) {
        this.countryCitizenship = countryCitizenship;
    }

    public String getCountryTax() {
        return countryTax;
    }

    public void setCountryTax(String countryTax) {
        this.countryTax = countryTax;
    }

    public List<CustomerEngagement> getCustomerEngagements() {
        return customerEngagements;
    }

    public void setCustomerEngagements(List<CustomerEngagement> customerEngagements) {
        this.customerEngagements = customerEngagements;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", customerType=" + customerType +
                ", companyName='" + companyName + '\'' +
                ", address=" + address +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", countryOfBirth='" + countryOfBirth + '\'' +
                ", countryCitizenship='" + countryCitizenship + '\'' +
                ", countryTax='" + countryTax + '\'' +
                ", customerEngagements=" + customerEngagements +
                '}';
    }
}
