CREATE TABLE "public"."users"
(
   username varchar(45) PRIMARY KEY NOT NULL,
   password varchar(60) NOT NULL,
   enabled smallint DEFAULT 1 NOT NULL,
   avatar bytea,
   firstname varchar(45),
   lastname varchar(45),
   position varchar(45)
)
;
CREATE UNIQUE INDEX users_pkey ON "public"."users"(username)
;



CREATE TABLE "public"."user_roles"
(
   user_role_id int PRIMARY KEY NOT NULL,
   username varchar(45) NOT NULL,
   role varchar(45) NOT NULL
)
;
ALTER TABLE "public"."user_roles"
ADD CONSTRAINT fk_username
FOREIGN KEY (username)
REFERENCES "public"."users"(username)
;
CREATE UNIQUE INDEX ukstlxfukw77ov5w1wo1tm3omca ON "public"."user_roles"
(
  role,
  username
)
;
CREATE UNIQUE INDEX user_roles_pkey ON "public"."user_roles"(user_role_id)
;
CREATE UNIQUE INDEX user_roles_role_username_key ON "public"."user_roles"
(
  role,
  username
)
;



INSERT into users(username, password, enabled, avatar, firstname, lastname, position)
VALUES ('dsartakov', '$2a$10$Teb98Vuq.jhBaz/pfI0p8uKbSBqEEqauILjGkEmJ0l78v0OkHrJRW', 1, NULL, 'Дмитрий', 'Сартаков', 'руководитель департамента управления рисками');

INSERT INTO user_roles (user_role_id,username,role) VALUES (10,'dsartakov','ROLE_OWNER');




/*таблица стоп листа*/

create table stoplist
(
  id serial not null primary key, --Уникальный id записи в стоп-листе * А
  inn decimal(10), --ИНН
  okpo decimal(8), --ОКПО
  fio varchar(255) not null, --ФИО *
  birth_date date,
  pasport_serial varchar(10),
  pasport_number varchar(20),
  create_date timestamp not null default now(),
  edit_date timestamp not null default now(),
  status varchar(1) default 'A' not null,
  remove_date timestamp,
  comment varchar(1000) not null,
  codificator decimal(2) not null,
  create_login_employee varchar(30) not null,
  edit_login_employee varchar(30) not null,
  create_company varchar(255) not null,
  created_department varchar(50) not null,
  source varchar(100)
);

/*таблица кодификаторов*/
create table stoplist_codificators
(
  code int primary key,
  name varchar(255)

);


/*наполнение таблицы кодификаторов*/
insert into stoplist_codificators values(1, 'сотрудники, совершившие хищение или мошенничество');
insert into stoplist_codificators values(2, 'клиенты-воры/мошенники');
insert into stoplist_codificators values(3, 'сотрудники с негативными результатами полиграфа');
insert into stoplist_codificators values(4, 'неблагонадежные партнеры');
insert into stoplist_codificators values(5, 'ранее судимые');

/*Справочник компаний Стоп-листа:*/
create table stoplist_company
(
  name varchar(255) primary key

);

insert into stoplist_company values('OMEGA');
insert into stoplist_company values('RUSH');

/*Справочник подразделений Стоп-листа:*/
create table stoplist_department
(
  name varchar(255) primary key

);
insert into stoplist_department values('Security');
insert into stoplist_department values('Risk');


create table stoplist_source
(
  name varchar(255) primary key

);

insert into stoplist_source values('Заключения расследований Департамента Безопасности');

insert into stoplist_source values('Базы МВД');
insert into stoplist_source values('Открытые реестры');
insert into stoplist_source values('Полиграфологические тесты');



select Left(a[1], 1) as lastName, Left(a[2], 1) as firstName, Left(a[3], 1) as surName, birth_date, COUNT(*)
from (
       select  (regexp_split_to_array(BTRIM(fio), '\s+')), birth_date from stoplist
     ) as dt(a)
GROUP BY lastName, firstName,surName, birth_date
HAVING
  COUNT(*) > 1