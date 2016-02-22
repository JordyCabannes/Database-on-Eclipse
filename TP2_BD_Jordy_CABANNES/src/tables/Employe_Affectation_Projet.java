/*
 * auteur : Jordy CABANNES
 */

package tables;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;

/*
 * Dans le schéma de la base de données la table employe_affectation_projet est issue d'une association
 * Plusieurs-à_Plusieurs. Or cette table contient des attributs, nous ne pouvons donc pas la créer à partir
 * de l'entité d'une des deux tables impliquées dans l'asociation. Nous devons donc créer l'entité 
 * Employe_Affectation_Projet
 */
@Entity
@Table(name="Employe_Affectation_Projet")
public class Employe_Affectation_Projet implements Serializable
{
	private static final long serialVersionUID = 1L;

		@Min(value=1)
		@EmbeddedId
	    private Employe_Affectation_ProjetPK id;
	
		@Min(value=0)
		@Column(name="heures")
		private Integer heures;
		
		@Past
		@Column(name="debut_semaine")
		private Date debut_semaine;
		
		@Future
		@Column(name="fin_semaine")
		private Date fin_semaine;
		
		/*
		 * Nous avons créé une entité pour la table employe_affectation_projet nous devons donc décomposé
		 * l'association Plusieurs-à-Plusieurs de base en deux associations Un-à-Plusieurs qui vont reliés 
		 * les entités Employe et Projet à l'entité Employe_Affectation_Projet
		 */
		
		/*
		 * Ce champ permet de relier l'entité Projet à l'entité Employe_Affectation_Projet, une instance de Projet
		 * sera présente une fois dans Projet mais une ou plusieurs fois dans l'ensemble des instances de 
		 * Employe_Affectation_Projet
		 */
		@ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "projet_numero", insertable = false, updatable = false)
	    private Projet projet;
		
		/*
		 * Ce champ permet de relier l'entité Employe à l'entité Employe_Affectation_Projet, une instance de EMploye
		 * sera présente une fois dans Employe mais une ou plusieurs fois dans l'ensemble des instances de 
		 * Employe_Affectation_Projet
		 */
		@ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "employe_id", insertable = false, updatable = false)
	    private Employe employe;
		
		public Employe_Affectation_Projet(){}

		public Employe_Affectation_ProjetPK getId() {
			return id;
		}

		public void setId(Employe_Affectation_ProjetPK id) {
			this.id = id;
		}

		public Integer getHeures() {
			return heures;
		}

		public void setHeures(Integer heures) {
			this.heures = heures;
		}

		public Date getDebut_semaine() {
			return debut_semaine;
		}

		public void setDebut_semaine(Date debut_semaine) {
			this.debut_semaine = debut_semaine;
		}

		public Date getFin_semaine() {
			return fin_semaine;
		}

		public void setFin_semaine(Date fin_semaine) {
			this.fin_semaine = fin_semaine;
		}

		public Employe getEmploye() {
			return employe;
		}

		public void setEmploye(Employe employe) {
			this.employe = employe;
		}

		public Projet getProjet() {
			return projet;
		}

		public void setProjet(Projet projet) {
			this.projet = projet;
		}

		

}
