package me.Control;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.HashMap;

public class Commands implements CommandExecutor, Listener {

    private HashMap<CommandSender, GeneratorInfo> answering = new HashMap<CommandSender, GeneratorInfo>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if(strings.length <= 0) {
            sender.sendMessage(Home.prefix.replace("%name%","WorldSwitch") + "§cDu musst angeben, in welche Welt du reisen möchtest!");
            return true;
        }
        switch(strings[0].toLowerCase()) {
            case "create":
            case "generate":
            case "new":
                if(!sender.isOp()) {
                    sender.sendMessage(Home.prefix.replace("%name%","WorldSwitch") + "§cDu bist nicht berechtigt, diesen Befehl auszuführen!");
                    return true;
                }
                if(strings.length < 2) {
                    sender.sendMessage(Home.prefix.replace("%name%","WorldControl") + "§cDu musst angeben, wie die neue Welt heißen soll!");
                    return true;
                }
                questionNewWorld(sender, strings[1], 0, true);
                break;
        }
        return true;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent ev) {

        if(!answering.containsKey(ev.getPlayer())) return;


    }

    private void questionNewWorld(CommandSender sender, String answer, int stage, boolean create) {

        boolean answered = answer != null;

        if(create) answering.put(sender, new GeneratorInfo(answer));

        if(!answered)
        switch(stage) {
            case 0:
                sender.sendMessage(Home.prefix.replace("%name%","WorldControl") + "§aWo soll die Welt generiert werden?");
                sender.sendMessage(Home.prefix.replace("%name%","WorldControl") + "§bNormal, Nether, End");
                break;
            case 1:
                sender.sendMessage(Home.prefix.replace("%name%","WorldControl") + "§aWelche Weltart soll generiert werden?");
                sender.sendMessage(Home.prefix.replace("%name%","WorldControl") + "§bNormal, Flat, Amplified, Large (Biomes)");
                break;
            case 2:
                sender.sendMessage(Home.prefix.replace("%name%","WorldControl") + "§aWelchen Seed soll die Welt haben?");
                sender.sendMessage(Home.prefix.replace("%name%","WorldControl") + "§bRandom, [Seed]");
                break;
            case 3:
                sender.sendMessage(Home.prefix.replace("%name%","WorldControl") + "§aStrukturen sollen generiert werden?");
                sender.sendMessage(Home.prefix.replace("%name%","WorldControl") + "§bTrue, False");
                break;
            default:
                sender.sendMessage(Home.prefix.replace("%name%","WorldControl") + "§aDie Welt wird generiert...");
                sender.sendMessage(Home.prefix.replace("%name%","WorldControl") + "§6");
        }

        if(answered) {
            questionNewWorld(sender, null, stage+1, false);
            return;
        } else answering.put(sender, answering.get(sender).setStage(stage));
    }

}
