package xinlan.com.AiAoBi.adapter;

import android.content.Context;

import java.util.List;

import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.entity.GetagentdownaInfo;

/**
 * Created by Administrator on 2016/12/26.
 */
public class AdapterRecentResults extends CommonAdapter<GetagentdownaInfo>{
    public AdapterRecentResults(Context context, int itemLayoutId, List<GetagentdownaInfo> mDatas) {
        super(context, itemLayoutId, mDatas);
    }

    @Override
    public void convert(ViewHolder helper, GetagentdownaInfo item) {
        helper.setText(R.id.adapter_recent_result_num,helper.getPosition()+1+"");
        helper.setText(R.id.adapter_recent_result_date,item.getIndate());
        helper.setText(R.id.adapter_recent_result_order_num,item.getNum());
        helper.setText(R.id.adapter_recent_result_money,item.getMoney());
    }
}
