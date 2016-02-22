/*
 * auteur : Jordy CABANNES
 */

package tables;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
//import org.hibernate.validator.constraints.NotEmpty;//Cet import est commenté car tous les @NotEmpty sont commentés.
//Ils sont commentés afin de ne pas générer d'erreurs lors de l'exécution si l'un des champs avec l'annotation @NotEmpty est vide.


/*La table personne_contact est une table fondamentale de la base de données, elle n'est pas issue d'une association, nous devons donc
créer l'entité Personne_Contact*/
@Entity
@Table(name="Personne_Contact")
public class Personne_Contact {
	
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
	
	/*
	 * Correspond à l'association "Un-à-Plusieurs" entre les tables adresse et personne_contact, d'après le schéma 
	 * une instance de personne_contact n'a qu'une seule adresse et une instance de la table adresse peut être 
	 * présente plusieurs fois dans l'ensemble des instances de personne_contact. Du coup l'entité Personne_Contact doit contenir 
	 * le champ suivant afin d'associer à une instance de Personne_Contact à un objet de type Adresse.
	 */
	@ManyToOne
	@JoinColumn(name="adresse_id")
	private Adresse adresse;
	
	/*
	 * Correspond à l'association "Plusieurs-à-Plusieurs" entre les tables telephone et personne_contact, d'après le schéma une personne_contact est associée à une ou plusieurs
	 * instances de telephone, d'où la collection. Grâce à ce champ nous associons Telephone à Personne_Contact
	 */
	//@NotEmpty
	@ManyToMany(mappedBy="personne_contacts", cascade=CascadeType.ALL)
	private Collection<Telephone> telephones = new ArrayList<Telephone>();
	
	public Personne_Contact(){}

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

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Collection<Telephone> getTelephones() {
		return telephones;
	}

	public void setTelephones(Collection<Telephone> telephones) {
		this.telephones = telephones;
	}

}
