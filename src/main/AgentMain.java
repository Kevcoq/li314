package main;

import java.io.IOException;

import agent.Simulation;
import agent.control.ControlFactory;
import agent.control.IControleur;
import agent.laby.ChargeurLabyrinthe;
import agent.laby.Labyrinthe;

/**
 * Classe principale pour tester le comportement des agents dans le labyrinthe.
 * 
 */
public class AgentMain {

	/**
	 * @param args
	 *            [0] : nom du fichier contenant le labyrinthe
	 * @param args
	 *            [1] : nombre de pas d'�valuation
	 * @param args
	 *            [2] : nombre de r�gles par controleur
	 */
	public static void main(String[] args) {

		String labyFile = "goal.mze"; // args[0];
		int nbSteps = 50; // Integer.parseInt(args[1]);
		int nbRules = 10; // Integer.parseInt(args[2]);
		Labyrinthe laby = null;
		try {
			laby = ChargeurLabyrinthe.chargerLabyrinthe(labyFile);
		} catch (IOException e) {
			System.out.println("Probl�me de chargement du labyrinthe" + e);
			System.exit(-1);
		}

		IControleur sc = ControlFactory.createControleur(nbRules);
		Simulation ms = new Simulation(laby.clone(), sc);
		System.out.println(ms.mesurePerf(nbSteps));
		System.out.println(sc);

	}
}
