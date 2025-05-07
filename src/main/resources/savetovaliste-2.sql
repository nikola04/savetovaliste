-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 07, 2025 at 01:14 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `savetovaliste`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `dodaj_psihoterapeuta` (IN `p_ime` VARCHAR(100), IN `p_prezime` VARCHAR(100), IN `p_jmbg` VARCHAR(13), IN `p_email` VARCHAR(100), IN `p_telefon` VARCHAR(20), IN `p_datum_rodjenja` DATE, IN `p_sertifikat_id` INT, IN `p_struka_id` INT)   BEGIN
    INSERT INTO psihoterapeut (
        ime, prezime, jmbg, email, telefon, datum_rodjenja, sertifikat_id, struka_id
    )
    VALUES (
        p_ime, p_prezime, p_jmbg, p_email, p_telefon, p_datum_rodjenja, p_sertifikat_id, p_struka_id
    );
    
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `beleske`
--

CREATE TABLE `beleske` (
  `beleske_id` int(11) NOT NULL,
  `tekst` text DEFAULT NULL,
  `seansa_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `cena_seanse`
--

CREATE TABLE `cena_seanse` (
  `cena_seanse_id` int(11) NOT NULL,
  `cena` double(10,2) NOT NULL,
  `datum_promene` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `centar_za_obuku`
--

CREATE TABLE `centar_za_obuku` (
  `centar_za_obuku_id` int(11) NOT NULL,
  `naziv` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'unique',
  `telefon` varchar(12) NOT NULL,
  `ulica` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `broj` int(11) NOT NULL,
  `opstina` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `centar_za_obuku`
--

INSERT INTO `centar_za_obuku` (`centar_za_obuku_id`, `naziv`, `email`, `telefon`, `ulica`, `broj`, `opstina`) VALUES
(1, 'Institut za psihoterapiju i psihologiju ', 'email1@gmail.com', '063443434', 'Jasenova', 1, 'Smederevo'),
(2, 'Centar za psihodinamsku obuku ', 'email2@gmail.com', '063443455', 'Lovska', 23, 'Beograd'),
(3, 'Psihoanalitički centar za obuku i istraživanje', 'email3@gmail.com', '063449811', 'Majke jevrosime', 35, 'Vozdovac'),
(4, 'Institut za gestalt psihoterapiju ', 'email4@gmail.com', '063445455', 'Rastka Petrovica', 656, 'Zvezdara'),
(5, 'EAP – Evropska asocijacija za psihoterapiju', 'email5@gmail.com', '063447677', 'Bulevar Kralja Aleksandra', 65, 'Negotin'),
(6, 'Beck Institute for Cognitive Behavior Therapy', 'email6@gmail.com', '063777755', 'Bulevar Mateje Matica', 3, 'Kovin'),
(7, 'The Gestalt Therapy Institute ', 'email7@gmail.com', '063446466', 'Virovo', 12, 'SAD'),
(8, 'Harvard Medical School ', 'email8@gmail.com', '065343434', 'Jovanova', 1, 'Valjevo'),
(9, 'Tavistock and Portman NHS Foundation Trust', 'email9@gmail.com', '063443423', 'Napoleonova', 5, 'Novi Pazar'),
(10, 'IPTAR ', 'email10@gmail.com', '063443233', 'St Navak', 6, 'Boston ');

-- --------------------------------------------------------

--
-- Table structure for table `fakultet`
--

CREATE TABLE `fakultet` (
  `fakultet_id` int(11) NOT NULL,
  `naziv` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `univerzitet_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `fakultet`
--

INSERT INTO `fakultet` (`fakultet_id`, `naziv`, `univerzitet_id`) VALUES
(1, 'Elektrotehnicki Fakultet', 1),
(2, 'Fakultet Muzicke Umetnosti', 2),
(3, 'Fakultet Tehnickih Nauka', 1),
(4, 'Pravni Fakultet', 4),
(5, 'Pravni Fakultet', 1),
(6, 'Medicinski Fakultet', 5),
(7, 'Fakultet za Marketing', 6),
(8, 'Fakultet za IT', 7),
(9, 'Fakultet Zdravstvenih Studija', 8),
(10, 'Tehnoloski Fakultet', 9);

-- --------------------------------------------------------

--
-- Table structure for table `kandidat`
--

CREATE TABLE `kandidat` (
  `kandidat_id` int(11) NOT NULL,
  `ime` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `prezime` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `jmbg` varchar(13) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `datum_rodjenja` date NOT NULL,
  `prebivaliste` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `telefon` varchar(12) NOT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `fakultet_id` int(11) NOT NULL,
  `centar_za_obuku_id` int(11) DEFAULT NULL,
  `studiija_id` int(11) NOT NULL,
  `supervizor_id` int(11) DEFAULT NULL,
  `psihoterapeut_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `klijent`
--

CREATE TABLE `klijent` (
  `klijent_id` int(11) NOT NULL,
  `ime` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `prezime` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `datum_rodjenja` date NOT NULL,
  `pol` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `broj` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ranije_terapije` tinyint(1) NOT NULL,
  `prijava_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `kurs_valute`
--

CREATE TABLE `kurs_valute` (
  `kurs_id` int(11) NOT NULL,
  `datum` date NOT NULL,
  `kurs_din` decimal(10,4) NOT NULL,
  `valuta_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `kurs_valute`
--

INSERT INTO `kurs_valute` (`kurs_id`, `datum`, `kurs_din`, `valuta_id`) VALUES
(1, '2025-05-05', 117.0000, 1),
(2, '2025-05-05', 1.0000, 2),
(3, '2025-05-05', 107.5670, 3),
(4, '2025-05-05', 4.7000, 4),
(5, '2025-05-05', 125.7000, 5),
(6, '2025-05-05', 57.5000, 6),
(7, '2025-05-05', 0.7200, 7),
(8, '2025-05-05', 137.5630, 8),
(9, '2025-05-05', 78.0398, 9),
(10, '2025-05-05', 5.6340, 10);

-- --------------------------------------------------------

--
-- Table structure for table `objavljivanje_podataka`
--

CREATE TABLE `objavljivanje_podataka` (
  `objavljivanje_id` int(11) NOT NULL,
  `seansa_id` int(11) NOT NULL,
  `razlog` enum('supervizija','prijava policiji','sud') NOT NULL,
  `datum_objave` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `oblast`
--

CREATE TABLE `oblast` (
  `oblasti_id` int(11) NOT NULL,
  `naziv` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `oblast`
--

INSERT INTO `oblast` (`oblasti_id`, `naziv`) VALUES
(1, 'Elektronika'),
(2, 'Energetika'),
(3, 'Telekomunikacije'),
(4, 'Likovne Umetnosti'),
(5, 'Muzika'),
(6, 'Dramske Umetnosti'),
(7, 'Marketing'),
(8, 'Informatika'),
(9, 'Robotika'),
(10, 'Inzenjering'),
(11, 'Gradjansko pravo'),
(12, 'Krivicno pravo'),
(13, 'Hirurgija'),
(14, 'Interna Medicina'),
(15, 'Finansije'),
(16, 'Menadzment'),
(17, 'Programiranje'),
(18, 'Racunarske Mreze'),
(19, 'Radiologija'),
(20, 'Fizikalna Terapija'),
(21, 'Hemijska Tehnologija'),
(22, 'Prehrambena Tehnologija'),
(23, 'Gluma'),
(24, 'Graficki Dizajn');

-- --------------------------------------------------------

--
-- Table structure for table `oblasti_psihoterapije`
--

CREATE TABLE `oblasti_psihoterapije` (
  `oblasti_id` int(11) NOT NULL,
  `naziv` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `oblasti_psihoterapije`
--

INSERT INTO `oblasti_psihoterapije` (`oblasti_id`, `naziv`) VALUES
(1, 'Kognitivno-bihejvioralna terap'),
(2, 'Terapija prihvatanja i posveće'),
(3, 'Humanistička terapija'),
(4, 'Geštalt terapija'),
(5, 'Porodična terapija\n'),
(6, 'Sistemska terapija\n'),
(7, 'Psihodinamska terapija\n'),
(8, 'Mindfulness terapija\n'),
(9, 'Eksperimentalna terapija\n'),
(10, 'Somatska terapija');

-- --------------------------------------------------------

--
-- Table structure for table `oblast_fakultet`
--

CREATE TABLE `oblast_fakultet` (
  `fakultet_id` int(11) NOT NULL,
  `oblasti_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `oblast_fakultet`
--

INSERT INTO `oblast_fakultet` (`fakultet_id`, `oblasti_id`) VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 4),
(2, 5),
(2, 6),
(3, 8),
(3, 9),
(3, 10),
(4, 11),
(4, 12),
(5, 13),
(5, 14),
(6, 13),
(6, 14),
(7, 7),
(7, 15),
(7, 16),
(8, 17),
(8, 18),
(9, 14),
(10, 1),
(10, 2),
(10, 3);

-- --------------------------------------------------------

--
-- Table structure for table `oblast_test`
--

CREATE TABLE `oblast_test` (
  `oblast_testa_id` int(11) NOT NULL,
  `neaziv` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `oblast_test`
--

INSERT INTO `oblast_test` (`oblast_testa_id`, `neaziv`) VALUES
(1, 'Depresija'),
(2, 'Anksioznost'),
(3, 'Stres'),
(4, 'Licnost'),
(5, 'Samopostovanje');

-- --------------------------------------------------------

--
-- Table structure for table `oblast_usmerenje`
--

CREATE TABLE `oblast_usmerenje` (
  `oblasti_id` int(11) NOT NULL,
  `uze_usmerenje_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `oblast_usmerenje`
--

INSERT INTO `oblast_usmerenje` (`oblasti_id`, `uze_usmerenje_id`) VALUES
(4, 2),
(5, 2),
(6, 2),
(7, 3),
(8, 1),
(9, 1),
(10, 1),
(14, 1),
(15, 3),
(16, 3),
(17, 4),
(18, 4),
(23, 2);

-- --------------------------------------------------------

--
-- Table structure for table `placanje`
--

CREATE TABLE `placanje` (
  `placanje_id` int(11) NOT NULL,
  `iznos` double(10,2) NOT NULL,
  `iznos_sa_provizijom` double(10,2) NOT NULL,
  `datum` date NOT NULL,
  `valuta_valuta_id` int(11) NOT NULL,
  `nacin_placanja` enum('kartica','gotovina') NOT NULL,
  `klijent_id` int(11) NOT NULL,
  `svrha_placanja` enum('seansa','test') NOT NULL,
  `rata` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `prijava`
--

CREATE TABLE `prijava` (
  `prijava_id` int(11) NOT NULL,
  `psihoterapeut_id` int(11) DEFAULT NULL,
  `kandidat_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `psihoterapeut`
--

CREATE TABLE `psihoterapeut` (
  `psihoterapeut_id` int(11) NOT NULL,
  `ime` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `prezime` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `jmbg` varchar(13) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `telefon` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `datum_rodjenja` date NOT NULL,
  `sertifikat_id` int(11) DEFAULT NULL,
  `struka_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `psihoterapeut`
--

INSERT INTO `psihoterapeut` (`psihoterapeut_id`, `ime`, `prezime`, `jmbg`, `email`, `telefon`, `datum_rodjenja`, `sertifikat_id`, `struka_id`) VALUES
(1, 'Nikola', 'Vasiljevic', '0107004761013', 'nikola06121@gmail.com', '0612145989', '2025-07-01', 1, 1),
(2, 'Patak', 'Nedeljkovic', '3010004790010', 'nnikola@gmail.com', '0611829631', '2004-05-19', 2, 3),
(7, 'patak', 'patkovic', '12312124', 'pp@gmail.com', '0414141', '2025-05-14', 3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `seansa`
--

CREATE TABLE `seansa` (
  `seansa_id` int(11) NOT NULL,
  `klijent_id` int(11) NOT NULL,
  `prva` tinyint(1) NOT NULL,
  `dan` date NOT NULL,
  `vreme` time NOT NULL,
  `vreme_trajanja` int(11) NOT NULL,
  `na_rate` tinyint(1) NOT NULL,
  `placeno` tinyint(1) NOT NULL,
  `cena_seanse_id` int(11) NOT NULL,
  `placanje_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `sertifikat`
--

CREATE TABLE `sertifikat` (
  `sertifikat_id` int(11) NOT NULL,
  `datum_sertifikata` date NOT NULL,
  `oblasti_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sertifikat`
--

INSERT INTO `sertifikat` (`sertifikat_id`, `datum_sertifikata`, `oblasti_id`) VALUES
(1, '2025-05-01', 1),
(2, '2025-05-05', 2),
(3, '2025-05-05', 3),
(4, '2025-05-05', 4),
(5, '2025-05-05', 5),
(6, '2025-05-05', 6),
(7, '2025-05-05', 7),
(8, '2025-05-05', 8),
(9, '2025-05-05', 1),
(10, '2025-05-05', 1);

-- --------------------------------------------------------

--
-- Table structure for table `stepen_studiija`
--

CREATE TABLE `stepen_studiija` (
  `stepen_studiija_id` int(11) NOT NULL,
  `naziv` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `stepen_studiija`
--

INSERT INTO `stepen_studiija` (`stepen_studiija_id`, `naziv`) VALUES
(1, 'Osnovne'),
(2, 'Master'),
(3, 'Doktorske');

-- --------------------------------------------------------

--
-- Table structure for table `struka`
--

CREATE TABLE `struka` (
  `struka_id` int(11) NOT NULL,
  `naziv` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `struka`
--

INSERT INTO `struka` (`struka_id`, `naziv`) VALUES
(1, 'Psiholog'),
(2, 'Psihijatar'),
(3, 'Klinicki Psiholog'),
(4, 'Bracni Psiholog'),
(5, 'Psihoanaliticar'),
(6, 'Hipnoterapeut'),
(7, 'Psihodramaturg');

-- --------------------------------------------------------

--
-- Table structure for table `test`
--

CREATE TABLE `test` (
  `test_id` int(11) NOT NULL,
  `naziv` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cena` int(11) NOT NULL,
  `oblast_testa_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `test`
--

INSERT INTO `test` (`test_id`, `naziv`, `cena`, `oblast_testa_id`) VALUES
(1, 'Beckova skala depresije', 1000, 1),
(2, 'Skala za procenu anksioznosti', 2000, 2),
(3, 'Big Five Invertory', 1500, 4),
(4, 'Perseived Stress Scale', 4000, 3),
(5, 'Rosenbergova skala samopostova', 1000, 5),
(6, 'Stress Test Patak', 500, 3),
(7, 'Hamiltonova skala depresije', 1000, 1),
(8, 'Inventar anksioznosti beck', 1500, 2),
(9, 'HEXACO inventar licnosti', 1999, 4),
(10, 'Skala stresnih dogadjaja', 2499, 3);

-- --------------------------------------------------------

--
-- Table structure for table `testiranje`
--

CREATE TABLE `testiranje` (
  `testiranje_id` int(11) NOT NULL,
  `rezultat` decimal(3,2) NOT NULL,
  `test_id` int(11) NOT NULL,
  `seansa_id` int(11) NOT NULL,
  `placanje_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `univerzitet`
--

CREATE TABLE `univerzitet` (
  `univerzitet_id` int(11) NOT NULL,
  `naziv` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `uze_usmerenje_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `univerzitet`
--

INSERT INTO `univerzitet` (`univerzitet_id`, `naziv`, `uze_usmerenje_id`) VALUES
(1, 'Univerzitet u Beogradu ', NULL),
(2, 'Univerzitet Umetnosti u Beogradu ', 2),
(3, 'Univerzitet Primenjenih Nauka u Novom Sadu', 1),
(4, 'Univerzitet u Nisu', NULL),
(5, 'Univerzitet u Kragujevcu ', NULL),
(6, 'Univerzitet za Ekonomiju i Menadzment', 3),
(7, 'Univerzitet u Novom Pazaru', 2),
(8, 'Univerzitet u Smederevskoj Palanci', 4),
(9, 'Univerzitet Primenjenih Nauka u Beogradu', 1),
(10, 'Univerzitet u Subotici ', 5);

-- --------------------------------------------------------

--
-- Table structure for table `uze_usmerenje`
--

CREATE TABLE `uze_usmerenje` (
  `naziv` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `uze_usmerenje_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `uze_usmerenje`
--

INSERT INTO `uze_usmerenje` (`naziv`, `uze_usmerenje_id`) VALUES
('Primenjene nauke', 1),
('Umetnost', 2),
('Ekonomija i menadzment', 3),
('IT', 4),
('Filologija', 5);

-- --------------------------------------------------------

--
-- Table structure for table `valuta`
--

CREATE TABLE `valuta` (
  `valuta_id` int(11) NOT NULL,
  `skraceni_naziv` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pun_naziv` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `valuta`
--

INSERT INTO `valuta` (`valuta_id`, `skraceni_naziv`, `pun_naziv`) VALUES
(1, 'EUR', 'euro'),
(2, 'RSD', 'Srpski dinar'),
(3, 'USD', 'Americki dolar'),
(4, 'CZK', 'Ceska kruna'),
(5, 'CHF', 'Franak'),
(6, 'KM', 'Konvertibilna Marka'),
(7, 'JPY', 'Jen'),
(8, 'GBP', 'Funta'),
(9, 'CAD', 'Kanadski Dolar'),
(10, 'MXP', 'Meksicki Pezos');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `beleske`
--
ALTER TABLE `beleske`
  ADD PRIMARY KEY (`beleske_id`),
  ADD KEY `beleske_seansa` (`seansa_id`);

--
-- Indexes for table `cena_seanse`
--
ALTER TABLE `cena_seanse`
  ADD PRIMARY KEY (`cena_seanse_id`);

--
-- Indexes for table `centar_za_obuku`
--
ALTER TABLE `centar_za_obuku`
  ADD PRIMARY KEY (`centar_za_obuku_id`);

--
-- Indexes for table `fakultet`
--
ALTER TABLE `fakultet`
  ADD PRIMARY KEY (`fakultet_id`),
  ADD KEY `Fakultet_Univerzitet` (`univerzitet_id`);

--
-- Indexes for table `kandidat`
--
ALTER TABLE `kandidat`
  ADD PRIMARY KEY (`kandidat_id`),
  ADD KEY `kandidat_centar_za_obuku` (`centar_za_obuku_id`),
  ADD KEY `kandidat_fakultet` (`fakultet_id`),
  ADD KEY `kandidat_psihoterapeut` (`supervizor_id`),
  ADD KEY `kandidat_stepen_studiija` (`studiija_id`),
  ADD KEY `kandidat_psihoterapeut_fk` (`psihoterapeut_id`);

--
-- Indexes for table `klijent`
--
ALTER TABLE `klijent`
  ADD PRIMARY KEY (`klijent_id`),
  ADD KEY `prijava_klijent` (`prijava_id`);

--
-- Indexes for table `kurs_valute`
--
ALTER TABLE `kurs_valute`
  ADD PRIMARY KEY (`kurs_id`),
  ADD KEY `kurs_valute_valuta` (`valuta_id`);

--
-- Indexes for table `objavljivanje_podataka`
--
ALTER TABLE `objavljivanje_podataka`
  ADD PRIMARY KEY (`objavljivanje_id`),
  ADD KEY `objavljivanje_podataka_seansa` (`seansa_id`);

--
-- Indexes for table `oblast`
--
ALTER TABLE `oblast`
  ADD PRIMARY KEY (`oblasti_id`);

--
-- Indexes for table `oblasti_psihoterapije`
--
ALTER TABLE `oblasti_psihoterapije`
  ADD PRIMARY KEY (`oblasti_id`);

--
-- Indexes for table `oblast_fakultet`
--
ALTER TABLE `oblast_fakultet`
  ADD PRIMARY KEY (`fakultet_id`,`oblasti_id`),
  ADD KEY `oblast_fakultet_oblast` (`oblasti_id`);

--
-- Indexes for table `oblast_test`
--
ALTER TABLE `oblast_test`
  ADD PRIMARY KEY (`oblast_testa_id`);

--
-- Indexes for table `oblast_usmerenje`
--
ALTER TABLE `oblast_usmerenje`
  ADD PRIMARY KEY (`oblasti_id`,`uze_usmerenje_id`),
  ADD KEY `oblast_usmerenje_uze_usmerenje` (`uze_usmerenje_id`);

--
-- Indexes for table `placanje`
--
ALTER TABLE `placanje`
  ADD PRIMARY KEY (`placanje_id`),
  ADD KEY `placanje_klijent` (`klijent_id`),
  ADD KEY `placanje_valuta` (`valuta_valuta_id`);

--
-- Indexes for table `prijava`
--
ALTER TABLE `prijava`
  ADD PRIMARY KEY (`prijava_id`),
  ADD KEY `prijava_kandidat` (`kandidat_id`),
  ADD KEY `prijava_psihoterapeut` (`psihoterapeut_id`);

--
-- Indexes for table `psihoterapeut`
--
ALTER TABLE `psihoterapeut`
  ADD PRIMARY KEY (`psihoterapeut_id`),
  ADD UNIQUE KEY `unique_jmbg` (`jmbg`),
  ADD UNIQUE KEY `unique_email` (`email`),
  ADD UNIQUE KEY `unique_sertifikat_id` (`sertifikat_id`),
  ADD KEY `psihoterapeut_sertifikat` (`sertifikat_id`),
  ADD KEY `psihoterapeut_struka` (`struka_id`);

--
-- Indexes for table `seansa`
--
ALTER TABLE `seansa`
  ADD PRIMARY KEY (`seansa_id`),
  ADD KEY `seansa_cena_seanse` (`cena_seanse_id`),
  ADD KEY `seansa_klijent` (`klijent_id`),
  ADD KEY `seansa_placanje` (`placanje_id`);

--
-- Indexes for table `sertifikat`
--
ALTER TABLE `sertifikat`
  ADD PRIMARY KEY (`sertifikat_id`),
  ADD KEY `sertifikat_oblasti_psihoterapije` (`oblasti_id`);

--
-- Indexes for table `stepen_studiija`
--
ALTER TABLE `stepen_studiija`
  ADD PRIMARY KEY (`stepen_studiija_id`);

--
-- Indexes for table `struka`
--
ALTER TABLE `struka`
  ADD PRIMARY KEY (`struka_id`);

--
-- Indexes for table `test`
--
ALTER TABLE `test`
  ADD PRIMARY KEY (`test_id`),
  ADD KEY `test_oblast_test` (`oblast_testa_id`);

--
-- Indexes for table `testiranje`
--
ALTER TABLE `testiranje`
  ADD PRIMARY KEY (`testiranje_id`),
  ADD KEY `testiranje_placanje` (`placanje_id`),
  ADD KEY `testiranje_seansa` (`seansa_id`),
  ADD KEY `testiranje_test` (`test_id`);

--
-- Indexes for table `univerzitet`
--
ALTER TABLE `univerzitet`
  ADD PRIMARY KEY (`univerzitet_id`),
  ADD KEY `univerzitet_uze_usmerenje` (`uze_usmerenje_id`);

--
-- Indexes for table `uze_usmerenje`
--
ALTER TABLE `uze_usmerenje`
  ADD PRIMARY KEY (`uze_usmerenje_id`);

--
-- Indexes for table `valuta`
--
ALTER TABLE `valuta`
  ADD PRIMARY KEY (`valuta_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `psihoterapeut`
--
ALTER TABLE `psihoterapeut`
  MODIFY `psihoterapeut_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `beleske`
--
ALTER TABLE `beleske`
  ADD CONSTRAINT `beleske_seansa` FOREIGN KEY (`seansa_id`) REFERENCES `seansa` (`seansa_id`);

--
-- Constraints for table `fakultet`
--
ALTER TABLE `fakultet`
  ADD CONSTRAINT `Fakultet_Univerzitet` FOREIGN KEY (`univerzitet_id`) REFERENCES `univerzitet` (`univerzitet_id`);

--
-- Constraints for table `kandidat`
--
ALTER TABLE `kandidat`
  ADD CONSTRAINT `kandidat_centar_za_obuku` FOREIGN KEY (`centar_za_obuku_id`) REFERENCES `centar_za_obuku` (`centar_za_obuku_id`),
  ADD CONSTRAINT `kandidat_fakultet` FOREIGN KEY (`fakultet_id`) REFERENCES `fakultet` (`fakultet_id`),
  ADD CONSTRAINT `kandidat_psihoterapeut_fk` FOREIGN KEY (`psihoterapeut_id`) REFERENCES `psihoterapeut` (`psihoterapeut_id`),
  ADD CONSTRAINT `kandidat_stepen_studiija` FOREIGN KEY (`studiija_id`) REFERENCES `stepen_studiija` (`stepen_studiija_id`);

--
-- Constraints for table `klijent`
--
ALTER TABLE `klijent`
  ADD CONSTRAINT `prijava_klijent` FOREIGN KEY (`prijava_id`) REFERENCES `prijava` (`prijava_id`);

--
-- Constraints for table `kurs_valute`
--
ALTER TABLE `kurs_valute`
  ADD CONSTRAINT `kurs_valute_valuta` FOREIGN KEY (`valuta_id`) REFERENCES `valuta` (`valuta_id`);

--
-- Constraints for table `objavljivanje_podataka`
--
ALTER TABLE `objavljivanje_podataka`
  ADD CONSTRAINT `objavljivanje_podataka_seansa` FOREIGN KEY (`seansa_id`) REFERENCES `seansa` (`seansa_id`);

--
-- Constraints for table `oblast_fakultet`
--
ALTER TABLE `oblast_fakultet`
  ADD CONSTRAINT `oblast_fakultet_fakultet` FOREIGN KEY (`fakultet_id`) REFERENCES `fakultet` (`fakultet_id`),
  ADD CONSTRAINT `oblast_fakultet_oblast` FOREIGN KEY (`oblasti_id`) REFERENCES `oblast` (`oblasti_id`);

--
-- Constraints for table `oblast_usmerenje`
--
ALTER TABLE `oblast_usmerenje`
  ADD CONSTRAINT `oblast_usmerenje_oblast` FOREIGN KEY (`oblasti_id`) REFERENCES `oblast` (`oblasti_id`),
  ADD CONSTRAINT `oblast_usmerenje_uze_usmerenje` FOREIGN KEY (`uze_usmerenje_id`) REFERENCES `uze_usmerenje` (`uze_usmerenje_id`);

--
-- Constraints for table `placanje`
--
ALTER TABLE `placanje`
  ADD CONSTRAINT `placanje_klijent` FOREIGN KEY (`klijent_id`) REFERENCES `klijent` (`klijent_id`),
  ADD CONSTRAINT `placanje_valuta` FOREIGN KEY (`valuta_valuta_id`) REFERENCES `valuta` (`valuta_id`);

--
-- Constraints for table `prijava`
--
ALTER TABLE `prijava`
  ADD CONSTRAINT `prijava_kandidat` FOREIGN KEY (`kandidat_id`) REFERENCES `kandidat` (`kandidat_id`),
  ADD CONSTRAINT `prijava_psihoterapeut` FOREIGN KEY (`psihoterapeut_id`) REFERENCES `psihoterapeut` (`psihoterapeut_id`);

--
-- Constraints for table `psihoterapeut`
--
ALTER TABLE `psihoterapeut`
  ADD CONSTRAINT `psihoterapeut_sertifikat` FOREIGN KEY (`sertifikat_id`) REFERENCES `sertifikat` (`sertifikat_id`),
  ADD CONSTRAINT `psihoterapeut_struka` FOREIGN KEY (`struka_id`) REFERENCES `struka` (`struka_id`);

--
-- Constraints for table `seansa`
--
ALTER TABLE `seansa`
  ADD CONSTRAINT `seansa_cena_seanse` FOREIGN KEY (`cena_seanse_id`) REFERENCES `cena_seanse` (`cena_seanse_id`),
  ADD CONSTRAINT `seansa_klijent` FOREIGN KEY (`klijent_id`) REFERENCES `klijent` (`klijent_id`),
  ADD CONSTRAINT `seansa_placanje` FOREIGN KEY (`placanje_id`) REFERENCES `placanje` (`placanje_id`);

--
-- Constraints for table `sertifikat`
--
ALTER TABLE `sertifikat`
  ADD CONSTRAINT `sertifikat_oblasti_psihoterapije` FOREIGN KEY (`oblasti_id`) REFERENCES `oblasti_psihoterapije` (`oblasti_id`);

--
-- Constraints for table `test`
--
ALTER TABLE `test`
  ADD CONSTRAINT `test_oblast_test` FOREIGN KEY (`oblast_testa_id`) REFERENCES `oblast_test` (`oblast_testa_id`);

--
-- Constraints for table `testiranje`
--
ALTER TABLE `testiranje`
  ADD CONSTRAINT `testiranje_placanje` FOREIGN KEY (`placanje_id`) REFERENCES `placanje` (`placanje_id`),
  ADD CONSTRAINT `testiranje_seansa` FOREIGN KEY (`seansa_id`) REFERENCES `seansa` (`seansa_id`),
  ADD CONSTRAINT `testiranje_test` FOREIGN KEY (`test_id`) REFERENCES `test` (`test_id`);

--
-- Constraints for table `univerzitet`
--
ALTER TABLE `univerzitet`
  ADD CONSTRAINT `univerzitet_uze_usmerenje` FOREIGN KEY (`uze_usmerenje_id`) REFERENCES `uze_usmerenje` (`uze_usmerenje_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
