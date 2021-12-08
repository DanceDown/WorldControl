package me.Control;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Home extends JavaPlugin {

    public static String prefix = "§f[§b%name%§f] ";

    @Override
    public void onEnable() {

        getCommand("world").setExecutor(new Commands());

        Bukkit.getConsoleSender().sendMessage(prefix.replace("%name%", "WorldControl"));

    }
}
