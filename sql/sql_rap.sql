CREATE DATABASE rap;
GO
USE rap;
GO

-- Khách hàng
CREATE TABLE khach_hang (
    maKH INT IDENTITY(1,1) PRIMARY KEY,
    tenKH NVARCHAR(100) NOT NULL,
    SDT NVARCHAR(15)
);

-- Nhân viên
CREATE TABLE nhan_vien (
    maNhanVien INT IDENTITY(1,1) PRIMARY KEY,
    tenNhanVien NVARCHAR(100) NOT NULL,
    SDT NVARCHAR(15),
    email NVARCHAR(100) UNIQUE,
    taiKhoan NVARCHAR(50) UNIQUE NOT NULL,
    matKhau NVARCHAR(255) NOT NULL,
    img NVARCHAR(255),
);

-- Thể loại
CREATE TABLE the_loai (
    maTheLoai INT IDENTITY(1,1) PRIMARY KEY,
    tenTheLoai NVARCHAR(100),
    moTa NVARCHAR(MAX)
);

-- Phim
CREATE TABLE phim (
    maPhim INT IDENTITY(1,1) PRIMARY KEY,
    tenPhim NVARCHAR(100) NOT NULL,
    moTa NVARCHAR(MAX),
    doTuoi NVARCHAR(10),
    quocGia NVARCHAR(100),
    thoiLuong INT,
    daoDien NVARCHAR(100),
    ngayKhoiChieu NVARCHAR(10),
    img NVARCHAR(255),
    maTheLoai INT,
    FOREIGN KEY (maTheLoai) REFERENCES the_loai(maTheLoai)
);

-- Phòng chiếu
CREATE TABLE phong_chieu (
    maPhongChieu INT IDENTITY(1,1) PRIMARY KEY,
    tenPhong NVARCHAR(50),
    soLuongGhe INT NOT NULL
);

-- Loại ghế
CREATE TABLE loai_ghe(
		maLoaiGhe INT IDENTITY(1,1) PRIMARY KEY,
    tenLoaiGhe NVARCHAR(50),
    moTa NVARCHAR(MAX),
    phuThu DECIMAL(12,2),
);

-- Ghế
CREATE TABLE ghe (
    maGhe INT IDENTITY(1,1) PRIMARY KEY,
    tenGhe NVARCHAR(10),
    maLoaiGhe INT,
    maPhongChieu INT,
    FOREIGN KEY (maLoaiGhe) REFERENCES loai_ghe(maLoaiGhe),
    FOREIGN KEY (maPhongChieu) REFERENCES phong_chieu(maPhongChieu)
);

-- Suất chiếu
CREATE TABLE suat_chieu (
    maSuatChieu INT IDENTITY(1,1) PRIMARY KEY,
    maPhim INT,
    maPhongChieu INT,
    ngayChieu DATE,
    gioChieu TIME,
    giaVeCoBan DECIMAL(10,2),
    FOREIGN KEY (maPhim) REFERENCES phim(maPhim),
    FOREIGN KEY (maPhongChieu) REFERENCES phong_chieu(maPhongChieu)
);

-- Thực phẩm
-- CREATE TABLE thuc_pham (
--     maThucPham INT IDENTITY(1,1) PRIMARY KEY,
--     tenThucPham NVARCHAR(100) NOT NULL,
--     img VARCHAR(255),
--     loaiThucPham VARCHAR(10) CHECK (loaiThucPham IN ('BAP', 'NUOC', 'COMBO', 'KHAC')) DEFAULT 'KHAC',
--     gia DECIMAL(10,2) NOT NULL,
--     trangThai BIT,
--     giaGiam DECIMAL(10,2) DEFAULT 0
-- );

-- Phương thức thanh toán
CREATE TABLE phuong_thuc_thanh_toan (
    maPTTT INT IDENTITY(1,1) PRIMARY KEY,
    tenPTTT NVARCHAR(50) NOT NULL, -- 'Tiền mặt', 'Thẻ', 'VNPay', 'MoMo', 'ZaloPay'
    moTa NVARCHAR(MAX),
    phiGiaoDich DECIMAL(5,2) DEFAULT 0, -- Phí giao dịch (%)
);

-- Bảng khuyến mãi/voucher
CREATE TABLE khuyen_mai (
    maKM INT IDENTITY(1,1) PRIMARY KEY,
    tenKM NVARCHAR(100) NOT NULL,
    maCode NVARCHAR(50) UNIQUE NOT NULL, -- Mã voucher
    giaTriKM DECIMAL(12,2) NOT NULL, -- Giá trị giảm
    ngayBatDau DATE NOT NULL,
    ngayKetThuc DATE NOT NULL,
    moTa NVARCHAR(MAX),
    trangThai BIT DEFAULT 1
);

-- Hóa đơn
CREATE TABLE hoa_don (
    maHD INT IDENTITY(1,1) PRIMARY KEY,
    ngayLapHoaDon DATETIME DEFAULT GETDATE(),
    maKH INT,
    maNhanVien INT,
    maPTTT INT NOT NULL,
    maKM INT NULL,
    soLuongBap INT,
    soLuongNuoc INT,
    FOREIGN KEY (maKH) REFERENCES khach_hang(maKH),
    FOREIGN KEY (maNhanVien) REFERENCES nhan_vien(maNhanVien),
    FOREIGN KEY (maPTTT) REFERENCES phuong_thuc_thanh_toan(maPTTT),
    FOREIGN KEY (maKM) REFERENCES khuyen_mai(maKM)

);

-- Chi tiết hóa đơn thực phẩm
-- CREATE TABLE chi_tiet_thuc_pham (
--     maHD INT,
--     maThucPham INT,
--     soLuong INT DEFAULT 1,
--     donGiaBan DECIMAL(12,2),
--     PRIMARY KEY (maHD, maThucPham),
--     FOREIGN KEY (maHD) REFERENCES hoa_don(maHD) ON DELETE CASCADE,
--     FOREIGN KEY (maThucPham) REFERENCES thuc_pham(maThucPham)
-- );

-- Vé
CREATE TABLE ve (
    maVe INT IDENTITY(1,1) PRIMARY KEY,
    maSuatChieu INT,
    maGhe INT,
    giaVe DECIMAL(12,2),
    ngayDat DATE,
    ngayChieu DATE,
    gioChieu TIME,
    tenGhe NVARCHAR(10), 
    tenPhongChieu NVARCHAR(50),
    FOREIGN KEY (maSuatChieu) REFERENCES suat_chieu(maSuatChieu) ON DELETE CASCADE,
    FOREIGN KEY (maGhe) REFERENCES ghe(maGhe) ON DELETE CASCADE,
    
    CONSTRAINT UK_Ve_SuatChieu_Ghe UNIQUE (maSuatChieu, maGhe)
);

-- Chi tiết vé
CREATE TABLE chi_tiet_hoa_don (
    maHD INT,
    maVe INT,
    donGiaBan DECIMAL(12,2),
    soLuong INT DEFAULT 1,
    PRIMARY KEY (maHD, maVe),
    FOREIGN KEY (maHD) REFERENCES hoa_don(maHD) ON DELETE CASCADE,
    FOREIGN KEY (maVe) REFERENCES ve(maVe) ON DELETE CASCADE
);

USE rap;
GO

INSERT INTO the_loai (tenTheLoai, moTa) VALUES 
(N'Hành động', N'Phim có nhiều cảnh hành động, võ thuật, rượt đuổi'),
(N'Hoạt hình', N'Phim hoạt hình, anime, phim 3D cho mọi lứa tuổi'),
(N'Kinh dị', N'Phim gây cảm giác sợ hãi, hồi hộp, gay cấn'),
(N'Hài', N'Phim hài hước, vui nhộn, giải trí'),
(N'Tình cảm', N'Phim về tình yêu, tình cảm lãng mạn'),
(N'Khoa học viễn tưởng', N'Phim về tương lai, công nghệ, vũ trụ'),
(N'Phiêu lưu', N'Phim về những cuộc hành trình, khám phá'),
(N'Tâm lý', N'Phim về tâm lý nhân vật, cảm xúc sâu sắc'),
(N'Trinh thám', N'Phim về giải mã, phá án, bí ẩn'),
(N'Chiến tranh', N'Phim về chiến tranh, quân sự'),
(N'Thần thoại', N'Phim về thần thoại, siêu anh hùng'),
(N'Tội phạm', N'Phim về băng đảng, mafia, tội phạm'),
(N'Gia đình', N'Phim dành cho cả gia đình xem'),
(N'Lịch sử', N'Phim tái hiện sự kiện lịch sử'),
(N'Âm nhạc', N'Phim ca nhạc, nhạc kịch');

GO

INSERT INTO Phim (tenPhim, moTa, doTuoi, quocGia, thoiLuong, daoDien, ngayKhoiChieu, img, maTheLoai) 
VALUES (
    N'Avengers: Hồi Kết',
    N'Sau sự kiện hủy diệt tàn khốc, vũ trụ chìm trong cảnh hoang tàn. Với sự trợ giúp của những đồng minh còn sống sót, biệt đội siêu anh hùng Avengers tập hợp một lần nữa để đảo ngược hành động của Thanos và khôi phục lại trật tự của vũ trụ.',
    N'13+',
    N'Âu Mỹ',
    181,
    N'Anthony Russo, Joe Russo',
    '2019-04-26',
    '/ImgPhim/endgame.jpg',
    1
);

-- 2. One Piece Film: Red
INSERT INTO Phim (tenPhim, moTa, doTuoi, quocGia, thoiLuong, daoDien, ngayKhoiChieu, img, maTheLoai) 
VALUES (
    N'Kho báu One Piece: ĐỎ',
    N'Uta - ca sĩ nổi tiếng nhất thế giới, có giọng hát được mô tả là "khác thường". Cô xuất hiện lần đầu tiên trước công chúng tại một buổi hòa nhạc trực tiếp. Khi tiếng hát của cô vang lên, cả thế giới đều nghe được. Luffy và băng Mũ Rơm cũng đến tham dự và bị mê hoặc bởi giọng hát tuyệt vời của cô.',
    N'13+',
    N'Nhật Bản',
    115,
    N'Goro Taniguchi',
    '2022-08-06',
    '/ImgPhim/onepiece.jpg',
    2
);

-- 3. Jujutsu Kaisen 0
INSERT INTO Phim (tenPhim, moTa, doTuoi, quocGia, thoiLuong, daoDien, ngayKhoiChieu, img, maTheLoai) 
VALUES (
    N'Chú thuật hồi chiến: 0',
    N'Yuta Okkotsu là một học sinh trung học bị ám ảnh bởi linh hồn của bạn gái quá cố, Rika Orimoto. Cô ấy đã chết trong một tai nạn giao thông ngay trước mắt anh. Linh hồn của cô không ra đi mà biến thành một lời nguyền mạnh mẽ bám theo Yuta.',
    N'13+',
    N'Nhật Bản',
    105,
    N'Sunghoo Park',
    '2021-12-24',
    '/ImgPhim/thaycung.jpg',
    2
);

-- 4. Spider-Man: No Way Home
INSERT INTO Phim (tenPhim, moTa, doTuoi, quocGia, thoiLuong, daoDien, ngayKhoiChieu, img, maTheLoai) 
VALUES (
    N'Người nhện: Không nhà',
    N'Lần đầu tiên trong lịch sử điện ảnh của Người Nhện, thân phận của anh chàng hàng xóm thân thiện bị lộ, khiến trách nhiệm làm siêu anh hùng của Peter đối đầu trực tiếp với cuộc sống bình thường của cậu. Khi cậu nhờ Doctor Strange giúp đỡ, mọi thứ trở nên nguy hiểm hơn, buộc cậu phải khám phá ra ý nghĩa thực sự của việc trở thành Người Nhện.',
    N'13+',
    N'Âu Mỹ',
    148,
    N'Jon Watts',
    '2021-12-17',
    '/ImgPhim/spiderman.jpg',
    1
);

-- 5. Bố Già
INSERT INTO Phim (tenPhim, moTa, doTuoi, quocGia, thoiLuong, daoDien, ngayKhoiChieu, img, maTheLoai) 
VALUES (
    N'Bố Già',
    N'Bố Già là bộ phim về ông Sang - một người cha "siêu nhân" sống ở khu nhà trọ nghèo. Ông là chỗ dựa tinh thần của những người xung quanh, luôn sẵn sàng giúp đỡ mọi người. Nhưng chính ông lại đang phải đối mặt với bệnh tật và những khó khăn trong mối quan hệ với con trai.',
    N'13+',
    N'Việt Nam',
    128,
    N'Trấn Thành',
    '2021-03-12',
    '/ImgPhim/bogia.jpg',
    5
);

-- 6. Thêm một số phim Việt Nam khác (bonus)
INSERT INTO Phim (tenPhim, moTa, doTuoi, quocGia, thoiLuong, daoDien, ngayKhoiChieu, img, maTheLoai) 
VALUES (
    N'Mai',
    N'Câu chuyện về Mai - một cô gái xinh đẹp và tài năng nhưng mang trong mình những vết thương quá khứ. Cô gặp gỡ Dương - một chàng trai lạc quan và yêu đời. Liệu tình yêu có thể chữa lành những nỗi đau?',
    N'16+',
    N'Việt Nam',
    131,
    N'Trấn Thành',
    '2024-02-10',
    '/ImgPhim/mai.jpg',
    5
);

-- 7. IT Chapter Two (IT 3)
INSERT INTO Phim (tenPhim, moTa, doTuoi, quocGia, thoiLuong, daoDien, ngayKhoiChieu, img, maTheLoai) 
VALUES (
    N'IT: Gã hề ma quái 3',
    N'27 năm sau những sự kiện kinh hoàng trong phần đầu, Câu lạc bộ những kẻ thất bại đã trưởng thành và rời xa thị trấn Derry. Tuy nhiên, một cuộc gọi đưa họ trở về khi những vụ giết người bắt đầu lại. Họ phải đối mặt với nỗi ám ảnh thuở nhỏ và chú hề Pennywise một lần nữa.',
    N'16+',
    N'Âu Mỹ',
    169,
    N'Andy Muschietti',
    '2019-09-06',
    '/ImgPhim/it3.jpg',
    3
);

-- 8. Squid Game: The Challenge (Series/Movie)
INSERT INTO Phim (tenPhim, moTa, doTuoi, quocGia, thoiLuong, daoDien, ngayKhoiChieu, img, maTheLoai) 
VALUES (
    N'Trò chơi con mực',
    N'456 người chơi, tất cả đều đang gặp khó khăn về tài chính trong cuộc sống, nhận được lời mời bí ẩn để tham gia vào một trò chơi sinh tồn. Họ phải chơi các trò chơi trẻ em đơn giản nhưng với cái giá là mạng sống của họ. Giải thưởng: 45.6 tỷ won. Nhưng chỉ có một người chiến thắng.',
    N'18+',
    N'Hàn Quốc',
    485,
    N'Hwang Dong-hyuk',
    '2021-09-17',
    '/ImgPhim/squidgame.jpg',
    1
);

-- 9. (Tử Chiến Trên Không)
INSERT INTO Phim (tenPhim, moTa, doTuoi, quocGia, thoiLuong, daoDien, ngayKhoiChieu, img, maTheLoai) 
VALUES (
    N'Tử Chiến Trên Không',
    N'Tử Chiến Trên Không là phim điện ảnh hành động - kịch tính, được lấy cảm hứng từ vụ cướp máy bay có thật tại Việt Nam sau năm 1975. Đón xem hành động Việt Nam kịch tính nhất tháng 9 này!',
    N'18+',
    N'Việt Nam',
    107,
    N'Hàm Trần',
    '2025-09-19',
    '/ImgPhim/tuchientrenkhong.jpg',
    1  -- Hành động
);

GO

INSERT INTO phong_chieu (tenPhong, soLuongGhe) VALUES 
(N'Phòng 1 - Standard', 120),
(N'Phòng 2 - VIP', 120),
(N'Phòng 3 - IMAX', 120),
(N'Phòng 4 - 4DX', 120),
(N'Phòng 5 - Standard', 120);

GO
-- ============================================
-- STORED PROCEDURE: Thêm suất chiếu cho nhiều ngày
-- ============================================
CREATE PROCEDURE sp_ThemSuatChieuNhieuNgay
    @soNgay INT = 4  -- Số ngày cần thêm (mặc định 4 ngày)
AS
BEGIN
    SET NOCOUNT ON;
    
    DECLARE @ngayHienTai DATE = CAST(GETDATE() AS DATE);
    DECLARE @ngayDangXuLy DATE;
    DECLARE @chiSoNgay INT = 0;
    
    -- Duyệt qua từng ngày
    WHILE @chiSoNgay < @soNgay
    BEGIN
        SET @ngayDangXuLy = DATEADD(DAY, @chiSoNgay, @ngayHienTai);
        
        -- Phim 1: Avengers: Hồi Kết (Phòng 3)
        INSERT INTO suat_chieu (maPhim, maPhongChieu, ngayChieu, gioChieu, giaVeCoBan) VALUES
        (1, 3, @ngayDangXuLy, '09:00:00', 100000),
        (1, 3, @ngayDangXuLy, '11:00:00', 100000),
        (1, 3, @ngayDangXuLy, '13:00:00', 100000),
        (1, 3, @ngayDangXuLy, '15:00:00', 100000),
        (1, 3, @ngayDangXuLy, '17:00:00', 110000),
        (1, 3, @ngayDangXuLy, '19:00:00', 110000),
        (1, 3, @ngayDangXuLy, '21:00:00', 120000);
        
        -- Phim 2: One Piece Film: Red (Phòng 1)
        INSERT INTO suat_chieu (maPhim, maPhongChieu, ngayChieu, gioChieu, giaVeCoBan) VALUES
        (2, 1, @ngayDangXuLy, '09:00:00', 80000),
        (2, 1, @ngayDangXuLy, '11:00:00', 80000),
        (2, 1, @ngayDangXuLy, '13:00:00', 80000),
        (2, 1, @ngayDangXuLy, '15:00:00', 80000),
        (2, 1, @ngayDangXuLy, '17:00:00', 85000),
        (2, 1, @ngayDangXuLy, '19:00:00', 85000),
        (2, 1, @ngayDangXuLy, '21:00:00', 90000);
        
        -- Phim 3: Jujutsu Kaisen 0 (Phòng 2)
        INSERT INTO suat_chieu (maPhim, maPhongChieu, ngayChieu, gioChieu, giaVeCoBan) VALUES
        (3, 2, @ngayDangXuLy, '09:00:00', 85000),
        (3, 2, @ngayDangXuLy, '11:00:00', 85000),
        (3, 2, @ngayDangXuLy, '13:00:00', 85000),
        (3, 2, @ngayDangXuLy, '15:00:00', 85000),
        (3, 2, @ngayDangXuLy, '17:00:00', 90000),
        (3, 2, @ngayDangXuLy, '19:00:00', 90000),
        (3, 2, @ngayDangXuLy, '21:00:00', 95000);
        
        -- Phim 4: Spider-Man: No Way Home (Phòng 4)
        INSERT INTO suat_chieu (maPhim, maPhongChieu, ngayChieu, gioChieu, giaVeCoBan) VALUES
        (4, 4, @ngayDangXuLy, '09:00:00', 120000),
        (4, 4, @ngayDangXuLy, '11:00:00', 120000),
        (4, 4, @ngayDangXuLy, '13:00:00', 120000),
        (4, 4, @ngayDangXuLy, '15:00:00', 120000),
        (4, 4, @ngayDangXuLy, '17:00:00', 125000),
        (4, 4, @ngayDangXuLy, '19:00:00', 130000),
        (4, 4, @ngayDangXuLy, '21:00:00', 130000);
        
        -- Phim 5: Bố Già (Phòng 1 và 5)
        INSERT INTO suat_chieu (maPhim, maPhongChieu, ngayChieu, gioChieu, giaVeCoBan) VALUES
        (5, 1, @ngayDangXuLy, '09:00:00', 75000),
        (5, 1, @ngayDangXuLy, '11:00:00', 75000),
        (5, 1, @ngayDangXuLy, '13:00:00', 75000),
        (5, 1, @ngayDangXuLy, '15:00:00', 75000),
        (5, 5, @ngayDangXuLy, '17:00:00', 80000),
        (5, 5, @ngayDangXuLy, '19:00:00', 80000),
        (5, 5, @ngayDangXuLy, '21:00:00', 85000);
        
        -- Phim 6: Mai (Phòng 2)
        INSERT INTO suat_chieu (maPhim, maPhongChieu, ngayChieu, gioChieu, giaVeCoBan) VALUES
        (6, 2, @ngayDangXuLy, '09:00:00', 80000),
        (6, 2, @ngayDangXuLy, '11:00:00', 80000),
        (6, 2, @ngayDangXuLy, '13:00:00', 80000),
        (6, 2, @ngayDangXuLy, '15:00:00', 80000),
        (6, 2, @ngayDangXuLy, '17:00:00', 85000),
        (6, 2, @ngayDangXuLy, '19:00:00', 90000),
        (6, 2, @ngayDangXuLy, '21:00:00', 90000);
        
        -- Phim 7: IT Chapter Two (Phòng 4)
        INSERT INTO suat_chieu (maPhim, maPhongChieu, ngayChieu, gioChieu, giaVeCoBan) VALUES
        (7, 4, @ngayDangXuLy, '09:00:00', 95000),
        (7, 4, @ngayDangXuLy, '11:00:00', 95000),
        (7, 4, @ngayDangXuLy, '13:00:00', 95000),
        (7, 4, @ngayDangXuLy, '15:00:00', 95000),
        (7, 4, @ngayDangXuLy, '17:00:00', 100000),
        (7, 4, @ngayDangXuLy, '19:00:00', 105000),
        (7, 4, @ngayDangXuLy, '21:00:00', 110000);
        
        -- Phim 8: Squid Game (Phòng 3)
        INSERT INTO suat_chieu (maPhim, maPhongChieu, ngayChieu, gioChieu, giaVeCoBan) VALUES
        (8, 3, @ngayDangXuLy, '09:00:00', 90000),
        (8, 3, @ngayDangXuLy, '11:00:00', 90000),
        (8, 3, @ngayDangXuLy, '13:00:00', 90000),
        (8, 3, @ngayDangXuLy, '15:00:00', 90000),
        (8, 3, @ngayDangXuLy, '17:00:00', 95000),
        (8, 3, @ngayDangXuLy, '19:00:00', 95000),
        (8, 3, @ngayDangXuLy, '21:00:00', 100000);
        
        -- Phim 9: Tử Chiến Trên Không (Phòng 5)
        INSERT INTO suat_chieu (maPhim, maPhongChieu, ngayChieu, gioChieu, giaVeCoBan) VALUES
        (9, 5, @ngayDangXuLy, '09:00:00', 85000),
        (9, 5, @ngayDangXuLy, '11:00:00', 85000),
        (9, 5, @ngayDangXuLy, '13:00:00', 85000),
        (9, 5, @ngayDangXuLy, '15:00:00', 85000),
        (9, 5, @ngayDangXuLy, '17:00:00', 90000),
        (9, 5, @ngayDangXuLy, '19:00:00', 90000),
        (9, 5, @ngayDangXuLy, '21:00:00', 95000);
        
        SET @chiSoNgay = @chiSoNgay + 1;
    END
    
    PRINT N'Đã thêm suất chiếu thành công cho ' + CAST(@soNgay AS NVARCHAR(10)) + N' ngày!';
    PRINT N'Tổng số suất chiếu đã thêm: ' + CAST(@soNgay * 9 * 7 AS NVARCHAR(10));
END
GO

-- ============================================
-- CÁCH SỬ DỤNG:
-- ============================================

-- Thêm suất chiếu cho 4 ngày (mặc định)
EXEC sp_ThemSuatChieuNhieuNgay;

INSERT INTO loai_ghe (tenLoaiGhe, moTa, phuThu) VALUES
(N'Ghế Thường', N'Ghế ngồi tiêu chuẩn, thoải mái', 0),
(N'Ghế VIP', N'Ghế da cao cấp, rộng rãi hơn', 20000),
(N'Ghế Đôi', N'Ghế đôi cho cặp đôi, không có tay vịn giữa', 30000);

DECLARE @maPhong INT;
DECLARE @hang CHAR(1);
DECLARE @cot INT;
DECLARE @tenGhe NVARCHAR(10);
DECLARE @maLoaiGhe INT;

-- Lặp qua tất cả 5 phòng
SET @maPhong = 1;
WHILE @maPhong <= 5
BEGIN
    -- Xác định loại ghế dựa vào phòng
    -- Phòng 1, 3, 4, 5: Ghế thường (maLoaiGhe = 1)
    -- Phòng 2: Ghế VIP (maLoaiGhe = 2)
    IF @maPhong = 2
        SET @maLoaiGhe = 2; -- Ghế VIP
    ELSE
        SET @maLoaiGhe = 1; -- Ghế thường
    
    -- Insert hàng A đến I (ghế đơn)
    SET @hang = 'A';
    WHILE @hang <= 'I'
    BEGIN
        SET @cot = 1;
        WHILE @cot <= 12
        BEGIN
            SET @tenGhe = @hang + CAST(@cot AS NVARCHAR);
            
            INSERT INTO ghe (tenGhe, maLoaiGhe, maPhongChieu)
            VALUES (@tenGhe, @maLoaiGhe, @maPhong);
            
            SET @cot = @cot + 1;
        END
        SET @hang = CHAR(ASCII(@hang) + 1);
    END
    
    -- Insert hàng J (ghế đôi)
    SET @cot = 1;
    WHILE @cot <= 12
    BEGIN
        -- Ghế đôi có tên dạng: J1,J2 hoặc J3,J4
        SET @tenGhe = 'J' + CAST(@cot AS NVARCHAR) + ',J' + CAST(@cot + 1 AS NVARCHAR);
        
        INSERT INTO ghe (tenGhe, maLoaiGhe, maPhongChieu)
        VALUES (@tenGhe, 3, @maPhong); -- maLoaiGhe = 3 là Ghế Đôi
        
        SET @cot = @cot + 2; -- Nhảy 2 vì ghế đôi chiếm 2 vị trí
    END
    
    SET @maPhong = @maPhong + 1;
END
	
INSERT INTO nhan_vien VALUES(N'Phạm Quang Khải', '0901313131', 'quangkhai12375@gmail.com', 'pqknhanvien', '12345678', '/img/img_qk.jpg');
INSERT INTO nhan_vien VALUES(N'Nguyễn Minh Đạt', '0921212122', 'coloky@gmail.com', 'admin', 'admin', '')

INSERT INTO phuong_thuc_thanh_toan (tenPTTT, moTa, phiGiaoDich) VALUES
(N'Tiền mặt', N'Thanh toán bằng tiền mặt tại quầy', 0),
(N'Thẻ ATM/Credit', N'Thanh toán bằng thẻ ngân hàng nội địa hoặc quốc tế', 0),
(N'MoMo', N'Thanh toán qua ví điện tử MoMo', 0),
(N'Chuyển khoản', N'Chuyển khoản ngân hàng trực tiếp', 0);

INSERT INTO khuyen_mai (tenKM, maCode, giaTriKM, ngayBatDau, ngayKetThuc, moTa, trangThai) VALUES
(
    N'Sinh viên IUH - Giảm 50K',
    'SVIUH',
    50000,
    '2024-09-01',
    '2025-08-31',
    N'Giảm 20% cho sinh viên xuất trình thẻ sinh viên còn hạn.',
    1
);

INSERT INTO khach_hang(tenKH, SDT) VALUES ( N'Việt', '0933333333')
INSERT INTO khach_hang(tenKH, SDT) VALUES ( N'Kiệt', '0922222222')
INSERT INTO khach_hang(tenKH, SDT) VALUES ( N'Tiến', '0911111111')
INSERT INTO khach_hang(tenKH, SDT) VALUES ( N'Tú', '0944444444')
INSERT INTO khach_hang(tenKH, SDT) VALUES ( N'Vỹ', '0955555555')

-- Vé 1: Ghế đơn A1
INSERT INTO ve (maSuatChieu, maGhe, giaVe, ngayDat, ngayChieu, gioChieu, tenGhe, tenPhongChieu)
VALUES (7, 1, 120000, CAST(GETDATE() AS DATE), CAST(GETDATE() AS DATE), '21:00:00', N'A1', N'Phòng 3 - IMAX');

-- Vé 2: Ghế đơn A2
INSERT INTO ve (maSuatChieu, maGhe, giaVe, ngayDat, ngayChieu, gioChieu, tenGhe, tenPhongChieu)
VALUES (7, 2, 120000, CAST(GETDATE() AS DATE), CAST(GETDATE() AS DATE), '21:00:00', N'A2', N'Phòng 3 - IMAX');

-- Vé 3: Ghế đơn A3
INSERT INTO ve (maSuatChieu, maGhe, giaVe, ngayDat, ngayChieu, gioChieu, tenGhe, tenPhongChieu)
VALUES (7, 3, 120000, CAST(GETDATE() AS DATE), CAST(GETDATE() AS DATE), '21:00:00', N'A3', N'Phòng 3 - IMAX');

-- Vé 4: Ghế đơn B1
INSERT INTO ve (maSuatChieu, maGhe, giaVe, ngayDat, ngayChieu, gioChieu, tenGhe, tenPhongChieu)
VALUES (7, 13, 120000, CAST(GETDATE() AS DATE), CAST(GETDATE() AS DATE), '21:00:00', N'B1', N'Phòng 3 - IMAX');

-- Vé 5: Ghế đơn B2
INSERT INTO ve (maSuatChieu, maGhe, giaVe, ngayDat, ngayChieu, gioChieu, tenGhe, tenPhongChieu)
VALUES (7, 14, 120000, CAST(GETDATE() AS DATE), CAST(GETDATE() AS DATE), '21:00:00', N'B2', N'Phòng 3 - IMAX');

-- Vé 6: Ghế đơn C1
INSERT INTO ve (maSuatChieu, maGhe, giaVe, ngayDat, ngayChieu, gioChieu, tenGhe, tenPhongChieu)
VALUES (7, 25, 120000, CAST(GETDATE() AS DATE), CAST(GETDATE() AS DATE), '21:00:00', N'C1', N'Phòng 3 - IMAX');

-- Vé 7: Ghế đơn C2
INSERT INTO ve (maSuatChieu, maGhe, giaVe, ngayDat, ngayChieu, gioChieu, tenGhe, tenPhongChieu)
VALUES (7, 26, 120000, CAST(GETDATE() AS DATE), CAST(GETDATE() AS DATE), '21:00:00', N'C2', N'Phòng 3 - IMAX');

-- Vé 8: Ghế đơn D1
INSERT INTO ve (maSuatChieu, maGhe, giaVe, ngayDat, ngayChieu, gioChieu, tenGhe, tenPhongChieu)
VALUES (7, 37, 120000, CAST(GETDATE() AS DATE), CAST(GETDATE() AS DATE), '21:00:00', N'D1', N'Phòng 3 - IMAX');

-- Vé 9: Ghế đôi J1,J2
INSERT INTO ve (maSuatChieu, maGhe, giaVe, ngayDat, ngayChieu, gioChieu, tenGhe, tenPhongChieu)
VALUES (7, 109, 270000, CAST(GETDATE() AS DATE), CAST(GETDATE() AS DATE), '21:00:00', N'J1,J2', N'Phòng 3 - IMAX');

-- Vé 10: Ghế đôi J3,J4
INSERT INTO ve (maSuatChieu, maGhe, giaVe, ngayDat, ngayChieu, gioChieu, tenGhe, tenPhongChieu)
VALUES (7, 110, 270000, CAST(GETDATE() AS DATE), CAST(GETDATE() AS DATE), '21:00:00', N'J3,J4', N'Phòng 3 - IMAX');

INSERT INTO hoa_don(ngayLapHoaDon, maKH, maNhanVien, maPTTT, maKM, soLuongBap, soLuongNuoc) 
VALUES (CAST(GETDATE() AS DATE), 1, 1, 1, 1, 2, 1)

INSERT INTO hoa_don(ngayLapHoaDon, maKH, maNhanVien, maPTTT, maKM, soLuongBap, soLuongNuoc) 
VALUES (CAST(GETDATE() AS DATE), 2, 1, 2, 1, 1, 1)

INSERT INTO hoa_don(ngayLapHoaDon, maKH, maNhanVien, maPTTT, soLuongBap, soLuongNuoc) 
VALUES (CAST(GETDATE() AS DATE), 3, 1, 3, 1, 1)

INSERT INTO hoa_don(ngayLapHoaDon, maKH, maNhanVien, maPTTT, soLuongBap, soLuongNuoc) 
VALUES (CAST(GETDATE() AS DATE), 4, 2, 1, 0, 1)

INSERT INTO hoa_don(ngayLapHoaDon, maKH, maNhanVien, maPTTT, maKM, soLuongBap, soLuongNuoc) 
VALUES (CAST(GETDATE() AS DATE), 1, 2, 2, 1, 0, 1)

INSERT INTO hoa_don(ngayLapHoaDon, maKH, maNhanVien, maPTTT, maKM, soLuongBap, soLuongNuoc) 
VALUES (CAST(GETDATE() AS DATE), 2, 2, 1, 1, 1, 1)

-- HÓA ĐƠN 1 (maHD = 1): 2 ghế đơn A1, A2
INSERT INTO chi_tiet_hoa_don (maHD, maVe, donGiaBan, soLuong)
VALUES (1, 1, 120000, 1);  -- Vé A1

INSERT INTO chi_tiet_hoa_don (maHD, maVe, donGiaBan, soLuong)
VALUES (1, 2, 120000, 1);  -- Vé A2

-- HÓA ĐƠN 2 (maHD = 2): 2 ghế đơn A3, B1
INSERT INTO chi_tiet_hoa_don (maHD, maVe, donGiaBan, soLuong)
VALUES (2, 3, 120000, 1);  -- Vé A3

INSERT INTO chi_tiet_hoa_don (maHD, maVe, donGiaBan, soLuong)
VALUES (2, 4, 120000, 1);  -- Vé B1

-- HÓA ĐƠN 3 (maHD = 3): 2 ghế đơn B2, C1
INSERT INTO chi_tiet_hoa_don (maHD, maVe, donGiaBan, soLuong)
VALUES (3, 5, 120000, 1);  -- Vé B2

INSERT INTO chi_tiet_hoa_don (maHD, maVe, donGiaBan, soLuong)
VALUES (3, 6, 120000, 1);  -- Vé C1

-- HÓA ĐƠN 4 (maHD = 4): 2 ghế đơn C2, D1
INSERT INTO chi_tiet_hoa_don (maHD, maVe, donGiaBan, soLuong)
VALUES (4, 7, 120000, 1);  -- Vé C2

INSERT INTO chi_tiet_hoa_don (maHD, maVe, donGiaBan, soLuong)
VALUES (4, 8, 120000, 1);  -- Vé D1

-- HÓA ĐƠN 5 (maHD = 5): 1 ghế đôi J1,J2
INSERT INTO chi_tiet_hoa_don (maHD, maVe, donGiaBan, soLuong)
VALUES (5, 9, 270000, 1);  -- Vé J1,J2

-- HÓA ĐƠN 6 (maHD = 6): 1 ghế đôi J3,J4
INSERT INTO chi_tiet_hoa_don (maHD, maVe, donGiaBan, soLuong)
VALUES (6, 10, 270000, 1);  -- Vé J3,J4