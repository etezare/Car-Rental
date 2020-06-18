package edu.miu.cs425.eCarRental.model;

import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
	
	@Id
	@Column(name = "user_id",nullable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@Column(name = "first_name",nullable=false)
	@NotBlank(message = "Please provide user first name")
    private String firstName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "last_name",nullable=false)
	@NotBlank(message = "Please provide user last name")
    private String lastName;

	@Column(name = "date_of_birth",nullable=false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Past(message = "Please provide user date of birth")
    private LocalDate dateOfBirth;

	@Column(name = "license_number",nullable=false)
	@NotBlank(message = "Please provide user date of birth")
    private String licenseNumber;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Booking> bookings;

	@ManyToMany
	@JoinTable(
            name="user_roles",
            joinColumns={@JoinColumn(name="user_id", referencedColumnName="user_id")},
            inverseJoinColumns={@JoinColumn(name="role_id", referencedColumnName="role_id")})
	private List<Role> roles;
	
	@OneToOne
	@JoinColumn(name="credential_id", nullable = true, unique = true)
	private Credential credential;

    public User() {

	}

	public User(Long userId, String firstName, String lastName, LocalDate dateOfBirth, String licenseNumber) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.licenseNumber = licenseNumber;

	}

	public User(String firstName,
				String lastName,
				LocalDate dateOfBirth, String licenseNumber,
				List<Booking> bookings, List<Role> roles, Credential credential) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.licenseNumber = licenseNumber;
		this.bookings = bookings;
		this.roles = roles;
		this.credential = credential;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Credential getCredential() {
		return credential;
	}

	public void setCredential(Credential credential) {
		this.credential = credential;
	}

	@Override
	public String toString() {
		return "User{" +
				"userId=" + userId +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", dateOfBirth=" + dateOfBirth +
				", licenseNumber=" + licenseNumber +
				", credential=" + credential +
				'}';
	}
}
