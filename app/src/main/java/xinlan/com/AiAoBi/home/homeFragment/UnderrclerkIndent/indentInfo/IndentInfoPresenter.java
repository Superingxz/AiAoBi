package xinlan.com.AiAoBi.home.homeFragment.UnderrclerkIndent.indentInfo;

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
import xinlan.com.AiAoBi.NetApi;
import xinlan.com.AiAoBi.NetCilent;
import xinlan.com.AiAoBi.entity.DoselagentaInfo;
import xinlan.com.AiAoBi.entity.DoselbargaindetInfo;
import xinlan.com.AiAoBi.home.enity.BaseModel;

/**
 * Created by Administrator on 2016/11/4.
 */
public class IndentInfoPresenter extends MvpNullObjectBasePresenter<IndentInfoView> {
    private NetApi netApi;
    private Map<String, String> map;

    public IndentInfoPresenter() {
        netApi = NetCilent.getInstance().getNetApi();
        map = new HashMap<>();
    }

    /**
     * 获取下级订单详情
     *
     * @param skeyid
     */
    public void doselbargaindet(String skeyid, String ordertype, String agentId) {
        map.put("skeyid", skeyid);
        //map.put("skeyid","CE02F726-ACA9-4FBA-B013-CEB897A40C6D");
        map.put("ordertype", ordertype);
        map.put("agentid", agentId);
        Call<BaseModel<DoselbargaindetInfo>> call = netApi.doselbargaindet(map);
        call.enqueue(new Callback<BaseModel<DoselbargaindetInfo>>() {
            @Override
            public void onResponse(Response<BaseModel<DoselbargaindetInfo>> response, Retrofit retrofit) {
                getView().dismissProgressDiolog();
                if (response.isSuccess()) {
                    BaseModel<DoselbargaindetInfo> body = response.body();
                    if ("1".equals(body.getRes())) {
                        //更改布局显示
                        getView().setVisbely(View.VISIBLE, View.GONE);
                        getView().setDataToActivity(body.getData());
                        //获取数据成功
                    }
                    if ("-1".equals(body.getRes())) {
                        //没有订单
                        getView().setVisbely(View.GONE, View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                getView().dismissProgressDiolog();
                getView().setVisbely(View.GONE, View.VISIBLE);
                getView().showToast("连接服务器失败！", Toast.LENGTH_SHORT);
            }
        });
    }

    /**
     * 获取下级代发订单
     *
     * @param skeyid
     */
    public void doselagenta(String skeyid) {
        map.put("skeyid", skeyid);
        Log.i("log", "doselagentab-----------: " + skeyid);
        Call<BaseModel<DoselagentaInfo>> call = netApi.doselagenta(map);
        call.enqueue(new Callback<BaseModel<DoselagentaInfo>>() {
            @Override
            public void onResponse(Response<BaseModel<DoselagentaInfo>> response, Retrofit retrofit) {
                getView().dismissProgressDiolog();
                if (response.isSuccess()) {
                    BaseModel<DoselagentaInfo> body = response.body();
                    if ("1".equals(body.getRes())) {
                        //更改布局显示
                        getView().setWaitVisbely(View.VISIBLE, View.GONE);
                        //获取数据成功
                        getView().setDataToWaitAdapter(body.getData());

                    }
                    if ("-1".equals(body.getRes())) {
                        //没有订单
                        getView().setWaitVisbely(View.GONE, View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                getView().dismissProgressDiolog();
                getView().setWaitVisbely(View.GONE, View.VISIBLE);
                getView().showToast("连接服务器失败！", Toast.LENGTH_SHORT);
            }
        });
    }

}
