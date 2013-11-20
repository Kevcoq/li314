package pobj.arith;

import pobj.util.Generateur;

public class ExpressionFactory {

	private static int MAXVARIABLE = 2;

	private static Generateur generateur = Generateur.getInstance();

	/**
	 * Un ctor pour des expressions binaires usuelles: +,-,*,/
	 * 
	 * @param op
	 *            le type de l'opérande, {@link Operator}, PLUS,MOINS,MULT,DIV
	 * @param left
	 *            operande gauche
	 * @param right
	 *            operande droite
	 * @return une expression binaire
	 */
	public static Expression createOperateurBinaire(Operator op,
			Expression left, Expression right) {
		return new OperateurBinaire(op, left, right);
	}

	/**
	 * Un ctor d'expressions constantes.
	 * 
	 * @param constant
	 *            sa valeur
	 * @return une constante
	 */
	public static Expression createConstant(double constant) {
		return new Constante(constant);
	}

	/**
	 * Un ctor de variables, identifiées par un entier compris entre 0 et
	 * MAXVARIABLES. La demande de création de variables d'indice plus grand
	 * entraine un accroissement de MAXVARIABLE.
	 * 
	 * @param id
	 *            l'indice de la variable
	 * @return une Variable
	 */
	public static Expression createVariable(int id) {
		if (id >= MAXVARIABLE)
			MAXVARIABLE = id + 1;
		return new Variable(id);
	}

	/**
	 * Un ctor qui borne à 5 la profondeur des expressions aléatoires générées.
	 * 
	 * @return une expression aléatoire de profondeur <= 5
	 */
	public static Expression createRandomExpression() {
		return createRandomExpression(3);
	}

	/**
	 * Un ctor pour générer des expressions aléatoires, utilise MAXVARIABLE pour
	 * déterminer des indices de variables raisonnables.
	 * 
	 * @param maxProf
	 *            la profondeur maximale de l'expression générée
	 * @return une expression, possiblement complexe.
	 */
	public static Expression createRandomExpression(int maxProf) {
		int type;
		if (maxProf == 0)
			type = generateur.nextInt(2);
		else
			type = generateur.nextInt(4);

		switch (type) {
		case 0:
			return createVariable(generateur.nextInt(MAXVARIABLE));
		case 1:
			return createConstant(generateur.nextDouble());
		default:
			Expression left = createRandomExpression(maxProf - 1);
			Expression right = createRandomExpression(maxProf - 1);
			// Accés à l'enum OPERATOR en mode indicé via la méthode values()
			return createOperateurBinaire(Operator.values()[type - 2], left,
					right);
		}
	}

	/**
	 * Génère un environnement d'évaluation aléatoire, en supposant qu'il n'y a
	 * pas plus de MAXVARIABLES.
	 * 
	 * @return Un environnement généré aléatoirement.
	 */
	public static EnvEval createRandomEnvironment() {
		EnvEval e = new EnvEval(MAXVARIABLE);
		for (int i = 0; i < MAXVARIABLE; i++) {
			e.setVariable(i, generateur.nextDouble());
		}
		return e;
	}
}
