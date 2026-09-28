package com.shopease.backend.models;
import jakarta.persistence.*;
@Entity
public class Payment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne @JoinColumn(name = "order_id")
    private Order order;
    private String paymentMethod;
    private String paymentStatus;
    private String transactionId;
    private Double amount;
    public Payment() {}
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public Order getOrder() { return order; } public void setOrder(Order order) { this.order = order; }
    public String getPaymentMethod() { return paymentMethod; } public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public String getPaymentStatus() { return paymentStatus; } public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
    public String getTransactionId() { return transactionId; } public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
    public Double getAmount() { return amount; } public void setAmount(Double amount) { this.amount = amount; }
}
