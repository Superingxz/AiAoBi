package xinlan.com.AiAoBi.entity;

/**
 * Created by Administrator on 2016/12/21.
 */
public class RemindModel<T> {
    String type;
    String centent;
    T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCentent() {
        return centent;
    }

    public void setCentent(String centent) {
        this.centent = centent;
    }
}
