package xinlan.com.AiAoBi.home.homeFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import xinlan.com.AiAoBi.App;
import xinlan.com.AiAoBi.BaseActivty;
import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.adapter.ChooseGoodsAdapter;
import xinlan.com.AiAoBi.entity.AddAaginOrder;
import xinlan.com.AiAoBi.entity.ChooseGoods;
import xinlan.com.AiAoBi.entity.Goods;
import xinlan.com.AiAoBi.home.enity.BaseModel;
import xinlan.com.AiAoBi.view.TitileView;

/**
 * 选择货品
 * Created by Administrator on 2016/11/4.
 */
public class ChooseGoodsActivity extends BaseActivty {
    @BindView(R.id.all)
    TextView all;
    @BindView(R.id.skin)
    TextView skin;
    @BindView(R.id.food)
    TextView food;
    @BindView(R.id.sanitation)
    TextView sanitation;
    @BindView(R.id.material)
    TextView material;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.reminder)
    TextView reminder;
    @BindView(R.id.title_layout)
    LinearLayout title_layout;
    List<Goods> chooseGoodsList = new ArrayList<>();
    List<Goods> foodList = new ArrayList<>();
    List<Goods> skinList = new ArrayList<>();
    List<Goods> materialList = new ArrayList<>();
    List<Goods> sanitationList = new ArrayList<>();
    List<Goods> smbumitList = new ArrayList<>();
    List<Goods> list=new ArrayList<>();

    ChooseGoodsAdapter chooseGoodsAdapter;
    List<ChooseGoods> intentchooseGoodsList = new ArrayList<>();
    Call<BaseModel<Goods>> goodsCall;
    List<AddAaginOrder> addlist;
    private int flag;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_goods);
        ButterKnife.bind(this);
        if (null != getIntent() && !TextUtils.isEmpty(getIntent().getStringExtra("materialList"))) {
            title_layout.setVisibility(View.GONE);
        }
        flag=getIntent().getFlags();
        initView();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        showProgressDialog("获取数据中...");
        HashMap<String, String> map = new HashMap<>();
        map.put("curuserid", App.getApp().getUserInfo().getPhone());
        goodsCall = netApi.getGoods(map);
        goodsCall.enqueue(new Callback<BaseModel<Goods>>() {
            @Override
            public void onResponse(Response<BaseModel<Goods>> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    if (("1").equals(response.body().getRes())) {
                        list.addAll(response.body().getData());
                        intentchooseGoodsList = (List<ChooseGoods>) getIntent().getSerializableExtra("chooseGoodsList");
                        if (intentchooseGoodsList != null && intentchooseGoodsList.size() > 0) {
                            for (int i = 0; i < intentchooseGoodsList.size(); i++) {
                                String goodid = intentchooseGoodsList.get(i).getGoodsid();
                                int remoInt = 0;
                                for (int j = 0; j < response.body().getData().size(); j++) {
                                    if (response.body().getData().get(j).getGoodsid().equals(goodid)) {
                                        remoInt = j;
                                        break;
                                    }
                                }
                                response.body().getData().remove(remoInt);
                            }
                        }
                        if (flag==0x006){
                            List<Goods> list= (List<Goods>) getIntent().getSerializableExtra("warehouseList");
                            for (int i = 0; i < list.size(); i++) {
                                String goodid = list.get(i).getGoodsid();
                                int remoInt = 0;
                                for (int j = 0; j < response.body().getData().size(); j++) {
                                    if (response.body().getData().get(j).getGoodsid().equals(goodid)) {
                                        remoInt = j;
                                        break;
                                    }
                                }
                                response.body().getData().remove(remoInt);
                            }
                        }
                        addlist = (List<AddAaginOrder>) getIntent().getSerializableExtra("addList");
                        if (addlist != null && addlist.size() > 0) {
                            for (int i = 0; i < addlist.size(); i++) {
                                String goodid = addlist.get(i).getGoodsid();
                                int remoInt = 0;
                                for (int j = 0; j < response.body().getData().size(); j++) {
                                    if (response.body().getData().get(j).getGoodsid().equals(goodid)) {
                                        remoInt = j;
                                        break;
                                    }
                                }
                                response.body().getData().remove(remoInt);
                            }
                        }
                        for (Goods goods : response.body().getData()) {
                            if (goods.getTypename().equals("护肤")) {
                                skinList.add(goods);
                            } else if (goods.getTypename().equals("食品")) {
                                foodList.add(goods);
                            } else if (goods.getTypename().equals("卫生用品")) {
                                sanitationList.add(goods);
                            } else if (goods.getTypename().equals("包材")) {
                                materialList.add(goods);
                            }
                        }
                        if (title_layout.getVisibility() == View.GONE) {
                            chooseGoodsList.addAll(materialList);
                        } else {
                           /* chooseGoodsList.addAll(foodList);
                            chooseGoodsList.addAll(skinList);
                            chooseGoodsList.addAll(sanitationList);
                            chooseGoodsList.addAll(materialList);*/
                            chooseGoodsList.addAll(response.body().getData());
                        }
                        chooseGoodsAdapter.notifyDataSetChanged();
                    }
                } else {
                    showToast(response.body().getMsg());
                    reminder.setVisibility(View.VISIBLE);
                    reminder.setText(response.body().getMsg());
                }
                dimissProgressDialog();
            }

            @Override
            public void onFailure(Throwable t) {
                showToast("服务器错误");
                dimissProgressDialog();
            }
        });
    }

    private void initView() {
        name.setText("选择产品（全部）");
        chooseGoodsAdapter = new ChooseGoodsAdapter(this, R.layout.item_choose_goods, chooseGoodsList);
        lv.setAdapter(chooseGoodsAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //0.全部 1.食品 2.护肤 3.卫生 4.材料
                switch (index) {
                    case 0:
                        if (chooseGoodsList.get(position).isCheck()) {
                            chooseGoodsList.get(position).setCheck(false);
                            smbumitList.remove(chooseGoodsList.get(position));
                        } else {
                            chooseGoodsList.get(position).setCheck(true);
                            smbumitList.add(chooseGoodsList.get(position));
                        }
                        break;
                    case 1:
                        if (foodList.get(position).isCheck()) {
                            foodList.get(position).setCheck(false);
                            smbumitList.remove(foodList.get(position));
                        } else {
                            foodList.get(position).setCheck(true);
                            smbumitList.add(foodList.get(position));
                        }
                        break;
                    case 2:

                        if (skinList.get(position).isCheck()) {
                            skinList.get(position).setCheck(false);
                            smbumitList.remove(skinList.get(position));
                        } else {
                            skinList.get(position).setCheck(true);
                            smbumitList.add(skinList.get(position));
                        }
                        break;
                    case 3:

                        if (sanitationList.get(position).isCheck()) {
                            sanitationList.get(position).setCheck(false);
                            smbumitList.remove(sanitationList.get(position));
                        } else {
                            sanitationList.get(position).setCheck(true);
                            smbumitList.add(sanitationList.get(position));
                        }

                        break;
                    case 4:

                        if (materialList.get(position).isCheck()) {
                            materialList.get(position).setCheck(false);
                            smbumitList.remove(materialList.get(position));
                        } else {
                            materialList.get(position).setCheck(true);
                            smbumitList.add(materialList.get(position));
                        }
                        break;
                }
                chooseGoodsAdapter.notifyDataSetChanged();
            }
        });
        titileView = (TitileView) findViewById(R.id.title_view);
        titileView.setTitle("选择产品");
        titileView.setRightText("确定", new TitileView.RightBtIface() {
            @Override
            public void onClick() {
                if (smbumitList.size() == 0) {
                    showToast("请选择货品");
                    return;
                }
                if (title_layout.getVisibility() == View.GONE||flag==0x006) {
                    List<Goods> goodsList = new ArrayList<>();
                    for (Goods goods : chooseGoodsAdapter.getData()) {
                        if (goods.isCheck()) {
                            if (goods.getEditText()==null){
                                goods.setEditText("1");
                            }
                            goodsList.add(goods);
                        }
                    }
                    Intent intent = new Intent();
                    intent.putExtra("chooseGoodsList", (Serializable) goodsList);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                } else {
                    Call<BaseModel<ChooseGoods>> neeDselGoods = netApi.neeDselGoods(constructionOrder());
                    neeDselGoods.enqueue(new Callback<BaseModel<ChooseGoods>>() {
                        @Override
                        public void onResponse(Response<BaseModel<ChooseGoods>> response, Retrofit retrofit) {
                            if (response.isSuccess()) {
                                if (("1").equals(response.body().getRes())) {
                                    Intent intent = new Intent();
                                    intent.putExtra("chooseGoodsList", (Serializable) response.body().getData());
                                    setResult(Activity.RESULT_OK, intent);
                                    finish();
                                } else {
                                    showToast(response.body().getMsg());
                                }
                            }
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            showToast("服务器错误");
                        }
                    });
                }


            }
        });

    }

    private HashMap<String, String> constructionOrder() {
        StringBuffer goodsidString = new StringBuffer();
        StringBuffer goodsnameString = new StringBuffer();
        StringBuffer goodssizeString = new StringBuffer();
        StringBuffer priceString = new StringBuffer();
        StringBuffer unitnameString = new StringBuffer();
        for (Goods goods : smbumitList) {
            goodsidString.append(goods.getGoodsid() + ";");
            goodsnameString.append(goods.getGoodsname() + ";");
            goodssizeString.append(goods.getGoodssize() + ";");
            priceString.append(goods.getPrice() + ";");
            unitnameString.append(goods.getMixunit() + ";");
        }

        goodsidString.deleteCharAt(goodsidString.length() - 1);
        goodsnameString.deleteCharAt(goodsnameString.length() - 1);
        goodssizeString.deleteCharAt(goodssizeString.length() - 1);
        priceString.deleteCharAt(priceString.length() - 1);
        unitnameString.deleteCharAt(unitnameString.length() - 1);
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("agentid", App.getApp().getUserInfo().getAgentid());
        map.put("goodsid", goodsidString.toString());
        map.put("goodsname", goodsnameString.toString());
        map.put("goodssize", goodssizeString.toString());
        map.put("price", priceString.toString());
        map.put("unitname", unitnameString.toString());
        Log.v("smbumitList", map.toString());
        return map;
    }

    int index;

    //0.全部 1.食品 2.护肤 3.卫生 4.材料
    @OnClick({R.id.food, R.id.material, R.id.skin, R.id.all, R.id.sanitation})
    void OnClick(View view) {
        chooseGoodsAdapter.clearAll();
        switch (view.getId()) {
            case R.id.food:
                name.setText("选择产品（食品）");
                index = 1;
                chooseGoodsAdapter.add(foodList);
                break;
            case R.id.skin:
                name.setText("选择产品（护肤）");
                index = 2;
                chooseGoodsAdapter.add(skinList);
                break;
            case R.id.sanitation:
                name.setText("选择产品（卫生用品）");
                index = 3;
                chooseGoodsAdapter.add(sanitationList);
                break;
            case R.id.material:
                name.setText("选择产品（包材）");
                index = 4;
                chooseGoodsAdapter.add(materialList);
                break;
            case R.id.all:
                name.setText("选择产品（全部）");
               /* chooseGoodsList = new ArrayList<>();
                chooseGoodsList.addAll(foodList);
                chooseGoodsList.addAll(skinList);
                chooseGoodsList.addAll(sanitationList);
                chooseGoodsList.addAll(materialList);*/
                chooseGoodsAdapter.add(list);
                index = 0;
                break;
        }
    }
}
