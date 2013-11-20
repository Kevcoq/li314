package pobj.arith;

/**
 * Représente une expression quelconque
 */
public interface Expression extends Cloneable {

	/**
	 * Une Expression peut être évaluée dans un environnement donné.
	 * L'environnement fixe la valeur des variables éventuelles. Cette fonction
	 * permet d'obtenir la valeur de l'expression pour une certaine valuation
	 * donnée de ses variables.
	 * 
	 * @param e
	 *            : l'environnement d'évaluation
	 * @return : la valeur de l'expression en ce point.
	 */
	public double eval(EnvEval e);

	public Expression clone();

	/**
	 * Permet de simplifier une expression en réduisant les sous-termes
	 * constants à des constantes.
	 * 
	 * @return : l'expression simplifiée
	 */
	public Expression simplifier();
}
