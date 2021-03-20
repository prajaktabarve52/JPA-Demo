CREATE DATABASE IF NOT EXISTS tutorialDb;

USE tutorialDb;



CREATE TABLE "department" (
  "department_id" int(11) NOT NULL AUTO_INCREMENT,
  "name" varchar(45) NOT NULL,
  "region" varchar(45) NOT NULL,
  PRIMARY KEY ("department_id"),
  UNIQUE KEY "idx_dept_name" ("name")
);

CREATE TABLE "employee" (
  "employee_id" int(11) NOT NULL AUTO_INCREMENT,
  "username" varchar(45) DEFAULT NULL,
  "password" varchar(45) DEFAULT NULL,
  "age" int(11) DEFAULT NULL,
  "ctc" int(11) DEFAULT NULL,
  "joindate" date DEFAULT NULL,
  "status" varchar(45) DEFAULT NULL,
  "department_id" int(11) DEFAULT NULL,
  "city" varchar(45) DEFAULT NULL,
  "pin" varchar(45) DEFAULT NULL,
  "state" varchar(45) DEFAULT NULL,
  "country" varchar(45) DEFAULT NULL,
  PRIMARY KEY ("employee_id"),
  KEY "FK_DEPT" ("department_id"),
  CONSTRAINT "FK_DEPT" FOREIGN KEY ("department_id") REFERENCES "department" ("department_id")
);
