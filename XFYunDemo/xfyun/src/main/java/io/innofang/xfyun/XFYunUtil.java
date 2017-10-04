package io.innofang.xfyun;

import android.content.Context;
import android.os.Bundle;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;

/**
 * Author: Inno Fang
 * Time: 2017/10/3 16:37
 * Description:
 */


public class XFYunUtil {

    private XFYunUtil() {
    }

    public static Builder build(Context context) {
        return Builder.get(context);
    }

    public static class Builder {

        private static Builder instance;

        private static Builder get(Context context) {
            if (null == instance) {
                instance = new Builder(context);
            }
            return instance;
        }

        private SpeechSynthesizer mTts;

        private String mVoiceName = "xiaoyan";
        private String mSpeed = "50";
        private String mVolume = "80";
        private String mEngineType = SpeechConstant.TYPE_CLOUD;

        public Builder(Context context) {
            mTts = SpeechSynthesizer.createSynthesizer(context, null);
        }

        public Builder setVoiceName(String voiceName) {
            mVoiceName = voiceName;
            return this;
        }

        public Builder setSpeed(String speed) {
            mSpeed = speed;
            return this;
        }

        public Builder setVolume(String volume) {
            mVolume = volume;
            return this;
        }

        public Builder setEngineType(String engineType) {
            mEngineType = engineType;
            return this;
        }

        public Builder setSynthesizerListener(SynthesizerListener synListener) {
            mSynListener = synListener;
            return this;
        }

        public void speak(String text) {
            mTts.setParameter(SpeechConstant.VOICE_NAME, mVoiceName); // 设置发音人
            mTts.setParameter(SpeechConstant.SPEED, mSpeed);// 设置语速
            mTts.setParameter(SpeechConstant.VOLUME, mVolume);// 设置音量，范围 0~100
            mTts.setParameter(SpeechConstant.ENGINE_TYPE, mEngineType);// 设置云端
            mTts.startSpeaking(text, mSynListener);
        }

        //合成监听器
        private SynthesizerListener mSynListener = new SynthesizerListener() {
            //会话结束回调接口，没有错误时，error为null
            public void onCompleted(SpeechError error) {
            }

            //缓冲进度回调
            //percent为缓冲进度0~100，beginPos为缓冲音频在文本中开始位置，endPos表示缓冲音频在
            //文本中结束位置，info为附加信息。
            public void onBufferProgress(int percent, int beginPos, int endPos, String info) {
            }

            //开始播放
            public void onSpeakBegin() {
            }

            //暂停播放
            public void onSpeakPaused() {
            }

            //播放进度回调
            //percent为播放进度0~100,beginPos为播放音频在文本中开始位置，endPos表示播放音频在文
            //本中结束位置.
            public void onSpeakProgress(int percent, int beginPos, int endPos) {
            }

            //恢复播放回调接口
            public void onSpeakResumed() {
            }

            //会话事件回调接口
            public void onEvent(int arg0, int arg1, int arg2, Bundle arg3) {
            }
        };

    }

}
