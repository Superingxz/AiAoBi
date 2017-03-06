package xinlan.com.AiAoBi.entity;

/**
 * Created by Administrator on 2016/12/20.
 */
public class MysendgoodsInfo {
   /* 类型：多行数据的结果集
    结果值说明：返回用户信息
    res:[1:获取数据成功!；-1:没有数据!；0:没有货品信息!]
    data:json格式，表示返回的记录

            json中字段说明
    optdate：日期
    outagent_id：发货代理商id
    outagent_name：发货代理商名称
    inagentparent_id：要货代理商id
    inagentparent_name：要货代理商名称
    goodsid:货号id
    goodsno:货号
    goodsname:货品名称
    goodssize:包装规格
    neednum:要货数
    costprice:单价
    alreadynum：发货数
    intel：收货人电话
    inman：收货人*/
    private String optdate;
    private String outagent_id;
    private String outagent_name;
    private String inagentparent_id;
    private String inagentparent_name;
    private String goodsid;
    private String goodsno;
    private String goodsname;
    private String goodssize;
    private String neednum;
    private String costprice;
    private String alreadynum;
    private String intel;
    private String inman;

    @Override
    public String toString() {
        return "MysendgoodsInfo{" +
                "optdate='" + optdate + '\'' +
                ", outagent_id='" + outagent_id + '\'' +
                ", outagent_name='" + outagent_name + '\'' +
                ", inagentparent_id='" + inagentparent_id + '\'' +
                ", inagentparent_name='" + inagentparent_name + '\'' +
                ", goodsid='" + goodsid + '\'' +
                ", goodsno='" + goodsno + '\'' +
                ", goodsname='" + goodsname + '\'' +
                ", goodssize='" + goodssize + '\'' +
                ", neednum='" + neednum + '\'' +
                ", costprice='" + costprice + '\'' +
                ", alreadynum='" + alreadynum + '\'' +
                ", intel='" + intel + '\'' +
                ", inman='" + inman + '\'' +
                '}';
    }

    public String getOptdate() {
        return optdate;
    }

    public String getOutagent_id() {
        return outagent_id;
    }

    public String getOutagent_name() {
        return outagent_name;
    }

    public String getInagentparent_id() {
        return inagentparent_id;
    }

    public String getInagentparent_name() {
        return inagentparent_name;
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

    public String getNeednum() {
        return neednum;
    }

    public String getCostprice() {
        return costprice;
    }

    public String getAlreadynum() {
        return alreadynum;
    }

    public String getIntel() {
        return intel;
    }

    public String getInman() {
        return inman;
    }
}
