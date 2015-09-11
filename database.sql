CREATE DOMAIN partita_iva AS numeric (15, 0);
CREATE DOMAIN fattura AS varchar(30);

CREATE TABLE cliente (
	partita_iva partita_iva PRIMARY KEY,
	email VARCHAR(32),
	nome VARCHAR(32) NOT NULL,
	telefono VARCHAR(30),
	indirizzo VARCHAR(64) NOT NULL
	);

CREATE TABLE ordine (
	numero_fattura fattura PRIMARY KEY,
	prezzo DECIMAL(9,2) NOT NULL CHECK(prezzo>=0),
	data_ordine DATE NOT NULL,
	data_completamento DATE,
	cliente partita_iva,
	
	FOREIGN KEY (cliente) 
		REFERENCES cliente (partita_iva)
			ON UPDATE CASCADE ON DELETE RESTRICT
	);

CREATE TABLE modello (
	numero INTEGER PRIMARY KEY,
	cliente partita_iva,
	
	FOREIGN KEY (cliente) 
		REFERENCES cliente (partita_iva)
			ON UPDATE CASCADE ON DELETE RESTRICT
	);

CREATE TABLE tessuto (
	codice INTEGER NOT NULL,
	variante_colore VARCHAR(32) NOT NULL,	
	
	PRIMARY KEY (codice, variante_colore)
	);
	
CREATE TABLE filato (
	titolo INTEGER NOT NULL,
	codice_colore INTEGER NOT NULL,
	fornitore VARCHAR(32) NOT NULL,
	costo DECIMAL(4,2) NOT NULL CHECK(costo>=0),
	
	PRIMARY KEY (titolo, codice_colore, fornitore)
	);

CREATE TABLE materiale (
	id INTEGER PRIMARY KEY
	);
		
CREATE TABLE articolo (
	codice INTEGER PRIMARY KEY,
	ordine fattura,
	tipo VARCHAR(16) NOT NULL,
	materiale INTEGER,
	quantita INTEGER NOT NULL CHECK(quantita>0),
	lavorazione VARCHAR(16),
	modello INTEGER,
	
	FOREIGN KEY (ordine)
		REFERENCES ordine(numero_fattura)
			ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (materiale)
		REFERENCES materiale(id)
			ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (modello)
		REFERENCES modello(numero)
			ON UPDATE CASCADE ON DELETE CASCADE
	);
	
CREATE TABLE consegna (
	data_consegna DATE NOT NULL,
	articolo INTEGER NOT NULL,
	quantita_consegnata INTEGER NOT NULL CHECK(quantita_consegnata>=0),
	corriere VARCHAR(32) NOT NULL,
	completata BOOLEAN NOT NULL,

	PRIMARY KEY (data_consegna, articolo),
	
	FOREIGN KEY (articolo) 
		REFERENCES articolo(codice)
			ON UPDATE CASCADE ON DELETE CASCADE
	);
	
CREATE TABLE fornisce_tessuto (	
	codice_tessuto INTEGER NOT NULL,
	colore_tessuto VARCHAR(32) NOT NULL,
	cliente partita_iva NOT NULL,
	
	PRIMARY KEY (codice_tessuto, colore_tessuto, cliente),

	FOREIGN KEY (codice_tessuto, colore_tessuto)
		REFERENCES tessuto (codice,variante_colore)
			ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (cliente)
		REFERENCES cliente(partita_iva)
			ON UPDATE CASCADE ON DELETE SET NULL
	);
	
CREATE TABLE costituito_da_tessuto (
	materiale INTEGER NOT NULL,
	codice_tessuto INTEGER NOT NULL,
	colore_tessuto VARCHAR(32) NOT NULL,
	
	PRIMARY KEY (materiale, codice_tessuto, colore_tessuto),
	
	FOREIGN KEY (materiale)
		REFERENCES materiale(id)
			ON UPDATE CASCADE ON DELETE CASCADE,

	FOREIGN KEY (codice_tessuto, colore_tessuto)
		REFERENCES tessuto (codice, variante_colore)
			ON UPDATE CASCADE ON DELETE CASCADE
	);	

CREATE TABLE costituito_da_filato (
	materiale INTEGER NOT NULL,
	titolo_filato INTEGER NOT NULL,
	colore_filato INTEGER NOT NULL,
	fornitore_filato VARCHAR(32) NOT NULL,
	
	PRIMARY KEY (materiale, titolo_filato, colore_filato, fornitore_filato),
	
	FOREIGN KEY (materiale)
		REFERENCES materiale(id)
			ON UPDATE CASCADE ON DELETE CASCADE,

	FOREIGN KEY (titolo_filato, colore_filato, fornitore_filato)
		REFERENCES filato (titolo, codice_colore, fornitore)
			ON UPDATE CASCADE ON DELETE CASCADE
	);
