package jsdemo.wzx.com.jsdemo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class Main4Activity extends AppCompatActivity {

    private WebView webview3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        initView();
        configureWebview();
    }

    private void configureWebview() {
        // 设置WebView属性，能够执行Javascript脚本
        WebSettings settings = webview3.getSettings();
        settings.setJavaScriptEnabled(true);

        webview3.loadUrl("https://www.baidu.com/");
        webview3.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        webview3.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // 页面(url)开始加载
                Toast.makeText(Main4Activity.this, "开始加载", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                // 页面(url)完成加载

            }

        });

        webview3.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                //会触发多次
                if (newProgress==100){
                    Toast.makeText(Main4Activity.this, "完成加载", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        webview3 = (WebView) findViewById(R.id.webview3);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            if(webview3.canGoBack()){
                webview3.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
                webview3.goBack();
                return true;
            }else {
                finish();
            }
        }
        return false;
    }


}
