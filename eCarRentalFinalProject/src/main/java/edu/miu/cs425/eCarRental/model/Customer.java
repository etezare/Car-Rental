package edu.miu.cs425.eCarRental.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Customers")
public class Customer {
	
	@Id
	@Column(name = "Customer_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;

	@Column(name = "first_name")
	@NotBlank(message = "*Please provide user first name")
    private String firstName;

	@Column(name = "last_name")
	@NotBlank(message = "*Please provide user last name")
    private String lastName;

	@Column(name = "date_of_birth")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotBlank(message = "*Please provide user date of birth")
    private LocalDate dateOfBirth;

	@Column(name = "license_number")
    private Long licenseNumber;
	
	@OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Booking> bookings;

	@ManyToMany
	@JoinTable(
            name="Customer_staffes",
            joinColumns={@JoinColumn(name="customer_id", referencedColumnName="customer_id")},
            inverseJoinColumns={@JoinColumn(name="staff_id", referencedColumnName="staff_id")})
	private List<Staff> staffes;
	
	@OneToOne
	@JoinColumn(name="credential_id", nullable = true, unique = true)
	private Credential credential;

    public Customer() {

	}

	public Customer(Long customerId, String firstName, String lastName, LocalDate dateOfBirth, Long licenseNumber) {
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.licenseNumber = licenseNumber;

	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long userId) {
		this.customerId = userId;
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

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Long getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(Long licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public List<Staff> getStaffes() {
		return staffes;
	}

	public void setStaffes(List<Staff> staffes) {
		this.staffes = staffes;
	}

	public Credential getCredential() {
		return credential;
	}

	public void setCredential(Credential credential) {
		this.credential = credential;
	}
    
}
