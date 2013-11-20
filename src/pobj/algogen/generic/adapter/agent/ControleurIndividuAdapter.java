package pobj.algogen.generic.adapter.agent;

import pobj.algogen.generic.AbstractIndividu;
import pobj.algogen.generic.Individu;
import agent.control.IControleur;

public class ControleurIndividuAdapter extends AbstractIndividu<IControleur> {

	public ControleurIndividuAdapter(IControleur c) {
		super(c);
	}

	@Override
	public Individu<IControleur> croiser(Individu<IControleur> other) {
		if (this == other) {
			ControleurIndividuAdapter croise = this.clone();
			return croise;
		} else {
			ControleurIndividuAdapter fils = new ControleurIndividuAdapter(
					getValeurPropre().creeFils(other.getValeurPropre(), 0));
			return fils;
		}
	}

	public ControleurIndividuAdapter clone() {
		ControleurIndividuAdapter clone = new ControleurIndividuAdapter(
				getValeurPropre());
		clone.setFitness(this.getFitness());
		return clone;
	}

	public String ToString() {
		return ("Fitness " + toString());
	}

	@Override
	public void muter() {
		// TODO Auto-generated method stub
		getValeurPropre().muter(1);
	}

}