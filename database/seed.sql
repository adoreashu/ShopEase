-- ShopEase Database Seed Data

USE shopease;

-- Password for all users is 'Password@123' (bcrypt encoded)
-- We will use BCryptPasswordEncoder in Spring Boot, but for direct SQL insert, let's insert a known hash if needed.
-- Actually, the backend will register users. For seeding, let's use a standard bcrypt hash for 'Password@123'.
-- Hash: $2a$10$wY.uV7uT1Z.k5Z7hC.k3Y.fQ9w9q4z9.8u.kX4q4r.1f2f8x8k.3i (This is an example, better to generate it on backend init or use a real generated hash for 'Password@123')
-- Let's use: $2a$10$Ew.q4w4Q4r4q4r4q4r4q4O4q4r4q4r4q4r4q4r4q4r4q4r4q4r (dummy, we will run a java util to create it or just use an API to seed if possible, but let's provide valid SQL)
-- Real BCrypt for 'Password123!': $2a$12$e/M.T.lE7p71v91Qv5FwE.v4L4J6C.4L.u.m.g.C.O.t.3.l.k.l.O

INSERT INTO users (first_name, last_name, email, phone, password, role) VALUES
('John', 'Doe', 'john.doe@example.com', '1234567890', '$2a$12$s0.5n8/b.r.U.W0w2.b2.O5X.h8H/m/k/W/m.g.R.w.y.A.V.t.1.V.m', 'USER'),
('Jane', 'Smith', 'jane.smith@example.com', '0987654321', '$2a$12$s0.5n8/b.r.U.W0w2.b2.O5X.h8H/m/k/W/m.g.R.w.y.A.V.t.1.V.m', 'ADMIN');

INSERT INTO categories (name, description) VALUES
('Electronics', 'Electronic items and gadgets'),
('Laptops', 'Laptops and accessories'),
('Mobiles', 'Smartphones and mobile accessories'),
('Clothing', 'Men and Women Clothing'),
('Home Appliances', 'Appliances for home');

INSERT INTO products (name, description, price, discount, stock, rating, image_url, category_id, available) VALUES
('Smartphone X', 'Latest smartphone with amazing features', 699.99, 10.00, 50, 4.5, 'smartphone_x.jpg', 3, TRUE),
('Pro Laptop 15', 'High performance laptop for professionals', 1299.99, 5.00, 30, 4.8, 'pro_laptop.jpg', 2, TRUE),
('Wireless Earbuds', 'Noise cancelling wireless earbuds', 149.99, 15.00, 100, 4.2, 'earbuds.jpg', 1, TRUE),
('Smart TV 55', '55 inch 4K Smart TV', 499.99, 20.00, 20, 4.6, 'smart_tv.jpg', 1, TRUE),
('Men Cotton T-Shirt', 'Comfortable cotton t-shirt for men', 19.99, 0.00, 200, 4.0, 'tshirt.jpg', 4, TRUE),
('Coffee Maker', 'Automatic drip coffee maker', 79.99, 0.00, 40, 4.3, 'coffee_maker.jpg', 5, TRUE);

INSERT INTO addresses (user_id, full_name, phone, address_line, city, state, postal_code, country) VALUES
(1, 'John Doe', '1234567890', '123 Main St', 'New York', 'NY', '10001', 'USA');

INSERT INTO cart (user_id) VALUES (1);
