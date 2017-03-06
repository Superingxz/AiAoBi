package xinlan.com.AiAoBi.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/5.
 */
public class Goods implements Serializable{
//    goodsno:货品编码
//    goodsname:产品名称
//    goodssize:规格型号
//    mixunit:单品单位
//    maxunit:整箱单位
//    zhnum:大小单位转换数
//    issigle:单品授权
//    isbarcode:必须扫条件
//    typename:货品种类
//    address:默认地址
//    days:保质期
//    visitdays:回访天数
//    price:单价
//    memo:备注
//    goodsid:货品id
//    typeid:货品种类id
    boolean isCheck;
    String goodsno;
    String goodsname;
    String goodssize;
    String mixunit;
    String maxunit;
    String zhnum;
    String issigle;
    String isbarcode;
    String typename;
    String address;
    String days;
    String visitdays;
    String price;
    String memo;
    String goodsid;
    String typeid;
    String editText;

    private String agent_minstock_id;
    private String unitname;
    private String minstock;

    public String getAgent_minstock_id() {
        return agent_minstock_id;
    }

    public String getUnitname() {
        return unitname;
    }

    public String getMinstock() {
        return minstock;
    }

    public String getEditText() {
        return editText;
    }

    public void setEditText(String editText) {
        this.editText = editText;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getGoodsno() {
        return goodsno;
    }

    public void setGoodsno(String goodsno) {
        this.goodsno = goodsno;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public String getGoodssize() {
        return goodssize;
    }

    public void setGoodssize(String goodssize) {
        this.goodssize = goodssize;
    }

    public String getMixunit() {
        return mixunit;
    }

    public void setMixunit(String mixunit) {
        this.mixunit = mixunit;
    }

    public String getMaxunit() {
        return maxunit;
    }

    public void setMaxunit(String maxunit) {
        this.maxunit = maxunit;
    }

    public String getZhnum() {
        return zhnum;
    }

    public void setZhnum(String zhnum) {
        this.zhnum = zhnum;
    }

    public String getIssigle() {
        return issigle;
    }

    public void setIssigle(String issigle) {
        this.issigle = issigle;
    }

    public String getIsbarcode() {
        return isbarcode;
    }

    public void setIsbarcode(String isbarcode) {
        this.isbarcode = isbarcode;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getVisitdays() {
        return visitdays;
    }

    public void setVisitdays(String visitdays) {
        this.visitdays = visitdays;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(String goodsid) {
        this.goodsid = goodsid;
    }

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }
}
