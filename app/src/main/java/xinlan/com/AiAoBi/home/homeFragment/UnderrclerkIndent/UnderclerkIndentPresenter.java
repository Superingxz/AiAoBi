package xinlan.com.AiAoBi.home.homeFragment.UnderrclerkIndent;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

import java.util.HashMap;
import java.util.Map;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import xinlan.com.AiAoBi.BaseActivty;
import xinlan.com.AiAoBi.NetApi;
import xinlan.com.AiAoBi.NetCilent;
import xinlan.com.AiAoBi.entity.DoselagentkInfo;
import xinlan.com.AiAoBi.entity.DoselagentqInfo;
import xinlan.com.AiAoBi.entity.DoselbargainInfo;
import xinlan.com.AiAoBi.home.enity.BaseModel;
import xinlan.com.AiAoBi.home.homeFragment.AddAgainNumActivity;

/**
 * Created by Administrator on 2016/11/4.
 */
public class UnderclerkIndentPresenter extends MvpNullObjectBasePresenter<UnderclerkIndentView> {
    private NetApi netApi;
    private Map<String,String> map;
    public UnderclerkIndentPresenter(){
        netApi= NetCilent.getInstance().getNetApi();
        map=new HashMap<>();
    }
    /**
     * 获取直属下级的订单
     */
    public void doselbargain(String agentid){
        getView().showProgressDialog("获取数据中...");
        map.put("agentid",agentid);
        Call<BaseModel<DoselbargainInfo>> call = netApi.doselbargain(map);
        call.enqueue(new Callback<BaseModel<DoselbargainInfo>>() {
            @Override
            public void onResponse(Response<BaseModel<DoselbargainInfo>> response, Retrofit retrofit) {
                getView().dismissProgressDiolog();
                if (response.isSuccess()){
                    BaseModel<DoselbargainInfo> body = response.body();
                    if ("1".equals(body.getRes())){
                        getView().setDataToActivity(body.getData());
                        getView().setVisbely(View.VISIBLE,View.GONE);
                        if(body.getData().size()==0){
                            getView().setVisbely(View.GONE,View.VISIBLE);
                        }
                    }else {
                        getView().setVisbely(View.GONE,View.VISIBLE);
                        getView().setAlldisposedLvVisbely(View.GONE,View.VISIBLE);
                        getView().setBtnComitText("提交订单（代发：0，自收：0）");
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                getView().setVisbely(View.GONE,View.VISIBLE);
                getView().showToast("连接服务器失败！", Toast.LENGTH_SHORT);
                getView().dismissProgressDiolog();
            }
        });
    }

    /**
     * 获取处理下级订单汇总
     */
    public void doselagentq(String agentid,String smkeyid){
        map.put("agentid",agentid);
        map.put("smkeyid",smkeyid);
        Call<BaseModel<DoselagentqInfo>> call = netApi.doselagentq(map);
        call.enqueue(new Callback<BaseModel<DoselagentqInfo>>() {
            @Override
            public void onResponse(Response<BaseModel<DoselagentqInfo>> response, Retrofit retrofit) {
                getView().dismissProgressDiolog();
                if (response.isSuccess()){
                    BaseModel<DoselagentqInfo> body = response.body();
                    if ("1".equals(body.getRes())){
                        //获取数据成功
                        body.getData();
                        getView().setAlldisposedLvVisbely(View.VISIBLE,View.GONE);
                        Log.i("list", "onResponse: setDataToAlldLv");
                        getView().setDataToAlldLv(body.getData());
                    }else {
                        getView().setAlldisposedLvVisbely(View.GONE,View.VISIBLE);
                        getView().showToast(body.getMsg(),Toast.LENGTH_SHORT);
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                getView().dismissProgressDiolog();
                getView().setAlldisposedLvVisbely(View.GONE,View.VISIBLE);
                getView().showToast("连接服务器失败",Toast.LENGTH_SHORT);
            }
        });
    }
    /**
     * 3_2_12处理下级订单(提交订单)
     */
    public void doselagentk(String agentid,String slevel){
        getView().showProgressDialog("正在提交订单");
        map.put("agentid",agentid);
        map.put("slevel",slevel);
        Call<BaseModel<DoselagentkInfo>> call = netApi.doselagentk(map);
        call.enqueue(new Callback<BaseModel<DoselagentkInfo>>() {
            @Override
            public void onResponse(Response<BaseModel<DoselagentkInfo>> response, Retrofit retrofit) {
                getView().dismissProgressDiolog();
                if (response.isSuccess()){
                    BaseModel body = response.body();
                    if ("1".equals(body.getRes())){
                        getView().showMsgDialog(body.getMsg());
                        getView().notifyDataChange();
                    }
                   else if ("-2".equals(body.getRes())){//起订量提醒

                       getView().showDiyDialog("您本次订货的产品中有不足起订数，具体如下：\n",
                               "是，直接下单", "我马上再加点", body.getData(), new UnderclerkIndentActivity.Mcallback() {

                                   @Override
                                   public void okDoSomething() {
                                       Call<BaseModel> doselagentw = netApi.doselagentw(map);
                                       getView().showProgressDialog("正在提交订单");
                                       doselagentw.enqueue(new Callback<BaseModel>() {
                                           @Override
                                           public void onResponse(Response<BaseModel> response, Retrofit retrofit) {
                                               if (response.isSuccess()){
                                                   getView().dismissProgressDiolog();
                                                   getView().showMsgDialog(response.body().getMsg());
                                                   getView().notifyDataChange();
                                               }
                                           }

                                           @Override
                                           public void onFailure(Throwable t) {
                                               getView().dismissProgressDiolog();
                                               getView().showToast(t.toString(),Toast.LENGTH_SHORT);
                                           }
                                       });
                                   }

                                   @Override
                                   public void cancleDoSomething() {
                                       getView().intentToActivity(AddAgainNumActivity.class);
                                   }
                               });

                    }else getView().showMsgDialog(body.getMsg());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                getView().dismissProgressDiolog();
                getView().showToast(t.toString(),Toast.LENGTH_SHORT);
            }
        });
    }
}
