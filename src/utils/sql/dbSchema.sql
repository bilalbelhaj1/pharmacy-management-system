-- ===============================
-- Database
-- ===============================
CREATE DATABASE IF NOT EXISTS pharmacyDB;
USE pharmacyDB;

-- ===============================
-- Users Table
-- ===============================
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(30) NOT NULL UNIQUE,
    password VARCHAR(250) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ===============================
-- Medicine Table
-- ===============================
CREATE TABLE medicine (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(150) NOT NULL,
    description TEXT NOT NULL,
    form VARCHAR(50),
    purchase_price DECIMAL(10,2) NOT NULL,
    sell_price DECIMAL(10,2) NOT NULL,
    stock INT DEFAULT 0 CHECK (stock >= 0),
    expiration_date DATE NOT NULL
);

-- ===============================
-- Supplier Table
-- ===============================
CREATE TABLE supplier (
    id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    phone_number VARCHAR(20) NOT NULL UNIQUE
);

-- ===============================
-- Sale Table
-- ===============================
CREATE TABLE sale (
    id INT PRIMARY KEY AUTO_INCREMENT,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(10,2) NOT NULL,
    status ENUM('PENDING', 'PAID', 'CANCELLED') DEFAULT 'PAID'
);

-- ===============================
-- Sale Item Table
-- ===============================
CREATE TABLE sale_item (
    id INT PRIMARY KEY AUTO_INCREMENT,
    sale_id INT NOT NULL,
    medicine_id INT NOT NULL,
    quantity INT NOT NULL CHECK (quantity > 0),
    price DECIMAL(10,2) NOT NULL,

    FOREIGN KEY (sale_id) REFERENCES sale(id) ON DELETE CASCADE,
    FOREIGN KEY (medicine_id) REFERENCES medicine(id),

    UNIQUE (sale_id, medicine_id)
);

-- ===============================
-- Purchase Table
-- ===============================
CREATE TABLE purchase (
    id INT PRIMARY KEY AUTO_INCREMENT,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    supplier_id INT NOT NULL,
    total DECIMAL(10,2) NOT NULL,

    FOREIGN KEY (supplier_id) REFERENCES supplier(id)
);

-- ===============================
-- Purchase Item Table
-- ===============================
CREATE TABLE purchase_item (
    id INT PRIMARY KEY AUTO_INCREMENT,
    purchase_id INT NOT NULL,
    medicine_id INT NOT NULL,
    quantity INT NOT NULL CHECK (quantity > 0),
    price DECIMAL(10,2) NOT NULL,

    FOREIGN KEY (purchase_id) REFERENCES purchase(id) ON DELETE CASCADE,
    FOREIGN KEY (medicine_id) REFERENCES medicine(id),

    UNIQUE (purchase_id, medicine_id)
);
