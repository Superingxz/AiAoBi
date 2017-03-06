package xinlan.com.AiAoBi.home.homeFragment.activityTrain;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.home.homeFragment.activityTrain.enityTrain.RecyAdapterData;

/**
 * Created by Administrator on 2016/10/18.
 */
public class TrainFragment extends Fragment {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private List<RecyAdapterData> data ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.flagment_train, container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        data=new ArrayList<>();
        TestRecy(data);
        MRecyAdapter mRecyAdapter=new MRecyAdapter(getActivity(),data);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(mRecyAdapter);

    }
    void TestRecy(List<RecyAdapterData> data){
        RecyAdapterData recyData=new RecyAdapterData();
        int i;
        for (i=0;i<=10;i++){
            data.add(recyData);
            recyData.setUrlImage("http://img.pconline.com.cn/images/upload/upc/tx/wallpaper/1302/25/c0/18395607_1361771674095.jpg");
            recyData.setDate("2018-08-08");
            recyData.setMessege("雨纷纷，旧故里草木深，我听闻你仍守着古城，城郊牧笛声，落在那座野村，缘分落地生根是我们");
            recyData.setTitle("烟火易冷");
        }
    }

}
