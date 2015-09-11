CREATE TABLE cliente
(
  partita_iva numeric(15,0) NOT NULL,
  email character varying(32),
  nome character varying(32) NOT NULL,
  telefono character varying(30),
  indirizzo character varying(64) NOT NULL,
  CONSTRAINT cliente_pkey PRIMARY KEY (partita_iva)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE cliente
  OWNER TO postgres;
