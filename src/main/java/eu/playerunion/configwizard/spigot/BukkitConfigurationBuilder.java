package eu.playerunion.configwizard.spigot;

import java.io.File;
import java.util.UUID;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.mojang.util.UUIDTypeAdapter;

import eu.playerunion.configwizard.shared.Configuration;
import eu.playerunion.configwizard.shared.impl.ConfigurationBuilder;
import eu.playerunion.configwizard.shared.impl.ConfigurationNodeContainer;
import eu.playerunion.configwizard.spigot.typeadapters.BukkitLocationTypeAdapter;

public class BukkitConfigurationBuilder implements ConfigurationBuilder {
	
	private @Nullable Configuration configuration;
	
	/**
	 * Új Bukkit/Spigot oldali konfiguráció létrehozása.
	 * 
	 * @param directory - A fájl mappája.
	 * @param fileName - A fájl neve.
	 * @param configurationNodeContainer - A beállításokat tartalmazó class.
	 */
	
	public BukkitConfigurationBuilder(@NotNull File directory, @NotNull String fileName, @NotNull ConfigurationNodeContainer configurationNodeContainer) {
		this.configuration = new Configuration(directory, fileName, configurationNodeContainer);
	}
	
	/**
	 * Bukkithoz tartozó TypeAdapterek engedélyezése.
	 * Érdemes alkalmazni, ha UUID-t, Locationt és hasonlóak tervezel tárolni.
	 * 
	 * @return bukkitConfigurationBuilder - Ez az objektum.
	 */
	
	public BukkitConfigurationBuilder enableBukkitTypeAdapters() {
		this.configuration.registerTypeAdapter(Location.class, new BukkitLocationTypeAdapter());
		
		this.configuration.registerTypeAdapter(UUID.class, new UUIDTypeAdapter());
		
		return this;
	}
	
	/**
	 * Konfiguráció létrehozása és lekérése.
	 */

	@Override
	public @Nullable Configuration create() {
		return this.configuration;
	}

}
