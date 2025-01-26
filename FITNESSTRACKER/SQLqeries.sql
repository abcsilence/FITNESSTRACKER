
-- Create the database
CREATE DATABASE fitness_tracker;

-- Use the database
USE fitness_tracker;

-- Create the Users table
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    fullname VARCHAR(255),
    username VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    Is_trainer BOOLEAN
);
select * from users;
-- Create the trainer table
CREATE TABLE trainer (
    trainer_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT UNIQUE, -- Ensures a user can only have one trainer profile
    specialization VARCHAR(255),
    experience INT,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE SET NULL
);

CREATE TABLE user_trainer (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    trainer_id INT,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE SET NULL,
    FOREIGN KEY (trainer_id) REFERENCES trainer(trainer_id) ON DELETE SET NULL
);


CREATE TABLE routine(
user_id int,
day varchar(255),
workout varchar(255),
calories_burned varchar(255),
 FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE SET NULL
 );

-- Create trigger to auto-insert trainers
DELIMITER //
CREATE TRIGGER after_user_insert
AFTER INSERT ON users
FOR EACH ROW
BEGIN
    IF NEW.Is_trainer = true THEN
        INSERT INTO trainer (user_id, specialization, experience)
        VALUES (NEW.user_id, 'General Fitness', 0);
    END IF;
END //
DELIMITER ; 