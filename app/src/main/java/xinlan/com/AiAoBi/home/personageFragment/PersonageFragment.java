package xinlan.com.AiAoBi.home.personageFragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.hannesdorfmann.mosby.mvp.MvpFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

import xinlan.com.AiAoBi.MainActivity;
import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.adapter.CustomerAdapter;
import xinlan.com.AiAoBi.entity.CustomerEnity;

/**
 * 个人中心
 * Created by Administrator on 2016/9/22.
 */
public class PersonageFragment extends MvpFragment<PesonageFragmentView, PesonageFragmentPresenter> {
    @Override
    public PesonageFragmentPresenter createPresenter() {
        return new PesonageFragmentPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.personage_fragment, container, false);
        ButterKnife.bind(this, view);
        initView(view);
        return view;
    }

    void initView(View view) {
        ListView listView= (ListView) view.findViewById(R.id.personage_fragment_lv);
        List<CustomerEnity> list=new ArrayList<>();
        list.add(new CustomerEnity(R.mipmap.icon_1_1,"我的价格"));
        list.add(new CustomerEnity(R.mipmap.icon_1_2,"最低库存量"));
        list.add(new CustomerEnity(R.mipmap.icon_1_3,"申请升级"));
        list.add(new CustomerEnity(R.mipmap.icon_1_4,"基本信息"));
        list.add(new CustomerEnity(R.mipmap.icon_1_5,"授权证书"));
        list.add(new CustomerEnity(R.mipmap.icon_1_6,"我的下级"));
        list.add(new CustomerEnity(R.mipmap.award,"我的奖励"));
        list.add(new CustomerEnity(R.mipmap.official_yeji,"累积业绩"));
        list.add(new CustomerEnity(R.mipmap.icon_1_7,"退出登录"));
        CustomerAdapter adapter=new CustomerAdapter(getActivity(),R.layout.customer_lv_adapter,list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                final Intent intent;
                switch (i){
                    case 0://我的价格
                        intent=new Intent(getActivity(),ActivityMyPrice.class);
                        startActivity(intent);
                        break;
                    case 1://最低库存量
                        intent=new Intent(getActivity(),ActivityWarehouse.class);
                        startActivity(intent);
                        break;
                    case 2://申请升级
                        intent = new Intent(getActivity(), ApplyforLevelActivty.class);
                        startActivity(intent);
                        break;
                    case 3://基本信息
                        intent = new Intent(getActivity(), UserInfoActivity.class);
                        startActivity(intent);
                        break;
                    case 4://授权证书
                        intent=new Intent(getActivity(),ActivityMyCredential.class);
                        startActivity(intent);
                        break;
                    case 5://我的下级
                        intent=new Intent(getActivity(),ActivityMyUnderclerk.class);
                        startActivity(intent);
                        break;
                    case 6://我的奖励
                        intent=new Intent(getActivity(),ActivityMyAward.class);
                        startActivity(intent);
                        break;
                    case 7://我的奖励
                        intent=new Intent(getActivity(),ActivityMyPerformance.class);
                        startActivity(intent);
                        break;
                    case 8:
                        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
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
                                Intent intent=new Intent(getActivity(),MainActivity.class);
                                getActivity().startActivity(intent);
                                getActivity().finish();
                            }
                        });
                        btncancle.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                alertDialog.dismiss();
                            }
                        });
                        textView.setText("退出当前用户？");
                        alertDialog.setCancelable(false);
                        break;
                }
            }
        });
    }

}
