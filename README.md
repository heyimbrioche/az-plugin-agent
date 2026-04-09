# AZPlugin (Bukkit)

Plugin serveur Minecraft (Spigot 1.8 / 1.9) pour avoir les items specifique au **AZ Client**.

```bash
git clone https://github.com/heyimbrioche/az-plugin-bukkit.git
```

## Build

À la racine du projet :

```bash
mvn -DskipTests package
```

Le plugin final (et JAR agent) se trouve dans `bukkit/plugin/target/` sous le nom `az-plugin-bukkit.jar`.

## Démarrage : agent obligatoire

Le plugin **ne charge pas** sans l’agent Java : il doit être passé à la JVM **avant** `-jar` (ou avant la classe principale du serveur).

1. Copie `az-plugin-bukkit.jar` dans le dossier `plugins/` du serveur (comme un plugin classique).
2. Ajoute l’option JVM `-javaagent` en pointant vers **le même fichier** (chemin absolu recommandé).

**Exemple (Linux / macOS)** — adapte les chemins :

```bash
java -javaagent:/home/user/serveur/plugins/az-plugin-bukkit.jar -Xms2G -Xmx2G -jar spigot.jar nogui
```

**Exemple (Windows, PowerShell)** :

```powershell
java "-javaagent:C:\serveur\plugins\az-plugin-bukkit.jar" -Xms2G -Xmx2G -jar spigot.jar nogui
```

Si tu utilises un script `start.sh` / panel d’hébergeur, ajoute la ligne `-javaagent:...` dans les **arguments JVM** (pas seulement après le `.jar` du serveur).

## BungeeCord (optionnel)

Un module patch séparé existe dans `bungee/patch` ; il a son propre `Premain-Class` dans son JAR. Même principe : `-javaagent:chemin/vers/le-jar-bungee-patch.jar` sur la commande qui lance le proxy.

## Remerciements

Merci à **nathan818** pour le travail et les informations publiées sur le dépôt officiel d’exemple [**az-examples/az-plugin**](https://github.com/az-examples/az-plugin.git), qui ont servi de base et de référence pour ce projet.
