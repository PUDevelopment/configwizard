package eu.playerunion.configwizard.shared;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashSet;

import org.apache.commons.lang3.Validate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;

import eu.playerunion.configwizard.shared.impl.ConfigurationNode;
import eu.playerunion.configwizard.shared.impl.ConfigurationNodeContainer;
import eu.playerunion.configwizard.shared.impl.ConfigurationTypeAdapter;

public class Configuration {

	private @NotNull File directory;
	private @NotNull File configFile;

	private @Nullable ConfigurationNodeContainer configurationNodeContainer;

	private @NotNull GsonBuilder gsonBuilder;
	private @NotNull Gson gson;
	private @NotNull JsonObject configurationHolder;

	public Configuration(@NotNull File directory, @NotNull String fileName,
			@NotNull ConfigurationNodeContainer configurationNodeContainer) {
		Validate.notNull(directory, "A fájlt tartalmazó mappa nem lehet null!");
		Validate.notNull(configFile, "A fájl nem lehet null!");
		Validate.notNull(configurationNodeContainer, "A node container nem lehet null!");

		this.directory = directory;
		this.configFile = new File(this.directory, fileName);

		this.configurationNodeContainer = configurationNodeContainer;

		this.gsonBuilder = new GsonBuilder().setPrettyPrinting().serializeNulls().enableComplexMapKeySerialization();
		this.gson = this.gsonBuilder.create();
		this.configurationHolder = new JsonObject();
	}

	/**
	 * Konfiguráció inicializálása.
	 * 
	 * @throws IOException
	 */

	public void initConfig() throws IOException {
		if (!this.directory.exists() || !this.configFile.exists())
			this.saveConfig();

		StringBuilder readData = new StringBuilder("");

		Files.lines(this.configFile.toPath()).forEach(line -> {
			readData.append(line);
		});

		this.configurationHolder = this.gson.fromJson(readData.toString(), JsonObject.class);

		this.getAnnotatedFields().forEach(field -> {
			try {
				field.setAccessible(true);

				ConfigurationNode node = field.getAnnotation(ConfigurationNode.class);
				ConfigurationNodeType type = node.type();
				String path = node.path();

				field.set(this.configurationNodeContainer,
						this.gson.fromJson(this.configurationHolder.get(path), type.type));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}

		});
	}

	/**
	 * A konfiguráció mentése fájlba.
	 * 
	 * @throws IOException
	 */

	public void saveConfig() throws IOException {
		if (!this.directory.exists())
			this.directory.mkdirs();

		if (!this.configFile.exists())
			this.configFile.createNewFile();

		this.getAnnotatedFields().forEach(field -> {
			try {
				field.setAccessible(true);

				ConfigurationNode node = field.getAnnotation(ConfigurationNode.class);
				String path = node.path();
				ConfigurationNodeType type = node.type();
				Object value = field.get(this.configurationNodeContainer);

				this.configurationHolder.add(path, this.gson.toJsonTree(value, type.type));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}

		});

		FileWriter writer = new FileWriter(this.configFile);

		writer.write(this.gson.toJson(this.configurationHolder));

		writer.close();
	}

	/**
	 * Összes @ConfigurationNode annotationnel ellátott mező lekérése.
	 * 
	 * @return fields - Mezőket tartalmazó HashSet.
	 */

	private @NotNull HashSet<Field> getAnnotatedFields() {
		HashSet<Field> fields = new HashSet<Field>();

		Arrays.stream(this.configurationNodeContainer.getClass().getDeclaredFields()).forEach(field -> {
			if (field.isAnnotationPresent(ConfigurationNode.class))
				fields.add(field);
		});

		return fields;
	}

	/**
	 * TypeAdapter beregisztrálása az objektumok könnyebb formázása érdekében.
	 * 
	 * @param type - A TypeAdater típusa.
	 * @param typeAdapter - Beregisztrálni kívánt TypeAdapter.
	 */

	public void registerTypeAdapter(@NotNull Class<?> type, @NotNull ConfigurationTypeAdapter<?> typeAdapter) {
		Validate.notNull(type, "A beregisztrálni kívánt TypAdapter típusa nem lehet null!");
		Validate.notNull(typeAdapter, "A beregisztrálni kívánt TypeAdapter nem lehet null!");

		this.gson = this.gsonBuilder.registerTypeAdapter(type, typeAdapter).create();
	}
	
	/**
	 * TypeAdapter beregisztrálása az objektumok könnyebb formázása érdekében.
	 * 
	 * @param type - A TypeAdapter típusa.
	 * @param typeAdapter - Beregisztrálni kívánt TypeAdapter.
	 */

	public void registerTypeAdapter(@NotNull Class<?> type, @NotNull TypeAdapter<?> typeAdapter) {
		Validate.notNull(type, "A beregisztrálni kívánt TypAdapter típusa nem lehet null!");
		Validate.notNull(typeAdapter, "A beregisztrálni kívánt TypeAdapter nem lehet null!");

		this.gson = this.gsonBuilder.registerTypeAdapter(type, typeAdapter).create();
	}

}
