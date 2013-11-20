package pobj.algogen.generic.adapter.agent;

import pobj.algogen.generic.Individu;
import pobj.algogen.generic.Population;
import agent.control.IControleur;
import agent.laby.Labyrinthe;

/**
 * Classe centralisant la responsabilité de creation de Population.
 * 
 * 
 */
public class PopulationFactory {

	/**
	 * Cree une Population dont les membres sont des Individus de valeur propre
	 * tirée aléatoirement.
	 * 
	 * @param size
	 *            La taille de la population à créer.
	 * @param nbRules
	 * @return une Population nouvellement construite.
	 */
	public static Population<IControleur> createRandomPopulation(int size,
			int nbRules) {
		Population<IControleur> pop = new Population<IControleur>();
		for (int i = 0; i < size; i++) {
			pop.add(createIndividu(nbRules));
		}
		return pop;
	}

	public static Individu<IControleur> createIndividu(int nbRules) {
		return new IndividuControleur(nbRules);
	}

	public static EnvironnementControleur createEnvironment(Labyrinthe laby,
			int nbsteps) {
		return new EnvironnementControleur(laby, nbsteps);
	}
}
