package xinlan.com.AiAoBi.adapter;

import android.content.Context;

import java.util.List;

import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.entity.ScanmansumIfo;

/**
 * Created by Administrator on 2016/12/6.
 */
public class AdapterScanmansum extends CommonAdapter<ScanmansumIfo>{
    public AdapterScanmansum(Context context, int itemLayoutId, List<ScanmansumIfo> mDatas) {
        super(context, itemLayoutId, mDatas);
    }

    @Override
    public void convert(ViewHolder helper, ScanmansumIfo item) {
        helper.setText(R.id.adapter_send_goods_title,helper.getPosition()+1+"");
        helper.setText(R.id.adapter_send_goods_receive_man,item.getAgent_name());
        helper.setText(R.id.adapter_send_goods_need_num,item.getNeednum());
        helper.setText(R.id.adapter_send_goods_has_send_num,item.getAlreadynum());
        helper.setText(R.id.adapter_send_goods_this_send_num,item.getCurnum());
        helper.setText(R.id.adapter_send_goods_Owegood,item.getNonum());
       // helper.setText(R.id.adapter_send_goods_remark,item.get);
    }
}
