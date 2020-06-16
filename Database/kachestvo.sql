-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 15 Haz 2020, 22:17:36
-- Sunucu sürümü: 10.4.8-MariaDB
-- PHP Sürümü: 7.1.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `kachestvo`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `comments`
--

CREATE TABLE `comments` (
  `Id` int(11) NOT NULL,
  `Name` varchar(25) COLLATE utf8_bin DEFAULT NULL,
  `Comment` text COLLATE utf8_bin NOT NULL,
  `Created_At` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `emails`
--

CREATE TABLE `emails` (
  `Id` int(11) NOT NULL,
  `Name` varchar(25) COLLATE utf8_bin NOT NULL,
  `Email` varchar(50) COLLATE utf8_bin NOT NULL,
  `Subject` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `next`
--

CREATE TABLE `next` (
  `Id` int(11) NOT NULL,
  `Next` varchar(1) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Tablo döküm verisi `next`
--

INSERT INTO `next` (`Id`, `Next`) VALUES
(1, '1'),
(2, '1'),
(3, '1');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `previous`
--

CREATE TABLE `previous` (
  `Id` int(11) NOT NULL,
  `Previous` varchar(1) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Tablo döküm verisi `previous`
--

INSERT INTO `previous` (`Id`, `Previous`) VALUES
(1, '1');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `slider`
--

CREATE TABLE `slider` (
  `Id` int(11) NOT NULL,
  `Name` varchar(50) COLLATE utf8_bin NOT NULL,
  `Description` text COLLATE utf8_bin NOT NULL,
  `Image` varchar(120) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Tablo döküm verisi `slider`
--

INSERT INTO `slider` (`Id`, `Name`, `Description`, `Image`) VALUES
(1, 'EFQM - 1', '“EFQM Mükemmellik Modeli”; Avrupa Kalite Yönetimi Vakfı tarafından oluşturulmuş bir Kalite Yönetim Modelidir.\r\nEFQM Mükemmellik Modeli, kuruluşların mükemmellik yolunda ilerleyip ilerlemediklerini ölçerek, kuvvetli yönlerini ve iyileştirmeye açık alanlarını görmelerini sağlayan,  uygun çözümler bulmalarında rehberlik eden, bir öz değerlendirme aracıdır. kalitenin kabulünü hızlandırarak ve kalite geliştirme aktivitelerini destekleyerek dünya pazarında Avrupa şirketlerinin yerini güçlendirmektir.\r\nEFQM’de esas alınan öz değerlendirme modeline göre, müşteri tatmini, çalışanların tatmini ve toplum üzerindeki etki süreçlerin uygun bir liderlik anlayışı ile yönetilmesiyle sağlanabilir ve böylece iş sonuçlarında mükemmelliğe ulaşılabilir\r\n', '1.jpg'),
(3, 'EFQM - 2', '“EFQM Mükemmellik Modeli”; Avrupa Kalite Yönetimi Vakfı tarafından oluşturulmuş bir Kalite Yönetim Modelidir.\r\nEFQM Mükemmellik Modeli, kuruluşların mükemmellik yolunda ilerleyip ilerlemediklerini ölçerek, kuvvetli yönlerini ve iyileştirmeye açık alanlarını görmelerini sağlayan,  uygun çözümler bulmalarında rehberlik eden, bir öz değerlendirme aracıdır. kalitenin kabulünü hızlandırarak ve kalite geliştirme aktivitelerini destekleyerek dünya pazarında Avrupa şirketlerinin yerini güçlendirmektir.\r\nEFQM’de esas alınan öz değerlendirme modeline göre, müşteri tatmini, çalışanların tatmini ve toplum üzerindeki etki süreçlerin uygun bir liderlik anlayışı ile yönetilmesiyle sağlanabilir ve böylece iş sonuçlarında mükemmelliğe ulaşılabilir\r\n', '2.jpg'),
(4, 'EFQM - 3', '“EFQM Mükemmellik Modeli”; Avrupa Kalite Yönetimi Vakfı tarafından oluşturulmuş bir Kalite Yönetim Modelidir.\r\nEFQM Mükemmellik Modeli, kuruluşların mükemmellik yolunda ilerleyip ilerlemediklerini ölçerek, kuvvetli yönlerini ve iyileştirmeye açık alanlarını görmelerini sağlayan,  uygun çözümler bulmalarında rehberlik eden, bir öz değerlendirme aracıdır. kalitenin kabulünü hızlandırarak ve kalite geliştirme aktivitelerini destekleyerek dünya pazarında Avrupa şirketlerinin yerini güçlendirmektir.\r\nEFQM’de esas alınan öz değerlendirme modeline göre, müşteri tatmini, çalışanların tatmini ve toplum üzerindeki etki süreçlerin uygun bir liderlik anlayışı ile yönetilmesiyle sağlanabilir ve böylece iş sonuçlarında mükemmelliğe ulaşılabilir\r\n', '3.jpg'),
(5, 'EFQM - 4', '“EFQM Mükemmellik Modeli”; Avrupa Kalite Yönetimi Vakfı tarafından oluşturulmuş bir Kalite Yönetim Modelidir.\r\nEFQM Mükemmellik Modeli, kuruluşların mükemmellik yolunda ilerleyip ilerlemediklerini ölçerek, kuvvetli yönlerini ve iyileştirmeye açık alanlarını görmelerini sağlayan,  uygun çözümler bulmalarında rehberlik eden, bir öz değerlendirme aracıdır. kalitenin kabulünü hızlandırarak ve kalite geliştirme aktivitelerini destekleyerek dünya pazarında Avrupa şirketlerinin yerini güçlendirmektir.\r\nEFQM’de esas alınan öz değerlendirme modeline göre, müşteri tatmini, çalışanların tatmini ve toplum üzerindeki etki süreçlerin uygun bir liderlik anlayışı ile yönetilmesiyle sağlanabilir ve böylece iş sonuçlarında mükemmelliğe ulaşılabilir\r\n', '4.jpg'),
(6, 'EFQM- 5', '“EFQM Mükemmellik Modeli”; Avrupa Kalite Yönetimi Vakfı tarafından oluşturulmuş bir Kalite Yönetim Modelidir.\r\nEFQM Mükemmellik Modeli, kuruluşların mükemmellik yolunda ilerleyip ilerlemediklerini ölçerek, kuvvetli yönlerini ve iyileştirmeye açık alanlarını görmelerini sağlayan,  uygun çözümler bulmalarında rehberlik eden, bir öz değerlendirme aracıdır. kalitenin kabulünü hızlandırarak ve kalite geliştirme aktivitelerini destekleyerek dünya pazarında Avrupa şirketlerinin yerini güçlendirmektir.\r\nEFQM’de esas alınan öz değerlendirme modeline göre, müşteri tatmini, çalışanların tatmini ve toplum üzerindeki etki süreçlerin uygun bir liderlik anlayışı ile yönetilmesiyle sağlanabilir ve böylece iş sonuçlarında mükemmelliğe ulaşılabilir\r\n', '5.jpg'),
(7, 'EFQM - 6', '“EFQM Mükemmellik Modeli”; Avrupa Kalite Yönetimi Vakfı tarafından oluşturulmuş bir Kalite Yönetim Modelidir.\r\nEFQM Mükemmellik Modeli, kuruluşların mükemmellik yolunda ilerleyip ilerlemediklerini ölçerek, kuvvetli yönlerini ve iyileştirmeye açık alanlarını görmelerini sağlayan,  uygun çözümler bulmalarında rehberlik eden, bir öz değerlendirme aracıdır. kalitenin kabulünü hızlandırarak ve kalite geliştirme aktivitelerini destekleyerek dünya pazarında Avrupa şirketlerinin yerini güçlendirmektir.\r\nEFQM’de esas alınan öz değerlendirme modeline göre, müşteri tatmini, çalışanların tatmini ve toplum üzerindeki etki süreçlerin uygun bir liderlik anlayışı ile yönetilmesiyle sağlanabilir ve böylece iş sonuçlarında mükemmelliğe ulaşılabilir\r\n', '6.jpg'),
(8, 'EFQM - 7', '“EFQM Mükemmellik Modeli”; Avrupa Kalite Yönetimi Vakfı tarafından oluşturulmuş bir Kalite Yönetim Modelidir.\r\nEFQM Mükemmellik Modeli, kuruluşların mükemmellik yolunda ilerleyip ilerlemediklerini ölçerek, kuvvetli yönlerini ve iyileştirmeye açık alanlarını görmelerini sağlayan,  uygun çözümler bulmalarında rehberlik eden, bir öz değerlendirme aracıdır. kalitenin kabulünü hızlandırarak ve kalite geliştirme aktivitelerini destekleyerek dünya pazarında Avrupa şirketlerinin yerini güçlendirmektir.\r\nEFQM’de esas alınan öz değerlendirme modeline göre, müşteri tatmini, çalışanların tatmini ve toplum üzerindeki etki süreçlerin uygun bir liderlik anlayışı ile yönetilmesiyle sağlanabilir ve böylece iş sonuçlarında mükemmelliğe ulaşılabilir\r\n', '7.jpg'),
(9, 'EFQM - 8', '“EFQM Mükemmellik Modeli”; Avrupa Kalite Yönetimi Vakfı tarafından oluşturulmuş bir Kalite Yönetim Modelidir.\r\nEFQM Mükemmellik Modeli, kuruluşların mükemmellik yolunda ilerleyip ilerlemediklerini ölçerek, kuvvetli yönlerini ve iyileştirmeye açık alanlarını görmelerini sağlayan,  uygun çözümler bulmalarında rehberlik eden, bir öz değerlendirme aracıdır. kalitenin kabulünü hızlandırarak ve kalite geliştirme aktivitelerini destekleyerek dünya pazarında Avrupa şirketlerinin yerini güçlendirmektir.\r\nEFQM’de esas alınan öz değerlendirme modeline göre, müşteri tatmini, çalışanların tatmini ve toplum üzerindeki etki süreçlerin uygun bir liderlik anlayışı ile yönetilmesiyle sağlanabilir ve böylece iş sonuçlarında mükemmelliğe ulaşılabilir\r\n', '8.jpg'),
(10, 'EFQM - 9', '“EFQM Mükemmellik Modeli”; Avrupa Kalite Yönetimi Vakfı tarafından oluşturulmuş bir Kalite Yönetim Modelidir.\r\nEFQM Mükemmellik Modeli, kuruluşların mükemmellik yolunda ilerleyip ilerlemediklerini ölçerek, kuvvetli yönlerini ve iyileştirmeye açık alanlarını görmelerini sağlayan,  uygun çözümler bulmalarında rehberlik eden, bir öz değerlendirme aracıdır. kalitenin kabulünü hızlandırarak ve kalite geliştirme aktivitelerini destekleyerek dünya pazarında Avrupa şirketlerinin yerini güçlendirmektir.\r\nEFQM’de esas alınan öz değerlendirme modeline göre, müşteri tatmini, çalışanların tatmini ve toplum üzerindeki etki süreçlerin uygun bir liderlik anlayışı ile yönetilmesiyle sağlanabilir ve böylece iş sonuçlarında mükemmelliğe ulaşılabilir\r\n', '9.jpg'),
(11, 'EFQM - 10', '“EFQM Mükemmellik Modeli”; Avrupa Kalite Yönetimi Vakfı tarafından oluşturulmuş bir Kalite Yönetim Modelidir.\r\nEFQM Mükemmellik Modeli, kuruluşların mükemmellik yolunda ilerleyip ilerlemediklerini ölçerek, kuvvetli yönlerini ve iyileştirmeye açık alanlarını görmelerini sağlayan,  uygun çözümler bulmalarında rehberlik eden, bir öz değerlendirme aracıdır. kalitenin kabulünü hızlandırarak ve kalite geliştirme aktivitelerini destekleyerek dünya pazarında Avrupa şirketlerinin yerini güçlendirmektir.\r\nEFQM’de esas alınan öz değerlendirme modeline göre, müşteri tatmini, çalışanların tatmini ve toplum üzerindeki etki süreçlerin uygun bir liderlik anlayışı ile yönetilmesiyle sağlanabilir ve böylece iş sonuçlarında mükemmelliğe ulaşılabilir\r\n', '10.jpg'),
(12, 'EFQM - 11', '“EFQM Mükemmellik Modeli”; Avrupa Kalite Yönetimi Vakfı tarafından oluşturulmuş bir Kalite Yönetim Modelidir.\r\nEFQM Mükemmellik Modeli, kuruluşların mükemmellik yolunda ilerleyip ilerlemediklerini ölçerek, kuvvetli yönlerini ve iyileştirmeye açık alanlarını görmelerini sağlayan,  uygun çözümler bulmalarında rehberlik eden, bir öz değerlendirme aracıdır. kalitenin kabulünü hızlandırarak ve kalite geliştirme aktivitelerini destekleyerek dünya pazarında Avrupa şirketlerinin yerini güçlendirmektir.\r\nEFQM’de esas alınan öz değerlendirme modeline göre, müşteri tatmini, çalışanların tatmini ve toplum üzerindeki etki süreçlerin uygun bir liderlik anlayışı ile yönetilmesiyle sağlanabilir ve böylece iş sonuçlarında mükemmelliğe ulaşılabilir\r\n', '11.jpg'),
(13, 'EFQM - 12', '“EFQM Mükemmellik Modeli”; Avrupa Kalite Yönetimi Vakfı tarafından oluşturulmuş bir Kalite Yönetim Modelidir.\r\nEFQM Mükemmellik Modeli, kuruluşların mükemmellik yolunda ilerleyip ilerlemediklerini ölçerek, kuvvetli yönlerini ve iyileştirmeye açık alanlarını görmelerini sağlayan,  uygun çözümler bulmalarında rehberlik eden, bir öz değerlendirme aracıdır. kalitenin kabulünü hızlandırarak ve kalite geliştirme aktivitelerini destekleyerek dünya pazarında Avrupa şirketlerinin yerini güçlendirmektir.\r\nEFQM’de esas alınan öz değerlendirme modeline göre, müşteri tatmini, çalışanların tatmini ve toplum üzerindeki etki süreçlerin uygun bir liderlik anlayışı ile yönetilmesiyle sağlanabilir ve böylece iş sonuçlarında mükemmelliğe ulaşılabilir\r\n', '12.jpg'),
(14, 'EFQM - 13', '“EFQM Mükemmellik Modeli”; Avrupa Kalite Yönetimi Vakfı tarafından oluşturulmuş bir Kalite Yönetim Modelidir.\r\nEFQM Mükemmellik Modeli, kuruluşların mükemmellik yolunda ilerleyip ilerlemediklerini ölçerek, kuvvetli yönlerini ve iyileştirmeye açık alanlarını görmelerini sağlayan,  uygun çözümler bulmalarında rehberlik eden, bir öz değerlendirme aracıdır. kalitenin kabulünü hızlandırarak ve kalite geliştirme aktivitelerini destekleyerek dünya pazarında Avrupa şirketlerinin yerini güçlendirmektir.\r\nEFQM’de esas alınan öz değerlendirme modeline göre, müşteri tatmini, çalışanların tatmini ve toplum üzerindeki etki süreçlerin uygun bir liderlik anlayışı ile yönetilmesiyle sağlanabilir ve böylece iş sonuçlarında mükemmelliğe ulaşılabilir\r\n', '13.jpg'),
(15, 'EFQM - 14', '“EFQM Mükemmellik Modeli”; Avrupa Kalite Yönetimi Vakfı tarafından oluşturulmuş bir Kalite Yönetim Modelidir.\r\nEFQM Mükemmellik Modeli, kuruluşların mükemmellik yolunda ilerleyip ilerlemediklerini ölçerek, kuvvetli yönlerini ve iyileştirmeye açık alanlarını görmelerini sağlayan,  uygun çözümler bulmalarında rehberlik eden, bir öz değerlendirme aracıdır. kalitenin kabulünü hızlandırarak ve kalite geliştirme aktivitelerini destekleyerek dünya pazarında Avrupa şirketlerinin yerini güçlendirmektir.\r\nEFQM’de esas alınan öz değerlendirme modeline göre, müşteri tatmini, çalışanların tatmini ve toplum üzerindeki etki süreçlerin uygun bir liderlik anlayışı ile yönetilmesiyle sağlanabilir ve böylece iş sonuçlarında mükemmelliğe ulaşılabilir\r\n', '14.jpg'),
(16, 'EFQM - 15', '“EFQM Mükemmellik Modeli”; Avrupa Kalite Yönetimi Vakfı tarafından oluşturulmuş bir Kalite Yönetim Modelidir.\r\nEFQM Mükemmellik Modeli, kuruluşların mükemmellik yolunda ilerleyip ilerlemediklerini ölçerek, kuvvetli yönlerini ve iyileştirmeye açık alanlarını görmelerini sağlayan,  uygun çözümler bulmalarında rehberlik eden, bir öz değerlendirme aracıdır. kalitenin kabulünü hızlandırarak ve kalite geliştirme aktivitelerini destekleyerek dünya pazarında Avrupa şirketlerinin yerini güçlendirmektir.\r\nEFQM’de esas alınan öz değerlendirme modeline göre, müşteri tatmini, çalışanların tatmini ve toplum üzerindeki etki süreçlerin uygun bir liderlik anlayışı ile yönetilmesiyle sağlanabilir ve böylece iş sonuçlarında mükemmelliğe ulaşılabilir\r\n', '15.jpg'),
(17, 'EFQM - 16', '“EFQM Mükemmellik Modeli”; Avrupa Kalite Yönetimi Vakfı tarafından oluşturulmuş bir Kalite Yönetim Modelidir.\r\nEFQM Mükemmellik Modeli, kuruluşların mükemmellik yolunda ilerleyip ilerlemediklerini ölçerek, kuvvetli yönlerini ve iyileştirmeye açık alanlarını görmelerini sağlayan,  uygun çözümler bulmalarında rehberlik eden, bir öz değerlendirme aracıdır. kalitenin kabulünü hızlandırarak ve kalite geliştirme aktivitelerini destekleyerek dünya pazarında Avrupa şirketlerinin yerini güçlendirmektir.\r\nEFQM’de esas alınan öz değerlendirme modeline göre, müşteri tatmini, çalışanların tatmini ve toplum üzerindeki etki süreçlerin uygun bir liderlik anlayışı ile yönetilmesiyle sağlanabilir ve böylece iş sonuçlarında mükemmelliğe ulaşılabilir\r\n', '16.jpg'),
(18, 'EFQM - 17', '“EFQM Mükemmellik Modeli”; Avrupa Kalite Yönetimi Vakfı tarafından oluşturulmuş bir Kalite Yönetim Modelidir.\r\nEFQM Mükemmellik Modeli, kuruluşların mükemmellik yolunda ilerleyip ilerlemediklerini ölçerek, kuvvetli yönlerini ve iyileştirmeye açık alanlarını görmelerini sağlayan,  uygun çözümler bulmalarında rehberlik eden, bir öz değerlendirme aracıdır. kalitenin kabulünü hızlandırarak ve kalite geliştirme aktivitelerini destekleyerek dünya pazarında Avrupa şirketlerinin yerini güçlendirmektir.\r\nEFQM’de esas alınan öz değerlendirme modeline göre, müşteri tatmini, çalışanların tatmini ve toplum üzerindeki etki süreçlerin uygun bir liderlik anlayışı ile yönetilmesiyle sağlanabilir ve böylece iş sonuçlarında mükemmelliğe ulaşılabilir\r\n', '17.jpg');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`Id`);

--
-- Tablo için indeksler `emails`
--
ALTER TABLE `emails`
  ADD PRIMARY KEY (`Id`);

--
-- Tablo için indeksler `next`
--
ALTER TABLE `next`
  ADD PRIMARY KEY (`Id`);

--
-- Tablo için indeksler `previous`
--
ALTER TABLE `previous`
  ADD PRIMARY KEY (`Id`);

--
-- Tablo için indeksler `slider`
--
ALTER TABLE `slider`
  ADD PRIMARY KEY (`Id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `comments`
--
ALTER TABLE `comments`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Tablo için AUTO_INCREMENT değeri `emails`
--
ALTER TABLE `emails`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Tablo için AUTO_INCREMENT değeri `next`
--
ALTER TABLE `next`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Tablo için AUTO_INCREMENT değeri `previous`
--
ALTER TABLE `previous`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Tablo için AUTO_INCREMENT değeri `slider`
--
ALTER TABLE `slider`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
