package xinlan.com.AiAoBi.requestJionIn;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xinlan.com.AiAoBi.App;
import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.UserInfo;
import xinlan.com.AiAoBi.entity.GeneralAgent;
import xinlan.com.AiAoBi.entity.GetCityInfo;
import xinlan.com.AiAoBi.entity.GetSetInfo;
import xinlan.com.AiAoBi.home.homeFragment.DisplayVIew;
import xinlan.com.AiAoBi.utils.GetImageString;
import xinlan.com.AiAoBi.utils.PhotoUtils;
import xinlan.com.AiAoBi.view.TitileView;

/**
 * Created by Administrator on 2016/10/19.
 */
public class JionInActivity extends MvpActivity<JionInView, JionInPreseter>
        implements JionInView, CompoundButton.OnCheckedChangeListener,
        AdapterView.OnItemSelectedListener {
    static public final int REQUEST_CODE_ASK_PERMISSIONS = 101;
    private static final int OFFICIAL = 5;
    private static final int GENERAL_AFENCY = 1;
    private static final int STAIR = 2;
    private static final int DEALER_DOWN = 3;

    @BindView(R.id.join_titleview)
    TitileView titileView;
    @BindView(R.id.join_edit_phone_numbe)
    EditText editPhoneNumbe;
    @BindView(R.id.join_edit_agentname)
    EditText editAgentname;
    @BindView(R.id.join_tv_refman)
    TextView tvRefman;
    @BindView(R.id.join_tv_slevel)
    TextView tvSlevel;
    @BindView(R.id.join_rank)
    Spinner spRank;
    @BindView(R.id.join_edit_referrer)
    EditText editReferrer;
    @BindView(R.id.join_edit_wechat)
    EditText editWechat;
    @BindView(R.id.join_iv_proof)
    ImageView ivProof;
    @BindView(R.id.join_edit_id_card)
    EditText editIdCard;
    @BindView(R.id.join_iv_id_card_front)
    ImageView ivIdCardFront;
    @BindView(R.id.join_iv_id_card_contrary)
    ImageView ivIdCardContrary;
    @BindView(R.id.join_chek_box)
    CheckBox chekBox;
    @BindView(R.id.join_tv_agreement)
    TextView tvAgreement;
    @BindView(R.id.join_btn_request_join)
    Button btnRequestJoin;
    @BindView(R.id.join_btn_cancle)
    Button btnCancle;
    @BindView(R.id.join_layout_id_card)
    LinearLayout layoutIDCard;
    @BindView(R.id.linear_layout_idCard_number)
    LinearLayout layoutIdCardNumber;
    @BindView(R.id.join_layout_address)
    LinearLayout layoutAddress;
    @BindView(R.id.join_province)
    Spinner spProvince;
    @BindView(R.id.join_city)
    Spinner spCity;
    @BindView(R.id.join_district)
    Spinner spDisrict;
    @BindView(R.id.join_layout_street)
    LinearLayout layoutStreet;
    @BindView(R.id.zhengfanmian)
    LinearLayout linearLayout;
    @BindView(R.id.join_edit_row)
    EditText editRow;
    @BindView(R.id.linear_dp)
    LinearLayout layoutDp;
    @BindView(R.id.join_tv_db)
    TextView textViewdp;
    @BindView(R.id.join_spslevel)
    Spinner spSlevel;
    @BindView(R.id.join_tv_with_proof)
    TextView tvWithProof;
    @BindView(R.id.join_tv_with_idcard_front)
    TextView tvWithFront;
    @BindView(R.id.join_tv_with_idcard_contrary)
    TextView tvWithCon;
    List<GetCityInfo.data> provinceList = new ArrayList<>();
    List<GetCityInfo.data> cityList = new ArrayList<>();
    List<String> goodsIds = new ArrayList<>();//goods_ids
    private PhotoUtils photoUtils;
    private BottomMenuDialog dialog;
    private Bitmap bitmap;
    //private Map<String, Bitmap> bitmaps;
    private Map<String, String> bitmapUrl;
    private GeneralAgent agentMsg;
    //保存照片转成的字符串
    private Map<String, String> map;
    /**
     * 照片处理完毕后回调
     */
    private PhotoUtils.McallBack back = new PhotoUtils.McallBack() {
        /**
         * @param bitmap//处理后的图片
         * @param path//原图的路径
         * @param state//图片的标记
         */
        @Override
        public void getBitmapFile(Bitmap bitmap, String path, int state) {
            //照片转成字符串
            String imageStr = GetImageString.getImageStr(path);
            Log.i("log", "----------图片大小：" + imageStr.length());
            JionInActivity.this.bitmap = bitmap;
            if (state == 1) {
                tvWithFront.setVisibility(View.GONE);
                ivIdCardFront.setBackground(new BitmapDrawable(null, bitmap));
                //ivIdCardFront.setImageBitmap(bitmap);
                agentMsg.setCardnofront(GetImageString.getImageStr(path));
            }
            if (state == 2) {
                tvWithCon.setVisibility(View.GONE);
                ivIdCardContrary.setBackground(new BitmapDrawable(null, bitmap));
                //ivIdCardContrary.setImageBitmap(bitmap);
                agentMsg.setCardnoback(GetImageString.getImageStr(path));
            }
            if (state == 0) {
                tvWithProof.setVisibility(View.GONE);
                ivProof.setBackground(new BitmapDrawable(null, bitmap));
                //ivProof.setImageBitmap(bitmap);
                agentMsg.setVoucher(GetImageString.getImageStr(path));
            }
            //bitmaps.put(state + "", bitmap);
            bitmapUrl.put(state + "", path);
            map.put(state + "", imageStr);
        }
    };
    private int state;
    private float width, height;
    private int intentData;
    private MTextWatch editIdCardWatcher = new MTextWatch() {
        @Override
        public void afterTextChanged(Editable editable) {
            cardno = editIdCard.getText().toString().trim();
        }
    };
    private MTextWatch editWechatWatcher = new MTextWatch() {

        @Override
        public void afterTextChanged(Editable editable) {
            weixin = editWechat.getText().toString().trim();
        }
    };
    private MTextWatch editPhoneNumbeWatcher = new MTextWatch() {

        @Override
        public void afterTextChanged(Editable editable) {
            phone = editPhoneNumbe.getText().toString().trim();
            if (phone.length() == 11) presenter.getAgentInfo("", phone, "1");//获取代理商信息
            else {
                editAgentname.setText(null);
                editAgentname.setEnabled(true);
            }
        }
    };
    private MTextWatch editReferrerWatcher = new MTextWatch() {
        @Override
        public void afterTextChanged(Editable editable) {
            refmantel = editReferrer.getText().toString().trim();
            if (refmantel.length() == 11) presenter.getAgentInfo("", refmantel, "2");
            else tvRefman.setText("");
        }
    };
    private MTextWatch editRowWatcher = new MTextWatch() {
        @Override
        public void afterTextChanged(Editable editable) {
            if (layoutStreet.getVisibility() == View.VISIBLE) {
                address = editRow.getText().toString().trim();
                Log.i("Log", "街道" + address);
            }
        }
    };
    private String agentname, cardno, slevel, phone, weixin, ismore,
            refman, refmantel, province, city, area, address, brandid, brand;
    private MTextWatch editAgentnameWatch = new MTextWatch() {
        @Override
        public void afterTextChanged(Editable editable) {
            agentname = editAgentname.getText().toString().trim();
        }
    };
    private ProgressDialog progressDialog;

    @NonNull
    @Override
    public JionInPreseter createPresenter() {
        return new JionInPreseter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_jion_in);
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= 19)//设置状态栏透明
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

    }

    /**
     * 根据Intent数据更改布局
     */
    private void changeLayout() {
        intentData = getIntent().getIntExtra("state",0);
        if (intentData != 0) {
            switch (intentData) {
                case OFFICIAL://官方申请注册
                    slevel = "2";
                    tvSlevel.setText("官方");
                    titileView.setTitle("官方申请加入");
                    break;
                case GENERAL_AFENCY://总代申请注册
                    slevel = "3";
                    tvSlevel.setText("总代");
                    titileView.setTitle("总代申请加入");
                    break;
                case STAIR://一级代理商申请加入
                    slevel = "4";
                    tvSlevel.setText("一级");
                    layoutAddress.setVisibility(View.GONE);
                    layoutStreet.setVisibility(View.GONE);
                    titileView.setTitle("一级代理商申请注册");
                    break;
                case DEALER_DOWN://经销商代理申请加入
                    tvSlevel.setText("经销商");
                    String[] strings = new String[]{"经销商", "特约", "花辦"};
                    ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.adapter_mspinner_geography_bar, strings);
                    arrayAdapter.setDropDownViewResource(R.layout.adapter_mspinner_geography);
                    spSlevel.setAdapter(arrayAdapter);
                    spSlevel.setOnItemSelectedListener(this);
                    textViewdp.setText("产品：");
                    layoutIdCardNumber.setVisibility(View.GONE);
                    layoutDp.setVisibility(View.VISIBLE);
                    tvSlevel.setVisibility(View.GONE);
                    layoutStreet.setVisibility(View.GONE);
                    layoutIDCard.setVisibility(View.GONE);
                    layoutAddress.setVisibility(View.GONE);
                    titileView.setTitle("申请注册");
                    linearLayout.setVisibility(View.GONE);
                    break;
            }
        }
    }
    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        initEvent();
        changeLayout();//更改布局
        presenter.getset("");//获取单品货全系
        if (layoutAddress.getVisibility() == View.VISIBLE)
            presenter.getProvince();//获取省份
    }
    private  UserInfo userInfo;
    private void initEvent() {
        editIdCard.addTextChangedListener(editIdCardWatcher);
        editPhoneNumbe.addTextChangedListener(editPhoneNumbeWatcher);
        editReferrer.addTextChangedListener(editReferrerWatcher);
        editWechat.addTextChangedListener(editWechatWatcher);
        editRow.addTextChangedListener(editRowWatcher);
        editAgentname.addTextChangedListener(editAgentnameWatch);
        chekBox.setOnCheckedChangeListener(this);
        spProvince.setOnItemSelectedListener(this);
        spDisrict.setOnItemSelectedListener(this);
        photoUtils = new PhotoUtils(back, this);
        //bitmaps = new HashMap<>();
        bitmapUrl = new HashMap<>();
        agentMsg = new GeneralAgent();
        map = new HashMap<>();
        dialog = new BottomMenuDialog(this);
        userInfo = App.getApp().getUserInfo();
    }

    @OnClick({R.id.join_iv_proof, R.id.join_tv_agreement,
            R.id.join_iv_id_card_front, R.id.join_iv_id_card_contrary,
            R.id.join_btn_request_join, R.id.join_btn_cancle,
            R.id.join_tv_with_proof, R.id.join_tv_with_idcard_front, R.id.join_tv_with_idcard_contrary})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.join_iv_proof:
                showPhotoDialog(0, ivProof.getWidth(), ivProof.getHeight());
                break;
            case R.id.join_iv_id_card_front:
                showPhotoDialog(1, ivIdCardFront.getWidth(), ivIdCardFront.getHeight());
                break;
            case R.id.join_iv_id_card_contrary:
                showPhotoDialog(2, ivIdCardContrary.getWidth(), ivIdCardContrary.getHeight());
                break;
            case R.id.join_btn_request_join:
                switch (intentData) {
                    case 3:
                        if (phone == null | "".equals(phone))
                            showToast("请输入手机号", Toast.LENGTH_SHORT);
                        else if (agentname == null | "".equals(agentname))
                            showToast("用户名称不能为空", Toast.LENGTH_SHORT);
                        else if (refmantel == null | "".equals(refmantel))
                            showToast("请输入推荐人号码", Toast.LENGTH_SHORT);
                        else if (tvRefman.getText() == null | "".equals(tvRefman.getText()))
                            showToast("推荐人号码输入有误\n推荐人名称获取失败", Toast.LENGTH_SHORT);
                        else if (weixin == null | "".equals(weixin))
                            showToast("请输入微信号", Toast.LENGTH_SHORT);
                        else if (map.get("0") == null) showToast("请上传付款凭证", Toast.LENGTH_SHORT);
                        /*else if (cardno == null | "".equals(cardno))
                            showToast("请输入身份证号码", Toast.LENGTH_SHORT);*/
                        else if (!chekBox.isChecked()) showToast("需同意注册协议", Toast.LENGTH_SHORT);
                        else {
                            showProgressDialog("正在提交");
                            presenter.DealerRequestJoin(agentname, slevel, phone, weixin, ismore,
                                    brandid, brand, refman, refmantel, map.get("0"));
                        }
                        break;
                    case 2:

                        if (phone == null | "".equals(phone))
                            showToast("请输入手机号", Toast.LENGTH_SHORT);
                        else if (agentname == null | "".equals(agentname))
                            showToast("用户名称不能为空", Toast.LENGTH_SHORT);
                        else if (refmantel == null | "".equals(refmantel))
                            showToast("请输入推荐人号码", Toast.LENGTH_SHORT);
                        else if (tvRefman.getText() == null | "".equals(tvRefman.getText()))
                            showToast("推荐人号码输入有误\n推荐人名称获取失败", Toast.LENGTH_SHORT);
                        else if (weixin == null | "".equals(weixin))
                            showToast("请输入微信号", Toast.LENGTH_SHORT);
                        else if (map.get("0") == null) showToast("请上传付款凭证", Toast.LENGTH_SHORT);
                        else if (cardno == null | "".equals(cardno))
                            showToast("请输入身份证号码", Toast.LENGTH_SHORT);
                        else if (map.get("1") == null) showToast("请上传身份证正面照片", Toast.LENGTH_SHORT);
                        else if (map.get("2") == null) showToast("请上传身份证反面照片", Toast.LENGTH_SHORT);
                        else if (!chekBox.isChecked()) showToast("需同意注册协议", Toast.LENGTH_SHORT);
                        else {
                            showProgressDialog("正在提交");
                            presenter.StairRequestJoin(agentname, cardno, slevel, phone, weixin, ismore,
                                    brandid, brand, refman, refmantel, map.get("0"), map.get("1"), map.get("2"));
                        }
                        break;
                    case 1:
                    case 5:
                        Log.i("log", "onClick:" + editAgentname.getText() + "/");
                        if (phone == null | "".equals(phone))
                            showToast("请输入手机号", Toast.LENGTH_SHORT);
                        else if (agentname == null | "".equals(agentname))
                            showToast("用户名称不能为空", Toast.LENGTH_SHORT);
                        else if (refmantel == null | "".equals(refmantel))
                            showToast("请输入推荐人号码", Toast.LENGTH_SHORT);
                        else if (tvRefman.getText() == null | "".equals(tvRefman.getText()))
                            showToast("推荐人号码输入\n推荐人名称获取失败", Toast.LENGTH_SHORT);
                        else if (weixin == null | "".equals(weixin))
                            showToast("请输入微信号", Toast.LENGTH_SHORT);
                        else if (map.get("0") == null) showToast("请上传付款凭证", Toast.LENGTH_SHORT);
                        else if (cardno == null | "".equals(cardno))
                            showToast("请输入身份证号码", Toast.LENGTH_SHORT);
                        else if (map.get("1") == null) showToast("请上传身份证正面照片", Toast.LENGTH_SHORT);
                        else if (map.get("2") == null) showToast("请上传身份证反面照片", Toast.LENGTH_SHORT);
                        else if (address == null | "".equals(address))
                            showToast("请输入地址", Toast.LENGTH_SHORT);
                        else if (!chekBox.isChecked()) showToast("需同意注册协议", Toast.LENGTH_SHORT);
                        else {
                            showProgressDialog("正在提交");
                            presenter.GeneralRequestJion(agentname, cardno, slevel, phone, weixin, ismore,
                                    refman, refmantel, province, city, area, address, brandid, brand,
                                    map.get("0"), map.get("1"), map.get("2"));
                        }
                        break;
                }
                break;
            case R.id.join_tv_with_proof:
                showPhotoDialog(0, ivProof.getWidth(), ivProof.getHeight());
                break;
            case R.id.join_tv_with_idcard_front:
                showPhotoDialog(1, ivIdCardFront.getWidth(), ivIdCardFront.getHeight());
                break;
            case R.id.join_tv_with_idcard_contrary:
                showPhotoDialog(2, ivIdCardContrary.getWidth(), ivIdCardContrary.getHeight());
                break;
            case R.id.join_tv_agreement:
                Intent intent = new Intent(this, AgreeMent.class);
                startActivity(intent);
                break;
            case R.id.join_btn_cancle:
                finish();
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        Log.i("Log", "adapterView.getId:" + adapterView.getId());
        Log.i("Log", "R.id.join_province:" + R.id.join_province);
        switch (adapterView.getId()) {

            case R.id.join_rank:
                if (spRank.getSelectedItem() != null) {
                    brand = spRank.getSelectedItem().toString();
                    if ("全系".equals(brand)) {
                        ismore = "1";
                        brand = "";
                        brandid = "";
                    } else {
                        ismore = "0";
                        brandid = brand = spRank.getSelectedItem().toString();
                        brandid = goodsIds.get(i);
                    }
                }
                /**
                 * 获取级别信息
                 */
                break;
            case R.id.join_province:
                if (layoutAddress.getVisibility() == View.VISIBLE) {
                    if (spProvince.getSelectedItem() != null) {
                        province = spProvince.getSelectedItem().toString();
                        Log.i("halo", "onItemSelected:省： "+province);
                        presenter.getCity(provinceList.get(i).getCity_name());
                    }
                }
                break;
            case R.id.join_city:
                if (layoutAddress.getVisibility() == View.VISIBLE) {
                    if (spCity.getSelectedItem() != null) {
                        city = spCity.getSelectedItem().toString();
                        presenter.getDisrict(cityList.get(i).getCity_name());
                    }
                }
                break;
            case R.id.join_district:
                if (layoutAddress.getVisibility() == View.VISIBLE) {
                    if (spDisrict.getSelectedItem() != null) {
                        area = spDisrict.getSelectedItem().toString();
                    }
                }
                break;
            case R.id.join_spslevel:
                int id = spSlevel.getSelectedItemPosition();
                if (id == 0) slevel = "5";
                if (id == 1) slevel = "6";
                if (id == 2) slevel = "7";
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    /**
     * 弹出底部框
     */
    @TargetApi(23)
    public void showPhotoDialog(final int state, float width, float height) {
        this.state = state;
        this.width = width;
        this.height = height;
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }

        dialog.setConfirmListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                if (Build.VERSION.SDK_INT >= 23) {
                    int checkPermission = checkSelfPermission(Manifest.permission.CAMERA);
                    if (checkPermission != PackageManager.PERMISSION_GRANTED) {
                        if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                            requestPermissions(new String[]{Manifest.permission.CAMERA},
                                    REQUEST_CODE_ASK_PERMISSIONS);
                        } else {
                            new android.app.AlertDialog.Builder(JionInActivity.this)
                                    .setMessage("您需要在设置里打开相机权限。")
                                    .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            requestPermissions(new String[]{Manifest.permission.CAMERA},
                                                    REQUEST_CODE_ASK_PERMISSIONS);
                                        }
                                    })
                                    .setNegativeButton("取消", null)
                                    .create().show();
                        }
                        return;
                    }
                }
                photoUtils.takePicture();
            }
        });
        dialog.setMiddleListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                photoUtils.selectPicture();
            }
        });
        //弹出dialog显示大图片
        dialog.setCheckListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                if (bitmapUrl.get(state + "") != null) {
                    // dialog.setCheckBtnVisible(View.VISIBLE);
                    Intent intent = new Intent(JionInActivity.this, DisplayVIew.class);
                    intent.putExtra("bitmap", bitmapUrl.get(state + ""));
                    startActivity(intent);
                } else {
                    showToast("请先选择照片", Toast.LENGTH_SHORT);
                }
            }
                /*final Dialog dialog = new Dialog(JionInActivity.this, R.style.dialogFullscreen);
                dialog.setContentView(R.layout.dialog_diplay_photo);
                ImageView imageView = (ImageView) dialog.findViewById(R.id.dialog_diaplay_imageView);
                if (bitmaps.get(state + "") != null)
                    imageView.setImageBitmap(bitmaps.get(state + ""));
                else {
                    Toast.makeText(JionInActivity.this, "请先选择图片", Toast.LENGTH_SHORT).show();
                    return;
                }
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }*/
        });
        dialog.show();
    }

    @Override
    public void showToast(String masge, int dur) {
        Toast.makeText(this, masge, dur).show();
    }

    /**
     * 初始化spRank
     *
     * @param list
     */
    @Override
    public void setDataToSpRank(List<GetSetInfo.data> list) {
        List<String> spRankData = new ArrayList<>();//goods_name
        for (int i = 0; i < list.size(); i++) {
            spRankData.add(list.get(i).getGoods_name());
            goodsIds.add(list.get(i).getGoodsid());
            if ("全系".equals(list.get(i).getGoods_name())) {
                ismore = "1";
                brand = "";
                brandid = "";
            } else ismore = "0";
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.adapter_mspinner_bar, spRankData);
        arrayAdapter.setDropDownViewResource( R.layout.adapter_mspinner);
        spRank.setAdapter(arrayAdapter);
        spRank.setOnItemSelectedListener(this);
    }

    /**
     * 初始化spProvince
     *
     * @param list
     */
    @Override
    public void setDataToSpProvince(List<GetCityInfo.data> list) {
        //把数据保存到本地
        this.provinceList = list;
        List<String> provinceData = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            provinceData.add(list.get(i).getCity_name());
        }
        ArrayAdapter<String> provinceAdapter = new ArrayAdapter<String>(this,R.layout.adapter_mspinner_geography_bar, provinceData);
        provinceAdapter.setDropDownViewResource(R.layout.adapter_mspinner_geography);
        spProvince.setAdapter(provinceAdapter);
        spProvince.setOnItemSelectedListener(this);
        for (int i=0;i<provinceData.size();i++){

            if (App.getApp().getLoginProvice()==null) return;
            if (App.getApp().getLoginProvice().equals(provinceData.get(i))){
                spProvince.setSelection(i,true);
            }
        }
    }

    /**
     * 初始化spCity
     *
     * @param list
     */
    @Override
    public void setDatatoSpCity(List<GetCityInfo.data> list) {
        this.cityList = list;
        List<String> cityData = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            cityData.add(list.get(i).getCity_name());
        }
        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(this, R.layout.adapter_mspinner_geography_bar, cityData);
        cityAdapter.setDropDownViewResource(R.layout.adapter_mspinner_geography);
        spCity.setAdapter(cityAdapter);
        spCity.setOnItemSelectedListener(this);
        for (int i=0;i<cityData.size();i++){
            if (App.getApp().getLoginCity()==null) return;
            if (App.getApp().getLoginCity().equals(cityData.get(i))){
                spCity.setSelection(i,true);
            }
        }
    }

    /**
     * 初始化spDisrict
     *
     * @param
     */
    @Override
    public void setDatatoSpDisrict(List<GetCityInfo.data> list) {
        List<String> disrictData = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            disrictData.add(list.get(i).getCity_name());
            Log.i("log", "城市名称：" + list.get(i).getCity_name() + "provinceList.size" + list.size());
        }
        ArrayAdapter<String> disrictAdapter = new ArrayAdapter<String>(this, R.layout.adapter_mspinner_geography_bar, disrictData);
        disrictAdapter.setDropDownViewResource(R.layout.adapter_mspinner_geography);
        spDisrict.setAdapter(disrictAdapter);
        spDisrict.setOnItemSelectedListener(this);
    }

    @Override
    public void showProgressDialog(String messege) {
        if (progressDialog != null) {
            progressDialog.cancel();
        }
        progressDialog = ProgressDialog.show(this, null, messege);
    }

    @Override
    public void dimissProgressDialog() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    @Override
    public void finishActivity() {
        this.finish();
    }

    @Override
    public void setTextToAgentname(String agentname) {
        editAgentname.setText(agentname);
        editAgentname.setEnabled(false);
        this.agentname = agentname;
    }

    @Override
    public void setTextTorefman(String refman) {
        tvRefman.setText(refman);
        this.refman = refman;
    }

    @Override
    public void setTextToSlevel(String slevel) {
        this.slevel = slevel;
        tvSlevel.setText(slevel);
    }

    @Override
    public void setEnableForEditAgentName(Boolean b) {
        editAgentname.setEnabled(b);
    }

    @Override
    public void showMsgDialog(String msg, final int style) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(msg);
        builder.setTitle("提示信息!");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (style == 0)
                    dialog.dismiss();
                if (style == 1)
                    finish();
            }
        });
        builder.setCancelable(false);
        builder.create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PhotoUtils.INTENT_CROP:
            case PhotoUtils.INTENT_TAKE:
            case PhotoUtils.INTENT_SELECT:
                Log.i("Log", width + "///" + height);
                photoUtils.onActivityResult(requestCode, resultCode, data, state, width, height);
                break;
        }
    }

}
