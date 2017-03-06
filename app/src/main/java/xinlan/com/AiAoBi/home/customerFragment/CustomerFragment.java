package xinlan.com.AiAoBi.home.customerFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.adapter.CustomerAdapter;
import xinlan.com.AiAoBi.entity.CustomerEnity;
import xinlan.com.AiAoBi.home.homeFragment.ActivityRegister;

/**
 * Created by Administrator on 2016/9/22.
 */
public class CustomerFragment extends MvpFragment<CustomerFragmentView, CustomerFragmentPresenter> {
    @BindView(R.id.customer_lv)
    ListView listView;
    private List<CustomerEnity> list;
    @Override
    public CustomerFragmentPresenter createPresenter() {
        return new CustomerFragmentPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.customer_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        list=new ArrayList<>();
        list.add(new CustomerEnity(R.mipmap.shengjishenpi,"升级审批"));
        list.add(new CustomerEnity(R.mipmap.lingshousaoma,"零售扫码"));
        list.add(new CustomerEnity(R.mipmap.chaxun,"发货查询"));
        list.add(new CustomerEnity(R.mipmap.shoukuandengji,"收款登记"));
        CustomerAdapter adapter=new CustomerAdapter(getActivity(),R.layout.customer_lv_adapter,list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent;
                switch (i){
                    case 0:
                        intent=new Intent(getActivity(), ApprovalList.class);
                        startActivity(intent);
                        break;
                    case 1://零售扫码
                        intent=new Intent(getActivity(),ActivitySaleScan.class);
                        startActivity(intent);
                        break;
                    case 2://发货查询
                        intent=new Intent(getActivity(),ActivityCheckMyGoods.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent=new Intent(getActivity(),ActivityRegister.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }

}
