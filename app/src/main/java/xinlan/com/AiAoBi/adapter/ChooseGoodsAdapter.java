package xinlan.com.AiAoBi.adapter;

import android.content.Context;
import android.widget.CheckBox;

import java.util.List;

import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.entity.Goods;

/**
 * Created by Administrator on 2016/11/4.
 */
public class ChooseGoodsAdapter extends CommonAdapter<Goods> {
    public ChooseGoodsAdapter(Context context, int itemLayoutId, List<Goods> mDatas) {
        super(context, itemLayoutId, mDatas);
    }
    @Override
    public void convert(ViewHolder helper, final Goods item) {
        helper.setText(R.id.num, String.valueOf(Integer.valueOf(helper.getPosition()) + 1));
        helper.setText(R.id.name, item.getGoodsname());
        helper.setText(R.id.unit_price, item.getPrice());
        if(item.isCheck()){
            ((CheckBox) helper.getView(R.id.check_box)).setChecked(true);
        }else{
            ((CheckBox) helper.getView(R.id.check_box)).setChecked(false);
        }


//        ((CheckBox) helper.getView(R.id.check_box)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    item.setCheck(true);
//                    chooseGoodsIface.onIsCheck(item);
//                } else {
//                    item.setCheck(false);
//                    chooseGoodsIface.onDeleteCheck(item);
//                }
//            }
//        });
    }
    ChooseGoodsIface chooseGoodsIface;

    public ChooseGoodsIface getChooseGoodsIface() {
        return chooseGoodsIface;
    }

    public void setChooseGoodsIface(ChooseGoodsIface chooseGoodsIface) {
        this.chooseGoodsIface = chooseGoodsIface;
    }

    public  interface   ChooseGoodsIface{
        void onIsCheck(Goods item);
        void onDeleteCheck(Goods item);
    }
}
