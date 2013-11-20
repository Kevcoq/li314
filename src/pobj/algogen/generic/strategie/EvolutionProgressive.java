package pobj.algogen.generic.strategie;

import pobj.algogen.generic.IEvolution;
import pobj.algogen.generic.IndivSelecteur;
import pobj.algogen.generic.Individu;
import pobj.algogen.generic.Population;

/**
 * Celle de la classe EvolutionProgressive choisit les parents de façon
 * proportionnelle à leur fitness et installe leur rejeton à la place de
 * l'individu qui a la plus mauvaise fitness au sein de la population.
 * 
 * @author Kevin, Sofien
 * @param <T>
 */
public class EvolutionProgressive<T> implements IEvolution<T> {
	private IndivSelecteur<T> select;

	public EvolutionProgressive(IndivSelecteur<T> select) {
		super();
		this.select = select;
	}

	@Override
	public Population<T> reproduire(Population<T> pop, double ratio) {
		// TODO Auto-generated method stub
		Individu<T> ind = select.getRandom(pop).croiser(select.getRandom(pop));
		pop.removeLast();
		pop.add(ind);
		return pop;
	}

}
