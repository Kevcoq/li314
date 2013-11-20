package test;

import java.io.IOException;

import junit.framework.TestCase;
import pobj.algogen.generic.Individu;
import pobj.algogen.generic.Population;
import pobj.algogen.generic.adapter.agent.LabyEnvironnementAdapter;
import pobj.algogen.generic.adapter.agent.PopulationFactory;
import pobj.util.Config;
import agent.control.IControleur;
import agent.laby.ChargeurLabyrinthe;
import agent.laby.Labyrinthe;

public class PopulationTest extends TestCase {
	private LabyEnvironnementAdapter env;
	private Labyrinthe labyTest;
	private Population<IControleur> pop;

	public PopulationTest(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		super.setUp();
		Config.chargerConfig("config.cfg");
		try {
			labyTest = ChargeurLabyrinthe.chargerLabyrinthe("trial.mze");
		} catch (IOException e) {
			fail("Could not find test maze !");
		}
		env = new LabyEnvironnementAdapter(labyTest, 10);
		pop = (Population<IControleur>) PopulationFactory
				.createRandomPopulation(100, 20);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testSize() {
		assertTrue(pop.size() == 100);
	}

	/*
	 * On vérifie que la taille augmente de 1
	 */
	public void testAdd() {
		int size = pop.size();
		Individu<IControleur> individu = (pop.get(0)).clone();
		pop.add(individu);
		assertTrue(pop.size() == size + 1);
	}

	/*
	 * on vérifie que la nouvelle population est différente de la précédente
	 */
	public void testEvoluer() {
		Population<IControleur> pop2 = pop.evoluer(env);
		assertTrue(!pop2.equals(pop));
	}

	/*
	 * On vérifie que les individus sont bien rangés dans l'ordre
	 */
	public void testEvaluer() {
		pop.evaluer(env);
		int max = pop.size();
		for (int i = 0; i < max - 1; i++) {
			assertTrue(pop.get(i).getFitness() >= pop.get(i + 1).getFitness());
		}
	}

}
