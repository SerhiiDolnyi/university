-- INSERT   GROUPS 
insert into groups (group_name) values ('1AA');
insert into groups (group_name) values ('1BB');
insert into groups (group_name) values ('2AA');
insert into groups (group_name) values ('2BB');
insert into groups (group_name) values ('3AA');

-- INSERT STUDENTS 
insert into students (group_id, first_name, last_name, contact) values (1, 'Naima', 'Sheppard', '04411111');
insert into students (group_id, first_name, last_name, contact) values (1, 'Kingston', 'Blackwell', '04422222');
insert into students (group_id, first_name, last_name, contact) values (2, 'Karley', 'Buchanan', '04433333');
insert into students (group_id, first_name, last_name, contact) values (2, 'Edward', 'Robles', '04444444');
insert into students (group_id, first_name, last_name, contact) values (3, 'Vanessa', 'Gibson', '04455555');
insert into students (group_id, first_name, last_name, contact) values (3, 'Karley', 'Mooney', '04466666');
insert into students (group_id, first_name, last_name, contact) values (4, 'Carly', 'Dixon', '04477777');
insert into students (group_id, first_name, last_name, contact) values (4, 'Naima', 'Matthews', '04488888');
insert into students (group_id, first_name, last_name, contact) values (5, 'Karley', 'Solomon', '04499999');
insert into students (group_id, first_name, last_name, contact) values (5, 'Jaqueline', 'Mahoney', '04400000');

-- INSERT INTO university
insert into university (department_name) values ('IT_DEPARTMENT');
insert into university (department_name) values ('MATH_DEPARTMENT');
insert into university (department_name) values ('DESIGN_DEPARTMENT');

-- INSERT INTO teachers 
insert into teachers (first_name, last_name, contact, department_id, position) values ('Hadley', 'Sheppard', '04411111', 1, 'LECTURER');
insert into teachers (first_name, last_name, contact, department_id, position) values ('Ricky', 'Kirby', '04422222', 2, 'SENIOR_LECTURER');
insert into teachers (first_name, last_name, contact, department_id, position) values ('Danielle', 'Silva', '04433333', 3, 'PROFESSOR');
insert into teachers (first_name, last_name, contact, department_id, position) values ('Naima', 'Vargas', '04444444', 1, 'SENIOR_LECTURER');
insert into teachers (first_name, last_name, contact, department_id, position) values ('Eliza', 'Zuniga', '04455555', 2, 'LECTURER');
insert into teachers (first_name, last_name, contact, department_id, position) values ('Alonso', 'Solomon', '04466666', 3, 'PROFESSOR');

-- INSERT INTO timetables
insert into timetables (timetable_date, start_lecture, end_lecture, location, group_id, subject, teacher_id) values('2022-01-01', '09:00:00', '10:00:00', 'Hall 1', 1, 'IT', 1);
insert into timetables (timetable_date, start_lecture, end_lecture, location, group_id, subject, teacher_id) values('2022-01-01', '10:00:00', '11:00:00', 'Hall 1', 1, 'MATH', 3);
insert into timetables (timetable_date, start_lecture, end_lecture, location, group_id, subject, teacher_id) values('2022-01-01', '09:00:00', '10:00:00', 'Hall 2', 2, 'DESIGN', 5);
insert into timetables (timetable_date, start_lecture, end_lecture, location, group_id, subject, teacher_id) values('2022-01-02', '10:00:00', '11:00:00', 'Hall 2', 2, 'IT', 2);
insert into timetables (timetable_date, start_lecture, end_lecture, location, group_id, subject, teacher_id) values('2022-01-02', '09:00:00', '10:00:00', 'Hall 1', 3, 'MATH', 4);
insert into timetables (timetable_date, start_lecture, end_lecture, location, group_id, subject, teacher_id) values('2022-01-02', '10:00:00', '11:00:00', 'Hall 1', 3, 'DESIGN', 6);
insert into timetables (timetable_date, start_lecture, end_lecture, location, group_id, subject, teacher_id) values('2022-01-03', '09:00:00', '10:00:00', 'Hall 2', 4, 'IT', 1);
insert into timetables (timetable_date, start_lecture, end_lecture, location, group_id, subject, teacher_id) values('2022-01-03', '10:00:00', '11:00:00', 'Hall 2', 4, 'MATH', 3);
insert into timetables (timetable_date, start_lecture, end_lecture, location, group_id, subject, teacher_id) values('2022-01-03', '09:00:00', '10:00:00', 'Hall 1', 5, 'DESIGN', 5);
insert into timetables (timetable_date, start_lecture, end_lecture, location, group_id, subject, teacher_id) values('2022-01-04', '10:00:00', '11:00:00', 'Hall 2', 5, 'IT', 2);
insert into timetables (timetable_date, start_lecture, end_lecture, location, group_id, subject, teacher_id) values('2022-01-04', '09:00:00', '10:00:00', 'Hall 2', 1, 'MATH', 4);
insert into timetables (timetable_date, start_lecture, end_lecture, location, group_id, subject, teacher_id) values('2022-01-04', '10:00:00', '11:00:00', 'Hall 2', 1, 'DESIGN', 6);

-- INSERT INTO department_content
insert into department_content (department_id, student_id, group_id, teacher_id, timetable_id) VALUES (1, 1, 1, 1, 1);
insert into department_content (department_id, student_id, group_id, teacher_id, timetable_id) VALUES (2, 2, 1, 3, 2);
insert into department_content (department_id, student_id, group_id, teacher_id, timetable_id) VALUES (3, 3, 2, 5, 3);
insert into department_content (department_id, student_id, group_id, teacher_id, timetable_id) VALUES (1, 4, 2, 2, 4);
insert into department_content (department_id, student_id, group_id, teacher_id, timetable_id) VALUES (2, 5, 3, 4, 5);
insert into department_content (department_id, student_id, group_id, teacher_id, timetable_id) VALUES (3, 6, 3, 6, 6);
insert into department_content (department_id, student_id, group_id, teacher_id, timetable_id) VALUES (1, 7, 4, 1, 7);
insert into department_content (department_id, student_id, group_id, teacher_id, timetable_id) VALUES (2, 8, 4, 3, 8);
insert into department_content (department_id, student_id, group_id, teacher_id, timetable_id) VALUES (3, 9, 5, 5, 9);
insert into department_content (department_id, student_id, group_id, teacher_id, timetable_id) VALUES (1, 10, 5, 2, 10);
insert into department_content (department_id, student_id, group_id, teacher_id, timetable_id) VALUES (2, 1, 1, 4, 11);
insert into department_content (department_id, student_id, group_id, teacher_id, timetable_id) VALUES (3, 2, 1, 6, 12);
