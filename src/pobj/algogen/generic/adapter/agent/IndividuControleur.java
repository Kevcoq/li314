package pobj.algogen.generic.adapter.agent;

import pobj.algogen.generic.AbstractIndividu;
import pobj.algogen.generic.Individu;
import agent.control.ControlFactory;
import agent.control.IControleur;

public class IndividuControleur extends AbstractIndividu<IControleur> implements
		Individu<IControleur> {

	/**
	 * Constructeur
	 * 
	 * @param controleur
	 *            : le contr�leur contenu
	 */
	public IndividuControleur(IControleur controleur) {
		super(controleur);
	}

	/**
	 * Constructeur qui cr�e un contr�leur al�atoire
	 * 
	 * @param nbRules
	 *            : le nombre de regles � cr�er
	 */
	public IndividuControleur(int nbRules) {
		super(ControlFactory.createControleur(nbRules));
	}

	@Override
	public String toString() {
		String retour = "" + getFitness() + ":" + getValeurPropre();
		return retour;
	}

	@Override
	public Individu<IControleur> croiser(Individu<IControleur> autre) {
		return new IndividuControleur(
				getValeurPropre().creeFils(
						((AbstractIndividu<IControleur>) autre)
								.getValeurPropre(), 0.05));
	}

	@Override
	public void muter() {
		getValeurPropre().muter(0.05);
	}

	@Override
	public Individu<IControleur> clone() {
		// TODO Auto-generated method stub
		Individu<IControleur> ind = new IndividuControleur(getValeurPropre());
		ind.setFitness(getFitness());
		return ind;
	}
}
