package eu.playerunion.configwizard.shared.impl;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;

public interface ConfigurationTypeAdapter<T> extends JsonSerializer<T>, JsonDeserializer<T> {
	
	/**
	 * TypeAdapter classok megkülönböztetésére használt interface.
	 */
	
	/**
	 * Serializolt objektumok típusának meghatározása.
	 * 
	 * @return type - Az objektum típusa.
	 */
	
	Type getType();

}
