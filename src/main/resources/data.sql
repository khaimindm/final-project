INSERT INTO persons (user_name, password, first_name, last_name, birthday, role) VALUES ('admin', '$2a$10$BbReyrJCZr5g27uqM/wiq.TvVFNVgRhhDnlC9AUBCsvEHAZqkcM22', 'Александр', 'Иванов', '1990-05-05', 'ROLE_RECORDKEEPER');
INSERT INTO persons (user_name, password, first_name, last_name, birthday, role) VALUES ('specialist', '$2a$10$kQR.rnkgmeOsN/HnenuOR.fuKyDoLYwF7xgWDe2d03GPXeSkU9ZaO', 'Андрей', 'Иванов', '1990-05-05', 'ROLE_SPECIALIST');
INSERT INTO persons (user_name, password, first_name, last_name, birthday, role) VALUES ('specialist2', '$2a$10$kQR.rnkgmeOsN/HnenuOR.fuKyDoLYwF7xgWDe2d03GPXeSkU9ZaO', 'Юлия', 'Кузьмина', '1990-05-05', 'ROLE_SPECIALIST');
INSERT INTO persons (user_name, password, first_name, last_name, birthday, role) VALUES ('specialist3', '$2a$10$kQR.rnkgmeOsN/HnenuOR.fuKyDoLYwF7xgWDe2d03GPXeSkU9ZaO', 'Анна', 'Попова', '1990-05-05', 'ROLE_SPECIALIST');
INSERT INTO persons (user_name, password, first_name, last_name, birthday, role) VALUES ('specialist4', '$2a$10$kQR.rnkgmeOsN/HnenuOR.fuKyDoLYwF7xgWDe2d03GPXeSkU9ZaO', 'Сергей', 'Ившин', '1990-05-05', 'ROLE_SPECIALIST');
INSERT INTO persons (user_name, password, first_name, last_name, birthday, role) VALUES ('specialist5', '$2a$10$kQR.rnkgmeOsN/HnenuOR.fuKyDoLYwF7xgWDe2d03GPXeSkU9ZaO', 'Евгений', 'Смирнов', '1990-05-05', 'ROLE_SPECIALIST');
INSERT INTO persons (user_name, password, first_name, last_name, birthday, role) VALUES ('user', '$2a$10$rf57EjEi5PGJItx91tFc/.8RGHeE1QfQvaTczX76mMLyjuPCmM.VS', 'Юлия', 'Попова', '1990-05-05', 'ROLE_PATIENT');
INSERT INTO persons (user_name, password, first_name, last_name, birthday, role) VALUES ('user2', '$2a$10$rf57EjEi5PGJItx91tFc/.8RGHeE1QfQvaTczX76mMLyjuPCmM.VS', 'Иван', 'Иванов', '1995-05-05', 'ROLE_PATIENT');
INSERT INTO persons (user_name, password, first_name, last_name, birthday, role) VALUES ('user3', '$2a$10$rf57EjEi5PGJItx91tFc/.8RGHeE1QfQvaTczX76mMLyjuPCmM.VS', 'Анна', 'Кузнецова', '1995-05-05', 'ROLE_PATIENT');
INSERT INTO persons (user_name, password, first_name, last_name, birthday, role) VALUES ('user4', '$2a$10$rf57EjEi5PGJItx91tFc/.8RGHeE1QfQvaTczX76mMLyjuPCmM.VS', 'Наталия', 'Соколова', '1995-05-05', 'ROLE_PATIENT');
INSERT INTO persons (user_name, password, first_name, last_name, birthday, role) VALUES ('user5', '$2a$10$rf57EjEi5PGJItx91tFc/.8RGHeE1QfQvaTczX76mMLyjuPCmM.VS', 'Евгений', 'Лебедев', '1990-05-05', 'ROLE_PATIENT');

INSERT INTO professional_activity (specialty_name, degree_certificate, date_of_employment, persons_person_id) VALUES ('Кардиолог', '123', '1990-05-05', '2');
INSERT INTO professional_activity (specialty_name, degree_certificate, date_of_employment, persons_person_id) VALUES ('Терапевт', '123', '1990-05-05', '3');
INSERT INTO professional_activity (specialty_name, degree_certificate, date_of_employment, persons_person_id) VALUES ('Диетолог', '123', '1990-05-05', '4');
INSERT INTO professional_activity (specialty_name, degree_certificate, date_of_employment, persons_person_id) VALUES ('Хирург', '123', '1990-05-05', '5');
INSERT INTO professional_activity (specialty_name, degree_certificate, date_of_employment, persons_person_id) VALUES ('Хирург', '123', '1990-05-05', '6');

INSERT INTO work_time (date_of_work, work_time_start_at, work_time_end_at, availability, specialty_name, persons_person_id) VALUES('2023-10-10', '08:00', '08:30', false, 'Терапевт', '3');
INSERT INTO work_time (date_of_work, work_time_start_at, work_time_end_at, availability, specialty_name, persons_person_id) VALUES('2023-10-20', '08:00', '08:30', false, 'Кардиолог', '2');

INSERT INTO booking_list (persons_person_id, work_time_id, completed) VALUES('7', '1', true);
INSERT INTO booking_list (persons_person_id, work_time_id, completed) VALUES('7', '2', true);

INSERT INTO medical_cards (examination, diagnosis, assigned_therapy, date_of_appointment, specialist_id, booking_list_id) VALUES('Насморк, боли в горле, головная боль', 'Острое респираторное заболевание', 'Обильное питье, постельный режим', '2023-10-10', '3', '1');
INSERT INTO medical_cards (examination, diagnosis, assigned_therapy, date_of_appointment, specialist_id, booking_list_id) VALUES('Боль в грудной клетке при физической нагрузке. Во время ходьбы боль, резкая усталость в мышцах ног. Боль проходит, если остановиться. Но возникает вновь при продолжении ходьбы.', 'Атеросклероз. Атеросклероз коронарных артерий. Атеросклероз артерий нижних конечностей.', 'Аторвастатин. 10 мг 1 раз/сут.', '2023-10-20', '2', '2');