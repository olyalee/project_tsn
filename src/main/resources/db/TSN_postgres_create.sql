DROP DATABASE IF EXISTS "tsn"

CREATE DATABASE "tsn"
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'en_US.UTF-8'
       LC_CTYPE = 'en_US.UTF-8'
       CONNECTION LIMIT = -1;


CREATE TABLE "users" (
	"login" varchar NOT NULL UNIQUE,
	"password_hash" varchar NOT NULL,
	"first_name" varchar NOT NULL,
	"last_name" varchar NOT NULL,
	"gender" varchar NOT NULL,
	"birth_date" DATE NOT NULL,
	"email" varchar NOT NULL,
	"country" varchar NOT NULL,
	"city" varchar NOT NULL,
	"science_field" varchar NOT NULL,
	"working_place" varchar NOT NULL,
	"position" varchar NOT NULL,
	CONSTRAINT users_pk PRIMARY KEY ("login")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "messages" (
	"message_id" serial NOT NULL,
	"from_user" varchar NOT NULL,
	"to_user" varchar NOT NULL,
	"text" varchar NOT NULL,
	"time" TIMESTAMP NOT NULL,
	CONSTRAINT messages_pk PRIMARY KEY ("message_id")
) WITH (
  OIDS=FALSE
);

CREATE SEQUENCE message_id_seq START WITH 1;
CREATE SEQUENCE education_id_seq START WITH 1;
CREATE SEQUENCE post_id_seq START WITH 1;
CREATE SEQUENCE community_id_seq START WITH 1;


CREATE TABLE "communities" (
	"community_id" serial NOT NULL,
	"title" varchar NOT NULL,
	CONSTRAINT communities_pk PRIMARY KEY ("community_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "community_members" (
	"community_id" integer NOT NULL,
	"login" varchar NOT NULL,
	CONSTRAINT community_members_pk PRIMARY KEY ("community_id","login")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "community_posts" (
	"post_id" serial NOT NULL,
	"community_id" integer NOT NULL,
	"login" varchar NOT NULL,
	"text" varchar NOT NULL,
	"time" TIMESTAMP NOT NULL,
	CONSTRAINT community_posts_pk PRIMARY KEY ("post_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "education" (
	"education_id" integer NOT NULL,
	"login" varchar NOT NULL,
	"education_type" varchar NOT NULL,
	"place_type" varchar NOT NULL,
	"place_title" varchar NOT NULL,
	"major" varchar NOT NULL,
	"start_year" integer NOT NULL,
	"end_year" integer,
	CONSTRAINT education_pk PRIMARY KEY ("education_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "colleagues" (
	"login" varchar NOT NULL,
	"colleague_login" varchar NOT NULL,
	CONSTRAINT colleagues_pk PRIMARY KEY ("login","colleague_login")
) WITH (
  OIDS=FALSE
);




ALTER TABLE ONLY "messages" ADD CONSTRAINT "messages_fk0" FOREIGN KEY ("from_user") REFERENCES "users"("login") ON DELETE CASCADE;
ALTER TABLE ONLY "messages" ADD CONSTRAINT "messages_fk1" FOREIGN KEY ("to_user") REFERENCES "users"("login") ON DELETE CASCADE;


ALTER TABLE ONLY "community_members" ADD CONSTRAINT "community_members_fk0" FOREIGN KEY ("community_id") REFERENCES "communities"("community_id") ON DELETE CASCADE;
ALTER TABLE ONLY "community_members" ADD CONSTRAINT "community_members_fk1" FOREIGN KEY ("login") REFERENCES "users"("login") ON DELETE CASCADE;

ALTER TABLE ONLY "community_posts" ADD CONSTRAINT "community_posts_fk0" FOREIGN KEY ("community_id") REFERENCES "communities"("community_id") ON DELETE CASCADE;
ALTER TABLE ONLY "community_posts" ADD CONSTRAINT "community_posts_fk1" FOREIGN KEY ("login") REFERENCES "users"("login") ON DELETE CASCADE;

ALTER TABLE ONLY "education" ADD CONSTRAINT "education_fk0" FOREIGN KEY ("login") REFERENCES "users"("login") ON DELETE CASCADE;

ALTER TABLE ONLY "colleagues" ADD CONSTRAINT "colleagues_fk0" FOREIGN KEY ("login") REFERENCES "users"("login") ON DELETE CASCADE;
ALTER TABLE ONLY "colleagues" ADD CONSTRAINT "colleagues_fk1" FOREIGN KEY ("colleague_login") REFERENCES "users"("login") ON DELETE CASCADE;

