package xinlan.com.AiAoBi.requestJionIn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import xinlan.com.AiAoBi.BaseActivty;
import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.entity.GetagreeInfo;
import xinlan.com.AiAoBi.home.enity.BaseModel;
import xinlan.com.AiAoBi.view.TitileView;

/**
 * Created by Administrator on 2016/10/31.
 */
public class AgreeMent extends BaseActivty {
    @BindView(R.id.agreement_title)
    TitileView agreementTitle;
    @BindView(R.id.text)
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreement);
        ButterKnife.bind(this);
        agreementTitle.setTitle("注册协议");
        getagree();
    }

    public void getagree() {
        showProgressDialog("获取数据中...");
        Call<BaseModel<GetagreeInfo>> call = netApi.getagree("");
        call.enqueue(new Callback<BaseModel<GetagreeInfo>>() {
            @Override
            public void onResponse(Response<BaseModel<GetagreeInfo>> response, Retrofit retrofit) {
                dimissProgressDialog();
                if (response.isSuccess()){
                    BaseModel<GetagreeInfo> body = response.body();
                    if ("1".equals(body.getRes())){
                        String msg=body.getData().get(0).getData();
                        text.setText("\t"+"\t\t"+msg);
                    }else showToast(body.getMsg());
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
