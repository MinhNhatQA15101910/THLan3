-- Bài 1 
CREATE DATABASE QLSV;
GO

USE QLSV;
GO

CREATE TABLE SinhVien(
	MaSV VARCHAR(8) NOT NULL,
	HoTen NVARCHAR(200) NOT NULL,
	Lop VARCHAR(8) NOT NULL,
	DiemTB FLOAT NOT NULL,

	CONSTRAINT PK_SinhVien PRIMARY KEY (MaSV) 
);
GO

INSERT INTO SinhVien(MaSV, HoTen, Lop, DiemTB) VALUES
('21520007', N'Võ Thanh Bình', 'KHMT2021', 7.8),
('21520013', N'Trương Bá Cường', 'KHMT2021', 6.5),
('21520032', N'Đôn Khánh Duy', 'TTNT2021', 9.2),
('21520035', N'Nguyễn Hoàng Khánh Duy', 'MMT2021', 8.1),
('21520053', N'Nguyễn Hữu Hiếu', 'KHMT2021', 5.9),
('21520093', N'Trần Hoàng Long', 'KTPM2021', 8.7),
('21520099', N'Trần Xuân Mạnh', 'HTTT2021', 6.8),
('21520102', N'Phạm Nhật Minh', 'MMT2021', 9.5),
('21520136', N'Huỳnh Bá Anh Quân', 'HTTT2021', 7.3),
('21520143', N'Vũ Quí San', 'TMDT2021', 5.7),
('21520167', N'Phan Huy Tiến', 'KHMT2021', 7.2),
('21520179', N'Nguyễn Thành Trung', 'KHMT2021', 6.6),
('21520182', N'Nguyễn Quang Trường', 'KTMT2021', 8.9),
('21520210', N'Nguyễn Minh Đức', 'KHMT2021', 6.4),
('21520251', N'Nguyễn Đình Tuấn Anh', 'TTNT2021', 9.7),
('21520262', N'Phù Hữu Đạt', 'KTMT2021', 9.1),
('21520266', N'Nguyễn Minh Đức', 'TTNT2021', 7.6),
('21520270', N'Hoàng Thị Ánh Dương', 'MMT2021', 5.3),
('21520302', N'Bế Hải Long', 'KHMT2021', 8.4),
('21520365', N'Trần Chí Thiện', 'KTMT2021', 6.9),
('21520378', N'Nguyễn Thị Thủy Tiên', 'KTPM2021', 8.2),
('21520381', N'Lê Nguyễn Công Toại', 'HTTT2021', 9.9),
('21520423', N'Huỳnh Ngọc Thiên Ân', 'KTMT2021', 7.5),
('21520438', N'Phan Quốc An', 'KHMT2021', 5.4),
('21520570', N'Lê Phan Thành Đạt', 'CNTT2021', 9.3),
('21520574', N'Nguyễn Quốc Đạt', 'MMT2021', 7.7),
('21520591', N'Trần Tiến Đạt', 'HTTT2021', 7.1),
('21520596', N'Nguyễn Thị Bích Diễm', 'MMT2021', 9.0),
('21520608', N'Võ Minh Đôn', 'KHMT2021', 8.0),
('21520626', N'Trương Hữu Minh Đức', 'KHMT2021', 6.2),
('21520641', N'Võ Thành Trung Dũng', 'CNTT2021', 8.5),
('21520648', N'Nguyễn Thị Thùy Dương', 'KTMT2021', 5.6),
('21520691', N'Nguyễn Thị Hà', 'TMDT2021', 6.7),
('21520711', N'Dương Thị Hồng Hạnh', 'CNTT2021', 9.8),
('21520714', N'Huỳnh Nhật Hào', 'CNTT2021', 9.4),
('21520723', N'Nguyễn Thị Thu Hiền', 'MMT2021', 5.8),
('21520747', N'Nguyễn Minh Hiếu', 'ATTT2021', 7.4),
('21520754', N'Trần Trung Hiếu', 'KHDL2021', 9.6),
('21520758', N'Võ Trung Hiếu', 'KHDL2021', 6.1),
('21520767', N'Võ Kiều Hoa', 'KHDL2021', 8.6),
('21520800', N'Nguyễn Hải Hưng', 'KTMT2021', 5.5),
('21520834', N'Lê Quốc Huy', 'ATTT2021', 7.9),
('21520863', N'Nguyễn Thị Thu Huyền', 'KTMT2021', 9.2),
('21520866', N'Hồ Công Huynh', 'KTMT2021', 6.9),
('21520879', N'Phan Tấn Nhất Khâm', 'KTMT2021', 7.6),
('21520882', N'Dương Lê Tường Khang', 'TMDT2021', 8.3),
('21520894', N'Nguyễn Thịnh Khang', 'ATTT2021', 5.3),
('21520938', N'Trần Nguyễn Anh Khoa', 'KHDL2021', 6.8),
('21520984', N'Đào Quang Linh', 'HTTT2021', 8.1),
('21521021', N'Đinh Phạm Thiên Long', 'TMDT2021', 7.4),
('21521046', N'Nguyễn Thiên Long', 'KHDL2021', 6.3),
('21521065', N'Nguyễn Tiến Luận', 'TMDT2021', 5.2),
('21521066', N'Nguyễn Xuân Luân', 'TTNT2021', 6.1);
GO

-- Bài 2
CREATE TABLE Lop (
    MaLop VARCHAR(8),
    TenLop NVARCHAR(200),
    CVHT NVARCHAR(200),

	CONSTRAINT PK_Lop PRIMARY KEY (MaLop),
	CONSTRAINT UQ_Lop_TenLop UNIQUE (TenLop),
	CONSTRAINT UQ_LOP_CVHT UNIQUE (CVHT)
);
GO

INSERT INTO Lop VALUES 
('KHMT2021', N'Khoa học máy tính 2021', N'Hoàng Ngọc Lương'),
('TTNT2021', N'Trí tuệ nhân tạo 2021', N'Phạm Nguyễn Trường An'),
('MMT2021', N'Mạng máy tính 2021', N'Thái Huy Tân'),
('KTPM2021', N'Kỹ thuật phần mềm 2021', N'Nguyễn Tấn Trần Minh Khang'),
('HTTT2021', N'Hệ thống thông tin 2021', N'Thái Bảo Trân'),
('TMDT2021', N'Thương mại điện tử 2021', N'Nguyễn Thanh Bình'),
('KTMT2021', N'Kỹ thuật máy tính 2021', N'Trần Ngọc Đức'),
('CNTT2021', N'Công nghệ thông tin 2021', N'Trần Hồng Nghi'),
('ATTT2021', N'An toàn thông tin 2021', N'Nguyễn Hồ Duy Trí'),
('KHDL2021', N'Khoa học dữ liệu 2021', N'Nguyễn Bích Vân');
GO

ALTER TABLE SinhVien
ADD CONSTRAINT FK_SinhVien_Lop
FOREIGN KEY (Lop) REFERENCES Lop (MaLop);
GO

-- Bài 6
-- Tạo các ràng buộc
ALTER TABLE SinhVien
ADD CONSTRAINT CHK_SinhVien_MaSV
CHECK (MaSV LIKE '[1-9][1-9]52[0-9][0-9][0-9][0-9]');
GO

ALTER TABLE SinhVien
ADD CONSTRAINT CHK_SinhVien_NamHoc
CHECK (LEFT(MaSV, 2) = RIGHT(Lop, 2));
GO

ALTER TABLE SinhVien
ADD CONSTRAINT CHK_SinhVien_DiemTB
CHECK (DiemTB >= 0 AND DiemTB <= 10);
GO

ALTER TABLE Lop
ADD CONSTRAINT CHK_Lop_NamHoc
CHECK (RIGHT(MaLop, 4) = RIGHT(TenLop, 4));
GO

-- Tạo các Stored Procedure
CREATE PROCEDURE AddClass
(@MaLop VARCHAR(8), @TenLop NVARCHAR(200), @CVHT NVARCHAR(200))
AS
BEGIN
	INSERT INTO Lop VALUES
	(@MaLop, @TenLop, @CVHT)
END
GO

CREATE PROCEDURE DeleteClass
(@MaLop VARCHAR(8))
AS
BEGIN
	DELETE FROM Lop
	WHERE MaLop = @MaLop
END
GO

CREATE PROCEDURE GetClassById
(@MaLop VARCHAR(8))
AS
BEGIN
	SELECT * 
	FROM Lop
	WHERE MaLop = @MaLop
END
GO

CREATE PROCEDURE GetClassByName
(@TenLop NVARCHAR(200))
AS
BEGIN
	SELECT * 
	FROM Lop
	WHERE TenLop = @TenLop
END
GO

CREATE PROCEDURE GetAllClasses
AS
BEGIN
	SELECT * 
	FROM Lop
END
GO

CREATE PROCEDURE UpdateClass
(@TenLop NVARCHAR(200), @CVHT NVARCHAR(200), @MaLop VARCHAR(8))
AS
BEGIN
	UPDATE Lop
	SET TenLop = @TenLop, CVHT = @CVHT
	WHERE MaLop = @MaLop
END
GO

CREATE PROCEDURE AddStudent
(@MaSV VARCHAR(8), @HoTen NVARCHAR(200), @Lop NVARCHAR(200), @DiemTB FLOAT)
AS
BEGIN
	INSERT INTO SinhVien VALUES
	(@MaSV, @HoTen, @Lop, @DiemTB)
END
GO

CREATE PROCEDURE DeleteStudent
(@MaSV VARCHAR(8))
AS
BEGIN
	DELETE FROM SinhVien
	WHERE MaSV = @MaSV
END
GO

CREATE PROCEDURE GetAllStudentsSortByAvgScoreAsc
AS
BEGIN
	SELECT * 
	FROM SinhVien
	ORDER BY DiemTB ASC
END
GO

CREATE PROCEDURE GetAllStudentsSortByAvgScoreDesc
AS
BEGIN
	SELECT * 
	FROM SinhVien
	ORDER BY DiemTB DESC
END
GO

CREATE PROCEDURE GetStudentsByClassId
(@MaLop VARCHAR(8))
AS
BEGIN
	SELECT * 
	FROM SinhVien
	WHERE Lop = @MaLop
END
GO

CREATE PROCEDURE GetStudentsFilteredByAvgScore
(@DiemTBMinValue FLOAT, @DiemTBMaxValue FLOAT)
AS
BEGIN
	SELECT * 
	FROM SinhVien
	WHERE DiemTB >= @DiemTBMinValue 
	AND DiemTB <= @DiemTBMaxValue
END
GO

CREATE PROCEDURE GetStudentsFilteredByClass
(@LopFilter NVARCHAR(20))
AS
BEGIN
	SELECT * 
	FROM SinhVien
	WHERE Lop LIKE '%' + @LopFilter + '%'
END
GO

CREATE PROCEDURE GetStudentsFilteredByFullName
(@HoTenFilter NVARCHAR(200))
AS
BEGIN
	SELECT * 
	FROM SinhVien
	WHERE HoTen LIKE '%' + @HoTenFilter + '%'
END
GO

CREATE PROCEDURE GetStudentsFilteredById
(@MaSVFilter NVARCHAR(20))
AS
BEGIN
	SELECT * 
	FROM SinhVien
	WHERE MaSV LIKE '%' + @MaSVFilter + '%'
END
GO

CREATE PROCEDURE GetStudentById
(@MaSV NVARCHAR(8))
AS
BEGIN
	SELECT * 
	FROM SinhVien
	WHERE MaSV = @MaSV
END
GO

CREATE PROCEDURE UpdateStudent
(@HoTen NVARCHAR(200), @Lop NVARCHAR(8), @DiemTB FLOAT, @MaSV VARCHAR(8))
AS
BEGIN
	UPDATE SinhVien
	SET HoTen = @HoTen, Lop = @Lop, DiemTB = @DiemTB
	WHERE MaSV = @MaSV
END
GO

SELECT 
    CONSTRAINT_NAME, 
    TABLE_NAME, 
    CONSTRAINT_TYPE
FROM 
    INFORMATION_SCHEMA.TABLE_CONSTRAINTS
WHERE 
    CONSTRAINT_TYPE IN ('PRIMARY KEY', 'FOREIGN KEY', 'UNIQUE', 'CHECK');
