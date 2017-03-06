package xinlan.com.AiAoBi.entity;

/**
 * Created by Administrator on 2016/12/27.
 */
public class GetquyminstockInfo {
    /**
     * json中字段说明
     agent_minstock_id：最低库存设置主键id
     goodsid：货品id
     goodsname:产品名称
     goodssize:规格型号
     unitname:单位
     minstock:最低库存
     */
    private String agent_minstock_id;
    private String goodsid;
    private String goodsname;
    private String goodssize;
    private String unitname;
    private String minstock;

    @Override
    public String toString() {
        return "GetquyminstockInfo{" +
                "agent_minstock_id='" + agent_minstock_id + '\'' +
                ", goodsid='" + goodsid + '\'' +
                ", goodsname='" + goodsname + '\'' +
                ", goodssize='" + goodssize + '\'' +
                ", unitname='" + unitname + '\'' +
                ", minstock='" + minstock + '\'' +
                '}';
    }

    public String getAgent_minstock_id() {
        return agent_minstock_id;
    }

    public void setAgent_minstock_id(String agent_minstock_id) {
        this.agent_minstock_id = agent_minstock_id;
    }

    public String getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(String goodsid) {
        this.goodsid = goodsid;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public String getGoodssize() {
        return goodssize;
    }

    public void setGoodssize(String goodssize) {
        this.goodssize = goodssize;
    }

    public String getUnitname() {
        return unitname;
    }

    public void setUnitname(String unitname) {
        this.unitname = unitname;
    }

    public String getMinstock() {
        return minstock;
    }

    public void setMinstock(String minstock) {
        this.minstock = minstock;
    }
}
