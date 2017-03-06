package xinlan.com.AiAoBi.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/12/7.
 */
public class SalescanInfo implements Serializable{
    /**
     * json中字段说明
     goodsid：货品id
     smallcode：小码
     comedays：回访天数
     goodsno：货品编号
     goodsname：货品名称
     goodssize：规则型号
     unitname：货品单位
     saleprice：销售单价
     batchno：批次号
     barcode:条码
     */
    private String goodsid;
    private String smallcode;
    private String comedays;
    private String goodsno;
    private String goodsname;
    private String goodssize;
    private String unitname;
    private String saleprice;
    private String batchno;
    private String barcode;
    private int count=1;

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getGoodsid() {
        return goodsid;
    }

    public String getSmallcode() {
        return smallcode;
    }

    public String getComedays() {
        return comedays;
    }

    public String getGoodsno() {
        return goodsno;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public String getGoodssize() {
        return goodssize;
    }

    public String getUnitname() {
        return unitname;
    }

    public String getSaleprice() {
        return saleprice;
    }

    public String getBatchno() {
        return batchno;
    }

    @Override
    public String toString() {
        return "SalescanInfo{" +
                "goodsid='" + goodsid + '\'' +
                ", smallcode='" + smallcode + '\'' +
                ", comedays='" + comedays + '\'' +
                ", goodsno='" + goodsno + '\'' +
                ", goodsname='" + goodsname + '\'' +
                ", goodssize='" + goodssize + '\'' +
                ", unitname='" + unitname + '\'' +
                ", saleprice='" + saleprice + '\'' +
                ", batchno='" + batchno + '\'' +
                '}';
    }
}
