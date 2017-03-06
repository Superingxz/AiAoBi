package xinlan.com.AiAoBi.adapter;

import android.content.Context;

import java.util.List;

import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.entity.MysendgoodsInfo;

/**
 * Created by Administrator on 2016/12/20.
 */
public class AdapterChekMyGood extends CommonAdapter<MysendgoodsInfo>{
    public AdapterChekMyGood(Context context, int itemLayoutId, List<MysendgoodsInfo> mDatas) {
        super(context, itemLayoutId, mDatas);
    }

    @Override
    public void convert(ViewHolder helper, MysendgoodsInfo item) {
        helper.setText(R.id.adapter_check_my_good_num,helper.getPosition()+1+"");
        helper.setText(R.id.adapter_check_my_good_date,item.getOptdate());
        helper.setText(R.id.adapter_check_my_good_recevice_man,item.getInman());
        helper.setText(R.id.adapter_check_my_good_goodsize,item.getGoodsname()+"/"+item.getGoodssize());
        helper.setText(R.id.adapter_check_my_good_price,item.getCostprice());
        helper.setText(R.id.adapter_check_my_good_get_num,item.getNeednum());
        helper.setText(R.id.adapter_check_my_good_send_num,item.getAlreadynum());
    }
}
