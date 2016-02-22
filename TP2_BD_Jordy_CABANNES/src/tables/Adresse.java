/*
 * auteur : Jordy CABANNES
 */

package tables;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.Min;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;
//import org.hibernate.validator.constraints.NotEmpty; //Cet import est commenté car tous les @NotEmpty sont commentés.
//Ils sont commentés afin de ne pas générer d'erreurs lors de l'exécution si l'un des champs avec l'annotation @NotEmpty est vide.

import validator.CodePostalValide;

/*La table adresse est une table fondamentale de la base de données, elle n'est pas issue d'une association, nous devons donc
créer l'entité Adresse*/
@Entity
@Table(name="Adresse")
@NamedQuery(name=Adresse.GET_ADRESSE_BY_ID, query=Adresse.GET_ADRESSE_BY_ID_QUERY)
public class Adresse 
{
	static final String GET_ADRESSE_BY_ID_QUERY = "from Adresse adr where adr.id=:id";
    public static final String GET_ADRESSE_BY_ID = "GET_ADRESSE_BY_ID";

	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;
	
	@Min(value=1)
	@Column(name="numero")
	private Integer numero;
	
	@Length(min=0, max=255)
	@Column(name="nom_rue")
	private String nom_rue;
	

	@CodePostalValide
	@Column(name="code_postal")
	private String code_postal;

	@Length(min=0, max=255)
	@Column(name="ville")
	private String ville;
	
	
	@Length(min=0, max=255)
	@Column(name="province")
	private String province;
	
	@Length(min=0, max=255)
	@Column(name="pays")
	private String pays;

	/*
	 * Correspond à l'association "Un-à-Plusieurs" entre les tables adresse et personne_contact, d'après le schéma 
	 * une instance de personne_contact n'a qu'une seule adresse et une instance de la table adresse peut être 
	 * présente plusieurs fois dans l'ensemble des instances de personne_contact. Du coup l'entité Adresse doit contenir 
	 * le champ suivant afin de référencer pour chaque Adresse les objets de type Personne_Contact ayant cette Adresse.
	 */
	//@NotEmpty
	@OneToMany(mappedBy="adresse", cascade=CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Collection<Personne_Contact> listePersonnes = new ArrayList<Personne_Contact>();

	/*
	 * Correspond à l'association "Un-à-Plusieurs" entre les tables adresse et employe, d'après le schéma 
	 * une instance de employe n'a qu'une seule adresse et une instance de la table adresse peut être 
	 * présente plusieurs fois dans l'ensemble des instances de employe. Du coup l'entité Adresse doit contenir 
	 * le champ suivant afin de référencer pour chaque Adresse les objets de type Employe ayant cette Adresse.
	 */
	//@NotEmpty
	@OneToMany(mappedBy="adresse", cascade=CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Collection<Employe> listeEmployes = new ArrayList<Employe>();
	
	/*
	 * Correspond à l'association "Plusieurs-à-Plusieurs" entre les tables adresse et service, A une adresse peut être associé
	 * plusieurs services. Du coup l'entité Adresse doit contenir le champ suivant afin de référencer pour chaque Adresse les objets de 
	 * type Service ayant cette Adresse.
	 */
	//@NotEmpty
	@ManyToMany(mappedBy="adresses", cascade=CascadeType.ALL)
	private Collection<Service> services = new ArrayList<Service>();
	
	public Adresse(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getNom_rue() {
		return nom_rue;
	}

	public void setNom_rue(String nom_rue) {
		this.nom_rue = nom_rue;
	}

	public String getCode_postal() {
		return code_postal;
	}

	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public Collection<Personne_Contact> getListePersonnes() {
		return listePersonnes;
	}

	public void setListePersonnes(Collection<Personne_Contact> listePersonnes) {
		this.listePersonnes = listePersonnes;
	}

	public Collection<Employe> getListeEmployes() {
		return listeEmployes;
	}

	public void setListeEmployes(Collection<Employe> listeEmployes) {
		this.listeEmployes = listeEmployes;
	}

	public Collection<Service> getServices() {
		return services;
	}

	public void setServices(Collection<Service> services) {
		this.services = services;
	}
	
	
	
}
