package eu.playerunion.configwizard.spigot;

import java.io.File;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import eu.playerunion.configwizard.shared.Configuration;
import eu.playerunion.configwizard.shared.impl.ConfigurationBuilder;
import eu.playerunion.configwizard.shared.impl.ConfigurationNodeContainer;
import eu.playerunion.configwizard.spigot.typeadapters.BukkitLocationTypeAdapter;

public class BukkitConfigurationBuilder implements ConfigurationBuilder {
	
	private @Nullable Configuration configuration;
	
	public BukkitConfigurationBuilder(@NotNull File directory, @NotNull String fileName, @NotNull ConfigurationNodeContainer configurationNodeContainer) {
		this.configuration = new Configuration(directory, fileName, configurationNodeContainer);
	}
	
	public BukkitConfigurationBuilder enableBukkitTypeAdapters() {
		this.configuration.registerTypeAdapter(new BukkitLocationTypeAdapter());
		
		return this;
	}

	@Override
	public @Nullable Configuration create() {
		return this.configuration;
	}

}
