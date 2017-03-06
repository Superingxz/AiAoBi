package xinlan.com.AiAoBi.home.enity;//

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 待审批信息
 * Created by Administrator on 2016/10/26.
 */
public class WaitExaminationInfo implements Serializable{
    String parentzdid;
    String operatdate;//操作时间;//
    String checkagentlevel;//审批代理商级别；
    String checkagentid;//审批代理商id；
    String reftype;//业务类别【apply;//代理商加入；up：代理商升级】
    String checkflag;//审核类别【1;//推荐人审批，2;//总代审批，3;//总裁审批，4;//公司审批】；
    String agentname;//代理商名称
    String agentid;//代应商id
    String cardno;//身份证;//
    String slevel;//代理商级别(取值【1;//总裁;//2;//官方;//3;//总代;//4;//一级;//5;//经销商;//6;//特约;//7;//花瓣】);//
    String slevel_up;//代理商升后的级别
    String  phone;//电话;//
    String weixin;//微信;//
    String  ismore;//单品或全系（取值【0：单品；1：全系】）;//
    String refman;//推荐人;//
    String refmantel;//推荐人电话;//
    String  refmanlevel;//推荐人级别
    String  curparentname;//上一级;//
    String curparenttel;//上一级电话;//
    String curparentlevel;//上一级级别
    String  province;//所属省;//
    String city;//所属城市;//
    String area;//区;//
    String address;//收货地址;//
    String brandid;//品牌id(取值为货品id);//
    String brand;//品牌(取值为货品名称);//
    String voucher;//付款凭证；
    String cardnoa;//身份证正面；
    String cardnob;//身份证反面；
    String brand_up;//升级后的品牌
    String brandid_up;//升级后的品牌id

    public String getParentzdid() {
        return parentzdid;
    }

    public String getBrand_up() {
        return brand_up;
    }

    public void setBrand_up(String brand_up) {
        this.brand_up = brand_up;
    }

    public String getBrandid_up() {
        return brandid_up;
    }

    public void setBrandid_up(String brandid_up) {
        this.brandid_up = brandid_up;
    }

    public String getRefmanlevel() {
        return refmanlevel;
    }

    public void setRefmanlevel(String refmanlevel) {
        this.refmanlevel = refmanlevel;
    }

    public String getCurparentlevel() {
        return curparentlevel;
    }

    public void setCurparentlevel(String curparentlevel) {
        this.curparentlevel = curparentlevel;
    }

    public String getOperatdate() {
        return operatdate;
    }

    public void setOperatdate(String operatdate) {
        this.operatdate = operatdate;
    }

    public String getCardnob() {
        return cardnob;
    }

    public void setCardnob(String cardnob) {
        this.cardnob = cardnob;
    }

    public String getCheckagentlevel() {
        return checkagentlevel;
    }

    public void setCheckagentlevel(String checkagentlevel) {
        this.checkagentlevel = checkagentlevel;
    }

    public String getCheckagentid() {
        return checkagentid;
    }

    public void setCheckagentid(String checkagentid) {
        this.checkagentid = checkagentid;
    }

    public String getReftype() {
        return reftype;
    }

    public void setReftype(String reftype) {
        this.reftype = reftype;
    }

    public String getCheckflag() {
        return checkflag;
    }

    public void setCheckflag(String checkflag) {
        this.checkflag = checkflag;
    }

    public String getAgentname() {
        return agentname;
    }

    public void setAgentname(String agentname) {
        this.agentname = agentname;
    }

    public String getAgentid() {
        return agentid;
    }

    public void setAgentid(String agentid) {
        this.agentid = agentid;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getSlevel() {
        return slevel;
    }

    public void setSlevel(String slevel) {
        this.slevel = slevel;
    }

    public String getSlevel_up() {
        return slevel_up;
    }

    public void setSlevel_up(String slevel_up) {
        this.slevel_up = slevel_up;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getIsmore() {
        return ismore;
    }

    public void setIsmore(String ismore) {
        this.ismore = ismore;
    }

    public String getRefman() {
        return refman;
    }

    public void setRefman(String refman) {
        this.refman = refman;
    }

    public String getRefmantel() {
        return refmantel;
    }

    public void setRefmantel(String refmantel) {
        this.refmantel = refmantel;
    }

    public String getCurparentname() {
        return curparentname;
    }

    public void setCurparentname(String curparentname) {
        this.curparentname = curparentname;
    }

    public String getCurparenttel() {
        return curparenttel;
    }

    public void setCurparenttel(String curparenttel) {
        this.curparenttel = curparenttel;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBrandid() {
        return brandid;
    }

    public void setBrandid(String brandid) {
        this.brandid = brandid;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }

    public String getCardnoa() {
        return cardnoa;
    }

    public void setCardnoa(String cardnoa) {
        this.cardnoa = cardnoa;
    }

    @Override
    public String toString() {
        return "WaitExaminationInfo{" +
                "operatdate='" + operatdate + '\'' +
                ", checkagentlevel='" + checkagentlevel + '\'' +
                ", checkagentid='" + checkagentid + '\'' +
                ", reftype='" + reftype + '\'' +
                ", checkflag='" + checkflag + '\'' +
                ", agentname='" + agentname + '\'' +
                ", agentid='" + agentid + '\'' +
                ", cardno='" + cardno + '\'' +
                ", slevel='" + slevel + '\'' +
                ", slevel_up='" + slevel_up + '\'' +
                ", phone='" + phone + '\'' +
                ", weixin='" + weixin + '\'' +
                ", ismore='" + ismore + '\'' +
                ", refman='" + refman + '\'' +
                ", refmantel='" + refmantel + '\'' +
                ", refmanlevel='" + refmanlevel + '\'' +
                ", curparentname='" + curparentname + '\'' +
                ", curparenttel='" + curparenttel + '\'' +
                ", curparentlevel='" + curparentlevel + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", address='" + address + '\'' +
                ", brandid='" + brandid + '\'' +
                ", brand='" + brand + '\'' +
                ", voucher='" + voucher + '\'' +
                ", cardnoa='" + cardnoa + '\'' +
                ", cardnob='" + cardnob + '\'' +
                '}';
    }
}
