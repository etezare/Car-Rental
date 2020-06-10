package edu.miu.cs425.eCarRental.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


    @Entity
    @Table(name = "credentials")
    public class Credential {

        @Id
        @Column(name = "credential_id",nullable=false)
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long credentialId;

        @Column(name = "user_name",nullable=false)
        @NotBlank(message = "Please provide user name")
        private String userName;

        @Column(name = "password",nullable=false)
        @NotBlank(message = "Please provide password")
        private String password;

        @OneToOne(mappedBy = "credential", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
        private Customer customer;

        public Credential() {}

        public Credential(Long credentialId, String userName, String password, Customer customer) {
            this.credentialId = credentialId;
            this.userName = userName;
            this.password = password;
            this.customer = customer;
        }

        public Credential(@NotBlank(message = "*Please provide user name") String userName,
                          @NotBlank(message = "*Please provide password") String password, Customer customer) {
            this.userName = userName;
            this.password = password;
            this.customer = customer;
        }

        public Long getCredentialId() {
            return credentialId;
        }

        public void setCredentialId(Long credentialId) {
            this.credentialId = credentialId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Customer getUser() {
            return customer;
        }

        public void setUser(Customer customer) {
            this.customer = customer;
        }

        @Override
        public String toString() {
            return "Credential{" +
                    "credentialId=" + credentialId +
                    ", userName='" + userName + '\'' +
                    ", password='" + password + '\'' +
                    ", customer=" + customer +
                    '}';
        }
    }
