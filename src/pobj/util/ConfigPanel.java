package pobj.util;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import pobj.algogen.generic.AlgoGenParameter;

public class ConfigPanel extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**/
	/**/

	private static Config config;
	private Map<JLabel, JTextField> mSwing = new HashMap<JLabel, JTextField>();
	private JPanel panel = new JPanel(new GridLayout(0, 2));
	private static boolean sv;

	public ConfigPanel(Config config) {
		sv = false;
		ConfigPanel.config = config;
		Set<Entry<String, String>> set = config.getMap().entrySet();
		for (Map.Entry<String, String> nUplet : set) {
			mSwing.put(new JLabel(nUplet.getKey()),
					new JTextField(nUplet.getValue()));
		}
		addPanel();
	}

	public void addPanel() {
		for (Entry<JLabel, JTextField> nUplet : mSwing.entrySet()) {
			panel.add(nUplet.getKey());
			panel.add(nUplet.getValue());
		}
		JButton button = new JButton("Sauvegarder");
		button.addActionListener(this);
		panel.add(button);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		sauvegarder();
	}

	private void sauvegarder() {
		sv = true;
		for (Entry<JLabel, JTextField> nUplet : mSwing.entrySet())
			config.getMap().put(nUplet.getKey().getText(),
					nUplet.getValue().getText());
	}

	private JPanel getPanel() {
		return panel;
	}

	/**/
	/**/
	/**/
	/**/
	/**/
	/**/
	/**/
	/**/
	public static void main(String[] args) {
		try {
			// Chargement de la config
			ConfigPanel cp;
			cp = new ConfigPanel(Config.chargerConfig("config.cfg"));

			// Gestion fênetre
			JFrame frame = new JFrame("Config");
			frame.add(cp.getPanel());
			frame.pack();
			frame.setVisible(true);
			frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			while (frame.isVisible())
				Thread.sleep(500);
			if (sv) {
				// Sauvegarde
				Config.sauverConfig(
						config.getParameterValue(AlgoGenParameter.NOM_CONFIG),
						config);
				System.out.println("Sauvegarde réussi.");
			}

		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
