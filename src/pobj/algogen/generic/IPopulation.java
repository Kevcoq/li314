package pobj.algogen.generic;

public interface IPopulation<T> extends Iterable<Individu<T>> {

	/**
	 * La taille de lapopulation, en nombre d'Individu.
	 * 
	 * @return la taille
	 */
	public int size();

	/**
	 * Ajoute un Individu � cette Population
	 * 
	 * @param individu
	 *            � ajouter
	 */
	public void add(Individu<T> individu);

	@Override
	public String toString();

	/**
	 * Permet de faire �voluer la Population en produisant une nouvelle
	 * g�n�ration. La fonction primordiale de la Population est de pouvoir
	 * evoluer. On passe un Environnement qui permettra de calculer le fitness
	 * des individus, afin de d�cider lesquels sont les plus aptes (survival of
	 * the fittest). On garde ici les 20% meilleurs et on les fait se reproduire
	 * pour g�n�rer la prochaine g�n�ration.
	 * 
	 * @param cible
	 *            l'objectif/probl�me � r�soudre/environnement conditionnant
	 *            l'�volution.
	 * @return une nouvelle Population, dont les membres sont tous nouveaux
	 *         (aucun individu de cette Population n'appartient � la Population
	 *         "this").
	 */
	public IPopulation<T> evoluer(Environnement<T> cible);
	public double getSommeFitnesses();

}
