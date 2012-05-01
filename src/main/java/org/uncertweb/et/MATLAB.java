package org.uncertweb.et;

import java.io.IOException;

import org.uncertweb.matlab.MLException;
import org.uncertweb.matlab.MLHandler;
import org.uncertweb.matlab.MLRequest;
import org.uncertweb.matlab.MLResult;

/**
 * Helper class to make it easier to switch between different MATLAB servers.
 * 
 * @author Richard Jones
 */
public class MATLAB {
	
	private static MLHandler handler;
	
	private MATLAB() {
		
	}

	public static MLResult sendRequest(MLRequest request) throws MLException, ConfigException, IOException {
		if (handler == null) {
			handler = new MLHandler();
		}
		Config config = Config.getInstance();
		String proxy = (String)config.get("matlab", "proxy");
		MLResult result;
		if (proxy != null) {
			result = handler.sendRequest(proxy, request);
		}
		else {
			String host = (String)config.get("matlab", "host");
			Integer port = (Integer)config.get("matlab", "port");
			if (host != null && port != null) {
				result = handler.sendRequest(host, port, request);
			}
			else {
				// TODO: could load some defaults here?
				throw new ConfigException("Couldn't find remote MATLAB details in config.");
			}
		}
		return result;
	}
	
}
