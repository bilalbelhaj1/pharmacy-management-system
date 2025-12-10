-- Create DataBase
CREATE DATABASE IF NOT EXISTS pharmacyDB;

-- Use the created Database
USE pharmacyDB;

-- Create tables

-- users Table
CREATE TABLE IF NOT EXISTS user (
   id INT PRIMARY KEY AUTO_INCREMENT,
   username VARCHAR(30) NOT NULL UNIQUE,
   password VARCHAR(250) NOT NULL,
   created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Create medicine Table
CREATE TABLE  IF NOT EXISTS medicine (
   id INT PRIMARY KEY AUTO_INCREMENT,
   name VARCHAR(150) NOT NULL,
   description TEXT NOT NULL,
   form VARCHAR(50),
   buy_price DECIMAL(10,2) NOT NULL,
   sell_price DECIMAL(10,2) NOT NULL,
   stock INT DEFAULT 0,
   expiration_date DATE
);

-- Suppliers table
CREATE TABLE IF NOT EXISTS supplier (
   id INT PRIMARY KEY AUTO_INCREMENT,
   first_name VARCHAR(30) NOT NULL,
   last_name VARCHAR(30) NOT NULL,
   email VARCHAR(50) NOT NULL UNIQUE,
   phone_number VARCHAR(10) NOT NULL UNIQUE
);


-- Sale Table
CREATE TABLE IF NOT EXISTS sale (
   id INT PRIMARY KEY AUTO_INCREMENT,
   date DATE,
   total DECIMAL(10, 2),
   status VARCHAR(30),
);

-- Sale item
CREATE TABLE IF NOT EXISTS sale_item (
   id INT PRIMARY KEY AUTO_INCREMENT,
   sale_id INT NOT NULL,
   medicine_id INT NOT NULL,
   quantity INT NOT NULL,
   price INT NULL,
   FOREIGN KEY (medicine_id) REFERENCES medicine (id),
   FOREIGN KEY (sale_id) REFERENCES sale(id)
);


-- Purchase Table
CREATE TABLE IF NOT EXISTS purchase (
   id INT PRIMARY KEY AUTO_INCREMENT,
   date DATE,
   supplier_id INT NOT NULL,
   total DECIMAL(10, 2),
   FOREIGN KEY (supplier_id) REFERENCES supplier(id)
);


-- Purchase Item Table
CREATE TABLE IF NOT EXISTS purchase_item (
   id INT PRIMARY KEY AUTO_INCREMENT,
   medicine_id INT NOT NULL,
   purchase_id INT NOT NULL,
   quantity INT NOT NULL,
   price INT NOT NULL,
   FOREIGN KEY (medicine_id) REFERENCES medicine(id),
   FOREIGN KEY (purchase_id) REFERENCES purchase(id)
);
