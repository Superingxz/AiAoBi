package xinlan.com.AiAoBi.entity;

/**
 * Created by Administrator on 2016/11/16.
 */
public class CustomerEnity {
    private  int res;
    private String string;

    public CustomerEnity(int res, String string) {
        this.res = res;
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public int getRes() {

        return res;
    }
}
