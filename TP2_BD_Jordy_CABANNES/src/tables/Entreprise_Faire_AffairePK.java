/*
 * auteur : Jordy CABANNES
 */

package tables;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/*
 * On crée cette classe qui n'est pas une entité et qui rassemble les attributs composants
 * la clé primaire de Entreprise_Faire_Affaire, on pourra ainsi seulement utiliser un champ
 * dans Entreprise_Faire_Affaire pour définir les clés primaires de cette entité
 */
@Embeddable
public class Entreprise_Faire_AffairePK implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Column(name="entreprise_id")
	private Integer entreprise_id;
	
	@Column(name="entreprise_partenaire_id")
	private Integer entreprise_partenaire_id;
	
	
	public Entreprise_Faire_AffairePK(){}
	
	public int hashCode() {
	    return (int)(entreprise_id + entreprise_partenaire_id);
	  }

	  public boolean equals(Object object) {
	    if (object instanceof Entreprise_Faire_AffairePK) {
	    	Entreprise_Faire_AffairePK otherId = (Entreprise_Faire_AffairePK) object;
	      return (otherId.entreprise_id == this.entreprise_id) && (otherId.entreprise_partenaire_id == this.entreprise_partenaire_id);
	    }
	    return false;
	  }

	public Integer getEntreprise_id() {
		return entreprise_id;
	}

	public void setEntreprise_id(Integer entreprise_id) {
		this.entreprise_id = entreprise_id;
	}

	public Integer getEntreprise_partenaire_id() {
		return entreprise_partenaire_id;
	}

	public void setEntreprise_partenaire_id(Integer entreprise_partenaire_id) {
		this.entreprise_partenaire_id = entreprise_partenaire_id;
	}		
}
