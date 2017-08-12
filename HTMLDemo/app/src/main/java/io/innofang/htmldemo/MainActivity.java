package io.innofang.htmldemo;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = (WebView) findViewById(R.id.web_view);
        /* 启用 JavaScript */
        mWebView.getSettings().setJavaScriptEnabled(true);
        /* 从 assets 目录下面的加载 html */
        mWebView.loadUrl("file:///android_asset/index.html");
        mWebView.addJavascriptInterface(MainActivity.this, "android");
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.call_js_button:
                mWebView.loadUrl("javascript:javaCallJS()");
                break;
            case R.id.call_js_with_args_button:
                mWebView.loadUrl("javascript:javaCallJSWithArgs(" + "'hello world'" + ")");
                break;
            case R.id.call_js_to_change_color_button:
                mWebView.loadUrl("javascript:changeColor('" + ColorUtil.generateRandomHexColorCode() + "')");
                break;
        }
    }

    /**
     * 由于安全原因 targetSdkVersion >= 17 需要加 @JavascriptInterface
     * JS 调用 Android JAVA 方法名和 HTML 中的按钮 onclick 后的别名后面的名字对应
     */
    @JavascriptInterface
    public void startFunction() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "show", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @JavascriptInterface
    public void startFunction(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new AlertDialog.Builder(MainActivity.this)
                        .setMessage(msg)
                        .show();
            }
        });
    }

    @JavascriptInterface
    public void showDialog(int a, float b, String c, boolean d) {
        if (d) {
            String msg = "a + b + c = " + a + b + c;
            new AlertDialog.Builder(MainActivity.this)
                    .setMessage(msg)
                    .show();
        }
    }
}
