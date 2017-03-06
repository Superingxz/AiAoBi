package xinlan.com.AiAoBi.home.homeFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import uk.co.senab.photoview.PhotoView;
import xinlan.com.AiAoBi.BaseActivty;
import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.entity.BaseproduceInfo;
import xinlan.com.AiAoBi.home.enity.BaseModel;
import xinlan.com.AiAoBi.view.TitileView;

/**
 * Created by Administrator on 2016/12/26.
 */
public class ActivityFeedbackDetails extends BaseActivty {
    @BindView(R.id.activity_feedback_details_title)
    TitileView activityFeedbackDetailsTitle;
    @BindView(R.id.feedback_details_title)
    TextView feedbackDetailsTitle;
    @BindView(R.id.feedback_details_iv)
    PhotoView feedbackDetailsIv;
    @BindView(R.id.feedback_details_details)
    TextView feedbackDetailsDetails;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_details);
        ButterKnife.bind(this);
        initEvent();

    }

    private void initEvent() {
        activityFeedbackDetailsTitle.setTitle("产品详情");
        String mkeyid = getIntent().getStringExtra("mkeyid");
        baseproduce(mkeyid);

    }

    private void baseproduce(String mkeyid) {
        showProgressDialog("获取数据中...");
        Call<BaseModel<BaseproduceInfo>> call = netApi.baseproduce("", mkeyid);
        call.enqueue(new Callback<BaseModel<BaseproduceInfo>>() {
            @Override
            public void onResponse(Response<BaseModel<BaseproduceInfo>> response, Retrofit retrofit) {
                dimissProgressDialog();
                if (response.isSuccess()) {
                    BaseModel<BaseproduceInfo> body = response.body();
                    if ("1".equals(body.getRes())) {
                        BaseproduceInfo baseproduceInfo = body.getData().get(0);
                        feedbackDetailsTitle.setText(baseproduceInfo.getTitle());
                        feedbackDetailsDetails.setText(baseproduceInfo.getRemrak());
                        Glide.with(ActivityFeedbackDetails.this).load(baseproduceInfo.getUrl())
                                .error(ContextCompat.getDrawable(ActivityFeedbackDetails.this,R.mipmap.error_100))
                                .into(feedbackDetailsIv);

                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                dimissProgressDialog();
                showToast("连接服务器失败！");
            }
        });
    }
}
