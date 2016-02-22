/*
 * auteur : Jordy CABANNES
 */

package tables;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.NotBlank;
//import org.hibernate.validator.constraints.NotEmpty;//Cet import est commenté car tous les @NotEmpty sont commentés.
//Ils sont commentés afin de ne pas générer d'erreurs lors de l'exécution si l'un des champs avec l'annotation @NotEmpty est vide.



/*La table projet est une table fondamentale de la base de données, elle n'est pas issue d'une association, nous devons donc
créer l'entité Projet*/
@Entity
@Table(name="Projet")
public class Projet implements Serializable
{	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="numero")
	private Integer numero;
	
	@NotBlank
	@Size(min=0, max=255)
	@Column(name="nom")
	private String nom;
	
	@Past
	@Column(name="date_debut")
	private Date date_debut;
	
	/*
	 * Correspond à l'association "Un-à-Plusieurs" entre les tables projet et service, d'après le schéma 
	 * une instance de projet n'a qu'un seul service et une instance de la table service peut être 
	 * présente plusieurs fois dans l'ensemble des instances de projet. Du coup l'entité Projet doit contenir 
	 * le champ suivant afin d'associer à une instance de Projet un objet de type Service.
	 */
	@ManyToOne
	@JoinColumn(name="service_id")
	private Service service;
	
	/*
	 * Correspond à l'association "Un-à-Plusieurs" entre les tables projet et employe_affectation_projet, d'après le schéma 
	 * une instance de employe_projet_affectation n'a qu'une seule instance de projet et une instance de la table projet peut être 
	 * présente plusieurs fois dans l'ensemble des instances de employe_projet_affectation. Du coup l'entité Projet doit contenir 
	 * le champ suivant afin de référencer pour chaque Projet les objets de type Employe_Affectation_Projet faisant référence à ce Projet.
	 */
	//@NotEmpty
	@OneToMany(mappedBy="projet", cascade=CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Collection<Employe_Affectation_Projet> employe_affectation_projet = new ArrayList<Employe_Affectation_Projet>();
	
	public Projet(){}
	
	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Date getDate_debut() {
		return date_debut;
	}

	public void setDate_debut(Date date_debut) {
		this.date_debut = date_debut;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Collection<Employe_Affectation_Projet> getEmploye_affectation_projet() {
		return employe_affectation_projet;
	}

	public void setEmploye_affectation_projet(Collection<Employe_Affectation_Projet> employe_affectation_projet) {
		this.employe_affectation_projet = employe_affectation_projet;
	}
	
	
}
