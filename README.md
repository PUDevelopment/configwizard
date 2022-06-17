# configwizard
Univerzális json alapú konfig kezelő lib az egyéb projektekhez (Spigot, BungeeCord, Sponge, etc.)

# Implementáció
```java
import eu.playerunion.configwizard.shared.ConfigurationNodeType;
import eu.playerunion.configwizard.shared.impl.ConfigurationNode;
import eu.playerunion.configwizard.shared.impl.ConfigurationNodeContainer;

public class TestConfigurationNodeContainer implements ConfigurationNodeContainer {
	
	@ConfigurationNode(path = "test", type = ConfigurationNodeType.INT)
	public Integer TEST_INT = 100;

}

import java.io.File;

import eu.playerunion.configwizard.shared.Configuration;
import eu.playerunion.configwizard.spigot.BukkitConfigurationBuilder;

TestConfigurationNodeContainer nodeContainer = new TestConfigurationNodeContainer();

Configuration config = new BukkitConfigurationBuilder(new File("mappa"), "config.json", nodeContainer).enableBukkitTypeAdapters().create();

config.initConfig();
		
int value = nodeContainer.TEST_INT;
```
