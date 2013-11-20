/**
 * 
 */
package pobj.arith;

/**
 * @author sigaud Représente une variable dans une expression
 */
public class Variable implements Expression, Cloneable {
	private int rang;

	/**
	 * Constructeur
	 * 
	 * @param r
	 *            : le rang de la variable
	 */
	public Variable(int r) {
		rang = r;
	}

	/**
	 * Rend la valeur de cette variable, en interrogeant EnvEval.
	 * 
	 * @return la valeur de cette variable
	 */
	public double eval(EnvEval e) {
		return e.getValue(rang);
	}

	@Override
	public String toString() {
		return "X" + rang;
	}

	@Override
	public Expression simplifier() {
		return ExpressionFactory.createVariable(rang);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Variable other = (Variable) obj;
		if (rang != other.rang)
			return false;
		return true;
	}

	public Expression clone() {
		return new Variable(rang);
	}
}
