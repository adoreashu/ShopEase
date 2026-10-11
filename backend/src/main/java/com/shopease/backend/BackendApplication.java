package com.shopease.backend;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.shopease.backend.models.*;
import com.shopease.backend.repositories.*;

@SpringBootApplication
public class BackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
    @Bean
    public CommandLineRunner initData(UserRepository userRepo, CategoryRepository catRepo, ProductRepository prodRepo, CartRepository cartRepo, PasswordEncoder encoder) {
        return args -> {
            if(userRepo.count() == 0) {
                User u1 = new User(); u1.setFirstName("John"); u1.setLastName("Doe"); u1.setEmail("john.doe@example.com"); u1.setPhone("1234567890"); u1.setPassword(encoder.encode("Password@123")); u1.setRole("USER"); userRepo.save(u1);
                User u2 = new User(); u2.setFirstName("Admin"); u2.setLastName("User"); u2.setEmail("admin@example.com"); u2.setPhone("0987654321"); u2.setPassword(encoder.encode("Password@123")); u2.setRole("ADMIN"); userRepo.save(u2);
                Cart c1 = new Cart(); c1.setUser(u1); cartRepo.save(c1);
                Cart c2 = new Cart(); c2.setUser(u2); cartRepo.save(c2);

                Category c = new Category(); c.setName("Electronics"); c.setDescription("Gadgets"); catRepo.save(c);
                Product p = new Product(); p.setName("Smartphone X"); p.setDescription("Latest flagship smartphone with AMOLED display"); p.setPrice(699.99); p.setDiscount(10.00); p.setStock(50); p.setCategory(c); p.setAvailable(true); prodRepo.save(p);
            }
            if(prodRepo.count() <= 1) {
                Category c = catRepo.findAll().stream().findFirst().orElse(null);
                Product p2 = new Product(); p2.setName("Wireless Noise-Cancelling Headphones"); p2.setDescription("Premium over-ear headphones with 30-hour battery life"); p2.setPrice(199.99); p2.setDiscount(15.00); p2.setStock(40); p2.setCategory(c); p2.setAvailable(true); prodRepo.save(p2);
                Product p3 = new Product(); p3.setName("Ultra-Slim Pro Laptop 15\""); p3.setDescription("High performance laptop with 16GB RAM and 512GB SSD"); p3.setPrice(999.99); p3.setDiscount(5.00); p3.setStock(25); p3.setCategory(c); p3.setAvailable(true); prodRepo.save(p3);
                Product p4 = new Product(); p4.setName("Smart Fitness Watch"); p4.setDescription("Water-resistant fitness tracker with heart-rate and GPS monitoring"); p4.setPrice(149.99); p4.setDiscount(20.00); p4.setStock(60); p4.setCategory(c); p4.setAvailable(true); prodRepo.save(p4);
                Product p5 = new Product(); p5.setName("Mechanical Gaming Keyboard"); p5.setDescription("RGB backlit keyboard with tactile mechanical switches"); p5.setPrice(89.99); p5.setDiscount(10.00); p5.setStock(80); p5.setCategory(c); p5.setAvailable(true); prodRepo.save(p5);
                Product p6 = new Product(); p6.setName("4K Ultra HD 27\" Monitor"); p6.setDescription("IPS display with 144Hz refresh rate and HDR400"); p6.setPrice(349.99); p6.setDiscount(12.00); p6.setStock(30); p6.setCategory(c); p6.setAvailable(true); prodRepo.save(p6);
            }

            prodRepo.findAll().forEach(item -> {
                String n = item.getName() != null ? item.getName().toLowerCase() : "";
                if(n.contains("headphone")) {
                    item.setImageUrl("https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=600&auto=format&fit=crop&q=80");
                } else if(n.contains("smartphone") || n.contains("phone")) {
                    item.setImageUrl("https://images.unsplash.com/photo-1511707171634-5f897ff02560?w=600&auto=format&fit=crop&q=80");
                } else if(n.contains("laptop")) {
                    item.setImageUrl("https://images.unsplash.com/photo-1496181133206-80ce9b88a853?w=600&auto=format&fit=crop&q=80");
                } else if(n.contains("watch")) {
                    item.setImageUrl("https://images.unsplash.com/photo-1523275335684-37898b6baf30?w=600&auto=format&fit=crop&q=80");
                } else if(n.contains("keyboard")) {
                    item.setImageUrl("https://images.unsplash.com/photo-1587829741301-dc798b83add3?w=600&auto=format&fit=crop&q=80");
                } else if(n.contains("monitor")) {
                    item.setImageUrl("https://images.unsplash.com/photo-1527443224154-c4a3942d3acf?w=600&auto=format&fit=crop&q=80");
                }
                prodRepo.save(item);
            });
        };
    }
}
