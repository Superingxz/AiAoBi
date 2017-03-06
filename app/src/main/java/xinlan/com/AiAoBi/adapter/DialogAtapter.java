package xinlan.com.AiAoBi.adapter;

import android.content.Context;

import java.util.List;

import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.entity.DoselagentkInfo;

/**
 * Created by Administrator on 2017/2/10.
 */
public class DialogAtapter extends CommonAdapter<DoselagentkInfo>{
    public DialogAtapter(Context context, int itemLayoutId, List<DoselagentkInfo> mDatas) {
        super(context, itemLayoutId, mDatas);
    }

    @Override
    public void convert(ViewHolder helper, DoselagentkInfo item) {
        if (item.getGoodsid().isEmpty()){
            helper.setText(R.id.dialog_name,"全系起订量为"+item.getMinnum());
            return;
        }
        helper.setText(R.id.dialog_position,helper.getPosition()+1+".");
        helper.setText(R.id.dialog_name,item.getGoodsname());
        helper.setText(R.id.dialog_counts,"少"+item.getMinnum()+item.getUnitname());
    }
}
