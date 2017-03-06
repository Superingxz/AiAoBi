package xinlan.com.AiAoBi.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/12/3.
 */
public class Mdata {
    /*
    * mdata表示主表说明
masterid：发货单号
outagent_name：发货代理商
buillstatus：发货状态代码（1：已签收，0：未签收）
is_get：发货状态（已签收或未签收）
indate：签收时间
emscomp：快递公司
emsno：快递单号*/
    private String masterid;
    private String smkeyid;//收货单主表id;
    private String outagent_name;
    private String buillstatus;
    private String is_get;
    private String indate;
    private String emscomp;
    private String emsno;

    public String getSmkeyid() {
        return smkeyid;
    }

    public String getMasterid() {
        return masterid;
    }

    public String getOutagent_name() {
        return outagent_name;
    }

    public String getBuillstatus() {
        return buillstatus;
    }

    public void setBuillstatus(String buillstatus) {
        this.buillstatus = buillstatus;
    }

    public String getIs_get() {
        return is_get;
    }

    public String getIndate() {
        return indate;
    }

    public void setIndate(String indate) {
        this.indate = indate;
    }

    public String getEmscomp() {
        return emscomp;
    }

    public String getEmsno() {
        return emsno;
    }

    @Override
    public String toString() {
        return "Mdata{" +
                "masterid='" + masterid + '\'' +
                ", smkeyid='" + smkeyid + '\'' +
                ", outagent_name='" + outagent_name + '\'' +
                ", buillstatus='" + buillstatus + '\'' +
                ", is_get='" + is_get + '\'' +
                ", indate='" + indate + '\'' +
                ", emscomp='" + emscomp + '\'' +
                ", emsno='" + emsno + '\'' +
                '}';
    }
}
