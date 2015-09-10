package it.unipd.dei.primalinea;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Tester {
	private SessionFactory sessionFactory;

	public Tester() {
		initSessionFactory();
	}

	/**
	 * Inizializza una SessionFactory
	 */
	private void initSessionFactory() {
		Configuration config = new Configuration().configure("hibernate.cfg.xml");

		// Crea un Registry usando la configurazione appena definita
		StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder()
				.applySettings(config.getProperties());
		ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();

		// Crea la SessionFactory a partire dalla configurazione e dal Registry
		sessionFactory = config.buildSessionFactory(serviceRegistry);
	}

	/**
	 * Testa l'inserimento di dati in ogni tabella, per verificare che la
	 * mappatura delle classi sia corretta. Sebbene istanziare i vari Set non
	 * sia necessario (è sufficiente usare il metodo get[nomeSet] delle classi,
	 * vedi metodo successivo), si è scelto comunque di farlo per rendere più
	 * esplicativo il codice.
	 */
	private void populateDatabase() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		Set<Cliente> clienti = new HashSet<Cliente>();
		Cliente cliente = new Cliente(new BigDecimal("452361897528965"), "dior@gmail.com", "Dior", "0444589623",
				"via Roma 2, 36040 Grisignano di Zocco (VI)");

		Set<Ordine> ordini = new HashSet<Ordine>();
		Calendar c = Calendar.getInstance();
		c.set(2015, Calendar.AUGUST, 6);
		Ordine ordine1 = new Ordine("123456789012345", new BigDecimal(50000.00), c.getTime(), cliente);
		c.set(2015, Calendar.JULY, 1);
		Ordine ordine2 = new Ordine("543210987654321", new BigDecimal(25000.00), c.getTime(), cliente);

		Set<Modello> modelli = new HashSet<Modello>();
		Modello modello1 = new Modello(1, cliente);
		Modello modello2 = new Modello(2, cliente);

		Set<Tessuto> tessuti = new HashSet<Tessuto>();
		Tessuto tessuto1 = new Tessuto(400, "nero");
		tessuti.add(tessuto1);
		Tessuto tessuto2 = new Tessuto(100, "blu");
		tessuti.add(tessuto2);

		Set<Filato> filati = new HashSet<Filato>();
		Filato filato1 = new Filato(30, 517, "Coats Cucirini", new BigDecimal(44.00));
		filati.add(filato1);
		Filato filato2 = new Filato(120, 349, "DMC", new BigDecimal(50.00));
		filati.add(filato2);

		Set<Materiale> materiali = new HashSet<Materiale>();
		Materiale materiale1 = new Materiale(1);
		materiali.add(materiale1);

		Set<Articolo> articoli = new HashSet<Articolo>();
		Articolo articolo1 = new Articolo();
		articolo1.setCodice(1);
		articolo1.setLavorazione(null);
		articolo1.setTipo("giacca");
		articolo1.setQuantita(30);
		articolo1.setReferences(ordine1, materiale1, modello1);

		Set<Consegna> consegne = new HashSet<Consegna>();
		c.set(2015, Calendar.AUGUST, 6);
		Consegna consegna1 = new Consegna(c.getTime(), articolo1, 5, "Bartolini", false);
		consegne.add(consegna1);
		articolo1.setConsegne(consegne);

		articoli.add(articolo1);

		modello1.setArticoli(articoli);
		materiale1.setArticoli(articoli);
		ordine1.setArticoli(articoli);

		articoli.removeAll(articoli);
		Articolo articolo2 = new Articolo();
		articolo2.setCodice(2);
		articolo2.setLavorazione(null);
		articolo2.setTipo("cappotto");
		articolo2.setQuantita(40);
		articolo2.setReferences(ordine1, materiale1, modello2);
		articoli.add(articolo2);
		modello2.setArticoli(articoli);

		modelli.add(modello1);
		modelli.add(modello2);
		ordini.add(ordine1);
		ordini.add(ordine2);

		materiale1.setTessuti(tessuti);
		tessuto1.setMateriali(materiali);
		tessuto2.setMateriali(materiali);

		materiale1.setFilati(filati);
		filato1.setMateriali(materiali);
		filato2.setMateriali(materiali);

		cliente.setModelli(modelli);
		cliente.setOrdini(ordini);

		cliente.setTessuti(tessuti);
		clienti.add(cliente);
		tessuto1.setClienti(clienti);
		tessuto2.setClienti(clienti);

		session.save(articolo1);
		session.save(consegna1);
		session.save(modello1);
		session.save(materiale1);
		session.save(tessuto1);
		session.save(tessuto2);
		session.save(filato1);
		session.save(filato2);

		session.save(cliente);

		session.getTransaction().commit();

		System.out.println("Done");
	}

	/**
	 * Aggiunge un tessuto, facente parte di un Materiale già esistente, al
	 * database. A differenza del metodo precedente non si istanzia
	 * esplicitamente un oggetto di tipo Set <Materiale>, ma si usa quello già
	 * definito dal costruttore di Tessuto, richiamato con il metodo
	 * getMateriali().
	 */
	private void addTessuto() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Tessuto tessuto1 = new Tessuto(150, "rosso");

		Materiale materiale1 = (Materiale) session.get(Materiale.class, 1);
		tessuto1.getMateriali().add(materiale1);

		session.save(tessuto1);

		session.getTransaction().commit();
		System.out.println("Done");
	}

	/**
	 * Aggiunge la consegna di un articolo al database
	 * 
	 * @param articolo
	 *            l'articolo consegnato
	 * @param quantitaConsegnata
	 *            la quantità consegnata
	 * @param dataConsegna
	 *            la data in cui è avvenuta la consegna
	 * @param corriere
	 *            il corriere che ha effettuato la consegna
	 */
	public void addConsegnaOfArticolo(Articolo articolo, int quantitaConsegnata, Date dataConsegna, String corriere) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		Consegna consegna = new Consegna(dataConsegna, articolo, quantitaConsegnata, corriere, false);
		articolo.getConsegne().add(consegna);
		session.save(consegna);

		session.getTransaction().commit();
		System.out.println("Done");
	}

	/**
	 * Aggiunge un cliente ed un tessuto al database. Metodo usato per testare
	 * l'associazione molti a molti tra Tessuto e Cliente.
	 */
	public void addClienteAndTessuto() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		Cliente cliente = new Cliente(new BigDecimal("452361897528965"), "dior@gmail.com", "Dior", "0444589623",
				"via Roma 2, 36040 Grisignano di Zocco (VI)");
		session.save(cliente);

		Tessuto tessuto = new Tessuto(420, "nero");
		session.save(tessuto);

		cliente.getTessuti().add(tessuto);
		session.saveOrUpdate(cliente);

		tessuto.getClienti().add(cliente);
		session.saveOrUpdate(tessuto);

		session.getTransaction().commit();

		System.out.println("Done");
	}

	/**
	 * Trova un Ordine a partire da un numero di fattura
	 * 
	 * @param numeroFattura
	 *            il numero di fattura di cui si vuole ricavare l'ordine
	 * @return l'ordine corrispondente al numero di fattura fornito
	 */
	public Ordine findOrdineFromFattura(String numeroFattura) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Ordine ordine = (Ordine) session.get(Ordine.class, numeroFattura);
		if (ordine == null) {
			System.out.println("Nessun ordine corrispondente a questo numero fattura");
		} else {
			System.out.println("Ordine trovato");
			System.out.println("--------------------------------------------------------------");
		}
		session.close();
		return ordine;
	}

	/**
	 * Trova un Articolo a partire dal suo codice
	 * 
	 * @param codice
	 *            il codice dell'articolo cercato
	 * @return l'articolo corrispondente al codice fornito
	 */
	public Articolo findArticoloFromCodice(int codice) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Articolo articolo = (Articolo) session.get(Articolo.class, codice);
		if (articolo == null) {
			System.out.println("Nessun articolo corrispondente a questo codice");
		} else {
			System.out.println("Articolo trovato");
			System.out.println("--------------------------------------------------------------");
		}
		session.close();
		return articolo;
	}

	/**
	 * Stampa gli attributi degli articoli appartenenti ad un ordine
	 * 
	 * @param ordine
	 *            l'ordine i cui articoli si vogliono visualizzare
	 */
	public void findArticoliOfOrdine(Ordine ordine) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("from Articolo where ordine=:ordineId");
		query.setString("ordineId", ordine.getNumeroFattura());
		Iterator queryIterator = query.list().iterator();
		while (queryIterator.hasNext()) {
			Articolo articolo = (Articolo) queryIterator.next();
			System.out.println("Codice: " + articolo.getCodice());
			System.out.println("Quantita: " + articolo.getQuantita());
			System.out.println("Lavorazione: " + articolo.getLavorazione());
			System.out.println("Materiale: " + articolo.getMateriale().getId());
			System.out.println("Modello: " + articolo.getModello().getNumero());
			System.out.println("--------------------------------------------------------------");
		}
		session.close();
	}

	/**
	 * Stampa i dettagli dei tessuti e dei filati che compongono un dato
	 * articolo
	 * 
	 * @param articolo
	 *            l'articolo di cui interessano i dettagli di tessuti e filati
	 */
	public void findDetailsOfArticolo(Articolo articolo) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		String tessutoQuery = "SELECT T.tessutoKey.codice, T.tessutoKey.varianteColore FROM Articolo A JOIN A.materiale M JOIN M.tessuti T WHERE A.codice=:articoloId";
		Query query = session.createQuery(tessutoQuery);
		query.setInteger("articoloId", articolo.getCodice());
		Iterator queryIterator = query.list().iterator();
		while (queryIterator.hasNext()) {
			Object[] tessuto = (Object[]) queryIterator.next();
			System.out.println("Codice tessuto: " + tessuto[0]);
			System.out.println("Colore tessuto: " + tessuto[1]);
			System.out.println("--------------------------------------------------------------");
		}
		String filatoQuery = "SELECT F.filatoKey.titolo, F.filatoKey.codiceColore, F.filatoKey.fornitore, F.costo FROM Articolo A JOIN A.materiale M JOIN M.filati F WHERE A.codice=:articoloId";
		query = session.createQuery(filatoQuery);
		query.setInteger("articoloId", articolo.getCodice());
		queryIterator = query.list().iterator();
		while (queryIterator.hasNext()) {
			Object[] filato = (Object[]) queryIterator.next();
			System.out.println("Titolo filato: " + filato[0]);
			System.out.println("Colore filato: " + filato[1]);
			System.out.println("Fornitore filato: " + filato[2]);
			System.out.println("Costo filato: " + filato[3]);
			System.out.println("--------------------------------------------------------------");
		}
	}

	/**
	 * Trova la quantità consegnata totale di un dato articolo
	 * 
	 * @param articolo
	 *            l'articolo di cui interessa la quantità consegnata totale
	 * @return la quantità consegnata totale dell'articolo fornito
	 */
	public long findTotalQuantitaConsegnataOfArticolo(Articolo articolo) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("SELECT sum(quantitaConsegnata) FROM Consegna WHERE articolo=:articoloId");
		query.setInteger("articoloId", articolo.getCodice());
		List results = query.list();
		return (Long) results.get(0);
	}

	/**
	 * Stampa i dettagli del Materiale usato per un dato Articolo
	 * 
	 * @param codice
	 *            il codice dell'Articolo di cui interessa il Materiale
	 */
	public void materialiPerArticolo(int codice) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Articolo articolo = (Articolo) session.get(Articolo.class, codice);
		if (articolo == null) {
			System.out.println("Il codice inserito non corrisponde a nessun articolo");
			return;
		}
		String materialiQuery = "SELECT T.tessutoKey.codice, T.tessutoKey.varianteColore, F.filatoKey.titolo, F.filatoKey.codiceColore FROM Articolo A JOIN A.materiale M JOIN M.filati F JOIN M.tessuti T WHERE A.codice=:articoloId";
		Query query = session.createQuery(materialiQuery);
		query.setInteger("articoloId", articolo.getCodice());
		Iterator queryIterator = query.list().iterator();
		while (queryIterator.hasNext()) {
			Object[] materiale = (Object[]) queryIterator.next();
			System.out.print("Codice tessuto: " + materiale[0] + ", ");
			System.out.print("colore tessuto: " + materiale[1] + ", ");
			System.out.print("titolo filato: " + materiale[2] + ", ");
			System.out.println("colore filato: " + materiale[3]);
		}
	}

	/**
	 * Stampa partita Iva e nome dei clienti che hanno effettuato ordini per un
	 * totale maggiore di un dato ammontare di euro
	 * 
	 * @param prezzo
	 *            l'ammontare di euro
	 */
	public void clientiPerPrezzo(BigDecimal prezzo) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		String prezzoQuery = "SELECT C.partitaIva, C.nome FROM Cliente C JOIN C.ordini O GROUP BY C.partitaIva HAVING sum(O.prezzo) >=:prezzo";
		Query query = session.createQuery(prezzoQuery);
		query.setBigDecimal("prezzo", prezzo);
		Iterator queryIterator = query.list().iterator();
		while (queryIterator.hasNext()) {
			Object[] clienti = (Object[]) queryIterator.next();
			System.out.print("Partita IVA: " + clienti[0] + ", ");
			System.out.println("nome: " + clienti[1]);
		}
	}

	/**
	 * Stampa la data del primo ordine effettuato da un Cliente
	 * @param partitaIva la partitaIva del Cliente
	 */
	public void dataPrimoOrdine(BigDecimal partitaIva) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Cliente cliente = (Cliente) session.get(Cliente.class, partitaIva);
		if (cliente == null) {
			System.out.println("La partita IVA inserita non corrisponde a nessun cliente");
			return;
		}
		String dataQuery = "SELECT min(O.dataOrdine) FROM Cliente C JOIN C.ordini O WHERE C.partitaIva=:partitaIva";
		Query query = session.createQuery(dataQuery);
		query.setBigDecimal("partitaIva", cliente.getPartitaIva());
		List dataOrdine = query.list();
		System.out.println(dataOrdine.get(0));
	}

	public static void main(String[] args) {
		Tester test = new Tester();
		// Ordine ordine = test.findOrdineFromFattura("201505");
		Articolo articolo = test.findArticoloFromCodice(232);
		Calendar c = Calendar.getInstance();
		c.set(2015, Calendar.AUGUST, 7);
		test.addConsegnaOfArticolo(articolo, 10, c.getTime(), "Bartolini");
		// System.out.println(test.findTotalQuantitaConsegnataOfArticolo(articolo));
	}
}
