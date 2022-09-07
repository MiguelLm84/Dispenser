package com.miguel_lm.shiftdispenser;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;
import com.miguel_lm.shiftdispenser.databinding.ActivityMainBinding;



public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    //static final String URL = "http://192.180.2.31:8082/gmedia4_5_3/expendedor.php";
    static final String URL = "http://rubenhermida.com";

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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            binding.webViewShiftDispenser.getSettings().setSafeBrowsingEnabled(true);
        }
        binding.webViewShiftDispenser.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

                Intent intent = new Intent(Intent.ACTION_VIEW, request.getUrl());
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                return true;
            }
        });
        binding.webViewShiftDispenser.loadUrl(URL);
    }
}