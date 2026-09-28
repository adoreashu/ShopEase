package com.shopease.backend.models;
import jakarta.persistence.*;
@Entity
public class Address {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne @JoinColumn(name = "user_id")
    private User user;
    private String fullName;
    private String phone;
    private String addressLine;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    public Address() {}
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public User getUser() { return user; } public void setUser(User user) { this.user = user; }
    public String getFullName() { return fullName; } public void setFullName(String fullName) { this.fullName = fullName; }
    public String getPhone() { return phone; } public void setPhone(String phone) { this.phone = phone; }
    public String getAddressLine() { return addressLine; } public void setAddressLine(String addressLine) { this.addressLine = addressLine; }
    public String getCity() { return city; } public void setCity(String city) { this.city = city; }
    public String getState() { return state; } public void setState(String state) { this.state = state; }
    public String getPostalCode() { return postalCode; } public void setPostalCode(String postalCode) { this.postalCode = postalCode; }
    public String getCountry() { return country; } public void setCountry(String country) { this.country = country; }
}
