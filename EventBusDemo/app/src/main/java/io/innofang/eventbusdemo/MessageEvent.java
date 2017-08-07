package io.innofang.eventbusdemo;

/**
 * Author: Inno Fang
 * Time: 2017/8/7 00:04
 * Description:
 */


public class MessageEvent {

    private String mMessage;

    public MessageEvent(String message) {
        mMessage = message;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }
}
