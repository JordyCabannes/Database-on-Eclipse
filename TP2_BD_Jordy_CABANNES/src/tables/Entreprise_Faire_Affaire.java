/*
 * auteur : Jordy CABANNES
 */

package tables;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/*
 * Dans le schéma de la base de données la table enterprise_faire_affaire est issue d'une association
 * Plusieurs-à_Plusieurs. Or cette table contient des attributs, nous ne pouvons donc pas la créer à partir
 * de l'entité d'une des deux tables impliquées dans l'asociation. Nous devons donc créer l'entité 
 * Enterprise_Faire_Affaire
 */
@Entity
@Table(name="Entreprise_Faire_Affaire")
public class Entreprise_Faire_Affaire implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Min(value=1)
	@EmbeddedId
	private Entreprise_Faire_AffairePK id;
	
	 
	@NotBlank
	@Length(min=0, max=255)
	@Column(name="type")
	private String type;
	
	
	/*
	 * Nous avons créé une entité pour la table entreprise_faire_affaire nous devons donc décomposé
	 * l'association Plusieurs-à-Plusieurs de base en deux associations Un-à-Plusieurs qui vont reliés 
	 * les entités Entreprise et Entreprise à l'entité Entreprise_Faire_Affaire
	 */
	
	/*
	 * Ce champ permet de relier l'entité ENtreprise à l'entité Entreprise_Faire_Affaire, une instance de Entreprise
	 * sera présente une fois dans Entreprise mais une ou plusieurs fois dans l'ensemble des instances de 
	 * Entreprise_Faire_Affaire. A chaque Entreprise dans Entreprise_Faire_Affaire lui est associé une Entreprise(Partenaire) 
	 * qui pourra elle aussi se retouver plusieurs fois dans les intances de Entreprise_Faire_Affaire
	 */
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entreprise_id", insertable = false, updatable = false)
    private Entreprise entreprise;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entreprise_partenaire_id", insertable = false, updatable = false)
    private Entreprise entreprise_partenaire;
	
	public Entreprise_Faire_Affaire(){}

	public Entreprise_Faire_AffairePK getId() {
		return id;
	}

	public void setId(Entreprise_Faire_AffairePK id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Entreprise getEntreprise1() {
		return entreprise;
	}

	public void setEntreprise1(Entreprise entreprise1) {
		this.entreprise = entreprise1;
	}

	public Entreprise getEntreprise2() {
		return entreprise_partenaire;
	}

	public void setEntreprise2(Entreprise entreprise2) {
		this.entreprise_partenaire = entreprise2;
	}

}
