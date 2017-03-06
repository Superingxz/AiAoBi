package xinlan.com.AiAoBi.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import java.util.List;

import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.entity.ChooseGoods;
import xinlan.com.AiAoBi.view.JrComputeView;

/**
 * Created by Administrator on 2016/11/4.
 */
public class MyGoodsAdapter extends CommonAdapter<ChooseGoods> {
    Context context;

    public MyGoodsAdapter(Context context, int itemLayoutId, List<ChooseGoods> mDatas) {
        super(context, itemLayoutId, mDatas);
        this.context = context;
    }
    @Override
    public void convert(final ViewHolder helper, final ChooseGoods item) {
        helper.setText(R.id.num, String.valueOf(Integer.valueOf(helper.getPosition()) + 1));
        helper.setText(R.id.gooods_name, item.getGoodsname() + "/" + item.getGoodssize());
        helper.setText(R.id.inventory, item.getStocknum());
        helper.setText(R.id.on_road, item.getOnloadnum());
        ((TextView) helper.getView(R.id.delete_text)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editeIface.deleteItem(helper.getPosition());
            }
        });
        final  JrComputeView jrComputeView=(JrComputeView)helper.getView(R.id.compute_view);
        jrComputeView.getEditText().clearFocus();
        jrComputeView.setMinNum(1);
        jrComputeView.setMaxNum(Integer.MAX_VALUE);
        if (TextUtils.isEmpty(item.getEditText())) {
            jrComputeView.setNum(1);
        } else {
            jrComputeView.setNum(Integer.valueOf(item.getEditText()));
        }
        jrComputeView.setCalculateCallBack(new JrComputeView.CalculateCallBack() {
            @Override
            public void reduceButtonBack(int num) {
                getData().get(helper.getPosition()).setEditText(String.valueOf(num));
                editeIface.edite(helper.getPosition(), String.valueOf(num));
            }

            @Override
            public void addButtonBack(int num) {
                getData().get(helper.getPosition()).setEditText(String.valueOf(num));
                editeIface.edite(helper.getPosition(), String.valueOf(num));
            }

            @Override
            public void editTextChange(int num) {
                Log.v("num",helper.getPosition()+"-"+num);
                getData().get(helper.getPosition()).setEditText(String.valueOf(num));
                editeIface.edite(helper.getPosition(), String.valueOf(num));

            }
        });
    }


    public EditeIface getEditeIface() {
        return editeIface;
    }

    public void setEditeIface(EditeIface editeIface) {
        this.editeIface = editeIface;
    }

    EditeIface editeIface;

    public interface EditeIface {
        void edite(int position, String num);

        void deleteItem(int position);

        void deleteAll();
    }
}
