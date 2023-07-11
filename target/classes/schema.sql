CREATE TABLE COURSE_TBL (
    id bigint not null auto_increment,
    abbreviation varchar(100) not null,
    fee float not null,
    modules int not null,
    title varchar(100) not null,
    primary key (id)
);
CREATE TABLE STUDENT_TBL (
    id bigint not null auto_increment,
    age int not null,
    department varchar(100) not null,
    name varchar(100) not null,
    primary key (id)
);
CREATE TABLE STUDENT_COURSE_TBL (
    student_id bigint not null,
    course_id bigint not null
);