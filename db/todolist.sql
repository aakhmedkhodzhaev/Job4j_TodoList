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