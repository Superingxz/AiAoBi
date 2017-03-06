package xinlan.com.AiAoBi.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/12/3.
 */
public class ASumData {
   /*
   * sumdata表示汇总合计说明
goodsid：货品id
goodsname：货品名称
goodssize：货品规格
unitname：货品单位
costprice：货品单价
totalnum：订单总数
alreadynum：已货数
lessnum：欠货数*/
    private String goodsid;
    private String goodsname;
    private String goodssize;
    private String unitname;
    private String costprice;
    private String totalnum;
    private String alreadynum;
    private String lessnum;

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

    public String getTotalnum() {
        return totalnum;
    }

    public String getAlreadynum() {
        return alreadynum;
    }

    public String getLessnum() {
        return lessnum;
    }

    @Override
    public String toString() {
        return "ASumData{" +
                "goodsid='" + goodsid + '\'' +
                ", goodsname='" + goodsname + '\'' +
                ", goodssize='" + goodssize + '\'' +
                ", unitname='" + unitname + '\'' +
                ", costprice='" + costprice + '\'' +
                ", totalnum='" + totalnum + '\'' +
                ", alreadynum='" + alreadynum + '\'' +
                ", lessnum='" + lessnum + '\'' +
                '}';
    }
}
