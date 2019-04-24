package jsdemo.wzx.com.jsdemo;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

/**
 * @author wang
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private WebView webview;
    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        configureWebview();
    }

    private void configureWebview() {
        WebSettings webSettings = webview.getSettings();

        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        webview.loadUrl("file:///android_asset/javascript.html");


        webview.setWebChromeClient(new WebChromeClient() {

            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
                b.setTitle("alert1");

                //JS 返回来的值
                b.setMessage(message);

                b.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //关闭窗口JS调用
                        result.confirm();
                    }
                });
                b.setCancelable(false);
                b.create().show();
                return true;
            }
        });
}

    private void initView() {
        webview = (WebView) findViewById(R.id.webview);

        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);
    }
    private String msg="android的值";
    @SuppressLint("NewApi")
    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.button1:
                webview.post(new Runnable() {
                    @Override
                    public void run() {
                        webview.evaluateJavascript("javascript:callJS('" + msg + "')", new ValueCallback<String>() {
                            @Override
                            public void onReceiveValue(String value) {
                                Log.e("TAG","是"+value);
                            }});
                    }
                });


                break;
                default:
        }
    }
}
