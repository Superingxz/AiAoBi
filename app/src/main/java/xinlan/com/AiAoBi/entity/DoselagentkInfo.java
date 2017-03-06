package xinlan.com.AiAoBi.entity;

/**
 * Created by Administrator on 2017/2/10.
 */
public class DoselagentkInfo {
    /**
     * goodsid：货品id，【如果goodsid为空，则表示全系，否则是单品拿货】
     goodsname：货品名称
     unitname：规格
     minnum ：数量
     */
    private String goodsid;
    private String goodsname;
    private String unitname;
    private String minnum;

    public DoselagentkInfo(String goodsname, String unitname, String minnum) {
        this.goodsname = goodsname;
        this.unitname = unitname;
        this.minnum = minnum;
    }

    public String getGoodsid() {
        return goodsid;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public String getUnitname() {
        return unitname;
    }

    public String getMinnum() {
        return minnum;
    }
}
