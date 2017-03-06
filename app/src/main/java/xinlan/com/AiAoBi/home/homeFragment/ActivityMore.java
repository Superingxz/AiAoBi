package xinlan.com.AiAoBi.home.homeFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import xinlan.com.AiAoBi.BaseActivty;
import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.adapter.AdapterFeedbackLv;
import xinlan.com.AiAoBi.adapter.AdapterNotice;
import xinlan.com.AiAoBi.adapter.AdapterRanking;
import xinlan.com.AiAoBi.entity.BasenoteInfo;
import xinlan.com.AiAoBi.entity.BaseproduceInfo;
import xinlan.com.AiAoBi.entity.BasesaleInfo;
import xinlan.com.AiAoBi.home.enity.BaseModel;
import xinlan.com.AiAoBi.view.TitileView;

/**
 * Created by Administrator on 2016/12/13.
 */
public class ActivityMore extends BaseActivty {
    @BindView(R.id.activity_notice_more_title)
    TitileView activityNoticeMoreTitle;
    @BindView(R.id.notice_more_lv)
    ListView noticeMoreLv;
    @BindView(R.id.notice_more_tvmsg)
    TextView noticeMoreTvmsg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_more);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        initEvent();
    }

    private void initEvent() {
        int flags = getIntent().getFlags();
        switch (flags){
            case 0x101:
                activityNoticeMoreTitle.setTitle("官方公告");
                basenote("1");//获取更多官方公告
                break;
            case 0x102:
                activityNoticeMoreTitle.setTitle("代理商排名");
                noticeMoreLv.setDividerHeight(0);
                basesale(user.getAgentid());//代理商比拼一览表
                break;
            case 0x103:
                activityNoticeMoreTitle.setTitle("产品反馈/海报");
                baseproduce("1");//产品反馈
                break;
        }
    }

    private void basenote(String ismore) {
        showProgressDialog("获取数据中...");
        Call<BaseModel<BasenoteInfo>> call = netApi.basenote(ismore);
        call.enqueue(new Callback<BaseModel<BasenoteInfo>>() {
            @Override
            public void onResponse(Response<BaseModel<BasenoteInfo>> response, Retrofit retrofit) {
                dimissProgressDialog();
                if (response.isSuccess()){
                    final BaseModel<BasenoteInfo> body = response.body();
                    if ("1".equals(body.getRes())){
                        AdapterNotice adapterNoticeMore=new AdapterNotice(ActivityMore.this,
                                R.layout.adapter_notice,body.getData());
                        noticeMoreLv.setAdapter(adapterNoticeMore);
                        noticeMoreLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Intent intent = new Intent(ActivityMore.this, DisplayNotice.class);
                                intent.putExtra("basenoteInfo", body.getData().get(i));
                                startActivity(intent);
                            }
                        });
                    }else {
                        noticeMoreTvmsg.setVisibility(View.VISIBLE);
                        noticeMoreLv.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                showToast("连接服务器失败！");
                dimissProgressDialog();
                noticeMoreTvmsg.setVisibility(View.VISIBLE);
                noticeMoreLv.setVisibility(View.GONE);
            }
        });
    }

    private void basesale(String agentid) {
        showProgressDialog("获取数据中...");
        Call<BaseModel<BasesaleInfo>> call = netApi.basesale(agentid);
        call.enqueue(new Callback<BaseModel<BasesaleInfo>>() {
            @Override
            public void onResponse(Response<BaseModel<BasesaleInfo>> response, Retrofit retrofit) {
                dimissProgressDialog();
                if (response.isSuccess()){
                    BaseModel<BasesaleInfo> body = response.body();
                    if ("1".equals(body.getRes())){
                        AdapterRanking adapterNoticeMore=new AdapterRanking(ActivityMore.this,
                                R.layout.adapter_ranking,body.getData());
                        noticeMoreLv.setAdapter(adapterNoticeMore);
                    }else {
                        noticeMoreTvmsg.setVisibility(View.VISIBLE);
                        noticeMoreLv.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                showToast("连接服务器失败！");
                dimissProgressDialog();
                noticeMoreTvmsg.setVisibility(View.VISIBLE);
                noticeMoreLv.setVisibility(View.GONE);
            }
        });
    }

    private void baseproduce(String ismore) {
        showProgressDialog("获取数据中...");
        Call<BaseModel<BaseproduceInfo>> call = netApi.baseproduce(ismore,null);
        call.enqueue(new Callback<BaseModel<BaseproduceInfo>>() {
            @Override
            public void onResponse(Response<BaseModel<BaseproduceInfo>> response, Retrofit retrofit) {
                dimissProgressDialog();
                if (response.isSuccess()){
                    final BaseModel<BaseproduceInfo> body = response.body();
                    if ("1".equals(body.getRes())){
                        AdapterFeedbackLv adapterNoticeMore=new AdapterFeedbackLv(ActivityMore.this,
                                R.layout.adapter_homefragment_feedback,body.getData());
                        noticeMoreLv.setAdapter(adapterNoticeMore);
                        noticeMoreLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                String mkeyid = body.getData().get(i).getMkeyid();
                                Intent intent=new Intent(ActivityMore.this,ActivityFeedbackDetails.class);
                                intent.putExtra("mkeyid",mkeyid);
                                startActivity(intent);
                            }
                        });
                    }else {
                        noticeMoreTvmsg.setVisibility(View.VISIBLE);
                        noticeMoreLv.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                showToast("连接服务器失败！");
                dimissProgressDialog();
                noticeMoreTvmsg.setVisibility(View.VISIBLE);
                noticeMoreLv.setVisibility(View.GONE);
            }
        });
    }

}
