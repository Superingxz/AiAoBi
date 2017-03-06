package xinlan.com.AiAoBi.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/10.
 */
public class DoselbargainInfo implements Serializable{
    /**
     类型：多行数据的结果集
     结果值说明：返回用户信息
     res:[1:获取数据成功；-1:没有订单!]
     data:json格式，表示返回的记录

     json中字段说明
     skeyid：订单主键id
     masterid：订单编号
     agent_id：申请代理商id
     agent_name：申请代理商名称
     slevel：申请代理商级别
     optdate：申请时间
     doagent_id：处理代理商id
     doagent_name：处理代理商名称
     doslevel：处理代理商级别
     doagent_phone：处理代理商电话
     salemoney：订单金额
     profit：上级利润
     ordertype：处理标识【apply：我的货品提报，deal：处理提报】
     inman：收货人
     intel：收货人电话
     inaddr：收货地址
     ref_id：提报订单主键id
     ref_no：提报订单号
     src_id：原始订单主键id
     src_no：原始订单号
     bargainflag：订单标识，值为self
     */
    private String skeyid;
    private String masterid;
    private String agent_id;
    private String agent_name;
    private String slevel;
    private String optdate;
    private String doagent_id;
    private String doagent_name;
    private String doslevel;
    private String doagent_phone;
    private String salemoney;
    private String profit;
    private String dealflag;
    private String inman;
    private String intel;
    private String inaddr;
    private String ref_id;
    private String ref_no;
    private String ref_detid;
    private String src_id;
    private String src_no;
    private String src_detid;
    private String ordertype;
    private String bargainflag;

    public void setSkeyid(String skeyid) {
        this.skeyid = skeyid;
    }

    public void setMasterid(String masterid) {
        this.masterid = masterid;
    }

    public void setAgent_id(String agent_id) {
        this.agent_id = agent_id;
    }

    public void setAgent_name(String agent_name) {
        this.agent_name = agent_name;
    }

    public void setSlevel(String slevel) {
        this.slevel = slevel;
    }

    public void setOptdate(String optdate) {
        this.optdate = optdate;
    }

    public void setDoagent_id(String doagent_id) {
        this.doagent_id = doagent_id;
    }

    public void setDoagent_name(String doagent_name) {
        this.doagent_name = doagent_name;
    }

    public void setDoslevel(String doslevel) {
        this.doslevel = doslevel;
    }

    public void setDoagent_phone(String doagent_phone) {
        this.doagent_phone = doagent_phone;
    }

    public void setSalemoney(String salemoney) {
        this.salemoney = salemoney;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    public void setDealflag(String dealflag) {
        this.dealflag = dealflag;
    }

    public void setInman(String inman) {
        this.inman = inman;
    }

    public void setIntel(String intel) {
        this.intel = intel;
    }

    public void setInaddr(String inaddr) {
        this.inaddr = inaddr;
    }

    public void setRef_id(String ref_id) {
        this.ref_id = ref_id;
    }

    public void setRef_no(String ref_no) {
        this.ref_no = ref_no;
    }

    public void setRef_detid(String ref_detid) {
        this.ref_detid = ref_detid;
    }

    public void setSrc_id(String src_id) {
        this.src_id = src_id;
    }

    public void setSrc_no(String src_no) {
        this.src_no = src_no;
    }

    public void setSrc_detid(String src_detid) {
        this.src_detid = src_detid;
    }

    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype;
    }

    public void setBargainflag(String bargainflag) {
        this.bargainflag = bargainflag;
    }

    public String getBargainflag() {
        return bargainflag;
    }

    public String getOrdertype() {
        return ordertype;
    }

    public String getRef_no() {
        return ref_no;
    }

    public String getSrc_no() {
        return src_no;
    }

    public String getSkeyid() {
        return skeyid;
    }

    public String getMasterid() {
        return masterid;
    }

    public String getAgent_id() {
        return agent_id;
    }

    public String getAgent_name() {
        return agent_name;
    }

    public String getSlevel() {
        return slevel;
    }

    public String getOptdate() {
        return optdate;
    }

    public String getDoagent_id() {
        return doagent_id;
    }

    public String getDoagent_name() {
        return doagent_name;
    }

    public String getDoslevel() {
        return doslevel;
    }

    public String getDoagent_phone() {
        return doagent_phone;
    }

    public String getSalemoney() {
        return salemoney;
    }

    public String getProfit() {
        return profit;
    }

    public String getDealflag() {
        return dealflag;
    }

    public String getInman() {
        return inman;
    }

    public String getIntel() {
        return intel;
    }

    public String getInaddr() {
        return inaddr;
    }

    public String getRef_id() {
        return ref_id;
    }

    public String getRef_detid() {
        return ref_detid;
    }

    public String getSrc_id() {
        return src_id;
    }

    public String getSrc_detid() {
        return src_detid;
    }

    @Override
    public String toString() {
        return "DoselbargainInfo{" +
                "skeyid='" + skeyid + '\'' +
                ", masterid='" + masterid + '\'' +
                ", agent_id='" + agent_id + '\'' +
                ", agent_name='" + agent_name + '\'' +
                ", slevel='" + slevel + '\'' +
                ", optdate='" + optdate + '\'' +
                ", doagent_id='" + doagent_id + '\'' +
                ", doagent_name='" + doagent_name + '\'' +
                ", doslevel='" + doslevel + '\'' +
                ", doagent_phone='" + doagent_phone + '\'' +
                ", salemoney='" + salemoney + '\'' +
                ", profit='" + profit + '\'' +
                ", dealflag='" + dealflag + '\'' +
                ", inman='" + inman + '\'' +
                ", intel='" + intel + '\'' +
                ", inaddr='" + inaddr + '\'' +
                '}';
    }
}
