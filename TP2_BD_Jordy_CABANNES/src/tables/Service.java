/*
 * auteur : Jordy CABANNES
 */

package tables;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.NotBlank;
//import org.hibernate.validator.constraints.NotEmpty;//Cet import est commenté car tous les @NotEmpty sont commentés.
//Ils sont commentés afin de ne pas générer d'erreurs lors de l'exécution si l'un des champs avec l'annotation @NotEmpty est vide.




/*La table service est une table fondamentale de la base de données, elle n'est pas issue d'une association, nous devons donc
créer l'entité Service*/
@Entity
@Table(name="Service")
public class Service 
{
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;
	
	@NotBlank
	@Size(min=0, max=255)
	@Column(name="nom")
	private String nom;
	
	/*
	 * Correspond à l'association "Un-à-Un" entre les tables employe et service, d'après le schéma un employe(directeur) est associé à un
	 * et un seul service. Ce champ permet de définir la clé étrangère pour faire le lien avec Employe.
	 */
	@OneToOne
	@JoinColumn(name="directeur_id")
	private Employe employe1;
	
	/*
	 * Correspond à l'association "Un-à-Un" entre les tables service et employe, d'après le schéma un service est associé 
	 * à un et un seul employe. Ce champ permet de faire le lien avec la clé étrangère service_id présente 
	 * dans la table employe 
	 */
	@OneToOne(mappedBy="service2")
	private Employe employe2; 
	
	@Past
	@Column(name="date_debut_direction")
	private Date date_debut_direction;
	
	/*
	 * Correspond à l'association "Plusieurs-à-Plusieurs" entre les tables service et adresse, d'après le schéma un service est associé à une ou plusieurs
	 * instances de adresse, d'où la collection. De plus cette association ne contient pas d'attribut, nous pouvons donc créer la table localisation_service
	 * Dans l'entité Service. Nous aurions pu la créer dans l'entité Adresse, le résultat au niveau de la base de données aurait été le même 
	 */
	//@NotEmpty
	@ManyToMany
	@JoinTable(name = "localisation_service", 
				joinColumns        = { @JoinColumn(name = "service_id", 
												   referencedColumnName="id")  
				}, 
				inverseJoinColumns = { @JoinColumn(name = "adresse_id", 
				                                   referencedColumnName="id") 
				})
	private Collection<Adresse> adresses = new ArrayList<Adresse>();
	
	/*
	 * Correspond à l'association "Un-à-Plusieurs" entre les tables service et entreprise, d'après le schéma 
	 * une instance de service n'a qu'une seule entreprise et une instance de la table entreprise peut être 
	 * présente plusieurs fois dans l'ensemble des instances de service. Du coup l'entité Service doit contenir 
	 * le champ suivant afin d'associer à une instance de Service un objet de type Entreprise.
	 */
	@ManyToOne
	@JoinColumn(name="entreprise_id")
	private Entreprise entreprise;
	
	/*
	 * Correspond à l'association "Un-à-Plusieurs" entre les tables service et projet, d'après le schéma 
	 * une instance de projet n'a qu'un seul service et une instance de la table service peut être 
	 * présente plusieurs fois dans l'ensemble des instances de projet. Du coup l'entité Service doit contenir 
	 * le champ suivant afin de référencer pour chaque Service les objets de type Projet ayant ce Service.
	 */
	//@NotEmpty
	@OneToMany(mappedBy="service", cascade=CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Collection<Projet> listeProjets = new ArrayList<Projet>();
	
	public Service(){}

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

	public Employe getEmploye1() {
		return employe1;
	}

	public void setEmploye1(Employe employe1) {
		this.employe1 = employe1;
	}

	public Employe getEmploye2() {
		return employe2;
	}

	public void setEmploye2(Employe employe2) {
		this.employe2 = employe2;
	}

	public Date getDate_debut_direction() {
		return date_debut_direction;
	}

	public void setDate_debut_direction(Date date_debut_direction) {
		this.date_debut_direction = date_debut_direction;
	}

	public Collection<Adresse> getAdresses() {
		return adresses;
	}

	public void setAdresses(Collection<Adresse> adresses) {
		this.adresses = adresses;
	}

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	public Collection<Projet> getListePersonnes() {
		return listeProjets;
	}

	public void setListePersonnes(Collection<Projet> listeProjets) {
		this.listeProjets = listeProjets;
	}

	
}
