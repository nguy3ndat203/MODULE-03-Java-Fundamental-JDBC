CREATE DATABASE IF NOT EXISTS BaiTap05
USE BaiTap05;

CREATE TABLE IF NOT EXISTS movies (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    director VARCHAR(100) NOT NULL,
    release_year INT NOT NULL
);

DELIMITER //

-- 1. Thêm phim
CREATE PROCEDURE add_movie(
    IN in_title VARCHAR(150),
    IN in_director VARCHAR(100),
    IN in_release_year INT
)
BEGIN
    INSERT INTO movies (title, director, release_year)
    VALUES (in_title, in_director, in_release_year);
END//

-- 2. Liệt kê tất cả phim
CREATE PROCEDURE list_movies()
BEGIN
    SELECT id, title, director, release_year
    FROM movies
    ORDER BY id ASC;
END//

-- 3. Sửa phim theo ID
CREATE PROCEDURE update_movie(
    IN in_id INT,
    IN in_title VARCHAR(150),
    IN in_director VARCHAR(100),
    IN in_release_year INT
)
BEGIN
    UPDATE movies
    SET title = in_title,
        director = in_director,
        release_year = in_release_year
    WHERE id = in_id;
END//

-- 4. Xóa phim theo ID
CREATE PROCEDURE delete_movie(
    IN in_id INT
)
BEGIN
    DELETE FROM movies
    WHERE id = in_id;
END//

DELIMITER ;