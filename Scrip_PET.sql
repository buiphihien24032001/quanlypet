  
CREATE DATABASE QuanLyPet
GO
USE QuanLyPet
GO
CREATE TABLE nhanvien
(
 manv VARCHAR(10) PRIMARY KEY,
 tennv NVARCHAR(255) NOT NULL,
 sdt VARCHAR(10) NOT NULL,
 email NVARCHAR(255) NOT NULL,
 id NVARCHAR(10) NOT NULL,
 pass NVARCHAR(10) NOT NULL,
 vaitro BIT NOT NULL
)
GO
CREATE TABLE khachhang
(
 makh VARCHAR(10) PRIMARY KEY,
 tenkh NVARCHAR(255) NOT NULL,
 sdt NVARCHAR(20) 
)
GO
CREATE TABLE nhacungcap
(
 mancc VARCHAR(10) PRIMARY KEY,
 tenncc NVARCHAR(255) NOT NULL,
 mathang NVARCHAR(255) NOT NULL,
 sdt VARCHAR(10) NOT NULL,
 diachi NVARCHAR(255) NOT NULL,
 ghichu NVARCHAR(255),
 
)
GO

CREATE TABLE hoadonnhaphang
(
 mahdnhap VARCHAR(10) PRIMARY KEY,
 mancc VARCHAR(10),
 ngaynhap DATE
)
GO
CREATE TABLE hdnhaphangchitiet
(
 mahdnhap VARCHAR(10) NOT NULL,
 idpet VARCHAR(10),
 mata VARCHAR(10),
 madd VARCHAR(10) ,
 slpet INT ,
 slta INT,
 sldd INT,
)
CREATE TABLE pet
(
 idpet VARCHAR(10) PRIMARY KEY,
 tuoi FLOAT NOT NULL,
 maloai VARCHAR(10) NOT NULL,
 magiong VARCHAR(10) NOT NULL,
 gia INT NOT NULL,
 slpet int
)
GO
CREATE TABLE giong
(
 magiong VARCHAR(10) PRIMARY KEY,
 tengiong NVARCHAR(255) NOT NULL,
)
GO
CREATE TABLE loai
(
 maloai VARCHAR(10) PRIMARY KEY,
 tenloai NVARCHAR(255) NOT NULL,
)
GO
CREATE TABLE hoadon
(
 mahd VARCHAR(10) PRIMARY KEY,
 ngaymua DATE NOT NULL,
 manv VARCHAR(10),
 makh VARCHAR(10) NOT NULL,
)
GO
CREATE TABLE hdchitiet
(
 mahd VARCHAR(10) NOT NULL,
 mapet VARCHAR(10),
 slpet INT,
 mata VARCHAR(10),
 slta INT,
 madd VARCHAR(10),
 sldd INT,
 
)
go
CREATE TABLE thucan
(
 mata VARCHAR(10) PRIMARY KEY,
 tenta NVARCHAR(255) NOT NULL,
 petan VARCHAR(10) NOT NULL,
 gia INT NOT NULL,
 slta int
)
GO
CREATE TABLE dodung
(
 madd VARCHAR(10) PRIMARY KEY,
 tendd NVARCHAR(255) NOT NULL,
 gia INT NOT NULL,
 sldd int
)
GO

CREATE TABLE petnhap
(
 mapet VARCHAR(10),
 gia INT NOT NULL,
)
GO
CREATE TABLE thucannhap
(
 mata VARCHAR(10),
 gia INT NOT NULL,
)
GO
CREATE TABLE dodungnhap
(
 madd VARCHAR(10),
 gia INT NOT NULL,
)
GO








ALTER TABLE dbo.pet ADD CONSTRAINT FK_giong FOREIGN KEY(magiong) REFERENCES dbo.giong(magiong),
      CONSTRAINT FK_loai FOREIGN KEY(maloai) REFERENCES dbo.loai(maloai)

ALTER TABLE dbo.hdchitiet ADD CONSTRAINT FK_hoadon FOREIGN KEY(mahd) REFERENCES dbo.hoadon(mahd),
         CONSTRAINT Fk_pet FOREIGN KEY(mapet) REFERENCES dbo.pet(idpet),
         CONSTRAINT FK_thucan FOREIGN KEY(mata) REFERENCES dbo.thucan(mata),
         CONSTRAINT FK_dodung FOREIGN KEY(madd) REFERENCES dbo.dodung(madd)
         
ALTER TABLE dbo.hoadon ADD CONSTRAINT FK_khachhang FOREIGN KEY(makh) REFERENCES dbo.khachhang(makh)

ALTER TABLE dbo.thucan ADD CONSTRAINT FK_doan FOREIGN KEY(petan) REFERENCES dbo.loai(maloai)





-- tạo trigger tự động thêm mã cho các bảng
-- Bảng Nhân Viên
go
create trigger trigNhanVien on nhanvien
 instead of insert
 as
 begin
  Declare @manv varchar(10)
  Declare @tennv nvarchar(30)
  Declare @sdt varchar(10)
  Declare @email nvarchar(20)
  Declare @id nvarchar(10)
  Declare @pass nvarchar(10)
  Declare @vaitro bit
  if not exists (select * from nhanvien) 
   Set @manv=1
  else
   Set @manv=(select RIGHT(MAX(manv),3) from nhanvien)+1
   set @tennv=(select tennv from inserted)
   set @sdt=(select sdt from inserted)
   set @email=(select email from inserted)
   set @id=(select id from inserted)
   set @pass=(select pass from inserted)
   set @vaitro=(select vaitro from inserted)
   Set @manv='NV'+REPLICATE('0',3-LEN(@manv))+@manv
   insert into nhanvien values(@manv,@tennv,@sdt,@email,@id,@pass,@vaitro)
 end
go
-- Bảng khách hàng
go
create trigger trigKhachHang on khachhang
instead of insert
as
begin
 declare @makh varchar(10)
 declare @tenkh nvarchar(50)
 declare @sdt nvarchar(20)
 if not exists ( select * from khachhang)
  set @makh=1
 else
  Set @makh=(select RIGHT(MAX(makh),3) from khachhang)+1
  set @tenkh=(select tenkh from inserted)
  set @sdt=(select sdt from inserted)
  Set @makh='KH'+REPLICATE('0',3-LEN(@makh))+@makh
  insert into khachhang values(@makh,@tenkh,@sdt)
 end
go

-- bảng nhà cung cấp
go
create trigger trigcungcap on nhacungcap
instead of insert
as
begin
 declare @mancc varchar(10)
 declare @tenncc nvarchar(255)
 declare @mathang nvarchar(255)
 declare @sdt varchar(10)
 declare @diachi nvarchar(255)
 declare @ghichu nvarchar(255)
 if not exists (select * from nhacungcap)
 set @mancc=1
else
 Set @mancc=(select RIGHT(MAX(mancc),3) from nhacungcap)+1
 set @tenncc=(select tenncc from inserted)
 set @mathang=(select mathang from inserted)
 set @sdt=(select sdt from inserted)
 set @diachi=(select diachi from inserted)
 Set @mancc='NCC'+REPLICATE('0',3-LEN(@mancc))+@mancc
 insert into nhacungcap values(@mancc,@tenncc,@mathang,@sdt,@diachi,@ghichu)
 end
go

-- bảng hóa đơn nhập hàng
go
create trigger trighoadonnhaphang on hoadonnhaphang
instead of insert
as
begin
 declare @mahdnhap varchar(10)
 declare @mancc varchar(10)
 declare @ngaynhap date
 if not exists (select * from hoadonnhaphang)
  set @mahdnhap=1
else
  Set @mahdnhap=(select RIGHT(MAX(mahdnhap),3) from hoadonnhaphang)+1
  set @mancc=(select mancc from inserted)
  set @ngaynhap=(select ngaynhap from inserted)
  Set @mahdnhap='HDN'+REPLICATE('0',3-LEN(@mahdnhap))+@mahdnhap
  insert into hoadonnhaphang values(@mahdnhap,@mancc,@ngaynhap)
 end
go

-- bảng pet
go
create trigger trigpet on pet
instead of insert
as
begin
 declare @idpet varchar(10)
 declare @tuoi float 
 declare @maloai varchar(10)
 declare @magiong varchar(10)
 declare @gia int
 declare @slpet int
 if not exists (select * from pet)
  set @idpet=1
else
  Set @idpet=(select RIGHT(MAX(idpet),3) from pet)+1
  set @tuoi=(select tuoi from inserted)
  set @maloai=(select maloai from inserted)
  set @magiong=(select magiong from inserted)
  set @gia=(select gia from inserted)
  set @slpet=(select slpet from inserted)
  Set @idpet='PET'+REPLICATE('0',3-LEN(@idpet))+@idpet
  insert into pet values(@idpet,@tuoi,@maloai,@magiong,@gia,@slpet)
 end
go

-- bảng giong
go
create trigger triggiong on giong
instead of insert
as
begin
 declare @magiong varchar(10)
 declare @tengiong nvarchar(255)
 if not exists (select * from giong)
  set @magiong=1
else
  Set @magiong=(select RIGHT(MAX(magiong),3) from giong)+1
  set @tengiong=(select tengiong from inserted)
  Set @magiong='G'+REPLICATE('0',3-LEN(@magiong))+@magiong
  insert into giong values(@magiong,@tengiong)
 end
go

-- bảng loai
go
create trigger trigloai on loai
instead of insert
as
begin
 declare @maloai varchar(10)
 declare @tenloai nvarchar(255)
 if not exists (select * from loai)
  set @maloai=1
else
  Set @maloai=(select RIGHT(MAX(maloai),3) from loai)+1
  set @tenloai=(select tenloai from inserted)
  Set @maloai='L'+REPLICATE('0',3-LEN(@maloai))+@maloai
  insert into loai values(@maloai,@tenloai)
 end
go

-- bảng hoadon
go
create trigger trighoadon on hoadon
instead of insert
as
begin
 declare @mahd varchar(10)
 declare @ngaymua date
 declare @manv varchar(10)
 declare @makh varchar(10)
 if not exists (select * from hoadon)
  set @mahd=1
else
  Set @mahd=(select RIGHT(MAX(mahd),3) from hoadon)+1
  set @ngaymua=(select ngaymua from inserted)
  set @manv=(select manv from inserted)
  set @makh=(select makh from inserted)
  Set @mahd='HD'+REPLICATE('0',3-LEN(@mahd))+@mahd
  insert into hoadon values(@mahd,@ngaymua,@manv,@makh)
 end
go



-- bảng thức ăn
go
create trigger trigthucan on thucan
instead of insert
as
begin
 declare @mata varchar(10)
 declare @tenta varchar(255)
 declare @petan varchar(10)
 declare @gia int
 declare @slta int
 if not exists (select * from thucan)
  set @mata=1
else
  Set @mata=(select RIGHT(MAX(mata),3) from thucan)+1
  set @tenta=(select tenta from inserted)
  set @petan=(select petan from inserted)
  set @gia=(select gia from inserted)
  set @slta=(select slta from inserted)
  Set @mata='TA'+REPLICATE('0',3-LEN(@mata))+@mata
  insert into thucan values(@mata,@tenta,@petan,@gia,@slta)
 end
go


-- bảng dodung
go
create trigger trigdodung on dodung
instead of insert
as
begin
 declare @madd varchar(10)
 declare @tendd nvarchar(255)
 declare @gia int
 declare @sldd int
 if not exists (select * from dodung)
  set @madd=1
else
  Set @madd=(select RIGHT(MAX(madd),3) from dodung)+1
  set @tendd=(select tendd from inserted)
  set @gia=(select gia from inserted)
  set @sldd=(select sldd from inserted)
  Set @madd='DD'+REPLICATE('0',3-LEN(@madd))+@madd
  insert into dodung values(@madd,@tendd,@gia,@sldd)
 end
go

-- bảng petnhap
create trigger trigpetnhap on petnhap
instead of insert
as
begin
 declare @mapet varchar(10)
 declare @gia int
 if not exists ( select * from petnhap)
  set @mapet=1
 else
  Set @mapet=(select RIGHT(MAX(mapet),3) from petnhap)+1
  set @gia=(select gia from inserted)
  Set @mapet='PET'+REPLICATE('0',3-LEN(@mapet))+@mapet
  insert into petnhap values(@mapet,@gia)
 end
go

-- bảng thucannhap
create trigger trigthucannhap on thucannhap
instead of insert
as
begin
 declare @mata varchar(10)
 declare @gia int
 if not exists ( select * from thucannhap)
  set @mata=1
 else
  Set @mata=(select RIGHT(MAX(mata),3) from thucannhap)+1
  set @gia=(select gia from inserted)
  Set @mata='TA'+REPLICATE('0',3-LEN(@mata))+@mata
  insert into thucannhap values(@mata,@gia)
 end
go

-- bảng dodungnhap
create trigger trigdodungnhap on dodungnhap
instead of insert
as
begin
 declare @madd varchar(10)
 declare @gia int
 if not exists ( select * from dodungnhap)
  set @madd=1
 else
  Set @madd=(select RIGHT(MAX(madd),3) from dodungnhap)+1
  set @gia=(select gia from inserted)
  Set @madd='DD'+REPLICATE('0',3-LEN(@madd))+@madd
  insert into dodungnhap values(@madd,@gia)
 end
go


-- thêm function
CREATE FUNCTION timmagiong (@tengiong NVARCHAR(255)) RETURNS VARCHAR(10) 
AS 
BEGIN
 DECLARE @magiong VARCHAR(10);
 SET @magiong = (SELECT magiong FROM dbo.giong WHERE tengiong=@tengiong)
 RETURN @magiong
END
go

CREATE FUNCTION timmaloai (@tenloai NVARCHAR(255)) RETURNS VARCHAR(10) 
AS 
BEGIN
 DECLARE @maloai VARCHAR(10);
 SET @maloai = (SELECT maloai FROM dbo.loai WHERE tenloai=@tenloai)
 RETURN @maloai
END
go
CREATE FUNCTION mata (@tenta NVARCHAR(255)) RETURNS VARCHAR(10)
AS
BEGIN
 DECLARE @mata VARCHAR(10);
 SET @mata = (SELECT mata FROM dbo.thucan WHERE tenta = @tenta)
 RETURN @mata
END
Trọng Nghĩa đã gửi Hôm qua lúc 23:29
CREATE FUNCTION madd (@tendd NVARCHAR(255)) RETURNS VARCHAR(10)
AS
BEGIN
 DECLARE @madd VARCHAR(10);
 SET @madd = (SELECT madd FROM dbo.dodung WHERE tendd = @tendd)
 RETURN @madd
END










INSERT INTO dbo.loai VALUES  ( '', N'Chó')
INSERT INTO dbo.loai VALUES  ( '', N'Mèo')
INSERT INTO dbo.loai VALUES  ( '', N'Hamster')


INSERT INTO dbo.giong VALUES ('', N'Pug')
INSERT INTO dbo.giong VALUES ('', N'Husky')
INSERT INTO dbo.giong VALUES ('', N'Corgi')
INSERT INTO dbo.giong VALUES ('', N'Golden')
INSERT INTO dbo.giong VALUES ('', N'Samoyed')
INSERT INTO dbo.giong VALUES ('', N'Poodle')
INSERT INTO dbo.giong VALUES ('', N'Shiba')
INSERT INTO dbo.giong VALUES ('', N'Anh lông ngắn')
INSERT INTO dbo.giong VALUES ('', N'Xiêm')
INSERT INTO dbo.giong VALUES ('', N'Ba tư')
INSERT INTO dbo.giong VALUES ('', N'Scottish Fold')
INSERT INTO dbo.giong VALUES ('','Munchkin')
INSERT INTO dbo.giong VALUES ('','Sphynx')
INSERT INTO dbo.giong VALUES ( '',N'Hamster Bear' )
INSERT INTO dbo.giong VALUES ('','Hamster Robo')
INSERT INTO dbo.giong VALUES ('','Hamster winter white')
INSERT INTO dbo.giong VALUES ('','chihuahua')
INSERT INTO dbo.giong VALUES ('','chó Bắc Kinh')
INSERT INTO dbo.giong VALUES ('','chó phú quốc')
INSERT INTO dbo.giong VALUES ('','Alaska')
INSERT INTO dbo.giong VALUES ('','Pomeranian ')
INSERT INTO dbo.giong VALUES ('','Pitbull ')


INSERT INTO dbo.nhacungcap( mancc , tenncc ,mathang , sdt , diachi ,ghichu )
VALUES  ( '' ,  N'petmart' ,  N'thức ăn' ,  '0869546875' , N'Lô 6 KCN II Long Thành - Đồng Nai ', N'' )
  INSERT INTO dbo.nhacungcap( mancc , tenncc ,mathang , sdt , diachi ,ghichu )
VALUES  ( '' ,  N'the nutrience' ,  N'thức ăn' ,  '0954657842' , N'40/16 Thanh Xuân - Hà Nội' , N'' )
  INSERT INTO dbo.nhacungcap( mancc , tenncc ,mathang , sdt , diachi ,ghichu )
VALUES  ( '' ,  N'petsaigon' ,  N'đồ dùng' ,  '0563214754' , N'1006/32 Quang Trung - Gò Vấp - TPHCM' , N'' )
  INSERT INTO dbo.nhacungcap( mancc , tenncc ,mathang , sdt , diachi ,ghichu )
VALUES  ( '' ,  N'dogily' ,  N'pet' ,  '0965458124' , N'606/121 đường Ba Tháng Hai, phường 14, quận 10, thành phố Hồ Chí Minh' , N'' )
 INSERT INTO dbo.nhacungcap( mancc , tenncc ,mathang , sdt , diachi ,ghichu )
VALUES   ( '' ,  N'azpet' ,  N'pet' ,  '0886542654' , N'Số 59, Văn Cao, Liễu Giai, Ba Đình, Hà Nội.' , N'' )

INSERT dbo.khachhang VALUES  ( '',  N'Bùi Trọng Nghĩa',N'0125874693' )
INSERT dbo.khachhang VALUES  ('',  N'Trịnh Nguyễn Thế Hân',N'0269585474' )
INSERT dbo.khachhang VALUES   ( '',  N'Lưu Minh Thiên',N'0369658214' )
INSERT dbo.khachhang VALUES    ( '',  N'Hoàng Đức Ngân',N'0487459636' )
INSERT dbo.khachhang VALUES    ( '',  N'Trịnh Hoàng Nam',N'0225896647' )
INSERT dbo.khachhang VALUES    ( '',  N'Hoàng Thu Thiên Nhi',N'0214574147' )
INSERT dbo.khachhang VALUES    ( '',  N'Lâm Quang Khanh',N'0336952014' )
INSERT dbo.khachhang VALUES    ( '',  N'Nguyễn Anh Thư',N'0250103698' )
INSERT dbo.khachhang VALUES    ( '',  N'Trần Lê Thiên Tôn',N'0985012046' )
INSERT dbo.khachhang VALUES   ( '',  N'Tần Trúc Thi',N'0986302544' )
INSERT dbo.khachhang VALUES   ( '',  N'Đoàn Thị Bảo Ngọc',N'0954215474' )
INSERT dbo.khachhang VALUES   ( '',  N'Nguyễn Minh Tuấn',N'0756421546' )
INSERT dbo.khachhang VALUES   ( '',  N'Trần Mạnh Hùng',N'0695321410' )
INSERT dbo.khachhang VALUES   ( '',  N'Lê Cẩm Tú',N'0956451245' )
INSERT dbo.khachhang VALUES   ( '',  N'Bùi Lệ Quyên',N'0486251324' )
INSERT dbo.khachhang VALUES   ( '',  N'Nguyễn Quốc Hưng',N'0965107515' )
INSERT dbo.khachhang VALUES   ( '',  N'Bùi Tấn Trường',N'0969365125' )
INSERT dbo.khachhang VALUES   ( '',  N'Trần Sơn Mạnh',N'0965324587' )
INSERT dbo.khachhang VALUES   ( '',  N'Phan Thị Hiếu',N'0469365145' )
INSERT dbo.khachhang VALUES   ( '',  N'Đinh Thị Hoàng Anh',N'0958458741' )
INSERT dbo.khachhang VALUES   ( '',  N'Huỳnh Thị Thùy Trang',N'0968547852' )
INSERT dbo.khachhang VALUES   ( '',  N'Đoàn Diễm Trinh',N'0965369789' )
INSERT dbo.khachhang VALUES   ( '',  N'Nguyễn Thị Nhung',N'0937589136' )

  
INSERT INTO dbo.nhanvien VALUES  ( '',N'Trương Đình Hoàng','0144145963', N'jameshien@gmail.com', N'nhanvien01', N'123456', 1 )
INSERT INTO dbo.nhanvien VALUES  ( '',N'Trần Quốc Nam','0903542164', N'trinhminhduc2707@gmail.com', N'nhanvien02', N'123456', 1 )
INSERT INTO dbo.nhanvien VALUES  ( '',N'Vũ Trần Đại Dương','0904254658', N'huypqhps12867@fpt.edu.vn', N'nhanvien03', N'123456', 1 )
INSERT INTO dbo.nhanvien VALUES  ( '',N'Nguyễn Trọng Đại','0904257845', N'handdb2001@gmail.com', N'nhanvien04', N'123456', 1 )
INSERT INTO dbo.nhanvien VALUES  ( '',N'Vũ Trung Tín','0905245785', N'nghiabui1809@gmail.com', N'nhanvien05', N'123456', 1 )
INSERT INTO dbo.nhanvien VALUES  ( '',N'Nguyễn Nhật Trung','0908245136', N'hihuhu@gmail.com', N'nhanvien06', N'123456', 1 )
INSERT INTO dbo.nhanvien VALUES  ( '',N'Phạm Nhật Vượng','0922354658', N'ddvtan@gmail.com', N'nhanvien07', N'123456', 1 )
INSERT INTO dbo.nhanvien VALUES  ( '',N'Âu Dương Phong','0996587463', N'admin@gmail.com', N'admin', N'admin', 0 )
  


INSERT INTO dbo.dodung VALUES  ( '', N'MON AMI CHEWIE - XƯƠNG NƠ BẰNG DA BÒ TRẮNG 12.5CM', 35000,300 )
INSERT INTO dbo.dodung VALUES  ( '', N'MON AMI TOY - ĐỒ CHƠI BÁNH HAMBURGER', 24000,120 )
INSERT INTO dbo.dodung VALUES  ( '', N'MON AMI TOY - ĐỒ CHƠI BANH MẶT CHÓ', 20000, 400 )
INSERT INTO dbo.dodung VALUES  ( '', N'MON AMI TOY - ĐỒ CHƠI HOTDOG', 20000, 120 )
INSERT INTO dbo.dodung VALUES  ( '', N'MON AMI TOY - ĐỒ CHƠI ỐC QUẾ', 20000, 30 )
INSERT INTO dbo.dodung VALUES  ( '', N'MÁY XỊT TỰ ĐỘNG BIOION', 160000,25 )
INSERT INTO dbo.dodung VALUES  ( '', N'XỊT KHỬ MÙI BIOION 250ML', 90000,500 )
INSERT INTO dbo.dodung VALUES  ( '', N'VỆ SINH MẮT CHO CHÓ', 130000 ,360)
INSERT INTO dbo.dodung VALUES  ( '', N'VỆ SINH CHÓ ĐỰC NHỎ', 160000 ,147)
INSERT INTO dbo.dodung VALUES  ( '', N'MIẾNG LÓT THAN 35X45CM - MỸ', 300000 ,165)
INSERT INTO dbo.dodung VALUES  ( '', N'MIẾNG LÓT 35X45CM - MỸ', 270000, 300 )
INSERT INTO dbo.dodung VALUES  ( '', N'XUA ĐUỔI REPELL 400ML', 87000 , 100 )
INSERT INTO dbo.dodung VALUES  ( '', N'PHẤN KHỬ MÙI 250GR', 65000 , 260)
INSERT INTO dbo.dodung VALUES  ( '', N'VỆ SINH TAI CHO CHÓ', 130000,157 )
INSERT INTO dbo.dodung VALUES  ( '', N'DẦU TẮM BIO DERMA - TRỊ GHẺ VÀ NẤM DA 150ML', 47000 ,458)
INSERT INTO dbo.dodung VALUES  ( '', N'DẦU TẮM BIO DERMA - TRỊ GHẺ VÀ NẤM DA 200ML', 60000,157 )
INSERT INTO dbo.dodung VALUES  ( '', N'DẦU TẮM BIO CARE - TRỊ VE, RẬN, BỌ CHÉT CHO CHÓ MÈO 150ML', 35000,200 )
INSERT INTO dbo.dodung VALUES  ( '', N'DẦU TẮM BIO JULIE - DƯỠNG LÔNG MƯỢT CHO CHÓ MÈO 150ML', 35000,300 )
INSERT INTO dbo.dodung VALUES  ( '', N'DAU TAM KHÔ YÚ DẠNG XỊT 75ML', 170000,360 )
INSERT INTO dbo.dodung VALUES  ( '', N'DAU TAM EVERYDAY FRESH 474ML', 180000 ,450)
INSERT INTO dbo.dodung VALUES  ( '', N'DẦU TẮM HƯƠNG HOA TƯƠI OUR DOG 1L (ÚC)', 130000 ,528)
INSERT INTO dbo.dodung VALUES  ( '', N'XỊT KHỬ MÙI DƯỠNG LÔNG', 140000,340 )
INSERT INTO dbo.dodung VALUES  ('',N'DẦU GỘI VÀ XẢ CHÓ-HÀN QUỐC', 190000,240)
INSERT INTO dbo.dodung VALUES  ('',N'XẺNG HỐT PHÂN MÈO CITYZOO', 18000,214 )
INSERT INTO dbo.dodung VALUES  ('',N'CÁT MÈO GENKI 5L - NHẬT', 65000 ,350)
INSERT INTO dbo.dodung VALUES  ('',N'CÁT MÈO CAT-SEYE 5L', 70000 ,600)
INSERT INTO dbo.dodung VALUES  ('',N'CÁT THỦY TINH VÓN 4L', 150000 ,240)
INSERT INTO dbo.dodung VALUES  ('',N'CÁT MÈO ẤN ĐỘ 7.5KG', 110000,300)
INSERT INTO dbo.dodung VALUES  ('',N'VỆ SINH TAI CHO MÈO', 130000,400)
INSERT INTO dbo.dodung VALUES  ('',N'KHAY VỆ SINH LỚN LHK', 145000,410)
INSERT INTO dbo.dodung VALUES  ('',N'DẦU GỘI VÀ XẢ MÈO-HÀN QUỐC', 200000,450)
INSERT INTO dbo.dodung VALUES  ('',N'VỆ SINH MẮT CHO MÈO', 130000,120)
INSERT INTO dbo.dodung VALUES  ('',N'MON AMI ENZO - KÉO CẮT MÓNG', 24000,250)
INSERT INTO dbo.dodung VALUES  ('',N'MON AMI ENZO - KỀM CẮT MÓNG NHỎ', 48000,145)
INSERT INTO dbo.dodung VALUES  ('',N'MON AMI ENZO - KỀM CẮT MÓNG LỚN', 60000,360)
INSERT INTO dbo.dodung VALUES  ('',N'MON AMI TOY CAT - ĐỒ CHƠI CUỘN CHỈ', 23000,254)
INSERT INTO dbo.dodung VALUES  ('',N'MON AMI TOY - ĐỒ CHƠI MÈO CÂU CÁ', 24000,350)
INSERT INTO dbo.dodung VALUES  ('',N'ĐỒ CHƠI AFP LAM - CHUỘT', 50000,650)
INSERT INTO dbo.dodung VALUES  ('',N'MON AMI TOY CAT - ĐỒ CHƠI EVA',88000,350)
INSERT INTO dbo.dodung VALUES  ('',N'CẦN CÂU MÈO DÀI',28000,475)
INSERT INTO dbo.dodung VALUES  ('',N'BALO NHỰA PHI HÀNH GIA', 350000 ,855)
INSERT INTO dbo.dodung VALUES  ('',N'BALO DA VUÔNG', 400000,654)

INSERT INTO dbo.thucan VALUES  ( '',  N'SMARTHEART CHÓ CON 400GR','L001', 27000 ,200)
INSERT INTO dbo.thucan VALUES  ( '',  N'BRITISH SHORTHAIR KITTEN 400GR','L002', 122000 ,60)
INSERT INTO dbo.thucan VALUES  ( '',  N'BRITISH SHORTHAIR KITTEN 2KG','L002', 491000 ,52)
INSERT INTO dbo.thucan VALUES  ( '',  N'BRITISH SHORTHAIR ADULT 400GR','L002', 117000 ,47)
INSERT INTO dbo.thucan VALUES  ( '',  N'BRITISH SHORTHAIR ADULT 2KG','L002', 455000 ,410)
INSERT INTO dbo.thucan VALUES  ( '',  N'FITMINDOG FOR LIFE ADULT ALL BREEDS 15KG','L001', 870000,230 )
INSERT INTO dbo.thucan VALUES  ( '',  N'FITMINDOG FOR LIFE ADULT ALL BREEDS 3KG','L001', 175000 ,245)
INSERT INTO dbo.thucan VALUES  ( '',  N'FITMIN DOG FOR LIFE ADULT LARGE BREEDS 15KG','L001', 940000 ,321)
INSERT INTO dbo.thucan VALUES  ( '',  N'FITMIN DOG FOR LIFE JUNIOR LARGE BREEDS 15KG','L001', 945000 ,241)
INSERT INTO dbo.thucan VALUES  ( '',  N'FITMIN DOG FOR LIFE PUPPY ALL BREEDS 15KG','L001', 915000 ,361)
INSERT INTO dbo.thucan VALUES ( '',  N'FITMIN DOG FOR LIFE PUPPY ALL BREEDS 3KG','L001', 185000 ,320)
INSERT INTO dbo.thucan VALUES ( '',  N'FITMIN DOG FOOD MAXI PERFORMANCE 15KG','L001', 1150000 ,150)
INSERT INTO dbo.thucan VALUES ( '',  N'FITMIN DOG FOOD MAXI PUPPY 15KG','L001', 1280000 ,240)
INSERT INTO dbo.thucan VALUES ( '',  N'PATE CHÓ MONGE - Ý','L001', 26000 ,165)
INSERT INTO dbo.thucan VALUES ( '',  N'MINI JUNIOR 195GR','L001', 58000 ,256)
INSERT INTO dbo.thucan VALUES  ( '',  N'THỨC ĂN MÈO CAT-SEYE 13,5KG','L002', 1010000,102 )
INSERT INTO dbo.thucan VALUES ( '',  N'PERSIAN KITTEN 400GR','L002', 116000,201 )
INSERT INTO dbo.thucan VALUES  ( '',  N'FITMIN CAT FOR LIFE HAIRBALL 400GR','L002', 72000 ,145)
INSERT INTO dbo.thucan VALUES  ( '',  N'FITMIN CAT FOR LIFE SALMON 400GR','L002', 72000,165 )
INSERT INTO dbo.thucan VALUES ( '',  N'FITMIN CAT PURITY KITTEN 400GR','L002', 96000,241 )
INSERT INTO dbo.thucan VALUES  ( '',  N'FITMIN CAT PURITY INDOOR 400GR','L002', 96000 ,620)
INSERT INTO dbo.thucan VALUES  ( '',  N'INTENSE BEAUTY JELLY 85GR','L002', 32000 ,210)
INSERT INTO dbo.thucan VALUES  ( '',  N'KITTEN INSTINCTIVE 85GR','L002', 32000 ,155)
          

INSERT INTO dbo.pet VALUES  ( ''  ,  0.8 , 'L001' , 'G001' ,  5000000 ,1)
INSERT INTO dbo.pet VALUES ( ''  ,  0.6 , 'L001' , 'G001' ,  4000000 ,2)
INSERT INTO dbo.pet VALUES ( ''   ,  1.0 , 'L001' , 'G001' ,  6000000 ,3)
INSERT INTO dbo.pet VALUES ( ''  ,  0.3 , 'L001' , 'G002' ,  10000000 ,4)
INSERT INTO dbo.pet VALUES ( ''  ,  0.5 , 'L001' , 'G002' ,  14000000 ,1)
INSERT INTO dbo.pet VALUES ( ''  ,  0.7 , 'L001' , 'G002' ,  20000000 ,4)
INSERT INTO dbo.pet VALUES ( ''  ,  1.1 , 'L001' , 'G003' ,  14000000 ,4)
INSERT INTO dbo.pet VALUES ( ''  ,  2.0 , 'L001' , 'G003' ,  17000000 ,2)
INSERT INTO dbo.pet VALUES ( ''  ,  1.5 , 'L001' , 'G003' ,  10000000 ,5)
INSERT INTO dbo.pet VALUES( ''  ,  0.5 , 'L001' , 'G004' ,  20000000,5 )
INSERT INTO dbo.pet VALUES ( ''  ,  1.8 , 'L001' , 'G004' ,  14000000 ,3)
INSERT INTO dbo.pet VALUES( ''  ,  0.4 , 'L001' , 'G004' ,  9000000 ,2)
INSERT INTO dbo.pet VALUES( ''  ,  2.5 , 'L001' , 'G005' ,  12000000 ,1)
INSERT INTO dbo.pet VALUES( ''  ,  1.7 , 'L001' , 'G005' ,  8000000 ,4)
INSERT INTO dbo.pet VALUES( ''  ,  1.8 , 'L001' , 'G005' ,  10000000 ,2)
INSERT INTO dbo.pet VALUES( ''  ,  2.8 , 'L001' , 'G006' ,  4000000 ,1)
INSERT INTO dbo.pet VALUES( ''  ,  3.8 , 'L001' , 'G006' ,  7000000,5 )
INSERT INTO dbo.pet VALUES( ''  ,  1.5 , 'L001' , 'G006' ,  8000000 ,1)
INSERT INTO dbo.pet VALUES( ''  ,  1.4 , 'L001' , 'G007' ,  40000000 ,5)
INSERT INTO dbo.pet VALUES( ''  ,  2.8 , 'L001' , 'G007' ,  30000000 ,6)
INSERT INTO dbo.pet VALUES( ''  ,  0.8 , 'L001' , 'G007' ,  20000000 ,2)
INSERT INTO dbo.pet VALUES( '' ,  0.5 , 'L002' , 'G008' ,  5500000 ,3)
INSERT INTO dbo.pet VALUES( '' ,  0.2 , 'L002' , 'G008' ,  4000000 ,1)
INSERT INTO dbo.pet VALUES( '' ,  0.7 , 'L002' , 'G008' ,  6500000 ,5)
INSERT INTO dbo.pet VALUES( ''  ,  0.6 , 'L002' , 'G009' ,  1200000 ,2)
INSERT INTO dbo.pet VALUES( ''  ,  0.3 , 'L002' , 'G009' ,  1500000 ,3)
INSERT INTO dbo.pet VALUES( '' ,  1.0 , 'L002' , 'G009' ,  1000000 ,2)
INSERT INTO dbo.pet VALUES( ''  ,  1.2 , 'L002' , 'G010' ,  15500000 ,3)
INSERT INTO dbo.pet VALUES( ''  ,  0.3 , 'L002' , 'G010' ,  9000000 ,5)
INSERT INTO dbo.pet VALUES( ''  ,  0.9 , 'L002' , 'G010' ,  10000000 ,2)
INSERT INTO dbo.pet VALUES( ''  ,  0.5 , 'L002' , 'G011' ,  20000000 ,1)
INSERT INTO dbo.pet VALUES( '' ,  0.2 , 'L002' , 'G011' ,  23000000 ,2)
INSERT INTO dbo.pet VALUES( '' ,  0.8 , 'L002' , 'G011' ,  26000000,3 )
INSERT INTO dbo.pet VALUES( '' ,  1.3 , 'L002' , 'G012' ,  16000000,2 )
INSERT INTO dbo.pet VALUES( ''  ,  1.6 , 'L002' , 'G012' ,  15500000 ,2)
INSERT INTO dbo.pet VALUES( ''  ,  0.5 , 'L002' , 'G012' ,  10000000 ,1)
INSERT INTO dbo.pet VALUES( ''  ,  0.4 , 'L002' , 'G013' ,  25000000 ,2)
INSERT INTO dbo.pet VALUES( ''  ,  1.1 , 'L002' , 'G013' ,  50000000 ,3)
INSERT INTO dbo.pet VALUES( ''  ,  0.6 , 'L002' , 'G013' ,  45000000 ,2)
INSERT INTO dbo.pet VALUES( ''  ,  0.4 , 'L003' , 'G014' , 100000  ,1)
INSERT INTO dbo.pet VALUES( ''  ,  0.4 , 'L003' , 'G014' ,  100000 ,5)
INSERT INTO dbo.pet VALUES( '' ,  0.6 , 'L003' , 'G014' ,  120000 ,2)
INSERT INTO dbo.pet VALUES( ''  ,  0.6 , 'L003' , 'G015' ,  120000 ,3)
INSERT INTO dbo.pet VALUES( ''  ,  0.6 , 'L003' , 'G015' ,  120000 ,2)
INSERT INTO dbo.pet VALUES( ''  ,  0.6 , 'L003' , 'G016' ,  120000 ,1)
INSERT INTO dbo.pet VALUES( ''  ,  0.8 , 'L003' , 'G016' ,  200000,2 )
INSERT INTO dbo.pet VALUES( '' ,  0.8 , 'L003' , 'G016' ,  200000 ,2)
INSERT INTO dbo.pet VALUES( ''  ,  0.8 , 'L003' , 'G016' ,  200000 ,3)

INSERT INTO dbo.hoadon VALUES('','20191206','NV001','KH001')
INSERT INTO dbo.hoadon VALUES('','20180312','NV003','KH004')
INSERT INTO dbo.hoadon VALUES('','20160523','NV004','KH005')
INSERT INTO dbo.hoadon VALUES('','20190920','NV001','KH001')
INSERT INTO dbo.hoadon VALUES('','20190326','NV005','KH003')
INSERT INTO dbo.hoadon VALUES('','20190825','NV001','KH002')
INSERT INTO dbo.hoadon VALUES('','20190413','NV006','KH006')
INSERT INTO dbo.hoadon VALUES('','20190623','NV007','KH007')
INSERT INTO dbo.hoadon VALUES('','20190724','NV002','KH008')
INSERT INTO dbo.hoadon VALUES('','20190625','NV006','KH009')
INSERT INTO dbo.hoadon VALUES('','20190324','NV004','KH010')
INSERT INTO dbo.hoadon VALUES('','20190124','NV006','KH011')
INSERT INTO dbo.hoadon VALUES('','20190610','NV005','KH012')
INSERT INTO dbo.hoadon VALUES('','20191023','NV006','KH013')
INSERT INTO dbo.hoadon VALUES('','20190214','NV004','KH014')
INSERT INTO dbo.hoadon VALUES('','20190324','NV003','KH015')
INSERT INTO dbo.hoadon VALUES('','20190401','NV001','KH016')
INSERT INTO dbo.hoadon VALUES('','20190312','NV002','KH017')
INSERT INTO dbo.hoadon VALUES('','20190710','NV006','KH018')
INSERT INTO dbo.hoadon VALUES('','20190324','NV001','KH019')
INSERT INTO dbo.hoadon VALUES('','20181212','NV003','KH020')
INSERT INTO dbo.hoadon VALUES('','20181026','NV006','KH021')
INSERT INTO dbo.hoadon VALUES('','20190206','NV004','KH022')
INSERT INTO dbo.hoadon VALUES('','20190622','NV002','KH004')
INSERT INTO dbo.hoadon VALUES('','20190405','NV006','KH015')
INSERT INTO dbo.hoadon VALUES('','20190610','NV007','KH016')


INSERT INTO dbo.hdchitiet VALUES('HD001','PET001',1,'TA001',2,'DD003',1)
INSERT INTO dbo.hdchitiet VALUES('HD002','PET003',1,'TA003',1,'DD002',1)
INSERT INTO dbo.hdchitiet VALUES('HD003','PET002',1,'TA002',2,'DD003',1)
INSERT INTO dbo.hdchitiet VALUES('HD004','PET004',1,'TA004',2,'DD005',1)
INSERT INTO dbo.hdchitiet VALUES('HD005','PET006',1,'TA006',2,'DD008',1)
INSERT INTO dbo.hdchitiet VALUES('HD006','PET005',1,'TA002',2,'DD009',1)
INSERT INTO dbo.hdchitiet VALUES('HD007','PET008',1,'TA004',2,'DD004',1)
INSERT INTO dbo.hdchitiet VALUES('HD008','PET007',1,'TA006',2,'DD004',1)
INSERT INTO dbo.hdchitiet VALUES('HD009','PET010',1,'TA009',2,'DD005',1)
INSERT INTO dbo.hdchitiet VALUES('HD010','PET009',1,'TA010',1,'DD007',1)
INSERT INTO dbo.hdchitiet VALUES('HD011','PET011',1,'TA006',1,'DD006',1)
INSERT INTO dbo.hdchitiet VALUES('HD012','PET013',1,'TA015',1,'DD003',1)
INSERT INTO dbo.hdchitiet VALUES('HD013','PET012',1,'TA007',1,'DD002',1)
INSERT INTO dbo.hdchitiet VALUES('HD014','PET015',1,'TA009',1,'DD006',1)
INSERT INTO dbo.hdchitiet VALUES('HD015','PET014',1,'TA004',2,'DD010',1)
INSERT INTO dbo.hdchitiet VALUES('HD016','PET016',1,'TA006',2,'DD012',1)
INSERT INTO dbo.hdchitiet VALUES('HD017','PET018',1,'TA014',2,'DD016',1)
INSERT INTO dbo.hdchitiet VALUES('HD018','PET017',1,'TA010',2,'DD018',1)
INSERT INTO dbo.hdchitiet VALUES('HD019','PET020',1,'TA005',3,'DD014',1)
INSERT INTO dbo.hdchitiet VALUES('HD020','PET021',1,'TA013',3,'DD016',1)
INSERT INTO dbo.hdchitiet VALUES('HD021','PET019',1,'TA005',1,'DD011',1)
INSERT INTO dbo.hdchitiet VALUES('HD022','PET023',1,'TA017',1,'DD012',1)
INSERT INTO dbo.hdchitiet VALUES('HD023','PET022',1,'TA002',2,'DD025',1)
INSERT INTO dbo.hdchitiet VALUES('HD024','PET024',1,'TA008',2,'DD026',1)
INSERT INTO dbo.hdchitiet VALUES('HD025','PET026',1,'TA007',1,'DD028',1)
INSERT INTO dbo.hdchitiet VALUES('HD026','PET028',1,'TA004',1,'DD028',1)




INSERT INTO dbo.hoadonnhaphang VALUES('','NCC001','20190120')
INSERT INTO dbo.hoadonnhaphang VALUES('','NCC001','20190125')
INSERT INTO dbo.hoadonnhaphang VALUES('','NCC001','20190126')
INSERT INTO dbo.hoadonnhaphang VALUES('','NCC001','20190127')
INSERT INTO dbo.hoadonnhaphang VALUES('','NCC001','20190128')
INSERT INTO dbo.hoadonnhaphang VALUES('','NCC001','20190129')
INSERT INTO dbo.hoadonnhaphang VALUES('','NCC001','20190129')
INSERT INTO dbo.hoadonnhaphang VALUES('','NCC001','20190130')
INSERT INTO dbo.hoadonnhaphang VALUES('','NCC001','20190130')
INSERT INTO dbo.hoadonnhaphang VALUES('','NCC001','20190130')
INSERT INTO dbo.hoadonnhaphang VALUES('','NCC001','20190130')
INSERT INTO dbo.hoadonnhaphang VALUES('','NCC001','20190516')
INSERT INTO dbo.hoadonnhaphang VALUES('','NCC001','20190516')
INSERT INTO dbo.hoadonnhaphang VALUES('','NCC001','20190516')
INSERT INTO dbo.hoadonnhaphang VALUES('','NCC001','20190516')
INSERT INTO dbo.hoadonnhaphang VALUES('','NCC001','20190516')

INSERT INTO dbo.hoadonnhaphang VALUES('','NCC002','20190610')
INSERT INTO dbo.hoadonnhaphang VALUES('','NCC002','20190610')
INSERT INTO dbo.hoadonnhaphang VALUES('','NCC002','20190610')
INSERT INTO dbo.hoadonnhaphang VALUES('','NCC002','20190610')
INSERT INTO dbo.hoadonnhaphang VALUES('','NCC002','20190625')
INSERT INTO dbo.hoadonnhaphang VALUES('','NCC002','20190625')
INSERT INTO dbo.hoadonnhaphang VALUES('','NCC002','20190625')
INSERT INTO dbo.hoadonnhaphang VALUES('','NCC002','20190625')

INSERT INTO dbo.hoadonnhaphang VALUES('','NCC003','20190115')
INSERT INTO dbo.hoadonnhaphang VALUES('','NCC003','20190115')
INSERT INTO dbo.hoadonnhaphang VALUES('','NCC003','20190115')
INSERT INTO dbo.hoadonnhaphang VALUES('','NCC003','20190115')
INSERT INTO dbo.hoadonnhaphang VALUES('','NCC003','20190115')
INSERT INTO dbo.hoadonnhaphang VALUES('','NCC003','20190115')
INSERT INTO dbo.hoadonnhaphang VALUES('','NCC003','20190216')
INSERT INTO dbo.hoadonnhaphang VALUES('','NCC003','20190216')
INSERT INTO dbo.hoadonnhaphang VALUES('','NCC003','20190216')
INSERT INTO dbo.hoadonnhaphang VALUES('','NCC003','20190216')
INSERT INTO dbo.hoadonnhaphang VALUES('','NCC003','20190216')
INSERT INTO dbo.hoadonnhaphang VALUES('','NCC003','20190216')
INSERT INTO dbo.hoadonnhaphang VALUES('','NCC003','20190216')
INSERT INTO dbo.hoadonnhaphang VALUES('','NCC003','20190216')


INSERT INTO dbo.hdnhaphangchitiet VALUES('HDN001','PET001','TA001','DD005',1,10,1)
INSERT INTO dbo.hdnhaphangchitiet VALUES('HDN002','PET002','TA002','DD002',1,5,1)
INSERT INTO dbo.hdnhaphangchitiet VALUES('HDN003','PET003','TA003','DD004',1,4,1)
INSERT INTO dbo.hdnhaphangchitiet VALUES('HDN004','PET004','TA004','DD005',1,5,1)
INSERT INTO dbo.hdnhaphangchitiet VALUES('HDN005','PET005','TA005','DD008',1,2,1)
INSERT INTO dbo.hdnhaphangchitiet VALUES('HDN006','PET006','TA006','DD006',1,4,1)
INSERT INTO dbo.hdnhaphangchitiet VALUES('HDN007','PET007','TA007','DD004',1,10,1)
INSERT INTO dbo.hdnhaphangchitiet VALUES('HDN008','PET008','TA008','DD007',1,10,1)
INSERT INTO dbo.hdnhaphangchitiet VALUES('HDN009','PET009','TA009','DD005',1,7,1)
INSERT INTO dbo.hdnhaphangchitiet VALUES('HDN010','PET010','TA010','DD002',1,8,1)
INSERT INTO dbo.hdnhaphangchitiet VALUES('HDN011','PET011','TA011','DD001',1,9,1)
INSERT INTO dbo.hdnhaphangchitiet VALUES('HDN012','PET012','TA012','DD003',1,7,1)
INSERT INTO dbo.hdnhaphangchitiet VALUES('HDN013','PET013','TA015','DD004',1,5,1)
INSERT INTO dbo.hdnhaphangchitiet VALUES('HDN014','PET014','TA013','DD005',1,2,1)
INSERT INTO dbo.hdnhaphangchitiet VALUES('HDN015','PET015','TA014','DD007',1,10,1)
INSERT INTO dbo.hdnhaphangchitiet VALUES('HDN016','PET016','TA016','DD002',1,10,1)
INSERT INTO dbo.hdnhaphangchitiet VALUES('HDN017','PET017','TA018','DD004',1,10,1)

INSERT INTO dbo.hdnhaphangchitiet VALUES('HDN018','PET007','TA017','DD005',1,10,2)
INSERT INTO dbo.hdnhaphangchitiet VALUES('HDN019','PET013','TA019','DD006',1,10,2)
INSERT INTO dbo.hdnhaphangchitiet VALUES('HDN020','PET015','TA020','DD008',1,10,2)
INSERT INTO dbo.hdnhaphangchitiet VALUES('HDN021','PET009','TA021','DD005',1,10,3)
INSERT INTO dbo.hdnhaphangchitiet VALUES('HDN022','PET005','TA022','DD005',1,10,1)
INSERT INTO dbo.hdnhaphangchitiet VALUES('HDN023','PET003','TA022','DD006',1,10,3)
INSERT INTO dbo.hdnhaphangchitiet VALUES('HDN024','PET003','TA023','DD004',1,10,2)
INSERT INTO dbo.hdnhaphangchitiet VALUES('HDN025','PET017','TA020','DD002',1,10,1)

INSERT INTO dbo.hdnhaphangchitiet VALUES('HDN026','PET001','TA015','DD008',1,5,2)
INSERT INTO dbo.hdnhaphangchitiet VALUES('HDN027','PET002','TA010','DD009',1,2,3)
INSERT INTO dbo.hdnhaphangchitiet VALUES('HDN028','PET003','TA021','DD006',1,5,5)
INSERT INTO dbo.hdnhaphangchitiet VALUES('HDN029','PET004','TA014','DD001',1,10,1)
INSERT INTO dbo.hdnhaphangchitiet VALUES('HDN030','PET005','TA009','DD005',1,1,1)
INSERT INTO dbo.hdnhaphangchitiet VALUES('HDN031','PET006','TA018','DD004',1,6,2)
INSERT INTO dbo.hdnhaphangchitiet VALUES('HDN032','PET007','TA014','DD002',1,2,3)
INSERT INTO dbo.hdnhaphangchitiet VALUES('HDN033','PET008','TA022','DD006',1,4,5)
INSERT INTO dbo.hdnhaphangchitiet VALUES('HDN034','PET009','TA019','DD007',1,6,2)
INSERT INTO dbo.hdnhaphangchitiet VALUES('HDN035','PET010','TA016','DD008',1,2,2)
INSERT INTO dbo.hdnhaphangchitiet VALUES('HDN036','PET015','TA011','DD009',1,5,1)
INSERT INTO dbo.hdnhaphangchitiet VALUES('HDN037','PET019','TA021','DD004',1,6,6)
INSERT INTO dbo.hdnhaphangchitiet VALUES('HDN038','PET012','TA004','DD002',1,2,2)

INSERT INTO dbo.dodungnhap VALUES  ( '',30000 )
INSERT INTO dbo.dodungnhap VALUES  ( '',20000 )
INSERT INTO dbo.dodungnhap VALUES  ( '',17000 )
INSERT INTO dbo.dodungnhap VALUES  ( '',17000 )
INSERT INTO dbo.dodungnhap VALUES  ( '',17000 )
INSERT INTO dbo.dodungnhap VALUES  ( '',13000 )
INSERT INTO dbo.dodungnhap VALUES  ( '',85000 )
INSERT INTO dbo.dodungnhap VALUES  ( '',120000 )
INSERT INTO dbo.dodungnhap VALUES  ( '',150000 )
INSERT INTO dbo.dodungnhap VALUES  ( '',280000 )
INSERT INTO dbo.dodungnhap VALUES  ( '',250000 )
INSERT INTO dbo.dodungnhap VALUES  ( '',80000 )
INSERT INTO dbo.dodungnhap VALUES  ( '',60000 )
INSERT INTO dbo.dodungnhap VALUES  ( '',125000 )
INSERT INTO dbo.dodungnhap VALUES  ( '',38000 )
INSERT INTO dbo.dodungnhap VALUES  ( '',55000 )
INSERT INTO dbo.dodungnhap VALUES  ( '',30000 )
INSERT INTO dbo.dodungnhap VALUES  ( '',30000 )
INSERT INTO dbo.dodungnhap VALUES  ( '',150000 )
INSERT INTO dbo.dodungnhap VALUES  ( '',170000 )
INSERT INTO dbo.dodungnhap VALUES  ( '',125000 )
INSERT INTO dbo.dodungnhap VALUES  ( '',130000 )
INSERT INTO dbo.dodungnhap VALUES  ( '',180000 )
INSERT INTO dbo.dodungnhap VALUES  ( '',165000 )
INSERT INTO dbo.dodungnhap VALUES  ( '',60000 )
INSERT INTO dbo.dodungnhap VALUES  ( '',65000 )
INSERT INTO dbo.dodungnhap VALUES  ( '',140000 )
INSERT INTO dbo.dodungnhap VALUES  ( '',105000 )
INSERT INTO dbo.dodungnhap VALUES  ( '',120000 )
INSERT INTO dbo.dodungnhap VALUES  ( '',138000 )
INSERT INTO dbo.dodungnhap VALUES  ( '',180000 )
INSERT INTO dbo.dodungnhap VALUES  ( '',115000 )
INSERT INTO dbo.dodungnhap VALUES  ( '',20000 )
INSERT INTO dbo.dodungnhap VALUES  ( '',40000 )
INSERT INTO dbo.dodungnhap VALUES  ( '',55000 )
INSERT INTO dbo.dodungnhap VALUES  ( '',20000 )
INSERT INTO dbo.dodungnhap VALUES  ( '',20000 )
INSERT INTO dbo.dodungnhap VALUES  ( '',45000 )
INSERT INTO dbo.dodungnhap VALUES  ( '',80000 )
INSERT INTO dbo.dodungnhap VALUES  ( '',23000 )
INSERT INTO dbo.dodungnhap VALUES  ( '',30000 )
INSERT INTO dbo.dodungnhap VALUES  ( '',35000 )

INSERT INTO dbo.thucannhap VALUES  ( '',25000 )
INSERT INTO dbo.thucannhap VALUES  ( '',120000 )
INSERT INTO dbo.thucannhap VALUES  ( '',480000 )
INSERT INTO dbo.thucannhap VALUES  ( '',110000 )
INSERT INTO dbo.thucannhap VALUES  ( '',445000 )
INSERT INTO dbo.thucannhap VALUES  ( '',850000 )
INSERT INTO dbo.thucannhap VALUES  ( '',170000 )
INSERT INTO dbo.thucannhap VALUES  ( '',910000 )
INSERT INTO dbo.thucannhap VALUES  ( '',920000 )
INSERT INTO dbo.thucannhap VALUES  ( '',900000 )
INSERT INTO dbo.thucannhap VALUES  ( '',180000 )
INSERT INTO dbo.thucannhap VALUES  ( '',1100000 )
INSERT INTO dbo.thucannhap VALUES  ( '',1250000 )
INSERT INTO dbo.thucannhap VALUES  ( '',23000 )
INSERT INTO dbo.thucannhap VALUES  ( '',50000 )
INSERT INTO dbo.thucannhap VALUES  ( '',1000000 )
INSERT INTO dbo.thucannhap VALUES  ( '',110000 )
INSERT INTO dbo.thucannhap VALUES  ( '',70000 )
INSERT INTO dbo.thucannhap VALUES  ( '',70000 )
INSERT INTO dbo.thucannhap VALUES  ( '',90000 )
INSERT INTO dbo.thucannhap VALUES  ( '',90000 )
INSERT INTO dbo.thucannhap VALUES  ( '',27000 )
INSERT INTO dbo.thucannhap VALUES  ( '',27000 )

INSERT INTO dbo.petnhap VALUES  ( '',4500000 )
INSERT INTO dbo.petnhap VALUES  ( '',3900000 )
INSERT INTO dbo.petnhap VALUES  ( '',5700000 )
INSERT INTO dbo.petnhap VALUES  ( '',9500000 )
INSERT INTO dbo.petnhap VALUES  ( '',12000000 )
INSERT INTO dbo.petnhap VALUES  ( '',18000000 )
INSERT INTO dbo.petnhap VALUES  ( '',12000000 )
INSERT INTO dbo.petnhap VALUES  ( '',15000000 )
INSERT INTO dbo.petnhap VALUES  ( '',9600000 )
INSERT INTO dbo.petnhap VALUES  ( '',17000000 )
INSERT INTO dbo.petnhap VALUES  ( '',12000000 )
INSERT INTO dbo.petnhap VALUES  ( '',8700000 )
INSERT INTO dbo.petnhap VALUES  ( '',11000000 )
INSERT INTO dbo.petnhap VALUES  ( '',7800000 )
INSERT INTO dbo.petnhap VALUES  ( '',9800000 )
INSERT INTO dbo.petnhap VALUES  ( '',3800000 )
INSERT INTO dbo.petnhap VALUES  ( '',6800000 )
INSERT INTO dbo.petnhap VALUES  ( '',7800000 )
INSERT INTO dbo.petnhap VALUES  ( '',36000000 )
INSERT INTO dbo.petnhap VALUES  ( '',27000000 )
INSERT INTO dbo.petnhap VALUES  ( '',18000000 )
INSERT INTO dbo.petnhap VALUES  ( '',5000000 )
INSERT INTO dbo.petnhap VALUES  ( '',3800000 )
INSERT INTO dbo.petnhap VALUES  ( '',6000000 )
INSERT INTO dbo.petnhap VALUES  ( '',1000000 )
INSERT INTO dbo.petnhap VALUES  ( '',1000000 )
INSERT INTO dbo.petnhap VALUES  ( '',990000 )
INSERT INTO dbo.petnhap VALUES  ( '',14000000 )
INSERT INTO dbo.petnhap VALUES  ( '',8000000 )
INSERT INTO dbo.petnhap VALUES  ( '',9900000 )
INSERT INTO dbo.petnhap VALUES  ( '',18000000 )
INSERT INTO dbo.petnhap VALUES  ( '',21000000 )
INSERT INTO dbo.petnhap VALUES  ( '',25000000 )
INSERT INTO dbo.petnhap VALUES  ( '',13000000 )
INSERT INTO dbo.petnhap VALUES  ( '',15000000 )
INSERT INTO dbo.petnhap VALUES  ( '',9800000 )
INSERT INTO dbo.petnhap VALUES  ( '',23000000 )
INSERT INTO dbo.petnhap VALUES  ( '',45000000 )
INSERT INTO dbo.petnhap VALUES  ( '',40000000 )
INSERT INTO dbo.petnhap VALUES  ( '',9800000 )
INSERT INTO dbo.petnhap VALUES  ( '',9600000 )
INSERT INTO dbo.petnhap VALUES  ( '',11000000 )
INSERT INTO dbo.petnhap VALUES  ( '',11800000 )
INSERT INTO dbo.petnhap VALUES  ( '',11500000 )
INSERT INTO dbo.petnhap VALUES  ( '',11000000 )
INSERT INTO dbo.petnhap VALUES  ( '',18000000 )
INSERT INTO dbo.petnhap VALUES  ( '',18000000 )
INSERT INTO dbo.petnhap VALUES  ( '',18000000 )







