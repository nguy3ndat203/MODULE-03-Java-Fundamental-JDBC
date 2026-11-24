USE BaiTap;

CREATE TABLE IF NOT EXISTS Students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    date_of_birth DATE NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE
);

DELIMITER //

-- 1. Lấy tất cả sinh viên
CREATE PROCEDURE get_all_students()
BEGIN
    SELECT student_id, full_name, date_of_birth, email
    FROM Students
    ORDER BY student_id ASC;
END//

-- 2. Thêm mới sinh viên
CREATE PROCEDURE add_student(
    IN in_full_name VARCHAR(100),
    IN in_date_of_birth DATE,
    IN in_email VARCHAR(100)
)
BEGIN
    INSERT INTO Students (full_name, date_of_birth, email)
    VALUES (in_full_name, in_date_of_birth, in_email);
END//

-- 3. Cập nhật sinh viên theo id
CREATE PROCEDURE update_student(
    IN in_id INT,
    IN in_full_name VARCHAR(100),
    IN in_date_of_birth DATE,
    IN in_email VARCHAR(100)
)
BEGIN
    UPDATE Students
    SET full_name = in_full_name,
        date_of_birth = in_date_of_birth,
        email = in_email
    WHERE student_id = in_id;
END//

-- 4. Tìm sinh viên theo id
CREATE PROCEDURE find_student_by_id(
    IN in_id INT
)
BEGIN
    SELECT student_id, full_name, date_of_birth, email
    FROM Students
    WHERE student_id = in_id;
END//

-- 5. Xóa sinh viên theo id
CREATE PROCEDURE delete_student(
    IN in_id INT
)
BEGIN
    DELETE FROM Students
    WHERE student_id = in_id;
END//

-- 6. Tìm kiếm sinh viên theo tên
CREATE PROCEDURE search_student_by_name(
    IN in_name VARCHAR(100)
)
BEGIN
    SELECT student_id, full_name, date_of_birth, email
    FROM Students
    WHERE full_name LIKE CONCAT('%', in_name, '%')
    ORDER BY student_id ASC;
END//

DELIMITER ;
