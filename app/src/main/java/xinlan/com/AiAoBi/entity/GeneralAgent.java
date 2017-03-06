package xinlan.com.AiAoBi.entity;

/**
 * Created by Administrator on 2016/10/25.
 */
public class GeneralAgent {
/**
 * agentname:供应商名称;
 cardno:身份证;
 slevel:级别(取值【1:总裁;2:官方;3:总代;4:一级;5:经销商;6:特约;7:特约】);
 phone:电话;
 weixin:微信;
 ismore:单品或全系（取值【0：单品；1：全系】）;
 refman:推荐人;
 refmantel:推荐人电话;
 province:所属省;
 city:所属城市;
 area:区;
 address:收货地址;
 brandid:品牌id(取值为货品id);
 brand:品牌(取值为货品名称);
 voucher:付款凭证；
 cardnofront:身份证正面；
 cardnoback:身份证反面；
 */

    private String agentname,cardno,slevel,phone,weixin,ismore,
        refman,refmantel,province,city,area,address,brandid,brand,voucher,cardnofront,cardnoback;

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }

    public String getCardnofront() {
        return cardnofront;
    }

    public void setCardnofront(String cardnofront) {
        this.cardnofront = cardnofront;
    }

    public String getCardnoback() {
        return cardnoback;
    }

    public void setCardnoback(String cardnoback) {
        this.cardnoback = cardnoback;
    }

    public String getAgentname() {
        return agentname;
    }

    public void setAgentname(String agentname) {
        this.agentname = agentname;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrandid() {
        return brandid;
    }

    public void setBrandid(String brandid) {
        this.brandid = brandid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRefmantel() {
        return refmantel;
    }

    public void setRefmantel(String refmantel) {
        this.refmantel = refmantel;
    }

    public String getRefman() {
        return refman;
    }

    public void setRefman(String refman) {
        this.refman = refman;
    }

    public String getIsmore() {
        return ismore;
    }

    public void setIsmore(String ismore) {
        this.ismore = ismore;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSlevel() {
        return slevel;
    }

    public void setSlevel(String slevel) {
        this.slevel = slevel;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
