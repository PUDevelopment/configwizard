package eu.playerunion.configwizard.shared;

import java.lang.reflect.Type;

public enum ConfigurationNodeType {
	
	STRING(String.class),
	INT(Integer.class),
	DOUBLE(Double.class),
	FLOAT(Float.class),
	SHORT(Short.class),
	LONG(Long.class);
	
	public Type type;
	
	private ConfigurationNodeType(Type type) {
		this.type = type;
	}

}
