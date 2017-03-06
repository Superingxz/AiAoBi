package xinlan.com.AiAoBi.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/16.
 */
public class MyorderInfo implements Serializable{
    /**
     * 类型：多行数据的结果集
     结果值说明：返回用户信息
     res:[1:获取数据成功!；-1:没有数据!；0:没有货品信息!]
     data:json格式，表示返回的记录

     json中字段说明
     optdate：月份
     agent_id：代理商id
     agent_name：代理商名称
     goodsid:货号id
     goodsno:货号
     goodsname:货品名称
     goodssize:包装规格
     num:总数量
     money:总金额
     */
    private String optdate;
    private String agent_id;
    private String agent_name;
    private String goodsid;
    private String goodsno;
    private String goodsname;
    private String goodssize;
    private String num;
    private String money;
    private String masterid;

    public String getMasterid() {
        return masterid;
    }

    @Override
    public String toString() {
        return "MyorderInfo{" +
                "optdate='" + optdate + '\'' +
                ", agent_id='" + agent_id + '\'' +
                ", agent_name='" + agent_name + '\'' +
                ", goodsid='" + goodsid + '\'' +
                ", goodsno='" + goodsno + '\'' +
                ", goodsname='" + goodsname + '\'' +
                ", goodssize='" + goodssize + '\'' +
                ", num='" + num + '\'' +
                ", money='" + money + '\'' +
                '}';
    }

    public String getOptdate() {
        return optdate;
    }

    public String getAgent_id() {
        return agent_id;
    }

    public String getAgent_name() {
        return agent_name;
    }

    public String getGoodsid() {
        return goodsid;
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

    public String getNum() {
        return num;
    }

    public String getMoney() {
        return money;
    }
}
