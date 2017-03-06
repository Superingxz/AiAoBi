package xinlan.com.AiAoBi.requestJionIn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import xinlan.com.AiAoBi.R;

/**
 * Created by Administrator on 2016/10/26.
 */
public class MSpinnerAdapter extends BaseAdapter {
    private List list;
    private Context context;
    public MSpinnerAdapter(List list,Context context) {
        this.list = list;
        this.context=context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.adapter_spinner, null);
        TextView textView= (TextView) inflate.findViewById(R.id.adapter_spinner);

        return null;
    }
}
