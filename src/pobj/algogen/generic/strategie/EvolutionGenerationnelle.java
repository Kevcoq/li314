package pobj.algogen.generic.strategie;

import pobj.algogen.generic.IEvolution;
import pobj.algogen.generic.IndivSelecteur;
import pobj.algogen.generic.Population;

public class EvolutionGenerationnelle<T> implements IEvolution<T> {
	private IndivSelecteur<T> select;

	public EvolutionGenerationnelle(IndivSelecteur<T> select) {
		super();
		this.select = select;
	}

	@Override
	public Population<T> reproduire(Population<T> pop, double ratio) {
		// TODO Auto-generated method stub

		Population<T> p = new Population<T>();
		for (int i = 0; i < pop.size(); i++)
			p.add((select.getRandom(pop)).croiser(select.getRandom(pop)));
		return p;
	}

}
