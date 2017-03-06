package xinlan.com.AiAoBi.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.entity.AddAaginOrder;
import xinlan.com.AiAoBi.view.JrComputeView;

/**
 * Created by Administrator on 2016/11/15.
 */
public class AddAgainAdapter extends  CommonAdapter<AddAaginOrder> {
    public AddAgainAdapter(Context context, int itemLayoutId, List<AddAaginOrder> mDatas) {
        super(context, itemLayoutId, mDatas);
    }
    @Override
    public void convert(final ViewHolder helper, AddAaginOrder item) {
        helper.setText(R.id.num,String.valueOf(helper.getPosition()+1));
        helper.setText(R.id.gooods_name,item.getGoodsname());
        helper.setText(R.id.order_num,item.getTotal());
        helper.setText(R.id.need_num,item.getLessnum());
        if(item.isAdd()){
            ((TextView)helper.getView(R.id.delete_text)).setVisibility(View.VISIBLE);
        }else{
            ((TextView)helper.getView(R.id.delete_text)).setVisibility(View.INVISIBLE);
        }
        ((TextView)helper.getView(R.id.delete_text)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editeIface.deleteItem(helper.getPosition());
            }
        });
        JrComputeView jrComputeView=helper.getView(R.id.compute_view);
        jrComputeView.setMinNum(0);
        jrComputeView.setMaxNum(Integer.MAX_VALUE);
        if(item.isAdd()){
            jrComputeView.setNum(Integer.valueOf(item.getEditText()));
        }else{
            if(TextUtils.isEmpty(item.getNum())){
                jrComputeView.setNum(0);
            }else{
                jrComputeView.setNum(Integer.valueOf(item.getNum()));
            }
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
