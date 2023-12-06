BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;

BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, cars, patrons, slips, parking_lot;

CREATE TABLE users (
   user_id SERIAL,
   username varchar(50) NOT NULL UNIQUE,
   password_hash varchar(200) NOT NULL,
   role varchar(50) NOT NULL,
   CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE patrons (
    patron_id SERIAL,
    user_id INTEGER,
    name varchar(50),
    phone_number varchar(10),
--    vin_number varchar(17)
    CONSTRAINT PK_patron PRIMARY KEY (patron_id),
    CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE cars (
    make varchar(50),
    model varchar(50),
    color varchar(50),
    license_plate varchar(50),
    vin_number varchar(17) NOT NULL UNIQUE,
    patron_id INTEGER NOT NULL,
    CONSTRAINT PK_car PRIMARY KEY (vin_number),
    CONSTRAINT FK_patron_id FOREIGN KEY (patron_id) REFERENCES patrons(patron_id)
);

CREATE TABLE slips (
    slip_number SERIAL,
    patron_id INTEGER,
    vin_number varchar(17),
    arrival_time timestamp NOT NULL,
    departure_time timestamp,
    hourly_price decimal(5,2) DEFAULT 5,
    total decimal(5,2),
    CONSTRAINT PK_slip PRIMARY KEY (slip_number),
    CONSTRAINT FK_patron_id FOREIGN KEY (patron_id) REFERENCES patrons(patron_id),
    CONSTRAINT FK_vin_number FOREIGN KEY (vin_number) REFERENCES cars(vin_number)
);

CREATE TABLE parking_lot (
    spot_number SERIAL,
    vin_number varchar(17),
    is_available boolean NOT NULL,
    CONSTRAINT PK_parking_lot PRIMARY KEY (spot_number),
    CONSTRAINT FK_vin_number FOREIGN KEY (vin_number) REFERENCES cars(vin_number)
);

COMMIT TRANSACTION;














