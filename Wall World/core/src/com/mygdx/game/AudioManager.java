package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class AudioManager {
    private static AudioManager instance;
    private Music music;

    private AudioManager() {}

    public static AudioManager getInstance() {
        if (instance == null) {
            instance = new AudioManager();
        }
        return instance;
    }

    public void playMusic(String filePath) {
        if (music == null) {
            music = Gdx.audio.newMusic(Gdx.files.internal(filePath));
            music.setLooping(true);
            music.setVolume(0.2f);
            music.play();
        }
    }

    public void stopMusic() {
        if (music != null) {
            music.stop();
        }
    }

    public void dispose() {
        if (music != null) {
            music.dispose();
            music = null;
        }
    }
}
