/**
 * 
 */
package pobj.arith;

/**
 * Une Expression arithmétique qui représente un opérateur binaire (+,-,/,*).
 * 
 * @author sigaud
 * 
 */
public class OperateurBinaire implements Expression {
	/**
	 * fils gauche de l'expression
	 */
	private Expression left;
	/**
	 * fils droit de l'expression
	 */
	private Expression right;

	/**
	 * Le type de cet opérateur, choisi dans l'enum {@link Operator}
	 */
	private Operator type;

	/**
	 * Ctor d'expression binaire, on passe l'operateur et les deux expressions
	 * filles.
	 * 
	 * @param t
	 *            type de l'opération
	 * @param l
	 *            fils gauche
	 * @param r
	 *            fild droit
	 */
	public OperateurBinaire(Operator t, Expression l, Expression r) {
		type = t;
		left = l;
		right = r;
	}

	/**
	 * Evalue l'expression dans un environnement donné. On applique les règles
	 * arithmétiques + invocations récursives sur les fils. {@inheritDoc}
	 */
	public double eval(EnvEval e) {
		if (type == Operator.PLUS)
			return left.eval(e) + right.eval(e);
		else if (type == Operator.MINUS)
			return left.eval(e) - right.eval(e);
		else if (type == Operator.MULT)
			return left.eval(e) * right.eval(e);
		else
			return left.eval(e) / right.eval(e);
	}

	/**
	 * Accessor.
	 * 
	 * @return le fils gauche de l'expression
	 */
	public Expression getLeft() {
		return left;
	}

	/**
	 * Accessor.
	 * 
	 * @return le fils droit de l'expression
	 */
	public Expression getRight() {
		return right;
	}

	/**
	 * Un affichage infixe de l'expression.
	 */
	@Override
	public String toString() {
		StringBuilder ret = new StringBuilder("(" + left);
		switch (type) {
		case PLUS:
			ret.append(" + ");
			break;
		case MINUS:
			ret.append(" - ");
			break;
		case MULT:
			ret.append(" * ");
			break;
		default:
			ret.append(" / ");
			break;
		}
		ret.append(right + ")");
		return ret.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Expression simplifier() {
		Expression ls = left.simplifier();
		Expression rs = right.simplifier();
		if (rs instanceof Constante && ls instanceof Constante) {
			return ExpressionFactory.createConstant(eval(null));
		} else {
			return ExpressionFactory.createOperateurBinaire(type, ls, rs);
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OperateurBinaire other = (OperateurBinaire) obj;
		if (left == null) {
			if (other.left != null)
				return false;
		} else if (!left.equals(other.left))
			return false;
		if (right == null) {
			if (other.right != null)
				return false;
		} else if (!right.equals(other.right))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	public Expression clone() {
		return new OperateurBinaire(type, left.clone(), right.clone());
	}
}
