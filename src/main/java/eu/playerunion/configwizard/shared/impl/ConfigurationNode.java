package eu.playerunion.configwizard.shared.impl;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import eu.playerunion.configwizard.shared.ConfigurationNodeType;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfigurationNode {
	
	/**
	 * A konfigurációban reprezentált érték útvonala.
	 * 
	 * @return path - Útvonal (pl.: "tick_speed")
	 */
	
	String path();
	
	/**
	 * A reprezentált érték típusa.
	 * 
	 * @return type - Az érték típusa.
	 */
	
	ConfigurationNodeType type();

}
