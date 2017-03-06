package xinlan.com.AiAoBi;


import com.squareup.okhttp.ResponseBody;

import java.util.Map;


import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import xinlan.com.AiAoBi.entity.AddAaginOrder;
import xinlan.com.AiAoBi.entity.AgentAddress;
import xinlan.com.AiAoBi.entity.AgentmsgInfo;
import xinlan.com.AiAoBi.entity.AgentpaymentaInfo;
import xinlan.com.AiAoBi.entity.AgentpaymentdInfo;
import xinlan.com.AiAoBi.entity.BasenoteInfo;
import xinlan.com.AiAoBi.entity.BaseproduceInfo;
import xinlan.com.AiAoBi.entity.BasesaleInfo;
import xinlan.com.AiAoBi.entity.BasesaleaxisInfo;
import xinlan.com.AiAoBi.entity.ChooseGoods;
import xinlan.com.AiAoBi.entity.DoselagentaInfo;
import xinlan.com.AiAoBi.entity.DoselagentbInfo;
import xinlan.com.AiAoBi.entity.DoselagentfInfo;
import xinlan.com.AiAoBi.entity.Doselagentg;
import xinlan.com.AiAoBi.entity.DoselagentkInfo;
import xinlan.com.AiAoBi.entity.DoselagentqInfo;
import xinlan.com.AiAoBi.entity.DoselbargainInfo;
import xinlan.com.AiAoBi.entity.DoselbargaindetInfo;
import xinlan.com.AiAoBi.entity.ExpressInfo;
import xinlan.com.AiAoBi.entity.GetAgentInfo;
import xinlan.com.AiAoBi.entity.GetCityInfo;
import xinlan.com.AiAoBi.entity.GetLevelInfo;
import xinlan.com.AiAoBi.entity.GetSetInfo;
import xinlan.com.AiAoBi.entity.GetagentdownInfo;
import xinlan.com.AiAoBi.entity.GetagentdownaInfo;
import xinlan.com.AiAoBi.entity.GetagentpriceInfo;
import xinlan.com.AiAoBi.entity.GetagreeInfo;
import xinlan.com.AiAoBi.entity.GetrewardInfo;
import xinlan.com.AiAoBi.entity.GetrewardtypeInfo;
import xinlan.com.AiAoBi.entity.GettotalInfo;
import xinlan.com.AiAoBi.entity.Goods;
import xinlan.com.AiAoBi.entity.GoodsID;
import xinlan.com.AiAoBi.entity.MBaseModel;
import xinlan.com.AiAoBi.entity.Msgacceptsend;
import xinlan.com.AiAoBi.entity.Msgnostock;
import xinlan.com.AiAoBi.entity.Msgnostockdet;
import xinlan.com.AiAoBi.entity.Msgordergoods;
import xinlan.com.AiAoBi.entity.Msgsalevisit;
import xinlan.com.AiAoBi.entity.Msgsalevisitdet;
import xinlan.com.AiAoBi.entity.Msgtakegoods;
import xinlan.com.AiAoBi.entity.Msgupgrade;
import xinlan.com.AiAoBi.entity.Msgupsendcode;
import xinlan.com.AiAoBi.entity.Msgupsenddet;
import xinlan.com.AiAoBi.entity.Msgupsendgoods;
import xinlan.com.AiAoBi.entity.MyorderInfo;
import xinlan.com.AiAoBi.entity.MysendgoodsInfo;
import xinlan.com.AiAoBi.entity.NeedbargainnumInfo;
import xinlan.com.AiAoBi.entity.OkgoodsInfo;
import xinlan.com.AiAoBi.entity.SalescanInfo;
import xinlan.com.AiAoBi.entity.ScanResult;
import xinlan.com.AiAoBi.entity.ScangetbargaincodeInfo;
import xinlan.com.AiAoBi.entity.ScangetmanInfo;
import xinlan.com.AiAoBi.entity.ScanmansumIfo;
import xinlan.com.AiAoBi.home.enity.BaseModel;
import xinlan.com.AiAoBi.home.enity.WaitExaminationInfo;

/**
 * Created by LSY on 2016/9/20.
 */
public interface NetApi {
    //请求服务器qpk信息
    @GET("/weather/index?format=2&cityname=%E8%8B%8F%E5%B7%9E&key=8a3e201a4cf27e4825bacd38f96986c4")
    Call<ResponseBody> getApkInfo();

    //登录接口  @Field("curuserid") String curuserid,@Field("mobile_phone") String mobile_phone
    @FormUrlEncoded
    @POST("app?eventcode=login")
    Call<GetAgentInfo> login(@FieldMap Map<String, String> map);
    //测试接口
    @FormUrlEncoded
    @POST("app?eventcode=login")
    Call<GetAgentInfo> check(@Field("check") String check);

    @FormUrlEncoded
    @POST("app?eventcode=logininfo")
    Call<GetAgentInfo> logininfo(@FieldMap Map<String, String> map);

    //密码找回接口
    @FormUrlEncoded
    @POST("app?eventcode=")
    Call<ResponseBody> findPassword();
    //1_6经销商注册协议
    @FormUrlEncoded
    @POST("app?eventcode=getagree")
    Call<BaseModel<GetagreeInfo>> getagree(@Field("curuserid") String curuserid);

    /**
     * 获取代理商信息
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("app?eventcode=login")
    Call<GetAgentInfo> getAgentInfo(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app?eventcode=delagent")
    Call<BaseModel> delagent(@Field("phone") String phone);

    /**
     * 获取代理商级别
     *
     * @param curuserid
     * @return
     */
    @FormUrlEncoded
    @POST("app?eventcode=getlevel")
    Call<GetLevelInfo> getLevel(@Field("curuserid") String curuserid);

    /**
     * 获取单品或全系
     *
     * @param curuserid
     * @return
     */
    @FormUrlEncoded
    @POST("app?eventcode=getdp")
    Call<GetSetInfo> getSet(@Field("curuserid") String curuserid);

    /**
     * 获取省市区
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("app?eventcode=getcity")
    Call<GetCityInfo> getCity(@FieldMap Map<String, String> map);

    /**
     * 总代理申请加入
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("app?eventcode=applyaddc")
    Call<ResponseBody> GeneralRequestJoin(@FieldMap Map<String, String> map);

    /**
     * 一级代理申请加入
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("app?eventcode=applyaddb")
    Call<ResponseBody> StairRequestJoin(@FieldMap Map<String, String> map);

    /**
     * 经销商（一级以下）代理申请加入
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("app?eventcode=applyadda")
    Call<ResponseBody> DealerRequestJoin(@FieldMap Map<String, String> map);


    //新代理商加入待审
    @FormUrlEncoded
    @POST("app?eventcode=applyaddcheck")
    Call<BaseModel<WaitExaminationInfo>> requestWaitExaminationInfo(@FieldMap Map<String, String> map);


    //新代理商加入待审核（同意加入和反回修改）
    @FormUrlEncoded
    @POST("app?eventcode=applyaddexe")
    Call<BaseModel> examinationUpdate(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app?eventcode=agentup")
    Call<BaseModel> agentup(@FieldMap Map<String, String> map);


    //收货人地址列表
    @FormUrlEncoded
    @POST("app?eventcode=agentgetaddr")
    Call<BaseModel<AgentAddress>> agentgetAddr(@FieldMap Map<String, String> map);

    //新增收货地址
    @FormUrlEncoded
    @POST("app?eventcode=agentaddaddr")
    Call<BaseModel> agentAddaddr(@FieldMap Map<String, String> map);

    //删除收货地址
    @FormUrlEncoded
    @POST("app?eventcode=agentdeladdr")
    Call<BaseModel> agentDeladdr(@FieldMap Map<String, String> map);

    //修改收货地址
    @FormUrlEncoded
    @POST("app?eventcode=agentupdateaddr")
    Call<BaseModel> agentupDateAddr(@FieldMap Map<String, String> map);

    //我的货品
    @FormUrlEncoded
    @POST("app?eventcode=getgoods")
    Call<BaseModel<Goods>> getGoods(@FieldMap Map<String, String> map);


    //我的货品确认选择
    @FormUrlEncoded
    @POST("app?eventcode=needselgoods")
    Call<BaseModel<ChooseGoods>> neeDselGoods(@FieldMap Map<String, String> map);


    //确认订货
    @FormUrlEncoded
    @POST("app?eventcode=needokgoods")
    Call<BaseModel> needOkGoods(@FieldMap Map<String, String> map);

    //获取直属下级的订单
    @FormUrlEncoded
    @POST("app?eventcode=doselbargain")
    Call<BaseModel<DoselbargainInfo>> doselbargain(@FieldMap Map<String, String> map);

    //获取直属下级的订单详情
    @FormUrlEncoded
    @POST("app?eventcode=doselbargaindet")
    Call<BaseModel<DoselbargaindetInfo>> doselbargaindet(@FieldMap Map<String, String> map);

    //获取直属下级的代理订单
    @FormUrlEncoded
    @POST("app?eventcode=doselagenta")
    Call<BaseModel<DoselagentaInfo>> doselagenta(@FieldMap Map<String, String> map);

    //获取直属下级的代理订单详情
    @FormUrlEncoded
    @POST("app?eventcode=doselagentb")
    Call<BaseModel<DoselagentbInfo>> doselagentb(@FieldMap Map<String, String> map);

    //选中产品代发
    @FormUrlEncoded
    @POST("app?eventcode=doselagentc")
    Call<BaseModel> doselagentc(@FieldMap Map<String, String> map);

    //3_2_6处理下级订单(显示我再加点货的内容)
    @FormUrlEncoded
    @POST("app?eventcode=doselagentd")
    Call<BaseModel<AddAaginOrder>> doselagentd(@FieldMap Map<String, String> map);

    //我再加点
    @FormUrlEncoded
    @POST("app?eventcode=doselagente")
    Call<BaseModel> doselagente(@FieldMap Map<String, String> map);

    //直属订单中现在扫货发货-开始扫描
    @FormUrlEncoded
    @POST("app?eventcode=doselagentf")
    Call<BaseModel<DoselagentfInfo>> doselagentf(@FieldMap Map<String, String> map);

    //直属订单中现在扫货发货中明细
    @FormUrlEncoded
    @POST("app?eventcode=doselagentg")
    Call<BaseModel<Doselagentg>> doselagentg(@FieldMap Map<String, String> map);

    //直属订单中现在扫货发货，输入条码
    @FormUrlEncoded
    @POST("app?eventcode=doselagenth")
    Call<BaseModel<ScanResult>> doselagenth(@FieldMap Map<String, String> map);

    //直属订单中现在扫货发货，确认发货
    @FormUrlEncoded
    @POST("app?eventcode=doselagentj")
    Call<BaseModel> doselagentj(@FieldMap Map<String, String> map);

    //3_2_13处理下级订单(提交订单-汇总)
    @FormUrlEncoded
    @POST("app?eventcode=doselagentq")
    Call<BaseModel<DoselagentqInfo>> doselagentq(@FieldMap Map<String, String> map);

    //3_2_12处理下级订单(提交订单)
    @FormUrlEncoded
    @POST("app?eventcode=doselagentk")
    Call<BaseModel<DoselagentkInfo>> doselagentk(@FieldMap Map<String, String> map);

    //3_2_14处理下级订单(直接下订单)
    @FormUrlEncoded
    @POST("app?eventcode=doselagentw")
    Call<BaseModel> doselagentw(@FieldMap Map<String, String> map);

    //2_4_1代理商收款登记（选择付款人-显示我的直属下级）
    @FormUrlEncoded
    @POST("app?eventcode=agentpaymenta")
    Call<BaseModel<AgentpaymentaInfo>> agentpaymenta(@FieldMap Map<String, String> map);

    //2_4_2代理商收款登记（选择付款人-按电话或名称查找的直属
    @FormUrlEncoded
    @POST("app?eventcode=agentpaymentb")
    Call<BaseModel<AgentpaymentaInfo>> agentpaymentb(@FieldMap Map<String, String> map);

    //2_4_3代理商收款登记（确认收款）
    @FormUrlEncoded
    @POST("app?eventcode=agentpaymentc")
    Call<BaseModel> agentpaymentc(@FieldMap Map<String, String> map);

    //2_4_4代理商收款登记（查询最近三个月内首款到账历史记录）
    @FormUrlEncoded
    @POST("app?eventcode=agentpaymentd")
    Call<BaseModel<AgentpaymentdInfo>> agentpaymentd(@Field("agentid") String agentid);

    //4_3个人中心中-我的授权证书
    @FormUrlEncoded
    @POST("app?eventcode=getagentright")
    Call<BaseModel> getagentright(@FieldMap Map<String, String> map);

    //4_2个人中心中-我的直属下级
    @FormUrlEncoded
    @POST("app?eventcode=getagentdown")
    Call<BaseModel<GetagentdownInfo>> getagentdown(@Field("agentid") String agentid);

    //4_1个人中心中-我的价格
    @FormUrlEncoded
    @POST("app?eventcode=getagentprice")
    Call<BaseModel<GetagentpriceInfo>> getagentprice(@Field("agentid") String agentid);

    //4_2_1个人中心中-我的直属下级(显示本月拿货和最近三个月拿货)
    @FormUrlEncoded
    @POST("app?eventcode=getagentdowna")
    Call<BaseModel<GetagentdownaInfo>> getagentdowna(@Field("agentid") String agentid, @Field("quyflag") String quyflag);

    //4_2_2个人中心中-我的直属下级(显示所有拿货)
    @FormUrlEncoded
    @POST("app?eventcode=getagentdownb")
    Call<BaseModel<GetagentdownaInfo>> getagentdownb(@FieldMap Map<String, String> map);

    //4_6个人中心-我的累积
    @FormUrlEncoded
    @POST("app?eventcode=gettotal")
    Call<BaseModel<GettotalInfo>> gettotal(@FieldMap Map<String, String> map);

    //5_1货品签收_(显示申请订单)
    @FormUrlEncoded
    @POST("app?eventcode=okgoods")
    Call<BaseModel<OkgoodsInfo>> okgoods(@Field("agentid") String agentid);

    //5_2货品签收_(查询签收内容)
    @FormUrlEncoded
    @POST("app?eventcode=okquygoods")
    Call<MBaseModel> okquygoods(@FieldMap Map<String, String> map);

    //5_3货品签收_(确认签收)
    @FormUrlEncoded
    @POST("app?eventcode=okacceptgoods")
    Call<BaseModel> okacceptgoods(@Field("smkeyid") String smkeyid, @Field("okman") String okman);

    //6_1扫码发货_(选择收货人)
    @FormUrlEncoded
    @POST("app?eventcode=scangetman")
    Call<BaseModel<ScangetmanInfo>> scangetman(@Field("agentid") String agentid);

    //6_2扫码发货_(选择收货人确认后，显示收货人货品汇总信息)
    @FormUrlEncoded
    @POST("app?eventcode=scanmansum")
    Call<BaseModel<ScanmansumIfo>> scanmansum(@Field("agentid") String agentid, @Field("refagent_id") String refagent_id);

    //6_3扫码发货_(通过收货人，查询订单号)
    @FormUrlEncoded
    @POST("app?eventcode=scangetbargaincode")
    Call<BaseModel<ScangetbargaincodeInfo>> scangetbargaincode(@Field("agentid") String agentid, @Field("refagent_id") String refagent_id);

    //6_1_1扫码发货_(选择收货人_通过姓名查询单个收货人)
    @FormUrlEncoded
    @POST("app?eventcode=scangetoneman")
    Call<BaseModel<ScangetmanInfo>> scangetoneman(@Field("agentid") String agentid, @Field("agentname") String agentname);

    //7_1零销扫码_(扫码)
    @FormUrlEncoded
    @POST("app?eventcode=salebarcode")
    Call<BaseModel<SalescanInfo>> salescan(@FieldMap Map<String, String> map);

    //7_4零销扫码_(确认零销)
    @FormUrlEncoded
    @POST("app?eventcode=saleok")
    Call<BaseModel> saleok(@FieldMap Map<String, String> map);

    //9_1我的提醒_(未读提醒)
    @FormUrlEncoded
    @POST("app?eventcode=agentmsg")
    Call<BaseModel<AgentmsgInfo>> agentmsg(@Field("agentid") String agentid);

    //9_2我的提醒_(最近一周已读)
    @FormUrlEncoded
    @POST("app?eventcode=agentweekmsg")
    Call<BaseModel<AgentmsgInfo>> agentweekmsg(@Field("agentid") String agentid);

    //8_2代理商比拼一览表
    @FormUrlEncoded
    @POST("app?eventcode=basesale")
    Call<BaseModel<BasesaleInfo>> basesale(@Field("agentid") String agentid);

    //8_1官方公告
    @FormUrlEncoded
    @POST("app?eventcode=basenote")
    Call<BaseModel<BasenoteInfo>> basenote(@Field("ismore") String ismore);


    //8_3产品反馈产品海报
    @FormUrlEncoded
    @POST("app?eventcode=baseproduce")
    Call<BaseModel<BaseproduceInfo>> baseproduce(@Field("ismore") String ismore,@Field("mkeyid") String mkeyid);

    //10_1扫描发货_(输入条码)scanbarcode
    @FormUrlEncoded
    @POST("app?eventcode=scanbarcode")
    Call<BaseModel<GoodsID>> scanbarcode(@FieldMap Map<String, String> map);

    // 10_3扫描发货_(获取要货数、已发数及货品信息 )
    @FormUrlEncoded
    @POST("app?eventcode=scangetnum")
    Call<BaseModel<Doselagentg>> scangetnum(@FieldMap Map<String, String> map);

    //10_2扫描发货_(确认发货 )scanok
    @FormUrlEncoded
    @POST("app?eventcode=scanok")
    Call<BaseModel> scanok(@FieldMap Map<String, String> map);

    //11_1我的订单_(下级订单查询 )
    @FormUrlEncoded
    @POST("app?eventcode=myorder")
    Call<BaseModel<MyorderInfo>> myorder(@FieldMap Map<String, String> map);

    //11_1_1我的订单_(下级订单查询 ， 代理商_查询每月明细)
    @FormUrlEncoded
    @POST("app?eventcode=myorderdet")
    Call<BaseModel<MyorderInfo>> myorderdet(@FieldMap Map<String, String> map);

    //11_2我的订单_(我的订单查询 )
    @FormUrlEncoded
    @POST("app?eventcode=myorderquy")
    Call<BaseModel<MyorderInfo>> myorderquy(@FieldMap Map<String, String> map);

    //3_1_2我的货品(待处理订单数量)
    @FormUrlEncoded
    @POST("app?eventcode=needbargainnum")
    Call<BaseModel<NeedbargainnumInfo>> needbargainnum(@Field("agentid") String agentid);

    //1_5获取快递公司
    @FormUrlEncoded
    @POST("app?eventcode=getexpress")
    Call<BaseModel<ExpressInfo>> getexpress(@Field("curuserid") String curuserid);

    //12_2客户中心_(发货查询-选择收货人 )
    @FormUrlEncoded
    @POST("app?eventcode=mysendgoodsman")
    Call<BaseModel<AgentpaymentaInfo>> mysendgoodsman(@FieldMap Map<String, String> map);

    //12_1客户中心_(发货查询 )
    @FormUrlEncoded
    @POST("app?eventcode=mysendgoods")
    Call<BaseModel<MysendgoodsInfo>> mysendgoods(@FieldMap Map<String, String> map);

    //9_6我的提醒_(零售客户回访)
    @FormUrlEncoded
    @POST("app?eventcode=msgsalevisit")
    Call<BaseModel<Msgsalevisit>> msgsalevisit(@Field("agentid") String agentid);

    //9_5我的提醒_(提醒客户订货)
    @FormUrlEncoded
    @POST("app?eventcode=msgordergoods")
    Call<BaseModel<Msgordergoods>> msgordergoods(@Field("agentid") String agentid);

    //9_7我的提醒_(临近升级)
    @FormUrlEncoded
    @POST("app?eventcode=msgupgrade")
    Call<BaseModel<Msgupgrade>> msgupgrade(@Field("agentid") String agentid);

    //9_8我的提醒_(提醒签收货品)
    @FormUrlEncoded
    @POST("app?eventcode=msgtakegoods")
    Call<BaseModel<Msgtakegoods>> msgtakegoods(@Field("agentid") String agentid);

    //9_5_1我的提醒_(  提醒客户订货_发货提醒给客户)
    @FormUrlEncoded
    @POST("app?eventcode=msgordergoodsdet")
    Call<BaseModel> msgordergoodsdet(@FieldMap Map<String, String> map);

    //9_6_1我的提醒_(零售客户回访_明细)
    @FormUrlEncoded
    @POST("app?eventcode=msgsalevisitdet")
    Call<BaseModel<Msgsalevisitdet>> msgsalevisitdet(@Field("agentid") String agentid);

    //9_6_2我的提醒_(零售客户回访_保存回访备注)
    @FormUrlEncoded
    @POST("app?eventcode=msgsalevisitdetins")
    Call<BaseModel> msgsalevisitdetins(@FieldMap Map<String, String> map);

    //9_10我的提醒_(提醒上级发货)
    @FormUrlEncoded
    @POST("app?eventcode=msgupsendgoods")
    Call<BaseModel<Msgupsendgoods>> msgupsendgoods(@Field("agentid") String agentid);

    //9_10_1我的提醒_(提醒签收货品，获取订单号)
    @FormUrlEncoded
    @POST("app?eventcode=msgupsendcode")
    Call<BaseModel<Msgupsendcode>> msgupsendcode(@FieldMap Map<String, String> map);

    //9_10_2我的提醒_(提醒签收货品，通过订单号查询明细)
    @FormUrlEncoded
    @POST("app?eventcode=msgupsenddet")
    Call<BaseModel<Msgupsenddet>> msgupsenddet(@FieldMap Map<String, String> map);

    //9_10_3我的提醒_(提醒签收货品，发送提醒给上级)
    @FormUrlEncoded
    @POST("app?eventcode=msgupsendins")
    Call<BaseModel> msgupsendins(@FieldMap Map<String, String> map);

    //9_11我的提醒_(我的货品不足)
    @FormUrlEncoded
    @POST("app?eventcode=msgnostock")
    Call<BaseModel<Msgnostock>> msgnostock(@Field("agentid") String agentid);

    //9_11我的提醒_(我的货品不足_明细)
    @FormUrlEncoded
    @POST("app?eventcode=msgnostockdet")
    Call<BaseModel<Msgnostockdet>> msgnostockdet(@Field("agentid") String agentid);

    //9_12我的提醒_(下级提醒自己发货)
    @FormUrlEncoded
    @POST("app?eventcode=msgacceptsend")
    Call<BaseModel<Msgacceptsend>> msgacceptsend(@Field("agentid") String agentid);


    //9_4我的提醒_(注明已阅读标识)
    @FormUrlEncoded
    @POST("app?eventcode=agentupdateflag")
    Call<BaseModel> agentupdateflag(@FieldMap Map<String, String> map);

    //4_4_1个人中心中-查询代理商最低库存设置
    @FormUrlEncoded
    @POST("app?eventcode=getquyminstock")
    Call<BaseModel<Goods>> getquyminstock(@Field("agentid") String agentid);

    //4_4_2个人中心中-删除代理商最低库存设置
    @FormUrlEncoded
    @POST("app?eventcode=getdelminstock")
    Call<BaseModel> getdelminstock(@Field("agent_minstock_id") String agent_minstock_id);

    //4_4个人中心中-代理商最低库存设置(确认保存)
    @FormUrlEncoded
    @POST("app?eventcode=getagminstock")
    Call<BaseModel> getagminstock(@FieldMap Map<String,String> map);

    //4_5个人中心-我的奖励明细
    @FormUrlEncoded
    @POST("app?eventcode=getreward")
    Call<BaseModel<GetrewardInfo>> getreward(@FieldMap Map<String,String> map);

    //4_5_1个人中心-我的奖励明细(获取奖励类型)
    @FormUrlEncoded
    @POST("app?eventcode=getrewardtype")
    Call<BaseModel<GetrewardtypeInfo>> getrewardtype(@Field("agentid")String agentid);

    //8_2_1代理商比拼一览表(纵轴数据)
    @FormUrlEncoded
    @POST("app?eventcode=basesaleaxis")
    Call<BaseModel<BasesaleaxisInfo>> basesaleaxis(@Field("agentid")String agentid);

    //8_2_2代理商比拼一览表(我的排名)
    @FormUrlEncoded
    @POST("app?eventcode=basecursale")
    Call<BaseModel<BasesaleInfo>> basecursale(@Field("agentid")String agentid,@Field("curparentid") String curparentid);
}

