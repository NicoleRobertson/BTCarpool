CREATE TABLE EMPLOYEE(
ID BIGINT AUTO_INCREMENT PRIMARY KEY,
FIRSTNAME VARCHAR(64),
LASTNAME VARCHAR(64),
HASCAR BIT,
ADDRESS_ID INTEGER,
USERNAME VARCHAR(64)
);

CREATE TABLE ADDRESS(
ID BIGINT AUTO_INCREMENT PRIMARY KEY,
STREETNAME VARCHAR,
STREETNUMBER INTEGER,
ZIPCODE INTEGER,
CITY VARCHAR
);

CREATE TABLE OFFICE(
ID BIGINT AUTO_INCREMENT PRIMARY KEY,
NAME VARCHAR,
ADDRESS_ID INTEGER
);

CREATE TABLE VEHICLE(
ID BIGINT AUTO_INCREMENT PRIMARY KEY,
EMPLOYEE_ID INTEGER,
NUMOFSEATS INTEGER,
COSTPERMILE DOUBLE,
LICENSEPLATE VARCHAR,
MODEL VARCHAR
);

CREATE TABLE CARRIDE(
ID BIGINT AUTO_INCREMENT PRIMARY KEY,
VEHICLE_ID INTEGER,
RIDEDATE DATE,
EMPLOYEE_ID INTEGER,
OFFICE_ID INTEGER,
AVAILABLESEATS INTEGER
);

CREATE TABLE BOOKING(
ID BIGINT AUTO_INCREMENT PRIMARY KEY,
EMPLOYEE_ID INTEGER,
CARRIDE_ID INTEGER,
ACTIVE BIT
);




ALTER TABLE EMPLOYEE ADD FOREIGN KEY (ADDRESS_ID) REFERENCES ADDRESS(ID);
ALTER TABLE OFFICE ADD FOREIGN KEY (ADDRESS_ID) REFERENCES ADDRESS(ID);
ALTER TABLE VEHICLE ADD FOREIGN KEY (EMPLOYEE_ID) REFERENCES EMPLOYEE(ID);
ALTER TABLE CARRIDE ADD FOREIGN KEY (EMPLOYEE_ID) REFERENCES EMPLOYEE(ID);
ALTER TABLE CARRIDE ADD FOREIGN KEY (VEHICLE_ID) REFERENCES VEHICLE(ID);
ALTER TABLE CARRIDE ADD FOREIGN KEY (OFFICE_ID) REFERENCES OFFICE(ID);
ALTER TABLE BOOKING ADD FOREIGN KEY (CARRIDE_ID) REFERENCES CARRIDE(ID);
ALTER TABLE BOOKING ADD FOREIGN KEY (EMPLOYEE_ID) REFERENCES EMPLOYEE(ID);