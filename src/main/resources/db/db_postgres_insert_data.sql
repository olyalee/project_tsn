

INSERT INTO public.users (login, password_hash, first_name, last_name, gender, birth_date, email, country, city, science_field, working_place, position)
VALUES ('alex', '�7\���)��F���d', 'Alexandr', 'Alexeev', 'MALE', to_date('1963-09-01', 'YYYY-MM-DD'), 'alex@gmail.com', 'Russia', 'Moscow', 'Math', 'MGU', 'lecturer');

INSERT INTO public.users (login, password_hash, first_name, last_name, gender, birth_date, email, country, city, science_field, working_place, position)
VALUES ('elena', '�S<��L[�cj�O�', 'Elena', 'Smirnova', 'FEMALE', to_date('1983-09-01', 'YYYY-MM-DD'), 'elena@gmail.com', 'Russia', 'Saint-Petesburg', 'Math', 'SPbGU', 'lecturer');

INSERT INTO public.users (login, password_hash, first_name, last_name, gender, birth_date, email, country, city, science_field, working_place, position)
VALUES ('petr', 'YV����Wq/���e��', 'Petr', 'Petrov', 'MALE', to_date('1971-11-11', 'YYYY-MM-DD'), 'petr@gmail.com', 'Russia', 'Moscow', 'Astronomy', 'MGU', 'lecturer');

INSERT INTO public.users (login, password_hash, first_name, last_name, gender, birth_date, email, country, city, science_field, working_place, position)
VALUES ('mark', '�g?M�Ǚ"����JA�', 'Mark', 'Markov', 'MALE', to_date('1978-02-01', 'YYYY-MM-DD'), 'mark@gmail.com', 'Russia', 'SPbGU', 'Astronomy', 'SPbGU', 'lecturer');


INSERT INTO public.education (education_id, login, education_type, place_type, place_title, major, start_year, end_year)
VALUES (nextval('education_id_seq'), 'elena', 'бакалавр', 'Университет', 'СПбГУ', 'Прикладная математика', 2008, 2012);

INSERT INTO public.education (education_id, login, education_type, place_type, place_title, major, start_year, end_year)
VALUES (nextval('education_id_seq'), 'elena', 'магистратура', 'Университет', 'СПбГУ', 'Прикладная математика', 2012, 2014);

INSERT INTO public.education (education_id, login, education_type, place_type, place_title, major, start_year, end_year)
VALUES (nextval('education_id_seq'), 'alex', 'специалитет', 'Университет', 'МГУ', 'Прикладная математика', 1988, 1993);

INSERT INTO public.education (education_id, login, education_type, place_type, place_title, major, start_year, end_year)
VALUES (nextval('education_id_seq'), 'alex', 'магистратура', 'Университет', 'МГУ', 'Прикладная математика', 2012, 2014);



INSERT INTO public.communities (community_id, title)
VALUES (nextval('community_id_seq'), 'Математики');

INSERT INTO public.communities (community_id, title)
VALUES (nextval('community_id_seq'), 'Научные сотрудники МГУ');

INSERT INTO public.communities (community_id, title)
VALUES (nextval('community_id_seq'), 'Астрономы');



INSERT INTO public.community_members (community_id, login)
VALUES (1, 'alex');

INSERT INTO public.community_members (community_id, login)
VALUES (1, 'elena');

INSERT INTO public.community_members (community_id, login)
VALUES (2, 'alex');

INSERT INTO public.community_members (community_id, login)
VALUES (2, 'petr');

INSERT INTO public.community_members (community_id, login)
VALUES (3, 'mark');

INSERT INTO public.community_members (community_id, login)
VALUES (3, 'petr');



INSERT INTO public.colleagues (login, colleague_login)
VALUES ('mark', 'elena');

INSERT INTO public.colleagues (login, colleague_login)
VALUES ('elena', 'mark');

INSERT INTO public.colleagues (login, colleague_login)
VALUES ('alex', 'petr');

INSERT INTO public.colleagues (login, colleague_login)
VALUES ('petr', 'alex');


INSERT INTO public.community_posts (post_id, community_id, login, text, time)
VALUES (nextval('post_id_seq'), 1, 'alex', 'Планируется научная конференция', '2017-2-1'::timestamp);

INSERT INTO public.community_posts (post_id, community_id, login, text, time)
VALUES (nextval('post_id_seq'), 1, 'elena', 'Интересная задача','2017-2-3'::timestamp);

INSERT INTO public.community_posts (post_id, community_id, login, text, time)
VALUES (nextval('post_id_seq'), 1, 'mark', 'Разработан новый курс лекций', '2017-2-2'::timestamp );


INSERT INTO public.messages (message_id, from_user, to_user, text, time)
VALUES (nextval('message_id_seq'), 'mark', 'elena', 'hello', '2017-3-2'::timestamp);

INSERT INTO public.messages (message_id, from_user, to_user, text, time)
VALUES (nextval('message_id_seq'), 'elena', 'mark', 'hi', '2017-3-2'::timestamp);

INSERT INTO public.messages (message_id, from_user, to_user, text, time)
VALUES (nextval('message_id_seq'), 'alex', 'petr', 'Приезжайте на конференцию', '2017-3-2'::timestamp);

INSERT INTO public.messages (message_id, from_user, to_user, text, time)
VALUES (nextval('message_id_seq'), 'petr', 'alex', 'Спасибо за приглашение', '2017-3-2'::timestamp);