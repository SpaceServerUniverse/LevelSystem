package space.yurisi.levelsystem.utils.levelmode;

public interface ILevelMode {

    String getName();

    int getMaxLevel();

    Long getLevelTable(int level);
}
