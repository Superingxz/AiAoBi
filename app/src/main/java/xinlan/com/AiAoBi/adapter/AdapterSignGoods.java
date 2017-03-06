package xinlan.com.AiAoBi.adapter;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

import java.util.List;

import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.entity.OkgoodsInfo;

/**
 * Created by Administrator on 2016/12/3.
 */
public class AdapterSignGoods extends CommonAdapter<OkgoodsInfo> {
    public AdapterSignGoods(Context context, int itemLayoutId, List<OkgoodsInfo> mDatas) {
        super(context, itemLayoutId, mDatas);
    }
    private SpannableString spannableString;
    @Override
    public void convert(ViewHolder helper, OkgoodsInfo item) {
        spannableString=new SpannableString(item.getMasterid());
        spannableString.setSpan(new UnderlineSpan(),0,item.getMasterid().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        TextView textView=helper.getView(R.id.sign_goods_name);
        textView.setText(spannableString);
        helper.setText(R.id.sign_goods_number,helper.getPosition()+1+"");
        //helper.setText(R.id.sign_goods_name,spannableString.toString());
        helper.setText(R.id.sign_goods_price,item.getSalemoney());
        helper.setText(R.id.sign_goods_state,item.getBargainflag());
    }
}
