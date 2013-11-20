package pobj.algogen.generic.adapter.agent;

import pobj.algogen.generic.Environnement;
import pobj.algogen.generic.Individu;
import agent.Simulation;
import agent.control.IControleur;
import agent.laby.Labyrinthe;

public class LabyEnvironnementAdapter implements Environnement<IControleur> {

	private int nbSteps;
	private Labyrinthe laby;

	/**
	 * Créer l'environnement du labyrinthe
	 * @param laby le labyrinthe
	 * @param nbSteps le nombre de pas d'évaluation
	 */
	public LabyEnvironnementAdapter(Labyrinthe laby, int nbSteps) {
		super();
		this.laby = laby;
		this.nbSteps = nbSteps;
	}

	public int getNbSteps() {
		return nbSteps;
	}

	public void setNbSteps(int nbSteps) {
		this.nbSteps = nbSteps;
	}

	@Override
	/** cette fonction renvoie la fitness de l'individu donnï¿½ en argument */
	public double eval(Individu<IControleur> individu) {
		Labyrinthe laby2 = laby.clone();
		Simulation simu = new Simulation(laby2, individu.getValeurPropre());
		return simu.mesurePerf(nbSteps);
	}

}
