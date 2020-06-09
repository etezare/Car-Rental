
import com.sun.istack.NotNull;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "addresses")
public class Address {



    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @Column(name = "street_line1")
    @NotNull(message = "*Please provide street number")
    private String streetLine1;

    @Column(name = "street_line2")
    private String streetLine2;

    @Column(name = "city")
    @NotNull(message = "*Please provide city name")
    private String city;

    @Column(name = "state")
    @NotNull(message = "*Please provide state name")
    private String state;

    @Column(name = "zip_code")
    @NotNull(message = "*Please provide zip/area code")
    private Integer zipCode;

    @Column(name = "country")
    @NotNull(message = "*Please provide country name")
    private String country;

    @OneToOne(mappedBy = "billingAddress", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Payment payment;

    public Address(Long addressId, String streetLine1, String streetLine2, String city, String state, Integer zipCode, String country) {
        this.addressId = addressId;
        this.streetLine1 = streetLine1;
        this.streetLine2 = streetLine2;
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

    public String getStreetLine1() {
        return streetLine1;
    }

    public void setStreetLine1(String streetLine1) {
        this.streetLine1 = streetLine1;
    }

    public String getStreetLine2() {
        return streetLine2;
    }

    public void setStreetLine2(String streetLine2) {
        this.streetLine2 = streetLine2;
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

}
