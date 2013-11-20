package pobj.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import pobj.algogen.generic.AlgoGenParameter;

public final class Config implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Config instance;
	private Map<String, String> config;

	private Config() {
		// TODO Auto-generated constructor stub
		config = new HashMap<>();
	}

	public static Config getInstance() {
		if (instance == null)
			instance = new Config();
		return instance;
	}

	public String getParameterValue(String param) {
		return config.get(param);
	}

	public void setParameterValue(String param, String value) {
		config.put(param, value);
	}

	Map<String, String> getMap() {
		return config;
	}

	/**/
	/**/
	/**/
	/**/
	/**/
	/**
	 * Import du labyrinthe sauvé par la sérialisation.
	 * 
	 * @param fileName
	 *            : nom du fichier sauvé par la sérialisation
	 * @throws IOException
	 *             problème de lecture du fichier ou de son contenu
	 */
	public static Config chargerConfig(String fileName) throws IOException {
		if (instance == null) {
			FileInputStream fis = new FileInputStream(fileName);
			@SuppressWarnings("resource")
			ObjectInputStream ois = new ObjectInputStream(fis);
			try {
				instance = (Config) ois.readObject();
				return instance;
			} catch (ClassCastException e) {
				throw new IOException(
						"Le fichier ne contient pas un Labyrinthe.", e);
			} catch (ClassNotFoundException e) {
				throw new IOException("JVM does not know the type Laby.", e);
			}
		}
		return instance;
		// FileInputStream fis = new FileInputStream(fileName);
		// @SuppressWarnings("resource")
		// ObjectInputStream ois = new ObjectInputStream(fis);
		// try {
		// Config config = (Config) ois.readObject();
		// return config;
		// } catch (ClassCastException e) {
		// throw new IOException("Le fichier ne contient pas un Labyrinthe.",
		// e);
		// } catch (ClassNotFoundException e) {
		// throw new IOException("JVM does not know the type Laby.", e);
		// }
	}

	/**
	 * Export du labyrinthe par la sérialisation
	 * 
	 * @param le
	 *            nom du fichier à charger (en convention {@link File})
	 * @throws IOException
	 *             En cas de problème d'écriture dans ce fichier.
	 */
	public static void sauverConfig(String fileName, Config config)
			throws IOException {
		FileOutputStream fos = new FileOutputStream(fileName);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(config);
		fos.close();

	}

	public static void main(String[] args) {
		try {
			Config config = new Config();
			config.setParameterValue(AlgoGenParameter.TYPE_IND, "Agent");
			config.setParameterValue(AlgoGenParameter.TAILLE_POP, "1000");
			config.setParameterValue(AlgoGenParameter.GRAINE, "916171");
			config.setParameterValue(AlgoGenParameter.LABYRINTHE, "goal.mze");
			config.setParameterValue(AlgoGenParameter.NB_STEPS, "50");
			config.setParameterValue(AlgoGenParameter.NB_IND, "100");
			config.setParameterValue(AlgoGenParameter.NB_EVO, "10");
			config.setParameterValue(AlgoGenParameter.NB_RULES, "20");
			config.setParameterValue(AlgoGenParameter.NB_PAS, "100");
			config.setParameterValue(AlgoGenParameter.NOM_CONFIG, "config.cfg");
			config.setParameterValue(AlgoGenParameter.EVO, "true");
			config.setParameterValue(AlgoGenParameter.SELECTION, "true");
			sauverConfig("config.cfg", config);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
