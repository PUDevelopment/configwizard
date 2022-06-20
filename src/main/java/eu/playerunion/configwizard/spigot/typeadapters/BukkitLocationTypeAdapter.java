package eu.playerunion.configwizard.spigot.typeadapters;

import java.lang.reflect.Type;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;

import eu.playerunion.configwizard.shared.impl.ConfigurationTypeAdapter;

public class BukkitLocationTypeAdapter implements ConfigurationTypeAdapter<Location> {

	@Override
	public Location deserialize(@NotNull JsonElement json, @NotNull Type typeOfT,
			@NotNull JsonDeserializationContext context) throws JsonParseException {

		JsonObject obj = context.deserialize(json, JsonObject.class);
		String world = obj.get("world").getAsString();
		double x = obj.get("x").getAsDouble();
		double y = obj.get("y").getAsDouble();
		double z = obj.get("z").getAsDouble();
		float pitch = obj.get("pitch").getAsFloat();
		float yaw = obj.get("yaw").getAsFloat();

		return new Location(Bukkit.getWorld(world), x, y, z, pitch, yaw);
	}

	@Override
	public JsonElement serialize(@NotNull Location src, @NotNull Type typeOfSrc,
			@NotNull JsonSerializationContext context) {
		Validate.notNull(src, "A feldolgozandó Location nem lehet null!");

		JsonObject obj = new JsonObject();

		obj.addProperty("world", src.getWorld().getName());
		obj.addProperty("x", src.getX());
		obj.addProperty("y", src.getY());
		obj.addProperty("z", src.getZ());
		obj.addProperty("pitch", src.getPitch());
		obj.addProperty("yaw", src.getYaw());

		return obj;
	}

}