package edu.miu.cs425.eCarRental.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "catagories")
public class Category {


    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(name = "category_name")
    @NotBlank(message = "Please provide category name")
    private String categoryName;

    @Column(name = "seats")
    @Min(2)
    @Max(8)
    private Integer seats;

    @Column(name = "doors")
    @Min(2)
    @Max(4)
    //@NotBlank(message = "Please provide number of doors")
    private Integer doors;

    @Column(name = "rate_per_day")
    @NotBlank(message = "Please provide rate per day")
    @DecimalMin(value = "0.00" , message = "Please provide rate per day")
    private Double ratePerDay;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Vehicle> vehicles;

    public Category() {
    }

    public Category(Long categoryId, @NotBlank(message = "Please provide category name") String categoryName,
                    Integer seats, Integer doors, Double ratePerDay) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.seats = seats;
        this.doors = doors;
        this.ratePerDay = ratePerDay;
    }

    public Category(@NotBlank(message = "Please provide category name") String categoryName,
                    @NotBlank(message = "Please provide number of seats") Integer seats,
                    @NotBlank(message = "Please provide number of doors") Integer doors,
                    @NotBlank(message = "Please provide rate per day") Double ratePerDay, List<Vehicle> vehicles) {
        this.categoryName = categoryName;
        this.seats = seats;
        this.doors = doors;
        this.ratePerDay = ratePerDay;
        this.vehicles = vehicles;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public Integer getDoors() {
        return doors;
    }

    public void setDoors(Integer doors) {
        this.doors = doors;
    }

    public Double getRatePerDay() {
        return ratePerDay;
    }

    public void setRatePerDay(Double ratePerDay) {
        this.ratePerDay = ratePerDay;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", seats=" + seats +
                ", doors=" + doors +
                ", ratePerDay=" + ratePerDay +
                '}';
    }
}


