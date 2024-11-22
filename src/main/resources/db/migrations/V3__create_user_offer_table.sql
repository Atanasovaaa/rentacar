CREATE TABLE IF NOT EXISTS td_offers(
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    car_id INT,
    rent_days INT,
    total_price DECIMAL(10, 2),
    is_existing INT DEFAULT 1,
    is_accepted INT DEFAULT 0,
    FOREIGN KEY (user_id) REFERENCES td_users(id),
    FOREIGN KEY (car_id) REFERENCES td_cars(id)
    );
