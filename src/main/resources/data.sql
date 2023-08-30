INSERT INTO persons (user_name, password, first_name, last_name, birthday, role) VALUES ('admin', '$2a$10$BbReyrJCZr5g27uqM/wiq.TvVFNVgRhhDnlC9AUBCsvEHAZqkcM22', 'Александр', 'Иванов', '1990-05-05', 'ROLE_RECORDKEEPER');
INSERT INTO persons (user_name, password, first_name, last_name, birthday, role) VALUES ('specialist', '$2a$10$kQR.rnkgmeOsN/HnenuOR.fuKyDoLYwF7xgWDe2d03GPXeSkU9ZaO', 'someFirstName', 'someLastName', '1990-05-05', 'ROLE_SPECIALIST');
INSERT INTO persons (user_name, password, first_name, last_name, birthday, role) VALUES ('specialist2', '$2a$10$kQR.rnkgmeOsN/HnenuOR.fuKyDoLYwF7xgWDe2d03GPXeSkU9ZaO', 'someFirstName', 'someLastName', '1990-05-05', 'ROLE_SPECIALIST');
INSERT INTO persons (user_name, password, first_name, last_name, birthday, role) VALUES ('specialist3', '$2a$10$kQR.rnkgmeOsN/HnenuOR.fuKyDoLYwF7xgWDe2d03GPXeSkU9ZaO', 'someFirstName', 'someLastName', '1990-05-05', 'ROLE_SPECIALIST');
INSERT INTO persons (user_name, password, first_name, last_name, birthday, role) VALUES ('specialist4', '$2a$10$kQR.rnkgmeOsN/HnenuOR.fuKyDoLYwF7xgWDe2d03GPXeSkU9ZaO', 'someFirstName', 'someLastName', '1990-05-05', 'ROLE_SPECIALIST');
INSERT INTO persons (user_name, password, first_name, last_name, birthday, role) VALUES ('user', '$2a$10$rf57EjEi5PGJItx91tFc/.8RGHeE1QfQvaTczX76mMLyjuPCmM.VS', 'Юлия', 'Попова', '1990-05-05', 'ROLE_PATIENT');

INSERT INTO professional_activity (specialty_name, degree_certificate, date_of_employment, persons_person_id) VALUES ('Бактериолог', '123', '1990-05-05', '1');
INSERT INTO professional_activity (specialty_name, degree_certificate, date_of_employment, persons_person_id) VALUES ('Анестезиолог', '123', '1990-05-05', '2');
INSERT INTO professional_activity (specialty_name, degree_certificate, date_of_employment, persons_person_id) VALUES ('Хирург', '123', '1990-05-05', '3');
INSERT INTO professional_activity (specialty_name, degree_certificate, date_of_employment, persons_person_id) VALUES ('Анестезиолог', '123', '1990-05-05', '4');
INSERT INTO professional_activity (specialty_name, degree_certificate, date_of_employment, persons_person_id) VALUES ('Бактериолог', '123', '1990-05-05', '5');
INSERT INTO professional_activity (specialty_name, degree_certificate, date_of_employment, persons_person_id) VALUES ('Хирург', '123', '1990-05-05', '6');