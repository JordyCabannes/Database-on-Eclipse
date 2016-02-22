/*
 * auteur : Jordy CABANNES
 */

package tables;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/*
 * On crée cette classe qui n'est pas une entité et qui rassemble les attributs composants
 * la clé primaire de Employe_Affectation_Projet, on pourra ainsi seulement utiliser un champ
 * dans Employe_Affectation_Projet pour définir les clés primaires de cette entité
 */
@Embeddable
public class Employe_Affectation_ProjetPK implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Column(name="employe_id")
	private Integer employe_id;
	
	@Column(name="projet_numero")
	private Integer projet_numero;
	
	public Employe_Affectation_ProjetPK(){}
	
	public int hashCode() {
	    return (int)(employe_id + projet_numero);
	  }

	  public boolean equals(Object object) {
	    if (object instanceof Employe_Affectation_ProjetPK) {
	    	Employe_Affectation_ProjetPK otherId = (Employe_Affectation_ProjetPK) object;
	      return (otherId.employe_id == this.employe_id) && (otherId.projet_numero == this.projet_numero);
	    }
	    return false;
	  }

	public Integer getEmploye_id() {
		return employe_id;
	}

	public void setEmploye_id(Integer employe_id) {
		this.employe_id = employe_id;
	}

	public Integer getProjet_numero() {
		return projet_numero;
	}

	public void setProjet_numero(Integer projet_numero) {
		this.projet_numero = projet_numero;
	}
}
