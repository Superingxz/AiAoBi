package xinlan.com.AiAoBi.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.entity.AgentAddress;

/**
 * Created by Administrator on 2016/11/3.
 */
public class AdressAdapter extends  CommonAdapter<AgentAddress> {
    public AdressAdapter(Context context, int itemLayoutId, List<AgentAddress> mDatas) {
        super(context, itemLayoutId, mDatas);
    }
    @Override
    public void convert(final ViewHolder helper, AgentAddress item) {
        helper.setText(R.id.address,item.getProvince()+item.getCity()+item.getArea()+item.getAddress());
        helper.setText(R.id.name,item.getInname());
        helper.setText(R.id.phone,item.getPhone());
        if(item.getIsdefault().equals("0")){
            ((CheckBox)helper.getView(R.id.isdefault_cb)).setChecked(false);
            ((TextView)helper.getView(R.id.isdefault_tv)).setText("设为默认地址");
            ((TextView)helper.getView(R.id.isdefault_tv)).setTextColor(Color.parseColor("#9c9c9c"));
        }else{
            ((TextView)helper.getView(R.id.isdefault_tv)).setTextColor(Color.parseColor("#fe547f"));
            ((TextView)helper.getView(R.id.isdefault_tv)).setText("默认地址");
            ((CheckBox)helper.getView(R.id.isdefault_cb)).setChecked(true);
        }
        ((Button)helper.getView(R.id.update_address_bt)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //修改地址
                addressOnClickIface.update(helper.getPosition());
            }
        });
        ((Button)helper.getView(R.id.de_address_bt)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //删除地址
                addressOnClickIface.delete(helper.getPosition());
            }
        });
        ((CheckBox) helper.getView(R.id.isdefault_cb)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                addressOnClickIface.isCheck(isChecked,helper.getPosition());
            }
        });

    }

    public AddressOnClickIface getAddressOnClickIface() {
        return addressOnClickIface;
    }

    public void setAddressOnClickIface(AddressOnClickIface addressOnClickIface) {
        this.addressOnClickIface = addressOnClickIface;
    }

    AddressOnClickIface addressOnClickIface;
    public  interface  AddressOnClickIface{
        void update(int position);
        void delete(int position);
        void isCheck(boolean isChecked,int position);
    }
}
