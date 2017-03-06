package xinlan.com.AiAoBi.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;

import xinlan.com.AiAoBi.App;
import xinlan.com.AiAoBi.R;

/**
 * Created by Administrator on 2016/12/8.
 */
public class AdapterSaleScanLvInfo extends CommonAdapter<String>{
    private String goodName;
    private Handler handler;
    public AdapterSaleScanLvInfo(Context context, int itemLayoutId, List<String> mDatas,String goodName) {
        super(context, itemLayoutId, mDatas);
        this.goodName=goodName;
        handler = App.getApp().getHandler();
    }

    @Override
    public void convert(final ViewHolder helper, final String item)  {

        helper.setText(R.id.adapter_sale_scan_lvitem_num,helper.getPosition()+1+"");
        helper.setText(R.id.adapter_sale_scan_lvitem_barcode,item);
        TextView delete=helper.getView(R.id.adapter_sale_scan_lvitem_else);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
                alertDialog.show();
                Window window = alertDialog.getWindow();
                window.setContentView(R.layout.dialog_diy);
                Button btnyes= (Button) window.findViewById(R.id.dialog_diy_btnyes);
                Button btncancle= (Button) window.findViewById(R.id.dialog_diy_btncancel);
                TextView textView= (TextView) window.findViewById(R.id.dialog_diy_text);
                btnyes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                        removeItem(helper.getPosition());
                        Message message = Message.obtain();
                        message.what = 0x701;
                        message.obj = new String[]{item, goodName};
                        handler.sendMessage(message);
                    }
                });
                btncancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
                textView.setText("确定要删除该条码吗？");
            }
        });

    }
}
