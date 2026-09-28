package com.shopease.backend.controllers;
import com.shopease.backend.models.*;
import com.shopease.backend.repositories.*;
import com.shopease.backend.dto.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired CartRepository cartRepository;
    @Autowired CartItemRepository cartItemRepository;
    @Autowired UserRepository userRepository;
    @Autowired ProductRepository productRepository;

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email).orElse(null);
    }
    
    private Cart getOrCreateCart() {
        User user = getCurrentUser();
        Cart cart = cartRepository.findAll().stream().filter(c -> c.getUser().getId().equals(user.getId())).findFirst().orElse(null);
        if (cart == null) {
            cart = new Cart(); cart.setUser(user); return cartRepository.save(cart);
        }
        return cart;
    }

    @GetMapping
    public List<CartItem> getCartItems() {
        Cart cart = getOrCreateCart();
        return cartItemRepository.findAll().stream().filter(ci -> ci.getCart().getId().equals(cart.getId())).toList();
    }

    @PostMapping("/items")
    public ResponseEntity<?> addToCart(@RequestBody CartItemRequest request) {
        Cart cart = getOrCreateCart();
        Product product = productRepository.findById(request.getProductId()).orElse(null);
        if (product == null) return ResponseEntity.badRequest().body(new MessageResponse("Product not found"));
        
        Optional<CartItem> existing = cartItemRepository.findAll().stream()
            .filter(ci -> ci.getCart().getId().equals(cart.getId()) && ci.getProduct().getId().equals(product.getId())).findFirst();
        
        if (existing.isPresent()) {
            CartItem ci = existing.get();
            ci.setQuantity(ci.getQuantity() + request.getQuantity());
            cartItemRepository.save(ci);
        } else {
            CartItem ci = new CartItem(); ci.setCart(cart); ci.setProduct(product); ci.setQuantity(request.getQuantity());
            cartItemRepository.save(ci);
        }
        return ResponseEntity.ok(new MessageResponse("Added to cart"));
    }

    @PutMapping("/items/{id}")
    public ResponseEntity<?> updateQuantity(@PathVariable Long id, @RequestBody CartItemRequest request) {
        CartItem ci = cartItemRepository.findById(id).orElse(null);
        if (ci != null) {
            ci.setQuantity(request.getQuantity());
            cartItemRepository.save(ci);
        }
        return ResponseEntity.ok(new MessageResponse("Updated"));
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<?> removeItem(@PathVariable Long id) {
        cartItemRepository.deleteById(id);
        return ResponseEntity.ok(new MessageResponse("Removed"));
    }
}
class CartItemRequest {
    private Long productId; private Integer quantity;
    public Long getProductId() { return productId; } public void setProductId(Long productId) { this.productId = productId; }
    public Integer getQuantity() { return quantity; } public void setQuantity(Integer quantity) { this.quantity = quantity; }
}
