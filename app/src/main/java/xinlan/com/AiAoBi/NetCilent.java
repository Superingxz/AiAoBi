package xinlan.com.AiAoBi;


import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by LSY on 2016/9/20.
 */
public class NetCilent {
    private String basurl = "http://120.76.54.180:85/ayb/";//服务器
   // private final String basurl="http://192.168.253.1:85/ayb/";
    //private final String basurl = "http://172.27.35.1:85/ayb/";
    private static NetCilent netCilent;
    private NetApi netApi;
    private int flag;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==0x1){
                flag=0x1;
            }
            if (msg.what==0x2){
                flag=0x2;
            }
            App.getApp().getHandlerClientb().sendEmptyMessage(0x3);
        }
    };
    private NetCilent() {
        App.getApp().setHandlerClienta(handler);
    }

    public static NetCilent getInstance() {
        if (netCilent == null) {
            netCilent = new NetCilent();
        }
        return netCilent;
    }

    public String getBasurl() {
        return basurl;
    }

    public void setBasurl(String basurl) {
        this.basurl = basurl;
    }

    private OkHttpClient getOkHttpClient() {
         OkHttpClient client = new OkHttpClient();
         client .setRetryOnConnectionFailure(true);
         client.setConnectTimeout(15, TimeUnit.SECONDS);
         if (flag==0x1){
            client.interceptors().add(new BaseInterceptor("flag","kangyu"));
         }else if (flag==0x2){
             client.interceptors().add(new BaseInterceptor("flag","normal"));
         }
        client.interceptors().add(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        return client;

    }

    /**
     * 获取API
     */
    public NetApi getNetApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(basurl)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
       // if (netApi == null) {
            netApi = retrofit.create(NetApi.class);
        //}
        Log.i("halo", "getNetApi: "+basurl);
        return netApi;
    }
}
