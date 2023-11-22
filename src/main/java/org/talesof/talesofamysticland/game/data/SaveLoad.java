package org.talesof.talesofamysticland.game.data;

import org.talesof.talesofamysticland.game.main.GamePanel;

import java.io.*;

public class SaveLoad {

    GamePanel gp;

    private static final long serialVersionUID = 9047733080742134121L;

    public SaveLoad(GamePanel gp) {
        this.gp = gp;
    }

    public void save() {

        try {
            ObjectOutputStream oos =
                    new ObjectOutputStream(
                        new FileOutputStream(
                                "save.dat"
                        )
                    );

            DataStorage ds = new DataStorage();

            // Object on map
            ds.mapObjectNames = new String[gp.maxMap][gp.obj[1].length];
            ds.mapObjectWorldX = new int[gp.maxMap][gp.obj[1].length];
            ds.mapObjectWorldY = new int[gp.maxMap][gp.obj[1].length];
            ds.mapObjectLootNames = new String[gp.maxMap][gp.obj[1].length];
            ds.mapObjectOpened = new boolean[gp.maxMap][gp.obj[1].length];

            for(int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
                for(int i = 0; i < gp.obj[1].length; i++) {

                    if(gp.obj[mapNum][i] == null) {
                        ds.mapObjectNames[mapNum][i] = "NA";
                    }
                    else {
                        ds.mapObjectNames[mapNum][i] = gp.obj[mapNum][i].name;
                        ds.mapObjectWorldX[mapNum][i] = gp.obj[mapNum][i].worldX;
                        ds.mapObjectWorldY[mapNum][i] = gp.obj[mapNum][i].worldY;

                        if(gp.obj[mapNum][i].loot != null) {
                            ds.mapObjectLootNames[mapNum][i] = gp.obj[mapNum][i].loot.name;
                        }

                        ds.mapObjectOpened[mapNum][i] = gp.obj[mapNum][i].opened;
                    }
                }
            }

            oos.writeObject(ds);

            oos.close();

        } catch(IOException e) {
            e.printStackTrace();
        }

    }

    public void load() {

        try {
            ObjectInputStream ois =
                    new ObjectInputStream(
                            new FileInputStream(
                                    "save.dat"
                            )
                    );

            DataStorage ds = (DataStorage) ois.readObject();

            // Objects on map
            for(int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
                for(int i = 0; i < gp.obj[1].length; i++) {

                    if(ds.mapObjectNames[mapNum][i].equals("NA")) {
                        gp.obj[mapNum][i] = null;
                    }
                    else {
                        gp.obj[mapNum][i] = gp.eGenerator.getObject(ds.mapObjectNames[mapNum][i]);
                        gp.obj[mapNum][i].worldX = ds.mapObjectWorldX[mapNum][i];
                        gp.obj[mapNum][i].worldY = ds.mapObjectWorldY[mapNum][i];

                        if(ds.mapObjectLootNames[mapNum][i] != null) {
                            gp.obj[mapNum][i].loot = gp.eGenerator.getObject(ds.mapObjectLootNames[mapNum][i]);
                        }

                        gp.obj[mapNum][i].opened = ds.mapObjectOpened[mapNum][i];
                        if(gp.obj[mapNum][i].opened) {
                            gp.obj[mapNum][i].down1 =  gp.obj[mapNum][i].image2;
                        }
                    }
                }
            }

            ois.close();

        } catch(IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
