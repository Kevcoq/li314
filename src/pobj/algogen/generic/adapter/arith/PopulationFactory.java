package pobj.algogen.generic.adapter.arith;

import pobj.algogen.generic.Population;
import pobj.arith.Expression;

/**
 * Classe centralisant la responsabilit� de creation de Population.
 * 
 * 
 */
public class PopulationFactory {
	/**
	 * Cree une Population dont les membres sont des Individus de valeur propre
	 * tir�e aleatoirement.
	 * 
	 * @param size
	 *            La taille de la population � cr�er.
	 * @return une Population nouvellement construite.
	 */
	public static Population<Expression> createRandomPopulation(int size) {
		Population<Expression> pop = new Population<Expression>();
		for (int i = 0; i < size; i++) {
			pop.add(new IndividuExpression());
		}
		return pop;
	}
}
