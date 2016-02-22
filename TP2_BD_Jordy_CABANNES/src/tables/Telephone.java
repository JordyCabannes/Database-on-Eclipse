/*
 * auteur : Jordy CABANNES
 */

package tables;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.Digits;

//import org.hibernate.validator.constraints.NotEmpty;//Cet import est commenté car tous les @NotEmpty sont commentés.
//Ils sont commentés afin de ne pas générer d'erreurs lors de l'exécution si l'un des champs avec l'annotation @NotEmpty est vide.




/*La table telephone est une table fondamentale de la base de données, elle n'est pas issue d'une association, nous devons donc
créer l'entité Telephone*/
@Entity
@Table(name="Telephone")
public class Telephone 
{
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;
	
	@Digits(integer=10,fraction=0)
	@Column(name="numero")
	private Integer numero;
	
	/*
	 * Correspond à l'association "Plusieurs-à-Plusieurs" entre les tables telephone et personne_contact, d'après le schéma un telephone est associé à une ou plusieurs
	 * instances de personne_contact, d'où la collection. De plus cette association ne contient pas d'attribut, nous pouvons donc créer la table telephone_personne_contact
	 * Dans l'entité Telephone. Nous aurions pu la créer dans l'entité Personne_Contact, le résultat au niveau de la base de données aurait été le même 
	 */
	//@NotEmpty
	@ManyToMany
	@JoinTable(name = "telephone_personne_contact", 
				joinColumns        = { @JoinColumn(name = "telephone_id", 
												   referencedColumnName="id")  
				}, 
				inverseJoinColumns = { @JoinColumn(name = "personne_conatct_id", 
				                                   referencedColumnName="id") 
				})
	private Collection<Personne_Contact> personne_contacts = new ArrayList<Personne_Contact>();
	
	/*
	 * Correspond à l'association "Plusieurs-à-Plusieurs" entre les tables telephone et employe, d'après le schéma un telephone est associé à une ou plusieurs
	 * instances de employe, d'où la collection. Grâce à ce champ nous associons Telephone à Employe
	 */
	//@NotEmpty
	@ManyToMany(mappedBy="telephones", cascade=CascadeType.ALL)
	private Collection<Employe> employes = new ArrayList<Employe>();
	
	public Telephone(){}

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

	public Collection<Personne_Contact> getPersonne_contacts() {
		return personne_contacts;
	}

	public void setPersonne_contacts(Collection<Personne_Contact> personne_contacts) {
		this.personne_contacts = personne_contacts;
	}

	public Collection<Employe> getEmployes() {
		return employes;
	}

	public void setEmployes(Collection<Employe> employes) {
		this.employes = employes;
	}

}
