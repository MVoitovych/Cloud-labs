CREATE DATABASE IF NOT EXISTS firedepartment;
USE firedepartment;

DROP TABLE IF EXISTS `emergency_call`;
CREATE TABLE `emergency_call` (
    id int NOT NULL AUTO_INCREMENT,
    short_description varchar(40) NULL,
    departure_id int NOT NULL,
    CONSTRAINT call_pk PRIMARY KEY (id)
);

DROP TABLE IF EXISTS departure_firecar;
CREATE TABLE departure_firecar (
    departure_id int NOT NULL,
    firecar_id int NOT NULL,
    CONSTRAINT departure_firecar_pk PRIMARY KEY (departure_id,firecar_id)
);

DROP TABLE IF EXISTS departure_fireman;
CREATE TABLE departure_fireman (
    departure_id int NOT NULL,
    fireman_id int NOT NULL,
    CONSTRAINT departure_fireman_pk PRIMARY KEY (departure_id,fireman_id)
);

DROP TABLE IF EXISTS departure;
CREATE TABLE departure (
    id int NOT NULL AUTO_INCREMENT,
    short_description varchar(255) NULL,
    CONSTRAINT departure_pk PRIMARY KEY (id)
);

DROP TABLE IF EXISTS firecar;
CREATE TABLE firecar (
    id int NOT NULL AUTO_INCREMENT,
    mark varchar(25) NULL,
    CONSTRAINT firecar_pk PRIMARY KEY (id)
);

DROP TABLE IF EXISTS fireman;
CREATE TABLE fireman (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(25) NOT NULL,
    surname varchar(25) NOT NULL,
    CONSTRAINT fireman_pk PRIMARY KEY (id)
);

ALTER TABLE `emergency_call` ADD CONSTRAINT call_departure 
FOREIGN KEY call_departure (departure_id)
    REFERENCES departure (id);

ALTER TABLE departure_firecar ADD CONSTRAINT departure_firecar_departure 
FOREIGN KEY departure_firecar_departure (departure_id)
    REFERENCES departure (id);

ALTER TABLE departure_firecar ADD CONSTRAINT departure_firecar_firecar 
FOREIGN KEY departure_firecar_firecar (firecar_id)
    REFERENCES firecar (id);

ALTER TABLE departure_fireman ADD CONSTRAINT departure_fireman_departure 
FOREIGN KEY departure_fireman_departure (departure_id)
    REFERENCES departure (id);

ALTER TABLE departure_fireman ADD CONSTRAINT departure_fireman_fireman 
FOREIGN KEY departure_fireman_fireman (fireman_id)
    REFERENCES fireman (id);


-- Inserts

INSERT INTO fireman(name, surname) VALUES
('name1', 'surename1'),
('name2', 'surename2'),
('name3', 'surename3'),
('name4', 'surename4'),
('name5', 'surename5'),
('name6', 'surename6'),
('name7', 'surename7'),
('name8', 'surename8'),
('name9', 'surename9'),
('name10', 'surename10');

INSERT INTO firecar(mark) VALUES 
('mark1'),
('mark2'),
('mark3'),
('mark4'),
('mark5'),
('mark6'),
('mark7'),
('mark8'),
('mark9'),
('mark10');

INSERT INTO departure (short_description) VALUES 
('description1'),
('description2'),
('description3'),
('description4'),
('description5'),
('description6'),
('description7'),
('description8'),
('description9'),
('description10');

INSERT INTO departure_firecar(departure_id, firecar_id) VALUES 
(1, 1),
(1, 2),
(1, 3),
(2, 1),
(2, 2),
(2, 3),
(3, 1),
(3, 2),
(3, 5),
(4, 10);

INSERT INTO departure_fireman(departure_id, fireman_id) VALUES
(4, 10), 
(1, 1),
(1, 2),
(1, 3),
(2, 1),
(2, 10),
(3, 10),
(5, 9),
(6, 10),
(7, 10);

INSERT INTO `emergency_call` (short_description, departure_id) VALUES
('description1', 10),
('description2', 9),
('description3', 8),
('description4', 7),
('description5', 6),
('description6', 5),
('description7', 4),
('description8', 3),
('description9', 2),
('description10',1);

