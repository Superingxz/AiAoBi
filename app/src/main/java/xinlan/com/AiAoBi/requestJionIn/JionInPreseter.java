package xinlan.com.AiAoBi.requestJionIn;

import android.util.Log;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;
import com.squareup.okhttp.ResponseBody;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import xinlan.com.AiAoBi.NetApi;
import xinlan.com.AiAoBi.NetCilent;
import xinlan.com.AiAoBi.entity.GetAgentInfo;
import xinlan.com.AiAoBi.entity.GetCityInfo;
import xinlan.com.AiAoBi.entity.GetLevelInfo;
import xinlan.com.AiAoBi.entity.GetSetInfo;

/**
 * Created by Administrator on 2016/10/19.
 */
public class JionInPreseter extends MvpNullObjectBasePresenter<JionInView> {
    private NetApi netApi;
    private Map<String, String> map;

    public JionInPreseter() {
        netApi = NetCilent.getInstance().getNetApi();
        map = new HashMap<>();
    }
    /**
     * 参数清单如下：
     1 功能编码：eventcode=getcity
     2 当前登录用户code: curuserid 例如135705401327
     3 邀请码：yqm=124213
     3 字段集参数：fieldvalues ，字段和值格式：cur_name:沈旭，字段之间用分号隔开
     4 字段集参数中字段说明

     agentname:供应商名称;
     cardno:身份证;
     slevel:级别(取值【1:总裁;2:官方;3:总代;4:一级;5:经销商;6:特约;7:特约】);
     phone:电话;
     weixin:微信;
     ismore:单品或全系（取值【0：单品；1：全系】）;
     refman:推荐人;
     refmantel:推荐人电话;
     province:所属省;
     city:所属城市;
     area:区;
     address:收货地址;
     brandid:品牌id(取值为货品id);
     brand:品牌(取值为货品名称);
     voucher:付款凭证；
     cardnofront:身份证正面；
     cardnoback:身份证反面；
     */
    /**
     * 总代理申请加入
     */
    public void GeneralRequestJion(String...strings) {
        map.clear();
        map.put("agentname",strings[0]);
        map.put("cardno",strings[1]);
        map.put("slevel",strings[2]);
        map.put("phone",strings[3]);
        map.put("weixin",strings[4]);
        map.put("ismore",strings[5]);
        map.put("refman",strings[6]);
        map.put("refmantel",strings[7]);
        map.put("province",strings[8]);
        map.put("city",strings[9]);
        map.put("area",strings[10]);
        map.put("address",strings[11]);
        map.put("brandid",strings[12]);
        map.put("brand",strings[13]);
        map.put("voucher",strings[14]);
        map.put("cardnoa",strings[15]);
        map.put("cardnob",strings[16]);
        Call<ResponseBody> call = netApi.GeneralRequestJoin(map);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
                map.clear();
                getView().dimissProgressDialog();
                if (response.isSuccess()){
                    ResponseBody body = response.body();
                    try {
                        String string = body.string();
                        JSONObject object=new JSONObject(string);
                        String msg = (String) object.get("msg");
                        Log.i("log","body.string:"+string);
                        getView().dimissProgressDialog();
                        getView().showMsgDialog(msg,0);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                map.clear();
                getView().dimissProgressDialog();
                Log.i("log","连接失败："+t.toString());
                getView().showToast("连接服务器失败",Toast.LENGTH_SHORT);
            }
        });
    }

    /**
     * 一级代理申请加入
     */
    public void StairRequestJoin(String...strings) {
        map.clear();
        map.put("agentname",strings[0]);
        map.put("cardno",strings[1]);
        map.put("slevel",strings[2]);
        map.put("phone",strings[3]);
        map.put("weixin",strings[4]);
        map.put("ismore",strings[5]);
        map.put("brandid",strings[6]);
        map.put("brand",strings[7]);
        map.put("refman",strings[8]);
        map.put("refmantel",strings[9]);
        map.put("voucher",strings[10]);
        map.put("cardnoa",strings[11]);
        map.put("cardnob",strings[12]);
        Call<ResponseBody> call = netApi.StairRequestJoin(map);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
                getView().dimissProgressDialog();
                map.clear();
                if (response.isSuccess()){
                    ResponseBody body = response.body();
                    try {
                        String string = body.string();
                        JSONObject object=new JSONObject(string);
                        String msg = (String) object.get("msg");
                        Log.i("log","body.string:"+string);
                        getView().showMsgDialog(msg,0);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                map.clear();
                getView().dimissProgressDialog();
                Log.i("log","连接失败："+t.toString());
                getView().showToast("连接服务器失败",Toast.LENGTH_SHORT);
            }
        });
    }

    /**
     * 经销商申请加入
     */
    public void DealerRequestJoin(String...strings) {
        map.clear();
        map.put("agentname",strings[0]);
        map.put("slevel",strings[1]);
        map.put("phone",strings[2]);
        map.put("weixin",strings[3]);
        map.put("ismore",strings[4]);
        map.put("brandid",strings[5]);
        map.put("brand",strings[6]);
        map.put("refman",strings[7]);
        map.put("refmantel",strings[8]);
        map.put("voucher",strings[9]);

        Call<ResponseBody> call = netApi.DealerRequestJoin(map);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
                map.clear();
                getView().dimissProgressDialog();
                if (response.isSuccess()){
                    ResponseBody body = response.body();
                    String s = retrofit.baseUrl() + map.toString();
                    try {
                        String string = body.string();
                        JSONObject object=new JSONObject(string);
                        String msg = (String) object.get("msg");
                        Log.i("log","body.string:"+string+";url:"+s);
                        getView().showMsgDialog(msg,0);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                map.clear();
                getView().dimissProgressDialog();
                Log.i("log","连接失败："+t.toString());
                getView().showToast("连接服务器失败",Toast.LENGTH_SHORT);
            }
        });
    }


    /**
     * 获取省份
     */
    public void getProvince(){
        map.put("curuserid","13570401327");
        map.put("citylevel","2");
        //RequestBody requestBody=RequestBody.create(MediaType.parse("text/*"),new Gson().toJson(new RequsetCityInfo()));
        Call<GetCityInfo> call = netApi.getCity(map);
        call.enqueue(new Callback<GetCityInfo>() {
            @Override
            public void onResponse(Response<GetCityInfo> response, Retrofit retrofit) {
                if (response.isSuccess()){
                    map.clear();
                    GetCityInfo body = response.body();
                    if ("1".equals(body.getRes())){
                        getView().setDataToSpProvince(body.getData());
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                map.clear();
                getView().dimissProgressDialog();
                getView().finishActivity();
                getView().showToast("连接服务器失败", Toast.LENGTH_SHORT);
            }
        });
    }

    /**
     * 获取城市
     */
    public void getCity(String cityName){
        map.put("curuserid","13570401327");
        map.put("citylevel","3");
        map.put("city_name",cityName);
        Call<GetCityInfo> call = netApi.getCity(map);
        call.enqueue(new Callback<GetCityInfo>() {
            @Override
            public void onResponse(Response<GetCityInfo> response, Retrofit retrofit) {
                Log.i("Log","response.isSuccess():"+response.isSuccess());
                if (response.isSuccess()){
                    map.clear();
                    GetCityInfo body = response.body();
                    Log.i("Log","body.getRes():"+body.getRes());
                    if ("1".equals(body.getRes())){
                        getView().setDatatoSpCity(body.getData());
                        Log.i("Log","getCity获取城市数据成功:"+body.getData());
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                map.clear();
                getView().dimissProgressDialog();
                getView().finishActivity();
                getView().showToast("连接服务器失败", Toast.LENGTH_SHORT);
            }
        });
    }
    /**
     * 获取区
     */
    public void getDisrict(String cityName){
        map.put("curuserid","13570401327");
        map.put("citylevel","4");
        map.put("city_name",cityName);
        Call<GetCityInfo> call = netApi.getCity(map);
        call.enqueue(new Callback<GetCityInfo>() {
            @Override
            public void onResponse(Response<GetCityInfo> response, Retrofit retrofit) {
                map.clear();
                Log.i("Log","isSuccess():"+response.isSuccess());
                if (response.isSuccess()){
                    GetCityInfo body = response.body();
                    Log.i("Log","body.getRes()():"+body.getRes());
                    if ("1".equals(body.getRes())){
                        getView().setDatatoSpDisrict(body.getData());
                        Log.i("Log","getdritct获取区数据成功:"+body.getData());
                        getView().dimissProgressDialog();
                    }
                }
            }
            @Override
            public void onFailure(Throwable t) {
                map.clear();
                getView().dimissProgressDialog();
                getView().finishActivity();
                getView().showToast("连接服务器失败", Toast.LENGTH_SHORT);
            }
        });
    }
    /**
     * 获取单品或者全系
     */
    public void getset(String curuserid){
        Call<GetSetInfo> call = netApi.getSet(curuserid);
        getView().showProgressDialog("请求服务器...");
        call.enqueue(new Callback<GetSetInfo>() {
           @Override
           public void onResponse(Response<GetSetInfo> response, Retrofit retrofit) {
               Log.i("log","getSet.onResponse:"+response.code());
               if (response.isSuccess()){
                   GetSetInfo body = response.body();
                   if ("1".equals(body.getRes())){
                       getView().dimissProgressDialog();
                       ArrayList<GetSetInfo.data> data = body.getData();
                       Log.i("Log","getset获取数据成功:"+data.toString());
                       getView().setDataToSpRank(data);
                   }else {
                       getView().dimissProgressDialog();
                       getView().finishActivity();
                       getView().showToast("服务器出错",Toast.LENGTH_SHORT);
                   }
               }else {
                   getView().dimissProgressDialog();
                   getView().finishActivity();
                   getView().showToast("服务器出错",Toast.LENGTH_SHORT);
               }
           }

           @Override
           public void onFailure(Throwable t) {
               getView().dimissProgressDialog();
               getView().finishActivity();
               getView().showToast("连接服务器失败", Toast.LENGTH_SHORT);
           }
       });
    }

    /**
     * 获取代理商信息
     */
    public void getAgentInfo(final String...strings){
        map.put("curuserid",strings[0]);
        map.put("phone",strings[1]);
        Call<GetAgentInfo> call = netApi.getAgentInfo(map);
        call.enqueue(new Callback<GetAgentInfo>() {
            @Override
            public void onResponse(Response<GetAgentInfo> response, Retrofit retrofit) {
                map.clear();
                if (response.isSuccess()){
                    GetAgentInfo agentInfo = response.body();
                    if ("1".equals(agentInfo.getRes())){
                        String agentname = agentInfo.getData().get(0).getAgentname();
                        Log.i("Log", "onResponse: "+agentInfo.getRes()+";msg:"+agentInfo.getMeg()+";data:"+agentInfo.getData().toString());
                        if ("1".equals(strings[2]))
                            if (agentname!=null){
                                getView().setTextToAgentname(agentname);
                            }else getView().setTextToAgentname("");
                        if ("2".equals(strings[2]))
                            getView().setTextTorefman(agentname);
                    }
                    if ("-1".equals(agentInfo.getRes())){

                        if ("1".equals(strings[2])) {
                            getView().setEnableForEditAgentName(true);
                        }
                        if ("2".equals(strings[2])) {
                            getView().showToast("推荐人号码不存在", Toast.LENGTH_SHORT);
                            getView().setTextTorefman(null);
                        }
                    }

                }
            }

            @Override
            public void onFailure(Throwable t) {
                getView().dimissProgressDialog();
                getView().showToast("连接服务器失败", Toast.LENGTH_SHORT);
            }
        });
    }
    /**
     * 获取代理商级别
     */
    public void getlevel(String curuserid){
        Call<GetLevelInfo> level = netApi.getLevel(curuserid);
        level.enqueue(new Callback<GetLevelInfo>() {
            @Override
            public void onResponse(Response<GetLevelInfo> response, Retrofit retrofit) {
                if (response.isSuccess()){
                    GetLevelInfo body = response.body();
                    if ("1".equals(body.getRes())){

                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
