package pobj.algogen.generic.adapter.agent;

import pobj.algogen.generic.Environnement;
import pobj.algogen.generic.Individu;
import agent.Simulation;
import agent.control.IControleur;
import agent.laby.Labyrinthe;

/**
 * Cette classe est un adaptateur qui permet de voir un Labyrinthe comme un
 * Environnement
 */
public class EnvironnementControleur implements Environnement<IControleur> {
	/**
	 * la simulation qui sert d'�valuation
	 */
	private Labyrinthe laby;
	private int nbSteps;

	/**
	 * Constructeur
	 * 
	 * @param m
	 *            : le labyrinthe
	 * @param nbs
	 *            : un nombre de pas de simulation
	 */
	public EnvironnementControleur(Labyrinthe laby, int nbs) {
		this.laby = laby;
		nbSteps = nbs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pobj.algogen.Environnement#eval(pobj.algogen.Individu) return le
	 * score de l'individu
	 */
	public double eval(Individu<IControleur> indiv) {
		IControleur ctrl = indiv.getValeurPropre();
		Simulation simu = new Simulation(laby.clone(), ctrl);
		return simu.mesurePerf(nbSteps);
	}

}
