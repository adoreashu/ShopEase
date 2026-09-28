package com.shopease.backend.controllers;
import com.shopease.backend.models.*;
import com.shopease.backend.repositories.*;
import com.shopease.backend.dto.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired OrderRepository orderRepository;
    @Autowired OrderItemRepository orderItemRepository;
    @Autowired CartRepository cartRepository;
    @Autowired CartItemRepository cartItemRepository;
    @Autowired UserRepository userRepository;
    @Autowired AddressRepository addressRepository;

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email).orElse(null);
    }

    @PostMapping
    public ResponseEntity<?> placeOrder(@RequestBody OrderRequest request) {
        User user = getCurrentUser();
        Cart cart = cartRepository.findAll().stream().filter(c -> c.getUser().getId().equals(user.getId())).findFirst().orElse(null);
        if (cart == null) return ResponseEntity.badRequest().body(new MessageResponse("Cart empty"));
        List<CartItem> items = cartItemRepository.findAll().stream().filter(ci -> ci.getCart().getId().equals(cart.getId())).toList();
        if (items.isEmpty()) return ResponseEntity.badRequest().body(new MessageResponse("Cart empty"));

        Address addr = new Address();
        addr.setUser(user); addr.setFullName(request.getFullName()); addr.setPhone(request.getPhone());
        addr.setAddressLine(request.getAddressLine()); addr.setCity(request.getCity()); addr.setState(request.getState());
        addr.setPostalCode(request.getPostalCode()); addr.setCountry(request.getCountry());
        addressRepository.save(addr);

        double subtotal = items.stream().mapToDouble(i -> (i.getProduct().getPrice() - i.getProduct().getDiscount()) * i.getQuantity()).sum();
        
        Order order = new Order();
        order.setUser(user); order.setAddress(addr); order.setOrderStatus("PLACED");
        order.setSubtotal(subtotal); order.setTax(subtotal * 0.1); order.setTotal(subtotal + (subtotal * 0.1));
        orderRepository.save(order);

        for(CartItem ci : items) {
            OrderItem oi = new OrderItem(); oi.setOrder(order); oi.setProduct(ci.getProduct());
            oi.setQuantity(ci.getQuantity()); oi.setPrice(ci.getProduct().getPrice() - ci.getProduct().getDiscount());
            orderItemRepository.save(oi);
            cartItemRepository.delete(ci);
        }
        return ResponseEntity.ok(order);
    }

    @GetMapping
    public List<Order> getOrders() {
        User user = getCurrentUser();
        return orderRepository.findAll().stream().filter(o -> o.getUser().getId().equals(user.getId())).toList();
    }
}
class OrderRequest {
    private String fullName; private String phone; private String addressLine; private String city; private String state; private String postalCode; private String country;
    public String getFullName() { return fullName; } public void setFullName(String fullName) { this.fullName = fullName; }
    public String getPhone() { return phone; } public void setPhone(String phone) { this.phone = phone; }
    public String getAddressLine() { return addressLine; } public void setAddressLine(String addressLine) { this.addressLine = addressLine; }
    public String getCity() { return city; } public void setCity(String city) { this.city = city; }
    public String getState() { return state; } public void setState(String state) { this.state = state; }
    public String getPostalCode() { return postalCode; } public void setPostalCode(String postalCode) { this.postalCode = postalCode; }
    public String getCountry() { return country; } public void setCountry(String country) { this.country = country; }
}
