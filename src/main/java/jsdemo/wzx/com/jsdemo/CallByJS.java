package jsdemo.wzx.com.jsdemo;
/*
 * @author wzx
 * create at 2019/4/23
 * description:
 */

import android.util.Log;
import android.webkit.JavascriptInterface;

public class CallByJS{
    // 被JS调用的方法必须加@JavascriptInterface注解
    @JavascriptInterface
    public void callByJS(String str) {
        Log.e("web_test",str);
    }
}