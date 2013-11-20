package pobj.arith;

/**
 * Représente une constante dans une expression arithmétique on se contente des
 * réels entre 0 et 1
 */
public class Constante implements Expression {
	private double value;

	/**
	 * Constructeur
	 * 
	 * @param d
	 *            : la valeur de la constante
	 */
	public Constante(double d) {
		value = d;
	}

	/**
	 * La valeur d'une constante est constante quel que soit l'environnement.
	 * {@inheritDoc}
	 */
	public double eval(EnvEval e) {
		return value;
	}

	/**
	 * Accessor.
	 * 
	 * @return la valeur de cette constante
	 */
	public double getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "" + value;
	}

	@Override
	public Expression simplifier() {
		return ExpressionFactory.createConstant(value);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(value);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Constante other = (Constante) obj;
		if (Double.doubleToLongBits(value) != Double
				.doubleToLongBits(other.value))
			return false;
		return true;
	}

	@Override
	public Expression clone() {
		return new Constante(value);
	}
}
