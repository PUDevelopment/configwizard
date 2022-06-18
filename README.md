# configwizard - leírás [![Build Status](https://jenkins.playerunion.eu/job/configwizard/1/badge/icon)](https://jenkins.playerunion.eu/job/configwizard/1/)
Univerzális json alapú konfig kezelő lib az egyéb projektekhez (Spigot, BungeeCord, Sponge, etc.)\
\
**A működése Reflection-ön alapul**, mely során előre deklarált változókat tudsz felhasználni a konfigurációban tárolt értékek lekéréséhez.\
Minden egyes alkalommal, ha új változót adsz hozzá a **Containerhez**, azok alapértékekkel automatikusan mentésre kerülnek a konfigban, amennyiben hiányoznak.\
Ha egy érték már létezik, Reflection segítségével a classban lévő változó automatikusan felülíródik és a konfigban tárolt értéket fogja a továbbiakban reprezentálni.

# Implementáció
Konfigurációs értékeket tartalmazó class
```java
import eu.playerunion.configwizard.shared.ConfigurationNodeType;
import eu.playerunion.configwizard.shared.impl.ConfigurationNode;
import eu.playerunion.configwizard.shared.impl.ConfigurationNodeContainer;

public class TestConfigurationNodeContainer implements ConfigurationNodeContainer {
	
	@ConfigurationNode(path = "test", type = ConfigurationNodeType.INT)
	public Integer TEST_INT = 100;

}
```
Konfigurációt létrehozó, kezelő és tartalmazó class
```java
import java.io.File;

import eu.playerunion.configwizard.shared.Configuration;
import eu.playerunion.configwizard.spigot.BukkitConfigurationBuilder;

TestConfigurationNodeContainer nodeContainer = new TestConfigurationNodeContainer();

Configuration config = new BukkitConfigurationBuilder(new File("mappa"), "config.json", nodeContainer).enableBukkitTypeAdapters().create();

config.initConfig();
		
int value = nodeContainer.TEST_INT;
```

# Példa konfiguráció
A konfigurációban tárolt értékek az alábbi módon fognak kinézni:
```json
{
  "test": 237,
  "test2": "ez egy teszt string",
  "test3": 2.0,
  "test4": "TESZT_ENUM"
}
```

# Felhasznált könyvtárak
- Gson: https://github.com/google/gson
- Spigot: https://hub.spigotmc.org/stash/projects/SPIGOT
- SpongeAPI: https://github.com/SpongePowered/SpongeAPI
```
