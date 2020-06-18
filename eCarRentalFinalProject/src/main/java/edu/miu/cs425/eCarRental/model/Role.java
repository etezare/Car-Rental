package edu.miu.cs425.eCarRental.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @Column(name = "role_id",nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(name = "role_name",nullable=false)
    @NotBlank(message = "Please provide user role")
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public Role() {
    }

    public Role(Long roleId, String roleName, List<User> users) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.users = users;
    }

    public Role(@NotBlank(message = "Please provide user role") String roleName, List<User> users) {
        this.roleName = roleName;
        this.users = users;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
