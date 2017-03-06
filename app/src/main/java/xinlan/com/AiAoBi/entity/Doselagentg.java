package xinlan.com.AiAoBi.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/11/15.
 */
public class Doselagentg {

    //    goodsid:货号id
//    goodsno:货号
//    goodsname:货品名称
//    goodssize:包装规格
//    unitname:产品单位
//    costprice:拿货价
//    num:本次发货数
//    total:要货数
//    lessnum:欠数
//    alreadynum:已发货数
    boolean isAddGoods;
    List<String> sanCodeList;//条码
    String src_detid;//原提报明细id：src_detid
    String ref_detid;//提报明细id：ref_detid
    String barcode;//条码
    String goodsid;
    String goodsno;
    String goodsname;
    String goodssize;
    String unitname;
    String costprice;
    String total;
    String lessnum;
    String alreadynum;
    String num;
    String neednum;//要货数

    public String getNeednum() {
        return neednum;
    }

    public void setNeednum(String neednum) {
        this.neednum = neednum;
    }

    public boolean isAddGoods() {
        return isAddGoods;
    }

    public void setAddGoods(boolean addGoods) {
        isAddGoods = addGoods;
    }

    public String getSrc_detid() {
        return src_detid;
    }

    public List<String> getSanCodeList() {
        return sanCodeList;
    }

    public void setSanCodeList(List<String> sanCodeList) {
        this.sanCodeList = sanCodeList;
    }

    public void setSrc_detid(String src_detid) {
        this.src_detid = src_detid;
    }

    public String getRef_detid() {
        return ref_detid;
    }

    public void setRef_detid(String ref_detid) {
        this.ref_detid = ref_detid;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(String goodsid) {
        this.goodsid = goodsid;
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

    public String getUnitname() {
        return unitname;
    }

    public void setUnitname(String unitname) {
        this.unitname = unitname;
    }

    public String getCostprice() {
        return costprice;
    }

    public void setCostprice(String costprice) {
        this.costprice = costprice;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getLessnum() {
        return lessnum;
    }

    public void setLessnum(String lessnum) {
        this.lessnum = lessnum;
    }

    public String getAlreadynum() {
        return alreadynum;
    }

    public void setAlreadynum(String alreadynum) {
        this.alreadynum = alreadynum;
    }
}
