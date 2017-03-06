package xinlan.com.AiAoBi.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.entity.Msgsalevisitdet;

/**
 * Created by Administrator on 2016/12/21.
 */
public class MsgsalevisitdetAdapter extends CommonAdapter<Msgsalevisitdet> {
    Context context;

    public MsgsalevisitdetAdapter(Context context, int itemLayoutId, List<Msgsalevisitdet> mDatas) {
        super(context, itemLayoutId, mDatas);
        this.context = context;
    }

    @Override
    public void convert(ViewHolder helper, final Msgsalevisitdet item) {
        helper.setText(R.id.name, item.getCustom());
        helper.setText(R.id.info, item.getPurchaseinfo());
        if (item.isCheck()) {
            ((CheckBox) helper.getView(R.id.check_box)).setChecked(true);
        } else {
            ((CheckBox) helper.getView(R.id.check_box)).setChecked(false);
        }
        helper.setText(R.id.relation, item.getRela());
        if (!TextUtils.isEmpty(item.getRela()) && item.getRela().equals("打电话")) {
            ((TextView) helper.getView(R.id.relation)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!TextUtils.isEmpty(item.getClienttel())) {
                        TextView textView = (TextView) v;
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + item.getClienttel()));
                        context.startActivity(intent);
                    }

                }
            });
        }

    }
}
