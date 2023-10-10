package space.yurisi.levelsystem.manager;

import space.yurisi.levelsystem.LevelSystem;
import space.yurisi.levelsystem.command.addexpCommand;

public class CommandManager {

    public CommandManager(LevelSystem main){
        main.getCommand("addexp").setExecutor(new addexpCommand());
    }
}
