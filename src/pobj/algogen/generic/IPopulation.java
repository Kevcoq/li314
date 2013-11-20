package pobj.algogen.generic;

public interface IPopulation<T> extends Iterable<Individu<T>> {

	/**
	 * La taille de lapopulation, en nombre d'Individu.
	 * 
	 * @return la taille
	 */
	public int size();

	/**
	 * Ajoute un Individu à cette Population
	 * 
	 * @param individu
	 *            à ajouter
	 */
	public void add(Individu<T> individu);

	@Override
	public String toString();

	/**
	 * Permet de faire évoluer la Population en produisant une nouvelle
	 * génération. La fonction primordiale de la Population est de pouvoir
	 * evoluer. On passe un Environnement qui permettra de calculer le fitness
	 * des individus, afin de décider lesquels sont les plus aptes (survival of
	 * the fittest). On garde ici les 20% meilleurs et on les fait se reproduire
	 * pour générer la prochaine génération.
	 * 
	 * @param cible
	 *            l'objectif/probléme à résoudre/environnement conditionnant
	 *            l'évolution.
	 * @return une nouvelle Population, dont les membres sont tous nouveaux
	 *         (aucun individu de cette Population n'appartient à la Population
	 *         "this").
	 */
	public IPopulation<T> evoluer(Environnement<T> cible);
	public double getSommeFitnesses();

}
