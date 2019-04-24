package jsdemo.wzx.com.jsdemo;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Set;

public class Main2Activity extends AppCompatActivity {

    private WebView webview2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
    }

    private void initView() {
        webview2 = (WebView) findViewById(R.id.webview2);
        WebSettings webSettings = webview2.getSettings();
        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);

        //第一种
        web1();

        //第二种
        web2();


        //第三种
        web3();

    }

    private void web3() {
        webview2.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                return super.onJsConfirm(view, url, message, result);
            }

            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                // 根据协议的参数进行拦截,下面拦截"callandroid://test"
                // 一般根据scheme & authority判断
                Uri uri = Uri.parse(message);//message是由JS的prompt()传来的
                // 如果 scheme  = callAndroid，即代表都符合约定的协议
                if (uri.getScheme().equals("callandroid")) {

                    // 如果 authority  = test，即代表都符合约定的协议
                    if (uri.getAuthority().equals("test")) {// 拦截url,下面开始执行Android的方法

                        // 可以获取参数
                        HashMap<String, String> params = new HashMap<>();
                        Set<String> paramNames = uri.getQueryParameterNames();
                        for (String name : paramNames) {
                            params.put(name, uri.getQueryParameter(name));
                        }
                        //将返回值返回给JS
                        result.confirm("拦截Prompt成功啦,传进来的参数是："+params.toString());
                    }
                    return true;
                }
                return super.onJsPrompt(view, url, message, defaultValue, result);
            }
        });
    }

    private void web2() {
        webview2.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 根据协议的参数进行拦截,下面拦截"callandroid://test"
                // 一般根据scheme & authority判断

                Uri uri = Uri.parse(url);
                // 如果 scheme  = callAndroid，即代表都符合约定的协议
                if (uri.getScheme().equals("callandroid")) {

                    // 如果 authority  = test，即代表都符合约定的协议
                    if (uri.getAuthority().equals("test")) {
                        // 拦截url,下面开始执行Android的方法

                        Toast.makeText(Main2Activity.this, "js通过拦截调用了Android的方法", Toast.LENGTH_SHORT).show();
                        // 可以获取参数
                        HashMap<String, String> params = new HashMap<>();
                        Set<String> paramNames = uri.getQueryParameterNames();
                        for (String name : paramNames) {
                            params.put(name, uri.getQueryParameter(name));
                        }

                        Log.e("web_test", "是"+params.toString());

                    }

                    return true;
                }
                return super.shouldOverrideUrlLoading(view, url);

            }
        });
    }

    private void web1() {
        // 通过addJavascriptInterface()将Java对象映射到JS对象
        //参数1：Javascript对象名
        //参数2：Java对象名
        // 通过addJavascriptInterface()将Java对象映射到JS对象
        webview2.addJavascriptInterface(new CallByJS(), "androidObj");

        // 加载JS代码
        // 格式规定为:file:///android_asset/文件名.html
        webview2.loadUrl("file:///android_asset/js.html");
    }
}
