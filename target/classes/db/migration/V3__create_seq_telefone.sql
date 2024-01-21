CREATE SEQUENCE telefone_seq START WITH 1 INCREMENT BY 1 NO MINVALUE MAXVALUE 2147483647 CACHE 1; 
CREATE TABLE telefone( id integer NOT NULL DEFAULT nextval('telefone_seq'::regclass), numero character varying(50) COLLATE pg_catalog."default" NOT NULL, usuario_pai_id bigint NOT NULL, usuario_cad_id bigint NOT NULL,CONSTRAINT telefone_pkey PRIMARY KEY (id), CONSTRAINT usuario_cad_fkey FOREIGN KEY (usuario_cad_id) REFERENCES model_login (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION, CONSTRAINT usuario_pai_fkey FOREIGN KEY (usuario_pai_id) REFERENCES model_login (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION);