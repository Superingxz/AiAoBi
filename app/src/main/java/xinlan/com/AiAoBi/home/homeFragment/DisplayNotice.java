package xinlan.com.AiAoBi.home.homeFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebSettings;
import android.webkit.WebView;

import xinlan.com.AiAoBi.BaseActivty;
import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.entity.BasenoteInfo;
import xinlan.com.AiAoBi.view.TitileView;

/**
 * Created by Administrator on 2016/12/12.
 */
public class DisplayNotice extends BaseActivty {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_notice);
        WebView view= (WebView) findViewById(R.id.notice_webview);
        TitileView title= (TitileView) findViewById(R.id.title);
        title.setTitle("公告详情");
        BasenoteInfo basenoteInfo= (BasenoteInfo) getIntent().getSerializableExtra("basenoteInfo");
        view.loadUrl(basenoteInfo.getUrl());
        WebSettings settings = view.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setDisplayZoomControls(false);//设置放大缩小按钮不显示
        settings.setBuiltInZoomControls(true);
    }
}
