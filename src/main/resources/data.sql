DROP TABLE IF EXISTS employee;

CREATE TABLE employee (
    id INT AUTO_INCREMENT  PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

INSERT INTO employee (id, name) VALUES (1, 'Xavier');

DROP TABLE IF EXISTS task;

CREATE TABLE task (
    id INT AUTO_INCREMENT  PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

INSERT INTO task (id, name) VALUES (1, 'To do !');
