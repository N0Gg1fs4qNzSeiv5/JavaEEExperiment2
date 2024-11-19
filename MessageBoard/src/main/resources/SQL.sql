DROP DATABASE IF EXISTS `javaee_experiment2`;
CREATE DATABASE `javaee_experiment2`;
USE `javaee_experiment2`;

CREATE TABLE `user`
(
    `id`       varchar(36) NOT NULL UNIQUE,
    `username` varchar(16) NOT NULL UNIQUE,
    `password` varchar(16) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `message`
(
    `id`        varchar(36)   NOT NULL UNIQUE,
    `title`     varchar(256)   NOT NULL,
    `content`   varchar(4096) NOT NULL,
    `create_at` datetime      NOT NULL DEFAULT NOW(),
    `user_id`   varchar(36)   NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);

CREATE TABLE `reply`
(
    `id`         varchar(36)   NOT NULL UNIQUE,
    `content`    varchar(1024) NOT NULL,
    `create_at`  datetime      NOT NULL DEFAULT NOW(),
    `user_id`    varchar(36)   NOT NULL,
    `message_id` varchar(36)   NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
    FOREIGN KEY (`message_id`) REFERENCES `message` (`id`)
);