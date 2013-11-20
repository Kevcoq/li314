package pobj.algogen.generic;

public abstract class AbstractIndividu<T> implements Individu<T>, Cloneable {

	private double fitness = 0.0;
	private T valeurPropre;

	public AbstractIndividu(T valeurPropre) {
		this.valeurPropre = valeurPropre;
	}

	public void setFitness(double d) {
		fitness = d;
	}

	public double getFitness() {
		return fitness;
	}

	protected void setValeurPropre(T valeurPropre) {
		this.valeurPropre = valeurPropre;
	}

	public T getValeurPropre() {
		return valeurPropre;
	}

	public abstract Individu<T> clone();

	/**
	 * Satisfait le contrat Comparable<Individu>. La comparaison de deux
	 * individus est basée sur leur fitness croissante. Cette fonction permet
	 * d'assurer le tri d'une Population. {@inheritDoc} {@link Comparable}
	 * 
	 * @param o
	 *            l'individu auquel on se compare
	 * @return -1, 0 ou 1 selon que l'on est resp. plus petit, égal ou plus
	 *         grand que o.
	 */
	@Override
	public int compareTo(Individu<T> o) {
		if (o.getFitness() == getFitness())
			return 0;

		else if (o.getFitness() < getFitness())
			return -1;
		return 1;
	}

}
