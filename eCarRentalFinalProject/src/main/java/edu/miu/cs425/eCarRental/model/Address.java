package edu.miu.cs425.eCarRental.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @Column(name = "street_line")
    @NotBlank(message = "Please provide street number!")
    private String streetLine;

    @Column(name = "city")
    @NotBlank(message = "Please provide city name!")
    private String city;

    @Column(name = "state")
    @NotBlank(message = "Please provide state name!")
    private String state;

    @Column(name = "zip_code")
    @NotBlank(message = "Please provide zip/area code!")
    private Integer zipCode;

    @Column(name = "country")
    @NotBlank(message = "Please provide country name!")
    private String country;

//    @OneToOne(mappedBy = "billingAddress", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private Payment payment;

    public Address(Long addressId, String streetLine, String city, String state, Integer zipCode, String country) {
        this.addressId = addressId;
        this.streetLine = streetLine;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
    }

    public Address(@NotBlank(message = "Please provide street number!") String streetLine, @NotBlank(message = "Please provide city name!") String city, @NotBlank(message = "Please provide state name!") String state, @NotBlank(message = "Please provide zip/area code!") Integer zipCode, @NotBlank(message = "Please provide country name!") String country) {
        this.streetLine = streetLine;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;

    }
    public Address() {}

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getstreetLine() {
        return streetLine;
    }

    public void setstreetLine(String streetLine) {
        this.streetLine = streetLine;
    }
    

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", streetLine='" + streetLine + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode=" + zipCode +
                ", country='" + country + '\'' +

                '}';
    }


}
