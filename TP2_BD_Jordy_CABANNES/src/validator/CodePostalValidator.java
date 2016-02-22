/*
 * auteur : Jordy CABANNES
 */

package validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
 
public class CodePostalValidator implements ConstraintValidator<CodePostalValide, String> {
 
  @Override
  public void initialize(CodePostalValide constraintAnnotation) 
  {}
  
  //Méthode qui permettra de vérifier si le format du code postal est correct
  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) 
  {
	  boolean res=true; 
	  
	  //On vérifie que le code postal n'est pas null et qu'il est de la forme LCL CLC avec entre LCL CLC la présence
	  //d'un trait d'union, d'un espace ou non.
	  if(value==null || !value.matches("^[A-Z]{1}[0-9]{1}[A-Z]{1}(-| |){1}[0-9]{1}[A-Z]{1}[0-9]{1}$"))
	  {
		  res=false;
	  }
	  return res;
  }
 
}
