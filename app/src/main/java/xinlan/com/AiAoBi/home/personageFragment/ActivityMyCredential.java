package xinlan.com.AiAoBi.home.personageFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import xinlan.com.AiAoBi.BaseActivty;
import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.home.enity.BaseModel;
import xinlan.com.AiAoBi.view.TitileView;

/**
 * Created by Administrator on 2016/11/22.
 */
public class ActivityMyCredential extends BaseActivty {
    @BindView(R.id.my_credential_title)
    TitileView title;
    @BindView(R.id.my_credential_iv)
    WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_credential);

    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        title.setTitle("授权证书");
        getagentright();

    }

    private void getagentright() {
        Map<String,String> map=new HashMap<>();
        map.put("agentid",user.getAgentid());
        map.put("slevel",user.getSlevel());
        map.put("ismore",user.getIsmore());
        map.put("brandid",user.getBrandid());
        map.put("brand",user.getBrand());
        Call<BaseModel> call = netApi.getagentright(map);
        call.enqueue(new Callback<BaseModel>() {
            @Override
            public void onResponse(Response<BaseModel> response, Retrofit retrofit) {
                if (response.isSuccess()){
                    BaseModel body = response.body();
                    if ("1".equals(body.getRes())){
                        //获取数据成功
                        JSONArray array=new JSONArray(body.getData());
                        try {
                            JSONObject object = array.getJSONObject(0);
                            String url=object.getString("url");
                            Log.i("log","bbbburl:"+url);
                            initialScale();
                            webView.loadUrl(url);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
   void initialScale(){
       WebSettings settings = webView.getSettings();
       settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
       settings.setDisplayZoomControls(false);//设置放大缩小按钮不显示
       settings.setBuiltInZoomControls(true);
       settings.setLoadWithOverviewMode(true);
       settings.setUseWideViewPort(true);
       settings.setDomStorageEnabled(true);
       //settings.setUseWideViewPort(true);
      // webView.setInitialScale(100);
       /*WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
       int width = wm.getDefaultDisplay().getWidth();
       if(width > 650)
       {
           this.webView.setInitialScale(190);
       }else if(width > 520)
       {
           this.webView.setInitialScale(160);
       }else if(width > 450)
       {
           this.webView.setInitialScale(140);
       }else if(width > 300)
       {
           this.webView.setInitialScale(120);
       }else
       {
           this.webView.setInitialScale(100);
       }*/
   }
}
