package xinlan.com.AiAoBi.home.homeFragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xinlan.com.AiAoBi.App;
import xinlan.com.AiAoBi.NetCilent;
import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.adapter.AdapterFeedbackLv;
import xinlan.com.AiAoBi.adapter.AdapterNotice;
import xinlan.com.AiAoBi.adapter.AdapterRanking;
import xinlan.com.AiAoBi.entity.BasenoteInfo;
import xinlan.com.AiAoBi.entity.BaseproduceInfo;
import xinlan.com.AiAoBi.entity.BasesaleInfo;
import xinlan.com.AiAoBi.entity.BasesaleaxisInfo;
import xinlan.com.AiAoBi.home.homeFragment.UnderrclerkIndent.UnderclerkIndentActivity;
import xinlan.com.AiAoBi.home.homeFragment.activityTrain.ActivityTrain;
import xinlan.com.AiAoBi.view.DiyListView;
import xinlan.com.AiAoBi.view.DiyRECView;
import xinlan.com.AiAoBi.view.DiyRecViewb;

/**
 * Created by Administrator on 2016/9/22.
 */
public class HomeFragment extends MvpFragment<HomeFragmentView, HomeFragmentPresenter> implements HomeFragmentView {
    @BindView(R.id.liner_top)
    LinearLayout top;
    @BindView(R.id.mygoods_iv)
    ImageView mygoodsIv;
    @BindView(R.id.underclerk_goods_iv)
    ImageView underclerkGoodsIv;
    @BindView(R.id.ask_for_permission_iv)
    ImageView askForPermissionIv;
    @BindView(R.id.learn_iv)
    ImageView learnIv;
    @BindView(R.id.send_goods_iv)
    ImageView sendGoodsIv;
    @BindView(R.id.sign_for_goods_iv)
    ImageView signForGoodsIv;
    @BindView(R.id.register_iv)
    ImageView registerIv;
    @BindView(R.id.remind_iv)
    ImageView remindIv;
    @BindView(R.id.diy_listview_notice)
    DiyListView notice;
    @BindView(R.id.notice_tvmsg)
    ImageView noticeTvMsg;
    @BindView(R.id.linear_notice)
    RelativeLayout linearNotice;
    @BindView(R.id.linear_ranking)
    RelativeLayout linearRanking;
    @BindView(R.id.home_fragment_mscrollview)
    ScrollView scrollView;
    @BindView(R.id.feedback_lv)
    DiyListView feedbackLv;
    @BindView(R.id.home_fragment_notice_more)
    TextView noticeMore;
    @BindView(R.id.home_fragment_ranking_more)
    TextView rankingMore;
    @BindView(R.id.home_fragment_feedback_more)
    TextView feedbackMore;
    @BindView(R.id.liner_feedback)
    LinearLayout feedback;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.view)
    DiyRECView viewA;
    @BindView(R.id.view_b)
    DiyRecViewb viewB;
    @BindView(R.id.home_fragment_change)
    ImageView change;
    @BindView(R.id.home_fragment_ranking_iv)
    ImageView rankingiv;
    @Override
    public HomeFragmentPresenter createPresenter() {
        return new HomeFragmentPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initEvent();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    private void initEvent() {
        presenter.basesale();
        presenter.basenote("0");
        //scrollView.smoothScrollTo(0,0);
        top.setFocusable(true);
        top.setFocusableInTouchMode(true);
        top.requestFocus();
        notice.addOnLayoutChangeListener(layoutChangeListener);
        presenter.baseproduce("0");
        presenter.basecursale();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.basesale();
                presenter.basenote("0");
                presenter.baseproduce("0");
                presenter.basesaleaxis();
                presenter.basesale();
            }
        });
    }
    private View.OnLayoutChangeListener layoutChangeListener=new View.OnLayoutChangeListener() {
        @Override
        public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
            //scrollView.smoothScrollTo(0,0);
            /*top.setFocusable(true);
            top.setFocusableInTouchMode(true);
            top.requestFocus();*/
        }
    };
    @OnClick({R.id.mygoods_iv, R.id.underclerk_goods_iv, R.id.ask_for_permission_iv,
            R.id.learn_iv, R.id.send_goods_iv, R.id.sign_for_goods_iv, R.id.register_iv, R.id.remind_iv,
            R.id.home_fragment_notice_more,R.id.home_fragment_ranking_more,R.id.home_fragment_feedback_more,
            R.id.home_fragment_change
    })
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.mygoods_iv:
                intent.setClass(getActivity(), MyGoodsActvity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.underclerk_goods_iv:
                intent.setClass(getActivity(), UnderclerkIndentActivity.class);
                startActivity(intent);
                break;
            case R.id.ask_for_permission_iv://审批授权
                intent.setClass(getActivity(), WaitExaminationActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.learn_iv:
                intent.setClass(getActivity(), ActivityTrain.class);
                getActivity().startActivity(intent);
                break;
            case R.id.send_goods_iv:
                intent.setClass(getActivity(), ActivitySendGoods.class);
                startActivity(intent);
                break;
            case R.id.sign_for_goods_iv://货品签收
                intent.setClass(getActivity(), ActivitySignGoods.class);
                getActivity().startActivity(intent);
                break;
            case R.id.register_iv://收款登记
                intent.setClass(getActivity(), ActivityRegister.class);
                getActivity().startActivity(intent);
                break;
            case R.id.remind_iv://我的提醒
                intent.setClass(getActivity(), ActivityRemind.class);
                getActivity().startActivity(intent);
                break;
            case R.id.home_fragment_notice_more:
                intent.setClass(getActivity(),ActivityMore.class);
                intent.setFlags(0x101);
                getActivity().startActivity(intent);
                break;
            case R.id.home_fragment_ranking_more:
                intent.setFlags(0x102);
                intent.setClass(getActivity(),ActivityMore.class);
                getActivity().startActivity(intent);
                break;
            case R.id.home_fragment_feedback_more:
                intent.setFlags(0x103);
                intent.setClass(getActivity(),ActivityMore.class);
                getActivity().startActivity(intent);
                break;
            case R.id.home_fragment_change:
                if (viewA.getVisibility()==View.VISIBLE){
                    viewA.setVisibility(View.INVISIBLE);
                    viewB.setVisibility(View.VISIBLE);
                    rankingiv.setImageResource(R.mipmap.official_my_ranking);
                }else {
                    viewA.setVisibility(View.VISIBLE);
                    viewB.setVisibility(View.INVISIBLE);
                    rankingiv.setImageResource(R.mipmap.official_paiming);
                }

                break;
        }
    }

    @Override
    public void setDataToRanking(List<BasesaleInfo> data) {
        if (data == null || data.size() == 0) {
            linearRanking.setVisibility(View.GONE);
            return;
        }
        linearRanking.setVisibility(View.VISIBLE);
        this.data=data;
        presenter.basesaleaxis();

    }
    private List<BasesaleInfo> data;
    @Override
    public void setDataToRankingb(List<BasesaleaxisInfo> data) {
        List<String> listx=new ArrayList<>();
        List<String> listy=new ArrayList<>();
        List<String> listData=new ArrayList<>();
        for (BasesaleInfo info:this.data){
            listx.add(info.getAgentname());
            listData.add(info.getSalemoney());
        }
        listy.add("0");
        for (BasesaleaxisInfo basesaleaxisInfo: data){
            listy.add(basesaleaxisInfo.getValue());
        }
        viewA.setDataToList(listx,listy,listData);
    }

    @Override
    public void setDataToRankingc(List<BasesaleInfo> data) {
        /*测试数据
        List<BasesaleInfo> list=new ArrayList<>();
        list.add(new BasesaleInfo("良辰","2000"));
        list.add(new BasesaleInfo("日天","3000"));
        list.add(new BasesaleInfo("日地","4100"));
        list.add(new BasesaleInfo("日地","5000"));
        list.add(new BasesaleInfo("日地","4500"));
        list.add(new BasesaleInfo("日地","5200"));
        list.add(new BasesaleInfo("日地","5100"));
        list.add(new BasesaleInfo("日地","4300"));
        list.add(new BasesaleInfo("日地","4600"));
        list.add(new BasesaleInfo("日地","4600"));
        list.add(new BasesaleInfo("日地","6000"));
        list.add(new BasesaleInfo("张名华","1000"));
        list.add(new BasesaleInfo("日地","1200"));*/
        if (data!=null&&!data.isEmpty())
        viewB.setDataToList(data);
    }

    private ProgressDialog progressDialog;

    @Override
    public void showProgressDialog(String messege) {
        if (progressDialog != null) {
            progressDialog.cancel();
        }
        progressDialog = ProgressDialog.show(getActivity(), null, messege);
    }

    @Override
    public void dimissProgressDialog() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    @Override
    public void showMsgDialog(String msg, final int style) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(msg);
        builder.setTitle("提示信息!");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (style == 0)
                    dialog.dismiss();
                if (style == 1)
                    getActivity().finish();
            }
        });
        builder.setCancelable(false);
        builder.create().show();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setDataToNotice(final List<BasenoteInfo> data) {
        if (data == null || data.size() == 0) {
            linearNotice.setVisibility(View.GONE);
            notice.setVisibility(View.GONE);
            return;
        }
        linearNotice.setVisibility(View.VISIBLE);
        notice.setVisibility(View.VISIBLE);
        AdapterNotice adapterNotice = new AdapterNotice(getActivity(), R.layout.adapter_notice, data);
        notice.setAdapter(adapterNotice);
        notice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), DisplayNotice.class);
                intent.putExtra("basenoteInfo", data.get(i));
                startActivity(intent);
            }
        });
    }

    @Override
    public void setDataToFeedback(final List<BaseproduceInfo> data) {
        AdapterFeedbackLv adapterFeedbackLv=new AdapterFeedbackLv(getActivity()
                ,R.layout.adapter_homefragment_feedback,data);
        feedbackLv.setAdapter(adapterFeedbackLv);
        feedbackLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String mkeyid = data.get(i).getMkeyid();
                Intent intent=new Intent(getActivity(),ActivityFeedbackDetails.class);
                intent.putExtra("mkeyid",mkeyid);
                startActivity(intent);
            }
        });
    }

    @Override
    public void setFeedBackVis(int visible) {
        feedback.setVisibility(visible);
    }

    @Override
    public void setRefreshing(boolean b) {
        swipeRefreshLayout.setRefreshing(b);
    }


}
