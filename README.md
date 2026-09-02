# AcidRain

A small Spigot/Paper plugin for Minecraft that makes rain hurt. While a storm is active, any player standing under open sky takes damage every second, accompanied by a sizzling steam sound.

## How it works

- Listens for `WeatherChangeEvent`; when the weather turns to rain, a repeating task starts (once per second, 20 ticks).
- Each tick, for every online player, the plugin compares the player's Y position against the highest block at their location. A player at or above that height counts as exposed and takes 1 damage plus an `ENTITY_GENERIC_EXTINGUISH_FIRE` sound.
- Players indoors, in caves, or under any block are unaffected.
- The task cancels itself as soon as the storm ends.
- On enable, the plugin sets the world's clear-weather duration to 1200 ticks so rain returns often.

## Requirements

- Java 8+ and Maven
- A Spigot/Paper server on **1.19** (`api-version: 1.19`)

## Build

```bash
mvn clean package
```

Copy the resulting JAR from `target/` into your server's `plugins/` folder and restart.

## Project layout

```
src/main/java/me/minegroup/AcidRain.java   Plugin entry point and weather listener
src/main/resources/plugin.yml              Plugin metadata
```

## Notes

The plugin currently hard-codes the world named `world` when setting the clear-weather duration, and the damage amount and tick interval are constants in the source — there is no config file yet.
