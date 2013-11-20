package pobj.algogen.generic.adapter.arith;

import pobj.algogen.generic.AbstractIndividu;
import pobj.algogen.generic.Individu;
import pobj.algogen.generic.Population;
import pobj.arith.Expression;
import pobj.arith.ExpressionFactory;
import pobj.arith.Operator;

/**
 * Un individu, connu pour etre membre d'une {@link Population}. L'individu
 * porte une valeur propre qui le caract�rise et une fitness qui caracterise son
 * adaptation � l'environnement.
 * 
 */
public class IndividuExpression extends AbstractIndividu<Expression> implements
		Individu<Expression> {

	/**
	 * Ctor d'Individu dont la valeur propre est tir�e al�atoirement.
	 */
	public IndividuExpression() {
		this(ExpressionFactory.createRandomExpression());
	}

	/**
	 * Ctor d'Individu permettant de positionner la valeur propre
	 * 
	 * @param valeurPropre
	 */
	public IndividuExpression(Expression valeurPropre) {
		super(valeurPropre);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "vp = " + getValeurPropre() + " fit = " + getFitness();
	}

	/**
	 * Permet de croiser les individus (se reproduire). On obtient un individu
	 * dont les caract�ristiques sont un m�lange des deux parents. NB: en
	 * principe le croisement est une op�ration sym�trique. a.croise(b) ~
	 * b.croise(a) NB2: le croisement ne doit modifier aucun des deux parents.
	 * 
	 * @param autre
	 *            l'autre parent
	 * @return un individu nouvellement cr��
	 */
	@Override
	public Individu<Expression> croiser(Individu<Expression> autre) {
		if (autre instanceof IndividuExpression) {
			Expression somme = ExpressionFactory.createOperateurBinaire(
					Operator.PLUS, this.getValeurPropre(),
					autre.getValeurPropre());
			Expression result = ExpressionFactory.createOperateurBinaire(
					Operator.DIV, somme, ExpressionFactory.createConstant(2.0));
			return new IndividuExpression(result);
		}
		return null;
	}

	/**
	 * Cette op�ration permet de faire muter un individu. Elle modifie en place
	 * l'individu en lui affectant une nouvelle valeur propre. Actuellement, on
	 * prend la moyenne de la valeur propre et d'une valeur al�atoire. D'autres
	 * strat�gies sont envisageables.
	 */
	@Override
	public void muter() {
		// Strategie 1 : jusqu'� + ou - 10% de variation

		// Strategie 2 : random moyenn�
		setValeurPropre(ExpressionFactory.createRandomExpression());

		// Strategie 3 : random pur
	}

	@Override
	public Individu<Expression> clone() {
		// TODO Auto-generated method stub
		Individu<Expression> ind = new IndividuExpression(getValeurPropre());
		ind.setFitness(getFitness());
		return ind;
	}
}
