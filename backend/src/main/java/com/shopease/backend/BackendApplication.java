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
                Product p = new Product(); p.setName("Smartphone X"); p.setDescription("Latest smartphone"); p.setPrice(699.99); p.setDiscount(10.00); p.setStock(50); p.setCategory(c); p.setAvailable(true); prodRepo.save(p);
            }
        };
    }
}
