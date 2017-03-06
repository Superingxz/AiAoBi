package xinlan.com.AiAoBi.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import xinlan.com.AiAoBi.App;
import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.UserInfo;
import xinlan.com.AiAoBi.entity.GetagentpriceInfo;

/**
 * Created by Administrator on 2016/12/2.
 */
public class MypriceAdapter extends CommonAdapter<GetagentpriceInfo>{
    private UserInfo user;
    public MypriceAdapter(Context context, int itemLayoutId, List<GetagentpriceInfo> mDatas) {
        super(context, itemLayoutId, mDatas);
        user= App.getApp().getUserInfo();
    }

    @Override
    public void convert(ViewHolder helper, final GetagentpriceInfo item) {
        TextView to1=helper.getView(R.id.adapter_my_price_to1);
        TextView more=helper.getView(R.id.adapter_my_price_more);
        helper.setText(R.id.adapter_my_price_num,helper.getPosition()+1+"");
        helper.setText(R.id.my_price_name,item.getGoodsname()+"/"+item.getGoodssize());
        helper.setText(R.id.adapter_my_price_more,"更多");
        switch (user.getSlevel()){
            case "1":
                helper.setText(R.id.adapter_my_price_get,item.getPrice1());
                helper.setText(R.id.adapter_my_price_to1,item.getPrice2());
                break;
            case "2":
                helper.setText(R.id.adapter_my_price_get,item.getPrice2());
                helper.setText(R.id.adapter_my_price_to1,item.getPrice3());
                break;
            case "3":
                helper.setText(R.id.adapter_my_price_get,item.getPrice3());
                helper.setText(R.id.adapter_my_price_to1,item.getPrice4());
                break;
            case "4":
                helper.setText(R.id.adapter_my_price_get,item.getPrice4());
                helper.setText(R.id.adapter_my_price_to1,item.getPrice5());
                break;
            case "5":
                helper.setText(R.id.adapter_my_price_get,item.getPrice5());
                helper.setText(R.id.adapter_my_price_to1,item.getPrice6());
                break;
            case "6":
                helper.setText(R.id.adapter_my_price_get,item.getPrice6());
                helper.setText(R.id.adapter_my_price_to1,item.getPrice7());
                more.setVisibility(View.GONE);
                break;
            case "7":
                helper.setText(R.id.adapter_my_price_get,item.getPrice7());
                to1.setVisibility(View.GONE);
                more.setVisibility(View.GONE);
                break;
        }
    }
}
