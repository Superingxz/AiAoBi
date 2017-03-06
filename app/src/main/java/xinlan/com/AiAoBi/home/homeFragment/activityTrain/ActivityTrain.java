package xinlan.com.AiAoBi.home.homeFragment.activityTrain;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.view.TitileView;

/**
 * Created by Administrator on 2016/10/17.
 */
public class ActivityTrain extends MvpActivity<TrainView, TrainPresenter> {

    @BindView(R.id.train_titleview)
    TitileView titileView;
    @BindView(R.id.train_tab1)
    TextView trainTab1;
    @BindView(R.id.train_tab2)
    TextView trainTab2;
    @BindView(R.id.train_tab3)
    TextView trainTab3;
    @BindView(R.id.train_tab4)
    TextView trainTab4;
    @BindView(R.id.train_line)
    ImageView trainLine;
    @BindView(R.id.train_viewpager)
    ViewPager trainViewpager;
    private List<TrainFragment> list;
    private FragmentPagerAdapter adapter;
    private TrainFragment fragment1;
    private TrainFragment fragment2;
    private TrainFragment fragment3;
    private TrainFragment fragment4;
    private LinearLayout.LayoutParams layoutParams;
    float width;
    @NonNull
    @Override
    public TrainPresenter createPresenter() {
        return new TrainPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);
    }
    private List<TextView> tab;
    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        initData();
        trainLine.setLayoutParams(layoutParams);
        list.add(fragment2);
        list.add(fragment3);
        list.add(fragment4);
        list.add(fragment1);

        trainViewpager.setAdapter(adapter);
        trainViewpager.addOnPageChangeListener(listener);
    }
    private void initData(){
        fragment1=new TrainFragment();
        fragment2=new TrainFragment();
        fragment3=new TrainFragment();
        fragment4=new TrainFragment();
        tab=new ArrayList<>();
        tab.add(trainTab1);
        tab.add(trainTab2);
        tab.add(trainTab3);
        tab.add(trainTab4);
        titileView.setTitle("知识培训");
        trainTab1.setTextColor(ContextCompat.getColor(ActivityTrain.this,R.color.red));
        layoutParams = (LinearLayout.LayoutParams) trainLine.getLayoutParams();
        width = getWindowManager().getDefaultDisplay().getWidth();
        layoutParams.leftMargin=(int) width/4;
        list=new ArrayList<>();
        adapter=new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        };
    }

    private ViewPager.OnPageChangeListener listener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            float v = width / 4 * positionOffset + width*(float) position / 4;
            layoutParams.leftMargin= (int) v;
            trainLine.setLayoutParams(layoutParams);
            Log.i("lsy","position:"+position);
            Log.i("lsy","positionOffset:"+positionOffset);
        }

        @Override
        public void onPageSelected(int position) {
            resetTextColor();
            trainViewpager.setCurrentItem(position);
            tab.get(position).setTextColor(ContextCompat.getColor(ActivityTrain.this,R.color.red));
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    private void resetTextColor(){
        trainTab1.setTextColor(ContextCompat.getColor(this,R.color.text_black_color));
        trainTab2.setTextColor(ContextCompat.getColor(this,R.color.text_black_color));
        trainTab3.setTextColor(ContextCompat.getColor(this,R.color.text_black_color));
        trainTab4.setTextColor(ContextCompat.getColor(this,R.color.text_black_color));
    }
    @OnClick({R.id.train_tab1, R.id.train_tab2, R.id.train_tab3, R.id.train_tab4})
    public void onClick(View view) {
        resetTextColor();
        switch (view.getId()) {
            case R.id.train_tab1:
                trainViewpager.setCurrentItem(0,false);
                trainTab1.setTextColor(ContextCompat.getColor(this,R.color.red));
                break;
            case R.id.train_tab2:
                trainViewpager.setCurrentItem(1,false);
                trainTab2.setTextColor(ContextCompat.getColor(this,R.color.red));
                break;
            case R.id.train_tab3:
                trainViewpager.setCurrentItem(2,false);
                trainTab3.setTextColor(ContextCompat.getColor(this,R.color.red));
                break;
            case R.id.train_tab4:
                trainViewpager.setCurrentItem(3,false);
                trainTab4.setTextColor(ContextCompat.getColor(this,R.color.red));
                break;
        }
    }
}
