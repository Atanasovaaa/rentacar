CREATE TABLE IF NOT EXISTS td_cars(
    id_car INT PRIMARY KEY AUTO_INCREMENT,
    brand_name VARCHAR(256),
    model_name VARCHAR(256),
    city_name VARCHAR(256),
    price_per_day DECIMAL(10, 2),
    is_available INT DEFAULT 1,
    is_existing INT DEFAULT 1
);