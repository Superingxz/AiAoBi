package xinlan.com.AiAoBi.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/12/3.
 */
public class AMData {
    List<Mdata> mdata;
    List<Ddata> ddata;

    public List<Mdata> getMdata() {
        return mdata;
    }

    public List<Ddata> getDdata() {
        return ddata;
    }

    @Override
    public String toString() {
        return "AMData{" +
                "mdata=" + mdata +
                ", ddata=" + ddata +
                '}';
    }
}
