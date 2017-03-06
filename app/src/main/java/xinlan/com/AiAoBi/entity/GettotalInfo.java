package xinlan.com.AiAoBi.entity;

/**
 * Created by Administrator on 2017/2/10.
 */
public class GettotalInfo {
    /**
     * 类型：多行数据的结果集
     结果值说明：返回用户信息
     res:[1:获取数据成功；-1:获取数据失败]
     data:json格式，表示返回的记录

     json中字段说明
     masterid：订单号
     goodsid：货品id
     goodsname:货品名称
     costprice:单价
     alreadynum:数量
     acceptmoney：金额
     totalmoney：计算金额
     */
    private String masterid;
    private String goodsid;
    private String goodsname;
    private String costprice;
    private String alreadynum;
    private String acceptmoney;
    private String totalmoney;

    public String getMasterid() {
        return masterid;
    }

    public String getGoodsid() {
        return goodsid;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public String getCostprice() {
        return costprice;
    }

    public String getAlreadynum() {
        return alreadynum;
    }

    public String getAcceptmoney() {
        return acceptmoney;
    }

    public String getTotalmoney() {
        return totalmoney;
    }
}
