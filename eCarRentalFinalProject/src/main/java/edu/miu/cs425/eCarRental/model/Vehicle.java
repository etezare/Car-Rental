package edu.miu.cs425.eCarRental.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "vehicles")
public class Vehicle {
	
	@Id
	@Column(name = "vehicle_id",nullable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long vehicleId;
	
	@Column(name = "vehicle_number",nullable=true)
	@NotBlank(message = "Please provide vehicle number")
	private String vehicleNumber;
	
	@Column(name = "plate_number",nullable=false)
	@NotBlank
    private String plateNumber;
	
	@Column(name = "make",nullable=false)
	@NotBlank(message = "Please provide make")
    private String make;
	
	@Column(name = "model",nullable=false)
	@NotBlank(message = "Please provide model")
    private String model;
	
	@Column(name = "year",nullable=false)
//	@NotNull(message="year is required")
    private Integer year;

	@ManyToOne
	@JoinColumn(name="category_id", nullable = false)
	private Category category;

	@OneToMany(mappedBy = "vehicle")
	private List<Booking> bookings;

	public Vehicle() {}

	public Vehicle(Long vehicleId, String vehicleNumber, String plateNumber, String make, String model,
			Integer year) {
		super();
		this.vehicleId = vehicleId;
		this.vehicleNumber = vehicleNumber;
		this.plateNumber = plateNumber;
		this.make = make;
		this.model = model;
		this.year = year;
	}

	public Vehicle( String vehicleNumber, String plateNumber,
				   String make,
				  String model, Integer year,
				   Category category, List<Booking> bookings) {
		this.vehicleNumber = vehicleNumber;
		this.plateNumber = plateNumber;
		this.make = make;
		this.model = model;
		this.year = year;
		this.category = category;
		this.bookings = bookings;
	}

	public Long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	@Override
	public String toString() {
		return "Vehicle{" +
				"vehicleId=" + vehicleId +
				", vehicleNumber='" + vehicleNumber + '\'' +
				", plateNumber=" + plateNumber +
				", make='" + make + '\'' +
				", model='" + model + '\'' +
				", year=" + year +
				'}';
	}
}
