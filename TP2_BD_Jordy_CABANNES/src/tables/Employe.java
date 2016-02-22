/*
 * auteur : Jordy CABANNES
 */

package tables;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.NotBlank;
//import org.hibernate.validator.constraints.NotEmpty;//Cet import est commenté car tous les @NotEmpty sont commentés.
//Ils sont commentés afin de ne pas générer d'erreurs lors de l'exécution si l'un des champs avec l'annotation @NotEmpty est vide.


/*La table employe est une table fondamentale de la base de données, elle n'est pas issue d'une association, nous devons donc
créer l'entité Employe*/
@Entity
@Table(name="Employe")
public class Employe implements Serializable
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
	
	@NotBlank
	@Size(min=0, max=255)
	@Column(name="prenom")
	private String prenom;
	
	@Min(value=0)
	@Digits(integer=11, fraction=0)
	@Column(name="salaire_net")
	private Integer salaire_net;
	
	@Past
	@Column(name="date_embauche")
	private Date date_embauche;
	
	@Digits(integer=9, fraction=0)
	@Column(name="nas")
	private Integer nas;
	
	@Past
	@Column(name="date_naissance")
	private Date date_naissance;
	
	/*
	 * Correspond à l'association "Un-à-Plusieurs" entre les tables adresse et employe, d'après le schéma 
	 * une instance de employe n'a qu'une seule adresse et une instance de la table adresse peut être 
	 * présente plusieurs fois dans l'ensemble des instances de employe. Du coup l'entité Employe doit contenir 
	 * le champ suivant afin d'associer à une instance de Employe un objet de type Adresse.
	 */
	@ManyToOne
	@JoinColumn(name="adresse_id")
	private Adresse adresse;
	
	/*
	 * Correspond à l'association "Un-à-Un" entre les tables employe et service, d'après le schéma un employe(directeur) est associé à un
	 * et un seul service. Ce champ permet de faire le lien avec la clé étrangère directeur_id présente dans la table service 
	 */
	@OneToOne(mappedBy="employe1")
	private Service service1; 
	
	/*
	 * Correspond à l'association "Un-à-Un" entre les tables employe et service, d'après le schéma un employe est associé à un
	 * et un seul service. Ce champ permet de définir la clé étrangère pour faire le lien avec Service.
	 */
	@OneToOne
	@JoinColumn(name="service_id")
	private Service service2;
	
	/*
	 * Correspond à l'association "Un-à-Un" entre les tables employe et entreprise, d'après le schéma un employe(dg) est associé à une
	 * et une seule entreprise. Ce champ permet de faire le lien avec la clé étrangère dg_id présente dans la table entreprise 
	 */
	@OneToOne(mappedBy="employe")
	private Entreprise entreprise; 
	
	/*
	 * Correspond à l'association "Plusieurs-à-Plusieurs" entre les tables employe et telephone, d'après le schéma un employe est associé à une ou plusieurs
	 * instances de telephone, d'où la collection. De plus cette association ne contient pas d'attribut, nous pouvons donc créer la table telephone_employe
	 * Dans l'entité Employe. Nous aurions pu la créer dans l'entité Telephone, le résultat au niveau de la base de données aurait été le même 
	 */
	//@NotEmpty
	@ManyToMany
	@JoinTable(name = "telephone_employe", 
				joinColumns        = { @JoinColumn(name = "employe_id", 
												   referencedColumnName="id")  
				}, 
				inverseJoinColumns = { @JoinColumn(name = "telephone_id", 
				                                   referencedColumnName="id") 
				})
	private Collection<Telephone> telephones = new ArrayList<Telephone>();
	
	
	/*
	 * Correspond à l'association "Un-à-Plusieurs" entre les tables employe et employe_affectation_projet, d'après le schéma 
	 * une instance de employe_projet_affectation n'a qu'une seule instance de emloye et une instance de la table employe peut être 
	 * présente plusieurs fois dans l'ensemble des instances de employe_projet_affectation. Du coup l'entité Employe doit contenir 
	 * le champ suivant afin de référencer pour chaque Employe les objets de type Employe_Affectation_Projet faisant référence à cet Employe.
	 */
	//@NotEmpty
	@OneToMany(mappedBy="employe")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Collection<Employe_Affectation_Projet> employe_affectation_projet=new ArrayList<Employe_Affectation_Projet>();
	
	/*
	 * Correspond à l'association "Un-à-Plusieurs" entre les tables employe et employe, d'après le schéma 
	 * une instance de employe n'a qu'un seul supérieur et une instance de la table employe(supérieur) peut être 
	 * présente plusieurs fois dans l'ensemble des instances de employe. Du coup l'entité Employe doit contenir 
	 * le champ suivant afin de référencer pour chaque Employe(supérieur) les objets de type Employe ayant ce supérieur.
	 */
	//@NotEmpty
	@OneToMany(mappedBy="employe", cascade=CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Collection<Employe> listeEmployes = new ArrayList<Employe>();
	
	/*
	 * Correspond à l'association "Un-à-Plusieurs" entre les tables employe et employe, d'après le schéma 
	 * une instance de employe n'a qu'un seul employe (supérieur) et une instance de la table employe(supéreur) peut être 
	 * présente plusieurs fois dans l'ensemble des instances de employe. Du coup l'entité Employe doit contenir 
	 * le champ suivant afin d'associer à une instance de Employe à un objet de type Employe(supérieur).
	 */
	@ManyToOne
	@JoinColumn(name="superieur_id")
	private Employe employe;
	

	public Employe(){}

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

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Integer getSalaire_net() {
		return salaire_net;
	}

	public void setSalaire_net(Integer salaire_net) {
		this.salaire_net = salaire_net;
	}

	public Date getDate_embauche() {
		return date_embauche;
	}

	public void setDate_embauche(Date date_embauche) {
		this.date_embauche = date_embauche;
	}

	public Integer getNas() {
		return nas;
	}

	public void setNas(Integer nas) {
		this.nas = nas;
	}

	public Date getDate_naissance() {
		return date_naissance;
	}

	public void setDate_naissance(Date date_naissance) {
		this.date_naissance = date_naissance;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Service getService1() {
		return service1;
	}

	public void setService1(Service service1) {
		this.service1 = service1;
	}

	public Service getService2() {
		return service2;
	}

	public void setService2(Service service2) {
		this.service2 = service2;
	}

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	public Collection<Telephone> getTelephones() {
		return telephones;
	}

	public void setTelephones(Collection<Telephone> telephones) {
		this.telephones = telephones;
	}

	public Collection<Employe> getListeEmployes() {
		return listeEmployes;
	}

	public void setListeEmployes(Collection<Employe> listeEmployes) {
		this.listeEmployes = listeEmployes;
	}

	public Employe getEmploye() {
		return employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	public Collection<Employe_Affectation_Projet> getEmploye_affectation_projet() {
		return employe_affectation_projet;
	}

	public void setEmploye_affectation_projet(Collection<Employe_Affectation_Projet> employe_affectation_projet) {
		this.employe_affectation_projet = employe_affectation_projet;
	}

	
	
	
	
}
