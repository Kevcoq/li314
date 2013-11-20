/**
 * 
 */
package test;

import java.io.IOException;

import junit.framework.TestCase;
import pobj.algogen.generic.Individu;
import pobj.algogen.generic.Population;
import pobj.algogen.generic.adapter.agent.LabyEnvironnementAdapter;
import pobj.algogen.generic.adapter.agent.PopulationFactory;
import agent.control.IControleur;
import agent.laby.ChargeurLabyrinthe;
import agent.laby.Labyrinthe;

/**
 * @author sigaud
 * 
 */
public class LabyEnvAdapterTest extends TestCase {
	private LabyEnvironnementAdapter env;
	private Labyrinthe labyTest;
	private Population<IControleur> pop;

	/**
	 * @param arg0
	 */
	public LabyEnvAdapterTest(String arg0) {
		super(arg0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		try {
			labyTest = ChargeurLabyrinthe.chargerLabyrinthe("trial.mze");
		} catch (IOException e) {
			e.printStackTrace();
			fail("Could not find test maze !");
		}
		env = new LabyEnvironnementAdapter(labyTest, 10);
		pop = PopulationFactory.createRandomPopulation(100, 20);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test method for
	 * {@link pobj.algogen.adapter.agent.LabyEnvironnementAdapter#eval(pobj.algogen.Individu)}
	 * .
	 */
	public void testEval() {
		Individu<IControleur> indiv = pop.get(0);
		double retour = env.eval(indiv);
		assert (retour > 0);
	}

}
