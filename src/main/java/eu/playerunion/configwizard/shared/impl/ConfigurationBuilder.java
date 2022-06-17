package eu.playerunion.configwizard.shared.impl;

import eu.playerunion.configwizard.shared.Configuration;

public interface ConfigurationBuilder {
	
	/**
	 * Egy új konfiguráció létrehozása.
	 * 
	 * @param directory - A későbbi konfigurációs fájlt tartalmazó mappa.
	 * @param configFile - A későbbi konfigurációs fájl.
	 * @return configuration - Egy új konfiguráció.
	 */
	
	Configuration create();

}
