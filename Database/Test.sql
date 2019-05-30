CREATE DATABASE javabase DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
CREATE USER 'java'@'localhost' IDENTIFIED BY 'password';
GRANT ALL ON javabase.* TO 'java'@'localhost' IDENTIFIED BY 'password';