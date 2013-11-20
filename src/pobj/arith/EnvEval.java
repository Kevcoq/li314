/**
 * 
 */
package pobj.arith;

import java.util.Arrays;

/**
 * @author sigaud Représente un environnement d'évaluation pour une expression
 *         dotée de variable en l'occurrence, affecte des valeurs aux variables
 */
public class EnvEval {
	private double[] variables;

	/**
	 * Constructeur
	 * 
	 * @param nbVars
	 *            : le nombre de variables
	 */
	public EnvEval(int taille) {
		variables = new double[taille];
	}

	/**
	 * Affecte une valeur à une variable
	 * 
	 * @param index
	 *            : l'index de la variable
	 * @param val
	 *            : la valeur de la variable
	 */
	public void setVariable(int rang, double val) {
		variables[rang] = val;
	}

	/**
	 * Renvoie la valeur d'une variable
	 * 
	 * @param index
	 *            : l'index de la variable
	 * @return : la valeur de la variable
	 */
	public double getValue(int rang) {
		return variables[rang];
	}

	@Override
	public String toString() {
		return Arrays.toString(variables);
	}
}
