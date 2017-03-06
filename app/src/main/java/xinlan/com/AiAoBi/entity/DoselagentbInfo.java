package xinlan.com.AiAoBi.entity;

/**
 * Created by Administrator on 2016/11/16.
 */
public class DoselagentbInfo {
    /**
     * json中字段说明
     detid：代理订单明细id
     skeyid：代理订单主键id
     goodsid：货品id
     goodsname：货品名称
     goodssize：规格型号
     unitname：单位
     oldnum：原始订单数
     totalnum：总数
     costprice：单价
     parentnum：代理数
     costpric_up：上一级成本价
     ref_detid：提报订单明细id
     src_detid：原始订单明细id
     */
    private String detid;
    private String skeyid;
    private String goodsid;
    private String goodsname;
    private String goodssize;
    private String unitname;
    private String oldnum;
    private String totalnum;
    private String costprice;
    private String parentnum;
    private String costpric_up;
    private String ref_detid;
    private String src_detid;
    private boolean isCheck;

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public boolean isCheck() {

        return isCheck;
    }

    public String getDetid() {
        return detid;
    }

    public String getSkeyid() {
        return skeyid;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public String getGoodsid() {
        return goodsid;
    }

    public String getGoodssize() {
        return goodssize;
    }

    public String getOldnum() {
        return oldnum;
    }

    public String getUnitname() {
        return unitname;
    }

    public String getTotalnum() {
        return totalnum;
    }

    public String getCostprice() {
        return costprice;
    }

    public String getParentnum() {
        return parentnum;
    }

    public String getCostpric_up() {
        return costpric_up;
    }

    public String getRef_detid() {
        return ref_detid;
    }

    public String getSrc_detid() {
        return src_detid;
    }

    @Override
    public String toString() {
        return "DoselagentbInfo{" +
                "detid='" + detid + '\'' +
                ", skeyid='" + skeyid + '\'' +
                ", goodsid='" + goodsid + '\'' +
                ", goodsname='" + goodsname + '\'' +
                ", goodssize='" + goodssize + '\'' +
                ", unitname='" + unitname + '\'' +
                ", oldnum='" + oldnum + '\'' +
                ", totalnum='" + totalnum + '\'' +
                ", costprice='" + costprice + '\'' +
                ", parentnum='" + parentnum + '\'' +
                ", costpric_up='" + costpric_up + '\'' +
                ", ref_detid='" + ref_detid + '\'' +
                ", src_detid='" + src_detid + '\'' +
                '}';
    }
}
