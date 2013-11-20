package pobj.algogen.generic;

/**
 * Un individu, con�u pour etre membre d'une {@link Population}. L'individu
 * porte une valeur propre qui le caract�rise et une fitness qui caracterise son
 * adaptation � l'environnement.
 * 
 */
public interface Individu<T> extends Comparable<Individu<T>> {

	public double getFitness();

	public void setFitness(double d);

	public T getValeurPropre();

	/**
	 * Permet de croiser les individus (se reproduire). On obtient un individu
	 * dont les caract�ristiques sont un m�lange des deux parents. NB: en
	 * principe le croisement est une op�ration sym�trique. a.croise(b) ~
	 * b.croise(a) NB2: le croisement ne doit modifier aucun des deux parents.
	 * 
	 * @param autre
	 *            l'autre parent
	 * @return un individu nouvellement cr��
	 */
	public Individu<T> croiser(Individu<T> autre);

	/**
	 * Cette op�ration permet de faire muter un individu. Elle modifie en place
	 * l'individu en lui affectant une nouvelle valeur propre. Actuellement, on
	 * prend la moyenne de la valeur propre et d'une valeur al�atoire. D'autres
	 * strat�gies sont envisageables.
	 */
	public void muter();

	public Individu<T> clone();

}
