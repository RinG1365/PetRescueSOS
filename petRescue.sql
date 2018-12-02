-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Dec 02, 2018 at 08:53 PM
-- Server version: 5.7.23
-- PHP Version: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `petRescue`
--

-- --------------------------------------------------------

--
-- Table structure for table `ADMIN`
--

CREATE TABLE `ADMIN` (
  `ID` varchar(5) NOT NULL,
  `PSS` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ADMIN`
--

INSERT INTO `ADMIN` (`ID`, `PSS`) VALUES
('admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `RESCUER`
--

CREATE TABLE `RESCUER` (
  `RID` int(5) NOT NULL,
  `RNAME` varchar(30) NOT NULL,
  `GENDER` varchar(1) NOT NULL,
  `PHONENO` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `RESCUER`
--

INSERT INTO `RESCUER` (`RID`, `RNAME`, `GENDER`, `PHONENO`) VALUES
(1, 'Patrick', 'M', '0122351563'),
(7, 'Syukor', 'M', '0121234567');

-- --------------------------------------------------------

--
-- Table structure for table `SOS`
--

CREATE TABLE `SOS` (
  `SOSID` int(5) NOT NULL,
  `SOSDATE` date NOT NULL,
  `SOSTIME` time NOT NULL,
  `SOSSTATUS` varchar(20) DEFAULT NULL,
  `PTYPE` varchar(10) NOT NULL,
  `PQUAN` int(2) NOT NULL,
  `USERNAME` varchar(30) NOT NULL,
  `RID` int(5) NOT NULL,
  `SID` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `SOS`
--

INSERT INTO `SOS` (`SOSID`, `SOSDATE`, `SOSTIME`, `SOSSTATUS`, `PTYPE`, `PQUAN`, `USERNAME`, `RID`, `SID`) VALUES
(1, '2018-12-03', '04:47:15', 'WAIT', 'Cat', 1, 'lpy', 1, 1),
(2, '2018-12-03', '04:48:53', 'WAIT', 'Cat', 1, 'lzx', 7, 1),
(3, '2018-12-03', '04:49:30', 'WAIT', 'Dog', 1, 'lzx', 1, 6);

-- --------------------------------------------------------

--
-- Table structure for table `STATION`
--

CREATE TABLE `STATION` (
  `SID` int(5) NOT NULL,
  `SAREA` varchar(50) NOT NULL,
  `ADDRESS` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `STATION`
--

INSERT INTO `STATION` (`SID`, `SAREA`, `ADDRESS`) VALUES
(1, 'Alor Gajah', 'Batu 8, Bertam Ulu, 76450 Melaka'),
(6, 'Durian Tunggal', 'Jln 13, Taman Durian Tunggal');

-- --------------------------------------------------------

--
-- Table structure for table `USERS`
--

CREATE TABLE `USERS` (
  `USERNAME` varchar(30) NOT NULL,
  `PSS` varchar(20) NOT NULL,
  `GENDER` varchar(1) NOT NULL,
  `PHONENO` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `USERS`
--

INSERT INTO `USERS` (`USERNAME`, `PSS`, `GENDER`, `PHONENO`) VALUES
('czq', '1234', 'F', '0193891563'),
('gjx', '1234', 'M', '123'),
('John', '1234', 'M', '0121234567'),
('lpy', '1234', 'F', '0193891563'),
('lzx', '1234', 'F', '0122351563'),
('yxk', '1234', 'F', '0193801563');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `ADMIN`
--
ALTER TABLE `ADMIN`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `RESCUER`
--
ALTER TABLE `RESCUER`
  ADD PRIMARY KEY (`RID`);

--
-- Indexes for table `SOS`
--
ALTER TABLE `SOS`
  ADD PRIMARY KEY (`SOSID`),
  ADD KEY `USERNAME` (`USERNAME`),
  ADD KEY `RID` (`RID`),
  ADD KEY `SID` (`SID`);

--
-- Indexes for table `STATION`
--
ALTER TABLE `STATION`
  ADD PRIMARY KEY (`SID`);

--
-- Indexes for table `USERS`
--
ALTER TABLE `USERS`
  ADD PRIMARY KEY (`USERNAME`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `RESCUER`
--
ALTER TABLE `RESCUER`
  MODIFY `RID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `SOS`
--
ALTER TABLE `SOS`
  MODIFY `SOSID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `STATION`
--
ALTER TABLE `STATION`
  MODIFY `SID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `SOS`
--
ALTER TABLE `SOS`
  ADD CONSTRAINT `sos_ibfk_1` FOREIGN KEY (`USERNAME`) REFERENCES `USERS` (`USERNAME`),
  ADD CONSTRAINT `sos_ibfk_2` FOREIGN KEY (`RID`) REFERENCES `RESCUER` (`RID`),
  ADD CONSTRAINT `sos_ibfk_3` FOREIGN KEY (`SID`) REFERENCES `STATION` (`SID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
