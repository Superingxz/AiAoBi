package xinlan.com.AiAoBi;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;
import com.squareup.okhttp.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import xinlan.com.AiAoBi.entity.BasesaleaxisInfo;
import xinlan.com.AiAoBi.entity.GetAgentInfo;
import xinlan.com.AiAoBi.home.HomeActivity;
import xinlan.com.AiAoBi.home.enity.BaseModel;
import xinlan.com.AiAoBi.requestJionIn.JionInActivity;

/**
 * Created by Administrator on 2016/9/21.
 */
public class MainPresenter extends MvpNullObjectBasePresenter<MainView> {
    private NetCilent netCilent;
    private UserInfo userInfo;
    private App app;
    private String basurl;
    public MainPresenter() {
        netCilent=NetCilent.getInstance();
        app=App.getApp();
        userInfo=new UserInfo();
        basurl=netCilent.getBasurl();
    }
    private Call<GetAgentInfo> call;

    /**
     * 登录请求     curuserid 13570401327    mobile_phone 18638230307
     */
    private String number;
    public void login(String number,String pw){
        this.number=number;
        getView().showProgressDialog("登录中...");
        Map<String,String> user=new HashMap<>();
        user.put("phone",number);
        user.put("pwd",pw);
        if (call!=null) call.cancel();
        call= netCilent.getNetApi().logininfo(user);
        call.enqueue(loginCallBack);
    }
    private Callback<GetAgentInfo> loginCallBack=new Callback<GetAgentInfo>() {
        @Override
        public void onResponse(Response<GetAgentInfo> response, Retrofit retrofit) {
            getView().dimissProgressDialog();
            if (response.isSuccess()){//请求成功:code=200
                getView().dimissProgressDialog();
                GetAgentInfo info = response.body();
                if (("1").equals(info.getRes())){
                    GetAgentInfo.data data = info.getData().get(0);
                    userInfo.setRegedit_date(data.getRegedit_date());
                    userInfo.setGrant_date(data.getGrant_date());
                    userInfo.setBalance(data.getBalance());
                    userInfo.setProvince(data.getProvince());
                    userInfo.setCity(data.getCity());
                    userInfo.setArea(data.getArea());
                    userInfo.setAddress(data.getAddress());
                    userInfo.setAgentid(data.getAgentid());
                    userInfo.setVoucher(data.getVoucher());
                    userInfo.setCurparentlevel(data.getCurparentlevel());
                    userInfo.setRefmanid(data.getRefmanid());
                    userInfo.setCurparentid(data.getCurparentid());
                    userInfo.setCurparentname(data.getCurparentname());
                    userInfo.setAgentcode(data.getAgentcode());
                    userInfo.setPhone(data.getPhone());
                    userInfo.setRefmantel(data.getRefmantel());
                    userInfo.setSlevel(data.getSlevel());
                    userInfo.setRefman(data.getRefman());
                    userInfo.setBrandid(data.getBrandid());
                    userInfo.setAgentname(data.getAgentname());
                    userInfo.setCurparenttel(data.getCurparenttel());
                    userInfo.setIsmore(data.getIsmore());
                    userInfo.setWeixin(data.getWeixin());
                    userInfo.setBrand(data.getBrand());
                    userInfo.setCardno(data.getCardno());
                    Log.i("Log", "当前登录用户信息: "+data.toString());
                    app.setUserInfo(userInfo);
                    //initLocation();
                    String status = null;
                    if ("0".equals(data.getAuditing())){
                        switch (data.getStatus()){
                            case "0":
                                status="新申请";
                                break;
                            case "3":
                                status="待推荐人审批";
                                break;
                            case "5":
                                status="待一级/总代审批";
                                break;
                            case "7":
                                status="待总裁审批";
                                break;
                            case "9":
                                status="待公司审批";
                                break;
                            case "8"://已退回
                                getView().showDiyMsgDialog("账号申请已被退回，\n是否删除申请信息？", "删除", "取消", new BaseActivty.Mcallback() {
                                    @Override
                                    public void doSomething(AlertDialog alertDialog) {
                                        delagent(alertDialog);
                                    }

                                    @Override
                                    public void onClick(View view) {

                                    }
                                });
                                return;
                        }
                        //未生效
                        getView().showDiyMsgDialog("该账号状态：\n"+status, "确定", "取消", new BaseActivty.Mcallback() {
                            @Override
                            public void doSomething(AlertDialog alertDialog) {
                                alertDialog.dismiss();
                            }
                            @Override
                            public void onClick(View view) {
                            }
                        });
                    }else {
                        Intent intent=new Intent();
                        getView().intentActivity(HomeActivity.class,intent);
                        getView().finishActivity();
                    }
                }else getView().showToast("账号或密码错误！",Toast.LENGTH_SHORT);
            }else getView().showToast(response.code()+"连接失败！",Toast.LENGTH_SHORT);
        }

        @Override
        public void onFailure(Throwable t) {
            Log.i("Log","onFailuer:"+t.toString());
            getView().dimissProgressDialog();
            getView().showToast("连接服务器失败"+t.toString(),Toast.LENGTH_SHORT);
        }
    };

    private void delagent(final AlertDialog alertDialog) {
        getView().showProgressDialog("正在删除...");
        Call<BaseModel> call =netCilent.getNetApi().delagent(userInfo.getPhone());
        call.enqueue(new Callback<BaseModel>() {
            @Override
            public void onResponse(Response<BaseModel> response, Retrofit retrofit) {
                getView().dimissProgressDialog();
                if (response.isSuccess()){
                    BaseModel body = response.body();
                    if ("1".equals(body.getRes())){
                        alertDialog.dismiss();
                        getView().showToast("删除代理商成功！",Toast.LENGTH_SHORT);
                    }else if ("-1".equals(body.getRes())){
                        getView().showToast("删除代理商失败！",Toast.LENGTH_SHORT);
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                getView().dimissProgressDialog();
                getView().showToast("连接服务器失败！",Toast.LENGTH_SHORT);
            }
        });
    }

    public void findpassword(){
        Call<ResponseBody> call = netCilent.getNetApi().findPassword();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
    /**
     * 初始化高德地图获取地理位置信息
     */

    void initLocation(final AMapLocationClient locationClient){
        AMapLocationListener locationListener=new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                //通过回调接口获取到位置信息
                if (aMapLocation!=null){
                    int errorCode = aMapLocation.getErrorCode();
                    Log.i("halo", "onLocationChanged: "+aMapLocation.getErrorCode());
                    if (errorCode==0){
                        aMapLocation.getAddress();//地址
                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date=new Date(aMapLocation.getTime());
                        String time = simpleDateFormat.format(date);//定位时间
                        app.setLoginProvice(aMapLocation.getProvince());
                        app.setLoginCity(aMapLocation.getCity());
                        Log.i("log","地理信息："+aMapLocation.getProvince()+aMapLocation.getCity()+aMapLocation.getAddress()+"；时间"+time+"手机型号："+ Build.MODEL+";系统版本："+Build.VERSION.RELEASE);
                    }else  {
                        //initLocation(locationClient);
                    }
                }

            }
        };
        AMapLocationClientOption option=new AMapLocationClientOption();
        option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//设置定位方式为高精度
        option.setOnceLocation(true);//设置只定位一次
        locationClient.setLocationListener(locationListener);//添加监听事件
        locationClient.setLocationOption(option);
        locationClient.startLocation();//启动定位
    }
    public void check(){
        getView().showProgressDialog("正在测试连接...");
        Call<GetAgentInfo> call = netCilent.getNetApi().check("check");
        call.enqueue(new Callback<GetAgentInfo>() {
            @Override
            public void onResponse(Response<GetAgentInfo> response, Retrofit retrofit) {
                getView().dimissProgressDialog();
                if (response.code()!=200){
                    getView().showDiyMsgDialog(response.code() + "连接失败", "确定", "取消", new BaseActivty.Mcallback() {
                        @Override
                        public void doSomething(AlertDialog alertDialog) {
                            alertDialog.dismiss();
                        }

                        @Override
                        public void onClick(View view) {

                        }
                    });
                    return;
                }
                Log.i("halo", "onResponse: "+response.code());
                if (response.isSuccess()){
                    netCilent.setBasurl(basurl);
                    getView().showDiyMsgDialog("连接服务器成功！", "确定", "取消", new BaseActivty.Mcallback() {
                        @Override
                        public void doSomething(AlertDialog alertDialog) {
                            alertDialog.dismiss();
                        }

                        @Override
                        public void onClick(View v) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Throwable t) {
                getView().dimissProgressDialog();
                netCilent.setBasurl(basurl);
                getView().showDiyMsgDialog("连接服务器失败！", "确定", "取消", new BaseActivty.Mcallback() {
                    @Override
                    public void doSomething(AlertDialog alertDialog) {
                        alertDialog.dismiss();
                    }

                    @Override
                    public void onClick(View v) {
                    }
                });
            }
        });
    }
}
