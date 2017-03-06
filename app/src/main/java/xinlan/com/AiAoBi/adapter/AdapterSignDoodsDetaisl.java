package xinlan.com.AiAoBi.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import xinlan.com.AiAoBi.App;
import xinlan.com.AiAoBi.NetApi;
import xinlan.com.AiAoBi.NetCilent;
import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.UserInfo;
import xinlan.com.AiAoBi.entity.AMData;
import xinlan.com.AiAoBi.entity.Ddata;
import xinlan.com.AiAoBi.entity.Mdata;
import xinlan.com.AiAoBi.home.enity.BaseModel;

/**
 * Created by Administrator on 2016/12/3.
 */
public class AdapterSignDoodsDetaisl extends CommonAdapter<AMData> {

    private NetApi netApi;
    private UserInfo userInfo;
    List<View> views=new ArrayList<>();
    public AdapterSignDoodsDetaisl(Context context, int itemLayoutId, List<AMData> mDatas) {
        super(context, itemLayoutId, mDatas);
        netApi=NetCilent.getInstance().getNetApi();
        userInfo=App.getApp().getUserInfo();
    }
    @Override
    public void convert(ViewHolder helper, AMData item) {
        LinearLayout linear=helper.getView(R.id.linear);
        TextView tvmsg=helper.getView(R.id.tvmsg);
        if (item.getMdata()==null){
            linear.setVisibility(View.GONE);
            tvmsg.setVisibility(View.VISIBLE);
            return;
        }else if (item.getMdata().size()==0){
            linear.setVisibility(View.GONE);
            tvmsg.setVisibility(View.VISIBLE);
            return;
        }
        Mdata mdata = item.getMdata().get(0);
        List<Ddata> dDatas=item.getDdata();
        helper.setText(R.id.adapter_sign_goods_details_number,mdata.getMasterid());
        helper.setText(R.id.adapter_sign_goods_details_manname,mdata.getOutagent_name());
        helper.setText(R.id.adapter_sign_goods_details_state,mdata.getIs_get());
        final View convertView = helper.getConvertView();
        TextView timehas=helper.getView(R.id.adapter_sign_goods_details_timehas);
        TextView time=helper.getView(R.id.adapter_sign_goods_details_time);
        Button signOk=helper.getView(R.id.adapter_sign_goods_details_signOk);
        TextView state = helper.getView(R.id.adapter_sign_goods_details_state);
        if ("1".equals(mdata.getBuillstatus())){
            //已签收
            timehas.setVisibility(View.VISIBLE);
            time.setVisibility(View.VISIBLE);
            signOk.setVisibility(View.GONE);
        }if ("0".equals(mdata.getBuillstatus())){
            state.setTextColor(Color.RED);
            timehas.setVisibility(View.GONE);
            time.setVisibility(View.GONE);
            signOk.setVisibility(View.VISIBLE);
            signOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    okacceptgoods(convertView);
                }
            });
            convertView.setTag(R.id.tag_second,helper.getPosition());
        }
        helper.setText(R.id.adapter_sign_goods_details_timehas,mdata.getIndate());
        ListView lv=helper.getView(R.id.adapter_sign_goods_details_lv);
        AdapterSignDoodsDetaislLv adapter=new AdapterSignDoodsDetaislLv(mContext,R.layout.adapter_sign_goods_details_lv,dDatas);

        View view=LayoutInflater.from(mContext).inflate(R.layout.adapter_sign_goods_details_lv_foot_view,null);
        views.add(view);
        TextView num= (TextView) view.findViewById(R.id.sign_goods_details_lv_num);
        TextView name= (TextView) view.findViewById(R.id.sign_goods_details_lv_name);
        TextView orderNum= (TextView) view.findViewById(R.id.sign_goods_details_lv_ordernum);
        TextView deliverNum= (TextView) view.findViewById(R.id.sign_goods_details_lv_delivernum);
        if (lv.getFooterViewsCount()>0){
            for (int i=0;i<views.size();i++){
                lv.removeFooterView(views.get(i));
            }
        }
        Log.i(TAG, "convert:footview个数： "+lv.getFooterViewsCount());
        lv.addFooterView(views.get(views.size()-1));
        name.setText("小计");
        num.setText("");
        orderNum.setText(calculateNums(dDatas)+"");
        deliverNum.setText(calculateAlreadynum(dDatas)+"");
        lv.setAdapter(adapter);
    }

    private static final String TAG = "SignDoodsDetaisl";
    private void okacceptgoods(final View view) {
        showProgressDialog("提交中...");
        Call<BaseModel> call = netApi.okacceptgoods(mDatas.get((int)view.getTag(R.id.tag_second)).getMdata().get(0).getSmkeyid(),
                userInfo.getAgentname());
        Log.i(TAG, "okacceptgoods: "+mDatas.get((int)view.getTag(R.id.tag_second)));
        call.enqueue(new Callback<BaseModel>() {
            @Override
            public void onResponse(Response<BaseModel> response, Retrofit retrofit) {
                dimissProgressDialog();
                if (response.isSuccess()){
                    BaseModel body = response.body();
                    showMsgDialog(body.getMsg());
                    if ("1".equals(body.getRes())) {
                        //签收成功，更布局
                        TextView timehas= (TextView) view.findViewById(R.id.adapter_sign_goods_details_timehas);
                        TextView time=(TextView) view.findViewById(R.id.adapter_sign_goods_details_time);
                        Button signOk=(Button) view.findViewById(R.id.adapter_sign_goods_details_signOk);
                        TextView state = (TextView) view.findViewById(R.id.adapter_sign_goods_details_state);
                        state.setText("已签收");
                        timehas.setVisibility(View.VISIBLE);
                        time.setVisibility(View.VISIBLE);
                        signOk.setVisibility(View.GONE);
                        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date date=new Date(System.currentTimeMillis());
                        String str = sDateFormat.format(date);
                        timehas.setText(str);
                        mDatas.get((int)view.getTag(R.id.tag_second)).getMdata().get(0).setBuillstatus("1");
                        mDatas.get((int)view.getTag(R.id.tag_second)).getMdata().get(0).setIndate(str);
                        //通知刷新货品签收页面的列表
                        Handler handler=App.getApp().getHandler();
                        if (handler!=null){
                            handler.sendEmptyMessage(0x121);
                        }
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

    private int calculateNums(List<Ddata> ddatas){
        int nums=0;
        for (Ddata data:
                ddatas) {
            if (data.getNum()!=null&&!"".equals(data.getNum())){

                nums+=Integer.parseInt(data.getNum());
            }
        }
        Log.i(TAG, "calculateNums: "+nums);
        return nums;
    }

    private int calculateAlreadynum(List<Ddata> ddatas){
        int alreadynum=0;
        for (Ddata data:
                ddatas) {
            if (data.getAlreadynum()!=null&&!"".equals(data.getAlreadynum())){

                alreadynum+=Integer.parseInt(data.getAlreadynum());
            }
        }
        return alreadynum;
    }
}
