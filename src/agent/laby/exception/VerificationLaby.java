package agent.laby.exception;

import java.awt.Point;

import agent.laby.ContenuCase;
import agent.laby.Labyrinthe;

public class VerificationLaby {

	public static void verifierConditions(Labyrinthe l)
			throws LabyErroneException {
		estCaseInitialeVide(l);
		estEntourerDeMurs(l);
	}

	private static void estCaseInitialeVide(Labyrinthe l)
			throws CaseDepartNonVideException {
		if (l.getContenuCase(l.getPositionInitiale()) != ContenuCase.VIDE)
			throw new CaseDepartNonVideException(l.getPositionInitiale());
	}

	private static void estEntourerDeMurs(Labyrinthe l)
			throws LabyMalEntoureException {
		for (int i = 0; i < l.Xsize(); i++) {
			if (l.getContenuCase(i, 0) != ContenuCase.MUR)
				throw new LabyMalEntoureException(new Point(i, 0));
			if (l.getContenuCase(i, l.Ysize() - 1) != ContenuCase.MUR)
				throw new LabyMalEntoureException(new Point(i, l.Ysize() - 1));
		}

		for (int j = 0; j < l.Ysize(); j++) {
			if (l.getContenuCase(0, j) != ContenuCase.MUR)
				throw new LabyMalEntoureException(new Point(0, j));
			if (l.getContenuCase(l.Xsize() - 1, j) != ContenuCase.MUR)
				throw new LabyMalEntoureException(new Point(l.Xsize() - 1, j));
		}

	}

	public static int corrigerLabyrinthe(Labyrinthe l) {
		int cpt = 0;
		try {
			verifierConditions(l);

		} catch (CaseDepartNonVideException e) {
			// TODO Auto-generated catch block
			l.setContenuCase(e.getPoint(), ContenuCase.VIDE);
			cpt = cpt + 1 + corrigerLabyrinthe(l);
		} catch (LabyMalEntoureException e) {
			l.setContenuCase(e.getPoint(), ContenuCase.MUR);
			cpt = cpt + 1 + corrigerLabyrinthe(l);
		} catch (LabyErroneException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); // Indique l'exception sur le flux d'erreur
									// standard
		}

		return cpt;
	}
}
