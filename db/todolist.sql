-- Table: public.item
-- DROP TABLE public.item;

CREATE TABLE public.item
(
    id integer NOT NULL DEFAULT nextval('item_id_seq'::regclass) ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    description character varying(250) COLLATE pg_catalog."default",
    created date,
    done boolean,
    CONSTRAINT item_pk PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.item
    OWNER to postgres;


-- drop table public.todo_item;
-- drop table public.todo_user;

CREATE TABLE todo_user (
   id SERIAL PRIMARY KEY,
   email Varchar(20) unique,
   name Varchar(20),
   password Varchar(20),
   Constraint user_uq UNIQUE(email)
);
CREATE TABLE todo_item (
    id SERIAL PRIMARY KEY,
    user_id SERIAL,
	description character varying(250) COLLATE pg_catalog."default",
    created date,
    done boolean,
    CONSTRAINT item_fk Foreign KEY (user_id) References todo_user(id)
);