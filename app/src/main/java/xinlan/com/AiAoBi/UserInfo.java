package xinlan.com.AiAoBi;

/**
 * Created by Administrator on 2016/10/27.
 */

public class UserInfo {
    /*
     * agentcode:供应商编码
agentname:供应商名称
cardno:身份证
slevel:级别
phone:电话
weixin:微信
ismore:单品或全系  //取值【0：单品；1：全系】）;
brand:品牌
refman:推荐人
refmantel:推荐人电话
curparentname:当前上级名称
curparenttel:当前上级电话
brandid:品牌id
voucher:申请人付款凭证
curparentlevel:当前上级级别【1:总裁;2:官方;3:总代;4:一级;5:经销商;6:特约;7:特约】);
refmanid:推荐人id
curparentid:当前上级id
     */
    private  String curparentname;
    private  String agentcode;
    private  String agentid;
    private  String phone;
    private  String refmantel;
    private  String slevel;
    private  String refman;
    private  String brandid;
    private  String agentname;
    private  String curparenttel;
    private  String ismore;
    private  String weixin;
    private  String brand;
    private  String cardno;
    private  String voucher;
    private  String curparentlevel;
    private  String refmanid;
    private  String curparentid;
    private  String regedit_date;
    private  String grant_date;
    private  String balance;
    private  String province;
    private  String city;
    private  String area;
    private  String address;

    public String getAgentid() {
        return agentid;
    }

    public void setAgentid(String agentid) {
        this.agentid = agentid;
    }

    public String getCurparentname() {
        return curparentname;
    }

    public void setCurparentname(String curparentname) {
        this.curparentname = curparentname;
    }

    public String getAgentcode() {
        return agentcode;
    }

    public void setAgentcode(String agentcode) {
        this.agentcode = agentcode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRefmantel() {
        return refmantel;
    }

    public void setRefmantel(String refmantel) {
        this.refmantel = refmantel;
    }

    public String getSlevel() {
        return slevel;
    }

    public void setSlevel(String slevel) {
        this.slevel = slevel;
    }

    public String getRefman() {
        return refman;
    }

    public void setRefman(String refman) {
        this.refman = refman;
    }

    public String getBrandid() {
        return brandid;
    }

    public void setBrandid(String brandid) {
        this.brandid = brandid;
    }

    public String getAgentname() {
        return agentname;
    }

    public void setAgentname(String agentname) {
        this.agentname = agentname;
    }

    public String getCurparenttel() {
        return curparenttel;
    }

    public void setCurparenttel(String curparenttel) {
        this.curparenttel = curparenttel;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }

    public String getCurparentlevel() {
        return curparentlevel;
    }

    public void setCurparentlevel(String curparentlevel) {
        this.curparentlevel = curparentlevel;
    }

    public String getRefmanid() {
        return refmanid;
    }

    public void setRefmanid(String refmanid) {
        this.refmanid = refmanid;
    }

    public String getCurparentid() {
        return curparentid;
    }

    public void setCurparentid(String curparentid) {
        this.curparentid = curparentid;
    }

    public String getRegedit_date() {
        return regedit_date;
    }

    public void setRegedit_date(String regedit_date) {
        this.regedit_date = regedit_date;
    }

    public String getGrant_date() {
        return grant_date;
    }

    public void setGrant_date(String grant_date) {
        this.grant_date = grant_date;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
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

    @Override
    public String toString() {
        return "UserInfo{" +
                "curparentname='" + curparentname + '\'' +
                ", agentcode='" + agentcode + '\'' +
                ", agentid='" + agentid + '\'' +
                ", phone='" + phone + '\'' +
                ", refmantel='" + refmantel + '\'' +
                ", slevel='" + slevel + '\'' +
                ", refman='" + refman + '\'' +
                ", brandid='" + brandid + '\'' +
                ", agentname='" + agentname + '\'' +
                ", curparenttel='" + curparenttel + '\'' +
                ", ismore='" + ismore + '\'' +
                ", weixin='" + weixin + '\'' +
                ", brand='" + brand + '\'' +
                ", cardno='" + cardno + '\'' +
                ", voucher='" + voucher + '\'' +
                ", curparentlevel='" + curparentlevel + '\'' +
                ", refmanid='" + refmanid + '\'' +
                ", curparentid='" + curparentid + '\'' +
                '}';
    }
}
