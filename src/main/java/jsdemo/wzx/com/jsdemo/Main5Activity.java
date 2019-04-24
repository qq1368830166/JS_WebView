package jsdemo.wzx.com.jsdemo;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

public class Main5Activity extends AppCompatActivity {

    private WebView webView5;
    private SwipeRefreshLayout swrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        initView();
        configureView();

    }

    private void configureView() {
        loadurl();
        //支持javascript
        webView5.getSettings().setJavaScriptEnabled(true);
        // 设置可以支持缩放
        webView5.getSettings().setSupportZoom(true);
        // 设置出现缩放工具
        webView5.getSettings().setBuiltInZoomControls(true);
        //扩大比例的缩放
        webView5.getSettings().setUseWideViewPort(true);
        //自适应屏幕
        webView5.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView5.getSettings().setLoadWithOverviewMode(true);


        swrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadurl();
            }
        });

        webView5.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                //会触发多次
                if (newProgress==100){
                    swrl.setRefreshing(false);
                }
            }
        });
    }

    private void initView() {
        webView5 = (WebView) findViewById(R.id.webView5);
        swrl = (SwipeRefreshLayout) findViewById(R.id.swrl);
    }


    private void loadurl(){
        webView5.loadUrl("https://cf.qq.com/");
    }
}
