CREATE TABLE IF NOT EXISTS `customer` (
    `customer_id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(100) NOT NULL,
    `email` varchar(100) NOT NULL,
    `mobile_number` varchar(20) NOT NULL,
    `created_at` date NOT NULL,
    `created_by` varchar(20) NOT NULL,
    `updated_at` date DEFAULT NULL,
    `updated_by` varchar(20) DEFAULT NULL
    );

CREATE TABLE IF NOT EXISTS `accounts` (
    `account_id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `customer_id` BIGINT NOT NULL,
    `account_number` BIGINT NOT NULL,
    `branch_address` varchar(200) NOT NULL,
    `Communication_sw` BOOLEAN
    `created_at` date NOT NULL,
    `created_by` varchar(20) NOT NULL,
    `updated_at` date DEFAULT NULL,
    `updated_by` varchar(20) DEFAULT NULL,
    FOREIGN KEY (`customer_id`) REFERENCES `customer`(`customer_id`)
    );