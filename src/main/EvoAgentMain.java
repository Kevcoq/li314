package main;

import java.io.IOException;

import pobj.algogen.generic.Environnement;
import pobj.algogen.generic.Population;
import pobj.algogen.generic.adapter.agent.PopulationFactory;
import agent.control.IControleur;
import agent.laby.ChargeurLabyrinthe;
import agent.laby.Labyrinthe;

/**
 * Classe principale pour �valuer des contr�leurs d'agents dans des labyrinthes
 */
public class EvoAgentMain {
	/**
	 * M�thode principale
	 * 
	 * @param args
	 *            [0] : nom du fichier o�� est charg�� le labyrinthe
	 * @param args
	 *            [1] : nombre de pas d'�valuation
	 * @param args
	 *            [2] : taille de la Population
	 * @param args
	 *            [3] : nombre de g�n�rations
	 * @param args
	 *            [4] : nombre de r�gles par controleur
	 */
	public static void main(String[] args) {
		String labyFile = "goal.mze"; // args[0];
		int nbSteps = 150; // Integer.parseInt(args[1]);
		int taillePop = 20; // Integer.parseInt(args[2]);
		int nbGens = 10; // Integer.parseInt(args[3]);
		int nbRules = 8; // Integer.parseInt(args[4]);
		Labyrinthe laby = null;
		try {
			laby = ChargeurLabyrinthe.chargerLabyrinthe(labyFile);
		} catch (IOException e) {
			System.out.println("Probleme de chargement du labyrinthe");
			System.exit(-1);
		}

		System.out
				.println("On utilisera une population de taille " + taillePop);

		Population<IControleur> pop = PopulationFactory.createRandomPopulation(
				taillePop, nbRules);
		System.out.println(pop);

		Environnement<IControleur> vc = PopulationFactory.createEnvironment(
				laby, nbSteps);
		for (int i = 0; i < nbGens; i++) {
			System.out.println("Generation " + i + ":");
			System.out.println(pop);
			pop = pop.evoluer(vc);
		}
		System.out.println("Resultat : " + pop.iterator().next());

	}
}