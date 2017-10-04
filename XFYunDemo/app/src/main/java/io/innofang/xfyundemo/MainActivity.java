package io.innofang.xfyundemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SynthesizerListener;

import io.innofang.xfyun.XFYunUtil;

public class MainActivity extends AppCompatActivity {

    private EditText mInputEditText;
    private Button mSpeakButton;
//    private SpeechSynthesizer mTts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInputEditText = (EditText) findViewById(R.id.input_edit_text);
        mSpeakButton = (Button) findViewById(R.id.speak_button);

//        mTts = SpeechSynthesizer.createSynthesizer(this, null);
        // 合成参数设置
//        mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan"); // 设置发音人
//        mTts.setParameter(SpeechConstant.SPEED, "50");// 设置语速
//        mTts.setParameter(SpeechConstant.VOLUME, "80");// 设置音量，范围 0~100
//        mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);     // 设置云端

        mSpeakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = mInputEditText.getText().toString();
//                mTts.startSpeaking(text, mSynListener);
                XFYunUtil.build(MainActivity.this).setSpeed("20").speak(text);
            }
        });
    }

    //合成监听器
    private SynthesizerListener mSynListener = new SynthesizerListener(){
        //会话结束回调接口，没有错误时，error为null
        public void onCompleted(SpeechError error) {}
        //缓冲进度回调
        //percent为缓冲进度0~100，beginPos为缓冲音频在文本中开始位置，endPos表示缓冲音频在
        //文本中结束位置，info为附加信息。
        public void onBufferProgress(int percent, int beginPos, int endPos, String info) {}
        //开始播放
        public void onSpeakBegin() {}
        //暂停播放
        public void onSpeakPaused() {}
        //播放进度回调
        //percent为播放进度0~100,beginPos为播放音频在文本中开始位置，endPos表示播放音频在文
        //本中结束位置.
        public void onSpeakProgress(int percent, int beginPos, int endPos) {}
        //恢复播放回调接口
        public void onSpeakResumed() {}
        //会话事件回调接口
        public void onEvent(int arg0, int arg1, int arg2, Bundle arg3) {}
    };

}
