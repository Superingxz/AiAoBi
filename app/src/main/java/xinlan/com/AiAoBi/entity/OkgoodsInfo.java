package xinlan.com.AiAoBi.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/3.
 */
public class OkgoodsInfo implements Serializable{
    /**
     * json中字段说明
     mkeyid：订单主键id
     masterid:申请单号
     num:订单数量
     salemoney:订单金额
     bargainflag:订单状态
     ordertype：处理标识【apply：我的货品提报，deal：处理提报】
     */
    private String mkeyid;
    private String masterid;
    private String num;
    private String salemoney;
    private String bargainflag;
    private String ordertype;

    public String getMkeyid() {
        return mkeyid;
    }

    public String getMasterid() {
        return masterid;
    }

    public String getNum() {
        return num;
    }

    public String getSalemoney() {
        return salemoney;
    }

    public String getBargainflag() {
        return bargainflag;
    }

    public String getOrdertype() {
        return ordertype;
    }

    @Override
    public String toString() {
        return "OkgoodsInfo{" +
                "mkeyid='" + mkeyid + '\'' +
                ", masterid='" + masterid + '\'' +
                ", num='" + num + '\'' +
                ", salemoney='" + salemoney + '\'' +
                ", bargainflag='" + bargainflag + '\'' +
                ", ordertype='" + ordertype + '\'' +
                '}';
    }
}
