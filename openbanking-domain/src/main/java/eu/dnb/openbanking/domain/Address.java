package eu.dnb.openbanking.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by rmang on 15-03-2018.
 */
@Entity
public class Address {

    @Id
    @GeneratedValue
    private Long id;

    private String postalAddressLine1;

    private String postalAddressLine2;

    private String postalAddressLine3;

    @Pattern(regexp = "^\\d{4}$", message = "{postCode.invalid}")
    private String postCode;

    private String postCity;

    @Size(min = 2, max = 2)
    private String postCountry;

    public Address() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostalAddressLine1() {
        return postalAddressLine1;
    }

    public void setPostalAddressLine1(String postalAddressLine1) {
        this.postalAddressLine1 = postalAddressLine1;
    }

    public String getPostalAddressLine2() {
        return postalAddressLine2;
    }

    public void setPostalAddressLine2(String postalAddressLine2) {
        this.postalAddressLine2 = postalAddressLine2;
    }

    public String getPostalAddressLine3() {
        return postalAddressLine3;
    }

    public void setPostalAddressLine3(String postalAddressLine3) {
        this.postalAddressLine3 = postalAddressLine3;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPostCity() {
        return postCity;
    }

    public void setPostCity(String postCity) {
        this.postCity = postCity;
    }

    public String getPostCountry() {
        return postCountry;
    }

    public void setPostCountry(String postCountry) {
        this.postCountry = postCountry;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", postalAddressLine1='" + postalAddressLine1 + '\'' +
                ", postalAddressLine2='" + postalAddressLine2 + '\'' +
                ", postalAddressLine3='" + postalAddressLine3 + '\'' +
                ", postCode='" + postCode + '\'' +
                ", postCity='" + postCity + '\'' +
                ", postCountry='" + postCountry + '\'' +
                '}';
    }
}
