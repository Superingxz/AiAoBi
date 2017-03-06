package xinlan.com.AiAoBi.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/12/3.
 */
public class Ddata {
    /* ddata表示子表说明
    goodsid：货品id
    goodsname：货品名称
    goodssize：货品规格
    unitname：货品单位
    costprice：货品单价
    num：订单数
    alreadynum：已货数*/
    private String goodsid;
    private String goodsname;
    private String goodssize;
    private String unitname;
    private String costprice;
    private String num;
    private String alreadynum;

    public String getGoodsid() {
        return goodsid;
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

    public String getCostprice() {
        return costprice;
    }

    public String getNum() {
        return num;
    }

    public String getAlreadynum() {
        return alreadynum;
    }

    @Override
    public String toString() {
        return "Ddata{" +
                "goodsid='" + goodsid + '\'' +
                ", goodsname='" + goodsname + '\'' +
                ", goodssize='" + goodssize + '\'' +
                ", unitname='" + unitname + '\'' +
                ", costprice='" + costprice + '\'' +
                ", num='" + num + '\'' +
                ", alreadynum='" + alreadynum + '\'' +
                '}';
    }
}
