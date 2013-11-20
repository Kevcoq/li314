package pobj.algogen.generic.strategie;

import pobj.algogen.generic.IndivSelecteur;
import pobj.algogen.generic.Individu;
import pobj.algogen.generic.Population;
import pobj.util.Generateur;

public class SelecteurUniforme<T> implements IndivSelecteur<T> {

	@Override
	public Individu<T> getRandom(Population<T> pop) {
		// TODO Auto-generated method stub
		return pop.get(Generateur.getInstance().nextInt(pop.size()));
	}

}
