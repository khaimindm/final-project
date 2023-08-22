INSERT INTO Persons (user_name, password, first_name, last_name, birthday, role) VALUES ('admin', '$2a$10$BbReyrJCZr5g27uqM/wiq.TvVFNVgRhhDnlC9AUBCsvEHAZqkcM22', 'someFirstName', 'someLastName', '1990-05-05', 'ROLE_RECORDKEEPER');
INSERT INTO Persons (user_name, password, first_name, last_name, birthday, role) VALUES ('specialist', '$2a$10$kQR.rnkgmeOsN/HnenuOR.fuKyDoLYwF7xgWDe2d03GPXeSkU9ZaO', 'someFirstName', 'someLastName', '1990-05-05', 'ROLE_SPECIALIST');
INSERT INTO Persons (user_name, password, first_name, last_name, birthday, role) VALUES ('user', '$2a$10$rf57EjEi5PGJItx91tFc/.8RGHeE1QfQvaTczX76mMLyjuPCmM.VS', 'someFirstName', 'someLastName', '1990-05-05', 'ROLE_PATIENT');

INSERT INTO professional_activity (specialty_name, degree_certificate, date_of_employment, persons_person_id) VALUES ('Бактериолог', '123', '1990-05-05', '2');
INSERT INTO professional_activity (specialty_name, degree_certificate, date_of_employment, persons_person_id) VALUES ('Анестезиолог', '123', '1990-05-05', '1');
INSERT INTO professional_activity (specialty_name, degree_certificate, date_of_employment, persons_person_id) VALUES ('Хирург', '123', '1990-05-05', '3');
INSERT INTO professional_activity (specialty_name, degree_certificate, date_of_employment, persons_person_id) VALUES ('Эндокринолог', '123', '1990-05-05', '2');
INSERT INTO professional_activity (specialty_name, degree_certificate, date_of_employment, persons_person_id) VALUES ('Дерматолог', '123', '1990-05-05', '2');