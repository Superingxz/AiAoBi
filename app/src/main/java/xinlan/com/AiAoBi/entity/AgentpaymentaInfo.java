package xinlan.com.AiAoBi.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/29.
 */
public class AgentpaymentaInfo implements Serializable{
    public boolean ischeck() {
        return ischeck;
    }

    public void setIscheck(boolean ischeck) {
        this.ischeck = ischeck;
    }

    /*json中字段说明
        agentid:代理商id
        agentcode:供应商编码
        agentname:供应商名称
        cardno:身份证
        slevel:级别
        phone:电话
        weixin:微信
        ismore:单品或全系
        brand:品牌
        refman:推荐人
        refmantel:推荐人电话
        refmanlevel:推荐人级别
        curparentname:当前上级名称
        curparenttel:当前上级电话
        curparentlevel：当前上级级别
        brandid:品牌id
        voucher:申请人付款凭证
        curparentlevel:当前上级级别【1:总裁;2:官方;3:总代;4:一级;5:经销商;6:特约;7:特约】);
        refmanid:推荐人id
        curparentid:当前上级id
        regedit_date:加入时间
        grant_date：授权时间
        balance：余额
        province：所属省
        city：所属城市
        area：区
        address：收货地址*/
    private boolean ischeck;
    private String agentid;
    private String agentcode;
    private String agentname;
    private String cardno;
    private String slevel;
    private String phone;
    private String weixin;
    private String ismore;
    private String brand;
    private String refman;
    private String refmantel;
    private String refmanlevel;
    private String curparentname;
    private String curparenttel;
    private String brandid;
    private String voucher;
    private String curparentlevel;
    private String refmanid;
    private String curparentid;
    private String regedit_date;
    private String grant_date;
    private String balance;
    private String province;
    private String city;
    private String area;
    private String address;
    private String slevelname;

    public String getSlevelname() {
        return slevelname;
    }

    public String getAgentid() {
        return agentid;
    }

    public String getAgentcode() {
        return agentcode;
    }

    public String getAgentname() {
        return agentname;
    }

    public String getCardno() {
        return cardno;
    }

    public String getSlevel() {
        return slevel;
    }

    public String getPhone() {
        return phone;
    }

    public String getWeixin() {
        return weixin;
    }

    public String getIsmore() {
        return ismore;
    }

    public String getBrand() {
        return brand;
    }

    public String getRefman() {
        return refman;
    }

    public String getRefmantel() {
        return refmantel;
    }

    public String getRefmanlevel() {
        return refmanlevel;
    }

    public String getCurparentname() {
        return curparentname;
    }

    public String getCurparenttel() {
        return curparenttel;
    }

    public String getBrandid() {
        return brandid;
    }

    public String getVoucher() {
        return voucher;
    }

    public String getCurparentlevel() {
        return curparentlevel;
    }

    public String getRefmanid() {
        return refmanid;
    }

    public String getCurparentid() {
        return curparentid;
    }

    public String getRegedit_date() {
        return regedit_date;
    }

    public String getGrant_date() {
        return grant_date;
    }

    public String getBalance() {
        return balance;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getArea() {
        return area;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "AgentpaymentaInfo{" +
                "agentid='" + agentid + '\'' +
                ", agentcode='" + agentcode + '\'' +
                ", agentname='" + agentname + '\'' +
                ", cardno='" + cardno + '\'' +
                ", slevel='" + slevel + '\'' +
                ", phone='" + phone + '\'' +
                ", weixin='" + weixin + '\'' +
                ", ismore='" + ismore + '\'' +
                ", brand='" + brand + '\'' +
                ", refman='" + refman + '\'' +
                ", refmantel='" + refmantel + '\'' +
                ", refmanlevel='" + refmanlevel + '\'' +
                ", curparentname='" + curparentname + '\'' +
                ", curparenttel='" + curparenttel + '\'' +
                ", brandid='" + brandid + '\'' +
                ", voucher='" + voucher + '\'' +
                ", curparentlevel='" + curparentlevel + '\'' +
                ", refmanid='" + refmanid + '\'' +
                ", curparentid='" + curparentid + '\'' +
                ", regedit_date='" + regedit_date + '\'' +
                ", grant_date='" + grant_date + '\'' +
                ", balance='" + balance + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
