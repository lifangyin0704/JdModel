package bwie.com.jdmodel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class Web_vpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_vp);
        WebView wv = (WebView) findViewById(R.id.wv);
        wv.loadUrl(getIntent().getStringExtra("url"));

    }
}
