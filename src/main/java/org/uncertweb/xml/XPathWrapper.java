package org.uncertweb.xml;
import java.lang.reflect.Field;

import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.xpath.XPath;

public class XPathWrapper {

	public static XPath newInstance(String xpath) throws JDOMException {
		XPath x = XPath.newInstance(xpath);

		// fill with namespaces
		Field[] fields = Namespaces.class.getFields();
		for (Field field : fields) {
			try {
				Namespace namespace = (Namespace) field.get(null);
				x.addNamespace(namespace);
			}
			catch (IllegalAccessException e) {
				// ignore
			}
		}
		
		return x;
	}

}