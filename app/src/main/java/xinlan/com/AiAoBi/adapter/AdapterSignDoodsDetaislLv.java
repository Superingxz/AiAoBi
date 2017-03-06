package xinlan.com.AiAoBi.adapter;

import android.content.Context;

import java.util.List;

import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.entity.Ddata;

/**
 * Created by Administrator on 2016/12/5.
 */
public class AdapterSignDoodsDetaislLv extends CommonAdapter<Ddata>{
    public AdapterSignDoodsDetaislLv(Context context, int itemLayoutId, List<Ddata> mDatas) {
        super(context, itemLayoutId, mDatas);
    }

    @Override
    public void convert(ViewHolder helper, Ddata item) {
        helper.setText(R.id.sign_goods_details_lv_num,helper.getPosition()+1+"");
        helper.setText(R.id.sign_goods_details_lv_name,item.getGoodsname()+"/"+item.getGoodssize());
        helper.setText(R.id.sign_goods_details_lv_price,item.getCostprice());
        helper.setText(R.id.sign_goods_details_lv_ordernum,item.getNum());
        helper.setText(R.id.sign_goods_details_lv_delivernum,item.getAlreadynum());
    }
}
