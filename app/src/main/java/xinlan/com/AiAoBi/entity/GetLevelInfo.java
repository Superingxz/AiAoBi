package xinlan.com.AiAoBi.entity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/27.
 */
public class GetLevelInfo {
    /**
     * {'res':'1','data':[{'name':'总裁','code':'1'},
     * {'name':'官方','code':'2'},
     * {'name':'总代','code':'3'},
     * {'name':'一级','code':'4'},
     * {'name':'经销商','code':'5'},
     * {'name':'特约','code':'6'},
     * {'name':'花瓣','code':'7'}],'msg':'获取数据成功'}
     */
    String res;
    ArrayList<data> data;
    String msg;
    public static class data{
        String name;
        String code;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<GetLevelInfo.data> getData() {
        return data;
    }

    public void setData(ArrayList<GetLevelInfo.data> data) {
        this.data = data;
    }
}
