package pobj.algogen.generic.strategie;

import pobj.algogen.generic.IndivSelecteur;
import pobj.algogen.generic.Individu;
import pobj.algogen.generic.Population;
import pobj.util.Generateur;

public class SelecteurParFitness<T> implements IndivSelecteur<T> {

	@Override
	public Individu<T> getRandom(Population<T> pop) {
		// TODO Auto-generated method stub
		double somme = 0, x = Generateur.getInstance().nextDouble()
				% pop.getSommeFitnesses();
		for (Individu<T> i : pop)
			if (somme > x)
				return i;
			else
				somme += i.getFitness();
		return pop.get(pop.size()-1);
	}

}
