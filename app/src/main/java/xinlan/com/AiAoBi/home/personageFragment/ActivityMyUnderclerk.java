package xinlan.com.AiAoBi.home.personageFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import xinlan.com.AiAoBi.BaseActivty;
import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.adapter.MyUnderclerkAdapter;
import xinlan.com.AiAoBi.adapter.RecAdapter;
import xinlan.com.AiAoBi.entity.GetagentdownInfo;
import xinlan.com.AiAoBi.home.enity.BaseModel;
import xinlan.com.AiAoBi.home.enity.MyUnderclerkInfo;
import xinlan.com.AiAoBi.view.TitileView;

/**
 * Created by Administrator on 2016/11/22.
 */
public class ActivityMyUnderclerk extends BaseActivty {
    @BindView(R.id.my_underclerl_title)
    TitileView title;
    @BindView(R.id.my_underclerk_recview)
    ListView recview;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_underclerk);
    }
    private List<GetagentdownInfo> list;
    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        title.setTitle("我的下级");
        initEvent();
        getagentdown(user.getAgentid());
    }

    private void initEvent() {
        list=new ArrayList<>();

    }

    private AdapterView.OnItemClickListener onItemClickListener=new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent=new Intent(ActivityMyUnderclerk.this,ActivityMyUnderclerkInfo.class);
            intent.putExtra("getagentdownInfo",list.get(i));
            startActivity(intent);
        }
    };
    private void getagentdown(String agentid) {
        showProgressDialog("获取数据中");
        Call<BaseModel<GetagentdownInfo>> call = netApi.getagentdown(agentid);
        call.enqueue(new Callback<BaseModel<GetagentdownInfo>>() {
            @Override
            public void onResponse(Response<BaseModel<GetagentdownInfo>> response, Retrofit retrofit) {
                dimissProgressDialog();
                if (response.isSuccess()){
                    BaseModel<GetagentdownInfo> body = response.body();
                    if ("1".equals(body.getRes())){
                        //获取数据成功
                        list=body.getData();
                        MyUnderclerkAdapter adapter=new MyUnderclerkAdapter(ActivityMyUnderclerk.this,R.layout.rec_adapter,body.getData());
                        recview.setAdapter(adapter);
                        recview.setOnItemClickListener(onItemClickListener);
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                dimissProgressDialog();
                showToast("连接服务器失败");
            }
        });
    }
}
