package pobj.util;

import java.util.Random;

public final class Generateur extends Random {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** L'instance statique */
	private static Generateur instance;
	private static Random generateur;
	private static long graine;

	/**
	 * Récupère l'instance unique de la class Singleton. Remarque : le
	 * constructeur est rendu inaccessible
	 */

	public static Generateur getInstance() {
		if (null == instance) // Premier appel 
			instance = new Generateur();
		return instance;
	}

	/**
	 * Constructeur redéfini comme étant privé pour interdire son appel et
	 * forcer à passer par la méthode <link
	 */
	private Generateur() {
		generateur = new Random(graine);
	}

	public int nextInt() {
		return generateur.nextInt();
	}

	public double nextDouble() {
		return generateur.nextDouble();
	}

	public static void setGraine(long graine) {
		Generateur.graine = graine;
		generateur = new Random(graine);
	}

	public int nextInt(int i) {
		return generateur.nextInt(i);
	}
}
