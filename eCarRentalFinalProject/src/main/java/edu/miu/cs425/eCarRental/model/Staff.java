package edu.miu.cs425.eCarRental.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "staffs")
public class Staff {

    @Id
    @Column(name = "staff_id",nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long staffId;

    @Column(name = "staff_name",nullable=false)
    @NotBlank(message = "Please provide user staff")
    private String staffName;

    @ManyToMany(mappedBy = "staffs")
    private List<Customer> customers;

    public Staff() {
    }

    public Staff(Long staffId, String staffName, List<Customer> customers) {
        this.staffId = staffId;
        this.staffName = staffName;
        this.customers = customers;
    }

    public Staff(@NotBlank(message = "Please provide user staff") String staffName, List<Customer> customers) {
        this.staffName = staffName;
        this.customers = customers;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "staffId=" + staffId +
                ", staffName='" + staffName + '\'' +
                '}';
    }
}
