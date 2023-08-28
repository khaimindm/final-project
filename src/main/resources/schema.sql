CREATE TABLE persons (person_id int PRIMARY KEY AUTO_INCREMENT, user_name varchar(100) NOT NULL, password varchar(200) NOT NULL, first_name varchar(255) NOT NULL, last_name varchar(255) NOT NULL, birthday date NOT NULL, role varchar(20) NOT NULL);
CREATE TABLE professional_activity (specialist_id int PRIMARY KEY AUTO_INCREMENT, specialty_name varchar(100) NOT NULL, degree_certificate varchar(100) NOT NULL, date_of_employment date NOT NULL, persons_person_id int NOT NULL, UNIQUE (persons_person_id));
CREATE TABLE work_time (work_time_id int PRIMARY KEY AUTO_INCREMENT, date_of_work date NOT NULL, work_time_start_at time NOT NULL, work_time_end_at time NOT NULL, persons_person_id int NOT NULL, UNIQUE (persons_person_id));