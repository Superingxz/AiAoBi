package xinlan.com.AiAoBi.adapter;

import android.content.Context;

import java.util.List;

import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.entity.GettotalInfo;

/**
 * Created by Administrator on 2017/2/10.
 */
public class AdapterMyPerformanc extends CommonAdapter<GettotalInfo>{
    public AdapterMyPerformanc(Context context, int itemLayoutId, List<GettotalInfo> mDatas) {
        super(context, itemLayoutId, mDatas);
    }

    @Override
    public void convert(ViewHolder helper, GettotalInfo item) {
        helper.setText(R.id.position,helper.getPosition()+1+"");
        helper.setText(R.id.number,item.getMasterid());
        helper.setText(R.id.name,item.getGoodsname());
        helper.setText(R.id.counts,item.getAlreadynum());
        helper.setText(R.id.money,item.getAcceptmoney());
        helper.setText(R.id.performance,item.getTotalmoney());
    }
}
