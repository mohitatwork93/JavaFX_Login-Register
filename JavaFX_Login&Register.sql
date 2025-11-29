CREATE DATABASE demo_db;
USE demo_db;

CREATE TABLE user_account(
account_id INT UNIQUE AUTO_INCREMENT PRIMARY KEY,
firstname VARCHAR(45) NOT NULL,
lastname VARCHAR(45) NOT NULL,
username VARCHAR(45) UNIQUE NOT NULL,
password VARCHAR(45) NOT NULL,
email VARCHAR(45) UNIQUE NOT NULL

);


INSERT INTO user_account (firstname, lastname, username, password, email) VALUES
('John','Doe','admin','pass123','johndoe@example.com');

SELECT * FROM user_account;
