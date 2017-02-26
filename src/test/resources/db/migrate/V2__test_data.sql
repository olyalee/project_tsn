INSERT INTO realtyx.users (login, psw)
 SELECT 'ivan', 'ivanpsw';

INSERT INTO realtyx.contacts(login, name, email, country_code, region_code, phone_number)
 SELECT 'ivan', 'Ivan', 'ivan@gmail.com', 7, 812, 5555555);