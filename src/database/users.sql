
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


DROP TABLE IF EXISTS `up`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `up` (
  `my_row_id` bigint unsigned NOT NULL AUTO_INCREMENT /*!80023 INVISIBLE */,
  `username` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `role` varchar(100) COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'N/A',
  `name` varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `gender` varchar(8) COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'N/A',
  `dob` date NOT NULL DEFAULT '1900-01-01',
  `province` varchar(30) COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'Unknown',
  `cpa` double NOT NULL DEFAULT '0',
  `university` varchar(200) COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'Unknown',
  PRIMARY KEY (`my_row_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


LOCK TABLES `up` WRITE;
/*!40000 ALTER TABLE `up` DISABLE KEYS */;
INSERT INTO `up` (`my_row_id`, `username`, `password`, `role`, `name`, `email`, `gender`, `dob`, `province`, `cpa`, `university`) VALUES 
      (1,'anhtuan','12345678','N/A','','','N/A','1900-01-01','Unknown',0,'Unknown'),
          (2,'CanhChau','Chau2002','Admin','Phùng Cảnh Châu','ChauPC207108@sis.hust.edu.vn','Male','2002-02-26','Nghệ An',2.96,'HUST - Hanoi University of Science & Technology'),
              (3,'DinhMinh','minhdm120','Admin','Vương Đình Minh','Minh.VD210610@sis.hust.edu.vn','Male','2003-07-19','Nghệ An',4,'HUST - Hanoi University of Science & Technology'),
                  (4,'duchieu123','tuânncut','N/A','Đức Hiếu','Hieu.ND215370@sis.hust.edu.vn','Male','2004-02-13','Hà Nội',3.01,'HUST - Hanoi University of Science & Technology'),
                      (5,'HoangLam','1234','Admin','Nguyễn Hoàng Lâm','Lam.NH210517@sis.hust.edu.vn','Unknown','2003-11-02','Nghệ An',3.89,'HUST - Hanoi University of Science & Technology'),
                          (6,'hoanglinh','abcd1234','Admin','','','N/A','1900-01-01','Unknown',0,'Unknown'),
                              (7,'Lamquang','123456q','Admin','n m quang','abc@gmail.com','Male','2003-04-14','Ninh Binh',4,'HUST - Hanoi University of Science & Technology'),
                                  (8,'MinhQuang','20215461','N/A','Minh Quang','Quang.NM215461@sis.hust.edu.vn','Male','2003-04-14','Ninh Bình',2.3,'HUST - Hanoi University of Science & Technology'),
                                      (9,'QuocTuan','20210891','N/A','Quốc Tuấn','Tuan.DQ210891@sis.hust.edu.vn','Male','2003-11-24','Bắc Ninh',3.45,'HUST - Hanoi University of Science & Technology'),
                                          (10,'QuocTuan1','1111','N/A','1342','122','N/A','1900-01-01','Unknown',0,'Unknown'),
                                              (11,'SyTrong','1234','Admin','Nguyễn Sỹ Trọng','TrongNS210866@sis.hust.edu.vn','Male','2003-03-26','Nghệ An',4,'Unknown'),
                                                  (12,'SyTrong1','12345','N/A','Nguyễn Sỹ Trọng','sytrong2603@gmail.com','Female','1900-01-01','Cà Mau',0.55,'Unknown'),
                                                      (13,'thienan','abcdef12','N/A','','','N/A','1900-01-01','Unknown',0,'Unknown'),
                                                          (14,'thuylinh','12345679','N/A','','','N/A','1900-01-01','Unknown',0,'Unknown'),
                                                              (15,'TrongTuan','20210906','N/A','Trọng Tuấn','Tuan.NT210906@sis.hust.edu.vn','Male','2003-02-04','Nghệ An',3.79,'FTU - Foreign Trade University'),
                                                                  (16,'TrungHieu','T214898','N/A','Phạm Trung Hiếu','HieuPT2148982@sis.hust.edu.vn','Male','1900-01-01','Hưng Yên',3.23,'Unknown'),
                                                                      (17,'Tuanminh1907','a1234567','N/A','Trần Tuấn Minh','trantuanminh1907@gmail.com','Male','2003-07-19','Hà Nội',3.55,'HUST - Hanoi University of Science & Technology'),
                                                                          (18,'VietAnh','V210041','N/A','Trịnh Thăng Việt Anh','AnhTTV210041@sis.hust.edu.vn','N/A','1900-01-01','Unknown',0,'Unknown'),
      (19,'Lamm_al0','123lam123','N/A','Nguyễn Hoàng Lâm','hoanglam02qh@gmail.com','Male','2023-02-11','Hà Nội',1.8,'HUST - Hanoi University of Science & Technology'),
  (20,'Tuetue','T111','N/A','Lê Đình Trí Tuệ','TueLDT@sis.hust.edu.vn','Male','2003-04-24','Hà Tĩnh',3.4,'HUST - Hanoi University of Science & Technology'),
  (21,'ngTriDat09','a1111','N/A','Nguyễn Trí Đạt','TriDat@sis.hust.edu.vn','Male','2003-12-18','Nghệ An',2.2,'HNUE - Hanoi National University of Education');
/*!40000 ALTER TABLE `up` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
