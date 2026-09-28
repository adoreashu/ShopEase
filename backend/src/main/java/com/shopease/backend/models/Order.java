package com.shopease.backend.models;
import jakarta.persistence.*;
@Entity
@Table(name = "orders")
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne @JoinColumn(name = "address_id")
    private Address address;
    private String orderStatus;
    private Double subtotal;
    private Double tax;
    private Double total;
    public Order() {}
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public User getUser() { return user; } public void setUser(User user) { this.user = user; }
    public Address getAddress() { return address; } public void setAddress(Address address) { this.address = address; }
    public String getOrderStatus() { return orderStatus; } public void setOrderStatus(String orderStatus) { this.orderStatus = orderStatus; }
    public Double getSubtotal() { return subtotal; } public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }
    public Double getTax() { return tax; } public void setTax(Double tax) { this.tax = tax; }
    public Double getTotal() { return total; } public void setTotal(Double total) { this.total = total; }
}
