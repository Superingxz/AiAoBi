package xinlan.com.AiAoBi.adapter;

import android.content.Context;

import java.util.List;

import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.entity.GetagentdownaInfo;

/**
 * Created by Administrator on 2016/12/11.
 */
public class AdapterGetagentdowna extends CommonAdapter<GetagentdownaInfo>{
    public AdapterGetagentdowna(Context context, int itemLayoutId, List<GetagentdownaInfo> mDatas) {
        super(context, itemLayoutId, mDatas);
    }

    @Override
    public void convert(ViewHolder helper, GetagentdownaInfo item) {
        helper.setText(R.id.adapter_my_underclerk_info_num,helper.getPosition()+1+"");
        helper.setText(R.id.adapter_my_underclerk_info_date,item.getIndate());
        helper.setText(R.id.adapter_my_underclerk_info_name,item.getGoodsname()+"/"+item.getGoodssize());
        helper.setText(R.id.adapter_my_underclerk_info_price,item.getCostprice());
        helper.setText(R.id.adapter_my_underclerk_info_counts,item.getNum());
        helper.setText(R.id.adapter_my_underclerk_info_money,item.getMoney());
    }
}
