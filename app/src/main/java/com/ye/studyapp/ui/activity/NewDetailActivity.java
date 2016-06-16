package com.ye.studyapp.ui.activity;

import android.view.View;

import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.ye.studyapp.R;
import com.ye.studyapp.base.BaseTitleBarActivity;

import butterknife.Bind;

public class NewDetailActivity extends BaseTitleBarActivity {

    @Bind(R.id.wv_news_content)
    WebView mWvNewsContent;

    @Override
    protected View getMainView() {
        View view=View.inflate(context, R.layout.activity_new_detail,null);
        ivLeft.setImageResource(R.mipmap.back);
        return view;
    }

    @Override
    protected void init() {
        String link=getIntent().getStringExtra("link");
        tvTitle.setText(getIntent().getStringExtra("title"));
        WebSettings webSettings = mWvNewsContent.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        mWvNewsContent.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        mWvNewsContent.loadUrl(link);
        setListenr();
    }

    private void setListenr() {
        //返回
        ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
