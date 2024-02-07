DROP DATABASE IF EXISTS sal;
CREATE DATABASE sal;

USE sal;

CREATE TABLE User (
	user_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
	username VARCHAR(50),
	password VARCHAR(50),
	email VARCHAR(50) UNIQUE,
	balance DOUBLE
);

CREATE TABLE Favorites (
	favorite_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
	user_id INT,
	event_id VARCHAR(50),
    FOREIGN KEY fk(user_id) REFERENCES User(user_id)
);

CREATE TABLE Wallet (
	ticket_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
	user_id INT,
	event_id VARCHAR(50),
    num_tickets int,
    purchase_price DOUBLE,
    FOREIGN KEY fk(user_id) REFERENCES User(user_id)
);





            

