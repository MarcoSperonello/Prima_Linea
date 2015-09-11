CREATE TABLE ordine
(
  numero_fattura character varying(255) NOT NULL,
  prezzo numeric(9,2) NOT NULL,
  data_ordine date NOT NULL,
  data_completamento date,
  cliente numeric(15,0),
  CONSTRAINT ordine_pkey PRIMARY KEY (numero_fattura),
  CONSTRAINT fk_fxwj7jnjdexcrnps0954p65om FOREIGN KEY (cliente)
      REFERENCES cliente (partita_iva) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT ordine_prezzo_check CHECK (prezzo >= 0::numeric)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ordine
  OWNER TO postgres;
