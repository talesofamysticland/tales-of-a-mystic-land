package org.talesof.talesofamysticland.game.main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import org.talesof.talesofamysticland.game.Game;

import java.net.URL;
import java.util.Objects;

public class Sound {

    Clip clip;
    URL[] soundURL = new URL[30];
    public long clipTime;
    FloatControl fc;
    int volumeScale = 3;
    float volume;

    public Sound() {

        soundURL[0] = getSoundURL("BlueBoyAdventure");
        soundURL[1] = getSoundURL("coin");
        soundURL[2] = getSoundURL("powerup");
        soundURL[3] = getSoundURL("unlock");
        soundURL[4] = getSoundURL("fanfare");
        soundURL[5] = getSoundURL("hitmonster");
        soundURL[6] = getSoundURL("receivedamage");
        soundURL[7] = getSoundURL("swingweapon");
        soundURL[8] = getSoundURL("levelup");
        soundURL[9] = getSoundURL("cursor");
        soundURL[10] = getSoundURL("burning");
        soundURL[11] = getSoundURL("cuttree");
        soundURL[12] = getSoundURL("gameover");
        soundURL[13] = getSoundURL("stairs");
        soundURL[14] = getSoundURL("sleep");
        soundURL[15] = getSoundURL("blocked");
        soundURL[16] = getSoundURL("parry");
        soundURL[17] = getSoundURL("speak");
        soundURL[18] = getSoundURL("Merchant");
        soundURL[19] = getSoundURL("Dungeon");
    }

    public URL getSoundURL(String file) {
        return Objects.requireNonNull(getClass().getResource(Game.GAME_ASSETS_PATH + "/sound/" + file + ".wav"));
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

            if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                checkVolume();
            } else {
                System.out.println("MASTER_GAIN control not supported for this audio clip");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clipTime = clip.getMicrosecondPosition();
        clip.stop();
    }

    public void resume() {
        clip.setMicrosecondPosition(clipTime);
        clip.start();
    }

    public void checkVolume() {

        switch(volumeScale) {
            case 0 -> volume = -80f;
            case 1 -> volume = -20f;
            case 2 -> volume = -12f;
            case 3 -> volume = -5f;
            case 4 -> volume = 1f;
            case 5 -> volume = 6f;
        }

        fc.setValue(volume);
    }
}
