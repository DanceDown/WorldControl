package me.Control;

import org.bukkit.World;
import org.bukkit.WorldType;

public class GeneratorInfo {

    private int seed;
    private int stage;
    private String name;
    private WorldType type;
    private World.Environment env;

    public GeneratorInfo(String name) {
        this.name = name;
    }

    public GeneratorInfo setStage(int value) {
        stage = value;
        return this;
    }

    public int getStage() {
        return stage;
    }
}
