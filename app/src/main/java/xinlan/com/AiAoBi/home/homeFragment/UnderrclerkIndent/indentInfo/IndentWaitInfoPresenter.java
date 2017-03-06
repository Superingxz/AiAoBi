package xinlan.com.AiAoBi.home.homeFragment.UnderrclerkIndent.indentInfo;

import android.view.View;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import xinlan.com.AiAoBi.App;
import xinlan.com.AiAoBi.NetApi;
import xinlan.com.AiAoBi.NetCilent;
import xinlan.com.AiAoBi.entity.DoselagentbInfo;
import xinlan.com.AiAoBi.home.enity.BaseModel;

/**
 * Created by Administrator on 2016/11/11.
 */
public class IndentWaitInfoPresenter extends MvpNullObjectBasePresenter<IndentWaitInfoView>{
    NetApi netApi;
    Map<String,String> map;
    public IndentWaitInfoPresenter(){
        netApi= NetCilent.getInstance().getNetApi();
        map=new HashMap<>();
    }
    /**
     * 获取代理订单详情
     */
    public void getDoselagentbInfo(String skeyId,String agentid){
        map.put("skeyid",skeyId);
        map.put("agentid",agentid);
        getView().showProgressDialog("获取数据中...");
        Call<BaseModel<DoselagentbInfo>> call = netApi.doselagentb(map);
        call.enqueue(new Callback<BaseModel<DoselagentbInfo>>() {
            @Override
            public void onResponse(Response<BaseModel<DoselagentbInfo>> response, Retrofit retrofit) {
                map.clear();
                getView().dismissProgressDiolog();
                if (response.isSuccess()){
                    BaseModel<DoselagentbInfo> body = response.body();
                    if ("1".equals(body.getRes())){
                        List<DoselagentbInfo> list = body.getData();
                        getView().setDataToActivity(list);
                        getView().setVisbely(View.VISIBLE,View.GONE);
                        //获取数据成功
                    }else if ("-1".equals(body.getRes())){
                        //没有订单
                        getView().setVisbely(View.GONE,View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                map.clear();
                getView().dismissProgressDiolog();
                getView().showToast("连接服务器失败", Toast.LENGTH_SHORT);
            }
        });
    }
    /**
     * 所选产品同意代发
     */
    public void checkGoodsToCommit(Map<String,String> map){
        Call<BaseModel> call = netApi.doselagentc(map);
        getView().showProgressDialog("正在提交...");
        call.enqueue(new Callback<BaseModel>() {
            @Override
            public void onResponse(Response<BaseModel> response, Retrofit retrofit) {
                getView().dismissProgressDiolog();
                if (response.isSuccess()){
                    BaseModel body = response.body();
                    if ("1".equals(body.getRes())){
                        getView().showMsgDialog(body.getMsg());
                        //通知下级代发订单详情刷新
                        App.getApp().getHandler().sendEmptyMessage(0x118);
                        App.getApp().getHandler2().sendEmptyMessage(0x116);
                        App.getApp().getHandler7().sendEmptyMessage(0x116);
                    }
                    if ("-1".equals(body.getRes())){
                        getView().showMsgDialog(body.getMsg());
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                getView().dismissProgressDiolog();
                getView().showToast("连接服务器失败",Toast.LENGTH_SHORT);
            }
        });
    }
}
