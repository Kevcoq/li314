package agent.laby.interf;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import pobj.algogen.generic.AlgoGenParameter;
import pobj.algogen.generic.Environnement;
import pobj.algogen.generic.Population;
import pobj.algogen.generic.adapter.agent.LabyEnvironnementAdapter;
import pobj.algogen.generic.adapter.agent.PopulationFactory;
import pobj.util.Config;
import pobj.util.Generateur;
import agent.Simulation;
import agent.control.IControleur;
import agent.laby.ChargeurLabyrinthe;
import agent.laby.Labyrinthe;

public class LabyViewer extends JFrame {
	/*******************/
	/**/
	private static Config config;
	/**/
	/****************/

	private static final long serialVersionUID = 1L;

	private JPanel sidePanel; // zone boutons
	private Labyrinthe laby; // Le labyrinthe sous-jacent
	private LabyActivePanel centerPanel; // La zone montrant le labyrinthe
	private static final int COLS = 15, LIGNES = 10; // Les tailles, constantes

	private static int nbEvo;
	private static int nbInd;

	/**
	 * Constructeur
	 */
	public LabyViewer() {
		// Titre de la JFrame
		super("Laby Viewer");

		// Construire le maze
		laby = new Labyrinthe(COLS, LIGNES);

		// creer les Panel et menus
		createCenterPanel();
		createSidePanel();

		// Positionner la taille de la fenetre
		setSize(800, 758);
		setResizable(false);
		// traiter le clic sur la croix
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		// rendre visible
		setVisible(true);
	}

	/**
	 * Crée le panneau latéral, ses boutons et associe les actions
	 * appropriées aux boutons.
	 */
	private void createSidePanel() {

		sidePanel = new JPanel();
		sidePanel.setLayout(new GridLayout(4, 4));

		JButton playButton = new JButton("Play");
		sidePanel.add(playButton);

		JTextArea controleurToString = new JTextArea("euh ???");// controleur.toString());
		sidePanel.add(controleurToString);

		JTextArea nbPasArea = new JTextArea("Nombre de pas : ");
		sidePanel.add(nbPasArea);
		final JTextField nbPasField = new JTextField(""
				+ config.getParameterValue(AlgoGenParameter.NB_PAS));
		sidePanel.add(nbPasField);

		JTextArea nbEvoArea = new JTextArea("Nombre d'�volution : ");
		sidePanel.add(nbEvoArea);
		final JTextField nbEvoField = new JTextField("" + nbEvo);
		sidePanel.add(nbEvoField);

		JTextArea nbIndArea = new JTextArea("Nombre d'individu : ");
		sidePanel.add(nbIndArea);
		final JTextField nbIndField = new JTextField("" + nbInd);
		sidePanel.add(nbIndField);

		// Les actions sur les boutons, cette forme anonyme évite les
		// getSource() dans actionPerformed
		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// L'action consiste simplement à positionner l'effet d'un
				// clic dans l'interface MazePanel
				new Thread(new Runnable() {
					public void run() {
						// invoquer un traitement long ou une animation

						try {
							nbEvo = Integer.parseInt(config
									.getParameterValue(AlgoGenParameter.NB_EVO));
							nbInd = Integer.parseInt(config
									.getParameterValue(AlgoGenParameter.NB_IND));
							Generateur.setGraine(Integer.parseInt(config
									.getParameterValue(AlgoGenParameter.GRAINE)));
							// MAJ de nbpas
							// nbPas = Integer.parseInt(nbPasField.getText());

							// Cr�ation du labyrinthe et de la pop
							Labyrinthe labyMain;
							labyMain = ChargeurLabyrinthe.chargerLabyrinthe(config
									.getParameterValue(AlgoGenParameter.LABYRINTHE));
							Environnement<IControleur> env = new LabyEnvironnementAdapter(
									labyMain,
									Integer.parseInt(config
											.getParameterValue(AlgoGenParameter.NB_STEPS)));
							Population<IControleur> pop = PopulationFactory.createRandomPopulation(
									nbInd,
									Integer.parseInt(config
											.getParameterValue(AlgoGenParameter.NB_RULES)));

							// Evolution de la pop
							for (int i = 0; i < nbEvo; i++)
								pop = pop.evoluer(env);

							// Lancement de la simu
							Labyrinthe labyCopie = laby.clone();
							Simulation simu = new Simulation(labyCopie, pop
									.get(0).getValeurPropre());
							centerPanel.setLaby(simu.getLaby());
							simu.addObserver(centerPanel);
							simu.mesurePerf(Integer.parseInt(config
									.getParameterValue(AlgoGenParameter.NB_PAS)));
							System.out.println(simu.getScore());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}).start();
			}
		});

		getContentPane().add(sidePanel, BorderLayout.EAST);

	}

	/**
	 * Crée le MazePanel responsable d'afficher le Maze courant.
	 */
	private void createCenterPanel() {
		centerPanel = new LabyActivePanel(laby);
		getContentPane().add(centerPanel, BorderLayout.CENTER);

	}

	public static void main(String[] args) {
		try {
			config = Config.chargerConfig(args[0]);
			new LabyViewer();

		} catch (IOException e) {
			new IOException("Could not find test maze !");
			e.printStackTrace();
		}

	}
}
