package xinlan.com.AiAoBi.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/1.
 */
public class GetagentdownInfo implements Serializable{
   /* json中字段说明
    agentid:代理商id
    agentcode:供应商编码
    agentname:供应商名称
    cardno:身份证
    slevel:级别
    slevelname:级别名称
    phone:电话
    weixin:微信
    ismore:单品或全系，【1：全系，0：单品】
    ismorename：单品或全系名称
    brand:品牌
    brandid:品牌id
    regedit_date:加入时间
    grant_date：授权时间
    province：所属省
    city：所属城市
    area：区
    address：收货地址*/
    private String agentid;
    private String agentcode;
    private String agentname;
    private String cardno;
    private String slevel;
    private String slevelname;
    private String phone;
    private String weixin;
    private String ismore;
    private String ismorename;
    private String brand;
    private String brandid;
    private String regedit_date;
    private String grant_date;
    private String province;
    private String city;
    private String area;
    private String address;

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

    public String getSlevelname() {
        return slevelname;
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

    public String getIsmorename() {
        return ismorename;
    }

    public String getBrand() {
        return brand;
    }

    public String getBrandid() {
        return brandid;
    }

    public String getRegedit_date() {
        return regedit_date;
    }

    public String getGrant_date() {
        return grant_date;
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
        return "GetagentdownInfo{" +
                "agentid='" + agentid + '\'' +
                ", agentcode='" + agentcode + '\'' +
                ", agentname='" + agentname + '\'' +
                ", cardno='" + cardno + '\'' +
                ", slevel='" + slevel + '\'' +
                ", slevelname='" + slevelname + '\'' +
                ", phone='" + phone + '\'' +
                ", weixin='" + weixin + '\'' +
                ", ismore='" + ismore + '\'' +
                ", ismorename='" + ismorename + '\'' +
                ", brand='" + brand + '\'' +
                ", brandid='" + brandid + '\'' +
                ", regedit_date='" + regedit_date + '\'' +
                ", grant_date='" + grant_date + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
