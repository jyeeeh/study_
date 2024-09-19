CREATE TABLE user (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      username VARCHAR(50) NOT NULL UNIQUE,
                      email VARCHAR(100) NOT NULL UNIQUE,
                      password VARCHAR(255) NOT NULL,
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO user (username, email, password) VALUES
                                                 ('user1', 'user1@example.com', 'password_1'),
                                                 ('user2', 'user2@example.com', 'password_2'),
                                                 ('user3', 'user3@example.com', 'password_3'),
                                                 ('user4', 'user4@example.com', 'password_4');

select * from user;