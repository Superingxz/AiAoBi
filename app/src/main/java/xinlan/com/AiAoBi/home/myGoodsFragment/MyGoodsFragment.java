package xinlan.com.AiAoBi.home.myGoodsFragment;

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

import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.adapter.CustomerAdapter;
import xinlan.com.AiAoBi.entity.CustomerEnity;

/**
 * Created by Administrator on 2016/9/22.
 */
public class MyGoodsFragment extends MvpFragment<MyGoodsFragmentView,MyGoodsFragmentPresenter>{
    @Override
    public MyGoodsFragmentPresenter createPresenter() {
        return new MyGoodsFragmentPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mygood_fragment, container, false);
        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView listView= (ListView) view.findViewById(R.id.mygoods_fragment_lv);
        List<CustomerEnity> list=new ArrayList<>();
        list.add(new CustomerEnity(R.mipmap.yichulidingdan,"下级订单查询"));
        list.add(new CustomerEnity(R.mipmap.lishidingdan,"我的订单查询"));
        list.add(new CustomerEnity(R.mipmap.dingdanjindu,"申请更换上级"));
        list.add(new CustomerEnity(R.mipmap.daohuoqianshou,"到货签收入库"));
        CustomerAdapter adapter=new CustomerAdapter(getActivity(),R.layout.customer_lv_adapter,list);
        listView.setAdapter(adapter);
        final Intent intent=new Intent();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        intent.setClass(getActivity(),ActivityMyLowerOrders.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent.setClass(getActivity(),ActivityCheckMyOrders.class);
                        startActivity(intent);
                        break;
                    case 2:
                        Toast.makeText(getActivity(),"部分功能正在开发中，敬请期待！",Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(getActivity(),"部分功能正在开发中，敬请期待！",Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        });
    }
}
