DROP TABLE IF EXISTS PRODUCT;

--DROP TABLE IF EXISTS PRICE;

CREATE TABLE PRODUCT (
  ID INT AUTO_INCREMENT PRIMARY KEY,
  NAME VARCHAR(200)
);

--CREATE TABLE PRICE (
--  ID INT AUTO_INCREMENT PRIMARY KEY,
--  PRODUCT_ID INT NOT NULL,
--  PRICE DOUBLE
--);

INSERT INTO PRODUCT VALUES (1, 'BED');
INSERT INTO PRODUCT VALUES (2, 'COUCH');
INSERT INTO PRODUCT VALUES (3, 'MOUSE');
INSERT INTO PRODUCT VALUES (4, 'MECHANICAL KEYBOARD');

--INSERT INTO PRICE VALUES (1, 1, 1200.5);
--INSERT INTO PRICE VALUES (2, 2, 2399.99);
--INSERT INTO PRICE VALUES (3, 3, 120);

