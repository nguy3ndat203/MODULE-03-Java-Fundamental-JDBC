CREATE DATABASE IF NOT EXISTS db_library_management;
USE db_library_management;

CREATE TABLE IF NOT EXISTS borrow_cards (
    card_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    book_title VARCHAR(150) NOT NULL,
    borrower_name VARCHAR(100) NOT NULL,
    borrow_date TIMESTAMP NOT NULL,
    return_deadline TIMESTAMP NOT NULL,
    quantity INT NOT NULL,
    status VARCHAR(30) NOT NULL,
    CONSTRAINT chk_return_after_borrow CHECK (return_deadline > borrow_date),
    CONSTRAINT chk_quantity_positive CHECK (quantity > 0)
);

DELIMITER //
-- 1. Lấy tất cả các phiếu mượn
CREATE PROCEDURE get_all_borrow_cards()
BEGIN
    SELECT card_id, book_title, borrower_name, borrow_date, return_deadline, quantity, status
    FROM borrow_cards
    ORDER BY card_id ASC;
END//

-- 2. Thêm mới một phiếu mượn
CREATE PROCEDURE add_borrow_card(
    IN in_book_title VARCHAR(150),
    IN in_borrower_name VARCHAR(100),
    IN in_borrow_date TIMESTAMP,
    IN in_return_deadline TIMESTAMP,
    IN in_quantity INT,
    IN in_status VARCHAR(30)
)
BEGIN
    INSERT INTO borrow_cards (book_title, borrower_name, borrow_date, return_deadline, quantity, status)
    VALUES (in_book_title, in_borrower_name, in_borrow_date, in_return_deadline, in_quantity, in_status);
END//


-- 3. Cập nhật thông tin phiếu mượn theo card_id
CREATE PROCEDURE update_borrow_card(
    IN in_card_id BIGINT UNSIGNED,
    IN in_book_title VARCHAR(150),
    IN in_borrower_name VARCHAR(100),
    IN in_borrow_date TIMESTAMP,
    IN in_return_deadline TIMESTAMP,
    IN in_quantity INT,
    IN in_status VARCHAR(30)
)
BEGIN
    UPDATE borrow_cards
    SET book_title = in_book_title,
        borrower_name = in_borrower_name,
        borrow_date = in_borrow_date,
        return_deadline = in_return_deadline,
        quantity = in_quantity,
        status = in_status
    WHERE card_id = in_card_id;
END//

-- 4. Xóa phiếu mượn theo card_id
CREATE PROCEDURE delete_borrow_card(
    IN in_card_id BIGINT UNSIGNED
)
BEGIN
    DELETE FROM borrow_cards
    WHERE card_id = in_card_id;
END//


-- 5. Lấy danh sách phiếu mượn theo tên độc giả borrower_name
CREATE PROCEDURE get_borrow_cards_by_borrower(
    IN in_borrower_name VARCHAR(100)
)
BEGIN
    SELECT card_id, book_title, borrower_name, borrow_date, return_deadline, quantity, status
    FROM borrow_cards
    WHERE LOWER(borrower_name) LIKE LOWER(CONCAT('%', in_borrower_name, '%'))
    ORDER BY card_id ASC;
END//

-- 6. Tìm kiếm phiếu mượn theo tên sách book_title
CREATE PROCEDURE search_borrow_cards_by_book_title(
    IN in_book_title VARCHAR(150)
)
BEGIN
    SELECT card_id, book_title, borrower_name, borrow_date, return_deadline, quantity, status
    FROM borrow_cards
    WHERE LOWER(book_title) LIKE LOWER(CONCAT('%', in_book_title, '%'))
    ORDER BY card_id ASC;
END//

DELIMITER ;




CALL add_borrow_card(
    'System Design',
    'Nguyễn Tiến Đạt',
    '2026-11-25 09:00:00',
    '2026-12-09 09:00:00',
    2,
    'Borrowing'
);
CALL add_borrow_card(
    'Clean Code',
    'Nguyễn Văn An',
    '2026-11-01 09:00:00',
    '2026-11-15 09:00:00',
    1,
    'Returned'
);

CALL add_borrow_card(
    'Mastery',
    'Trần Thị A',
    '2026-11-10 14:30:00',
    '2026-11-24 14:30:00',
    2,
    'Overdue'
);

CALL add_borrow_card(
    'Design Patterns',
    'Lê Minh Mẫn',
    '2026-11-18 10:15:00',
    '2026-12-02 10:15:00',
    1,
    'Borrowing'
);

CALL add_borrow_card(
    'Database Internals',
    'Phạm Thắng',
    '2026-11-20 08:45:00',
    '2026-12-05 08:45:00',
    3,
    'Borrowing'
);


CALL get_all_borrow_cards();