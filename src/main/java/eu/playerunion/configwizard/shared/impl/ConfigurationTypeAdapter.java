package eu.playerunion.configwizard.shared.impl;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;

public interface ConfigurationTypeAdapter<T> extends JsonSerializer<T>, JsonDeserializer<T> {

}
