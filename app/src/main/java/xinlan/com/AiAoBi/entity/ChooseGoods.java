package xinlan.com.AiAoBi.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/4.
 */
public class ChooseGoods implements Serializable {
    private String price;
    private String onloadnum;
    private String stocknum;
    private String agentid;
    private String editText;
    private boolean isFocus;
    private String agent_minstock_id;
    private String goodsid;
    private String goodsname;
    private String goodssize;
    private String unitname;
    private String minstock;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOnloadnum() {
        return onloadnum;
    }

    public void setOnloadnum(String onloadnum) {
        this.onloadnum = onloadnum;
    }

    public String getStocknum() {
        return stocknum;
    }

    public void setStocknum(String stocknum) {
        this.stocknum = stocknum;
    }

    public String getAgentid() {
        return agentid;
    }

    public void setAgentid(String agentid) {
        this.agentid = agentid;
    }

    public String getEditText() {
        return editText;
    }

    public void setEditText(String editText) {
        this.editText = editText;
    }

    public boolean isFocus() {
        return isFocus;
    }

    public void setFocus(boolean focus) {
        isFocus = focus;
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
