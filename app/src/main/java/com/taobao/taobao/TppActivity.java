package com.taobao.taobao;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import android.webkit.WebView;
import android.webkit.WebViewClient;


public class TppActivity extends AppCompatActivity {

    private WebView webView;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.taobao);
        webView.loadUrl("https://h5.m.taopiaopiao.com/app/moviemain/pages/index/index.html");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.canGoBack();
        webView.setWebViewClient(new WebViewClient());
//        webView.getSettings().setUserAgentString("Mozilla/5.0 (Linux; Google; Android 8.1.0; zh-CN; Nexus 6 Build/HonorH30-L01) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/65.0.3325.109 UCBrowser/11.8.8.968 UWS/2.13.1.10 Mobile Safari/537.36 AliApp(TB/7.7.3)");
        webView.getSettings().setGeolocationEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
//        TppActivity.this.getWindow().setStatusBarColor(getResources().getColor(R.color.navigationBarDividerColor));
        webView.getSettings().setDomStorageEnabled(true);

        webView.requestFocusFromTouch();

        webView.setSaveFromParentEnabled(true);



        final SwipeRefreshLayout refreshLayout = findViewById(R.id.refresh);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        webView.reload();
                        refreshLayout.setRefreshing(false);
                    }
                },2000);
            }
        });

//        WindowManager.LayoutParams lp=getWindow().getAttributes();
//
//        lp.alpha=0.3f;
//
//        getWindow().setAttributes(lp);


//        webView.setWebViewClient(new WebViewClient() {
//            public void onPageFinished(WebView view, String url) {
//                //用户名
//                String user="username";
//                //密码
//                String pwd="123456";
//                //把用户名密码填充到表单
//                view.loadUrl("javascript:document.getElementById('username').value = '"+user+"';");
//            }
//        });


//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            String channelId = "normal";
//            String channelName = "普通消息";
//            int importance = NotificationManager.IMPORTANCE_HIGH;
//            createNotificationChannel(channelId, channelName, importance);
//
//            channelId = "update";
//            channelName = "更新消息";
//            importance = NotificationManager.IMPORTANCE_DEFAULT;
//            createNotificationChannel(channelId, channelName, importance);
//        }

//        sendChatMsg();


    }

//    public void sendChatMsg() {
//        RemoteMessage message = null;
//        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        Notification notification = new NotificationCompat.Builder(this, "update")
//                .setContentTitle(message.getFrom())
//                .setContentText((CharSequence) message.getNotification())
//                .setWhen(System.currentTimeMillis())
//                .setSmallIcon(R.drawable.ic_movie)
//                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_movie))
//                .setAutoCancel(true)
//                .build();
//        manager.notify(1, notification);
//    }

//    @RequiresApi(api = Build.VERSION_CODES.O)
//    private void createNotificationChannel(String channelId, String channelName, int importance) {
//        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
//        NotificationManager notificationManager = (NotificationManager) getSystemService(
//                NOTIFICATION_SERVICE);
//        notificationManager.createNotificationChannel(channel);
//    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            if (webView.canGoBack()){
                webView.goBack();
                return true;
            }else{
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
