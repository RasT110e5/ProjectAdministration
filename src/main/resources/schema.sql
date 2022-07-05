CREATE TABLE EMPLOYEE
(
    ID            IDENTITY PRIMARY KEY,
    NAME          TEXT,
    COST_PER_HOUR INTEGER
);

CREATE TABLE PROJECT
(
    ID   IDENTITY PRIMARY KEY,
    NAME TEXT
);

CREATE TABLE EMPLOYEE_PROJECT
(
    EMPLOYEE INTEGER,
    PROJECT  INTEGER,
    PRIMARY KEY (EMPLOYEE, PROJECT)
);
