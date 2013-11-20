package agent.laby.interf;

import java.awt.event.ActionEvent;

import pobj.obs.ISimpleObserver;
import agent.laby.Labyrinthe;

public class LabyActivePanel extends LabyPanel implements ISimpleObserver {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LabyActivePanel(Labyrinthe laby) {
		super(laby);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		updateGraphics();
		try {
			Thread.sleep(50);// en millisecondes
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// laby.setContenuCase(laby.getPositionInitiale(), ContenuCase.VIDE);
		// laby.setPositionInitiale(getMousePosition());
		// laby.setContenuCase(laby.getPositionInitiale(), ContenuCase.AGENT);
	}

}
