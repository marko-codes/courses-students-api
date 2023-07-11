insert into COURSE_TBL (abbreviation, fee, modules, title)
            VALUES ('RJS', 2400, 12, 'React JS');
insert into COURSE_TBL (abbreviation, fee, modules, title)
            VALUES ('spring', 2800, 12, 'Spring Boot');
insert into COURSE_TBL (abbreviation, fee, modules, title)
            VALUES ('NG', 2200, 12, 'Angular');

insert into STUDENT_TBL (age, department, name)
            VALUES (20, 'FRONTEND', 'Petar Petrovic');
insert into STUDENT_TBL (age, department, name)
            VALUES (19, 'BACKEND', 'Jake Malone');
insert into STUDENT_TBL (age, department, name)
            VALUES (23, 'FULLSTACK', 'Marko Maricic');

insert into STUDENT_COURSE_TBL (student_id, course_id)
            VALUES (3, 1);
insert into STUDENT_COURSE_TBL (student_id, course_id)
            VALUES (3, 2);
insert into STUDENT_COURSE_TBL (student_id, course_id)
            VALUES (3, 3);
insert into STUDENT_COURSE_TBL (student_id, course_id)
            VALUES (2, 2);
insert into STUDENT_COURSE_TBL (student_id, course_id)
            VALUES (1, 1);
insert into STUDENT_COURSE_TBL (student_id, course_id)
            VALUES (1, 3);