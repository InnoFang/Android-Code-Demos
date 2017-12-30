package com.example.innf.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Inno Fang
 * Time: 2016/7/14 18:36
 * Description:
 */

public class BeatBox {
    private static final String TAG = "BeatBox";

    private static final String SOUNDS_FOLDER = "sample_sounds";
    private static final int MAX_SOUNDS = 5;

    private AssetManager mAssets;
    private List<Sound> mSounds = new ArrayList<>();
    private SoundPool mSoundPool;


    public BeatBox(Context context) {
        //访问Assets需要AssetManager类，可以从Context中获取
        mAssets = context.getAssets();
        // This old constructor is deprecated, but we need it for
        // compatibility
        mSoundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0);
        loadSounds();
    }

    public void play(Sound sound) {
        Integer soundId = sound.getSoundId();
        if (soundId == null) {//确保soundId不会是null值，Sound加载失败会出现null值
            return;
        }
        mSoundPool.play(soundId, 1.0f,   1.0f,         1,       0,        1.0f);
            //参数顺序 音频ID，   左音量   右音量  优先级(无效)  是否循环   播放速率
    }

    public void release() {
        mSoundPool.release();
    }

    private void loadSounds() {
        String[] soundNames;
        try {
            soundNames = mAssets.list(SOUNDS_FOLDER); // 列出目录中的所有文件名
            Log.i(TAG, "Found " + soundNames.length + " sounds");
        } catch (IOException e) {
            Log.e(TAG, "Could not list assets", e);
            return;
        }

        for (String filename : soundNames) {
            try{
                String assetPath = SOUNDS_FOLDER + "/" + filename;
                Sound sound = new Sound(assetPath);
                load(sound);
                mSounds.add(sound);
            }catch (IOException e){
                Log.e(TAG, "Could not load sound " + filename, e);
            }
        }
    }

    public List<Sound> getSound() {
        return mSounds;
    }

    private void load(Sound sound) throws IOException{
        AssetFileDescriptor afd = mAssets.openFd(sound.getAssetPath());
        int soundId = mSoundPool.load(afd, 1);
        sound.setSoundId(soundId);
    }
}
