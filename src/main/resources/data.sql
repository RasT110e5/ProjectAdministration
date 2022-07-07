INSERT INTO EMPLOYEE (NAME, COST_PER_HOUR)
VALUES ('Roque Sosa', 10);
INSERT INTO EMPLOYEE (NAME, COST_PER_HOUR)
VALUES ('Eduardo Paveto', 20);
INSERT INTO EMPLOYEE (NAME, COST_PER_HOUR)
VALUES ('Nicolas Garcia', 30);
INSERT INTO EMPLOYEE (NAME, COST_PER_HOUR)
VALUES ('Chester Roscoe', 10);
INSERT INTO EMPLOYEE (NAME, COST_PER_HOUR)
VALUES ('Russel Mccall', 102);
INSERT INTO EMPLOYEE (NAME, COST_PER_HOUR)
VALUES ('Erick Adams', 87);
INSERT INTO EMPLOYEE (NAME, COST_PER_HOUR)
VALUES ('Harvey Wellington', 93);
INSERT INTO EMPLOYEE (NAME, COST_PER_HOUR)
VALUES ('Benny Herbert', 22);
INSERT INTO EMPLOYEE (NAME, COST_PER_HOUR)
VALUES ('Brad Morrison', 82);
INSERT INTO EMPLOYEE (NAME, COST_PER_HOUR)
VALUES ('Carter Murray', 119);
INSERT INTO EMPLOYEE (NAME, COST_PER_HOUR)
VALUES ('Josh Vangness', 95);
INSERT INTO EMPLOYEE (NAME, COST_PER_HOUR)
VALUES ('Roger Crawford', 7);
INSERT INTO EMPLOYEE (NAME, COST_PER_HOUR)
VALUES ('William Uttley', 112);
INSERT INTO EMPLOYEE (NAME, COST_PER_HOUR)
VALUES ('Marvin Knight', 38);
INSERT INTO EMPLOYEE (NAME, COST_PER_HOUR)
VALUES ('Luke Richards', 111);
INSERT INTO EMPLOYEE (NAME, COST_PER_HOUR)
VALUES ('Javier Warner', 119);
INSERT INTO EMPLOYEE (NAME, COST_PER_HOUR)
VALUES ('Chad Nayler', 64);
INSERT INTO EMPLOYEE (NAME, COST_PER_HOUR)
VALUES ('Tyler Shaw', 70);
INSERT INTO EMPLOYEE (NAME, COST_PER_HOUR)
VALUES ('Tyler Owen', 39);
INSERT INTO EMPLOYEE (NAME, COST_PER_HOUR)
VALUES ('Chris Allcott', 86);
INSERT INTO EMPLOYEE (NAME, COST_PER_HOUR)
VALUES ('Martin Hill', 8);
INSERT INTO EMPLOYEE (NAME, COST_PER_HOUR)
VALUES ('Hank Michael', 57);
INSERT INTO EMPLOYEE (NAME, COST_PER_HOUR)
VALUES ('Ron Ainsworth', 57);
INSERT INTO EMPLOYEE (NAME, COST_PER_HOUR)
VALUES ('David Logan', 83);
INSERT INTO EMPLOYEE (NAME, COST_PER_HOUR)
VALUES ('Sebastian Lee', 97);
INSERT INTO EMPLOYEE (NAME, COST_PER_HOUR)
VALUES ('Mason Abbot', 39);
INSERT INTO EMPLOYEE (NAME, COST_PER_HOUR)
VALUES ('Bob Sinclair', 8);
INSERT INTO EMPLOYEE (NAME, COST_PER_HOUR)
VALUES ('Domenic Collingwood', 111);

INSERT INTO PROJECT (NAME)
VALUES ('Plan EOY Party');
INSERT INTO EMPLOYEE_PROJECT (EMPLOYEE, PROJECT)
VALUES (1, 1);
INSERT INTO EMPLOYEE_PROJECT (EMPLOYEE, PROJECT)
VALUES (3, 1);
INSERT INTO EMPLOYEE_PROJECT (EMPLOYEE, PROJECT)
VALUES (2, 1);
INSERT INTO EMPLOYEE_PROJECT (EMPLOYEE, PROJECT)
VALUES (20, 1);
INSERT INTO EMPLOYEE_PROJECT (EMPLOYEE, PROJECT)
VALUES (12, 1);
INSERT INTO EMPLOYEE_PROJECT (EMPLOYEE, PROJECT)
VALUES (13, 1);
INSERT INTO EMPLOYEE_PROJECT (EMPLOYEE, PROJECT)
VALUES (15, 1);
INSERT INTO EMPLOYEE_PROJECT (EMPLOYEE, PROJECT)
VALUES (16, 1);

INSERT INTO TASK (NAME, DESCRIPTION, ESTIMATED_HOURS, CREATED_DATE, ACTUAL_DURATION, STATUS,
                  PROJECT, ASSIGNED_EMPLOYEE)
VALUES ('Book Venue',
        'Find and send deposit for a venue to host the event',
        3,
        {ts '2022-07-06 18:47:52'},
        1,
        'OPEN',
        1,
        20);
INSERT INTO TASK (NAME, DESCRIPTION, ESTIMATED_HOURS, CREATED_DATE, ACTUAL_DURATION, STATUS,
                  PROJECT, ASSIGNED_EMPLOYEE)
VALUES ('Find Catering',
        'Find and send deposit for a catering company to serve in the event',
        3,
        {ts '2022-07-06 18:48:52'},
        1,
        'IN_PROGRESS',
        1,
        20);
INSERT INTO TASK (NAME, DESCRIPTION, ESTIMATED_HOURS, CREATED_DATE, ACTUAL_DURATION, STATUS,
                  PROJECT, ASSIGNED_EMPLOYEE)
VALUES ('Send Invites',
        'Send invites to employees with all the details of the event',
        2,
        {ts '2022-07-06 18:49:52'},
        3,
        'REVIEW',
        1,
        20);
INSERT INTO TASK (NAME, DESCRIPTION, CREATED_DATE, ESTIMATED_HOURS, ACTUAL_DURATION, STATUS,
                  PROJECT, ASSIGNED_EMPLOYEE)
VALUES ('Book entertainment A',
        'Look over the options for entertainment A and negotiate a price for the event',
        {ts '2022-07-06 11:00:00'},
        3,
        4,
        'CLOSED',
        1,
        15);
INSERT INTO TASK (NAME, DESCRIPTION, CREATED_DATE, ESTIMATED_HOURS, ACTUAL_DURATION, STATUS,
                  PROJECT, ASSIGNED_EMPLOYEE)
VALUES ('Book entertainment B',
        'Look over the options for entertainment B and negotiate a price for the event',
        {ts '2022-07-06 11:00:00'},
        3,
        2,
        'OPEN',
        1,
        13);
INSERT INTO TASK (NAME, DESCRIPTION, CREATED_DATE, ESTIMATED_HOURS, ACTUAL_DURATION, STATUS,
                  PROJECT, ASSIGNED_EMPLOYEE)
VALUES ('Book entertainment C',
        'Look over the options for entertainment C and negotiate a price for the event',
        {ts '2022-07-06 11:00:00'},
        6,
        2,
        'IN_PROGRESS',
        1,
        12);
INSERT INTO TASK (NAME, DESCRIPTION, CREATED_DATE, ESTIMATED_HOURS, ACTUAL_DURATION, STATUS,
                  PROJECT, ASSIGNED_EMPLOYEE)
VALUES ('Book entertainment D',
        'Look over the options for entertainment D and negotiate a price for the event',
        {ts '2022-07-06 11:00:00'},
        3,
        10,
        'REVIEW',
        1,
        16);

INSERT INTO PROJECT (NAME)
VALUES ('Create Q2 Report');
INSERT INTO EMPLOYEE_PROJECT (EMPLOYEE, PROJECT)
VALUES (1, 2);
INSERT INTO EMPLOYEE_PROJECT (EMPLOYEE, PROJECT)
VALUES (9, 2);
INSERT INTO EMPLOYEE_PROJECT (EMPLOYEE, PROJECT)
VALUES (10, 2);
INSERT INTO EMPLOYEE_PROJECT (EMPLOYEE, PROJECT)
VALUES (11, 2);
INSERT INTO TASK (NAME, DESCRIPTION, ESTIMATED_HOURS, CREATED_DATE, ACTUAL_DURATION, STATUS,
                  PROJECT, ASSIGNED_EMPLOYEE)
VALUES ('Prepare template using enterprise tool',
        'Download and set the tool to prepare a template we can use as the base for the report',
        10,
        {ts '2022-07-06 18:49:52'},
        24,
        'CLOSED',
        2,
        9);
INSERT INTO TASK (NAME, DESCRIPTION, CREATED_DATE, ESTIMATED_HOURS, ACTUAL_DURATION, STATUS,
                  PROJECT, ASSIGNED_EMPLOYEE)
VALUES ('Add data from department A',
        'Talk with the director of department A and agree on the data they need to provide us to prepare the report',
        {ts '2022-07-06 11:00:00'},
        3,
        5,
        'OPEN',
        2,
        11);
INSERT INTO TASK (NAME, DESCRIPTION, CREATED_DATE, ESTIMATED_HOURS, ACTUAL_DURATION, STATUS,
                  PROJECT, ASSIGNED_EMPLOYEE)
VALUES ('Add data from department B',
        'Talk with the director of department B and agree on the data they need to provide us to prepare the report',
        {ts '2022-07-06 11:00:00'},
        3,
        1,
        'IN_PROGRESS',
        2,
        9);
INSERT INTO TASK (NAME, DESCRIPTION, CREATED_DATE, ESTIMATED_HOURS, ACTUAL_DURATION, STATUS,
                  PROJECT, ASSIGNED_EMPLOYEE)
VALUES ('Add data from department C',
        'Talk with the director of department C and agree on the data they need to provide us to prepare the report',
        {ts '2022-07-06 11:00:00'},
        3,
        7,
        'REVIEW',
        2,
        11);
INSERT INTO TASK (NAME, DESCRIPTION, CREATED_DATE, ESTIMATED_HOURS, ACTUAL_DURATION, STATUS,
                  PROJECT, ASSIGNED_EMPLOYEE)
VALUES ('Add data from department D',
        'Talk with the director of department D and agree on the data they need to provide us to prepare the report',
        {ts '2022-07-06 11:00:00'},
        3,
        1,
        'CLOSED',
        2,
        10);

INSERT INTO PROJECT (NAME)
VALUES ('Inventory System Application');
INSERT INTO EMPLOYEE_PROJECT (EMPLOYEE, PROJECT)
VALUES (1, 3);
INSERT INTO EMPLOYEE_PROJECT (EMPLOYEE, PROJECT)
VALUES (2, 3);
INSERT INTO EMPLOYEE_PROJECT (EMPLOYEE, PROJECT)
VALUES (3, 3);
INSERT INTO EMPLOYEE_PROJECT (EMPLOYEE, PROJECT)
VALUES (10, 3);
INSERT INTO EMPLOYEE_PROJECT (EMPLOYEE, PROJECT)
VALUES (20, 3);
INSERT INTO EMPLOYEE_PROJECT (EMPLOYEE, PROJECT)
VALUES (22, 3);
INSERT INTO EMPLOYEE_PROJECT (EMPLOYEE, PROJECT)
VALUES (17, 3);
INSERT INTO TASK (NAME, DESCRIPTION, ESTIMATED_HOURS, CREATED_DATE, ACTUAL_DURATION, STATUS,
                  PROJECT, ASSIGNED_EMPLOYEE)
VALUES ('SetUp local dev environment',
        'Download and setup all the technologies a developer needs locally to work in this project',
        10,
        {ts '2022-07-06 18:49:52'},
        24,
        'OPEN',
        3,
        1);
INSERT INTO TASK (NAME, DESCRIPTION, ESTIMATED_HOURS, CREATED_DATE, ACTUAL_DURATION, STATUS,
                  PROJECT, ASSIGNED_EMPLOYEE)
VALUES ('Create Inventory View',
        'Taking the response from the inventory datasource create a view to display the information correctly',
        24,
        {ts '2022-07-06 18:49:52'},
        6,
        'IN_PROGRESS',
        3,
        2);
INSERT INTO TASK (NAME, DESCRIPTION, ESTIMATED_HOURS, CREATED_DATE, ACTUAL_DURATION, STATUS,
                  PROJECT, ASSIGNED_EMPLOYEE)
VALUES ('Create Devices View',
        'Taking the response from the backend create a view to display the information correctly',
        10,
        {ts '2022-07-06 18:49:52'},
        24,
        'REVIEW',
        3,
        2);
INSERT INTO TASK (NAME, DESCRIPTION, ESTIMATED_HOURS, CREATED_DATE, ACTUAL_DURATION, STATUS,
                  PROJECT, ASSIGNED_EMPLOYEE)
VALUES ('Create Devices Rest endpoint',
        'Create rest endpoint to return a response with all the information of our devices by location',
        10,
        {ts '2022-07-06 18:49:52'},
        24,
        'CLOSED',
        3,
        3);
INSERT INTO TASK (NAME, DESCRIPTION, ESTIMATED_HOURS, CREATED_DATE, ACTUAL_DURATION, STATUS,
                  PROJECT, ASSIGNED_EMPLOYEE)
VALUES ('SetUp pipeline for dev deployment',
        'Configure a Jenkinsfile to deploy completely our application to our development environment',
        24,
        {ts '2022-07-06 18:49:52'},
        12,
        'OPEN',
        3,
        10);
INSERT INTO TASK (NAME, DESCRIPTION, ESTIMATED_HOURS, CREATED_DATE, ACTUAL_DURATION, STATUS,
                  PROJECT, ASSIGNED_EMPLOYEE)
VALUES ('SetUp pipeline for uat deployment',
        'Configure a Jenkinsfile to deploy completely our application to our uat environment',
        23,
        {ts '2022-07-06 18:49:52'},
        10,
        'IN_PROGRESS',
        3,
        10);
INSERT INTO TASK (NAME, DESCRIPTION, ESTIMATED_HOURS, CREATED_DATE, ACTUAL_DURATION, STATUS,
                  PROJECT, ASSIGNED_EMPLOYEE)
VALUES ('SetUp pipeline for qa deployment',
        'Configure a Jenkinsfile to deploy completely our application to our qa environment',
        12,
        {ts '2022-07-06 18:49:52'},
        2,
        'REVIEW',
        3,
        17);
INSERT INTO TASK (NAME, DESCRIPTION, ESTIMATED_HOURS, CREATED_DATE, ACTUAL_DURATION, STATUS,
                  PROJECT, ASSIGNED_EMPLOYEE)
VALUES ('SetUp pipeline for prod deployment',
        'Configure a Jenkinsfile to deploy completely our application to our qa environment',
        36,
        {ts '2022-07-06 18:49:52'},
        10,
        'CLOSED',
        3,
        17);
INSERT INTO TASK (NAME, DESCRIPTION, ESTIMATED_HOURS, CREATED_DATE, ACTUAL_DURATION, STATUS,
                  PROJECT, ASSIGNED_EMPLOYEE)
VALUES ('Implement feature A',
        'Talk with users that request feature A, design the feature and implement it',
        8,
        {ts '2022-07-06 18:49:52'},
        1,
        'OPEN',
        3,
        1);
INSERT INTO TASK (NAME, DESCRIPTION, ESTIMATED_HOURS, CREATED_DATE, ACTUAL_DURATION, STATUS,
                  PROJECT, ASSIGNED_EMPLOYEE)
VALUES ('Implement feature B',
        'Talk with users that request feature B, design the feature and implement it',
        3,
        {ts '2022-07-06 18:49:52'},
        2,
        'IN_PROGRESS',
        3,
        1);
INSERT INTO TASK (NAME, DESCRIPTION, ESTIMATED_HOURS, CREATED_DATE, ACTUAL_DURATION, STATUS,
                  PROJECT, ASSIGNED_EMPLOYEE)
VALUES ('Implement feature C',
        'Talk with users that request feature C, design the feature and implement it',
        12,
        {ts '2022-07-06 18:49:52'},
        5,
        'REVIEW',
        3,
        20);
INSERT INTO TASK (NAME, DESCRIPTION, ESTIMATED_HOURS, CREATED_DATE, ACTUAL_DURATION, STATUS,
                  PROJECT, ASSIGNED_EMPLOYEE)
VALUES ('Implement feature D',
        'Talk with users that request feature D, design the feature and implement it',
        10,
        {ts '2022-07-06 18:49:52'},
        3,
        'CLOSED',
        3,
        22);
