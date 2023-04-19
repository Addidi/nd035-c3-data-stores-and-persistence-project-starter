SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

USE critter_db;

CREATE TABLE `customer` (
  `id` bigint(20) PRIMARY KEY AUTO_INCREMENT NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL
);

CREATE TABLE `customer_pet_ids` (
  `customer_id` bigint(20) NOT NULL,
  `pet_ids` bigint(20) DEFAULT NULL,
  foreign key (`customer_id`) references customer(`id`)
);

CREATE TABLE `employee` (
  `id` bigint(20) PRIMARY KEY AUTO_INCREMENT NOT NULL,
  `name` varchar(255) DEFAULT NULL
);

CREATE TABLE `employee_days_available` (
  `employee_id` bigint(20) NOT NULL,
  `days_available` int(11) DEFAULT NULL,
   foreign key (`employee_id`) references employee(`id`)
);

CREATE TABLE `employee_skills` (
  `employee_id` bigint(20) NOT NULL,
  `skills` int(11) DEFAULT NULL,
  foreign key (`employee_id`) references employee(`id`)
);

CREATE TABLE `pet_data` (
  `id` bigint(20) PRIMARY KEY AUTO_INCREMENT NOT NULL,
  `birth_date` date DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL,
  `owner_id` bigint(20) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL
);

CREATE TABLE `schedule` (
  `id` bigint(20) PRIMARY KEY AUTO_INCREMENT NOT NULL,
  `date` date DEFAULT NULL
);

CREATE TABLE `schedule_activities` (
  `schedule_id` bigint(20) NOT NULL,
  `activities` int(11) DEFAULT NULL,
  foreign key (`schedule_id`) references schedule(`id`)
);

CREATE TABLE `schedule_employee_ids` (
  `schedule_id` bigint(20) NOT NULL,
  `employee_ids` bigint(20) DEFAULT NULL,
  foreign key (`schedule_id`) references schedule(`id`)
);

CREATE TABLE `schedule_pet_ids` (
  `schedule_id` bigint(20) NOT NULL,
  `pet_ids` bigint(20) DEFAULT NULL,
  foreign key (`schedule_id`) references schedule(`id`)
);
COMMIT;

