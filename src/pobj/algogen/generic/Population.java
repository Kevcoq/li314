package pobj.algogen.generic;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import pobj.algogen.generic.strategie.EvolutionGenerationnelle;
import pobj.algogen.generic.strategie.EvolutionProgressive;
import pobj.algogen.generic.strategie.SelecteurParFitness;
import pobj.algogen.generic.strategie.SelecteurUniforme;
import pobj.util.Config;
import pobj.util.Generateur;
import pobj.util.MyArrayList;

/**
 * Une Population, compos�e d'Individu.
 * 
 */
public class Population<T> implements IPopulation<T> {

	/**
	 * Les individus composant la Population. Note: on type avec l'interface
	 * List de pr�f�rence � la classe d'impl�mentation choisie ici
	 * ArrayList. Note2: Pas de ctor explicite dans cette classe, le ctor par
	 * d�faut cr�e des Population vide.
	 */
	private List<Individu<T>> individus = new MyArrayList<Individu<T>>();
	private IEvolution<T> evo;
	private static Config config = Config.getInstance();

	/**
	 * La taille de lapopulation, en nombre d'Individu.
	 * 
	 * @return la taille
	 */
	public int size() {
		return individus.size();
	}

	/**
	 * Ajoute un Individu � cette Population
	 * 
	 * @param individu
	 *            � ajouter
	 */
	public void add(Individu<T> individu) {
		individus.add(individu);
	}

	@Override
	public String toString() {
		return individus.toString();
	}

	/**
	 * Permet de faire �voluer la Population en produisant une nouvelle
	 * g�n�ration. La fonction primordiale de la Population est de pouvoir
	 * evoluer. On passe un Environnement qui permettra de calculer le fitness
	 * des individus, afin de d�cider lesquels sont les plus aptes (survival
	 * of the fittest). On garde ici les 20% meilleurs et on les fait se
	 * reproduire pour g�n�rer la prochaine g�n�ration.
	 * 
	 * @param cible
	 *            l'objectif/probl�me � r�soudre/environnement
	 *            conditionnant l'�volution.
	 * @return une nouvelle Population, dont les membres sont tous nouveaux
	 *         (aucun individu de cette Population n'appartient � la
	 *         Population "this").
	 */
	public Population<T> evoluer(Environnement<T> cible) {
		evaluer(cible); // Mesurer la fitness des individus + trier.
		Population<T> pop = reproduire(this, 0); // g�n�rer une nouvelle
														// population
		// en
		// se basant sur les 20% meilleurs.
		pop.muter(0.05); // 5% de mutation
		pop.evaluer(cible); // mise � jour du fitness
		return pop;
	}

	/**
	 * Evalue les individus dans un certain environnement. La Population est
	 * �galement tri�e par fitness au cours de ce traitement.
	 * 
	 * @param cible
	 *            l'environnement qui repr�sente notre objectif.
	 */
	public void evaluer(Environnement<T> cible) {
		for (Individu<T> ind : individus) {
			ind.setFitness(cible.eval(ind));
		}
		Collections.sort(individus); // Attention necessite que Individu
										// implemente Comparable<Individu>
	}

	/**
	 * Fait muter les membres de la Population, avec la probabilit� pass�e
	 * en parametre. En g�n�ral on passe une valeur plutot faible < 10%.
	 * 
	 * @param ratio
	 *            le ratio de mutation de chaque individu.
	 */
	private void muter(double ratio) {
		for (Individu<T> ind : individus) {
			if (Generateur.getInstance().nextDouble() % 1 < ratio)
				ind.muter();
		}
	}

	/**
	 * Construit une nouvelle Population en croisant les 20% meilleurs de la
	 * Population actuelle.
	 * 
	 * @return une nouvelle Population, dont 80% des membres descendent des 20%
	 *         meilleurs.
	 */
	private Population<T> reproduire(Population<T> pop, double ratio) {
		IndivSelecteur<T> select;
		if (config.getParameterValue(AlgoGenParameter.SELECTION).equals("true"))
			select = new SelecteurUniforme<>();
		else
			select = new SelecteurParFitness<>();
		if (config.getParameterValue(AlgoGenParameter.EVO).equals("true"))
			evo = new EvolutionGenerationnelle<>(select);
		else
			evo = new EvolutionProgressive<>(select);
		return evo.reproduire(pop, ratio);

	}

	@Override
	public Iterator<Individu<T>> iterator() {
		return individus.iterator();
	}

	public Individu<T> get(int i) {
		return individus.get(i);
	}

	public double getSommeFitnesses() {
		double somme = 0;
		for (Individu<T> i : individus)
			somme += i.getFitness();
		return somme;
	}

	public void removeLast() {
		// TODO Auto-generated method stub
		individus.remove(individus.size()-1);
	}
}
