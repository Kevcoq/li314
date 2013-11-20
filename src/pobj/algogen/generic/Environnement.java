package pobj.algogen.generic;

/**
 * Un Environnement repr�sente un probl�me ou une situation � r�soudre. Dans un
 * certain environnement on peut obtenir une m�trique mesurant la pertinence de
 * chaque individu d'une Population.
 * 
 */
public interface Environnement<T> {

	/**
	 * Calcule la pertinence/fitness d'un individu vis-�-vis de l'objectif. La
	 * fitness est comprise entre 0 et 1. Plus la valeur est faible, plus
	 * l'individu est pertinent.
	 * 
	 * @param ind
	 *            l'individu qu'on �value
	 * @return une m�trique donnant l'adaptation de l'individu � son
	 *         environnement.
	 * */
	double eval(Individu<T> ind);
}
