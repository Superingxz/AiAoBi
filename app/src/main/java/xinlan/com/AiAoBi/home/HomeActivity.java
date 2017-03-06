package xinlan.com.AiAoBi.home;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xinlan.com.AiAoBi.BaseActivty;
import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.home.customerFragment.CustomerFragment;
import xinlan.com.AiAoBi.home.homeFragment.HomeFragment;
import xinlan.com.AiAoBi.home.myGoodsFragment.MyGoodsFragment;
import xinlan.com.AiAoBi.home.personageFragment.PersonageFragment;

/**
 * Created by LSY on 2016/9/22.
 */
public class HomeActivity extends BaseActivty {

    @BindView(R.id.activity_main_viewpager)
    ViewPager pager;
    @BindView(R.id.tab1)
    ImageView tab1;
    @BindView(R.id.tab2)
    ImageView tab2;
    @BindView(R.id.tab3)
    ImageView tab3;
    @BindView(R.id.tab4)
    ImageView tab4;
    @BindView(R.id.tv_tab1)
    TextView tvTab1;
    @BindView(R.id.home_linear_tab1)
    LinearLayout homeLinearTab1;
    @BindView(R.id.tv_tab2)
    TextView tvTab2;
    @BindView(R.id.home_linear_tab2)
    LinearLayout homeLinearTab2;
    @BindView(R.id.tv_tab3)
    TextView tvTab3;
    @BindView(R.id.home_linear_tab3)
    LinearLayout homeLinearTab3;
    @BindView(R.id.tv_tab4)
    TextView tvTab4;
    @BindView(R.id.home_linear_tab4)
    LinearLayout homeLinearTab4;
    private FragmentPagerAdapter adapter;
    private List<Fragment> fragments;
    private ViewPager.OnPageChangeListener pagerListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            resetTab();
            switch (position) {
                case 0:
                    homeLinearTab1.setBackgroundColor(Color.parseColor("#C7F78F"));
                    tab1.setImageResource(R.mipmap.tab1_1);
                    tvTab1.setTextColor(Color.parseColor("#027141"));
                    break;
                case 1:
                    homeLinearTab2.setBackgroundColor(Color.parseColor("#C7F78F"));
                    tab2.setImageResource(R.mipmap.tab2_1);
                    tvTab2.setTextColor(Color.parseColor("#027141"));
                    break;
                case 2:
                    homeLinearTab3.setBackgroundColor(Color.parseColor("#C7F78F"));
                    tab3.setImageResource(R.mipmap.tab3_1);
                    tvTab3.setTextColor(Color.parseColor("#027141"));
                    break;
                case 3:
                    homeLinearTab4.setBackgroundColor(Color.parseColor("#C7F78F"));
                    tab4.setImageResource(R.mipmap.tab4_1);
                    tvTab4.setTextColor(Color.parseColor("#027141"));

                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        initData();
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(pagerListener);
        pager.setOffscreenPageLimit(3);
    }

    private void initData() {
        fragments = new ArrayList<>();
        HomeFragment homeFragment = new HomeFragment();
        MyGoodsFragment myGoodsFragment = new MyGoodsFragment();
        CustomerFragment customerFragment = new CustomerFragment();
        PersonageFragment personageFragment = new PersonageFragment();
        fragments.add(homeFragment);
        fragments.add(myGoodsFragment);
        fragments.add(customerFragment);
        fragments.add(personageFragment);
        adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };
    }

    /**
     * 获取Tab顶部的drawable
     *
     * @param res
     * @return drawable
     */
    //@TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private Drawable getMdrawble(int res) {

        Drawable drawable = ContextCompat.getDrawable(this, res);

        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        }
        return drawable;
    }

    /**
     * 重置Tab
     */
    private void resetTab() {
        tab1.setImageResource(R.mipmap.tab1_2);
        tab2.setImageResource(R.mipmap.tab2_2);
        tab3.setImageResource(R.mipmap.tab3_2);
        tab4.setImageResource(R.mipmap.tab4_2);
        homeLinearTab1.setBackgroundColor(Color.parseColor("#044B2E"));
        homeLinearTab2.setBackgroundColor(Color.parseColor("#044B2E"));
        homeLinearTab3.setBackgroundColor(Color.parseColor("#044B2E"));
        homeLinearTab4.setBackgroundColor(Color.parseColor("#044B2E"));
        tvTab1.setTextColor(Color.WHITE);
        tvTab2.setTextColor(Color.WHITE);
        tvTab3.setTextColor(Color.WHITE);
        tvTab4.setTextColor(Color.WHITE);
    }

    @OnClick({R.id.home_linear_tab1, R.id.home_linear_tab2, R.id.home_linear_tab3, R.id.home_linear_tab4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_linear_tab1://首页
                pager.setCurrentItem(0, false);
                break;
            case R.id.home_linear_tab2://我的要货
                pager.setCurrentItem(1, false);
                break;
            case R.id.home_linear_tab3://客户中心
                pager.setCurrentItem(2, false);
                break;
            case R.id.home_linear_tab4://个人中心
                pager.setCurrentItem(3, false);
                break;
        }
    }


    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click(); //调用双击退出函数
        }
        return false;
    }

    /**
     * 双击退出函数
     */
    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (!isExit) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            finish();
            System.exit(0);
        }
    }
}
