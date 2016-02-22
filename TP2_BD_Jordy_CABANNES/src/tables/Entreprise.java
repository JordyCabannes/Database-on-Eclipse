/*
 * auteur : Jordy CABANNES
 */

package tables;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.NotBlank;
//import org.hibernate.validator.constraints.NotEmpty;//Cet import est commenté car tous les @NotEmpty sont commentés.
//Ils sont commentés afin de ne pas générer d'erreurs lors de l'exécution si l'un des champs avec l'annotation @NotEmpty est vide.


/*La table entreprise est une table fondamentale de la base de données, elle n'est pas issue d'une association, nous devons donc
créer l'entité Entreprise*/
@Entity
@Table(name="Entreprise")
public class Entreprise implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;
	
	@NotBlank
	@Size(min=0, max=255)
	@Column(name="nom")
	private String nom;
	
	/*
	 * Correspond à l'association "Un-à-Plusieurs" entre les tables entreprise et entreprise_faire_affaire, d'après le schéma 
	 * une instance de entreprise_faire_affaire n'a qu'une seule instance de entreprise(partenaure) et une instance de la table entreprise(partenaire) peut être 
	 * présente plusieurs fois dans l'ensemble des instances de entreprise_faire_affaire. Du coup l'entité Entreprise doit contenir 
	 * le champ suivant afin de référencer pour chaque Entreprise(partenaire) les objets de type Entreprise_Faire_Affaire faisant référence à cet Entreprise(partenaire)
	 */
	//@NotEmpty
	@OneToMany(mappedBy="entreprise_partenaire", cascade=CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Collection<Entreprise_Faire_Affaire> entreprise_partenaire_faire_affaire = new ArrayList<Entreprise_Faire_Affaire>() ;	
	
	/*
	 * Correspond à la seconde association "Un-à-Plusieurs" entre les tables entreprise et entreprise_faire_affaire, d'après le schéma 
	 * une instance de entreprise_faire_affaire n'a qu'une seule instance de entreprise et une instance de la table entreprise peut être 
	 * présente plusieurs fois dans l'ensemble des instances de entreprise_faire_affaire. Du coup l'entité Entreprise doit contenir 
	 * le champ suivant afin de référencer pour chaque Entreprise les objets de type Entreprise_Faire_Affaire faisant référence à cet Entreprise
	 */
	//@NotEmpty
	@OneToMany(mappedBy="entreprise", cascade=CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Collection<Entreprise_Faire_Affaire> entreprise_faire_affaire=new ArrayList<Entreprise_Faire_Affaire>();
	
	/*
	 * Correspond à l'association "Un-à-Un" entre les tables entreprise et employe(dg), d'après le schéma une entreprise est associée à un
	 * et un seul employe(dg). Ce champ permet de définir la clé étrangère pour faire le lien avec Employe.
	 */
	@OneToOne
	@JoinColumn(name="dg_id")
	private Employe employe;
	
	/*
	 * Correspond à l'association "Un-à-Plusieurs" entre les tables entreprise et service, d'après le schéma 
	 * une instance de service n'a qu'une seule entreprise et une instance de la table entreprise peut être 
	 * présente plusieurs fois dans l'ensemble des instances de service. Du coup l'entité Entreprise doit contenir 
	 * le champ suivant afin de référencer pour chaque Entreprise les objets de type Service présent dans cette Entreprise.
	 */
	//@NotEmpty
	@OneToMany(mappedBy="entreprise", cascade=CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Collection<Service> services = new ArrayList<Service>();
	
	
	
	public Entreprise(){}

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Employe getEmploye() {
		return employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	public Collection<Service> getServices() {
		return services;
	}

	public void setServices(Collection<Service> services) {
		this.services = services;
	}

	public Collection<Entreprise_Faire_Affaire> getEntreprise_faire_affaire() {
		return entreprise_faire_affaire;
	}

	public void setEntreprise_faire_affaire(Collection<Entreprise_Faire_Affaire> entreprise_faire_affaire) {
		this.entreprise_faire_affaire = entreprise_faire_affaire;
	}

	public Collection<Entreprise_Faire_Affaire> getEntreprise_partenaire_faire_affaire() {
		return entreprise_partenaire_faire_affaire;
	}

	public void setEntreprise_partenaire_faire_affaire(Collection<Entreprise_Faire_Affaire> entreprise_partenaire_faire_affaire) {
		this.entreprise_partenaire_faire_affaire = entreprise_partenaire_faire_affaire;
	}
}
