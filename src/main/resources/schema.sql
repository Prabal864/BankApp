<<<<<<< HEAD
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
=======
CREATE TABLE IF NOT EXISTS `cards` (
                                       `card_id` int NOT NULL AUTO_INCREMENT,
                                       `mobile_number` varchar(15) NOT NULL,
    `card_number` varchar(100) NOT NULL,
    `card_type` varchar(100) NOT NULL,
    `total_limit` int NOT NULL,
    `amount_used` int NOT NULL,
    `available_amount` int NOT NULL,
>>>>>>> origin/Cards_Microservice
    `created_at` date NOT NULL,
    `created_by` varchar(20) NOT NULL,
    `updated_at` date DEFAULT NULL,
    `updated_by` varchar(20) DEFAULT NULL,
<<<<<<< HEAD
    FOREIGN KEY (`customer_id`) REFERENCES `customer`(`customer_id`)
=======
    PRIMARY KEY (`card_id`)
>>>>>>> origin/Cards_Microservice
    );