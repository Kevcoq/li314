package test;

import pobj.util.Config;
import junit.framework.TestCase;

public class ConfigTest extends TestCase {
	private Config config;

	protected void setUp() throws Exception {
		super.setUp();
		config = Config.getInstance();
		config.setParameterValue("x", "X");
		config.setParameterValue("y", "Y");
		config.setParameterValue("z", "Z");
	}

	public void testGetParameterValue() {
		assertTrue(config.getParameterValue("y").equals("Y"));
		assertTrue(config.getParameterValue("z").equals("Z"));
		assertTrue(config.getParameterValue("x").equals("X"));
	}

	public void testSetParameterValue() {
		config.setParameterValue("z", "ZZZ");
		assertTrue(config.getParameterValue("z").equals("ZZZ"));
	}

}
