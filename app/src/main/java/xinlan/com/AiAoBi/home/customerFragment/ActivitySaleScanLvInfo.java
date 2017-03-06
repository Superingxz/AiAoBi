package xinlan.com.AiAoBi.home.customerFragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xinlan.com.AiAoBi.App;
import xinlan.com.AiAoBi.BaseActivty;
import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.adapter.AdapterSaleScanLvInfo;
import xinlan.com.AiAoBi.requestJionIn.MTextWatch;
import xinlan.com.AiAoBi.scancode.activity.CaptureActivity;
import xinlan.com.AiAoBi.utils.CommonUtils;
import xinlan.com.AiAoBi.view.TitileView;

/**
 * Created by Administrator on 2016/12/8.
 */
public class ActivitySaleScanLvInfo extends BaseActivty {
    @BindView(R.id.sale_scan_lvitem_title)
    TitileView title;
    @BindView(R.id.title_goodname)
    TextView titleGoodName;
    @BindView(R.id.sale_scan_lvitem_scanbtn)
    Button scanbtn;
    @BindView(R.id.sale_scan_lvitem_edit)
    EditText edit;
    @BindView(R.id.sale_scan_lvitem_deletebtn)
    Button deletebtn;
    @BindView(R.id.sale_scan_lvitem_lv)
    ListView lv;
    private List<String> barcodes;
    private AdapterSaleScanLvInfo adapter;
    private String goodName;
    private String barCode;
    private Handler handler;
    private MTextWatch textWatch = new MTextWatch() {
        @Override
        public void afterTextChanged(Editable editable) {
            super.afterTextChanged(editable);
            barCode = edit.getText().toString().trim();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_scan_lvitem);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        intiEvent();
    }

    int listSize;
    private void intiEvent() {
        title.setTitle("条码列表");
        handler = App.getApp().getHandler();
        edit.addTextChangedListener(textWatch);
        barcodes = getIntent().getStringArrayListExtra("barcode");
        listSize = barcodes.size();
        goodName = getIntent().getStringExtra("goodName");
        titleGoodName.setText(goodName);
        adapter = new AdapterSaleScanLvInfo(this, R.layout.adapter_sale_scan_lvitem, barcodes, goodName);
        lv.setAdapter(adapter);
        title.setLefebtIface(new TitileView.LeftBtIface() {
            @Override
            public void onClick() {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
//        Log.v("删除条码之后的数量", String.valueOf(adapter.getData().size()));
//        if (listSize != adapter.getData().size()) {
//            Intent intent = new Intent();
//            intent.putExtra("goodsid", getIntent().getStringExtra("goodsid"));
//            intent.putStringArrayListExtra("barcodes", (ArrayList<String>) adapter.getData());
//            setResult(Activity.RESULT_OK, intent);
//            Log.v("删除条码之后的数量++++++++++++++", String.valueOf(adapter.getData().size()));
//        }
        super.onBackPressed();
    }

    @OnClick({R.id.sale_scan_lvitem_scanbtn, R.id.sale_scan_lvitem_deletebtn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sale_scan_lvitem_scanbtn:
                boolean permision = isPermision();
                if (!permision){
                    showMsgDialog("相机打开失败，请检查是否已为该应用开启相机权限！",0);
                    return;
                }
                // 调用ZXIng开源项目源码  扫描二维码
                Intent openCameraIntent = new Intent(ActivitySaleScanLvInfo.this,
                        CaptureActivity.class);
                startActivityForResult(openCameraIntent, 0);
                break;
            case R.id.sale_scan_lvitem_deletebtn:
                if (barcodes.contains(barCode)) {
                    //条码存在列表上
                    showDiyMsgDialog("确定要删除该条码？", new Mcallback() {
                        @Override
                        public void doSomething(AlertDialog alertDialog) {
                            alertDialog.dismiss();
                            Message message = Message.obtain();
                            message.what = 0x701;
                            message.obj = new String[]{barCode, goodName};
                            handler.sendMessage(message);
                            adapter.removeItem(barCode);
                            edit.setText("");

                        }

                        @Override
                        public void onClick(View view) {

                        }
                    });
                } else {
                    showMsgDialog("条码不存在当前列表上！", 0);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            barCode = bundle.getString("result");
            edit.setText(barCode);
        }
    }
}
