DROP DATABASE IF EXISTS MVC;
CREATE DATABASE IF NOT EXISTS MVC;
SHOW DATABASES ;
USE MVC;
#----------------------------------------

DROP TABLE IF EXISTS Register;
CREATE TABLE IF NOT EXISTS Register(
    fullName VARCHAR (15),
    address VARCHAR (15),
    email VARCHAR(50),
    userType VARCHAR (15),
    telephone VARCHAR(15),
    userName VARCHAR(15),
    password VARCHAR(15)
);
SHOW TABLES ;
DESCRIBE Register;
#----------------------------------------
DROP TABLE IF EXISTS Customer;
CREATE TABLE IF NOT EXISTS Customer(
    custID VARCHAR(6) PRIMARY KEY,
    custName VARCHAR(30) NOT NULL DEFAULT 'Unknown',
    custAddress VARCHAR (30),
    postalCode VARCHAR (9),
    nationalId VARCHAR (15),
    city VARCHAR (20),
    province VARCHAR (20),
    custTitle VARCHAR (5)
    );
SHOW TABLES ;
DESCRIBE Customer;
#---------------------
DROP TABLE IF EXISTS Item;
CREATE TABLE IF NOT EXISTS Item(
    itemCode VARCHAR(6) PRIMARY KEY,
    packSize VARCHAR (20),
    unitPrice DECIMAL(6,2) DEFAULT 0.00,
    qtyOnHand INT(5) DEFAULT 0,
    description VARCHAR (50),
    discount DECIMAL (6,2)
);

SHOW TABLES ;
DESCRIBE Item;
#------------------------
DROP TABLE IF EXISTS `Order`;
CREATE TABLE IF NOT EXISTS `Order`(
    orderID VARCHAR(6),
    orderDate DATE,
    cID VARCHAR (6),
    cost DOUBLE,
    CONSTRAINT PRIMARY KEY (orderID),
    CONSTRAINT FOREIGN KEY (cID) REFERENCES Customer(custID) ON DELETE CASCADE ON UPDATE CASCADE
);
SHOW TABLES ;
DESCRIBE `Order`;
#-----------------------
#==================================================
DROP TABLE IF EXISTS `SavedOrder`;
CREATE TABLE IF NOT EXISTS `SavedOrder`(
    oId VARCHAR (6),
    custNIC VARCHAR (15),
    itemCode VARCHAR (6),
    itemDescription VARCHAR (50),
    quantity INT (11),
    discount DECIMAL (20,2),
    total DECIMAL (20,2)
);
SHOW TABLES;
DESCRIBE `SavedOrder`;
#=====================================================================
DROP TABLE IF EXISTS `TempOrderID`;
CREATE TABLE IF NOT EXISTS `TempOrderID`(
    oId VARCHAR (6)

);
SHOW TABLES;
DESCRIBE `TempOrderID`;

