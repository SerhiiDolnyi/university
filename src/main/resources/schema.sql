DROP TABLE IF EXISTS groups CASCADE;
DROP TABLE IF EXISTS students CASCADE;
DROP TABLE IF EXISTS teachers CASCADE;
DROP TABLE IF EXISTS timetables CASCADE;
DROP TABLE IF EXISTS university CASCADE;
DROP TABLE IF EXISTS department_content CASCADE;

CREATE TABLE IF NOT EXISTS groups (
						group_id SERIAL,
                        group_name VARCHAR(5),
                        PRIMARY KEY (group_id),
                        UNIQUE (group_id)
                        );
                        
CREATE TABLE IF NOT EXISTS students (
                        student_id SERIAL,
                        group_id int,
                        first_name VARCHAR (20) NOT NULL,
                        last_name VARCHAR (20) NOT NULL,
                        contact VARCHAR (20) NOT NULL,
                        PRIMARY KEY (student_id),
                        FOREIGN KEY (group_id) REFERENCES groups (group_id) ON UPDATE CASCADE ON DELETE CASCADE,
                        UNIQUE (student_id)
                        );

CREATE TABLE IF NOT EXISTS teachers (
                        teacher_id SERIAL,
                        first_name VARCHAR (20) NOT NULL,
                        last_name VARCHAR (20) NOT NULL,
                        contact VARCHAR (20) NOT NULL,
                        department_id int,
                        position VARCHAR (20) NOT NULL,
                        PRIMARY KEY (teacher_id),
                        FOREIGN KEY (department_id) REFERENCES university (department_id) ON UPDATE CASCADE ON DELETE CASCADE,
                        UNIQUE (teacher_id)
                        );

CREATE TABLE IF NOT EXISTS timetables (
						timetable_id SERIAL,
						timetable_date DATE NOT NULL,
						start_lecture TIME NOT NULL,
						end_lecture TIME NOT NULL,
						location VARCHAR (20) NOT NULL,
						group_id  int,
						subject VARCHAR (20) NOT NULL,
						teacher_id int,
						PRIMARY KEY (timetable_id),
						FOREIGN KEY (group_id) REFERENCES groups (group_id) ON UPDATE CASCADE ON DELETE CASCADE,
						FOREIGN KEY (teacher_id) REFERENCES teachers (teacher_id) ON UPDATE CASCADE ON DELETE CASCADE,
						UNIQUE (timetable_id)
						);

CREATE TABLE IF NOT EXISTS university (
						department_id SERIAL,
						department_name VARCHAR (20) NOT NULL,
						PRIMARY KEY (department_id),
						UNIQUE (department_id)
						);
						
CREATE TABLE IF NOT EXISTS department_content (
						departmentContent_id SERIAL,
						department_id int,						
						student_id int,
						group_id int,
						teacher_id int,
						timetable_id int,
						PRIMARY KEY (departmentContent_id),
						FOREIGN KEY (department_id) REFERENCES university (department_id) ON UPDATE CASCADE ON DELETE CASCADE,
						FOREIGN KEY (teacher_id) REFERENCES teachers (teacher_id) ON UPDATE CASCADE ON DELETE CASCADE,
						FOREIGN KEY (student_id) REFERENCES students (student_id) ON UPDATE CASCADE ON DELETE CASCADE,
						FOREIGN KEY (group_id) REFERENCES groups (group_id) ON UPDATE CASCADE ON DELETE CASCADE,
						FOREIGN KEY (timetable_id) REFERENCES timetables (timetable_id) ON UPDATE CASCADE ON DELETE CASCADE,
						UNIQUE (departmentContent_id)
						);


						