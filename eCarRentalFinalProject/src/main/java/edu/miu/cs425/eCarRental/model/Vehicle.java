package edu.miu.cs425.eCarRental.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "vehicles")
public class Vehicle {
	
	@Id
	@Column(name = "vehicle_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long vehicleId;
	
	@Column(name = "vehicle_number")
	@NotBlank(message = "*Please provide vehicle number")
	private String vehicleNumber;
	
	@Column(name = "plate_number")
    private Integer plateNumber;
	
	@Column(name = "make")
	@NotBlank(message = "*Please provide make")
    private String make;
	
	@Column(name = "model")
	@NotBlank(message = "*Please provide model")
    private String model;
	
	@Column(name = "year")
	@NotBlank(message = "*Please provide year")
    private Integer year;
	
	@ManyToOne
	@JoinColumn(name="category_id", nullable = false)
	@NotBlank(message = "*Please select category")
	private Category category;
	
	@OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
	private List<Booking> bookings;
    
	public Vehicle() {}

	public Vehicle(Long vehicleId, String vehicleNumber, Integer plateNumber, String make, String model,
			Integer year) {
		super();
		this.vehicleId = vehicleId;
		this.vehicleNumber = vehicleNumber;
		this.plateNumber = plateNumber;
		this.make = make;
		this.model = model;
		this.year = year;
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

	public Integer getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(Integer plateNumber) {
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
	
}
