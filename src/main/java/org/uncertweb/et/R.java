package org.uncertweb.et;

import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;


public class R {

	private R() {

	}

	public static RConnection getConnection() throws RserveException, ConfigException {
		// connect to r and load library
		Config config = Config.getInstance();
		String host = (String)config.get("rserve", "host");
		Integer port = (Integer)config.get("rserve", "port");
		if (host != null && port != null) {
			RConnection c = new RConnection(host, port);
			c.voidEval("library('sensitivity')");
			c.voidEval("library('Cairo')");
			return c;
		}
		else {
			throw new ConfigException("Couldn't find Rserve details in config.");
		}
	}

}
