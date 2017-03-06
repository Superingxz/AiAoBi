package xinlan.com.AiAoBi.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/15.
 */
public class AddAaginOrder implements Serializable{
    String goodsno;
    String goodsname;
    String goodssize;
    String unitname;
    String costprice;
    String minnum;
    String total;
    String lessnum;
    String num;
    String is_new;
    String agentid;
    String agent_dogoodsmid2_id;
    String goodsid;
    String editText="0";
    boolean isAdd;

    public boolean isAdd() {
        return isAdd;
    }

    public void setAdd(boolean add) {
        isAdd = add;
    }

    public String getEditText() {
        return editText;
    }

    public void setEditText(String editText) {
        this.editText = editText;
    }

    public String getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(String goodsid) {
        this.goodsid = goodsid;
    }

    public String getGoodsno() {
        return goodsno;
    }

    public void setGoodsno(String goodsno) {
        this.goodsno = goodsno;
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

    public String getCostprice() {
        return costprice;
    }

    public void setCostprice(String costprice) {
        this.costprice = costprice;
    }

    public String getMinnum() {
        return minnum;
    }

    public void setMinnum(String minnum) {
        this.minnum = minnum;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getLessnum() {
        return lessnum;
    }

    public void setLessnum(String lessnum) {
        this.lessnum = lessnum;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getIs_new() {
        return is_new;
    }

    public void setIs_new(String is_new) {
        this.is_new = is_new;
    }

    public String getAgentid() {
        return agentid;
    }

    public void setAgentid(String agentid) {
        this.agentid = agentid;
    }

    public String getAgent_dogoodsmid2_id() {
        return agent_dogoodsmid2_id;
    }

    public void setAgent_dogoodsmid2_id(String agent_dogoodsmid2_id) {
        this.agent_dogoodsmid2_id = agent_dogoodsmid2_id;
    }
}
