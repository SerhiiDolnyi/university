delete from students where student_id = 73;

select * from students;

--get timetable of students for day
SELECT timetables.timetable_id, timetables.timetable_date, timetables.start_lecture, 
timetables.end_lecture, location, timetables.group_id, timetables.subject, timetables.teacher_id 
from timetables
inner join department_content on department_content.timetable_id = timetables.timetable_id
where department_content.student_id = 1 and timetables.timetable_date = '2022-01-01'
order by timetables.start_lecture;

--get timetable of students for month
SELECT timetables.timetable_id, timetables.timetable_date, timetables.start_lecture, 
timetables.end_lecture, location, timetables.group_id, timetables.subject, timetables.teacher_id 
from timetables
inner join department_content on department_content.timetable_id = timetables.timetable_id
where department_content.student_id = 1 and extract(month from timetables.timetable_date) = 1
order by timetables.timetable_date;

--get timetable of TEACHER for day
SELECT timetables.timetable_id, timetables.timetable_date, timetables.start_lecture, 
timetables.end_lecture, location, timetables.group_id, timetables.subject, timetables.teacher_id 
from timetables
inner join department_content on department_content.timetable_id = timetables.timetable_id
where department_content.teacher_id = 2 and timetables.timetable_date = '2022-01-02'
order by timetables.start_lecture;

--get timetable of TEACHER for month
SELECT timetables.timetable_id, timetables.timetable_date, timetables.start_lecture, 
timetables.end_lecture, location, timetables.group_id, timetables.subject, timetables.teacher_id 
from timetables
inner join department_content on department_content.timetable_id = timetables.timetable_id
where department_content.teacher_id = 2 and extract(month from timetables.timetable_date) = 1
order by timetables.timetable_date;
