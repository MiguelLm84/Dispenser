package com.miguel_lm.shiftdispenser;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;
import com.miguel_lm.shiftdispenser.databinding.ActivityMainBinding;



public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    //static final String URL = "http://192.180.2.31:8082/gmedia4_5_3/expendedor.php";
    static final String URL = "http://rubenhermida.com";
    //static final String SEARCH_PATH = "/search?q=rubenhermida.com";
    //static final String SEARCH_PATH = "/search?q=192.180.2.31:8082/gmedia4_5_3/expendedor.php";
    //static final String URL = "https://google.es" + SEARCH_PATH;

    //private static final String ACTION_USB_PERMISSION = "com.android.example.USB_PERMISSION";
    //UsbConnection usbConnection = UsbPrintersConnections.selectFirstConnected(this);
    //UsbManager usbManager = (UsbManager) this.getSystemService(Context.USB_SERVICE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        getWebView();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void getWebView() {

        binding.webViewShiftDispenser.getSettings().setJavaScriptEnabled(true);
        binding.webViewShiftDispenser.getSettings().setBuiltInZoomControls(true);
        binding.webViewShiftDispenser.getSettings().setDisplayZoomControls(false);
        binding.webViewShiftDispenser.getSettings().setDomStorageEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            binding.webViewShiftDispenser.getSettings().setSafeBrowsingEnabled(false);
        }
        binding.webViewShiftDispenser.setWebViewClient(new WebViewClient() {

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed(); // Ignore SSL certificate errors
            }

            /*@Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

                /*Intent intent = new Intent(Intent.ACTION_VIEW, request.getUrl());
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                return true;*/
                //return false;
            //}

            /*@Override
            public void onReceivedSslError(WebView view, final SslErrorHandler handler, SslError error) {
                String message = "SSL Certificate error.";

                switch (error.getPrimaryError()) {
                    case SslError.SSL_UNTRUSTED:
                        message = "The certificate authority is not trusted.";
                        break;
                    case SslError.SSL_EXPIRED:
                        message = "The certificate has expired.";
                        break;
                    case SslError.SSL_IDMISMATCH:
                        message = "The certificate Hostname mismatch.";
                        break;
                    case SslError.SSL_NOTYETVALID:
                        message = "The certificate is not yet valid.";
                        break;
                }
                message += "\"SSL Certificate Error\" Do you want to continue anyway?.. YES";

                handler.proceed();

                Log.e("TAG", "onReceivedSslError : " + message);
            }*/
        });
        binding.webViewShiftDispenser.loadUrl(URL);
    }
}