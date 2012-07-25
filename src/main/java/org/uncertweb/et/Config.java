package org.uncertweb.et;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.ho.yaml.Yaml;
import org.ho.yaml.exception.YamlException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Limited to one level in yaml.
 * 
 * @author r_jones
 *
 */
public class Config {

	private Map<Object, Object> map;
	private static Config instance;
	private static final Logger logger = LoggerFactory.getLogger(Config.class);

	public Config() {
		map = new HashMap<Object, Object>();
		String configName = "et.yml";
		InputStream stream = this.getClass().getClassLoader().getResourceAsStream(configName);
		Object o;
		try {
			if (stream != null) {
				o = Yaml.load(stream);
			}
			else {

				try {
					o = Yaml.load(new File("src/main/resources", configName));
				}
				catch (FileNotFoundException e1) {
					try {
						o = Yaml.load(new File(configName));
					}
					catch (FileNotFoundException e2) {
						logger.info("Couldn't find config file, functionality will be limited.");
						o = null;
					}
				}
			}
		}
		catch (YamlException e2) {
			logger.error("YAML parsing error when loading config file.", e2);
			o = null;
		}
		if (o != null) {
			map.putAll((Map<?, ?>)o);
		}
	}

	public static Config getInstance() {
		if (instance == null) {
			instance = new Config();
		}
		return instance;
	}

	public Object get(String key, String subkey) {
		Map<?, ?> submap = (Map<?, ?>)map.get(key);
		if (submap != null) {
			Object value = submap.get(subkey);
			if (value instanceof String) {
				Pattern pattern = Pattern.compile("\\$\\{([a-zA-Z\\d.]+)\\}");
				Matcher matcher = pattern.matcher((String)value);
				StringBuffer sb = new StringBuffer();
				while (matcher.find()) {
					matcher.appendReplacement(sb, Matcher.quoteReplacement(System.getProperty(matcher.group(1))));
				}
				matcher.appendTail(sb);
				return sb.toString();
			}
			else {
				return submap.get(subkey);
			}
		}
		return null;
	}

}
