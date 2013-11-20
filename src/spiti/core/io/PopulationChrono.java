package spiti.core.io;

import java.io.IOException;

import pobj.algogen.generic.Population;
import pobj.algogen.generic.adapter.arith.EnvironnementExpression;
import pobj.algogen.generic.adapter.arith.PopulationFactory;
import pobj.arith.Expression;
import pobj.util.Config;

public class PopulationChrono {
	public static void main(String[] args) {
		try {
			Config.chargerConfig("config.cfg");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		EnvironnementExpression env = new EnvironnementExpression();
		Population<Expression> pop = null;

		// Une boucle pour avoir un test plus explicite sur 5 essais.
		for (int j = 0; j < 5; j++) {

			// Le chrono
			Chrono chrono = new Chrono();
			// On lance la crï¿½ation de la pop puis l'ï¿½volution
			pop = PopulationFactory.createRandomPopulation(10000);
			for (int i = 0; i < 10; i++)
				pop.evoluer(env);
			chrono.stop();
		}
	}
}

// Score ArrayList : ~60 ms
// Score MyArrayList : ~185 ms
// Score MyArrayList + Générateur : ~250 ms