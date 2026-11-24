CREATE DATABASE IF NOT EXISTS baitap_session17
USE baitap_session17;

CREATE TABLE IF NOT EXISTS students (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL
);

DELIMITER //

CREATE PROCEDURE add_students(
    IN in_name VARCHAR(100),
    IN in_age INT
)
BEGIN
    INSERT INTO students (name, age)
    VALUES (in_name, in_age);
END//


CREATE PROCEDURE update_student(
    IN in_id INT,
    IN in_name VARCHAR(100),
    IN in_age INT
)
BEGIN
    UPDATE students
    SET name = in_name,
        age = in_age
    WHERE id = in_id;
END//

CREATE PROCEDURE delete_student(
    IN in_id INT
)
BEGIN
    DELETE FROM students
    WHERE id = in_id;
END//

DELIMITER ;