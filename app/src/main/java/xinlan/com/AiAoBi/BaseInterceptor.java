package xinlan.com.AiAoBi;


import android.util.Log;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Administrator on 2017/1/4.
 */
public class BaseInterceptor implements Interceptor {
    private final String mApiKey;
    private final String mApiSecret;

    public BaseInterceptor(String apiKey, String apiSecret) {
        mApiKey = apiKey;
        mApiSecret = apiSecret;
    }

    @Override public Response intercept(Interceptor.Chain chain) throws IOException {
        /*Request oldRequest = chain.request();
        Log.i("httpk",oldRequest.url().getQuery());
        // 添加新的参数
        HttpUrl.Builder authorizedUrlBuilder = oldRequest.httpUrl()
                .newBuilder()
                .addQueryParameter(mApiKey, mApiSecret);

        // 新的请求
        Request newRequest = oldRequest.newBuilder()
                .method(oldRequest.method(), oldRequest.body())
                .url(authorizedUrlBuilder.build())
                .build();
        return chain.proceed(newRequest);*/
        Request original = chain.request();
        HttpUrl originalHttpUrl = original.httpUrl();

        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter(mApiKey, mApiSecret)
                .build();
        // Request customization: add request headers
        Request.Builder requestBuilder = original.newBuilder()
                .url(url);
        Request request = requestBuilder.build();
        Log.i("httphaha","=================="+url.toString());
        return chain.proceed(request);
    }
}
