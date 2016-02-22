/*
 * auteur : Jordy CABANNES
 */

package validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
//On relie l'annotation à la classe qui va permettre la validation de la donnée
@Constraint(validatedBy = {CodePostalValidator.class})
@Target(ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface CodePostalValide {
	
	//Message que l'on renvoi si le format du code postal saisi n'est pas bon
	String message() default "Format de code postal invalide, doit être de la forme LCLCLC, "
			+ "ou LCL-CLC ou LCL CLC avec C: chiffre et L : Lettre en majuscule.";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};

}
