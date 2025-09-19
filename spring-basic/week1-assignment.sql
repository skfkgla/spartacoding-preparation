-- 문제1
CREATE TABLE IF NOT EXISTS MANAGER
(
    id bigint primary key,
    name varchar(100) not null,
    student_code varchar(100) not null,
    CONSTRAINT manager_fk_student_code foreign key(student_code) references student(student_code)
);

-- 문제2
ALTER TABLE MANAGER MODIFY COLUMN id bigint auto_increment;

-- 문제3
INSERT INTO MANAGER(name, student_code) VALUES('managerA', 's1');
INSERT INTO MANAGER(name, student_code) VALUES('managerA', 's2');
INSERT INTO MANAGER(name, student_code) VALUES('managerA', 's3');
INSERT INTO MANAGER(name, student_code) VALUES('managerA', 's4');
INSERT INTO MANAGER(name, student_code) VALUES('managerA', 's5');

INSERT INTO MANAGER(name, student_code) VALUES('managerB', 's6');
INSERT INTO MANAGER(name, student_code) VALUES('managerB', 's7');
INSERT INTO MANAGER(name, student_code) VALUES('managerB', 's8');
INSERT INTO MANAGER(name, student_code) VALUES('managerB', 's9');

-- 문제4
SELECT s.name, e.exam_seq, e.score
FROM MANAGER m JOIN STUDENT S on m.student_code  = s.student_code 
JOIN EXAM e on m.student_code  = e.student_code WHERE m.name = 'managerA';

-- 문제5
ALTER TABLE EXAM DROP CONSTRAINT exam_fk_student_code;
ALTER TABLE EXAM ADD CONSTRAINT exam_fk_student_code FOREIGN KEY(student_code) REFERENCES STUDENT(student_code) ON DELETE CASCADE;
ALTER TABLE MANAGER DROP CONSTRAINT manager_fk_student_code;
ALTER TABLE MANAGER ADD CONSTRAINT manager_fk_student_code FOREIGN KEY(student_code) REFERENCES STUDENT(student_code) ON DELETE CASCADE;

DELETE FROM STUDENT WHERE student_code = 's1';
