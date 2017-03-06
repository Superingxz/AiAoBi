package xinlan.com.AiAoBi.adapter;

import android.content.Context;

import java.util.List;

import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.entity.BasesaleInfo;

/**
 * Created by Administrator on 2016/12/11.
 */
public class AdapterRanking extends CommonAdapter<BasesaleInfo>{
    public AdapterRanking(Context context, int itemLayoutId, List<BasesaleInfo> mDatas) {
        super(context, itemLayoutId, mDatas);
    }

    @Override
    public void convert(ViewHolder helper, BasesaleInfo item) {
        if (helper.getPosition() % 2 == 0) {
            helper.getConvertView().setBackgroundResource(R.drawable.shape_listview_check);
        } else {
            helper.getConvertView().setBackgroundResource(R.drawable.shape_listview3);
        }
        helper.setText(R.id.adapter_ranking,helper.getPosition()+1+"");
        helper.setText(R.id.adapter_agent_name,item.getAgentname());
        helper.setText(R.id.adapter_deeds,item.getSalemoney());
        helper.setText(R.id.adapter_teammans,item.getNum());
    }
}
