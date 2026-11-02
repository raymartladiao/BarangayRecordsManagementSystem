CREATE DATABASE IF NOT EXISTS barangay_records;
USE barangay_records;

CREATE TABLE IF NOT EXISTS residents (
    resident_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(80) NOT NULL,
    last_name VARCHAR(80) NOT NULL,
    sex VARCHAR(20) NOT NULL,
    birth_date DATE NOT NULL,
    address VARCHAR(255) NOT NULL,
    contact_no VARCHAR(30),
    civil_status VARCHAR(30)
);

-- Optional sample record
INSERT INTO residents
(first_name, last_name, sex, birth_date, address, contact_no, civil_status)
VALUES
('Juan', 'Dela Cruz', 'Male', '2000-01-15',
 'Barangay Poblacion', '09123456789', 'Single');
