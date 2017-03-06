package xinlan.com.AiAoBi.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

import java.util.List;

import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.entity.DoselagentaInfo;

/**
 * Created by Administrator on 2016/11/11.
 */
public class IndentInfoWaitAdapter extends CommonAdapter<DoselagentaInfo> {
    SpannableString ss;
    public IndentInfoWaitAdapter(Context context, int itemLayoutId, List<DoselagentaInfo> mDatas) {
        super(context, itemLayoutId, mDatas);
    }

    @Override
    public void convert(ViewHolder helper, DoselagentaInfo item) {
        ss=new SpannableString(item.getRef_no());
        ss.setSpan(new UnderlineSpan(),0,item.getRef_no().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        TextView tv=helper.getView(R.id.adapter_undercler_down_wait_gnumber);
        tv.setText(ss);
       /* tv.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        tv.getPaint().setAntiAlias(true);*/
       // helper.setText(R.id.adapter_undercler_down_wait_gnumber,item.getRef_no());
        helper.setText(R.id.adapter_undercler_down_wait_name,item.getInman()+"/"+item.getSlevelname());
        helper.setText(R.id.adapter_undercler_down_wait_money,item.getSalemoney());
    }
}
