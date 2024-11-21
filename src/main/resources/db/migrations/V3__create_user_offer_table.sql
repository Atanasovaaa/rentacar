CREATE TABLE IF NOT EXISTS td_offers(
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    car_id INT,
    rent_days INT,
    start_date DATE,
    total_price DECIMAL(10, 2),
    is_active INT DEFAULT 1,
    is_existing INT DEFAULT 1,
    FOREIGN KEY (user_id) REFERENCES td_users(id),
    FOREIGN KEY (car_id) REFERENCES td_cars(id)
    );

CREATE TABLE IF NOT EXISTS tc_user_offer(
    user_id INT,
    offer_id INT,
    is_accepted INT DEFAULT 0,
    PRIMARY KEY (user_id, offer_id),
    FOREIGN KEY (user_id) REFERENCES td_users(id),
    FOREIGN KEY (offer_id) REFERENCES td_offers(id)
    );