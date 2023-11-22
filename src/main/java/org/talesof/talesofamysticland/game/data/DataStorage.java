package org.talesof.talesofamysticland.game.data;

import java.io.Serializable;

public class DataStorage implements Serializable {

    // Object on Map
    String[][] mapObjectNames;
    int[][] mapObjectWorldX;
    int[][] mapObjectWorldY;
    String[][] mapObjectLootNames;
    boolean[][] mapObjectOpened;
}
