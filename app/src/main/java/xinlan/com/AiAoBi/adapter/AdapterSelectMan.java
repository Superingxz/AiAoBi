package xinlan.com.AiAoBi.adapter;

import android.content.Context;
import android.util.Log;
import android.widget.CheckBox;

import java.util.List;

import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.entity.ScangetmanInfo;

/**
 * Created by Administrator on 2016/12/5.
 */
public class AdapterSelectMan extends CommonAdapter<ScangetmanInfo> {
    public AdapterSelectMan(Context context, int itemLayoutId, List<ScangetmanInfo> mDatas) {
        super(context, itemLayoutId, mDatas);
    }

    @Override
    public void convert(ViewHolder helper, ScangetmanInfo item) {
        helper.setText(R.id.adapter_check_my_customer_num,helper.getPosition()+1+"");
        helper.setText(R.id.adapter_check_my_customer_name,item.getAgent_name());
        CheckBox checkBox=helper.getView(R.id.adapter_check_my_customer_cb);
        if (item.isCheck()){
            checkBox.setChecked(true);
        }else checkBox.setChecked(false);
    }
}
