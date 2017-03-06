package xinlan.com.AiAoBi.adapter;

import android.content.Context;

import java.util.List;

import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.entity.GetrewardInfo;

/**
 * Created by Administrator on 2017/1/18.
 */
public class AdapterMyAward extends CommonAdapter<GetrewardInfo>{
    public AdapterMyAward(Context context, int itemLayoutId, List<GetrewardInfo> mDatas) {
        super(context, itemLayoutId, mDatas);
    }

    @Override
    public void convert(ViewHolder helper, GetrewardInfo item) {
        if (helper.getPosition() % 2 == 0) {
            helper.getConvertView().setBackgroundResource(R.drawable.shape_listview_check);
        } else {
            helper.getConvertView().setBackgroundResource(R.drawable.shape_listview3);
        }
        helper.setText(R.id.award_num,helper.getPosition()+1+"");
        helper.setText(R.id.award_time,item.getOptdate());
        helper.setText(R.id.award_type,item.getChangetype());
        helper.setText(R.id.award_money,item.getAmt());
        helper.setText(R.id.award_remark,item.getRemark());
    }
}
