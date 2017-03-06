package xinlan.com.AiAoBi.entity;

/**
 * Created by Administrator on 2016/12/7.
 */
public class SalebarcodeInfo {
    /**
     * 类型：多行数据的结果集
     结果值说明：返回用户信息
     res:[1:扫码成功!；-1:扫码失败!]
     data:json格式，表示返回的记录

     json中字段说明
     is_new：是否新增标识，【0：新增，1：更新】
     goodsid：货品id
     smallcode：小码
     comedays：回访天数
     goodsno：货品编号
     goodsname：货品名称
     goodssize：规则型号
     unitname：货品单位
     num：数量
     saleprice：销售单价
     amt：销售金额
     batchno：批次号
     agent_saled_id：明细主键
     agent_salem_id：销售开单主键id
     */
    private String is_new;
    private String goodsid;
    private String smallcode;
    private String comedays;
    private String goodsno;
    private String goodsname;
    private String goodssize;
    private String unitname;
    private String num;
    private String saleprice;
    private String amt;
    private String batchno;
    private String agent_saled_id;
    private String agent_salem_id;

    public String getIs_new() {
        return is_new;
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

    public String getNum() {
        return num;
    }

    public String getSaleprice() {
        return saleprice;
    }

    public String getAmt() {
        return amt;
    }

    public String getBatchno() {
        return batchno;
    }

    public String getAgent_saled_id() {
        return agent_saled_id;
    }

    public String getAgent_salem_id() {
        return agent_salem_id;
    }

    @Override
    public String toString() {
        return "SalebarcodeInfo{" +
                "is_new='" + is_new + '\'' +
                ", goodsid='" + goodsid + '\'' +
                ", smallcode='" + smallcode + '\'' +
                ", comedays='" + comedays + '\'' +
                ", goodsno='" + goodsno + '\'' +
                ", goodsname='" + goodsname + '\'' +
                ", goodssize='" + goodssize + '\'' +
                ", unitname='" + unitname + '\'' +
                ", num='" + num + '\'' +
                ", saleprice='" + saleprice + '\'' +
                ", amt='" + amt + '\'' +
                ", batchno='" + batchno + '\'' +
                ", agent_saled_id='" + agent_saled_id + '\'' +
                ", agent_salem_id='" + agent_salem_id + '\'' +
                '}';
    }
}
