package edu.miu.cs425.eCarRental.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;


@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @Column(name = "booking_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @Column(name = "reference_number")
    @NotBlank
    private String referenceNumber;

    @Column(name = "booking_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotBlank(message = "Please provide booking date")
    private LocalDate bookingDate;

    @Column(name = "start_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotBlank(message = "Please provide booking start date and time")
    private LocalDate startDate;

    @Column(name = "end_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotBlank(message = "Please provide booking end date and time")
    private LocalDate endDate;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "first_name")
    @NotBlank(message = "Please provide user first name")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "Please provide user last name")
    private String lastName;

    @Column(name = "date_of_birth")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotBlank(message = "Please provide user date of birth")
    private LocalDate dateOfBirth;

    @Column(name = "license_number")
    private Long licenseNumber;

    @Column(name = "email")
    @NotBlank(message = "Please provide email address")
    private String email;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = true)
    private Vehicle vehicle;

//	@ManyToOne
//	@JoinColumn(name="user_id", nullable = true, unique = true)
//	private User user;

    @OneToOne
    @JoinColumn(name = "payment_id", nullable = true, unique = true)
    private Payment payment;

    public Booking() {
    }

    public Booking(Long bookingId, String referenceNumber, LocalDate bookingDate, LocalDate startDate,
                   LocalDate endDate, Double totalPrice, String firstName, String lastName, LocalDate dateOfBirth,
                   Long licenseNumber, String email, Vehicle vehicle, Payment payment) {
        this.bookingId = bookingId;
        this.referenceNumber = referenceNumber;
        this.bookingDate = bookingDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = totalPrice;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.licenseNumber = licenseNumber;
        this.email = email;
        this.vehicle = vehicle;
        this.payment = payment;
    }

    public Booking(@NotBlank String referenceNumber, @NotBlank(message = "Please provide booking date") LocalDate bookingDate,
                   @NotBlank(message = "Please provide booking start date and time") LocalDate startDate,
                   @NotBlank(message = "Please provide booking end date and time") LocalDate endDate, Double totalPrice,
                   @NotBlank(message = "Please provide user first name") String firstName, @NotBlank(message = "Please provide user last name") String lastName,
                   @NotBlank(message = "Please provide user date of birth") LocalDate dateOfBirth, Long licenseNumber,
                   @NotBlank(message = "Please provide email address") String email, Vehicle vehicle, Payment payment) {
        this.referenceNumber = referenceNumber;
        this.bookingDate = bookingDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = totalPrice;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.licenseNumber = licenseNumber;
        this.email = email;
        this.vehicle = vehicle;
        this.payment = payment;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

}

