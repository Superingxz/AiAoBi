package xinlan.com.AiAoBi.updateAPP;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.google.gson.Gson;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import xinlan.com.AiAoBi.NetCilent;
import xinlan.com.AiAoBi.R;

/**
 * Created by LSY on 2016/9/20.
 */
public class UpdateApkManager {
    private Gson gson;
    private ApkInfo apkInfo;
    private Context context;
    private int versionCode;
    private Callback<ResponseBody> callback = new Callback<ResponseBody>() {
        @Override
        public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
            if (response.isSuccess()) {
                try {
                    String result = response.body().string();
                    apkInfo = gson.fromJson(result, ApkInfo.class);
                    if (apkInfo != null) {
                        PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(),
                                PackageManager.GET_CONFIGURATIONS);
                        versionCode = packageInfo.versionCode;
                        if (apkInfo.getversionCode() > versionCode) {
                            final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            builder.setTitle("检测更新！");
                            builder.setIcon(R.mipmap.ic_launcher);
                            builder.setMessage("检测到最新版本，是否开始更新？");
                            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent(context, ApkDownlLoadService.class);
                                    context.startService(intent);
                                }
                            });
                            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                            builder.show();
                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onFailure(Throwable t) {
            Log.i("Tag", "onFailure: " + t.toString());
        }
    };

    public UpdateApkManager(Context context) {
        gson = new Gson();
        this.context = context;
    }

    /**
     * 获取服务器APK信息,判断是否有更新版本
     */
    public void checkoutUpdate() {
        Call<ResponseBody> call = NetCilent.getInstance().getNetApi().getApkInfo();
        call.enqueue(callback);
    }


}
