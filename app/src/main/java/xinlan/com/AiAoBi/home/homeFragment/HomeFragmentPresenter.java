package xinlan.com.AiAoBi.home.homeFragment;

import android.view.View;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import xinlan.com.AiAoBi.App;
import xinlan.com.AiAoBi.NetApi;
import xinlan.com.AiAoBi.NetCilent;
import xinlan.com.AiAoBi.UserInfo;
import xinlan.com.AiAoBi.entity.BasenoteInfo;
import xinlan.com.AiAoBi.entity.BaseproduceInfo;
import xinlan.com.AiAoBi.entity.BasesaleInfo;
import xinlan.com.AiAoBi.entity.BasesaleaxisInfo;
import xinlan.com.AiAoBi.home.enity.BaseModel;

/**
 * Created by Administrator on 2016/9/22.
 */
public class HomeFragmentPresenter extends MvpNullObjectBasePresenter<HomeFragmentView> {
    private NetApi netApi;
    private UserInfo userInfo;
    public HomeFragmentPresenter(){
        netApi= NetCilent.getInstance().getNetApi();
        userInfo=App.getApp().getUserInfo();
    }
    /**
     * 8_2代理商比拼一览表
     */
    public void basesale (){
        Call<BaseModel<BasesaleInfo>> call = netApi.basesale(userInfo.getAgentid());
        call.enqueue(new Callback<BaseModel<BasesaleInfo>>() {
            @Override
            public void onResponse(Response<BaseModel<BasesaleInfo>> response, Retrofit retrofit) {
                if (response.isSuccess()){
                    BaseModel<BasesaleInfo> body = response.body();
                    if ("1".equals(body.getRes())){
                        getView().setDataToRanking(body.getData());
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                getView().showToast("连接服务器失败！");
            }
        });

    }

    /**
     * 8_1官方公告
     */
    public void basenote(final String ismore){
        Call<BaseModel<BasenoteInfo>> call = netApi.basenote(ismore);
        call.enqueue(new Callback<BaseModel<BasenoteInfo>>() {
            @Override
            public void onResponse(Response<BaseModel<BasenoteInfo>> response, Retrofit retrofit) {
                if (response.isSuccess()){
                    BaseModel<BasenoteInfo> body = response.body();
                    if ("1".equals(body.getRes())){
                        if ("0".equals(ismore)){
                            //首页数据
                            getView().setDataToNotice(body.getData());
                        }
                        if ("1".equals(ismore)){
                            //更多数据

                        }
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                getView().showToast("连接服务器失败！");
            }
        });
    }
    /**
     * 8_3产品反馈产品海报
     */
    public void baseproduce(String ismore){
        Call<BaseModel<BaseproduceInfo>> call = netApi.baseproduce(ismore,null);
        call.enqueue(new Callback<BaseModel<BaseproduceInfo>>() {
            @Override
            public void onResponse(Response<BaseModel<BaseproduceInfo>> response, Retrofit retrofit) {
                if (response.isSuccess()){
                    BaseModel<BaseproduceInfo> body = response.body();
                    if ("1".equals(body.getRes())){
                        getView().setFeedBackVis(View.VISIBLE);
                        getView().setDataToFeedback(body.getData());
                        getView().setRefreshing(false);
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                getView().setRefreshing(false);
                getView().showToast("连接服务器失败！");
            }
        });
    }
    public void basesaleaxis(){
        Call<BaseModel<BasesaleaxisInfo>> call = netApi.basesaleaxis(userInfo.getAgentid());
        call.enqueue(new Callback<BaseModel<BasesaleaxisInfo>>() {
            @Override
            public void onResponse(Response<BaseModel<BasesaleaxisInfo>> response, Retrofit retrofit) {
                if (response.isSuccess()){
                    BaseModel<BasesaleaxisInfo> body = response.body();
                    if ("1".equals(body.getRes())){
                        getView().setDataToRankingb(body.getData());
                    }else getView().showToast(body.getMsg());
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
    public void basecursale(){
        Call<BaseModel<BasesaleInfo>> call = netApi.basecursale(userInfo.getAgentid(), userInfo.getCurparentid());
        call.enqueue(new Callback<BaseModel<BasesaleInfo>>() {
            @Override
            public void onResponse(Response<BaseModel<BasesaleInfo>> response, Retrofit retrofit) {
                if (response.isSuccess()){
                    BaseModel<BasesaleInfo> body = response.body();
                    if ("1".equals(body.getRes())){
                        getView().setDataToRankingc(body.getData());
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
