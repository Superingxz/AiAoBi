package xinlan.com.AiAoBi.adapter;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.ArrayList;
import java.util.List;

import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.entity.DoselagentbInfo;

/**
 * Created by Administrator on 2016/11/16.
 */
public class DoselagentaInfoAdapter extends CommonAdapter<DoselagentbInfo> {
    public DoselagentaInfoAdapter(Context context, int itemLayoutId, List<DoselagentbInfo> mDatas) {
        super(context, itemLayoutId, mDatas);
    }
    @Override
    public void convert(ViewHolder helper, final DoselagentbInfo item) {
        helper.setText(R.id.undercler_down_wait_details_number,helper.getPosition()+1+"");
        helper.setText(R.id.undercler_down_wait_details_price,item.getCostprice());
        helper.setText(R.id.undercler_down_wait_details_sum,item.getTotalnum());
        helper.setText(R.id.undercler_down_wait_details_money,Float.parseFloat(item.getCostprice())*Float.parseFloat(item.getTotalnum())+"");
        helper.setText(R.id.undercler_down_wait_details_name,item.getGoodsname()+"/"+item.getUnitname());
        CheckBox checkBox= helper.getView(R.id.undercler_down_wait_details_checkbox);
        if (item.isCheck()) checkBox.setChecked(true);
        else checkBox.setChecked(false);
    }
}
