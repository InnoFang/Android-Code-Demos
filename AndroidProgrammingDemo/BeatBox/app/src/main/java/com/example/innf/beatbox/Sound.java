package com.example.innf.beatbox;

/**
 * Author: Inno Fang
 * Time: 2016/7/14 19:11
 * Description:
 */

public class Sound {
    private String mAssetPath;
    private String mName;
    private Integer mSoundId;

    public Sound(String assetPath) {
        mAssetPath = assetPath;
        String[] components = assetPath.split("/");
        String filename = components[components.length - 1];
        mName = filename.replace(".wav", "");
    }

    public String getAssetPath() {
        return mAssetPath;
    }

    public String getName() {
        return mName;
    }

    public Integer getSoundId() {
        return mSoundId;
    }

    public void setSoundId(Integer soundId) { // 这里用Integer而不是用int，这样在Sound的mSoundId没有值时可以设置为null
        mSoundId = soundId;
    }
}
