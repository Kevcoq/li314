package pobj.algogen.generic.adapter.arith;

import pobj.algogen.generic.Environnement;
import pobj.algogen.generic.Individu;
import pobj.arith.EnvEval;
import pobj.arith.Expression;
import pobj.arith.ExpressionFactory;
import pobj.util.Generateur;

/**
 * Un environnement qui repr�sente une valeur cible particuliere, adapt�e aux
 * Individus munis d'une valeur propre.
 * 
 * 
 */
public class EnvironnementExpression implements Environnement<Expression> {
	/** La valeur qu'on cherche � atteindre. */
	private double cible;
	private EnvEval env;

	/** Tire une valeur aleatoire dans l'intervalle [0,1[. */
	public EnvironnementExpression() {
		cible = Generateur.getInstance().nextDouble()%1;
		env = ExpressionFactory.createRandomEnvironment();
	}

	@Override
	public String toString() {
		return "Objectif : f(" + env + ") = " + cible;
	}

	/**
	 * Mesure l'�cart entre la valeur propre de l'indivdu et la valeur cible.
	 * {@inheritDoc}
	 */
	public double eval(Individu<Expression> i) {
		double r1 = i.getValeurPropre().eval(env);
		// if (r1==r2) return -1;
		// return (1/((r2-r1)*(r2-r1)));
		return Math.abs(cible - r1);
	}

}
