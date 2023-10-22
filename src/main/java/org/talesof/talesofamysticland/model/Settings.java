package org.talesof.talesofamysticland.model;

import java.time.LocalDateTime;

public class Settings {

    private Integer id;
    private Integer playerId;
    private Float volumeEffects;
    private Float volumeMusic;
    private Float volumeGeral;
    private boolean fullScreen;
    private String resolution;
    private LocalDateTime saveDate;

    public Settings(Integer id, Integer playerId, Float volumeEffects, Float volumeMusic, Float volumeGeral,
            boolean fullScreen, String resolution, LocalDateTime saveDate) {
        this.id = id;
        this.playerId = playerId;
        this.volumeEffects = volumeEffects;
        this.volumeMusic = volumeMusic;
        this.volumeGeral = volumeGeral;
        this.fullScreen = fullScreen;
        this.resolution = resolution;
        this.saveDate = saveDate;
    }

    public Settings(Integer playerId, Float volumeEffects, Float volumeMusic, Float volumeGeral,
            boolean fullScreen, String resolution, LocalDateTime saveDate) {

        this(null, playerId, volumeEffects, volumeMusic, volumeGeral, fullScreen, resolution, saveDate);
    }

    public Settings(Integer id, Integer playerId, Float volumeEffects, Float volumeMusic, Float volumeGeral,
            boolean fullScreen, String resolution) {

        this(id, playerId, volumeEffects, volumeMusic, volumeGeral, fullScreen, resolution, LocalDateTime.now());
    }

    public Settings(Integer playerId, Float volumeEffects, Float volumeMusic, Float volumeGeral,
            boolean fullScreen, String resolution) {

        this(playerId, volumeEffects, volumeMusic, volumeGeral, fullScreen, resolution, LocalDateTime.now());
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public Float getVolumeEffects() {
        return volumeEffects;
    }

    public void setVolumeEffects(Float volumeEffects) {
        this.volumeEffects = volumeEffects;
    }

    public Float getVolumeMusic() {
        return volumeMusic;
    }

    public void setVolumeMusic(Float volumeMusic) {
        this.volumeMusic = volumeMusic;
    }

    public Float getVolumeGeral() {
        return volumeGeral;
    }

    public void setVolumeGeral(Float volumeGeral) {
        this.volumeGeral = volumeGeral;
    }

    public boolean isFullScreen() {
        return fullScreen;
    }

    public void setFullScreen(boolean fullScreen) {
        this.fullScreen = fullScreen;
    }
    
    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public LocalDateTime getSaveDate() {
        return saveDate;
    }

    public void setSaveDate(LocalDateTime saveDate) {
        this.saveDate = saveDate;
    }

    @Override
    public String toString() {
        return "Settings [id=" + id + ", playerId=" + playerId + ", volumeEffects=" + volumeEffects + ", volumeMusic="
                + volumeMusic + ", volumeGeral=" + volumeGeral + ", fullScreen=" + fullScreen + ", resolution="
                + resolution + ", saveDate=" + saveDate + "]\n";
    }
}