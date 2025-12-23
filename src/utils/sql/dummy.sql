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

INSERT INTO medicine VALUES
        (18, "medicine1", "this a test to test the functionalities of the app", "pil", 123.12, 1223.2, 122, "2027-01-01"),
        (19, "medicine1", "this a test to test the functionalities of the app", "pil", 123.12, 1223.2, 122, "2027-01-01"),
        (20, "medicine1", "this a test to test the functionalities of the app", "pil", 123.12, 1223.2, 122, "2027-01-01"),
        (21, "medicine1", "this a test to test the functionalities of the app", "pil", 123.12, 1223.2, 122, "2027-01-01"),
        (22, "medicine1", "this a test to test the functionalities of the app", "pil", 123.12, 1223.2, 122, "2027-01-01"),
        (23, "medicine1", "this a test to test the functionalities of the app", "pil", 123.12, 1223.2, 122, "2027-01-01"),
        (24, "medicine1", "this a test to test the functionalities of the app", "pil", 123.12, 1223.2, 122, "2027-01-01"),
        (25, "medicine1", "this a test to test the functionalities of the app", "pil", 123.12, 1223.2, 122, "2027-01-01"),
        (26, "medicine1", "this a test to test the functionalities of the app", "pil", 123.12, 1223.2, 122, "2027-01-01"),
        (27, "medicine1", "this a test to test the functionalities of the app", "pil", 123.12, 1223.2, 122, "2027-01-01"),
        (28, "medicine1", "this a test to test the functionalities of the app", "pil", 123.12, 1223.2, 122, "2027-01-01"),
        (29, "medicine1", "this a test to test the functionalities of the app", "pil", 123.12, 1223.2, 122, "2027-01-01"),
        (30, "medicine1", "this a test to test the functionalities of the app", "pil", 123.12, 1223.2, 122, "2027-01-01")
;