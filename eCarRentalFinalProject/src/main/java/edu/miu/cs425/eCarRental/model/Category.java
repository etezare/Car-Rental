package edu.miu.cs425.eCarRental.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "catagories")
public class Category {


    @Id
    @Column(name = "categoryId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(name = "categoryName")
    @NotBlank(message = "*Please provide category name")
    private String categoryName;
    @Column(name = "seats")
    @NotBlank(message = "*Please provide number of seats")
    private Integer seats;

    @Column(name = "doors")
    @NotBlank(message = "*Please provide number of doors")
    private Integer doors;

    @Column(name = "fuelEconomy")
    @NotBlank(message = "*Please provide fuel economy")
    private Integer fuelEconomy;

    @Column(name = "ratePerDay")
    @NotBlank(message = "*Please provide rate per day")
    private Double ratePerDay;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Vehicle> vehicles;

    public Category() {
    }

    public Category(Long categoryId, @NotBlank(message = "*Please provide category name") String categoryName,
                    Integer seats, Integer doors, Integer fuelEconomy, Double ratePerDay) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.seats = seats;
        this.doors = doors;
        this.fuelEconomy = fuelEconomy;
        this.ratePerDay = ratePerDay;
    }

    public Category(@NotBlank(message = "*Please provide category name") String categoryName,
                    @NotBlank(message = "*Please provide number of seats") Integer seats,
                    @NotBlank(message = "*Please provide number of doors") Integer doors,
                    @NotBlank(message = "*Please provide fuel economy") Integer fuelEconomy,
                    @NotBlank(message = "*Please provide rate per day") Double ratePerDay, List<Vehicle> vehicles) {
        this.categoryName = categoryName;
        this.seats = seats;
        this.doors = doors;
        this.fuelEconomy = fuelEconomy;
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

    public Integer getFuelEconomy() {
        return fuelEconomy;
    }

    public void setFuelEconomy(Integer fuelEconomy) {
        this.fuelEconomy = fuelEconomy;
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
}


