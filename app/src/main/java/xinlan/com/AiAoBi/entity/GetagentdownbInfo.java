package xinlan.com.AiAoBi.entity;

/**
 * Created by Administrator on 2016/12/11.
 */
public class GetagentdownbInfo {
    /**
     * 类型：多行数据的结果集
     结果值说明：返回用户信息
     res:[1:获取数据成功；-1:没有数据]
     data:json格式，表示返回的记录

     json中字段说明
     goodsname:货品名称
     indate:签收日期
     num:数量
     goodssize:规格型号
     goodsid:货品id
     costprice:单价
     money:金额
     */
    private String goodsname;
    private String indate;
    private String num;
    private String goodssize;
    private String goodsid;
    private String costprice;
    private String money;

    public String getGoodsname() {
        return goodsname;
    }

    public String getIndate() {
        return indate;
    }

    public String getNum() {
        return num;
    }

    public String getGoodssize() {
        return goodssize;
    }

    public String getGoodsid() {
        return goodsid;
    }

    public String getCostprice() {
        return costprice;
    }

    public String getMoney() {
        return money;
    }

    @Override
    public String toString() {
        return "GetagentdownbInfo{" +
                "goodsname='" + goodsname + '\'' +
                ", indate='" + indate + '\'' +
                ", num='" + num + '\'' +
                ", goodssize='" + goodssize + '\'' +
                ", goodsid='" + goodsid + '\'' +
                ", costprice='" + costprice + '\'' +
                ", money='" + money + '\'' +
                '}';
    }
}
