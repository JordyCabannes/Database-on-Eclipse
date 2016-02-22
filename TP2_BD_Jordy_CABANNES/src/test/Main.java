/*
 * auteur : Jordy CABANNES
 */

package test;

import tables.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import java.util.Iterator;

import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;





public class Main {
	public static void main(String[] args) 
	{
		general();
		adressePersonneContact();
	}	
	
	 public static void adressePersonneContact() {
			
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			session.beginTransaction();
			
			Adresse a1 = new Adresse();
			a1.setNumero(156);
			a1.setNom_rue("bd2");
			a1.setPays("France");
			a1.setProvince("Midi-Py");
			a1.setCode_postal("A5A7Z9");
			a1.setVille("Marciac");
			
			Adresse a2 = new Adresse();
			a2.setNumero(157);
			a2.setNom_rue("T");
			a2.setPays("France");
			a2.setProvince("Midi-Py");
			a2.setCode_postal("A5A7Z8");
			a2.setVille("Toulouse");

			Personne_Contact p1 = new Personne_Contact();
			p1.setNom("Jean");
			p1.setPrenom("Bon");
			p1.setAdresse(a1);
			
			Personne_Contact p2 = new Personne_Contact();
			p2.setNom("Jordy");
			p2.setPrenom("cabannes");
			p2.setAdresse(a2);
			
			a1.getListePersonnes().add(p1);
			a1.getListePersonnes().add(p2);
			
			a2.getListePersonnes().add(p1);
			a2.getListePersonnes().add(p2);
			
			session.save(a1);
			session.save(a2);
			
			session.save(p1);
			session.save(p2);
			 
			session.getTransaction().commit();
			
			session.close();
			sf.close();
			
			
			/*Query query=session.createQuery("select * from Adresse");
			List<Adresse> adresses1 = query.list();
			for(Adresse elem: adresse1)
       		{
       	 		System.out.println (elem);
       		}

			//Utilisation d'un alias
			Query query2=session.createQuery("select * from Adresse as adr where adr.id = 1");
			List<Adresse> adresses2 = query2.list();
			for(Adresse elem: adresse2)
       		{
       	 		System.out.println (elem);
       		}

			
			//Utilisation de order by
			Query query3=session.createQuery("select * from Adresse as adr where adr.pays=France order by adr.code_postal");
			List<Adresse> adresses3 = query3.list();
			for(Adresse elem: adresse3)
       		{
       	 		System.out.println (elem);
       		}

			
			//Utilisation de group by et de count
			Query query4=session.createQuery("select count(*) from Adresse as adr where adr.pays=France group by adr.code_postal");
			List<Adresse> adresses4 = query4.list();
			for(Adresse elem: adresse4)
       		{
       	 		System.out.println (elem);
       		}

				
			//Utilisation de update 
			Query query5=session.createQuery("update Adresse set numero=23 where adr.id=2");
			List<Adresse> adresses5 = query5.list();
			for(Adresse elem: adresse5)
       		{
       	 		System.out.println (elem);
       		}

			
			//Utilisation de delete 
			Query query6=session.createQuery("delete from Adresse where adr.id=4");
			List<Adresse> adresses6 = query6.list();
			for(Adresse elem: adresse6)
       		{
       	 		System.out.println (elem);
       		}

			
			//Utilisation de sum
			Query query7=session.createQuery("select sum(emp.salaire_net) from Employe as emp");
			List<Employe> employes1 = query7.list();
			for(Employe elem: employes1)
       		{
       	 		System.out.println (elem);
       		}

			
			//Utilisation de min
			Query query8=session.createQuery("select min(emp.salaire_net) from Employe as emp");
			List<Employe> employes2 = query8.list();
			for(Employe elem: employes2)
       		{
       	 		System.out.println (elem);
       		}
			
			//Utilisation de max
			Query query9=session.createQuery("select max(emp.salaire_net) from Employe as emp");
			List<Employe> employes3 = query9.list();
			for(Employe elem: employes3)
       		{
       	 		System.out.println (elem);
       		}
			
			//Utilisation de avg
			Query query10=session.createQuery("select avg(emp.salaire_net) from Employe as emp");
			List<Employe> employes4 = query10.list();
			for(Employe elem: employes4)
       		{
       	 		System.out.println (elem);
       		}
			
			//Utilisation des sous-requêtes
			Query query11=session.createQuery("select emp.nom from Employe as emp where emp.salaire_net < (select avg(e.salaire_net) from Employe as e)");
			List<Employe> employes5 = query11.list();
			for(Employe elem: employes5)
       		{
       	 		System.out.println (elem);
       		}
       		
			//Utilisation des paramètres nommés
			Query query12=session.createQuery("select * from Employe as emp whereeamp.prenom = :name");
			query12.setString("name", "Marc");
			List<Employe> employes6 = query12.list();
			for(Employe elem: employes6)
       		{
       	 		System.out.println (elem);
       		}
			
			//Itération sur les résultats
			Query query13=session.createQuery("select * from Employe as emp where emp.salaire_net < (select avg(e.salaire_net) from Employe as e)");
			Iterator<Employe> employes7 = query13.iterate();
			while(employes7.hasNext())
			{
				Employe emp = employes7.next();
				System.out.println("Nom" + emp.getNom() + ", Prénom : " + emp.getPrenom());
				
			}
			
			
			//Utilisation des requêtes nommées
			Query query14=session.getNamedQuery(Adresse.GET_ADRESSE_BY_ID).setInteger("id", 1);
			List<Employe> employes8 = query14.list();
			for(Employe elem: employes8)
       		{
       	 		System.out.println (elem);
       		}
			
			//Utilisation de la pagination
			Query query15 = session.createQuery("select * from Employe");
			query.setFirstResult(1);
			query.setMaxResults(10);
			List<Employe> employes9 = query15.list();
			for(Employe elem: employes9)
       		{
       	 		System.out.println (elem);
       		}
			
			//Utlisation de la jointure innerjoin
			Query query16 = session.createQuery("select * from Adresse as adr inner join Employe as emp where adr.id=emp.adresse_id");
			List<Adresse> adresse7 = query16.list();
			for(Adresse elem: adresse7)
       		{
       	 		System.out.println (elem);
       		}*/

	 }
	 
	 public static void general() {
			
			// Lire les enregistrements existants  
			System.out.println("******* Lecture *******");
			List<Adresse> adresses = list();
			System.out.println("Total Adresses: " + adresses.size());
			
			
			// Ajouter de nouveaux enregistrements 
			System.out.println("******* Ecriture *******");
			
			Adresse adr = new Adresse();
			adr.setCode_postal("G5L1A1");
			adr.setNom_rue("rang 4");
			adr.setNumero(1111);
			adr.setPays("CA");
			adr.setProvince("QC");
			adr.setVille("Rimouski");
			
			Personne_Contact p3 = new Personne_Contact();
			p3.setNom("Toto");
			p3.setPrenom("titi");
		
			
			adr.getListePersonnes().add(p3);
			
			Set<Personne_Contact> listeDePersonneContact = new HashSet<Personne_Contact>();
			listeDePersonneContact.add(p3);
			adr.setListePersonnes(listeDePersonneContact);
			
			adr = save(adr);
			adr = read_Adresse(adr.getId());
			System.out.printf("%d %s %s %s %s %s %s\n", adr.getId(), adr.getNumero(), adr.getNom_rue(), adr.getVille(), adr.getPays(), adr.getProvince(), adr.getPays() );
			
			// Mettre à jour
			System.out.println("******* Mettre à jour *******");
			Adresse adr2 = read_Adresse(1); // lire l'adresse ayant un id = 1
			
			
			System.out.println("Ville:" + adr2.getVille());
			adr2.setVille("Paris");
			update(adr2);	// sauvegarder les détails de l'adresse dans la basesave the updated employee details
			
			adr2 = read_Adresse(1); // lire de nouveau l'adresse ayant un id = 1
			System.out.println("Ville apres modification:" + adr2.getVille());
			
			//Supprimer
			System.out.println("******* Supprimer *******");
			delete(adr); 
			Adresse adr3 = read_Adresse(adr.getId());
			System.out.println("Objet:" + adr3);
		}

	 /**
		 * retourner la liste des adresses
		 * @return List
		 */
		@SuppressWarnings("unchecked")
		private static List<Adresse> list() {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();

			List<Adresse> adresses = session.createQuery("from Adresse").list();
			 
			session.close();
			return adresses;
		}
	 
	 /**
		 * lire l'adresse à partir de son id
		 * @return Adresse
		 */
	 	private static Adresse read_Adresse(Integer id) 
	 	{
				SessionFactory sf = HibernateUtil.getSessionFactory();
				Session session = sf.openSession();

				Adresse adresse = (Adresse) session.get(Adresse.class, id);
				
				session.close();
				return adresse;
		}
		
		/**
		 * enregistrer un objet de type Adresse
		 * @return Adresse 
		 */
		private static Adresse save(Adresse adresse) {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();

			session.beginTransaction();
			
			Integer id = (Integer) session.save(adresse);
			
			adresse.setId(id);
			 
			session.getTransaction().commit();
			
			session.close();
			return adresse;
		}

		/**
		 * Mettre à jour un objet de type Adresse
		 * @return Adresse
		 */
		private static Adresse update(Adresse adresse) {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();

			session.beginTransaction();

			session.merge(adresse);
			
			session.getTransaction().commit();
			
			session.close();
			return adresse;

		}

		/**
		 * Supprimer un objet de type Adresse
		 * @return void 
		 */
		private static void delete(Adresse adresse) {
			
			//Configuration conf = new Configuration().configure();
			//SessionFactory sf = conf.buildSessionFactory(new ServiceRegistryBuilder()
			//							.applySettings(conf.getProperties()).buildServiceRegistry());
			//Session session = sf.openSession();
			
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			
			session.beginTransaction();
			
			session.delete(adresse); 
			
			session.getTransaction().commit();
		
			
			session.close();
		}
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	 
		 /**
		 * lire l'employe_affectation_projet à partir de son id
		 * @return Employe_Affectation_Projet
		 */
		private static Employe_Affectation_Projet read_EAP(Employe_Affectation_ProjetPK id) 
	 	{
				SessionFactory sf = HibernateUtil.getSessionFactory();
				Session session = sf.openSession();

				Employe_Affectation_Projet eap = (Employe_Affectation_Projet) session.get(Employe_Affectation_Projet.class, id);
				
				session.close();
				return eap;
		}
		
		/**
		 * enregistrer un objet de type Employe_Affectation_Projet
		 * @return Employe_Affectation_Projet 
		 */
		private static Employe_Affectation_Projet save(Employe_Affectation_Projet eap) {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();

			session.beginTransaction();
			
			Employe_Affectation_ProjetPK id = (Employe_Affectation_ProjetPK) session.save(eap);
			
			eap.setId(id);
			 
			session.getTransaction().commit();
			
			session.close();
			return eap;
		}

		/**
		 * Mettre à jour un objet de type Employe_Affectation_Projet
		 * @return Employe_Affectation_Projet
		 */
		private static Employe_Affectation_Projet update(Employe_Affectation_Projet eap) {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();

			session.beginTransaction();

			session.merge(eap);
			
			session.getTransaction().commit();
			
			session.close();
			return eap;

		}

		/**
		 * Supprimer un objet de type Employe_Affectation_Projet
		 * @return void 
		 */
		private static void delete(Employe_Affectation_Projet eap) {
			
			//Configuration conf = new Configuration().configure();
			//SessionFactory sf = conf.buildSessionFactory(new ServiceRegistryBuilder()
			//							.applySettings(conf.getProperties()).buildServiceRegistry());
			//Session session = sf.openSession();
			
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			
			session.beginTransaction();
			
			session.delete(eap); 
			
			session.getTransaction().commit();
		
			
			session.close();
		}
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		 /**
		 * lire l'employe à partir de son id
		 * @return Employe
		 */
		private static Employe read_Employe(Integer id) 
	 	{
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();

			Employe emp = (Employe) session.get(Employe.class, id);
			
			session.close();
			return emp;
	}
	
	/**
	 * enregistrer un objet de type Employe
	 * @return Employe 
	 */
	private static Employe save(Employe emp) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		session.beginTransaction();
		
		Integer id = (Integer) session.save(emp);
		
		emp.setId(id);
		 
		session.getTransaction().commit();
		
		session.close();
		return emp;
	}

	/**
	 * Mettre à jour un objet de type Employe
	 * @return Employe
	 */
	private static Employe update(Employe emp) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		session.beginTransaction();

		session.merge(emp);
		
		session.getTransaction().commit();
		
		session.close();
		return emp;

	}

	/**
	 * Supprimer un objet de type Employe
	 * @return void 
	 */
	private static void delete(Employe emp) {
		
		//Configuration conf = new Configuration().configure();
		//SessionFactory sf = conf.buildSessionFactory(new ServiceRegistryBuilder()
		//							.applySettings(conf.getProperties()).buildServiceRegistry());
		//Session session = sf.openSession();
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		
		session.beginTransaction();
		
		session.delete(emp); 
		
		session.getTransaction().commit();
	
		
		session.close();
	}
	
	
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * lire l'entreprise à partir de son id
	 * @return Entreprise
	 */
		private static Entreprise read_Entreprise(Integer id) 
	 	{
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
	
			Entreprise ent = (Entreprise) session.get(Entreprise.class, id);
			
			session.close();
			return ent;
		}
		
		/**
		 * enregistrer un objet de type Entreprise
		 * @return Entreprise 
		 */
		private static Entreprise save(Entreprise ent) {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
		
			session.beginTransaction();
			
			Integer id = (Integer) session.save(ent);
			
			ent.setId(id);
			 
			session.getTransaction().commit();
			
			session.close();
			return ent;
		}
		
		/**
		 * Mettre à jour un objet de type Entreprise
		 * @return Entreprise
		 */
		private static Entreprise update(Entreprise ent) {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
		
			session.beginTransaction();
		
			session.merge(ent);
			
			session.getTransaction().commit();
			
			session.close();
			return ent;
		
		}
		
		/**
		 * Supprimer un objet de type Entreprise
		 * @return void 
		 */
		private static void delete(Entreprise ent) {
			
			//Configuration conf = new Configuration().configure();
			//SessionFactory sf = conf.buildSessionFactory(new ServiceRegistryBuilder()
			//							.applySettings(conf.getProperties()).buildServiceRegistry());
			//Session session = sf.openSession();
			
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			
			session.beginTransaction();
			
			session.delete(ent); 
			
			session.getTransaction().commit();
		
			
			session.close();
		}
		
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
		/**
		 * lire la personne_contact à partir de son id
		 * @return Personne_Contact
		 */
		private static Personne_Contact read_Personne_Contact(Integer id) 
	 	{
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
	
			Personne_Contact pers = (Personne_Contact) session.get(Personne_Contact.class, id);
			
			session.close();
			return pers;
		}
		
		/**
		 * enregistrer un objet de type Personne_Contact
		 * @return Personne_Contact 
		 */
		private static Personne_Contact save(Personne_Contact pers) {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
		
			session.beginTransaction();
			
			Integer id = (Integer) session.save(pers);
			
			pers.setId(id);
			 
			session.getTransaction().commit();
			
			session.close();
			return pers;
		}
		
		/**
		 * Mettre à jour un objet de type Personne_Contact
		 * @return Personne_Contact
		 */
		private static Personne_Contact update(Personne_Contact pers) {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
		
			session.beginTransaction();
		
			session.merge(pers);
			
			session.getTransaction().commit();
			
			session.close();
			return pers;
		
		}
		
		/**
		 * Supprimer un objet de type Personne_Contact
		 * @return void 
		 */
		private static void delete(Personne_Contact pers) {
			
			//Configuration conf = new Configuration().configure();
			//SessionFactory sf = conf.buildSessionFactory(new ServiceRegistryBuilder()
			//							.applySettings(conf.getProperties()).buildServiceRegistry());
			//Session session = sf.openSession();
			
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			
			session.beginTransaction();
			
			session.delete(pers); 
			
			session.getTransaction().commit();
		
			
			session.close();
		}

		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
		/**
		 * lire le projet à partir de son numéro
		 * @return Projet
		 */
		private static Projet read_Projet(Integer id) 
	 	{
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
	
			Projet proj = (Projet) session.get(Projet.class, id);
			
			session.close();
			return proj;
		}
		
		/**
		 * enregistrer un objet de type Projet
		 * @return Projet 
		 */
		private static Projet save(Projet proj) {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
		
			session.beginTransaction();
			
			Integer id = (Integer) session.save(proj);
			
			proj.setNumero(id);
			 
			session.getTransaction().commit();
			
			session.close();
			return proj;
		}
		
		/**
		 * Mettre à jour un objet de type Projet
		 * @return Projet
		 */
		private static Projet update(Projet proj) {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
		
			session.beginTransaction();
		
			session.merge(proj);
			
			session.getTransaction().commit();
			
			session.close();
			return proj;
		
		}
		
		/**
		 * Supprimer un objet de type Projet
		 * @return void 
		 */
		private static void delete(Projet proj) {
			
			//Configuration conf = new Configuration().configure();
			//SessionFactory sf = conf.buildSessionFactory(new ServiceRegistryBuilder()
			//							.applySettings(conf.getProperties()).buildServiceRegistry());
			//Session session = sf.openSession();
			
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			
			session.beginTransaction();
			
			session.delete(proj); 
			
			session.getTransaction().commit();
		
			
			session.close();
		}


		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
		/**
		 * lire le service à partir de son id
		 * @return Service
		 */
		private static Service read_Service(Integer id) 
	 	{
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
	
			Service serv = (Service) session.get(Service.class, id);
			
			session.close();
			return serv;
		}
		
		/**
		 * enregistrer un objet de type Service
		 * @return Service 
		 */
		private static Service save(Service serv) {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
		
			session.beginTransaction();
			
			Integer id = (Integer) session.save(serv);
			
			serv.setId(id);
			 
			session.getTransaction().commit();
			
			session.close();
			return serv;
		}
		
		/**
		 * Mettre à jour un objet de type Service
		 * @return Service
		 */
		private static Service update(Service serv) {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
		
			session.beginTransaction();
		
			session.merge(serv);
			
			session.getTransaction().commit();
			
			session.close();
			return serv;
		
		}
		
		/**
		 * Supprimer un objet de type Service
		 * @return void 
		 */
		private static void delete(Service serv) {
			
			//Configuration conf = new Configuration().configure();
			//SessionFactory sf = conf.buildSessionFactory(new ServiceRegistryBuilder()
			//							.applySettings(conf.getProperties()).buildServiceRegistry());
			//Session session = sf.openSession();
			
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			
			session.beginTransaction();
			
			session.delete(serv); 
			
			session.getTransaction().commit();
		
			
			session.close();
		}


		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
		/**
		 * lire le telephone à partir de son id
		 * @return Telephone
		 */
		private static Telephone read_Telephone(Integer id) 
	 	{
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
	
			Telephone tel = (Telephone) session.get(Telephone.class, id);
			
			session.close();
			return tel;
		}
		
		/**
		 * enregistrer un objet de type Telephone
		 * @return Telephone 
		 */
		private static Telephone save(Telephone tel) {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
		
			session.beginTransaction();
			
			Integer id = (Integer) session.save(tel);
			
			tel.setId(id);
			 
			session.getTransaction().commit();
			
			session.close();
			return tel;
		}
		
		/**
		 * Mettre à jour un objet de type Telephone
		 * @return Telephone
		 */
		private static Telephone update(Telephone tel) {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
		
			session.beginTransaction();
		
			session.merge(tel);
			
			session.getTransaction().commit();
			
			session.close();
			return tel;
		
		}
		
		/**
		 * Supprimer un objet de type Telephone
		 * @return void 
		 */
		private static void delete(Telephone tel) {
			
			//Configuration conf = new Configuration().configure();
			//SessionFactory sf = conf.buildSessionFactory(new ServiceRegistryBuilder()
			//							.applySettings(conf.getProperties()).buildServiceRegistry());
			//Session session = sf.openSession();
			
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			
			session.beginTransaction();
			
			session.delete(tel); 
			
			session.getTransaction().commit();
		
			
			session.close();
		}
		
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	 
		 /**
		 * lire l'enterprise_faire_affaire à partir de son id
		 * @return Entreprise_Faire_Affaire
		 */
		private static Entreprise_Faire_Affaire read_EFA(Entreprise_Faire_Affaire id) 
	 	{
				SessionFactory sf = HibernateUtil.getSessionFactory();
				Session session = sf.openSession();

				Entreprise_Faire_Affaire efa = (Entreprise_Faire_Affaire) session.get(Entreprise_Faire_Affaire.class, id);
				
				session.close();
				return efa;
		}
		
		/**
		 * enregistrer un objet de type Entreprise_Faire_Affaire
		 * @return Entreprise_Faire_Affaire 
		 */
		private static Entreprise_Faire_Affaire save(Entreprise_Faire_Affaire efa) {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();

			session.beginTransaction();
			
			Entreprise_Faire_AffairePK id = (Entreprise_Faire_AffairePK) session.save(efa);
			
			efa.setId(id);
			 
			session.getTransaction().commit();
			
			session.close();
			return efa;
		}

		/**
		 * Mettre à jour un objet de type Entreprise_Faire_Affaire
		 * @return Entreprise_Faire_Affaire
		 */
		private static Entreprise_Faire_Affaire update(Entreprise_Faire_Affaire efa) {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();

			session.beginTransaction();

			session.merge(efa);
			
			session.getTransaction().commit();
			
			session.close();
			return efa;

		}

		/**
		 * Supprimer un objet de type Employe_Affectation_Projet
		 * @return void 
		 */
		private static void delete(Entreprise_Faire_Affaire efa) {
			
			//Configuration conf = new Configuration().configure();
			//SessionFactory sf = conf.buildSessionFactory(new ServiceRegistryBuilder()
			//							.applySettings(conf.getProperties()).buildServiceRegistry());
			//Session session = sf.openSession();
			
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			
			session.beginTransaction();
			
			session.delete(efa); 
			
			session.getTransaction().commit();
		
			
			session.close();
		}
}
