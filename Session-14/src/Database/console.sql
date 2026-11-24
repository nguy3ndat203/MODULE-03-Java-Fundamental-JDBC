CREATE DATABASE IF NOT EXISTS baitap_session17
USE baitap_session17;

CREATE TABLE IF NOT EXISTS students (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL
);

create table accounts (
    id int auto_increment primary key ,
    balance decimal not null
);

CREATE TABLE orders (
    id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    total_amount DECIMAL ,
    product_id int not null ,
    quantity int
    );

CREATE TABLE products (
  id INT PRIMARY KEY AUTO_INCREMENT,
  product_name varchar(100) not null ,
  stock INT
);

CREATE TABLE employees (
   id INT PRIMARY KEY AUTO_INCREMENT,
   name VARCHAR(255) NOT NULL
);

CREATE TABLE projects (
  id INT PRIMARY KEY AUTO_INCREMENT,
  project_name VARCHAR(255) NOT NULL
);

CREATE TABLE employee_projects (
   employee_id INT,
   project_id INT,
   PRIMARY KEY (employee_id, project_id),
   FOREIGN KEY (employee_id) REFERENCES employees(id),
   FOREIGN KEY (project_id) REFERENCES projects(id)
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


create procedure transfer_funds(id_from int , id_to int , amount decimal ,out result varchar(255))
begin
    declare count_id_from int ;
    declare count_id_to int ;
    declare balance_from decimal ;
    declare balance_to decimal ;
    select count(id) into count_id_from from accounts where id = id_from ;

    select balance into balance_from from accounts where id = id_from ;


    if count_id_from = 0 then set result = 'không tìm thấy người gửi !' ;
    elseif count_id_to = 0 then set result = 'không tìm thấy người nhận !' ;
    elseif balance_from - amount < 0 then set result = 'Người gửi không đủ tiền !' ;
    else
        update accounts set balance = balance - amount where id = id_from ;
        update accounts set balance = balance + amount where id = id_to ;
        set result = 'Chuyển tiền thành công !' ;
    end if ;
end //



CREATE PROCEDURE place_order(
    IN in_customerId INT,
    IN in_totalAmount DECIMAL,
    IN in_productId INT,
    IN in_quantity INT,
    OUT result VARCHAR(255)
)
BEGIN
    DECLARE available_stock INT;

    SELECT stock INTO available_stock FROM products WHERE id = in_productId;

    IF available_stock IS NULL THEN
        SET result = 'Sản phẩm không tồn tại!';
    elseif available_stock < in_quantity THEN
        SET result = 'Không đủ hàng trong kho!';
    else
    -- Thực hiện đặt hàng
    INSERT INTO orders ( customer_id ,total_amount ,product_id ,quantity) VALUES (in_customerId, in_totalAmount , in_productId,in_quantity);
    -- Cập nhật số lượng hàng tồn kho
    UPDATE products SET stock = stock - in_quantity WHERE id = in_productId;
    SET result = 'Đặt hàng thành công!';
    end if ;
END //


CREATE PROCEDURE assign_employee_to_project(
    IN emp_id INT,
    IN proj_id INT,
    OUT result VARCHAR(255)
)
BEGIN
    DECLARE employee_exists INT;
    DECLARE project_exists INT;

    SELECT COUNT(id) INTO employee_exists FROM employees WHERE id = emp_id;

    SELECT COUNT(id) INTO project_exists FROM projects WHERE id = proj_id;

    if employee_exists = 0 then set result = 'Nhân viên không tồn tại ! ' ;
    elseif project_exists = 0 then set result = 'Dự án không tồn tại ! ' ;
    else
    -- Gán nhân viên vào dự án
    INSERT INTO employee_projects (employee_id, project_id) VALUES (emp_id, proj_id);
    SET result = 'Gán nhân viên vào dự án thành công!';
    end if ;
END //

DELIMITER ;