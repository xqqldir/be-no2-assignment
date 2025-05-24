CREATE TABLE schedule (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          title VARCHAR(255) NOT NULL,
                          username VARCHAR(100) NOT NULL,
                          password VARCHAR(255) NOT NULL,
                          created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          modified_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);