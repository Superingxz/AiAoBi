package xinlan.com.AiAoBi.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.entity.DoselbargaindetInfo;

/**
 * Created by Administrator on 2016/11/11.
 */
public class IndentInfoAdapter extends CommonAdapter<DoselbargaindetInfo> {
    public IndentInfoAdapter(Context context, int itemLayoutId, List<DoselbargaindetInfo> mDatas) {
        super(context, itemLayoutId, mDatas);
    }
    @Override
    public void convert( ViewHolder helper, DoselbargaindetInfo item) {
        helper.setText(R.id.adapter_undercler_down_number,helper.getPosition()+1+"");
        helper.setText(R.id.adapter_undercler_down_name,item.getGoodsname()+"/"+item.getUnitname());
        helper.setText(R.id.adapter_undercler_down_price,item.getCostprice());
        helper.setText(R.id.adapter_undercler_down_mine,item.getZsnum());
        helper.setText(R.id.adapter_undercler_down_daifa,item.getDfnum());
        helper.setText(R.id.adapter_undercler_down_allgoods,item.getSumnum());
        CheckBox checkBox=helper.getView(R.id.adapter_undercler_down_checkbox);
        if (item.isCheck()) checkBox.setChecked(true);
        else checkBox.setChecked(false);
    }

}
