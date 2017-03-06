package xinlan.com.AiAoBi.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/1.
 */
public class GetagentpriceInfo implements Serializable{
   /* goodsid:货品id
    goodsname:产品名称
    goodssize:规格型号
    price:单价*/
    private String goodsid;
    private String goodsname;
    private String goodssize;
    private String price1;
    private String price2;
    private String price3;
    private String price4;
    private String price5;
    private String price6;
    private String price7;

    public String getPrice1() {
        return price1;
    }

    public String getPrice2() {
        return price2;
    }

    public String getPrice3() {
        return price3;
    }

    public String getGoodsid() {
        return goodsid;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public String getGoodssize() {
        return goodssize;
    }

    public String getPrice4() {
        return price4;
    }

    public String getPrice5() {
        return price5;
    }

    public String getPrice6() {
        return price6;
    }

    public String getPrice7() {
        return price7;
    }

    @Override
    public String toString() {
        return "GetagentpriceInfo{" +
                "goodsid='" + goodsid + '\'' +
                ", goodsname='" + goodsname + '\'' +
                ", goodssize='" + goodssize + '\'' +
                ", price4='" + price4 + '\'' +
                ", price5='" + price5 + '\'' +
                ", price6='" + price6 + '\'' +
                ", price7='" + price7 + '\'' +
                '}';
    }
}
