package edu.miu.cs425.eCarRental.model;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;


    @Column(name = "street", nullable=false)
    @NotBlank(message = "Please provide street number!")
    private String street;

    @Column(name = "city",nullable=false)
    @NotBlank(message = "Please provide city name!")
    private String city;

    @Column(name = "state",nullable=false)
    @NotBlank(message = "Please provide state name!")
    private String state;

    @Column(name = "zip_code",nullable=false)
    @Digits(fraction = 0,integer = 5,message = "Please provide valid zip code")
    private Integer zipCode;


    @Column(name = "country",nullable=false)
    @NotBlank(message = "Please provide country name!")
    private String country;

    @OneToOne(mappedBy = "billingAddress", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Payment payment;

    public Address(Long addressId, String street, String city, String state, Integer zipCode, String country, Payment payment) {
        this.addressId = addressId;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
        this.payment = payment;
    }

    public Address(Long addressId, @NotBlank(message = "Please provide street number!") String street,
                   @NotBlank(message = "Please provide city name!")
            String city, @NotBlank(message = "Please provide state name!") String state,
                   @Digits(fraction = 0, integer = 5, message = "Please provide valid zip code")
            Integer zipCode, @NotBlank(message = "Please provide country name!") String country) {
        this.addressId = addressId;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
    }

    public Address(@NotBlank(message = "Please provide street number!") String street,
                   @NotBlank(message = "Please provide city name!") String city,
                   @NotBlank(message = "Please provide state name!") String state, Integer zipCode,
                   @NotBlank(message = "Please provide country name!") String country, Payment payment) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
        this.payment = payment;
    }
    public Address() {}

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode=" + zipCode +
                ", country='" + country + '\'' +
                ", payment=" + payment +
                '}';
    }
}
