CREATE TABLE IF NOT EXISTS td_users(
    id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(256),
    last_name VARCHAR(256),
    address VARCHAR(256),
    phone_number VARCHAR(10),
    age INT,
    has_crashes INT DEFAULT 0,
    is_active INT DEFAULT 1
    );
